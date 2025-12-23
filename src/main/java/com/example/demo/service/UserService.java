package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepo userRepo;

    public Optional<User> findByUsername( String username){
        return userRepo.findByUsername( username );
    }
    public boolean existsByUsername(String username){
        return userRepo.existsByUsername( username );
    }
    public void saveUser( User user){
        userRepo.save( user );
    }
}
