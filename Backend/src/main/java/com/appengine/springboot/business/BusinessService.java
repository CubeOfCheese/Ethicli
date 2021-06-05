package com.appengine.springboot.business;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

  @Autowired
  private MongoOperations mongoOperations;

  @Autowired
  private BusinessRepository businessRepository;

  public List<Business> regexWebsite(String website) {
    Query query = new Query().addCriteria(Criteria.where("website").regex(website, "i"));
    return mongoOperations.find(query, Business.class);
  }

  public Business getBusinessByWebsite(String companyName) throws IOException {
    Business business = new Business();
    List<Business> businessList = regexWebsite(companyName);
    for (Business value : businessList) {
      if (companyName.equals(value.getWebsite().split("\\.")[0])) {
        business.update(value);
        break;
      }
    }
    // All scores were previously set to 0 by default except for manual scores
    if (business.getOverallScore() == 0
      || (business.getEnvironmentScore() == 0 && business.getLaborScore() == 0 && business.getSocialScore() == 0 && business.getAnimalsScore() == 0)) {
      business.calculate();

      Update updatedFields = new Update();
      updatedFields.set("overallScore", business.getOverallScore());
      updatedFields.set("laborScore", business.getLaborScore());
      updatedFields.set("socialScore", business.getSocialScore());
      updatedFields.set("environmentScore", business.getEnvironmentScore());
      updatedFields.set("animalsScore", business.getAnimalsScore());
      mongoOperations.updateFirst(
          new Query().addCriteria(Criteria.where("_id").is(business.getId())),
          updatedFields,
          Business.class);
    }
    return business;
  }

  public Business addBusiness(Business business) {
    if (business.getOverallScore() == 0) {
      business.calculate();
    }
    return businessRepository.insert(business);
  }
}
