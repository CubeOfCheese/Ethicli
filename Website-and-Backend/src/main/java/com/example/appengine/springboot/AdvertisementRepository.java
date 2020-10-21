package com.example.appengine.springboot;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdvertisementRepository extends MongoRepository<Advertisement, String> {

  Optional<Advertisement> findById(String id);

  List<Advertisement> findByProductTags(String productTags);
}
