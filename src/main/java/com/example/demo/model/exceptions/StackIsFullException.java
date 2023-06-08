package com.example.demo.model.exceptions;

public class StackIsFullException extends RuntimeException {
    public StackIsFullException() {
        super("Stack is full");
    }
}
