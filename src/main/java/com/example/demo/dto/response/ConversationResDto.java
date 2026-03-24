package com.example.demo.dto.response;

import java.util.List;

import com.example.demo.dto.utils.CommonResDto;

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
