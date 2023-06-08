package com.example.demo.model.exceptions;

public class StackDoesNotExistException extends RuntimeException {
    public StackDoesNotExistException() {
        super("Stack does not exist");
    }
}
