package com.mashreq.validator.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(BindingResult bindingResult) {
        Map<String, String> errorMap=new HashMap<String, String>();
        if(bindingResult.hasErrors()) {

        List<FieldError> fieldErrorsList=bindingResult.getFieldErrors();
        for(FieldError error:fieldErrorsList) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
    }
        return ResponseEntity.badRequest().body(errorMap);
}

}