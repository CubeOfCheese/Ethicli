package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business {
    String name;
    boolean bcorpCertified;
    String bcorpProfile;
    String website;
    int year;
    double overallScore;
    boolean bluesignPartner;

    public Business() {
        this.name = null;
        this.bcorpCertified = false;
        this.bcorpProfile = null;
        this.website = null;
        this.year = 0;
        this.overallScore = 0;
        this.bluesignPartner = false;
    }

    public Business(@JsonProperty("name") String name, @JsonProperty("certified") boolean certified,
                    @JsonProperty("bcorpProfile") String bcorpProfile, @JsonProperty("website") String website,
                    @JsonProperty("year") int year, @JsonProperty("overallScore") double overallScore,
                    @JsonProperty("bluesign_partner") boolean bluesign_partner) {
        this.name = name;
        this.bcorpCertified = certified;
        this.bcorpProfile = bcorpProfile;
        this.website = website;
        this.year = year;
        this.overallScore = overallScore;
        this.bluesignPartner = bluesignPartner;
    }

    public String getName() {
        return name;
    }

    public boolean isBcorpCertified() {
        return bcorpCertified;
    }

    public String getBcorpProfile() {
        return bcorpProfile;
    }

    public String getWebsite() {
        return website;
    }

    public int getYear() {
        return year;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public boolean isBluesignPartner() {
        return bluesignPartner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCertified(boolean certified) {
        this.bcorpCertified = certified;
    }

    public void setBcorpProfile(String bcorpProfile) {
        this.bcorpProfile = bcorpProfile;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public void setBluesignPartner(boolean bluesignPartner) {
        this.bluesignPartner = bluesignPartner;
    }

    public void display() {
        System.out.println("Name:      " + this.name);
        System.out.println("Certified: " + this.bcorpCertified);
        System.out.println("BCProfile: " + this.bcorpProfile);
        System.out.println("Website:   " + this.website);
        System.out.println("Year:      " + this.year);
        System.out.println("Score:     " + this.overallScore);
        System.out.println("Bluesign:  " + this.bluesignPartner);
    }
}
