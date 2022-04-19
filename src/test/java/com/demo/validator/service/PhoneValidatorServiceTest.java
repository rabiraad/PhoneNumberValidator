package com.demo.validator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.demo.validator.constants.ErrorMessages;
import com.demo.validator.exception.InvalidPhoneNumberException;
import com.demo.validator.model.PhoneNumberDetails;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class PhoneValidatorServiceTest {

  @Test
  public void testGetPhoneNumberDetailsAfterValidation() throws IOException {
    PhoneValidatorService phoneValidatorService = new PhoneValidatorService();
    PhoneNumberDetails phoneDetailsActual =
        phoneValidatorService.getPhoneNumberDetailsAfterValidation("+96170173804");
    PhoneNumberDetails phoneDetailsExpected =
        new PhoneNumberDetails("+961", "Lebanon", "NOT_SUPPORTED");
    assertEquals(phoneDetailsExpected, phoneDetailsActual);
  }

  @Test
  public void testGetPhoneNumberDetailsAfterValidation_invalidPhoneNumber() throws IOException {
    PhoneValidatorService phoneValidatorService = new PhoneValidatorService();
    try {
      phoneValidatorService.getPhoneNumberDetailsAfterValidation("+961701704");
      fail("should not reach: the previous call should throw an exception.");
    } catch (InvalidPhoneNumberException e) {
      assertEquals(e.getMessage(), ErrorMessages.INVALID_PHONE_NUMBER);
    }
  }

  @Test
  public void testGetPhoneNumberDetailsAfterValidation_notParsablePhoneNumber() throws IOException {
    PhoneValidatorService phoneValidatorService = new PhoneValidatorService();
    try {
      phoneValidatorService.getPhoneNumberDetailsAfterValidation("0096170173804");
      fail("should not reach: the previous call should throw an exception.");
    } catch (InvalidPhoneNumberException e) {
      assertEquals(e.getMessage(), ErrorMessages.INVALID_PHONE_NUMBER_FORMAT);
    }
  }
}
