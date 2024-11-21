package com.travelapp.backend.common.exception;

import org.springframework.http.HttpStatus;

public class WrongPasswordException extends BusinessException {
    public WrongPasswordException(HttpStatus status, String message) {
        super(status, message);
    }

    public WrongPasswordException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
