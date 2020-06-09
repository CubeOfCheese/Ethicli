package com.example.appengine.springboot;

public class BadUrl {
  private String url;

  public BadUrl(String aUrl) {
    url = aUrl;
  }

  public String getUrl() {
    return url;
  }
  public void setUrl(String aUrl) {
    url = aUrl;
  }
}
