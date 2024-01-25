package com.rect2m.componentsforpc.domain.exception;

public class UserAlreadyAuthException extends RuntimeException {

    public UserAlreadyAuthException(String message) {
        super(message);
    }
}
