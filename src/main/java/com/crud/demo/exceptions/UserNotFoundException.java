package com.crud.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends RuntimeException {
    final HttpStatus status = HttpStatus.NOT_FOUND;
    public UserNotFoundException(String message) {
        super(message);
    }

}
