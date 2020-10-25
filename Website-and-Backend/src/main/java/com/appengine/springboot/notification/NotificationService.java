package com.appengine.springboot.notification;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
public class NotificationService {

  private final JavaMailSender javaMailSender;

  @Autowired
  public NotificationService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  // Controls Feedback Service: If you need to change sender information (email/password)
  // make sure tochange it in resources/application.properties as well
  @PostMapping("/feedback")
  public String sendNotification(@RequestBody Feedback message, HttpServletResponse response)
      throws MailException {
    response.addHeader("Access-Control-Allow-Origin", "*");
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo("team.ethicli@gmail.com");
    mail.setFrom("feedback.ethicli@gmail.com");
    mail.setSubject(message.getMessageType() + " Feedback");
    mail.setText(message.toString());
    javaMailSender.send(mail);
    return message.toString();
  }
}
