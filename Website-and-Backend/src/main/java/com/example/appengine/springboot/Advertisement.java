package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "Advertisement")
public class Advertisement {

    @Id
    private String productName;
    private String business;
    private long price;
    private String [] productTags;
    private String productURL;
    private String productImageURL;
    private long insights;
    private long clicks;

    public Advertisement() {
        this.productName = null;
        this.business = null;
        this.price = 0;
        this.productTags = new String[25];
        this.productURL = null;
        this.productImageURL = null;
        this.insights = 0;
        this.clicks = 0;
    }

    public Advertisement(
            @JsonProperty("productName") String productName,
            @JsonProperty("business") String business,
            @JsonProperty("price") long price,
            @JsonProperty("productTags") String [] productTags,
            @JsonProperty("productURL") String productURL,
            @JsonProperty("productImageURL") String  productImageURL,
            @JsonProperty("insights") long insights,
            @JsonProperty("clicks") long clicks) {
        this.business = business;
        this.price = price;
        this.productImageURL = productImageURL;
        this.productName = productName;
        this.productTags = productTags;
        this.productURL = productURL;
        this.insights = insights;
        this.clicks = clicks;
    }

    public String  getBusiness() {
        return business;
    }

    public long getPrice() {
        return price;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public String getProductName() {
        return productName;
    }

    public String[] getProductTags() {
        return productTags;
    }

    public String getProductURL() {
        return productURL;
    }

    public long getInsights() {
        return insights;
    }

    public long getClicks() {
        return clicks;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductTags(String[] productTags) {
        this.productTags = productTags;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public void setInsights(long insights) {
        this.insights = insights;
    }

    public void setClicks(long clicks) {
        this.clicks = clicks;
    }
}

