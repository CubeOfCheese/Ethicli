package com.example.appengine.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
