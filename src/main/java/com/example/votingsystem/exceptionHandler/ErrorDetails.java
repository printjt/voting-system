package com.example.votingsystem.exceptionHandler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Setter
@Getter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ErrorDetails {
    private Map<String, String> errors;
    private String message;
    private String uri;
}
