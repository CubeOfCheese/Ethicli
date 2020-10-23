package com.example.appengine.springboot;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

  @Autowired private MongoOperations mongoOperations;

  public Advertisement getAdvertisementByProductTags(Map<String, Object> payload)
      throws IOException {
    final double WEIGHT_THRESHOLD = 0.75;
    HashMap<String, Double> adMap = new HashMap<String, Double>();
    String[] names = Tools.prepareForProductTagQuery(payload.get("name").toString());
    for (int a = 0; a < names.length; ++a) {
      List<Advertisement> advertisements = regexProductTag(names[a]);
      for (int b = 0; b < advertisements.size(); ++b) {
        for (int c = 0; c < advertisements.get(b).getProductTags().length; ++c) {
          if (advertisements.get(b).getProductTags()[c].getTag().contains(names[a])) {
            if (adMap.containsKey(advertisements.get(b).getId())) {
              adMap.put(
                  advertisements.get(b).getId(),
                  advertisements.get(b).getProductTags()[c].getWeight()
                      + adMap.get(advertisements.get(b).getId()).doubleValue());
            } else {
              adMap.put(
                  advertisements.get(b).getId(),
                  advertisements.get(b).getProductTags()[c].getWeight());
            }
          }
        }
      }
    }
    if (!adMap.isEmpty()) {
      double maxValueInMap = (Collections.max(adMap.values())); // This will return max value
      if (maxValueInMap > WEIGHT_THRESHOLD) {
        for (Entry<String, Double> entry : adMap.entrySet()) { // Itrate through hashmap
          if (entry.getValue() == maxValueInMap) {
            return getById(entry.getKey()).get();
          }
        }
      }
    }
    return new Advertisement();
  }

  @Autowired AdvertisementRepository advertisementRepository;

  public List<Advertisement> getAll() {
    return advertisementRepository.findAll();
  }

  public Advertisement addAdvertisement(Advertisement Advertisement) {
    return advertisementRepository.insert(Advertisement);
  }

  public Advertisement update(Advertisement Advertisement) {
    return advertisementRepository.save(Advertisement);
  }

  public void delete(String id) {
    advertisementRepository.deleteById(id);
  }

  public List<Advertisement> getAllByTags(String tags) {
    return advertisementRepository.findByProductTags(tags);
  }

  public Optional<Advertisement> getById(String id) {
    return advertisementRepository.findById(id);
  }

  public List<Advertisement> regexProductTag(String tag) {
    Query query = new Query();
    query.addCriteria(Criteria.where("productTags.tag").regex(tag));
    List<Advertisement> Advertisement = mongoOperations.find(query, Advertisement.class);
    return Advertisement;
  }

  public List<Advertisement> addAllAdvertisements(List<Advertisement> Advertisement) {
    return advertisementRepository.insert(Advertisement);
  }
}
