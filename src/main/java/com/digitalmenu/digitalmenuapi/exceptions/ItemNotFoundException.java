package com.digitalmenu.digitalmenuapi.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
