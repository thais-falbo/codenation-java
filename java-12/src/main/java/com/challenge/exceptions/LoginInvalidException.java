package com.challenge.exceptions;

public class LoginInvalidException extends RuntimeException {
    public LoginInvalidException() {
        super("Login inválido");
    }
}
