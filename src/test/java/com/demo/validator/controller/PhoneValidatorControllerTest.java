package com.demo.validator.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.demo.validator.constants.ErrorMessages;
import com.demo.validator.exception.InvalidPhoneNumberException;
import com.demo.validator.model.PhoneNumberDetails;
import com.demo.validator.service.PhoneValidatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneValidatorControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private PhoneValidatorService service;

  @Test
  public void testGetPhoneNumberDetailsAfterValidation() throws Exception {

    PhoneNumberDetails detailsExpected = new PhoneNumberDetails("+961", "Lebanon", "NOT_SUPPORTED");
    String PHONE_NUMBER = "+96170173804";
    when(service.getPhoneNumberDetailsAfterValidation(eq(PHONE_NUMBER)))
        .thenReturn(detailsExpected);
    this.mockMvc
        .perform(get("/api/v1/validators/" + PHONE_NUMBER))
        .andExpect(status().isOk())
        .andExpect(jsonPath("countryCode", equalTo(detailsExpected.getCountryCode())))
        .andExpect(jsonPath("countryName", equalTo(detailsExpected.getCountryName())))
        .andExpect(jsonPath("operatorName", equalTo(detailsExpected.getOperatorName())));
  }

  @Test
  public void testGetPhoneNumberDetailsAfterValidation_invalidPhoneNumber() throws Exception {

    String PHONE_NUMBER = "+961701804";
    when(service.getPhoneNumberDetailsAfterValidation(eq(PHONE_NUMBER)))
        .thenThrow(new InvalidPhoneNumberException(ErrorMessages.INVALID_PHONE_NUMBER));
    this.mockMvc
        .perform(get("/api/v1/validators/" + PHONE_NUMBER))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(ErrorMessages.INVALID_PHONE_NUMBER));
  }

  @Test
  public void testGetPhoneNumberDetailsAfterValidation_invalidPhoneNumberFormat() throws Exception {

    String PHONE_NUMBER = "0096170173804";
    when(service.getPhoneNumberDetailsAfterValidation(eq(PHONE_NUMBER)))
        .thenThrow(new InvalidPhoneNumberException(ErrorMessages.INVALID_PHONE_NUMBER_FORMAT));
    this.mockMvc
        .perform(get("/api/v1/validators/" + PHONE_NUMBER))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().string(ErrorMessages.INVALID_PHONE_NUMBER_FORMAT));
  }
}
