package com.appengine.springboot.business;

import com.appengine.springboot.advertisement.Advertisement;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class BusinessController {

  @Autowired
  BusinessService businessService;

  @GetMapping("/score/{company}")
  public Business getBusinessByWebsite(@PathVariable("company") String companyName, HttpServletResponse response) throws IOException {
    return businessService.getBusinessByWebsite(companyName);
  }

  @GetMapping("/Business/getAll")
  public List<Business> getAll() {
    return businessService.getAll();
  }

  @PutMapping("/Business/insert")
  public Business insert(@RequestBody Business business) {
    return businessService.insert(business);
  }

  @PutMapping("/Business/insertAll")
  public List<Business> insertAll(@RequestBody List<Business> businesses) {
    return businessService.insertAll(businesses);
  }

  @PutMapping("/Business/update")
  public Business update(@RequestBody Business business) {
    return businessService.update(business);
  }

  @PutMapping("/Business/updateAll")
  public List<Business> updateAll(@RequestBody List<Business> businesses) {
    return businessService.updateAll(businesses);
  }
}
