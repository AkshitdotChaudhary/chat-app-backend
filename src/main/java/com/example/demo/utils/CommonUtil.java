package com.example.demo.utils;

import java.util.ArrayList;

import com.example.demo.constant.ServiceCodeEnum;
import com.example.demo.dto.utils.PageInfo;

public class CommonUtil
{
    public static PageInfo processPageInfo()
    {
        return new PageInfo();
    }

    public static ArrayList<String> getStatusParams( ServiceCodeEnum serviceCodeEnum )
    {
        ArrayList<String> paramList = new ArrayList<String>();
        paramList.add( serviceCodeEnum.getStatusCode() );
        paramList.add( serviceCodeEnum.getStatusDesc() );
        return paramList;
    }
}
