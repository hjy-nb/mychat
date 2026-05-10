package com.yao.mychat.service.except;

public class PasswordError extends RuntimeException{
    public PasswordError(String message) {
        super(message);
    }

    public PasswordError(String message, Throwable cause) {
        super(message, cause);
    }
}
