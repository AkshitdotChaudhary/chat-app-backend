package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChatHistoryResDto
    extends
    CommonResDto
{
    public ChatHistoryResDto()
    {
        super();
    }
    List<MessageResDto> messages;
    PageInfo            pageInfo;
}
