package com.example.demo.controllers;

import lombok.Setter;

@Setter
public class UserLoginResponseDto {
    public String username;
    public String token;

    public UserLoginResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
