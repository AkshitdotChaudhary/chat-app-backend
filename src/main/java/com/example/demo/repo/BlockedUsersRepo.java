package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BlockedUsers;

@Repository
public interface BlockedUsersRepo
    extends
    JpaRepository<BlockedUsers, Integer>
{
}
