package com.demo.validator.constants;

public class ErrorMessages {
  private ErrorMessages() {}

  public static final String INVALID_PHONE_NUMBER_FORMAT =
      "Phone number should start with + sign and country dial code. Phone number should only contain digits";
  public static final String INVALID_PHONE_NUMBER = "Invalid phone number for given country/region";
}
