package com.example.appengine.springboot;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Advertisement {
  @Id private String id;
  private String name;
  private String website;
  private ProductTag[] productTags;
  private double price;
  private String productURL;
  private String productImageURL;
  private Business business;

  public Advertisement() {

  }
  public Advertisement(String name, String website, ProductTag[] productTags, double price, String productURL, String productImageURL, Business business) {
    this.name = name;
    this.website = website;
    this.productTags = productTags;
    this.price = price;
    this.productURL = productURL;
    this.productImageURL = productImageURL;
    this.business = business;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Business getBusiness() {
    return business;
  }

  public void setBusiness(Business business) {
    this.business = business;
  }
}
