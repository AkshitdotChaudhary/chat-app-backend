package com.example.demo.controller;

import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.impl.UserImpl;
import com.example.demo.utils.AESEncryptionUtil;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.constant.UrlMappingConstant.LOGIN;
import static com.example.demo.constant.UrlMappingConstant.SIGNUP;

@RestController
public class UserController
{
    @Autowired
    private UserImpl userImpl;
    @PostMapping(SIGNUP)
    public UserResDto signup( @RequestBody UserReqDto reqDto, HttpServletResponse inRes )
        throws Exception
    {
        UserResDto res = userImpl.signup( reqDto );
        inRes.setHeader( "token", AESEncryptionUtil.encrypt( res.getToken() ) );
        res.setToken( null );
        return res;
    }

    @PostMapping(LOGIN)
    public UserResDto login( @RequestBody UserReqDto req, HttpServletResponse inRes )
        throws Exception
    {
        UserResDto res = userImpl.login( req );
        inRes.setHeader( "token", AESEncryptionUtil.encrypt( res.getToken() ) );
        res.setToken( null );
        return res;
    }
}
