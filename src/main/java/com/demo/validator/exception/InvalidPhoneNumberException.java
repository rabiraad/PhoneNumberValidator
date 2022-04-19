package com.demo.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPhoneNumberException extends RuntimeException {
  public InvalidPhoneNumberException(String message) {
    super(message);
  }
}
