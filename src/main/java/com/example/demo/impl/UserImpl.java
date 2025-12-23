package com.example.demo.impl;

import com.example.demo.dto.UserReqDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserImpl
{
    @Autowired
    private UserService userService;

    public UserResDto signup(UserReqDto req) {
        UserResDto res = new UserResDto();
        String username = req.getUsername();
        String password = req.getPassword();
        Optional<User> user = userService.findByUsername( username);
        if (user.isPresent()) {
            res.setId( user.get().getId() );
            res.setUsername( username );
            return res;
        }
        userService.saveUser(new User( username, password));
        return res;
    }

    public UserResDto login(UserReqDto req) {
        UserResDto res = new UserResDto();
        User user = userService.findByUsername(req.getUsername()).orElse(null);
        if (user == null || !user.getPassword().equals(req.getPassword())) {
            return res;
        }
        res.setId( user.getId() );
        res.setUsername( user.getUsername() );
        return res;
    }
}