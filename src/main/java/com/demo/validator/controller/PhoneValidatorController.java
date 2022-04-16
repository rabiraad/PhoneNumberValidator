package com.demo.validator.controller;

import com.demo.validator.model.PhoneNumberDetails;
import com.demo.validator.service.PhoneValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validators/")
@Slf4j
public class PhoneValidatorController {

  @Autowired private PhoneValidatorService phoneValidatorService;

  @GetMapping("/{phoneNumber}")
  public PhoneNumberDetails validatePhoneNumber(@PathVariable String phoneNumber) {
    log.info("PhoneValidatorController -- validatePhoneNumber: validating " + phoneNumber);
    return phoneValidatorService.validatePhoneNumber(phoneNumber);
  }
}
