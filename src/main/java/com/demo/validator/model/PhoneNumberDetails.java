package com.demo.validator.model;

public class PhoneNumberDetails {

  String countryCode;
  String countryName;
  String operatorName;

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public PhoneNumberDetails(String countryCode, String countryName, String operatorName) {
    super();
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.operatorName = operatorName;
  }
}
