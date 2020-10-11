package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "BadUrls")
public class BadUrl {

  @Id
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

  @Override
  public String toString() {
    return "BadUrl{" + "url=" + this.url + '}';
  }
}
