package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService
{
    @Autowired
    private MessageRepo messageRepo;

    public Message saveMessage( Message msg ){
        return messageRepo.save( msg);
    }
}
