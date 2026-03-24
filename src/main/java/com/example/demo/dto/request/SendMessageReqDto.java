package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageReqDto
{
    private Integer conversationId;
    private Integer senderId;
    private String  content;
}
