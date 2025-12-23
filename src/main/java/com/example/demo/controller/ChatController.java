package com.example.demo.controller;

import com.example.demo.impl.ChatImpl;
import com.example.demo.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.example.demo.constant.UrlMappingConstant.SEND_CHAT;
import static com.example.demo.constant.UrlMappingConstant.TOPIC_MESSAGE;

@Controller
public class ChatController
{
    @Autowired
    private ChatImpl chatImpl;

    @MessageMapping(SEND_CHAT)
    @SendTo(TOPIC_MESSAGE)
    public MessageDto send( MessageDto message) {
        return chatImpl.saveMessage( message );
    }
}
