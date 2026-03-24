package com.example.demo.dto.utils;

import java.util.ArrayList;

import lombok.Data;

@Data
public class CommonResDto
{
    private String responseStatus;
    private String responseMessage;
    public void setStatus( ArrayList<String> inParamList )
    {
        this.responseStatus = inParamList.get( 0 );
        this.responseMessage = inParamList.get( 1 );
    }
}
