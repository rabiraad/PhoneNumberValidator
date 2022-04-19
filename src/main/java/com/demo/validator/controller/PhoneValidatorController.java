package com.demo.validator.controller;

import com.demo.validator.constants.ErrorMessages;
import com.demo.validator.exception.InvalidPhoneNumberException;
import com.demo.validator.model.PhoneNumberDetails;
import com.demo.validator.service.PhoneValidatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import javax.validation.constraints.Pattern;
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

  private final String PHONE_NUMBER_REGEX = "^[+]\\d+$";
  @Autowired private PhoneValidatorService phoneValidatorService;

  @GetMapping("/{phoneNumber}")
  @ApiOperation(
      value =
          "validates the input phone number and returns the country code, country name, and operator name")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 400,
            message = ErrorMessages.INVALID_PHONE_NUMBER_FORMAT,
            response = InvalidPhoneNumberException.class)
      })
  public PhoneNumberDetails getPhoneNumberDetailsAfterValidation(
      @PathVariable
          @ApiParam(value = ErrorMessages.INVALID_PHONE_NUMBER_FORMAT)
          @Pattern(regexp = PHONE_NUMBER_REGEX, message = ErrorMessages.INVALID_PHONE_NUMBER_FORMAT)
          String phoneNumber)
      throws IOException {
    log.info("PhoneValidatorController -- validatePhoneNumber: validating " + phoneNumber);
    return phoneValidatorService.getPhoneNumberDetailsAfterValidation(phoneNumber);
  }
}
