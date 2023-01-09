package com.example.votingsystem.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Data
public class voteCycleRequest {
    @NotBlank(message = "start Date is mandatory")
    private String startDate;
    @NotBlank(message = "end Date is mandatory")
    private String endDate;


}
