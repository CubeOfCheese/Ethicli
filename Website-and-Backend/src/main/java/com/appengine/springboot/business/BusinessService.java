package com.appengine.springboot.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
    if (validateURL(companyName)) {
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

	// Compares companyName to Blocked URLS - Sheet.csv
	public static boolean validateURL(String companyName) throws IOException {
		Resource resource = new ClassPathResource("Blocked URLS - Sheet1.csv");
		InputStream file = resource.getInputStream();
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
			while ((line = br.readLine()) != null) { // cycles through line by line
				if (companyName.equals(line)) {
					return false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
