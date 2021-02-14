package com.appengine.springboot.notification;

public class Feedback {

  private String url;
  private String userName;
  private String userEmail;
  private String messageType;
  private String message;

  public Feedback() {
    this.url = "";
    this.userName = "";
    this.userEmail = "";
    this.messageType = "";
    this.message = "";
  }

  public Feedback(String url, String userName, String userEmail, String requestType, String message) {
    this.url = url;
    this.userName = userName;
    this.userEmail = userEmail;
    this.messageType = requestType;
    this.message = message;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "====== Feedback ======"
        + "\nURL: "
        + url
        + "\nUser Name: "
        + userName
        + "\nUser Email: "
        + userEmail
        + "\nMessage Type: "
        + messageType
        + "\nMessage: "
        + message;
  }
}
