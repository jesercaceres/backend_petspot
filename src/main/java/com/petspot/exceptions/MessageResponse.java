package com.petspot.exceptions;

public class MessageResponse {

    private Boolean hasError;

    private String message;

    public MessageResponse(String message, Boolean hasError) {
        this.message = message;
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }
}