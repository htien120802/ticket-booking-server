package com.internship.coachbookingapi.handler;

import com.internship.coachbookingapi.model.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e) {
        return ResponseEntity.ok(ResponseModel.builder()
                .status(400)
                .error(true)
                .message(e.getMessage())
                .build());
    }
}
