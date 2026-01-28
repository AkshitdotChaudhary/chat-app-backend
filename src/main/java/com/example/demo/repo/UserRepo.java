package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo
    extends
    JpaRepository<User, Integer>
{
    User findByUsernameAndStatus( String username, Byte status );

    User findByIdAndStatus( Integer id, Byte status );

    boolean existsByUsername( String username );
}
