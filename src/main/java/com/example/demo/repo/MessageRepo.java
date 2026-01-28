package com.example.demo.repo;

import com.example.demo.model.Message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo
    extends
    JpaRepository<Message, Integer>
{
    Page<Message> findByConversationIdOrderByCreatedAtDesc( Integer conversationId, Pageable pageable );
}
