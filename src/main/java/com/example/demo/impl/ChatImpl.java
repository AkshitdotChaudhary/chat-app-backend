package com.example.demo.impl;

import com.example.demo.constant.ActivityStatusEnum;
import com.example.demo.constant.MessageTypeEnum;
import com.example.demo.dto.request.SendMessageReqDto;
import com.example.demo.dto.response.MessageResDto;
import com.example.demo.model.Conversation;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.service.ConversationService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatImpl
{
    @Autowired
    private MessageService      messageService;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private UserService         userService;
    private static final Byte   ACTIVE = ActivityStatusEnum.ACTIVE.getStatus();
    public MessageResDto sendMessage( SendMessageReqDto req )
    {
        Conversation conversation = conversationService.findConversationByIdAndStatus( req.getConversationId(),
                                                                                       ACTIVE );
        User sender = userService.findUserByIdAndStatus( req.getSenderId(), ACTIVE );
        Message msg = new Message();
        msg.setConversation( conversation );
        msg.setSender( sender );
        msg.setContent( req.getContent() );
        msg.setMessageType( MessageTypeEnum.TEXT.getMessageType() );
        messageService.saveMessage( msg );
        // create delivery statuses
        //        memberRepo.findByConversationId(conversation.getId())
        //                .forEach(m -> {
        //                    MessageStatus ms = new MessageStatus();
        //                    ms.setMessage(msg);
        //                    ms.setUser(m.getUser());
        //                    ms.setStatus(DeliveryStatus.SENT);
        //                    statusRepo.save(ms);
        //                });
        return new MessageResDto( msg
                .getId(), conversation.getId(), sender.getId(), msg.getContent(), msg.getCreatedAt() );
    }
}
