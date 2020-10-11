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

  public Advertisement() {
    super();
  }

  public Advertisement(String name, String website, ProductTag[] productTags, double price) {
    super();
    this.name = name;
    this.website = website;
    this.productTags = productTags;
    this.price = price;
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
}
