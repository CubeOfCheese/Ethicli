package com.appengine.springboot.advertisement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
    String[] productDescriptions = prepareForProductTagQuery(payload.get("productName").toString());
    List<Advertisement> masterAdvertisements = null;
    HashMap<String, Double> adMap = new HashMap<String, Double>();
    double currentCompanyScore = ((Number) payload.get("currentCompanyScore")).doubleValue();
    for (String description : productDescriptions) {
      if (masterAdvertisements == null) {
        masterAdvertisements = regexProductTag(description);
      } else {
        masterAdvertisements.addAll(regexProductTag(description));
      }
    }
    for (String productDescription : productDescriptions) {
      for (Advertisement masterAdvertisement : masterAdvertisements) {
        if (masterAdvertisement.getCompanyScore() <= currentCompanyScore) {
          continue;
        }
        double weight = 0;
        for (int posPTIndex = 0; posPTIndex < masterAdvertisement.getProductTags().length; ++posPTIndex) {
          ProductTag positiveProductTag = masterAdvertisement.getProductTags()[posPTIndex];
          if (positiveProductTag.getTag().contains(productDescription)) {
            weight += compareToProductTag(positiveProductTag, productDescription);
          }
        }
        for (int negPtIndex = 0; masterAdvertisement.getNegativeProductTags() != null && negPtIndex < masterAdvertisement.getNegativeProductTags().length;
            ++negPtIndex) {
          ProductTag negativeProductTag = masterAdvertisement.getNegativeProductTags()[negPtIndex];
          if (negativeProductTag.getTag().contains(productDescription)) {
            weight += compareToProductTag(negativeProductTag, productDescription);
          }
        }
        if (weight != 0) {
          if (adMap.containsKey(masterAdvertisement.getId())) {
            adMap.put(masterAdvertisement.getId(), weight + adMap.get(masterAdvertisement.getId()));
          } else {
            adMap.put(masterAdvertisement.getId(), weight);
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

  public Advertisement addAdvertisement(Advertisement advertisement) {
    for (int a = 0; a < advertisement.getProductTags().length; ++a) {
      advertisement.getProductTags()[a].setTag(advertisement.getProductTags()[a].getTag().toLowerCase());
    }
    return advertisementRepository.insert(advertisement);
  }

  public Advertisement update(Advertisement advertisement) {
    return advertisementRepository.save(advertisement);
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
    Query query = new Query().addCriteria(Criteria.where("productTags.tag").regex(tag, "i"));
    return mongoOperations.find(query, Advertisement.class);
  }

  public List<Advertisement> addAllAdvertisements(List<Advertisement> advertisements) {
    for (Advertisement advertisement : advertisements) {
      if (advertisement.getProductTags() == null) {
        continue;
      }
      for (int b = 0; b < advertisement.getProductTags().length; ++b) {
        advertisement.getProductTags()[b].setTag(advertisement.getProductTags()[b].getTag().toLowerCase());
      }
      if (advertisement.getNegativeProductTags() == null) {
        continue;
      }
      for (int b = 0; b < advertisement.getNegativeProductTags().length; ++b) {
        advertisement.getNegativeProductTags()[b].setTag(advertisement.getNegativeProductTags()[b].getTag().toLowerCase());
        advertisement.getNegativeProductTags()[b].setWeight(-1 * Math.abs(advertisement.getNegativeProductTags()[b].getWeight()));
      }
    }
    return advertisementRepository.insert(advertisements);
  }

	public static String[] prepareForProductTagQuery(String input) throws IOException {
		String[] inputToArray = input.toLowerCase().split(" ");
		String inputCleaned = "";
		for (int a = 0; a < inputToArray.length; ++a) {
			if (inputToArray[a].length() > 2 && !isCommonWord(inputToArray[a])) {
				inputCleaned += removePunctuation(inputToArray[a]) + " ";
			}
		}
		return inputCleaned.split(" ");
	}

	public static String removePunctuation(String string) {
		return string.replaceAll("[^a-zA-Z0-9]", "");
	}

	public static boolean isCommonWord(String word) throws IOException {
		Resource resource = new ClassPathResource("Common Words");
		InputStream file = resource.getInputStream();
		BufferedReader br = null;
		String commonWord = "";
		try {
			br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
			while ((commonWord = br.readLine()) != null) {
				if (commonWord.equals(word)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static double compareToProductTag(ProductTag productTag, String descriptor) {
		double sizeDifference = productTag.getTag().length() - descriptor.length();
		if (sizeDifference <= 1
			|| (sizeDifference == 2 && productTag.getTag().substring(productTag.getTag().length() - 2, productTag.getTag().length()).equals("es"))) {
			return productTag.getWeight();
		} else {
			return productTag.getWeight() / sizeDifference;
		}
	}
}
