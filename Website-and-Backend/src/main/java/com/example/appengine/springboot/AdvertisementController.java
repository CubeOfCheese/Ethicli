package com.example.appengine.springboot;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Advertisement")
public class AdvertisementController {

  @Autowired AdvertisementService advertisementService;

  @GetMapping(value = "/getByProductTags")
  public Advertisement getAdvertisementByProductTags(@RequestBody Map<String, Object> payload)
      throws Exception {
    return advertisementService.getAdvertisementByProductTags(payload);
  }

  @GetMapping("/getAll")
  public List<Advertisement> getAll() {
    return advertisementService.getAll();
  }

  @GetMapping("/getById")
  public Optional<Advertisement> getById(@RequestParam("id") String id) {
    return advertisementService.getById(id);
  }

  @PutMapping("/add")
  public Advertisement addAdvertisement(@RequestBody Advertisement Advertisement) {
    return advertisementService.addAdvertisement(Advertisement);
  }

  @PutMapping("/addAll")
  public List<Advertisement> addAllAdvertisements(@RequestBody List<Advertisement> Advertisement) {
    return advertisementService.addAllAdvertisements(Advertisement);
  }

  @PutMapping("/update")
  public Advertisement updateAdvertisement(@RequestBody Advertisement Advertisement) {
    return advertisementService.update(Advertisement);
  }

  @DeleteMapping("/deleteById")
  public void delete(@RequestParam("id") String id) {
    advertisementService.delete(id);
  }

  @GetMapping("/tagRegex")
  public List<Advertisement> regexUser(@RequestParam(name = "tag") String tag) {
    return advertisementService.regexProductTag(tag);
  }
}
