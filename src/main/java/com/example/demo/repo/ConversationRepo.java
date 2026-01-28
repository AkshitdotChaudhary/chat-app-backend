package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.GetConversationResDto;
import com.example.demo.model.Conversation;

@Repository
public interface ConversationRepo
    extends
    JpaRepository<Conversation, Integer>
{
    Conversation findByIdAndStatus( Integer id, Byte status );

    @Query("""
                SELECT new com.example.demo.dto.GetConversationResDto(
                    c.id, c.type, c.title
                )
                FROM ConversationMembers cm
                JOIN cm.conversation c
                WHERE cm.user.id = :userId
                  AND cm.status = 1
            """)
    List<GetConversationResDto> findConversationsByUserId( Integer userId );
}
