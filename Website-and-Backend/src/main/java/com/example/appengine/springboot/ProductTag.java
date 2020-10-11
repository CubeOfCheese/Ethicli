package com.example.appengine.springboot;

public class ProductTag {
  private String tag;
  private double weight;

  public ProductTag() {
    super();
  }

  public ProductTag(String tag, double weight) {
    super();
    this.tag = tag;
    this.weight = weight;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }
}
