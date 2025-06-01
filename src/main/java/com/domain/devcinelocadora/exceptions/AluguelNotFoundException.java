package com.domain.devcinelocadora.exceptions;

public class AluguelNotFoundException extends RuntimeException {
    public AluguelNotFoundException(String message) {
        super(message);
    }
}
