package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepo            userRepo;
    private static final Logger LOGGER = LogManager.getLogger( UserService.class );
    public User findUserByUsernameAndStatus( String username, Byte status )
    {
        User res = userRepo.findByUsernameAndStatus( username, status );
        LOGGER.info( "User Found with username '{}' and status '{}'", username, status );
        return res;
    }

    public List<User> findUsersByIds( List<Integer> ids )
    {
        List<User> res = userRepo.findAllById( ids );
        LOGGER.info( "Users Found with ids '{}'", ids );
        return res;
    }

    public User findUserByIdAndStatus( Integer id, Byte status )
    {
        return userRepo.findByIdAndStatus( id, status );
    }

    public boolean existsByUsername( String username )
    {
        return userRepo.existsByUsername( username );
    }

    public void saveUser( User user )
    {
        userRepo.save( user );
    }
}
