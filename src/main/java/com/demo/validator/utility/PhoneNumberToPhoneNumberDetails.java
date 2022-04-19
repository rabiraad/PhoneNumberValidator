package com.demo.validator.utility;

import com.demo.validator.model.PhoneNumberDetails;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class PhoneNumberToPhoneNumberDetails {

  private PhoneNumberToPhoneNumberDetails() {}

  // TODO: support get operator name, consider using truecaller
  private static String getOperatorName(PhoneNumber phoneNumber) {
    return "NOT_SUPPORTED";
  }

  // TODO: to optimize process
  private static String getCountryName(PhoneNumber phoneNumber) throws IOException {
    String file = "src/main/resources/map-dialcode-to-countryname.json";
    Map<String, String> map = JsonUtilities.getJsonAsHashmap(file);
    String dialCode = getDialCodeAsString(phoneNumber);
    String countryName = map.get(dialCode);
    if (!countryName.equals("UNKNOWN")) {
      return countryName;
    } else {
      return getCountryNameForDialCodeWithMultipleRegions(phoneNumber);
    }
  }

  private static String getDialCodeAsString(PhoneNumber phoneNumber) {
    return "+" + phoneNumber.getCountryCode();
  }

  public static PhoneNumberDetails getPhoneNumberDetails(PhoneNumber phoneNumber)
      throws IOException {
    return new PhoneNumberDetails(
        getDialCodeAsString(phoneNumber),
        getCountryName(phoneNumber),
        getOperatorName(phoneNumber));
  }

  /**
   * Some country codes correspond to multiple regions/countries. For example: (+1) corresponds to
   * Canada and USA. All these cases can be found in country-codes-with-multiple-regions.json This
   * method returns the specific region corresponding to a given phone number
   *
   * @param phoneNumber with a country code that matches multiple regions
   * @return specific country/region name corresponding to the input phone number
   * @throws IOException if for some reason the needed json files were not found
   */
  private static String getCountryNameForDialCodeWithMultipleRegions(PhoneNumber phoneNumber)
      throws IOException {
    Map<String, ArrayList<String>> mapDialCodeToRegions =
        JsonUtilities.getJsonAsHashmap(
            "src/main/resources/country-codes-with-multiple-regions.json");
    Map<String, String> mapIsoCodeToRegionName =
        JsonUtilities.getJsonAsHashmap("src/main/resources/map-isocode-to-countryname.json");
    ArrayList<String> possibleRegionIsoCodes =
        mapDialCodeToRegions.get(getDialCodeAsString(phoneNumber));
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    for (String regionCode : possibleRegionIsoCodes) {
      if (phoneNumberUtil.isValidNumberForRegion(phoneNumber, regionCode)) {
        return mapIsoCodeToRegionName.get(regionCode);
      }
    }
    throw new RuntimeException("Was not able to map dial code to country code");
  }
}
