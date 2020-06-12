package com.example.appengine.springboot;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface BadUrlRepository extends DatastoreRepository<BadUrl, String> {

}
