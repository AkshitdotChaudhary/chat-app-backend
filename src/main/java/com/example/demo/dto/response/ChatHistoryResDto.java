package com.example.demo.dto.response;

import java.util.List;

import com.example.demo.dto.utils.CommonResDto;
import com.example.demo.dto.utils.PageInfo;

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
