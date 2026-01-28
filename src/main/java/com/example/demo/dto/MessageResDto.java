package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResDto
{
    private Integer       messageId;
    private Integer       conversationId;
    private Integer       senderId;
    private String        content;
    private LocalDateTime createdAt;
}
