package com.example.appengine.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Business {
  private boolean bcorpCertified;
  private boolean blackOwnedBusiness;
  private boolean bluesignPartner;
  private boolean certifiedHumane;
  private boolean chooseCrueltyFreeCertified;
  private boolean chooseCrueltyFreeVegan;
  private boolean ethicalElephantCrueltyFree;
  private boolean ethicalElephantTestsOnAnimals;
  private boolean leapingBunnyCertified;
  private boolean nativeOwnedBusiness;
  private boolean pocOwnedBusiness;
  private boolean supportsBLM;
  private boolean veganDotOrgCertified;
  private boolean wildlifeFriendlyCertified;
  private double animalsScore;
  private double bcorpCommunityScore;
  private double bcorpEnvironmentScore;
  private double bcorpGovernanceScore;
  private double bcorpScore;
  private double bcorpWorkerScore;
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
  private String overallScoreDetails;
  private String supportsBLMContribution;
  private String supportsBLMEntity;
  private String supportsBLMSource;
  private String website;
  private String[] animalScoreSource;
  private String[] environmentScoreSource;
  private String[] laborScoreSource;
  private String[] socialScoreSource;

  public Business() {
    this.bcorpCertified = false;
    this.blackOwnedBusiness = false;
    this.bluesignPartner = false;
    this.certifiedHumane = false;
    this.chooseCrueltyFreeCertified = false;
    this.chooseCrueltyFreeVegan = false;
    this.ethicalElephantCrueltyFree = false;
    this.ethicalElephantTestsOnAnimals = false;
    this.leapingBunnyCertified = false;
    this.nativeOwnedBusiness = false;
    this.pocOwnedBusiness = false;
    this.supportsBLM = false;
    this.veganDotOrgCertified = false;
    this.wildlifeFriendlyCertified = false;
    this.animalsScore = 0;
    this.bcorpCommunityScore = 0;
    this.bcorpEnvironmentScore = 0;
    this.bcorpGovernanceScore = 0;
    this.bcorpScore = 0;
    this.bcorpWorkerScore = 0;
    this.corporateCriticScore = 0;
    this.environmentScore = 0;
    this.goodOnYouScore = 0;
    this.greenPowerPercentage = 0;
    this.laborScore = 0;
    this.overallScore = 0;
    this.socialScore = 0;
    this.textileScore = 0;
    this.bcorpCertYear = 0;
    this.bcorpProfile = "";
    this.betterBusinessBureau = "";
    this.companyType = "";
    this.ethicalElephantType = "";
    this.name = "";
    this.overallScoreDetails = "";
    this.supportsBLMContribution = "";
    this.supportsBLMEntity = "";
    this.supportsBLMSource = "";
    this.website = "";
    this.animalScoreSource = new String[] {""};
    this.environmentScoreSource = new String[] {""};
    this.laborScoreSource = new String[] {""};
    this.socialScoreSource = new String[] {""};
  }

  public Business(
      @JsonProperty("name") String name, // Business Profile:
      @JsonProperty("website") String website,
      @JsonProperty("companyType") String companyType,
      @JsonProperty("bcorpCertYear") int bcorpCertYear,
      @JsonProperty("bcorpProfile") String bcorpProfile,
      @JsonProperty("blackOwnedBusiness") boolean blackOwnedBusiness,
      @JsonProperty("ethicalElephantType") String ethicalElephantType,
      @JsonProperty("nativeOwnedBusiness") boolean nativeOwnedBusiness,
      @JsonProperty("pocOwnedBusiness") boolean pocOwnedBusiness,
      @JsonProperty("supportsBLM") boolean supportsBLM,
      @JsonProperty("supportsBLMContribution") String supportsBLMContribution,
      @JsonProperty("supportsBLMEntity") String supportsBLMEntity,
      @JsonProperty("supportsBLMSource") String supportsBLMSource,
      @JsonProperty("bcorpCommunityScore")
          double bcorpCommunityScore, // Numerical Data for Calculation:
      @JsonProperty("bcorpEnvironmentScore") double bcorpEnvironmentScore,
      @JsonProperty("bcorpGovernanceScore") double bcorpGovernanceScore,
      @JsonProperty("bcorpScore") double bcorpScore,
      @JsonProperty("bcorpWorkerScore") double bcorpWorkerScore,
      @JsonProperty("corporateCriticScore") double corporateCriticScore,
      @JsonProperty("goodOnYouScore") double goodOnYouScore,
      @JsonProperty("greenPowerPercentage") double greenPowerPercentage,
      @JsonProperty("betterBusinessBureau")
          String betterBusinessBureau, // Boolean Data for Calculation:
      @JsonProperty("bcorpCertified") boolean bcorpCertified,
      @JsonProperty("bluesignPartner") boolean bluesignPartner,
      @JsonProperty("certifiedHumane") boolean certifiedHumane,
      @JsonProperty("chooseCrueltyFreeCertified") boolean chooseCrueltyFreeCertified,
      @JsonProperty("chooseCrueltyFreeVegan") boolean chooseCrueltyFreeVegan,
      @JsonProperty("ethicalElephantCrueltyFree") boolean ethicalElephantCrueltyFree,
      @JsonProperty("ethicalElephantTestsOnAnimals") boolean ethicalElephantTestsOnAnimals,
      @JsonProperty("leapingBunnyCertified") boolean leapingBunnyCertified,
      @JsonProperty("veganDotOrgCertified") boolean veganDotOrgCertified,
      @JsonProperty("wildlifeFriendlyCertified") boolean wildlifeFriendlyCertified,
      @JsonProperty("animalScoreSource") String[] animalScoreSource, // Business Info:
      @JsonProperty("environmentalScoreSource") String[] environmentScoreSource,
      @JsonProperty("laborScoreSource") String[] laborScoreSource,
      @JsonProperty("socialScoreSource") String[] socialScoreSource,
      @JsonProperty("overallScoreDetails") String overallScoreDetails,
      @JsonProperty("animalsScore") double animalsScore, // Ethicli Scores:
      @JsonProperty("environmentScore") double environmentScore,
      @JsonProperty("laborScore") double laborScore,
      @JsonProperty("socialScore") double socialScore,
      @JsonProperty("textileScore") double textileScore,
      @JsonProperty("overallScore") double overallScore) {
    this.name = name;
    this.website = website;
    this.companyType = companyType;
    this.bcorpCertYear = bcorpCertYear;
    this.bcorpProfile = bcorpProfile;
    this.blackOwnedBusiness = blackOwnedBusiness;
    this.ethicalElephantType = ethicalElephantType;
    this.nativeOwnedBusiness = nativeOwnedBusiness;
    this.pocOwnedBusiness = pocOwnedBusiness;
    this.supportsBLM = supportsBLM;
    this.supportsBLMContribution = supportsBLMContribution;
    this.supportsBLMEntity = supportsBLMEntity;
    this.supportsBLMSource = supportsBLMSource;
    this.bcorpScore = bcorpScore;
    this.bcorpCommunityScore = bcorpCommunityScore;
    this.bcorpEnvironmentScore = bcorpEnvironmentScore;
    this.bcorpGovernanceScore = bcorpGovernanceScore;
    this.bcorpWorkerScore = bcorpWorkerScore;
    this.corporateCriticScore = corporateCriticScore;
    this.goodOnYouScore = goodOnYouScore;
    this.greenPowerPercentage = greenPowerPercentage;
    this.betterBusinessBureau = betterBusinessBureau;
    this.bcorpCertified = bcorpCertified;
    this.bluesignPartner = bluesignPartner;
    this.certifiedHumane = certifiedHumane;
    this.chooseCrueltyFreeCertified = chooseCrueltyFreeCertified;
    this.chooseCrueltyFreeVegan = chooseCrueltyFreeVegan;
    this.ethicalElephantCrueltyFree = ethicalElephantCrueltyFree;
    this.ethicalElephantTestsOnAnimals = ethicalElephantTestsOnAnimals;
    this.leapingBunnyCertified = leapingBunnyCertified;
    this.veganDotOrgCertified = veganDotOrgCertified;
    this.wildlifeFriendlyCertified = wildlifeFriendlyCertified;
    this.animalScoreSource = animalScoreSource;
    this.environmentScoreSource = environmentScoreSource;
    this.laborScoreSource = laborScoreSource;
    this.socialScoreSource = socialScoreSource;
    this.overallScoreDetails = overallScoreDetails;
    this.animalsScore = animalsScore;
    this.environmentScore = environmentScore;
    this.laborScore = laborScore;
    this.socialScore = socialScore;
    this.textileScore = textileScore;
    this.overallScore = overallScore;
  }

  // Getters:
  public boolean isBcorpCertified() {
    return bcorpCertified;
  }

  public boolean isBlackOwnedBusiness() {
    return blackOwnedBusiness;
  }

  public boolean isBluesignPartner() {
    return bluesignPartner;
  }

  public boolean isCertifiedHumane() {
    return certifiedHumane;
  }

  public boolean isChooseCrueltyFreeCertified() {
    return chooseCrueltyFreeCertified;
  }

  public boolean isChooseCrueltyFreeVegan() {
    return chooseCrueltyFreeVegan;
  }

  public boolean isEthicalElephantCrueltyFree() {
    return ethicalElephantCrueltyFree;
  }

  public boolean isEthicalElephantTestsOnAnimals() {
    return ethicalElephantTestsOnAnimals;
  }

  public boolean isLeapingBunnyCertified() {
    return leapingBunnyCertified;
  }

  public boolean isNativeOwnedBusiness() {
    return nativeOwnedBusiness;
  }

  public boolean isPocOwnedBusiness() {
    return pocOwnedBusiness;
  }

  public boolean isSupportsBLM() {
    return supportsBLM;
  }

  public boolean isVeganDotOrgCertified() {
    return veganDotOrgCertified;
  }

  public boolean isWildlifeFriendlyCertified() {
    return wildlifeFriendlyCertified;
  }

  public double getAnimalsScore() {
    return animalsScore;
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

  public double getBcorpScore() {
    return bcorpScore;
  }

  public double getBcorpWorkerScore() {
    return bcorpWorkerScore;
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

  public double getOverallScore() {
    return overallScore;
  }

  public double getSocialScore() {
    return socialScore;
  }

  public double getTextileScore() {
    return textileScore;
  }

  public int getBcorpCertYear() {
    return bcorpCertYear;
  }

  public String[] getAnimalScoreSource() {
    return animalScoreSource;
  }

  public String getBcorpProfile() {
    return bcorpProfile;
  }

  public String getBetterBusinessBureau() {
    return betterBusinessBureau;
  }

  public String getCompanyType() {
    return companyType;
  }

  public String[] getEnvironmentScoreSource() {
    return environmentScoreSource;
  }

  public String getEthicalElephantType() {
    return ethicalElephantType;
  }

  public String[] getLaborScoreSource() {
    return laborScoreSource;
  }

  public String getName() {
    return name;
  }

  public String getOverallScoreDetails() {
    return overallScoreDetails;
  }

  public String[] getSocialScoreSource() {
    return socialScoreSource;
  }

  public String getSupportsBLMContribution() {
    return supportsBLMContribution;
  }

  public String getSupportsBLMEntity() {
    return supportsBLMEntity;
  }

  public String getSupportsBLMSource() {
    return supportsBLMSource;
  }

  public double getGreenPowerPercentage() {
    return greenPowerPercentage;
  }

  public String getWebsite() {
    return website;
  }

  // Setters:
  public void setBcorpCertified(boolean certified) {
    this.bcorpCertified = certified;
  }

  public void setBlackOwnedBusiness(boolean blackOwnedBusiness) {
    this.blackOwnedBusiness = blackOwnedBusiness;
  }

  public void setBluesignPartner(boolean bluesignPartner) {
    this.bluesignPartner = bluesignPartner;
  }

  public void setCertifiedHumane(boolean certifiedHumane) {
    this.certifiedHumane = certifiedHumane;
  }

  public void setChooseCrueltyFreeCertified(boolean chooseCrueltyFreeCertified) {
    this.chooseCrueltyFreeCertified = chooseCrueltyFreeCertified;
  }

  public void setChooseCrueltyFreeVegan(boolean chooseCrueltyFreeVegan) {
    this.chooseCrueltyFreeVegan = chooseCrueltyFreeVegan;
  }

  public void setEthicalElephantCrueltyFree(boolean ethicalElephantCrueltyFree) {
    this.ethicalElephantCrueltyFree = ethicalElephantCrueltyFree;
  }

  public void setEthicalElephantTestsOnAnimals(boolean ethicalElephantTestsOnAnimals) {
    this.ethicalElephantTestsOnAnimals = ethicalElephantTestsOnAnimals;
  }

  public void setLeapingBunnyCertified(boolean leapingBunnyCertified) {
    this.leapingBunnyCertified = leapingBunnyCertified;
  }

  public void setNativeOwnedBusiness(boolean nativeOwnedBusiness) {
    this.nativeOwnedBusiness = nativeOwnedBusiness;
  }

  public void setPocOwnedBusiness(boolean pocOwnedBusiness) {
    this.pocOwnedBusiness = pocOwnedBusiness;
  }

  public void setSupportsBLM(boolean supportsBLM) {
    this.supportsBLM = supportsBLM;
  }

  public void setVeganDotOrgCertified(boolean veganDotOrgCertified) {
    this.veganDotOrgCertified = veganDotOrgCertified;
  }

  public void setWildlifeFriendlyCertified(boolean wildlifeFriendlyCertified) {
    this.wildlifeFriendlyCertified = wildlifeFriendlyCertified;
  }

  public void setAnimalsScore(double animalsScore) {
    this.animalsScore = animalsScore;
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

  public void setBcorpScore(double bcorpScore) {
    this.bcorpScore = bcorpScore;
  }

  public void setBcorpWorkerScore(double bcorpWorkerScore) {
    this.bcorpWorkerScore = bcorpWorkerScore;
  }

  public void setCorporateCriticScore(double corporateCriticScore) {
    this.corporateCriticScore = corporateCriticScore;
  }

  public void setEnvironmentScore(double environmentScore) {
    this.environmentScore = environmentScore;
  }

  public void setGoodOnYouScore(double goodOnYouScore) {
    this.goodOnYouScore = goodOnYouScore;
  }

  public void setGreenPowerPercentage(double greenPowerPercentage) {
    this.greenPowerPercentage = greenPowerPercentage;
  }

  public void setLaborScore(double laborScore) {
    this.laborScore = laborScore;
  }

  public void setOverallScore(double overallScore) {
    this.overallScore = overallScore;
  }

  public void setSocialScore(double socialScore) {
    this.socialScore = socialScore;
  }

  public void setTextileScore(double textileScore) {
    this.textileScore = textileScore;
  }

  public void setBcorpCertYear(int bcorpCertYear) {
    this.bcorpCertYear = bcorpCertYear;
  }

  public void setAnimalScoreSource(String[] animalScoreSource) {
    this.animalScoreSource = animalScoreSource;
  }

  public void setBcorpProfile(String bcorpProfile) {
    this.bcorpProfile = bcorpProfile;
  }

  public void setBetterBusinessBureau(String betterBusinessBureau) {
    this.betterBusinessBureau = betterBusinessBureau;
  }

  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public void setEnvironmentScoreSource(String[] environmentScoreSource) {
    this.environmentScoreSource = environmentScoreSource;
  }

  public void setEthicalElephantType(String ethicalElephantType) {
    this.ethicalElephantType = ethicalElephantType;
  }

  public void setLaborScoreSource(String[] laborScoreSource) {
    this.laborScoreSource = laborScoreSource;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOverallScoreDetails(String overallScoreDetails) {
    this.overallScoreDetails = overallScoreDetails;
  }

  public void setSocialScoreSource(String[] socialScoreSource) {
    this.socialScoreSource = socialScoreSource;
  }

  public void setSupportsBLMContribution(String supportsBLMContribution) {
    this.supportsBLMContribution = supportsBLMContribution;
  }

  public void setSupportsBLMEntity(String supportsBLMEntity) {
    this.supportsBLMEntity = supportsBLMEntity;
  }

  public void setSupportsBLMSource(String supportsBLMSource) {
    this.supportsBLMSource = supportsBLMSource;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public void update(Business business) {
    boolean update = true;
    if (business.getWebsite() != null && this.website != null) {
      if (!(business.getWebsite().contains(this.website)
          || this.website.contains(business.getWebsite()))) {
        update = false;
      }
    }
    if (update) {
      if (this.bcorpCertified == false) this.bcorpCertified = business.isBcorpCertified();
      if (this.blackOwnedBusiness == false)
        this.blackOwnedBusiness = business.isBlackOwnedBusiness();
      if (this.bluesignPartner == false) this.bluesignPartner = business.isBluesignPartner();
      if (this.certifiedHumane == false) this.certifiedHumane = business.isCertifiedHumane();
      if (this.chooseCrueltyFreeCertified == false)
        this.chooseCrueltyFreeCertified = business.isChooseCrueltyFreeCertified();
      if (this.chooseCrueltyFreeVegan == false)
        this.chooseCrueltyFreeVegan = business.isChooseCrueltyFreeVegan();
      if (this.ethicalElephantCrueltyFree == false)
        this.ethicalElephantCrueltyFree = business.isEthicalElephantCrueltyFree();
      if (this.ethicalElephantTestsOnAnimals == false)
        this.ethicalElephantTestsOnAnimals = business.isEthicalElephantTestsOnAnimals();
      if (this.leapingBunnyCertified == false)
        this.leapingBunnyCertified = business.isLeapingBunnyCertified();
      if (this.nativeOwnedBusiness == false)
        this.nativeOwnedBusiness = business.isNativeOwnedBusiness();
      if (this.pocOwnedBusiness == false) this.pocOwnedBusiness = business.isPocOwnedBusiness();
      if (this.supportsBLM == false) this.supportsBLM = business.isSupportsBLM();
      if (this.veganDotOrgCertified == false)
        this.veganDotOrgCertified = business.isVeganDotOrgCertified();
      if (this.wildlifeFriendlyCertified == false)
        this.wildlifeFriendlyCertified = business.isWildlifeFriendlyCertified();
      if (this.animalsScore == 0) this.animalsScore = business.getAnimalsScore();
      if (this.bcorpCommunityScore == 0)
        this.bcorpCommunityScore = business.getBcorpCommunityScore();
      if (this.bcorpEnvironmentScore == 0)
        this.bcorpEnvironmentScore = business.getBcorpEnvironmentScore();
      if (this.bcorpGovernanceScore == 0)
        this.bcorpGovernanceScore = business.getBcorpGovernanceScore();
      if (this.bcorpScore == 0) this.bcorpScore = business.getBcorpScore();
      if (this.bcorpWorkerScore == 0) this.bcorpWorkerScore = business.getBcorpWorkerScore();
      if (this.corporateCriticScore == 0)
        this.corporateCriticScore = business.getCorporateCriticScore();
      if (this.environmentScore == 0) this.environmentScore = business.getEnvironmentScore();
      if (this.goodOnYouScore == 0) this.goodOnYouScore = business.getGoodOnYouScore();
      if (this.greenPowerPercentage == 0)
        this.greenPowerPercentage = business.getGreenPowerPercentage();
      if (this.laborScore == 0) this.laborScore = business.getLaborScore();
      if (this.overallScore == 0) this.overallScore = business.getOverallScore();
      if (this.socialScore == 0) this.socialScore = business.getSocialScore();
      if (this.textileScore == 0) this.textileScore = business.getTextileScore();
      if (this.bcorpCertYear == 0) this.bcorpCertYear = business.getBcorpCertYear();
      if (this.animalScoreSource[0].equals(""))
        this.animalScoreSource = business.getAnimalScoreSource();
      if (this.bcorpProfile.equals("")) this.bcorpProfile = business.getBcorpProfile();
      if (this.betterBusinessBureau.equals(""))
        this.betterBusinessBureau = business.getBetterBusinessBureau();
      if (this.companyType.equals("")) this.companyType = business.getCompanyType();
      if (this.environmentScoreSource[0].equals(""))
        this.environmentScoreSource = business.getEnvironmentScoreSource();
      if (this.ethicalElephantType == "")
        this.ethicalElephantType = business.getEthicalElephantType();
      if (this.laborScoreSource[0].equals(""))
        this.laborScoreSource = business.getLaborScoreSource();
      if (this.name.equals("")) this.name = business.getName();
      if (this.overallScoreDetails.equals(""))
        this.overallScoreDetails = business.getOverallScoreDetails();
      if (this.socialScoreSource[0].equals(""))
        this.socialScoreSource = business.getSocialScoreSource();
      if (this.supportsBLMContribution.equals("")) {
        this.supportsBLMContribution = business.getSupportsBLMContribution();
      } else if (!business.getSupportsBLMContribution().equals("")) {
        this.supportsBLMContribution += " + " + business.getSupportsBLMContribution();
      }
      if (this.supportsBLMEntity.equals("")) {
        this.supportsBLMEntity = business.getSupportsBLMEntity();
      } else if (!business.getSupportsBLMEntity().equals("")) {
        this.supportsBLMEntity += " + " + business.getSupportsBLMEntity();
      }
      if (this.supportsBLMSource.equals("")) {
        this.supportsBLMSource = business.getSupportsBLMSource();
      } else if (!business.getSupportsBLMSource().equals("")) {
        this.supportsBLMSource += " + " + business.getSupportsBLMSource();
      }
      if (this.website.equals("")) this.website = business.getWebsite();
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
        // Turns bcorpEnvironmentScore into a 0 - 10 score in which an average score would earn a
        // 7.5
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
      // Mean scores based on number of animal factors (0 - 10):
      double testsOnAnimalsNoFactors = 3;
      double testsOnAnimalsWithFactors = .55;
      double oneFactorAnimal = 6;
      double twoFactorAnimal = 8;
      double threeFactorAnimal = 9;
      double fourFactorAnimal = 9.5;
      double fiveFactorAnimal = 9.75;
      double rangeAnimal = 1;
      // Weights for Animal Factors (1 - 10):
      double ethicalElephantCrueltyFreeVeganWeight = 9;
      double veganDotOrgCertifiedWeight = 9;
      double chooseCrueltyFreeVeganWeight = 9;
      double ethicalElephantCrueltyFreeVeganOptionsWeight = 8.5;
      double ethicalElephantCrueltyFreeWeight = 8;
      double leapingBunnyWeight = 8;
      double certifiedHumaneWeight = 7;
      double chooseCrueltyFreeWeight = 7;
      double numberOfFactorsAnimal = 5;
      // Animal Factor Switches (Tells the Positive Factor loop if a factor has been used yet):
      boolean ethicalElephantCrueltyFreeVeganSwitch = true;
      boolean veganDotOrgCertifiedSwitch = true;
      boolean chooseCrueltyFreeVeganSwitch = true;
      boolean ethicalElephantCrueltyFreeVeganOptionsSwitch = true;
      boolean ethicalElephantCrueltyFreeSwitch = true;
      boolean leapingBunnySwitch = true;
      boolean certifiedHumaneSwitch = true;
      boolean chooseCrueltyFreeSwitch = true;
      // Animal Calculation Variables:
      double[] scoreGuideAnimal = {
        oneFactorAnimal,
        twoFactorAnimal - oneFactorAnimal,
        threeFactorAnimal - twoFactorAnimal,
        fourFactorAnimal - threeFactorAnimal,
        fiveFactorAnimal - fourFactorAnimal
      };
      double cumulativeAnimalScore = 0;
      double animalFactors = 0;
      double animalWeightMean =
          (certifiedHumaneWeight
                  + veganDotOrgCertifiedWeight
                  + ((ethicalElephantCrueltyFreeVeganWeight
                          + ethicalElephantCrueltyFreeVeganOptionsWeight
                          + ethicalElephantCrueltyFreeWeight)
                      / 3)
                  + ((chooseCrueltyFreeVeganWeight + chooseCrueltyFreeVeganWeight) / 2)
                  + leapingBunnyWeight)
              / numberOfFactorsAnimal;

      // Animal Welfare - Positive Factors:
      for (int a = 0; a < scoreGuideAnimal.length; ++a) {
        if (ethicalElephantCrueltyFreeVeganSwitch
            && this.ethicalElephantCrueltyFree
            && this.ethicalElephantType.equals("Vegan")) {
          cumulativeAnimalScore +=
              scoreGuideAnimal[a]
                  - (rangeAnimal / 2)
                  + (rangeAnimal * (ethicalElephantCrueltyFreeVeganWeight / animalWeightMean) / 2);
          ethicalElephantCrueltyFreeVeganSwitch = false;
          ++animalFactors;
        } else if (veganDotOrgCertifiedSwitch && this.veganDotOrgCertified) {
          cumulativeAnimalScore +=
              scoreGuideAnimal[a]
                  - (rangeAnimal / 2)
                  + (rangeAnimal * (veganDotOrgCertifiedWeight / animalWeightMean) / 2);
          veganDotOrgCertifiedSwitch = false;
          ++animalFactors;
        } else if (chooseCrueltyFreeVeganSwitch
            && this.chooseCrueltyFreeCertified
            && this.chooseCrueltyFreeVegan) {
          cumulativeAnimalScore +=
              scoreGuideAnimal[a]
                  - (rangeAnimal / 2)
                  + (rangeAnimal * (chooseCrueltyFreeVeganWeight / animalWeightMean) / 2);
          chooseCrueltyFreeVeganSwitch = false;
          ++animalFactors;
        } else if (ethicalElephantCrueltyFreeVeganOptionsSwitch
            && this.ethicalElephantCrueltyFree
            && this.ethicalElephantType.equals("Vegan Options")) {
          cumulativeAnimalScore +=
              scoreGuideAnimal[a]
                  - (rangeAnimal / 2)
                  + (rangeAnimal
                      * (ethicalElephantCrueltyFreeVeganOptionsWeight / animalWeightMean)
                      / 2);
          ethicalElephantCrueltyFreeVeganOptionsSwitch = false;
          ++animalFactors;
        } else if (ethicalElephantCrueltyFreeSwitch
            && this.ethicalElephantCrueltyFree
            && this.ethicalElephantType.equals("")) {
          cumulativeAnimalScore +=
              scoreGuideAnimal[a]
                  - (rangeAnimal / 2)
                  + (rangeAnimal * (ethicalElephantCrueltyFreeWeight / animalWeightMean) / 2);
          ethicalElephantCrueltyFreeSwitch = false;
          ++animalFactors;
        } else if (leapingBunnySwitch && this.leapingBunnyCertified) {
          cumulativeAnimalScore +=
              scoreGuideAnimal[a]
                  - (rangeAnimal / 2)
                  + (rangeAnimal * (leapingBunnyWeight / animalWeightMean) / 2);
          leapingBunnySwitch = false;
          ++animalFactors;
        } else if (certifiedHumaneSwitch && this.certifiedHumane) {
          cumulativeAnimalScore +=
              scoreGuideAnimal[a]
                  - (rangeAnimal / 2)
                  + (rangeAnimal * (certifiedHumaneWeight / animalWeightMean) / 2);
          certifiedHumaneSwitch = false;
          ++animalFactors;
        } else if (chooseCrueltyFreeSwitch
            && this.chooseCrueltyFreeCertified
            && !this.chooseCrueltyFreeVegan) {
          cumulativeAnimalScore +=
              scoreGuideAnimal[a]
                  - (rangeAnimal / 2)
                  + (rangeAnimal * (chooseCrueltyFreeWeight / animalWeightMean) / 2);
          chooseCrueltyFreeSwitch = false;
          ++animalFactors;
        } else {
          a = scoreGuideAnimal.length;
        }
      }

      // Animal Welfare - Negative Factors:
      if (this.ethicalElephantTestsOnAnimals) {
        if (cumulativeAnimalScore == 0) {
          cumulativeAnimalScore = testsOnAnimalsNoFactors;
        } else {
          for (int a = 0; a < animalFactors; ++a) {
            testsOnAnimalsWithFactors = Math.sqrt(testsOnAnimalsWithFactors);
          }
          cumulativeAnimalScore -= testsOnAnimalsWithFactors;
        }
      }

      // Animal Welfare - Score:
      if (cumulativeAnimalScore > 10) cumulativeAnimalScore = 10;
      this.animalsScore = cumulativeAnimalScore;

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
}
