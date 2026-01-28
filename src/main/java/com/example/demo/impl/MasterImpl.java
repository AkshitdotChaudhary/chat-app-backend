package com.example.demo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.demo.constant.ActivityStatusEnum;
import com.example.demo.dto.ChatHistoryResDto;
import com.example.demo.dto.ConversationResDto;
import com.example.demo.dto.GetConversationResDto;
import com.example.demo.dto.MessageResDto;
import com.example.demo.dto.StartConversationReqDto;
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
    private final MessageService messageService;
    @Autowired
    private ConversationService  conversationService;
    @Autowired
    private UserService          userService;
    private static final Byte    ACTIVE = ActivityStatusEnum.ACTIVE.getStatus();
    MasterImpl( MessageService messageService )
    {
        this.messageService = messageService;
    }

    public ConversationResDto getConversationByUserId( Integer userId )
    {
        List<GetConversationResDto> conversations = conversationService.getConversationsByUserId( userId );
        ConversationResDto res = new ConversationResDto();
        res.setResponseStatus( 123 );
        res.setResponseMessage( "got" );
        res.setConversations( conversations );
        return res;
    }

    public GetConversationResDto startConverstion( StartConversationReqDto req )
    {
        GetConversationResDto res = new GetConversationResDto();
        Conversation conversation = conversationService
                .saveConversation( new Conversation( req.getConversationType().getConversationtype(),
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
        return res;
    }

    public ChatHistoryResDto getMessages( Integer conversationId, Pageable pageable )
    {
        ChatHistoryResDto res = new ChatHistoryResDto();
        List<MessageResDto> messageList = messageService.getMessages( conversationId, pageable ).stream()
                .map( msg -> new MessageResDto( msg.getId(),
                                                msg.getConversation().getId(),
                                                msg.getSender().getId(),
                                                msg.getContent(),
                                                msg.getCreatedAt() ) )
                .toList();
        res.setMessages( messageList );
        return res;
    }
}
