package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetConversationResDto
{
    public Integer id;
    public String  type;
    public String  title;
    public GetConversationResDto( Integer id, String type, String title )
    {
        this.id = id;
        this.type = type;
        this.title = title;
    }
}
