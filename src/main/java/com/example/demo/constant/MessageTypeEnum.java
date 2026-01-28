package com.example.demo.constant;

public enum MessageTypeEnum {
    TEXT("text"),
    IMAGE("image"),
    AUDIO("audio"),
    VIDEO("video");
    private String messageType;
    MessageTypeEnum( String messageType )
    {
        setMessageType( messageType );
    }

    public String getMessageType()
    {
        return messageType;
    }

    public void setMessageType( String messageType )
    {
        this.messageType = messageType;
    }
}