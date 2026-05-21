package com.example.demo.impl;

import com.example.demo.constant.ActivityStatusEnum;
import com.example.demo.constant.ServiceCodeEnum;
import com.example.demo.dto.request.UserReqDto;
import com.example.demo.dto.response.UserResDto;
import com.example.demo.model.SessionToken;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.CommonUtil;
import com.example.demo.utils.JwtUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
    private static final Byte ACTIVE   = ActivityStatusEnum.ACTIVE.getStatus();
    private static final Byte DEACTIVE = ActivityStatusEnum.DEACTIVE.getStatus();
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
            userService.saveUser( new User( username, passwordEncoder.encode( password ), DEACTIVE ) );
            serviceCode = ServiceCodeEnum.SUCCESS;
        }
        res.setStatus( CommonUtil.getStatusParams( serviceCode ) );
        return res;
    }

    public UserResDto login( UserReqDto req )
    {
        UserResDto res = new UserResDto();
        SessionToken sessionToken = new SessionToken();
        ServiceCodeEnum serviceCode = ServiceCodeEnum.UNABLE_TO_PROCESS;
        User user = userService.findUserByUsernameAndStatus( req.getUsername(), ACTIVE );
        if ( user == null )
        {
            serviceCode = ServiceCodeEnum.USER_NOT_FOUND;
        }
        else
        {
            if ( !passwordEncoder.matches( req.getPassword(), user.getPassword() ) )
            {
                serviceCode = ServiceCodeEnum.INVALID_CREDENTIALS;
            }
            else
            {
                String token = jwtUtils.generateToken( (long) user.getId(), "12345" );
                sessionToken.setToken( token );
                sessionToken.setLoginUser( user );
                sessionToken.setStatus( ACTIVE );
                sessionToken.setLoginDate( LocalDateTime.now().atZone( ZoneOffset.UTC ).toInstant().toEpochMilli() );
                userService.saveSessionToken( sessionToken );
                serviceCode = ServiceCodeEnum.SUCCESS;
                res.setId( user.getId() );
                res.setToken( token );
                res.setUsername( user.getUsername() );
            }
        }
        res.setStatus( CommonUtil.getStatusParams( serviceCode ) );
        return res;
    }

    public String blockUnBlock()
    {
        return null;
    }
}