package com.example.demo.dto.response;

import com.example.demo.dto.utils.CommonResDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResDto
    extends
    CommonResDto
{
    UserResDto user;
}
