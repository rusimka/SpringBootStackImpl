package com.example.demo.model.exceptions;

public class StackIsEmptyException extends RuntimeException {
    public StackIsEmptyException() {
        super("Stack is empty");
    }
}
