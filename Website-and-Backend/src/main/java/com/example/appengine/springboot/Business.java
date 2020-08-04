package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business {
    private boolean bcorpCertified; 
    private boolean blackOwnedBusiness;
    private boolean bluesignPartner;
    private boolean ethicalElephantCrueltyFree;
    private boolean nativeOwnedBusiness;
    private boolean pocOwnedBusiness;
    private boolean supportsBLM;
    private boolean veganDotOrgCertified;
    private double animalsScore;
    private double bcorpCommunityScore;
    private double bcorpEnvironmentScore;
    private double bcorpGovernanceScore;
    private double bcorpWorkerScore;
    private double bcorpScore;
    private double corporateCriticScore;
    private double environmentScore;
    private double goodOnYouScore;
    private double greenPowerPercentage;
    private double laborScore;
    private double overallScore;
    private double socialScore;
    private double textileScore;
    private int bcorpCertYear;
    private String bcorpProfile;
    private String betterBusinessBureau;
    private String companyType;
    private String ethicalElephantType;
    private String name;
    private String supportsBLMContribution;
    private String supportsBLMEntity;
    private String supportsBLMSource;
    private String website;

    public Business() {
        this.bcorpCertified = false;
        this.blackOwnedBusiness = false;
        this.bluesignPartner = false;
        this.ethicalElephantCrueltyFree = false;
        this.nativeOwnedBusiness = false;
        this.pocOwnedBusiness = false;
        this.supportsBLM = false;
        this.veganDotOrgCertified = false;
        this.animalsScore = 0;
        this.bcorpCommunityScore = 0;
        this.bcorpEnvironmentScore = 0;
        this.bcorpGovernanceScore = 0;
        this.bcorpWorkerScore = 0;
        this.bcorpScore = 0;
        this.corporateCriticScore = 0;
        this.environmentScore = 0;
        this.goodOnYouScore = 0;
        this.greenPowerPercentage = 0;
        this.laborScore = 0;
        this.overallScore = 0;
        this.socialScore = 0;
        this.textileScore = 0;
        this.bcorpCertYear = 0;
        this.bcorpProfile = null;
        this.betterBusinessBureau = null;
        this.companyType = null;
        this.ethicalElephantType = null;
        this.name = null;
        this.supportsBLMContribution = null;
        this.supportsBLMEntity = null;
        this.supportsBLMSource = null;
        this.website = null;
    }

    public Business(@JsonProperty("name") String name, @JsonProperty("bcorpCertified") boolean bcorpCertified,
                    @JsonProperty("bcorpProfile") String bcorpProfile, @JsonProperty("website") String website,
                    @JsonProperty("bcorpCertYear") int bcorpCertYear, @JsonProperty("bcorpScore") double bcorpScore,
                    @JsonProperty("bcorpCommunityScore") double bcorpCommunityScore, @JsonProperty("bcorpEnvironmentScore") double bcorpEnvironmentScore,
                    @JsonProperty("bcorpGovernanceScore") double bcorpGovernanceScore, @JsonProperty("bcorpWorkerScore") double bcorpWorkerScore,
                    @JsonProperty("bluesignPartner") boolean bluesignPartner, @JsonProperty("supportsBLM") boolean supportsBLM,
                    @JsonProperty("supportsBLMSource") String supportsBLMSource, @JsonProperty("supportsBLMEntity") String supportsBLMEntity,
                    @JsonProperty("supportsBLMContribution") String supportsBLMContribution, @JsonProperty("companyType") String companyType,
                    @JsonProperty("blackOwnedBusiness") boolean blackOwnedBusiness, @JsonProperty("betterBusinessBureau") String betterBusinessBureau,
                    @JsonProperty("corporateCriticScore") double corporateCriticScore, @JsonProperty("goodOnYouScore") double goodOnYouScore,
                    @JsonProperty("environmentScore") double environmentScore, @JsonProperty("textileScore") double textileScore,
                    @JsonProperty("animalsScore") double animalsScore, @JsonProperty("laborScore") double laborScore,
                    @JsonProperty("socialScore") double socialScore, @JsonProperty("overallScore") double overallScore,
                    @JsonProperty("pocOwnedBusiness") boolean pocOwnedBusiness, @JsonProperty("nativeOwnedBusiness") boolean nativeOwnedBusiness,
                    @JsonProperty("greenPowerPercentage") double greenPowerPercentage, @JsonProperty("veganDotOrgCertified") boolean veganDotOrgCertified,
                    @JsonProperty("ethicalElephantCrueltyFree") boolean ethicalElephantCrueltyFree, @JsonProperty("ethicalElephantType") String ethicalElephantType) {
        this.bcorpCertified = bcorpCertified;
        this.blackOwnedBusiness = blackOwnedBusiness;
        this.bluesignPartner = bluesignPartner;
        this.ethicalElephantCrueltyFree = ethicalElephantCrueltyFree;
        this.nativeOwnedBusiness = nativeOwnedBusiness;
        this.pocOwnedBusiness = pocOwnedBusiness;
        this.supportsBLM = supportsBLM;
        this.veganDotOrgCertified = veganDotOrgCertified;
        this.animalsScore = animalsScore;
        this.overallScore = overallScore;
        this.bcorpCommunityScore = bcorpCommunityScore;
        this.bcorpEnvironmentScore = bcorpEnvironmentScore;
        this.bcorpGovernanceScore = bcorpGovernanceScore;
        this.bcorpWorkerScore = bcorpWorkerScore;
        this.bcorpScore = bcorpScore;
        this.corporateCriticScore = corporateCriticScore;
        this.environmentScore = environmentScore;
        this.goodOnYouScore = goodOnYouScore;
        this.greenPowerPercentage = greenPowerPercentage;
        this.laborScore = laborScore;
        this.socialScore = socialScore;
        this.textileScore = textileScore;
        this.bcorpCertYear = bcorpCertYear;
        this.bcorpProfile = bcorpProfile;
        this.betterBusinessBureau = betterBusinessBureau;
        this.companyType = companyType;
        this.ethicalElephantType = ethicalElephantType;
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

    public boolean isEthicalElephantCrueltyFree() {
        return ethicalElephantCrueltyFree;
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

    public double getBcorpCommunityScore() {
        return bcorpCommunityScore;
    }

    public double getBcorpEnvironmentScore() {
        return bcorpEnvironmentScore;
    }

    public double getBcorpGovernanceScore() {
        return bcorpGovernanceScore;
    }

    public double getBcorpWorkerScore() {
        return bcorpWorkerScore;
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

    public String getEthicalElephantType() {
        return ethicalElephantType;
    }

    public boolean isBlackOwnedBusiness() {
        return blackOwnedBusiness;
    }

    public double getAnimalsScore() {
        return animalsScore;
    }

    public double getOverallScore() {
        return overallScore;
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

    public double getSocialScore() {
        return socialScore;
    }

    public double getTextileScore() {
        return textileScore;
    }

    public String getBetterBusinessBureau() {
        return betterBusinessBureau;
    }

    public double getGreenPowerPercentage() {
        return greenPowerPercentage;
    }

    public boolean isNativeOwnedBusiness() {
        return nativeOwnedBusiness;
    }

    public boolean isVeganDotOrgCertified() {
        return veganDotOrgCertified;
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

    public void setBcorpCommunityScore(double bcorpCommunityScore) {
        this.bcorpCommunityScore = bcorpCommunityScore;
    }

    public void setBcorpEnvironmentScore(double bcorpEnvironmentScore) {
        this.bcorpEnvironmentScore = bcorpEnvironmentScore;
    }

    public void setBcorpGovernanceScore(double bcorpGovernanceScore) {
        this.bcorpGovernanceScore = bcorpGovernanceScore;
    }

    public void setEthicalElephantCrueltyFree(boolean ethicalElephantCrueltyFree) {
        this.ethicalElephantCrueltyFree = ethicalElephantCrueltyFree;
    }

    public void setBcorpWorkerScore(double bcorpWorkerScore) {
        this.bcorpWorkerScore = bcorpWorkerScore;
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

    public void setEthicalElephantType(String ethicalElephantType) {
        this.ethicalElephantType = ethicalElephantType;
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

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public void setGreenPowerPercentage(double greenPowerPercentage) {
        this.greenPowerPercentage = greenPowerPercentage;
    }

    public void setLaborScore(double laborScore) {
        this.laborScore = laborScore;
    }

    public void setSocialScore(double socialScore) {
        this.socialScore = socialScore;
    }

    public void setTextileScore(double textileScore) {
        this.textileScore = textileScore;
    }

    public void setSupportsBLMContribution(String supportsBLMContribution) {
        this.supportsBLMContribution = supportsBLMContribution;
    }

    public void setVeganDotOrgCertified(boolean veganDotOrgCertified) {
        this.veganDotOrgCertified = veganDotOrgCertified;
    }

    public void setNativeOwnedBusiness(boolean nativeOwnedBusiness) {
        this.nativeOwnedBusiness = nativeOwnedBusiness;
    }

    public void setPocOwnedBusiness(boolean pocOwnedBusiness) {
        this.pocOwnedBusiness = pocOwnedBusiness;
    }

    public void update(Business business){
        boolean update = true;
        if (business.getWebsite() != null && this.website != null) {
            if (!(business.getWebsite().contains(this.website) || this.website.contains(business.getWebsite()))) {
                update = false;
            }
        }
        if (update) {
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
            if (this.bcorpCommunityScore == 0)
                this.bcorpCommunityScore = business.getBcorpCommunityScore();
            if (this.bcorpEnvironmentScore == 0)
                this.bcorpEnvironmentScore = business.getBcorpEnvironmentScore();
            if (this.bcorpGovernanceScore == 0)
                this.bcorpGovernanceScore= business.getBcorpGovernanceScore();
            if (this.bcorpWorkerScore == 0)
                this.bcorpWorkerScore = business.getBcorpWorkerScore();
            if (this.bcorpScore == 0)
                this.bcorpScore = business.getBcorpScore();
            if (this.bluesignPartner == false)
                this.bluesignPartner = business.isBluesignPartner();
            if (this.supportsBLM == false)
                this.supportsBLM = business.getSupportsBLM();
            if (this.ethicalElephantCrueltyFree == false)
                this.ethicalElephantCrueltyFree = business.isEthicalElephantCrueltyFree();
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
            if (this.ethicalElephantType == null)
                this.ethicalElephantType = business.getEthicalElephantType();
            if (this.blackOwnedBusiness == false)
                this.blackOwnedBusiness = business.isBlackOwnedBusiness();
            if (this.betterBusinessBureau == null)
                this.betterBusinessBureau = business.getBetterBusinessBureau();
            if (this.corporateCriticScore == 0)
                this.corporateCriticScore = business.getCorporateCriticScore();
            if (this.goodOnYouScore == 0)
                this.goodOnYouScore = business.getGoodOnYouScore();
            if (this.greenPowerPercentage == 0)
                this.greenPowerPercentage = business.getGreenPowerPercentage();
            if (this.environmentScore == 0)
                this.environmentScore = business.getEnvironmentScore();
            if (this.textileScore == 0)
                this.textileScore = business.getTextileScore();
            if (this.animalsScore == 0)
                this.animalsScore = business.getAnimalsScore();
            if (this.laborScore == 0)
                this.laborScore = business.getLaborScore();
            if (this.overallScore == 0)
                this.overallScore = business.getOverallScore();
            if (this.veganDotOrgCertified == false)
                this.veganDotOrgCertified = business.isVeganDotOrgCertified();
            if (this.socialScore == 0) {
                this.socialScore = business.getSocialScore();
            }
            if (this.pocOwnedBusiness == false)
                this.pocOwnedBusiness = business.isPocOwnedBusiness();
            if (this.nativeOwnedBusiness == false)
                this.nativeOwnedBusiness = business.isNativeOwnedBusiness();
        }
    }

    public void calculate() {
        if (this.overallScore == 0) {
            // Environmental Impact Score
            double environmentalImpactScore = 0;
            final double BCORP_ENVIRORNMENT_MEAN = 17.8; // Mean of all bcorpWorkerScore data
            int environmentalImpactFactors = 0;
            if (this.greenPowerPercentage != 0) {
                ++environmentalImpactFactors;
                environmentalImpactScore += (5 + ((this.greenPowerPercentage / 100) * 2.5));
            }
            if (this.bcorpEnvironmentScore != 0) {
                ++environmentalImpactFactors;
                // Turns bcorpEnvironmentScore into a 0 - 10 score in which an average score would earn a 7.5
                environmentalImpactScore += (this.bcorpEnvironmentScore / (BCORP_ENVIRORNMENT_MEAN / 7.5));
            }
            if (bluesignPartner) {
                if (environmentalImpactFactors == 0) {
                    ++environmentalImpactFactors;
                    environmentalImpactScore += 7.5;
                } else {
                    environmentalImpactScore += environmentalImpactFactors;
                }
            }
            if (environmentalImpactScore != 0 && environmentalImpactFactors != 0) {
                this.environmentScore = environmentalImpactScore / environmentalImpactFactors;
            }
            if (this.environmentScore > 9.5) {
                this.environmentScore = 9.5;
            }
            // Labor Practice Score
            double laborImpactScore = 0;
            final double BCORP_LABOR_MEAN = 20.8;
            int laborImpactFactors = 0;
            if (this.bcorpWorkerScore != 0) {
                ++laborImpactFactors;
                // Turns bcorpWorkerScore into a 0 - 10 score in which an average score would earn a 7.5
                laborImpactScore += (this.bcorpWorkerScore / (BCORP_LABOR_MEAN / 7.5));
            }
            if (laborImpactScore != 0 && laborImpactFactors != 0) {
                this.laborScore = laborImpactScore / laborImpactFactors;
            }
            if (this.laborScore > 9.5) {
                this.laborScore = 9.5;
            }
            // Animal Welfare Score
            // Overall Score
            double overallImpactScore = 0;
            int overallImpactFactors = 0;
            if (this.environmentScore != 0) {
                ++overallImpactFactors;
                overallImpactScore += this.environmentScore;
            }
            if (this.laborScore != 0) {
                ++overallImpactFactors;
                overallImpactScore += this.laborScore;
            }
            if (this.animalsScore != 0) {
                ++overallImpactFactors;
                overallImpactScore += this.animalsScore;
            }
            if (overallImpactScore != 0 && overallImpactFactors != 0) {
                this.overallScore = overallImpactScore / overallImpactFactors;
            }
            if (this.overallScore > 9.5) {
                this.overallScore = 9.5;
            }
        }
    }

    public void display() {
        System.out.println("Name:                   " + this.name);
        System.out.println("Website:                " + this.website);
        System.out.println("Business Type:          " + this.companyType);
        System.out.println("Bcorp Community:        " + this.bcorpCommunityScore);
        System.out.println("Bcorp Environment:      " + this.bcorpEnvironmentScore);
        System.out.println("Bcorp Governance:       " + this.bcorpGovernanceScore);
        System.out.println("Bcorp Workers:          " + this.bcorpWorkerScore);
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
        System.out.println("Vegan.org Certified:    " + this.veganDotOrgCertified);
        System.out.println("EthicalElephant CR:     " + this.ethicalElephantCrueltyFree);
        System.out.println("EthicalElephant Type:   " + this.ethicalElephantType);
        System.out.println("BBB Score:              " + this.betterBusinessBureau);
        System.out.println("CCR Score:              " + this.corporateCriticScore);
        System.out.println("GoY Score:              " + this.goodOnYouScore);
        System.out.println("Green Power %:          " + this.greenPowerPercentage);
        System.out.println("Environment Score:      " + this.environmentScore);
        System.out.println("Textile Score:          " + this.textileScore);
        System.out.println("Animal Score:           " + this.animalsScore);
        System.out.println("Labor Score:            " + this.laborScore);
        System.out.println("Social Score:           " + this.socialScore);
        System.out.println("Overall Score:          " + this.overallScore);
    }
}
