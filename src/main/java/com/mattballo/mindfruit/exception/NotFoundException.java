package com.mattballo.mindfruit.exception;

import java.util.NoSuchElementException;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}