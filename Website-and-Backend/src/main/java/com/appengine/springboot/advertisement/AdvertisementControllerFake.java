package com.appengine.springboot.advertisement;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.appengine.springboot.advertisement.Advertisement;
import com.appengine.springboot.advertisement.AdvertisementService;


@CrossOrigin
@Controller
@RequestMapping("/Advertisement")
public class AdvertisementControllerFake {

  @Autowired
  AdvertisementService advertisementService;

  @PostMapping(path = "/adder", consumes = "application/x-www-form-urlencoded")
  public String addAdvertisement(Advertisement Advertisement) {
    advertisementService.addAdvertisement(Advertisement);
    return "redirect:/amys-tools-ad";
  }
}
