package com.example.demo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GetConversationResDto;
import com.example.demo.model.Conversation;
import com.example.demo.model.ConversationMembers;
import com.example.demo.repo.ConversationMemberRepo;
import com.example.demo.repo.ConversationRepo;

@Service
public class ConversationService
{
    @Autowired
    private ConversationRepo       conversationRepo;
    @Autowired
    private ConversationMemberRepo conversationMemberRepo;
    private static final Logger    LOGGER = LogManager.getLogger( ConversationService.class );
    public Conversation findConversationByIdAndStatus( Integer id, Byte status )
    {
        return conversationRepo.findByIdAndStatus( id, status );
    }

    public List<GetConversationResDto> getConversationsByUserId( Integer userId )
    {
        List<GetConversationResDto> res = conversationRepo.findConversationsByUserId( userId );
        LOGGER.info( res.isEmpty() ? "Conversation list is empty"
                                   : "conversation list found with length : " + res.size() );
        return res;
    }

    public Conversation saveConversation( Conversation conversation )
    {
        Conversation res = conversationRepo.save( conversation );
        LOGGER.info( "Conversation saved with id : {}", res.getId() );
        return res;
    }

    public List<ConversationMembers> addMembersToConversation( List<ConversationMembers> conversationMembers )
    {
        List<ConversationMembers> res = conversationMemberRepo.saveAll( conversationMembers );
        LOGGER.info( res.isEmpty() ? "Members not added to Conversation"
                                   : res.size() + " Members added to conversation" );
        return res;
    }
}
