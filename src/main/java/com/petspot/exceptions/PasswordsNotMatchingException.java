package com.petspot.exceptions;

public class PasswordsNotMatchingException extends Exception {
    public PasswordsNotMatchingException(String message) {
        super(message);
    }
}