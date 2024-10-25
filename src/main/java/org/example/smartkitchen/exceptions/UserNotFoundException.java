package org.example.smartkitchen.exceptions;

public class UserNotFoundException extends AbstractApiException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
