package com.demo.validator.handler;

import com.demo.validator.exception.InvalidPhoneNumberException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class RestExceptionHandler extends ExceptionHandlerExceptionResolver {
  @ExceptionHandler({InvalidPhoneNumberException.class})
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity handle(InvalidPhoneNumberException exception) {

    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }
}
