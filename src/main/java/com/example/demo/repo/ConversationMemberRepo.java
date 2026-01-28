package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ConversationMembers;

@Repository
public interface ConversationMemberRepo
    extends
    JpaRepository<ConversationMembers, Integer>
{
    boolean existsByConversationIdAndUserId( Integer conversationId, Integer userId );

    List<ConversationMembers> findByConversationId( Integer conversationId );

    List<ConversationMembers> findByUserId( Integer userId );
}
