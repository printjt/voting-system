package com.example.votingsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    @JsonProperty(namespace = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @JsonProperty(namespace = "password")
    @NotBlank(message = "Password is mandatory")
    private String password;
}
