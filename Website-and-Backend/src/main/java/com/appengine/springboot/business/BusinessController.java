package com.appengine.springboot.business;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BusinessController {

  @Autowired
  BusinessService businessService;

  @GetMapping("/score/{company}")
  public Business getBusinessByWebsite(@PathVariable("company") String companyName, HttpServletResponse response) throws IOException {
    response.addHeader("Access-Control-Allow-Origin", "*");
    return businessService.getBusinessByWebsite(companyName);
  }
}
