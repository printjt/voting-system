package com.example.votingsystem.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@Setter
@Getter
public class ErrorDetail {
    private List<String> errors;
    private String message;
    private String uri;
    private boolean success;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ErrorDetail(String message, String uri) {
        this();
        this.message = message;
        this.uri = uri;
        this.success = false;
        this.errors = new ArrayList<>();
    }

    public ErrorDetail() {
        this.timestamp = LocalDateTime.now();
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
