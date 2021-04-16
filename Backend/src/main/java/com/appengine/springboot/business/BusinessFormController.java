package com.appengine.springboot.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/Business")
public class BusinessFormController {

  @Autowired BusinessService businessService;

  @PostMapping(path = "/add-desde-form", consumes = "application/x-www-form-urlencoded")
  public String addBusiness(Business business) {
    business.setManuallyScored(true);
    businessService.addBusiness(business);
    return "redirect:/amys-tools/business-adder";
  }
}
