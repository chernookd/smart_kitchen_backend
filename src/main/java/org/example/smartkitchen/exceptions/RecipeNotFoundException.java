package org.example.smartkitchen.exceptions;

public class RecipeNotFoundException extends AbstractApiException {
    public RecipeNotFoundException(String message) {
        super(message);
    }
}
