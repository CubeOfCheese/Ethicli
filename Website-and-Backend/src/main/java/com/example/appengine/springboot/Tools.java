package com.example.appengine.springboot;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Tools {

  // Removes all occurences of char target from String name
  public static String charRemove(String name, char target) {
    char tempArray[] = name.toCharArray();
    String output = "";
    for (int a = 0; a < name.length(); ++a) {
      if (tempArray[a] != target) output += tempArray[a];
    }
    return output;
  }

  // Compares searchTerm with business.website if it exists, if no match is found, it compares with
  // business.name
  public static boolean compare(String searchTerm, Business business) {
    searchTerm = searchTerm.toLowerCase();
    if (business.getWebsite() != null) { // Compares searchTerm with business.website
      String website = business.getWebsite().toLowerCase();
      if (website.contains("http://")) {
        website = website.substring(7, website.length());
      } else if (website.contains("https://")) {
        website = website.substring(8, website.length());
      }
      if (website.contains("www.")) {
        website = website.substring(4, website.length());
      }
      if (website.length() > searchTerm.length()) {
        if (searchTerm.equals(website.substring(0, searchTerm.length()))
            && website.charAt(searchTerm.length()) == '.') {
          return true;
        }
      }
    }
    // Compare searchTerm with business.name - Checks matches of words in business.name individually
    // and together
    String nameArray[] = prepareSearchTerm(business.getName().toLowerCase()).split(" ");
    String nameToken;
    for (int a = 0; a < nameArray.length; ++a) {
      nameToken = "";
      for (int b = a; b < nameArray.length; ++b) {
        nameToken += nameArray[b];
        if (searchTerm.equals(nameToken)) return true;
      }
    }
    return false;
  }

  public static String[] csvToStringArray(String content) {
    String[] splitByComma;
    String output = "";
    boolean quoteDetected = false;
    content = content.replaceAll("\"\"", "@@DOUBLEQUOTES@@");
    splitByComma = content.split(",");
    for (int a = 0; a < splitByComma.length; ++a) {
      if (splitByComma[a].contains("\"")) quoteDetected = !quoteDetected;
      if (quoteDetected) {
        output += splitByComma[a] + ",";
      } else {
        output += splitByComma[a] + "@@DELIMITER@@";
      }
    }
    return output.replaceAll("\"", "").replaceAll("@@DOUBLEQUOTES@@", "\"").split("@@DELIMITER@@");
  }

  // Removes unnecessary characters from searchTerm
  public static String prepareSearchTerm(String searchTerm) {
    searchTerm = searchTerm.toLowerCase();
    searchTerm = charRemove(searchTerm, ',');
    searchTerm = charRemove(searchTerm, '\'');
    searchTerm = charRemove(searchTerm, ':');
    searchTerm = charRemove(searchTerm, ';');
    searchTerm = charRemove(searchTerm, '.');
    searchTerm = charRemove(searchTerm, '-');
    searchTerm = charRemove(searchTerm, '_');
    searchTerm = charRemove(searchTerm, '&');
    return searchTerm;
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
        if (commonWord.equals(word))
          return true;
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
        if (companyName.equals(line)) return false;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }
}
