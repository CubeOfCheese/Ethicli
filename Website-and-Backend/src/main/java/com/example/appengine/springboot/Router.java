package com.example.appengine.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class Router {
  @RequestMapping(value = "/faq")
  public String getFAQ() {
    System.out.println("big faqs");
    return "classpath:resources/templates/faq";
  }
}
