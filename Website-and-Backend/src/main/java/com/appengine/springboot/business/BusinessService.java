package com.appengine.springboot.business;

import com.appengine.springboot.Tools;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

  @Autowired
  private MongoOperations mongoOperations;

  public List<Business> regexWebsite(String website) {
    Query query = new Query().addCriteria(Criteria.where("website").regex(website, "i"));
    return mongoOperations.find(query, Business.class);
  }

  public Business getBusinessByWebsite(String companyName) throws IOException {
    Business business = new Business();
    if (Tools.validateURL(companyName)) {
      List<Business> businessList = regexWebsite(companyName);
      for (Business value : businessList) {
        if (companyName.equals(value.getWebsite().split("\\.")[0])) {
          business.update(value);
          break;
        }
      }
      business.calculate();
    }
    return business;
  }
}
