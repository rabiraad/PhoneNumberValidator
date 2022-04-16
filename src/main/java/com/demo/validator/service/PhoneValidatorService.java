package com.demo.validator.service;

import com.demo.validator.model.PhoneNumberDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhoneValidatorService {

  public PhoneNumberDetails validatePhoneNumber(String phoneNumber) {
    log.debug("PhoneValidatorService -- validatePhoneNumber: validating " + phoneNumber);
    return new PhoneNumberDetails("+961", "Lebanon", "alfa");
  }
}
