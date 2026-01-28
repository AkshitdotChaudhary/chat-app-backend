package com.example.demo.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResDto extends CommonResDto
{
    private Integer id;
    private String username;
    @JsonIgnore
    private String token;
}
