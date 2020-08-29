package com.example.appengine.springboot;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface AdvertisementRepo extends DatastoreRepository<Advertisement, String> {

}