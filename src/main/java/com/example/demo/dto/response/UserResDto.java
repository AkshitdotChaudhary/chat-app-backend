package com.example.demo.dto.response;

import lombok.Data;

import com.example.demo.dto.utils.CommonResDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResDto
    extends
    CommonResDto
{
    private Integer id;
    private String  username;
}
