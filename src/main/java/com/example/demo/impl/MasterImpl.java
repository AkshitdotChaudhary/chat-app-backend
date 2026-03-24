package com.example.demo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.demo.constant.ActivityStatusEnum;
import com.example.demo.constant.ServiceCodeEnum;
import com.example.demo.dto.request.StartConversationReqDto;
import com.example.demo.dto.response.ChatHistoryResDto;
import com.example.demo.dto.response.ConversationResDto;
import com.example.demo.dto.response.GetConversationResDto;
import com.example.demo.dto.response.MessageResDto;
import com.example.demo.model.Conversation;
import com.example.demo.model.ConversationMembers;
import com.example.demo.model.User;
import com.example.demo.service.ConversationService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import com.example.demo.utils.CommonUtil;

@Component
public class MasterImpl
{
    @Autowired
    private MessageService      messageService;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private UserService         userService;
    private static final Logger LOGGER = LogManager.getLogger( MasterImpl.class );
    private static final Byte   ACTIVE = ActivityStatusEnum.ACTIVE.getStatus();
    public ConversationResDto getConversationByUserId( Integer userId )
    {
        ServiceCodeEnum serviceCode = ServiceCodeEnum.UNABLE_TO_PROCESS;
        List<GetConversationResDto> conversations = conversationService.getConversationsByUserId( userId );
        ConversationResDto res = new ConversationResDto();
        res.setConversations( conversations );
        serviceCode = ServiceCodeEnum.SUCCESS;
        res.setStatus( CommonUtil.getStatusParams( serviceCode ) );
        LOGGER.info( "" );
        return res;
    }

    public GetConversationResDto startConverstion( StartConversationReqDto req )
    {
        ServiceCodeEnum serviceCode = ServiceCodeEnum.UNABLE_TO_PROCESS;
        GetConversationResDto res = new GetConversationResDto();
        Conversation conversation = conversationService
                .saveConversation( new Conversation( req.getConversationType().getConversationType(),
                                                     req.getTitle(),
                                                     ACTIVE ) );
        List<User> users = userService.findUsersByIds( req.getMembers() );
        List<ConversationMembers> conversationMembers = new ArrayList<ConversationMembers>();
        for ( User user : users )
        {
            ConversationMembers conversationMember = new ConversationMembers( conversation, user, ACTIVE );
            conversationMembers.add( conversationMember );
        }
        conversationService.addMembersToConversation( conversationMembers );
        res.setId( conversation.getId() );
        res.setTitle( conversation.getTitle() );
        res.setType( conversation.getType() );
        serviceCode = ServiceCodeEnum.SUCCESS;
        res.setStatus( CommonUtil.getStatusParams( serviceCode ) );
        return res;
    }

    public ChatHistoryResDto getMessages( Integer conversationId, int page, int size )
    {
        ChatHistoryResDto res = new ChatHistoryResDto();
        ServiceCodeEnum serviceCode = ServiceCodeEnum.UNABLE_TO_PROCESS;
        Pageable pageable = PageRequest.of( page, size );
        List<MessageResDto> messageList = messageService.getMessages( conversationId, pageable ).stream()
                .map( msg -> new MessageResDto( msg.getId(),
                                                msg.getConversation().getId(),
                                                msg.getSender().getId(),
                                                msg.getContent(),
                                                msg.getCreatedAt() ) )
                .toList();
        res.setMessages( messageList );
        serviceCode = ServiceCodeEnum.SUCCESS;
        res.setStatus( CommonUtil.getStatusParams( serviceCode ) );
        return res;
    }
}