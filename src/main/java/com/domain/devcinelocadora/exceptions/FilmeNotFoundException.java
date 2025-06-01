package com.domain.devcinelocadora.exceptions;

public class FilmeNotFoundException extends RuntimeException {
    public FilmeNotFoundException(String message) {
        super(message);
    }
}
