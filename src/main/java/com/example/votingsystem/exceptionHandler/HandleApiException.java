package com.example.votingsystem.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestControllerAdvice
@RestController
public class HandleApiException  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleApiException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> mapError = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((FieldError error) ->
                mapError.put(error.getField(), error.getDefaultMessage())
        );
        ErrorDetails errorDetails = ErrorDetails.build(mapError, "Validation Error", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetail> handleApiException(RuntimeException me, WebRequest request) {
        ErrorDetail details = new ErrorDetail(me.getClass().getSimpleName(), request.getDescription(false));
        details.addError(me.getLocalizedMessage());
        return new ResponseEntity<>(details, BAD_REQUEST);
    }


}
