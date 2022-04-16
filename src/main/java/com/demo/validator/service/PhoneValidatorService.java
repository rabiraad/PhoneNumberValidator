package com.demo.validator.service;

import com.demo.validator.model.PhoneNumberDetails;
import org.springframework.stereotype.Service;

@Service
public class PhoneValidatorService {

  public PhoneNumberDetails validatePhoneNumber(String phoneNumber) {
    System.out.println("PhoneValidatorService -- validatePhoneNumber: validating " + phoneNumber);
    return new PhoneNumberDetails("+961", "Lebanon", "alfa");
  }
}
