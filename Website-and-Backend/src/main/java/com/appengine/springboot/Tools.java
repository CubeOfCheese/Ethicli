package com.appengine.springboot;

import com.appengine.springboot.advertisement.ProductTag;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Tools {

  // Removes all occurences of char target from String name
  public static String charRemove(String name, char target) {
    char[] tempArray = name.toCharArray();
    String output = "";
    for (int a = 0; a < name.length(); ++a) {
      if (tempArray[a] != target) {
        output += tempArray[a];
      }
    }
    return output;
  }

  // Counts number of " in a String
  public static int doubleQuoteCounter(String content) {
    int counter = 0;
    for (int a = 0; a < content.length(); ++a) {
      if (content.charAt(a) == '\"') {
        ++counter;
      }
    }
    return counter;
  }

  public static String[] prepareForProductTagQuery(String input) throws IOException {
    String[] inputToArray = input.toLowerCase().split(" ");
    String inputCleaned = "";
    for (int a = 0; a < inputToArray.length; ++a) {
      if (inputToArray[a].length() > 2 && !isCommonWord(inputToArray[a])) {
        inputCleaned += removePunctuation(inputToArray[a]) + " ";
      }
    }
    return inputCleaned.split(" ");
  }

  public static String removePunctuation(String string) {
    return string.replaceAll("[^a-zA-Z0-9]", "");
  }

  public static boolean isCommonWord(String word) throws IOException {
    Resource resource = new ClassPathResource("Common Words");
    InputStream file = resource.getInputStream();
    BufferedReader br = null;
    String commonWord = "";
    String[] brLine;
    try {
      br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
      while ((commonWord = br.readLine()) != null) {
        if (commonWord.equals(word)) {
          return true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  // Compares companyName to Blocked URLS - Sheet.csv
  public static boolean validateURL(String companyName) throws IOException {
    Resource resource = new ClassPathResource("Blocked URLS - Sheet1.csv");
    InputStream file = resource.getInputStream();
    BufferedReader br = null;
    String line = "";
    try {
      br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
      while ((line = br.readLine()) != null) { // cycles through line by line
        if (companyName.equals(line)) {
          return false;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  public static double compareToProductTag(ProductTag productTag, String descriptor) {
    double sizeDifference = productTag.getTag().length() - descriptor.length();
    if (sizeDifference <= 1
        || (sizeDifference == 2 && productTag.getTag().substring(productTag.getTag().length() - 2, productTag.getTag().length()).equals("es"))) {
      return productTag.getWeight();
    } else {
      return productTag.getWeight() / sizeDifference;
    }
  }
}
