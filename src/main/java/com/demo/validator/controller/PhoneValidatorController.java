package com.demo.validator.controller;

import com.demo.validator.model.PhoneNumberDetails;
import com.demo.validator.service.PhoneValidatorService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/validators/")
@Slf4j
public class PhoneValidatorController {

  @Autowired private PhoneValidatorService phoneValidatorService;

  @GetMapping("/{phoneNumber}")
  @ApiOperation(
      value =
          "validates the input phone number and returns the country code, country name, and operator name")
  public PhoneNumberDetails validatePhoneNumber(@PathVariable String phoneNumber) {
    log.info("PhoneValidatorController -- validatePhoneNumber: validating " + phoneNumber);
    return phoneValidatorService.validatePhoneNumber(phoneNumber);
  }
}
