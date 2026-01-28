package com.example.demo.controller;

import com.example.demo.impl.ChatImpl;
import com.example.demo.dto.MessageResDto;
import com.example.demo.dto.SendMessageReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.example.demo.constant.UrlMappingConstant.WS_SEND_MESSAGE;
import static com.example.demo.constant.UrlMappingConstant.WS_CONVERSATION_TOPIC;

@Controller
public class ChatController
{
    @Autowired
    private ChatImpl              chatImpl;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @MessageMapping(WS_SEND_MESSAGE)
    //    @SendTo("/topic/conversation/{conversationId}")
    public MessageResDto send( @DestinationVariable Long conversationId, SendMessageReqDto message )
    {
        MessageResDto response = chatImpl.sendMessage( message );
        messagingTemplate.convertAndSend( WS_CONVERSATION_TOPIC + conversationId, response );
        return response;
    }
}
