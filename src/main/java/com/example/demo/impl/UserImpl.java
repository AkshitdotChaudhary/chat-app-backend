package com.example.demo.impl;

import com.example.demo.constant.ActivityStatusEnum;
import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserImpl
{
    @Autowired
    private UserService       userService;
    @Autowired
    private JwtUtils          jwtUtils;
    private static final Byte ACTIVE = ActivityStatusEnum.ACTIVE.getStatus();
    public UserResDto signup( UserReqDto req )
    {
        UserResDto res = new UserResDto();
        String username = req.getUsername();
        String password = req.getPassword();
        User user = userService.findUserByUsernameAndStatus( username, ACTIVE );
        if ( user != null )
        {
            res.setResponseMessage( "message" );
            res.setResponseStatus( 1 );
            res.setId( user.getId() );
            res.setUsername( username );
            return res;
        }
        res.setResponseMessage( "message" );
        res.setResponseStatus( 1 );
        userService.saveUser( new User( username, password ) );
        return res;
    }

    public UserResDto login( UserReqDto req )
    {
        UserResDto res = new UserResDto();
        User user = userService.findUserByUsernameAndStatus( req.getUsername(), ACTIVE );
        if ( user == null || !user.getPassword().equals( req.getPassword() ) )
        {
            return res;
        }
        res.setResponseStatus( 1 );
        res.setId( user.getId() );
        res.setToken( jwtUtils.generateToken( (long) user.getId(), "12345" ) );
        res.setUsername( user.getUsername() );
        return res;
    }
}