package com.example.demo.constant;

public enum ActivityStatusEnum {
    ACTIVE((byte) 1),
    DEACTIVE((byte) 0),
    DELETED((byte) 2);
    private Byte status;
    ActivityStatusEnum( Byte status )
    {
        this.setStatus( status );
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus( Byte status )
    {
        this.status = status;
    }
}
