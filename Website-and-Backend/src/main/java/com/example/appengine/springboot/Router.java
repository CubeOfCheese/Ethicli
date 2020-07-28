package com.example.appengine.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class Router {
  @RequestMapping({"/", "/home"})
  public String getHome() {
    return "index";
  }

  @RequestMapping(value = "/faq")
  public String getFAQ() {
    return "faq";
  }

  @RequestMapping(value = "/privacy")
  public String getPrivacy() {
    return "privacy";
  }

  @RequestMapping(value = "/info/{company}")
  public String getInfo(@PathVariable("company") String companyName, Model model) {
    System.out.println(companyName);
    model.addAttribute("companyName", companyName);
    return "info";
  }
}
