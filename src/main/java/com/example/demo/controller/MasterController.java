package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.UrlMappingConstant;
import com.example.demo.dto.request.StartConversationReqDto;
import com.example.demo.dto.response.ChatHistoryResDto;
import com.example.demo.dto.response.ConversationResDto;
import com.example.demo.dto.response.GetConversationResDto;
import com.example.demo.impl.MasterImpl;

@RestController
public class MasterController
{
    @Autowired
    private MasterImpl          masterImpl;
    private static final Logger LOGGER = LogManager.getLogger( MasterController.class );
    @GetMapping(value = UrlMappingConstant.GET_USER_CONVERSATIONS)
    public ConversationResDto getConversation( @PathVariable Integer userId )
    {
        LOGGER.info( "in getConversation controller, userId : {}", userId );
        return masterImpl.getConversationByUserId( userId );
    }

    @PostMapping(value = UrlMappingConstant.CREATE_CONVERSATION)
    public GetConversationResDto startConversation( @RequestBody StartConversationReqDto req )
    {
        return masterImpl.startConverstion( req );
    }

    @GetMapping(value = UrlMappingConstant.GET_MESSAGES)
    public ChatHistoryResDto getMessages( @PathVariable Integer conversationId,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size )
    {
        return masterImpl.getMessages( conversationId, page, size );
    }
}
