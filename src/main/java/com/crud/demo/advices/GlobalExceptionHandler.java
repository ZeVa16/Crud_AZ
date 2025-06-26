package com.crud.demo.advices;

import com.crud.demo.dtos.ApiResponse;
import com.crud.demo.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException e) {

        ApiResponse<String> response =ApiResponse.<String>builder()
                .message(e.getMessage())
                .data(null)
                .status(e.getStatus())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

}
}
