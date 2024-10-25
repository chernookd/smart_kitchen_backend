package org.example.smartkitchen.exceptions;

public abstract class AbstractApiException extends RuntimeException {
    public AbstractApiException(String message) {
        super(message);
    }
}
