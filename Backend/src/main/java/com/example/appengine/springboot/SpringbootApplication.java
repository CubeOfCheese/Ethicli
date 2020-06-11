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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
// @ComponentScan({"com.example.appengine.springboot"});
@RestController
public class SpringbootApplication {

    public static void main(String[] args) {
      // ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
      SpringApplication.run(SpringbootApplication.class, args);
    }
    @Autowired
    BadUrlRepository BadUrlRepository;

    @PostMapping("/feedback")
    public String storeBadUrls(@RequestBody BadUrl requestUrl) {
      BadUrlRepository.save(requestUrl);
      return requestUrl.toString();
    }

    // Controls Searches of all data Sources
    @GetMapping("/score/{company}")
    public Business masterController(@PathVariable("company") String companyName) throws IOException {
        Business business = new Business();
        business.update(isBlueSignPartner(companyName));
        business.update(searchBCorp(companyName));
        return business;
    }

    // Searches bluesign-reference-list.txt
    public Business isBlueSignPartner(String companyName) throws IOException {
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
                if (company[0].contains(companyName)) {
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
        Business business = new Business();
        business.setBluesignPartner(bluesignPartner);
        return business;
    }

    // Searches bcorp.csv
    public static Business searchBCorp(String searchTerm) throws IOException {
        // bcorp.csv collumn headers in order
        final int nameCollumn = 0;
        final int certifiedCollumn = 1;
        final int bCorpProfileCollumn = 2;
        final int websiteCollumn = 3;
        final int yearCollumn = 4;
        final int overallScoreCollumn = 5;
        final int collumnCount = 6;

        Business busTemp = new Business(); // Business temp obj for search and compare
        Business business = new Business(); // Business obj for matching data of most recent .year

        Resource resource = new ClassPathResource("bcorp.csv");
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        String dataToken = ""; // Data that is eventually sent to specified business values
        int collumn = 0; // bcorp.csv collumn index for cycling through data
        boolean doubleQuoteRecognizer = false; // To resolve .csv 'double quote when comma is present' issue
        try {
            br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) { // cycles through line by line
                String[] brLine = line.split(",");
                for (int brCount = 0; brCount < brLine.length; ++brCount) { // cycles through line split by ","
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
                            doubleQuoteRecognizer = false;
                        } else{
                            --collumn;
                        }
                    }
                    if (!doubleQuoteRecognizer) { // Allows incomplete dataTokens to pass through unnassigned
                        if (dataToken.contains("bcorporation.net/dir")) { // Ensures correct location for prolife data
                            collumn = bCorpProfileCollumn;
                        }
                        switch (collumn) { // Determines dataToken finds correct Business obj variable
                            case nameCollumn: // .setName
                                if (dataToken.contains("\"")) { // Removes leading and trailing "\""
                                    dataToken = dataToken.substring(1, dataToken.length() - 1);
                                }
                                if (dataToken.toLowerCase().contains("llc")) { // Removes " llc"
                                    dataToken = dataToken.substring(0, dataToken.length() - 4);
                                }
                                if (dataToken.toLowerCase().contains("inc")) {// Removes " inc."
                                    dataToken = dataToken.substring(0, dataToken.length() - 5);
                                }
                                busTemp.setName(dataToken);
                                break;
                            case certifiedCollumn: // .setCertified
                                if (!dataToken.contains("de-certified"))
                                    busTemp.setCertified(true);
                                break;
                            case bCorpProfileCollumn:  // .setBcorpProfile
                                if (!dataToken.contains("bcorporation.net/dir") ){
                                    --collumn; // allows profile data to catch up with correct collumn
                                } else {
                                    busTemp.setBcorpProfile(dataToken);
                                }
                                break;
                            case websiteCollumn: // .setWebsite
                                busTemp.setWebsite(dataToken);
                                break;
                            case yearCollumn: // .setYear
                                try {
                                    busTemp.setYear(Integer.parseInt(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case overallScoreCollumn: // .serOverallScore
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
                    if (collumn == collumnCount) { // End of Cycle indicator
                        collumn = 0; // Restarts Cycle
                        // compares searchTerm with name and website
                        if (busTemp.getName().toLowerCase().contains(searchTerm.toLowerCase()) || busTemp.getWebsite().toLowerCase().contains(searchTerm.toLowerCase()) )
                            if (busTemp.getYear() > business.getYear()) // only updates data if the .year is the most recent
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
}
