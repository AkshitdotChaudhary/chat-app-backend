package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConversationTypeEnum {
    PRIVATE("private"),
    GROUP("group");
    private final String conversationType;
}
