package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class ConversationResDto
    extends
    CommonResDto
{
    public ConversationResDto()
    {
        super();
    }
    List<GetConversationResDto> conversations;
}
