package com.appengine.springboot.business;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends MongoRepository<Business, String> {

  Business getByWebsite(String website);
}
