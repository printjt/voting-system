package com.example.votingsystem.dto.request;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Data
public class NewCandidateRequest {

    @NotBlank(message = "name is mandatory")
    public String name;

    @NotBlank(message = "party is mandatory")
    public String party;

    @Lob
    public byte[] image;

    public String description;

}
