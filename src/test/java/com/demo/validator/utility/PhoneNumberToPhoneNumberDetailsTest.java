package com.demo.validator.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.demo.validator.model.PhoneNumberDetails;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class PhoneNumberToPhoneNumberDetailsTest {
  @Test
  public void testGetPhoneNumberDetails() throws IOException {
    PhoneNumber phoneNumber = new PhoneNumber();
    phoneNumber.setCountryCode(961);
    phoneNumber.setNationalNumber(70173804);
    PhoneNumberDetails detailsActual =
        PhoneNumberToPhoneNumberDetails.getPhoneNumberDetails(phoneNumber);
    assertEquals("+961", detailsActual.getCountryCode());
    assertEquals("Lebanon", detailsActual.getCountryName());
    assertEquals("NOT_SUPPORTED", detailsActual.getOperatorName());
  }

  @Test
  public void testGetPhoneNumberDetails_countryCodeHasMultipleRegions() throws IOException {
    PhoneNumber phoneNumber = new PhoneNumber();
    phoneNumber.setCountryCode(1);
    phoneNumber.setNationalNumber(2505550199L);
    PhoneNumberDetails detailsActual =
        PhoneNumberToPhoneNumberDetails.getPhoneNumberDetails(phoneNumber);
    assertEquals("+1", detailsActual.getCountryCode());
    assertEquals("Canada", detailsActual.getCountryName());
    assertEquals("NOT_SUPPORTED", detailsActual.getOperatorName());
    phoneNumber.setNationalNumber(2124567890L);
    detailsActual = PhoneNumberToPhoneNumberDetails.getPhoneNumberDetails(phoneNumber);
    assertEquals("+1", detailsActual.getCountryCode());
    assertEquals("United States", detailsActual.getCountryName());
    assertEquals("NOT_SUPPORTED", detailsActual.getOperatorName());
  }
}
