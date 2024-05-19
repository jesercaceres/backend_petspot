package com.petspot.exceptions;

public class DuplicatePetException extends RuntimeException {
    public DuplicatePetException(String message) {
        super(message);
    }
}