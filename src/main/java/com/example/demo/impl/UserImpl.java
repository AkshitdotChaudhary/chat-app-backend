package com.example.demo.impl;

import com.example.demo.constant.ActivityStatusEnum;
import com.example.demo.constant.ServiceCodeEnum;
import com.example.demo.dto.request.UserReqDto;
import com.example.demo.dto.response.LoginResDto;
import com.example.demo.dto.response.UserResDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.CommonUtil;
import com.example.demo.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserImpl
{
    @Autowired
    private UserService       userService;
    @Autowired
    private JwtUtils          jwtUtils;
    @Autowired
    private PasswordEncoder   passwordEncoder;
    private static final Byte ACTIVE = ActivityStatusEnum.ACTIVE.getStatus();
    public UserResDto signup( UserReqDto req )
    {
        ServiceCodeEnum serviceCode = ServiceCodeEnum.UNABLE_TO_PROCESS;
        UserResDto res = new UserResDto();
        String username = req.getUsername();
        String password = req.getPassword();
        User user = userService.findUserByUsernameAndStatus( username, ACTIVE );
        if ( user != null )
        {
            serviceCode = ServiceCodeEnum.DUPLICATE_REQUEST;
        }
        else
        {
            userService.saveUser( new User( username, passwordEncoder.encode( password ), ACTIVE ) );
            serviceCode = ServiceCodeEnum.SUCCESS;
        }
        res.setStatus( CommonUtil.getStatusParams( serviceCode ) );
        return res;
    }

    public LoginResDto login( UserReqDto req )
    {
        LoginResDto res = new LoginResDto();
        UserResDto userResDto = new UserResDto();
        ServiceCodeEnum serviceCode = ServiceCodeEnum.UNABLE_TO_PROCESS;
        User user = userService.findUserByUsernameAndStatus( req.getUsername(), ACTIVE );
        if ( user == null || !passwordEncoder.matches( req.getPassword(), user.getPassword() ) )
        {
            serviceCode = ServiceCodeEnum.AUTHENTICATION_FAILED;
        }
        else
        {
            serviceCode = ServiceCodeEnum.SUCCESS;
            userResDto.setId( user.getId() );
            userResDto.setToken( jwtUtils.generateToken( (long) user.getId(), "12345" ) );
            userResDto.setUsername( user.getUsername() );
            res.setUser( userResDto );
        }
        res.setStatus( CommonUtil.getStatusParams( serviceCode ) );
        return res;
    }

    public String blockUnBlock()
    {
        return null;
    }
}