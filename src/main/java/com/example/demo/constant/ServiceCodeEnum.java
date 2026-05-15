package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceCodeEnum {
    SUCCESS("001", "Your request has been processed successfully"),
    NO_RECORD("002", "No record found"),
    VALIDATION_ERROR("003", "Invalid Request, Validation Error"),
    UNABLE_TO_PROCESS("004", "Unable to process your request, please try later"),
    INVALID_CREDENTIALS("005", "Invalid Credentials"),
    USER_NOT_FOUND("006", "User not found"),
    AUTHENTICATION_FAILED("007", "Authentication failed"),
    DUPLICATE_REQUEST("008", "Record is Already Exist");
    private final String statusCode;
    private final String statusDesc;
}
