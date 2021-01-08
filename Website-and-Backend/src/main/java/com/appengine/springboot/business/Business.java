package com.appengine.springboot.business;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Business {

  @Id
  private String id;
  private boolean betterCottonMember;
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
  private String country;
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
    this.betterCottonMember = false;
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
    this.country = "";
    this.ethicalElephantType = "";
    this.name = "";
    this.overallScoreDetails = "";
    this.supportsBLMContribution = "";
    this.supportsBLMEntity = "";
    this.supportsBLMSource = "";
    this.website = "";
    this.animalScoreSource = new String[]{""};
    this.environmentScoreSource = new String[]{""};
    this.laborScoreSource = new String[]{""};
    this.socialScoreSource = new String[]{""};
  }

  public Business(
    String name, // Business Profile:
    String website,
    String companyType,
    String country,
    int bcorpCertYear,
    String bcorpProfile,
    boolean blackOwnedBusiness,
    String ethicalElephantType,
    boolean nativeOwnedBusiness,
    boolean pocOwnedBusiness,
    boolean supportsBLM,
    String supportsBLMContribution,
    String supportsBLMEntity,
    String supportsBLMSource,
    double bcorpCommunityScore, // Numerical Data for Calculation:
    double bcorpEnvironmentScore,
    double bcorpGovernanceScore,
    double bcorpScore,
    double bcorpWorkerScore,
    double corporateCriticScore,
    double goodOnYouScore,
    double greenPowerPercentage,
    String betterBusinessBureau, // Boolean Data for Calculation:
    boolean betterCottonMember,
    boolean bcorpCertified,
    boolean bluesignPartner,
    boolean certifiedHumane,
    boolean chooseCrueltyFreeCertified,
    boolean chooseCrueltyFreeVegan,
    boolean ethicalElephantCrueltyFree,
    boolean ethicalElephantTestsOnAnimals,
    boolean leapingBunnyCertified,
    boolean veganDotOrgCertified,
    boolean wildlifeFriendlyCertified,
    String[] animalScoreSource, // Business Info:
    String[] environmentScoreSource,
    String[] laborScoreSource,
    String[] socialScoreSource,
    String overallScoreDetails,
    double animalsScore, // Ethicli Scores:
    double environmentScore,
    double laborScore,
    double socialScore,
    double textileScore,
    double overallScore) {
    this.name = name;
    this.website = website;
    this.companyType = companyType;
    this.country = country;
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
    this.betterCottonMember = betterCottonMember;
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

  public boolean isBetterCottonMember() {
    return betterCottonMember;
  }

  public void setBetterCottonMember(boolean betterCottonMember) {
    this.betterCottonMember = betterCottonMember;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isBcorpCertified() {
    return bcorpCertified;
  }

  public void setBcorpCertified(boolean certified) {
    this.bcorpCertified = certified;
  }

  public boolean isBlackOwnedBusiness() {
    return blackOwnedBusiness;
  }

  public void setBlackOwnedBusiness(boolean blackOwnedBusiness) {
    this.blackOwnedBusiness = blackOwnedBusiness;
  }

  public boolean isBluesignPartner() {
    return bluesignPartner;
  }

  public void setBluesignPartner(boolean bluesignPartner) {
    this.bluesignPartner = bluesignPartner;
  }

  public boolean isCertifiedHumane() {
    return certifiedHumane;
  }

  public void setCertifiedHumane(boolean certifiedHumane) {
    this.certifiedHumane = certifiedHumane;
  }

  public boolean isChooseCrueltyFreeCertified() {
    return chooseCrueltyFreeCertified;
  }

  public void setChooseCrueltyFreeCertified(boolean chooseCrueltyFreeCertified) {
    this.chooseCrueltyFreeCertified = chooseCrueltyFreeCertified;
  }

  public boolean isChooseCrueltyFreeVegan() {
    return chooseCrueltyFreeVegan;
  }

  public void setChooseCrueltyFreeVegan(boolean chooseCrueltyFreeVegan) {
    this.chooseCrueltyFreeVegan = chooseCrueltyFreeVegan;
  }

  public boolean isEthicalElephantCrueltyFree() {
    return ethicalElephantCrueltyFree;
  }

  public void setEthicalElephantCrueltyFree(boolean ethicalElephantCrueltyFree) {
    this.ethicalElephantCrueltyFree = ethicalElephantCrueltyFree;
  }

  public boolean isEthicalElephantTestsOnAnimals() {
    return ethicalElephantTestsOnAnimals;
  }

  public void setEthicalElephantTestsOnAnimals(boolean ethicalElephantTestsOnAnimals) {
    this.ethicalElephantTestsOnAnimals = ethicalElephantTestsOnAnimals;
  }

  public boolean isLeapingBunnyCertified() {
    return leapingBunnyCertified;
  }

  public void setLeapingBunnyCertified(boolean leapingBunnyCertified) {
    this.leapingBunnyCertified = leapingBunnyCertified;
  }

  public boolean isNativeOwnedBusiness() {
    return nativeOwnedBusiness;
  }

  public void setNativeOwnedBusiness(boolean nativeOwnedBusiness) {
    this.nativeOwnedBusiness = nativeOwnedBusiness;
  }

  public boolean isPocOwnedBusiness() {
    return pocOwnedBusiness;
  }

  public void setPocOwnedBusiness(boolean pocOwnedBusiness) {
    this.pocOwnedBusiness = pocOwnedBusiness;
  }

  public boolean isSupportsBLM() {
    return supportsBLM;
  }

  public void setSupportsBLM(boolean supportsBLM) {
    this.supportsBLM = supportsBLM;
  }

  public boolean isVeganDotOrgCertified() {
    return veganDotOrgCertified;
  }

  public void setVeganDotOrgCertified(boolean veganDotOrgCertified) {
    this.veganDotOrgCertified = veganDotOrgCertified;
  }

  public boolean isWildlifeFriendlyCertified() {
    return wildlifeFriendlyCertified;
  }

  public void setWildlifeFriendlyCertified(boolean wildlifeFriendlyCertified) {
    this.wildlifeFriendlyCertified = wildlifeFriendlyCertified;
  }

  public double getAnimalsScore() {
    return animalsScore;
  }

  public void setAnimalsScore(double animalsScore) {
    this.animalsScore = animalsScore;
  }

  public double getBcorpCommunityScore() {
    return bcorpCommunityScore;
  }

  public void setBcorpCommunityScore(double bcorpCommunityScore) {
    this.bcorpCommunityScore = bcorpCommunityScore;
  }

  public double getBcorpEnvironmentScore() {
    return bcorpEnvironmentScore;
  }

  public void setBcorpEnvironmentScore(double bcorpEnvironmentScore) {
    this.bcorpEnvironmentScore = bcorpEnvironmentScore;
  }

  public double getBcorpGovernanceScore() {
    return bcorpGovernanceScore;
  }

  public void setBcorpGovernanceScore(double bcorpGovernanceScore) {
    this.bcorpGovernanceScore = bcorpGovernanceScore;
  }

  public double getBcorpScore() {
    return bcorpScore;
  }

  public void setBcorpScore(double bcorpScore) {
    this.bcorpScore = bcorpScore;
  }

  public double getBcorpWorkerScore() {
    return bcorpWorkerScore;
  }

  public void setBcorpWorkerScore(double bcorpWorkerScore) {
    this.bcorpWorkerScore = bcorpWorkerScore;
  }

  public double getCorporateCriticScore() {
    return corporateCriticScore;
  }

  public void setCorporateCriticScore(double corporateCriticScore) {
    this.corporateCriticScore = corporateCriticScore;
  }

  public double getEnvironmentScore() {
    return environmentScore;
  }

  public void setEnvironmentScore(double environmentScore) {
    this.environmentScore = environmentScore;
  }

  public double getGoodOnYouScore() {
    return goodOnYouScore;
  }

  public void setGoodOnYouScore(double goodOnYouScore) {
    this.goodOnYouScore = goodOnYouScore;
  }

  public double getLaborScore() {
    return laborScore;
  }

  public void setLaborScore(double laborScore) {
    this.laborScore = laborScore;
  }

  public double getOverallScore() {
    return overallScore;
  }

  public void setOverallScore(double overallScore) {
    this.overallScore = overallScore;
  }

  public double getSocialScore() {
    return socialScore;
  }

  public void setSocialScore(double socialScore) {
    this.socialScore = socialScore;
  }

  public double getTextileScore() {
    return textileScore;
  }

  public void setTextileScore(double textileScore) {
    this.textileScore = textileScore;
  }

  public int getBcorpCertYear() {
    return bcorpCertYear;
  }

  public void setBcorpCertYear(int bcorpCertYear) {
    this.bcorpCertYear = bcorpCertYear;
  }

  public String[] getAnimalScoreSource() {
    return animalScoreSource;
  }

  public void setAnimalScoreSource(String[] animalScoreSource) {
    this.animalScoreSource = animalScoreSource;
  }

  public String getBcorpProfile() {
    return bcorpProfile;
  }

  public void setBcorpProfile(String bcorpProfile) {
    this.bcorpProfile = bcorpProfile;
  }

  public String getBetterBusinessBureau() {
    return betterBusinessBureau;
  }

  public void setBetterBusinessBureau(String betterBusinessBureau) {
    this.betterBusinessBureau = betterBusinessBureau;
  }

  public String getCompanyType() {
    return companyType;
  }

  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public String[] getEnvironmentScoreSource() {
    return environmentScoreSource;
  }

  public void setEnvironmentScoreSource(String[] environmentScoreSource) {
    this.environmentScoreSource = environmentScoreSource;
  }

  public String getEthicalElephantType() {
    return ethicalElephantType;
  }

  public void setEthicalElephantType(String ethicalElephantType) {
    this.ethicalElephantType = ethicalElephantType;
  }

  public String[] getLaborScoreSource() {
    return laborScoreSource;
  }

  public void setLaborScoreSource(String[] laborScoreSource) {
    this.laborScoreSource = laborScoreSource;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOverallScoreDetails() {
    return overallScoreDetails;
  }

  public void setOverallScoreDetails(String overallScoreDetails) {
    this.overallScoreDetails = overallScoreDetails;
  }

  public String[] getSocialScoreSource() {
    return socialScoreSource;
  }

  public void setSocialScoreSource(String[] socialScoreSource) {
    this.socialScoreSource = socialScoreSource;
  }

  public String getSupportsBLMContribution() {
    return supportsBLMContribution;
  }

  public void setSupportsBLMContribution(String supportsBLMContribution) {
    this.supportsBLMContribution = supportsBLMContribution;
  }

  public String getSupportsBLMEntity() {
    return supportsBLMEntity;
  }

  public void setSupportsBLMEntity(String supportsBLMEntity) {
    this.supportsBLMEntity = supportsBLMEntity;
  }

  public String getSupportsBLMSource() {
    return supportsBLMSource;
  }

  public void setSupportsBLMSource(String supportsBLMSource) {
    this.supportsBLMSource = supportsBLMSource;
  }

  public double getGreenPowerPercentage() {
    return greenPowerPercentage;
  }

  public void setGreenPowerPercentage(double greenPowerPercentage) {
    this.greenPowerPercentage = greenPowerPercentage;
  }

  public String getWebsite() {
    return website;
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
      if (this.country.equals("")) {
        this.country = business.getCountry();
      }

      if (this.betterCottonMember == false) {
        this.betterCottonMember = business.isBetterCottonMember();
      }

      if (this.id == null) {
        this.id = business.getId();
      }

      if (this.bcorpCertified == false) {
        this.bcorpCertified = business.isBcorpCertified();
      }
      if (this.blackOwnedBusiness == false) {
        this.blackOwnedBusiness = business.isBlackOwnedBusiness();
      }
      if (this.bluesignPartner == false) {
        this.bluesignPartner = business.isBluesignPartner();
      }
      if (this.certifiedHumane == false) {
        this.certifiedHumane = business.isCertifiedHumane();
      }
      if (this.chooseCrueltyFreeCertified == false) {
        this.chooseCrueltyFreeCertified = business.isChooseCrueltyFreeCertified();
      }
      if (this.chooseCrueltyFreeVegan == false) {
        this.chooseCrueltyFreeVegan = business.isChooseCrueltyFreeVegan();
      }
      if (this.ethicalElephantCrueltyFree == false) {
        this.ethicalElephantCrueltyFree = business.isEthicalElephantCrueltyFree();
      }
      if (this.ethicalElephantTestsOnAnimals == false) {
        this.ethicalElephantTestsOnAnimals = business.isEthicalElephantTestsOnAnimals();
      }
      if (this.leapingBunnyCertified == false) {
        this.leapingBunnyCertified = business.isLeapingBunnyCertified();
      }
      if (this.nativeOwnedBusiness == false) {
        this.nativeOwnedBusiness = business.isNativeOwnedBusiness();
      }
      if (this.pocOwnedBusiness == false) {
        this.pocOwnedBusiness = business.isPocOwnedBusiness();
      }
      if (this.supportsBLM == false) {
        this.supportsBLM = business.isSupportsBLM();
      }
      if (this.veganDotOrgCertified == false) {
        this.veganDotOrgCertified = business.isVeganDotOrgCertified();
      }
      if (this.wildlifeFriendlyCertified == false) {
        this.wildlifeFriendlyCertified = business.isWildlifeFriendlyCertified();
      }
      if (this.animalsScore == 0) {
        this.animalsScore = business.getAnimalsScore();
      }
      if (this.bcorpCommunityScore == 0) {
        this.bcorpCommunityScore = business.getBcorpCommunityScore();
      }
      if (this.bcorpEnvironmentScore == 0) {
        this.bcorpEnvironmentScore = business.getBcorpEnvironmentScore();
      }
      if (this.bcorpGovernanceScore == 0) {
        this.bcorpGovernanceScore = business.getBcorpGovernanceScore();
      }
      if (this.bcorpScore == 0) {
        this.bcorpScore = business.getBcorpScore();
      }
      if (this.bcorpWorkerScore == 0) {
        this.bcorpWorkerScore = business.getBcorpWorkerScore();
      }
      if (this.corporateCriticScore == 0) {
        this.corporateCriticScore = business.getCorporateCriticScore();
      }
      if (this.environmentScore == 0) {
        this.environmentScore = business.getEnvironmentScore();
      }
      if (this.goodOnYouScore == 0) {
        this.goodOnYouScore = business.getGoodOnYouScore();
      }
      if (this.greenPowerPercentage == 0) {
        this.greenPowerPercentage = business.getGreenPowerPercentage();
      }
      if (this.laborScore == 0) {
        this.laborScore = business.getLaborScore();
      }
      if (this.overallScore == 0) {
        this.overallScore = business.getOverallScore();
      }
      if (this.socialScore == 0) {
        this.socialScore = business.getSocialScore();
      }
      if (this.textileScore == 0) {
        this.textileScore = business.getTextileScore();
      }
      if (this.bcorpCertYear == 0) {
        this.bcorpCertYear = business.getBcorpCertYear();
      }
      if (this.animalScoreSource[0].equals("")) {
        this.animalScoreSource = business.getAnimalScoreSource();
      }
      if (this.bcorpProfile.equals("")) {
        this.bcorpProfile = business.getBcorpProfile();
      }
      if (this.betterBusinessBureau.equals("")) {
        this.betterBusinessBureau = business.getBetterBusinessBureau();
      }
      if (this.companyType.equals("")) {
        this.companyType = business.getCompanyType();
      }
      if (this.environmentScoreSource[0].equals("")) {
        this.environmentScoreSource = business.getEnvironmentScoreSource();
      }
      if (this.ethicalElephantType.equals("")) {
        this.ethicalElephantType = business.getEthicalElephantType();
      }
      if (this.laborScoreSource[0].equals("")) {
        this.laborScoreSource = business.getLaborScoreSource();
      }
      if (this.name.equals("")) {
        this.name = business.getName();
      }
      if (this.overallScoreDetails.equals("")) {
        this.overallScoreDetails = business.getOverallScoreDetails();
      }
      if (this.socialScoreSource[0].equals("")) {
        this.socialScoreSource = business.getSocialScoreSource();
      }
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
      if (this.website.equals("")) {
        this.website = business.getWebsite();
      }
    }
  }

  private void calculateAnimalScore() {
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
    double veganHandicap = 2;
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
    int animalFactorIndex = 0;
    if (this.ethicalElephantType.equals("Vegan") || this.veganDotOrgCertified || this.chooseCrueltyFreeVegan) {
      animalFactorIndex += veganHandicap;
      for (int a = 0; a < veganHandicap; ++a) {
        cumulativeAnimalScore += scoreGuideAnimal[a];
      }
    }
    for (; animalFactorIndex < scoreGuideAnimal.length; ++animalFactorIndex) {
      if (ethicalElephantCrueltyFreeVeganSwitch
        && this.ethicalElephantCrueltyFree
        && this.ethicalElephantType.equals("Vegan")) {
        cumulativeAnimalScore +=
          scoreGuideAnimal[animalFactorIndex]
            - (rangeAnimal / 2)
            + (rangeAnimal * (ethicalElephantCrueltyFreeVeganWeight / animalWeightMean) / 2);
        ethicalElephantCrueltyFreeVeganSwitch = false;
        ++animalFactors;
      } else if (veganDotOrgCertifiedSwitch && this.veganDotOrgCertified) {
        cumulativeAnimalScore +=
          scoreGuideAnimal[animalFactorIndex]
            - (rangeAnimal / 2)
            + (rangeAnimal * (veganDotOrgCertifiedWeight / animalWeightMean) / 2);
        veganDotOrgCertifiedSwitch = false;
        ++animalFactors;
      } else if (chooseCrueltyFreeVeganSwitch
        && this.chooseCrueltyFreeCertified
        && this.chooseCrueltyFreeVegan) {
        cumulativeAnimalScore +=
          scoreGuideAnimal[animalFactorIndex]
            - (rangeAnimal / 2)
            + (rangeAnimal * (chooseCrueltyFreeVeganWeight / animalWeightMean) / 2);
        chooseCrueltyFreeVeganSwitch = false;
        ++animalFactors;
      } else if (ethicalElephantCrueltyFreeVeganOptionsSwitch
        && this.ethicalElephantCrueltyFree
        && this.ethicalElephantType.equals("Vegan Options")) {
        cumulativeAnimalScore +=
          scoreGuideAnimal[animalFactorIndex]
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
          scoreGuideAnimal[animalFactorIndex]
            - (rangeAnimal / 2)
            + (rangeAnimal * (ethicalElephantCrueltyFreeWeight / animalWeightMean) / 2);
        ethicalElephantCrueltyFreeSwitch = false;
        ++animalFactors;
      } else if (leapingBunnySwitch && this.leapingBunnyCertified) {
        cumulativeAnimalScore +=
          scoreGuideAnimal[animalFactorIndex]
            - (rangeAnimal / 2)
            + (rangeAnimal * (leapingBunnyWeight / animalWeightMean) / 2);
        leapingBunnySwitch = false;
        ++animalFactors;
      } else if (certifiedHumaneSwitch && this.certifiedHumane) {
        cumulativeAnimalScore +=
          scoreGuideAnimal[animalFactorIndex]
            - (rangeAnimal / 2)
            + (rangeAnimal * (certifiedHumaneWeight / animalWeightMean) / 2);
        certifiedHumaneSwitch = false;
        ++animalFactors;
      } else if (chooseCrueltyFreeSwitch
        && this.chooseCrueltyFreeCertified
        && !this.chooseCrueltyFreeVegan) {
        cumulativeAnimalScore +=
          scoreGuideAnimal[animalFactorIndex]
            - (rangeAnimal / 2)
            + (rangeAnimal * (chooseCrueltyFreeWeight / animalWeightMean) / 2);
        chooseCrueltyFreeSwitch = false;
        ++animalFactors;
      } else {
        animalFactorIndex = scoreGuideAnimal.length;
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
    this.animalsScore = Math.min(cumulativeAnimalScore, 10);
  }

  private void calculateEnvironmentScore() {
    // Mean scores based on number of animal factors (0 - 10):
    final double BCORP_ENVIRORNMENT_MEAN = 17.8; // Mean of all bcorpWorkerScore data
    final double GREEN_POWER_MAX = 200;
    double oneFactorEnvironment = 6;
    double twoFactorEnvironment = 8;
    double threeFactorEnvironment = 9;
    double fourFactorEnvironment = 9.5;
    double fiveFactorEnvironment = 9.75;
    // Ranges determine the amount scores can fluctuate from their base score
    double environmentRange = 1;
    double bCorpRange = 4;
    double greenPowerRange = 3;
    // Weights for Environment Factors (1 - 10):
    double bCorpEnvWeight = 9;
    double bluesignWeight = 8;
    double greenPowerWeight = 7;
    double betterCottonWeight = 2;
    // Environment Factor Switches (Tells the Positive Factor loop if a factor has been used yet):
    boolean bluesignSwitch = true;
    boolean greenPowerSwitch = true;
    boolean bCorpEnvSwitch = true;
    boolean betterCottonSwitch = true;
    // Environment Calculation Variables:
    double numberOfFactorsEnvironment = 4;
    double environmentHandicap = 2;
    double[] scoreGuideEnvironment = {
      oneFactorEnvironment,
      twoFactorEnvironment - oneFactorEnvironment,
      threeFactorEnvironment - twoFactorEnvironment,
      fourFactorEnvironment - threeFactorEnvironment,
      fiveFactorEnvironment - fourFactorEnvironment
    };
    double cumulativeEnvironmentScore = 0;
    double environmentWeightMean =
      (bluesignWeight + greenPowerWeight + bCorpEnvWeight + betterCottonWeight)
        / numberOfFactorsEnvironment;
    // Environment - Positive Factors:
    int environmentFactorIndex = 0;
    if (this.bluesignPartner) {
      environmentFactorIndex += environmentHandicap;
      for (int a = 0; a < environmentHandicap; ++a) {
        cumulativeEnvironmentScore += scoreGuideEnvironment[a];
      }
    }
    for (; environmentFactorIndex < scoreGuideEnvironment.length; ++environmentFactorIndex) {
      if (bCorpEnvSwitch && this.bcorpEnvironmentScore != 0) {
        cumulativeEnvironmentScore +=
          scoreGuideEnvironment[environmentFactorIndex]
            - (environmentRange / 2)
            + (environmentRange * (bCorpEnvWeight / environmentWeightMean) / 2);
        cumulativeEnvironmentScore +=
            + (bCorpRange * (this.bcorpEnvironmentScore / BCORP_ENVIRORNMENT_MEAN) / 2);
        bCorpEnvSwitch = false;
      } else if (bluesignSwitch && this.bluesignPartner) {
        cumulativeEnvironmentScore +=
          scoreGuideEnvironment[environmentFactorIndex]
            - (environmentRange / 2)
            + (environmentRange * (bluesignWeight / environmentWeightMean) / 2);
        bluesignSwitch = false;
      } else if (greenPowerSwitch && this.greenPowerPercentage != 0) {
        cumulativeEnvironmentScore +=
          scoreGuideEnvironment[environmentFactorIndex]
            - (environmentRange / 2)
            + (environmentRange * (greenPowerWeight / environmentWeightMean) / 2);
        double greenPowerCapped = Math.min(this.greenPowerPercentage, GREEN_POWER_MAX) / 100;
        cumulativeEnvironmentScore +=
          - (greenPowerRange / 2) + 0.5
          + (greenPowerRange * greenPowerCapped / 2);
          greenPowerSwitch = false;
      } else if (betterCottonSwitch && this.betterCottonMember) {
        cumulativeEnvironmentScore +=
          scoreGuideEnvironment[environmentFactorIndex]
            - (environmentRange / 2)
            + (environmentRange * (betterCottonWeight / environmentWeightMean) / 2);
        betterCottonSwitch = false;
      } else {
        environmentFactorIndex = scoreGuideEnvironment.length;
      }
    }
    this.environmentScore = Math.min(cumulativeEnvironmentScore, 10);
  }

  private void calculateLaborScore() {
    // Labor Practice Score
    double laborImpactScore = 0;
    final double BCORP_LABOR_MEAN = 20.8;
    int laborImpactFactors = 0;
    if (this.bcorpWorkerScore != 0) {
      ++laborImpactFactors;
      // Turns bcorpWorkerScore into a 0 - 10 score in which an average score would earn a 7.5
      laborImpactScore += ((this.bcorpWorkerScore / BCORP_LABOR_MEAN) * 7.5);
    }
    if (laborImpactScore != 0) {
      this.laborScore = laborImpactScore / laborImpactFactors;
    }
    this.laborScore = Math.min(this.laborScore, 10);
  }

  private void calculateSocialScore() {
    double socialImpactScore = 0;
    final double BCORP_SOCIAL_MEAN = 30.5;
    int socialImpactFactors = 0;
    if (this.bcorpCommunityScore != 0) {
      ++socialImpactFactors;
      // Turns bcorpCommunityScore into a 0 - 10 score in which an average score would earn a 7.5
      socialImpactScore += ((this.bcorpCommunityScore / BCORP_SOCIAL_MEAN) * 7.5);
    }
    if (socialImpactScore != 0) {
      this.socialScore = socialImpactScore / socialImpactFactors;
    }
    this.socialScore = Math.min(this.socialScore, 10);
  }

  public void calculate() {
    if (this.overallScore == 0) {
      calculateAnimalScore();
      calculateEnvironmentScore();
      calculateSocialScore();
      calculateLaborScore();
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
      if (this.socialScore != 0) {
        ++overallImpactFactors;
        overallImpactScore += this.socialScore;
      }
      if (this.animalsScore != 0) {
        ++overallImpactFactors;
        overallImpactScore += this.animalsScore;
      }
      if (overallImpactScore != 0) {
        this.overallScore = overallImpactScore / overallImpactFactors;
      }
      this.overallScore = Math.min(this.overallScore, 10);
    }
  }
}
