package com.rect2m.componentsforpc.domain.exception;

public class AuthException extends RuntimeException {

    public AuthException() {
        super("Не вірний логін чи пароль.");
    }
}
