package com.appengine.springboot;

import com.appengine.springboot.business.BusinessService;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Router {

  @RequestMapping(value = "/")
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

  @RequestMapping(value = "/about")
  public String getAbout() {
    return "about";
  }

  @RequestMapping(value = "/goodbye")
  public String getGoodbye() {
    return "uninstall";
  }

  @RequestMapping(value = "/amytools-ad")
  public String getAmyToolsAd() {
    return "amytools/ad";
  }

  @RequestMapping(value = "/countdown-2021")
  public String getCountdown2021() {
    return "events/countdown";
  }

  @RequestMapping(value = "/info/{company}")
  public String getInfo(@PathVariable("company") String companyName, Model model) throws IOException {
    model.addAttribute("business", BusinessService.getBusinessByWebsite(companyName));
    return "info";
  }
}
