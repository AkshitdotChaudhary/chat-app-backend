package com.example.demo.dto.response;

import com.example.demo.dto.utils.CommonResDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetConversationResDto
    extends
    CommonResDto
{
    public Integer id;
    public String  type;
    public String  title;
    public GetConversationResDto( Integer id, String type, String title )
    {
        super();
        this.id = id;
        this.type = type;
        this.title = title;
    }
}
