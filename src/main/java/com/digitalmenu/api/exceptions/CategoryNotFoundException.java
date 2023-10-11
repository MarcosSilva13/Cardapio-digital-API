package com.digitalmenu.api.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
