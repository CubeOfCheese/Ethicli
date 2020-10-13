package com.example.appengine.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Collection Exists? " + mongoTemplate.collectionExists("advertisement"));
  }
}