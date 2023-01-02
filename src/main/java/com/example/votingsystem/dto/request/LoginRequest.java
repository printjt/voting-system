package com.example.votingsystem.dto.request;

import com.example.votingsystem.model.User;
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

    public static User toUserAuthData(User user){
        return new User(user.getId(),user.getUsername(),null,user.getRole(),user.getDateOfBirth(),user.getNationalNumber(),user.getFullName(),user.getPhone(),user.isNotLocked(),user.isNotExpired(),user.isEnabled());
    }
}
