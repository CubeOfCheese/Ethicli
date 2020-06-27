package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business {
    boolean bcorpCertified;
    boolean blackOwnedBusiness;
    boolean bluesignPartner;
    boolean nativeOwnedBusiness;
    boolean pocOwnedBusiness;
    boolean supportsBLM;
    double animalsScore;
    double averageScore;
    double bcorpScore;
    double corporateCriticScore;
    double environmentScore;
    double goodOnYouScore;
    double laborScore;
    double textileScore;
    int bcorpCertYear;
    String bcorpProfile;
    String betterBusinessBureau;
    String companyType;
    String name;
    String supportsBLMContribution;
    String supportsBLMEntity;
    String supportsBLMSource;
    String website;

    public Business() {
        this.bcorpCertified = false;
        this.blackOwnedBusiness = false;
        this.bluesignPartner = false;
        this.nativeOwnedBusiness = false;
        this.pocOwnedBusiness = false;
        this.supportsBLM = false;
        this.animalsScore = 0;
        this.averageScore = 0;
        this.bcorpScore = 0;
        this.corporateCriticScore = 0;
        this.environmentScore = 0;
        this.goodOnYouScore = 0;
        this.laborScore = 0;
        this.textileScore = 0;
        this.bcorpCertYear = 0;
        this.bcorpProfile = null;
        this.betterBusinessBureau = null;
        this.companyType = null;
        this.name = null;
        this.supportsBLMContribution = null;
        this.supportsBLMEntity = null;
        this.supportsBLMSource = null;
        this.website = null;
    }

    public Business(@JsonProperty("name") String name, @JsonProperty("bcorpCertified") boolean bcorpCertified,
                    @JsonProperty("bcorpProfile") String bcorpProfile, @JsonProperty("website") String website,
                    @JsonProperty("bcorpCertYear") int bcorpCertYear, @JsonProperty("bcorpScore") double bcorpScore,
                    @JsonProperty("bluesignPartner") boolean bluesignPartner, @JsonProperty("supportsBLM") boolean supportsBLM,
                    @JsonProperty("supportsBLMSource") String supportsBLMSource, @JsonProperty("supportsBLMEntity") String supportsBLMEntity,
                    @JsonProperty("supportsBLMContribution") String supportsBLMContribution, @JsonProperty("companyType") String companyType,
                    @JsonProperty("blackOwnedBusiness") boolean blackOwnedBusiness, @JsonProperty("betterBusinessBureau") String betterBusinessBureau,
                    @JsonProperty("corporateCriticScore") double corporateCriticScore, @JsonProperty("goodOnYouScore") double goodOnYouScore,
                    @JsonProperty("environmentScore") double environmentScore, @JsonProperty("textileScore") double textileScore,
                    @JsonProperty("animalsScore") double animalsScore, @JsonProperty("laborScore") double laborScore,
                    @JsonProperty("averageScore") double averageScore, @JsonProperty("pocOwnedBusiness") boolean pocOwnedBusiness,
                    @JsonProperty("nativeOwnedBusiness") boolean nativeOwnedBusiness) {
        this.bcorpCertified = bcorpCertified;
        this.blackOwnedBusiness = blackOwnedBusiness;
        this.bluesignPartner = bluesignPartner;
        this.nativeOwnedBusiness = nativeOwnedBusiness;
        this.pocOwnedBusiness = pocOwnedBusiness;
        this.supportsBLM = supportsBLM;
        this.animalsScore = animalsScore;
        this.averageScore = averageScore;
        this.bcorpScore = bcorpScore;
        this.corporateCriticScore = corporateCriticScore;
        this.environmentScore = environmentScore;
        this.goodOnYouScore = goodOnYouScore;
        this.laborScore = laborScore;
        this.textileScore = textileScore;
        this.bcorpCertYear = bcorpCertYear;
        this.bcorpProfile = bcorpProfile;
        this.betterBusinessBureau = betterBusinessBureau;
        this.companyType = companyType;
        this.name = name;
        this.supportsBLMContribution = supportsBLMContribution;
        this.supportsBLMEntity = supportsBLMEntity;
        this.supportsBLMSource = supportsBLMSource;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public boolean isBcorpCertified() {
        return bcorpCertified;
    }

    public boolean isPocOwnedBusiness() {
        return pocOwnedBusiness;
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

    public double getBcorpScore() {
        return bcorpScore;
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

    public String getSupportsBLMContribution() {
        return supportsBLMContribution;
    }

    public String getCompanyType() {
        return companyType;
    }

    public boolean isBlackOwnedBusiness() {
        return blackOwnedBusiness;
    }

    public double getAnimalsScore() {
        return animalsScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public double getCorporateCriticScore() {
        return corporateCriticScore;
    }

    public double getEnvironmentScore() {
        return environmentScore;
    }

    public double getGoodOnYouScore() {
        return goodOnYouScore;
    }

    public double getLaborScore() {
        return laborScore;
    }

    public double getTextileScore() {
        return textileScore;
    }

    public String getBetterBusinessBureau() {
        return betterBusinessBureau;
    }

    public boolean isNativeOwnedBusiness() {
        return nativeOwnedBusiness;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBcorpCertified(boolean certified) {
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

    public void setBcorpScore(double bcorpScore) {
        this.bcorpScore = bcorpScore;
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

    public void setBetterBusinessBureau(String betterBusinessBureau) {
        this.betterBusinessBureau = betterBusinessBureau;
    }

    public void setCorporateCriticScore(double corporateCriticScore) {
        this.corporateCriticScore = corporateCriticScore;
    }

    public void setGoodOnYouScore(double goodOnYouScore) {
        this.goodOnYouScore = goodOnYouScore;
    }

    public void setAnimalsScore(double animalsScore) {
        this.animalsScore = animalsScore;
    }

    public void setEnvironmentScore(double environmentScore) {
        this.environmentScore = environmentScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public void setLaborScore(double laborScore) {
        this.laborScore = laborScore;
    }

    public void setTextileScore(double textileScore) {
        this.textileScore = textileScore;
    }

    public void setSupportsBLMContribution(String supportsBLMContribution) {
        this.supportsBLMContribution = supportsBLMContribution;
    }

    public void setNativeOwnedBusiness(boolean nativeOwnedBusiness) {
        this.nativeOwnedBusiness = nativeOwnedBusiness;
    }

    public void setPocOwnedBusiness(boolean pocOwnedBusiness) {
        this.pocOwnedBusiness = pocOwnedBusiness;
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
        if (this.bcorpScore == 0)
            this.bcorpScore = business.getBcorpScore();
        if (this.bluesignPartner == false)
            this.bluesignPartner = business.isBluesignPartner();
        if (this.supportsBLM == false)
            this.supportsBLM = business.getSupportsBLM();
        if (this.supportsBLMSource == null) {
            this.supportsBLMSource = business.getSupportsBLMSource();
        } else if (business.getSupportsBLMSource() != null) {
            this.supportsBLMSource += " + " + business.getSupportsBLMSource();
        }
        if (this.supportsBLMEntity == null) {
            this.supportsBLMEntity = business.getSupportsBLMEntity();
        } else if (business.getSupportsBLMEntity() != null) {
            this.supportsBLMEntity += " + " + business.getSupportsBLMEntity();
        }
        if (this.supportsBLMContribution == null) {
            this.supportsBLMContribution = business.getSupportsBLMContribution();
        } else if (business.getSupportsBLMContribution() != null) {
            this.supportsBLMContribution += " + " + business.getSupportsBLMContribution();
        }
        if (this.companyType == null)
            this.companyType = business.getCompanyType();
        if (this.blackOwnedBusiness == false)
            this.blackOwnedBusiness = business.isBlackOwnedBusiness();
        if (this.betterBusinessBureau == null)
            this.betterBusinessBureau = business.getBetterBusinessBureau();
        if (this.corporateCriticScore == 0)
            this.corporateCriticScore = business.getCorporateCriticScore();
        if (this.goodOnYouScore == 0)
            this.goodOnYouScore = business.getGoodOnYouScore();
        if (this.environmentScore == 0)
            this.environmentScore = business.getEnvironmentScore();
        if (this.textileScore == 0)
            this.textileScore = business.getTextileScore();
        if (this.animalsScore == 0)
            this.animalsScore = business.getAnimalsScore();
        if (this.laborScore == 0)
            this.laborScore = business.getLaborScore();
        if (this.averageScore == 0)
            this.averageScore = business.getAverageScore();
        if (this.pocOwnedBusiness == false)
            this.pocOwnedBusiness = business.isPocOwnedBusiness();
        if (this.nativeOwnedBusiness == false)
            this.nativeOwnedBusiness = business.isNativeOwnedBusiness();
    }

    public void display() {
        System.out.println("Name:                   " + this.name);
        System.out.println("Website:                " + this.website);
        System.out.println("Business Type:          " + this.companyType);
        System.out.println("Bcorp Score:            " + this.bcorpScore);
        System.out.println("Black Owned Business:   " + this.blackOwnedBusiness);
        System.out.println("Native Owned Business:  " + this.nativeOwnedBusiness);
        System.out.println("BCorp Certified:        " + this.bcorpCertified);
        System.out.println("BCorp Profile:          " + this.bcorpProfile);
        System.out.println("BCorp Cert Year:        " + this.bcorpCertYear);
        System.out.println("Bluesign Partner:       " + this.bluesignPartner);
        System.out.println("POC Owned Business:     " + this.pocOwnedBusiness);
        System.out.println("Supports BLM:           " + this.supportsBLM);
        System.out.println("Supports BLMSource:     " + this.supportsBLMSource);
        System.out.println("Supports BLMEntity:     " + this.supportsBLMEntity);
        System.out.println("BLM Contribution:       " + this.supportsBLMContribution);
        System.out.println("BBB Score:              " + this.betterBusinessBureau);
        System.out.println("CCR Score:              " + this.corporateCriticScore);
        System.out.println("GoY Score:              " + this.goodOnYouScore);
        System.out.println("Environment Score:      " + this.environmentScore);
        System.out.println("Textile Score:          " + this.textileScore);
        System.out.println("Animal Score:           " + this.animalsScore);
        System.out.println("Labor Score:            " + this.laborScore);
        System.out.println("Average Score:          " + this.averageScore);
    }
}
