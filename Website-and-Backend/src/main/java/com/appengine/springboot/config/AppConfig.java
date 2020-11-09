package com.appengine.springboot.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

  public MongoClient mongoClient() {
    return MongoClients.create(
        "mongodb+srv://user:l4FOpDHMTrkMSKXj@cluster0.krjxc.gcp.mongodb.net/Ethicli?retryWrites=true&w=majority");
  }

  public @Bean
  MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), "Ethicli");
  }
}
