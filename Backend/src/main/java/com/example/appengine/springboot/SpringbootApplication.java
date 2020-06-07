/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appengine.springboot;

// [START gae_java11_helloworld]
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


@SpringBootApplication
@RestController
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    @GetMapping("/feedback/")
    public void storeBadUrls(@RequestParam(value = "url", defaultValue = "New tab") String requestUrl) {
      Entity referralEntity = new Entity("BadUrl");
      referralEntity.setProperty("url", requestUrl);
      datastore.put(referralEntity);
    }

    @GetMapping("/score/{company}")
    public Business isBlueSignPartner(@PathVariable("company") String companyName) throws IOException, FileNotFoundException {
        boolean bluesignPartner = false;
        Resource resource = new ClassPathResource("bluesign-reference-list.txt");
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        try {
            br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] company = line.split(csvSplitBy);
                if ((company[0].toLowerCase()).contains(companyName.toLowerCase())) {
                    bluesignPartner = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Business business = buildSearch(companyName, companyName);
        business.setBluesignPartner(bluesignPartner);
        if (business.getName() != null && business.isBcorpCertified()){
            return (business);
        }
        return business;
    }

    public static Business searchBCorp(String searchTerm, int searchType) throws IOException, FileNotFoundException {
        final int nameCollumn = 0;
        final int certifiedCollumn = 1;
        final int bCorpProfileCollumn = 2;
        final int websiteCollumn = 3;
        final int yearCollumn = 4;
        final int overallScoreCollumn = 5;
        final int collumnCount = 6;
        int collumn = 0;

        Business busTemp = new Business();
        Business business = new Business();

        Resource resource = new ClassPathResource("bcorp.csv");
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        String dataToken = "";
        boolean doubleQuoteRecognizer = false;
        try {
            br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                String[] brLine = line.split(",");
                for (int brCount = 0; brCount < brLine.length; ++brCount) {
                    if (!doubleQuoteRecognizer) {

                        if (brLine[brCount].contains("\"")) { // Start of double quote
                            doubleQuoteRecognizer = true;
                            dataToken = brLine[brCount];
                            --collumn;
                        } else {
                            dataToken = brLine[brCount];
                        }
                    } else {
                        dataToken = dataToken + "," + brLine[brCount]; // Merges double quote data
                        if (brLine[brCount].contains("\"")) { // End of qouble quote
                            if (brLine[brCount].contains("\"\"")){

                            } else {
                                doubleQuoteRecognizer = false;
                            }
                        } else{
                            --collumn;
                        }
                    }
                    if (!doubleQuoteRecognizer) {
                        if (dataToken.contains("bcorporation.net/dir") ){
                            collumn = bCorpProfileCollumn;
                        }
                        switch (collumn) {

                            case nameCollumn:
                                if (dataToken.contains("\"")){
                                    dataToken = dataToken.substring(1, dataToken.length() - 1);
                                }
                                if (dataToken.toLowerCase().contains("llc")) {
                                    dataToken = dataToken.substring(0, dataToken.length() - 4);
                                }
                                if (dataToken.toLowerCase().contains("inc")) {
                                    dataToken = dataToken.substring(0, dataToken.length() - 5);
                                }
                                busTemp.setName(dataToken);
                                break;
                            case certifiedCollumn:
                                if (!dataToken.contains("de-certified"))
                                    busTemp.setCertified(true);
                                break;
                            case bCorpProfileCollumn:
                                if (!dataToken.contains("bcorporation.net/dir") ){
                                    --collumn;
                                } else {
                                    busTemp.setBcorpProfile(dataToken);
                                }
                                break;
                            case websiteCollumn:
                                busTemp.setWebsite(dataToken);
                                break;
                            case yearCollumn:
                                try {
                                    busTemp.setYear(Integer.parseInt(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case overallScoreCollumn:
                                try {
                                    busTemp.setOverallScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                        }
                    }
                    ++collumn;
                    if (collumn == collumnCount) {
                        collumn = 0;
                        if (busTemp.getName().toLowerCase().contains(searchTerm.toLowerCase()) || busTemp.getWebsite().toLowerCase().contains(searchTerm.toLowerCase()) )
                            if (busTemp.getYear() > business.getYear())
                                business = busTemp;
                        busTemp = new Business();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return business;
    }

    public static Business buildSearch(String url, String metaTag) throws IOException, FileNotFoundException {
        Business bus = new Business();
        String searchTerm;
        if (url.contains("http"))
            url = url.substring(8);
        if (url.contains("www"))
            url = url.substring(4);
        for (int a = 0; a < url.length(); ++a)
            if (url.charAt(a) == '.')
                url = url.substring(0, a);
        bus = searchBCorp(url, 0);
        if (bus.getName() != null)
            return bus;
        return bus;
    }
}
