package com.example.demo.controller;

import com.example.demo.impl.ChatImpl;
import com.example.demo.constant.UrlMappingConstant;
import com.example.demo.dto.request.SendMessageReqDto;
import com.example.demo.dto.response.MessageResDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController
{
    @Autowired
    private ChatImpl              chatImpl;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @MessageMapping(value = UrlMappingConstant.WS_SEND_MESSAGE)
    //    @SendTo("/topic/conversation/{conversationId}")
    public MessageResDto send( @DestinationVariable Long conversationId, SendMessageReqDto message )
    {
        MessageResDto response = chatImpl.sendMessage( message );
        messagingTemplate.convertAndSend( UrlMappingConstant.WS_CONVERSATION_TOPIC + conversationId, response );
        return response;
    }
}
