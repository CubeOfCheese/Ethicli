package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business {
    String name;
    String bcorpProfile;
    String website;
    String supportsBLMSource;
    String supportsBLMEntity;
    String supportsBLMContribution;
    String companyType;
    String betterBusinessBureau;
    double corporateCriticScore;
    double goodOnYouScore;
    double environmentScore;
    double textilesScore;
    double animalsScore;
    double laborScore;
    double averageScore;
    double bcorpScore;
    int bcorpCertYear;
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
        this.bcorpScore = 0;
        this.bluesignPartner = false;
        this.supportsBLM = false;
        this.supportsBLMSource = null;
        this.supportsBLMEntity = null;
        this.supportsBLMContribution = null;
        this.companyType = null;
        this.blackOwnedBusiness = false;
        this.betterBusinessBureau = null;
        this.corporateCriticScore = 0;
        this.goodOnYouScore = 0;
        this.environmentScore = 0;
        this.textilesScore = 0;
        this.animalsScore = 0;
        this.laborScore = 0;
        this.averageScore = 0;
    }

    public Business(@JsonProperty("name") String name, @JsonProperty("bcorpCertified") boolean bcorpCertified,
                    @JsonProperty("bcorpProfile") String bcorpProfile, @JsonProperty("website") String website,
                    @JsonProperty("bcorpCertYear") int bcorpCertYear, @JsonProperty("bcorpScore") double bcorpScore,
                    @JsonProperty("bluesign_partner") boolean bluesignPartner, @JsonProperty("supportsBLM") boolean supportsBLM,
                    @JsonProperty("supportsBLMSource") String supportsBLMSource, @JsonProperty("supportsBLMEntity") String supportsBLMEntity,
                    @JsonProperty("supportsBLMContribution") String supportsBLMContribution, @JsonProperty("companyType") String companyType,
                    @JsonProperty("blackOwnedBusiness") boolean blackOwnedBusiness, @JsonProperty("betterBusinessBureau") String betterBusinessBureau,
                    @JsonProperty("corporateCriticScore") double corporateCriticScore, @JsonProperty("goodOnYouScore") double goodOnYouScore,
                    @JsonProperty("environmentScore") double environmentScore, @JsonProperty("textilesScore") double textilesScore,
                    @JsonProperty("animalsScore") double animalsScore, @JsonProperty("laborScore") double laborScore,
                    @JsonProperty("averageScore") double averageScore) {
        this.name = name;
        this.bcorpCertified = bcorpCertified;
        this.bcorpProfile = bcorpProfile;
        this.website = website;
        this.bcorpCertYear = bcorpCertYear;
        this.bcorpScore = bcorpScore;
        this.bluesignPartner = bluesignPartner;
        this.supportsBLM = supportsBLM;
        this.supportsBLMSource = supportsBLMSource;
        this.supportsBLMEntity = supportsBLMEntity;
        this.supportsBLMContribution = supportsBLMContribution;
        this.companyType = companyType;
        this.blackOwnedBusiness = blackOwnedBusiness;
        this.betterBusinessBureau = betterBusinessBureau;
        this.corporateCriticScore = corporateCriticScore;
        this.goodOnYouScore = goodOnYouScore;
        this.environmentScore = environmentScore;
        this.textilesScore = textilesScore;
        this.animalsScore = animalsScore;
        this.laborScore = laborScore;
        this.averageScore = averageScore;
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

    public double getTextilesScore() {
        return textilesScore;
    }

    public String getBetterBusinessBureau() {
        return betterBusinessBureau;
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

    public void setTextilesScore(double textilesScore) {
        this.textilesScore = textilesScore;
    }

    public void setSupportsBLMContribution(String supportsBLMContribution) {
        this.supportsBLMContribution = supportsBLMContribution;
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
        if (this.textilesScore == 0)
            this.textilesScore = business.getTextilesScore();
        if (this.animalsScore == 0)
            this.animalsScore = business.getAnimalsScore();
        if (this.laborScore == 0)
            this.laborScore = business.getLaborScore();
        if (this.averageScore == 0)
            this.averageScore = business.getAverageScore();
    }

    public void display() {
        System.out.println("Name:                   " + this.name);
        System.out.println("Website:                " + this.website);
        System.out.println("Business Type:          " + this.companyType);
        System.out.println("Bcorp Score:            " + this.bcorpScore);
        System.out.println("Black Owned Business:   " + this.blackOwnedBusiness);
        System.out.println("BCorp Certified:        " + this.bcorpCertified);
        System.out.println("BCorp Profile:          " + this.bcorpProfile);
        System.out.println("BCorp Cert Year:        " + this.bcorpCertYear);
        System.out.println("Bluesign Partner:       " + this.bluesignPartner);
        System.out.println("Supports BLM:           " + this.supportsBLM);
        System.out.println("Supports BLMSource:     " + this.supportsBLMSource);
        System.out.println("Supports BLMEntity:     " + this.supportsBLMEntity);
        System.out.println("BLM Contribution:       " + this.supportsBLMContribution);
        System.out.println("BBB Score:              " + this.betterBusinessBureau);
        System.out.println("CCR Score:              " + this.corporateCriticScore);
        System.out.println("GoY Score:              " + this.goodOnYouScore);
        System.out.println("Environment Score:      " + this.environmentScore);
        System.out.println("Textile Score:          " + this.textilesScore);
        System.out.println("Animal Score:           " + this.animalsScore);
        System.out.println("Labor Score:            " + this.laborScore);
        System.out.println("Average Score:          " + this.averageScore);
    }
}
