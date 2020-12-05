package com.appengine.springboot.advertisement;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.appengine.springboot.advertisement.Advertisement;

@Controller
@RequestMapping("/Advertisement")
public class AdvertisementControllerFake {

  @PostMapping(path = "/adder", consumes = "application/x-www-form-urlencoded")
  public String addAdvertisement(Advertisement Advertisement) {
    System.out.println("/add called");
    System.out.println(Advertisement.getCompanyName());
    System.out.println(Advertisement);
    return "redirect:/amystools-ad";
  }
}
