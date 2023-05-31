package com.example.demo.model.exceptions;

public class MaximumSizeSmallerOrEqualToZeroException extends RuntimeException {
  public MaximumSizeSmallerOrEqualToZeroException() {
    super("The maximum size should be bigger than zero");
  }
}
