package com.digitalmenu.digitalmenuapi.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
