package com.example.appengine.springboot;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BadUrl {
  private String url;

  public BadUrl() {
    this.url = "";
  }

  public BadUrl(@JsonProperty("url") String aUrl) {
    this.url = aUrl;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String aUrl) {
    url = aUrl;
  }
}