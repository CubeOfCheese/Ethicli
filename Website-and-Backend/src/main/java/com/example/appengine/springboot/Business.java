package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business {
    String name;
    String bcorpProfile;
    String website;
    String supportsBLMSource;
    String supportsBLMEntity;
    String companyType;
    int bcorpCertYear;
    double overallScore;
    boolean bluesignPartner;
    boolean bcorpCertified;
    boolean supportsBLM;
    boolean blackOwnedBusiness;

    public Business() {
        this.name = null;
        this.bcorpCertified = false;
        this.bcorpProfile = null;
        this.website = null;
        this.bcorpCertYear = 0;
        this.overallScore = 0;
        this.bluesignPartner = false;
        this.supportsBLM = false;
        this.supportsBLMSource = null;
        this.supportsBLMEntity = null;
        this.companyType = null;
        this.blackOwnedBusiness = false;
    }

    public Business(@JsonProperty("name") String name, @JsonProperty("bcorpCertified") boolean bcorpCertified,
                    @JsonProperty("bcorpProfile") String bcorpProfile, @JsonProperty("website") String website,
                    @JsonProperty("bcorpCertYear") int bcorpCertYear, @JsonProperty("overallScore") double overallScore,
                    @JsonProperty("bluesign_partner") boolean bluesignPartner, @JsonProperty("supportsBLM") boolean supportsBLM,
                    @JsonProperty("supportsBLMSource") String supportsBLMSource, @JsonProperty("supportsBLMEntity") String supportsBLMEntity,
                    @JsonProperty("companyType") String companyType, @JsonProperty("blackOwnedBusiness") boolean blackOwnedBusiness) {
        this.name = name;
        this.bcorpCertified = bcorpCertified;
        this.bcorpProfile = bcorpProfile;
        this.website = website;
        this.bcorpCertYear = bcorpCertYear;
        this.overallScore = overallScore;
        this.bluesignPartner = bluesignPartner;
        this.supportsBLM = supportsBLM;
        this.supportsBLMSource = supportsBLMSource;
        this.supportsBLMEntity = supportsBLMEntity;
        this.companyType = companyType;
        this.blackOwnedBusiness = blackOwnedBusiness;
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

    public int getBcorpCertYear() {
        return bcorpCertYear;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public boolean isBluesignPartner() {
        return bluesignPartner;
    }

    public String getSupportsBLMEntity() {
        return supportsBLMEntity;
    }

    public String getSupportsBLMSource() {
        return supportsBLMSource;
    }

    public boolean getSupportsBLM() {
        return supportsBLM;
    }

    public String getCompanyType() {
        return companyType;
    }

    public boolean isBlackOwnedBusiness() {
        return blackOwnedBusiness;
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

    public void setBcorpCertYear(int bcorpCertYear) {
        this.bcorpCertYear = bcorpCertYear;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public void setBluesignPartner(boolean bluesignPartner) {
        this.bluesignPartner = bluesignPartner;
    }

    public void setSupportsBLMSource(String supportsBLMSource) {
        this.supportsBLMSource = supportsBLMSource;
    }

    public void setSupportsBLM(boolean supportsBLM) {
        this.supportsBLM = supportsBLM;
    }

    public void setSupportsBLMEntity(String supportsBLMEntity) {
        this.supportsBLMEntity = supportsBLMEntity;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public void setBlackOwnedBusiness(boolean blackOwnedBusiness) {
        this.blackOwnedBusiness = blackOwnedBusiness;
    }

    public void update(Business business){
        if (this.name == null)
            this.name = business.getName();
        if (this.bcorpCertified == false)
            this.bcorpCertified = business.isBcorpCertified();
        if (this.bcorpProfile == null)
            this.bcorpProfile = business.getBcorpProfile();
        if (this.website == null)
            this.website = business.getWebsite();
        if (this.bcorpCertYear == 0)
            this.bcorpCertYear = business.getBcorpCertYear();
        if (this.overallScore == 0)
            this.overallScore = business.getOverallScore();
        if (this.bluesignPartner == false)
            this.bluesignPartner = business.isBluesignPartner();
        if (this.supportsBLM == false)
            this.supportsBLM = business.getSupportsBLM();
        if (this.supportsBLMSource == null)
            this.supportsBLMSource = business.getSupportsBLMSource();
        if (this.supportsBLMEntity == null)
            this.supportsBLMEntity = business.getSupportsBLMEntity();
        if (this.companyType == null)
            this.companyType = business.getCompanyType();
        if (this.blackOwnedBusiness == false)
            this.blackOwnedBusiness = business.isBlackOwnedBusiness();
    }

    public void display() {
        System.out.println("Name:                   " + this.name);
        System.out.println("Website:                " + this.website);
        System.out.println("Business Type:          " + this.companyType);
        System.out.println("Score:                  " + this.overallScore);
        System.out.println("Black Owned Business:   " + this.blackOwnedBusiness);
        System.out.println("BCorp Certified:        " + this.bcorpCertified);
        System.out.println("BCorp Profile:          " + this.bcorpProfile);
        System.out.println("BCorp Cert Year:        " + this.bcorpCertYear);
        System.out.println("Bluesign Partner:       " + this.bluesignPartner);
        System.out.println("Supports BLM:           " + this.supportsBLM);
        System.out.println("Supports BLMSource:     " + this.supportsBLMSource);
        System.out.println("Supports BLMEntity:     " + this.supportsBLMEntity);
    }
}
