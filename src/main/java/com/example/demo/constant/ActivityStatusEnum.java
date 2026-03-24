package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityStatusEnum {
    ACTIVE((byte) 1),
    DEACTIVE((byte) 0),
    DELETED((byte) 2);
    private final Byte status;
}
