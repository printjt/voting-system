package com.example.votingsystem.validation;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private String field;
    private String message;
    private String rejectedValue;


    public static Map<String, Object> setValidationMessages(BindingResult result) {
        Map<String, Object> res = new HashMap<>();
        List<ErrorMessage> validationMessages = new ArrayList<>();
        for (ObjectError validationMessage : result.getAllErrors()
        )
            validationMessages.add(new ErrorMessage(validationMessage.getCode(), validationMessage.getDefaultMessage(), validationMessage.getObjectName()));

        res.put("errors", validationMessages);
        res.put("status", false);

        return res;

    }

    public static ResponseEntity<?> sendValidationMessages(BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(setValidationMessages(result));

        return null;
    }

}
