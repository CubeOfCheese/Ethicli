package com.appengine.springboot.advertisement;

import com.appengine.springboot.Tools;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

  @Autowired
  AdvertisementRepository advertisementRepository;
  @Autowired
  private MongoOperations mongoOperations;

  public Advertisement getAdvertisementByProductTags(Map<String, Object> payload) throws IOException {
    final double WEIGHT_THRESHOLD = 0.75;
    HashMap<String, Double> adMap = new HashMap<String, Double>();
    String[] names = Tools.prepareForProductTagQuery(payload.get("name").toString());
    for (int namesIndex = 0; namesIndex < names.length; ++namesIndex) {
      List<Advertisement> advertisements = regexProductTag(names[namesIndex]);
      for (int advertisementsIndex = 0; advertisementsIndex < advertisements.size(); ++advertisementsIndex) {
        for (int productTagsIndex = 0; productTagsIndex < advertisements.get(advertisementsIndex).getProductTags().length; ++productTagsIndex) {
          if (advertisements.get(advertisementsIndex).getProductTags()[productTagsIndex].getTag().contains(names[namesIndex])) {
            if (adMap.containsKey(advertisements.get(advertisementsIndex).getId())) {
              adMap.put(
                  advertisements.get(advertisementsIndex).getId(),
                  advertisements.get(advertisementsIndex).getProductTags()[productTagsIndex].getWeight()
                      + adMap.get(advertisements.get(advertisementsIndex).getId()).doubleValue());
            } else {
              adMap.put(
                  advertisements.get(advertisementsIndex).getId(),
                  advertisements.get(advertisementsIndex).getProductTags()[productTagsIndex].getWeight());
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
