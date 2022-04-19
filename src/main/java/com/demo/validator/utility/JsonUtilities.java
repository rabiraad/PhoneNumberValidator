package com.demo.validator.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class JsonUtilities {

  private JsonUtilities() {}

  public static <T> HashMap<String, T> getJsonAsHashmap(String jsonFilePath) throws IOException {
    String json = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, new TypeReference<HashMap<String, T>>() {});
  }
}
