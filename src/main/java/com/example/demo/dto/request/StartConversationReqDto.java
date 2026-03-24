package com.example.demo.dto.request;

import java.util.List;

import com.example.demo.constant.ConversationTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StartConversationReqDto
{
    private ConversationTypeEnum conversationType;
    private String               title;
    private List<Integer>        members;
}
