package com.example.demo.impl;

import com.example.demo.dto.MessageDto;
import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatImpl
{
    @Autowired
    private MessageService messageService;

    public MessageDto saveMessage( MessageDto message){
        Message msg = new Message( message.getFrom(), message.getText() );
        messageService.saveMessage(msg);
        return message;
    }
}
