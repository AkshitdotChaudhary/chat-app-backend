package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTypeEnum {
    TEXT("text"),
    IMAGE("image"),
    AUDIO("audio"),
    VIDEO("video");
    private final String messageType;
}