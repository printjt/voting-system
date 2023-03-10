package com.example.votingsystem.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VoteRequest {

    @NotNull(message = "candidate Id is mandatory")
    private Long candidateId;

    @NotNull(message = "username is mandatory")
    private String username;

    private Long nationalId;

}
