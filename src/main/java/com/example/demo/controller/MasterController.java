package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ChatHistoryResDto;
import com.example.demo.dto.ConversationResDto;
import com.example.demo.dto.GetConversationResDto;
import com.example.demo.dto.MessageResDto;
import com.example.demo.dto.StartConversationReqDto;
import com.example.demo.impl.MasterImpl;

import static com.example.demo.constant.UrlMappingConstant.GET_CONVERSATIONS;
import static com.example.demo.constant.UrlMappingConstant.GET_MESSAGES;

import java.util.List;

import static com.example.demo.constant.UrlMappingConstant.CREATE_CONVERSATION;

@RestController
public class MasterController
{
    @Autowired
    private MasterImpl          masterImpl;
    private static final Logger LOGGER = LogManager.getLogger( MasterController.class );
    @GetMapping(GET_CONVERSATIONS)
    public ConversationResDto getConversation( @PathVariable Integer userId )
    {
        return masterImpl.getConversationByUserId( userId );
    }

    @PostMapping(CREATE_CONVERSATION)
    public GetConversationResDto startConversation( @RequestBody StartConversationReqDto req )
    {
        return masterImpl.startConverstion( req );
    }

    @GetMapping(GET_MESSAGES)
    public ChatHistoryResDto getMessages( @PathVariable Integer conversationId, Pageable pageable )
    {
        return masterImpl.getMessages( conversationId, pageable );
    }
}
