package com.example.appengine.springboot;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Advertisement {

  @Id
  private String id;
  private String productName;
  private String website;
  private String companyName;
  private ProductTag[] productTags;
  private double price;
  private String productURL;
  private String productImageURL;
  private double companyScore;

  public Advertisement() {
  }

  public Advertisement(
      String id,
      String productName,
      String website,
      String companyName,
      ProductTag[] productTags,
      double price,
      String productURL,
      String productImageURL,
      double companyScore) {
    this.id = id;
    this.productName = productName;
    this.website = website;
    this.companyName = companyName;
    this.productTags = productTags;
    this.price = price;
    this.productURL = productURL;
    this.productImageURL = productImageURL;
    this.companyScore = companyScore;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public ProductTag[] getProductTags() {
    return productTags;
  }

  public void setProductTags(ProductTag[] productTags) {
    this.productTags = productTags;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getProductURL() {
    return productURL;
  }

  public void setProductURL(String productURL) {
    this.productURL = productURL;
  }

  public String getProductImageURL() {
    return productImageURL;
  }

  public void setProductImageURL(String productImageURL) {
    this.productImageURL = productImageURL;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public double getCompanyScore() {
    return companyScore;
  }

  public void setCompanyScore(double companyScore) {
    this.companyScore = companyScore;
  }
}
