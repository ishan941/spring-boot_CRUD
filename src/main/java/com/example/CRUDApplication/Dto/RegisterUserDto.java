package com.example.CRUDApplication.Dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;
    private String password;
    private String username;
    private String role;
}