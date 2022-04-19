package com.demo.validator.service;

import com.demo.validator.constants.ErrorMessages;
import com.demo.validator.exception.InvalidPhoneNumberException;
import com.demo.validator.model.PhoneNumberDetails;
import com.demo.validator.utility.PhoneNumberToPhoneNumberDetails;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhoneValidatorService {

  public PhoneNumberDetails getPhoneNumberDetailsAfterValidation(String phoneNumber)
      throws IOException {
    log.debug("PhoneValidatorService -- getPhoneNumberDetailsAfterValidation for: " + phoneNumber);
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    try {
      PhoneNumber phone =
          phoneNumberUtil.parse(
              phoneNumber, PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN.name());
      if (phoneNumberUtil.isValidNumber(phone)) {
        return PhoneNumberToPhoneNumberDetails.getPhoneNumberDetails(phone);
      } else {
        throw new InvalidPhoneNumberException(ErrorMessages.INVALID_PHONE_NUMBER);
      }
    } catch (NumberParseException e) {
      throw new InvalidPhoneNumberException(ErrorMessages.INVALID_PHONE_NUMBER_FORMAT);
    }
  }
}
