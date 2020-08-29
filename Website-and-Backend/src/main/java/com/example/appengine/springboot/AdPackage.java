package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdPackage {
    private Advertisement advertisement;
    private Business business;

    public AdPackage() {
        this.advertisement = new Advertisement();
        this.business = new Business();
    }

    public AdPackage(@JsonProperty("advertisement") Advertisement advertisement,
                     @JsonProperty("business") Business business) {
        this.advertisement = advertisement;
        this.business = business;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public Business getBusiness() {
        return business;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
