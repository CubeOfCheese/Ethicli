package com.example.appengine.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RestController
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Autowired
    private BadUrlRepository BadUrlRepository;

    // Controls Feedback Service: If you need to change sender information (email/password)
    // make sure tochange it in resources/application.properties as well
    @PostMapping("/feedback")
    public String sendNotification(@RequestBody BadUrl url) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("team.ethicli@gmail.com");
        mail.setFrom("feedback.ethicli@gmail.com");
        mail.setSubject("Bad URL");
        mail.setText("Bad URL: " + url.toString());
        javaMailSender.send(mail);
        System.out.println("storeBadUrls triggered");
        BadUrlRepository.save(url);
        return url.toString();
    }
}
