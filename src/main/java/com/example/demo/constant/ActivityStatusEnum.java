package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityStatusEnum {
    DEACTIVE((byte) 0),
    ACTIVE((byte) 1),
    DELETED((byte) 2);
    private final Byte status;
}
