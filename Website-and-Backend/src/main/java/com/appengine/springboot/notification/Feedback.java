package com.appengine.springboot.notification;

public class Feedback {

  private String url;
  private String userEmail;
  private String messageType;

  public Feedback() {
    this.url = "";
    this.userEmail = "";
    this.messageType = "";
  }

  public Feedback(String url, String userEmail, String requestType) {
    this.url = url;
    this.userEmail = userEmail;
    this.messageType = requestType;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  @Override
  public String toString() {
    return "====== Feedback ======"
        + "\nURL: "
        + url
        + "\nUser Email: "
        + userEmail
        + "\nMessage Type: "
        + messageType;
  }
}
