package com.appengine.springboot.business;

import com.appengine.springboot.advertisement.Advertisement;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/Business")
public class BusinessController {

  @Autowired
  BusinessService businessService;

  @GetMapping("/score/{company}")
  public Business getBusinessByWebsite(@PathVariable("company") String companyName, HttpServletResponse response) throws IOException {
    return businessService.getByWebsite(companyName);
  }

  @GetMapping("/regex/{company}")
  public List<Business> regexBusinessByWebsite(@PathVariable("company") String companyName, HttpServletResponse response) throws IOException {
    return businessService.websiteRegex(companyName);
  }

  @PutMapping("/insertAll")
  public List<Business> insertAllBusiness(@RequestBody List<Business> business) {
    return businessService.insertAll(business);
  }

  @PutMapping("/insert")
  public Business insertBusiness(@RequestBody Business business) {
    return businessService.insert(business);
  }

  @PutMapping("/updateAll")
  public List<Business> updateAllBusiness(@RequestBody List<Business> business) {
    return businessService.updateAll(business);
  }

  @PutMapping("/update")
  public Business updateBusiness(@RequestBody Business business) {
    return businessService.update(business);
  }


  @GetMapping("/getByWebsite/{website}")
  public Business getById(@PathVariable("website") String website) {
    return businessService.getByWebsite(website);
  }

  @GetMapping("/getAll")
  public List<Business> getAll() {
    return businessService.getAll();
  }

}
