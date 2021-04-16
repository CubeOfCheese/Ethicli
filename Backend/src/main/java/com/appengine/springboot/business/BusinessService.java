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

  @Autowired private MongoOperations mongoOperations;

  @Autowired private BusinessRepository businessRepository;

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
    if (business.getOverallScore() == 0) {  // All scores were previously set to 0 by default except for manual scores
      business.calculate();
      mongoOperations.updateFirst(
          new Query().addCriteria(Criteria.where("_id").is(business.getId())),
          new Update().set("overallScore", business.getOverallScore()),
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
