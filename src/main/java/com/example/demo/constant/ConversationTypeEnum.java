package com.example.demo.constant;

public enum ConversationTypeEnum {
    PRIVATE("private"),
    GROUP("group");
    private String conversationType;
    ConversationTypeEnum( String conversationType )
    {
        this.setConversationtype( conversationType );
    }

    public String getConversationtype()
    {
        return conversationType;
    }

    public void setConversationtype( String conversationType )
    {
        this.conversationType = conversationType;
    }
}
