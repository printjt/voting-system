package com.example.votingsystem.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "role is mandatory")
    private String role;
    private String dateOfBirth;
    @NotBlank(message = "national number is mandatory")
    private String nationalNumber;
    private String fullName;
    private String phone;



}
