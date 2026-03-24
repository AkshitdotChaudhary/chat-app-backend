package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.demo.constant.UrlMappingConstant;
import com.example.demo.dto.request.UserReqDto;
import com.example.demo.dto.response.LoginResDto;
import com.example.demo.dto.response.UserResDto;
import com.example.demo.impl.UserImpl;
import com.example.demo.utils.AESEncryptionUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController
{
    @Autowired
    private UserImpl            userImpl;
    private static final Logger LOGGER = LogManager.getLogger( UserController.class );
    @PostMapping(value = UrlMappingConstant.REGISTER)
    public UserResDto signup( @RequestBody UserReqDto reqDto, HttpServletResponse inRes )
        throws Exception
    {
        UserResDto res = userImpl.signup( reqDto );
        String token = res.getToken();
        if ( token != null )
        {
            inRes.setHeader( "token", AESEncryptionUtil.encrypt( token ) );
            res.setToken( null );
        }
        return res;
    }

    @PostMapping(value = UrlMappingConstant.LOGIN)
    public LoginResDto login( @RequestBody UserReqDto req, HttpServletResponse inRes )
        throws Exception
    {
        LoginResDto res = userImpl.login( req );
        inRes.setHeader( "token", AESEncryptionUtil.encrypt( res.getUser().getToken() ) );
        res.getUser().setToken( null );
        LOGGER.info( "res : {}", res );
        return res;
    }

    @PostMapping(value = UrlMappingConstant.BLOCK_USER)
    public void blockUser( @PathVariable Integer userId,
                           @RequestHeader Map<String, String> httpHeaders,
                           HttpServletRequest inRequest,
                           HttpServletResponse inResponse )
    {
    }
}