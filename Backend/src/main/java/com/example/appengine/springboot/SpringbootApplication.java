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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

@SpringBootApplication
@RestController
public class SpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }

  @GetMapping("/score/{company}")
  public boolean isBlueSignPartner(@PathVariable("company") String companyName) throws IOException, FileNotFoundException {
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
                if (company[0].equals(companyName)) {
                  return true;
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
        if (business.getName() != null && business.isCertified())
        	return true;
    return false;
  }

  public static Business searchBCorp(String searchTerm, int searchType) throws FileNotFoundException {
        final int nameCollumn = 0;
        final int certifiedCollumn = 3;
        final int bCorpProfileCollumn = 13;
        final int websiteCollumn = 14;
        final int yearCollumn = 15;
        final int overallScoreCollumn = 16;
        final int collumnCount = 115;
        int collumn = 0;
        Business busTemp = new Business();
        Business business = new Business();

        Scanner scan = new Scanner(new File("src/main/resources/bcorp.txt"));
        scan.useDelimiter(Pattern.compile(","));

        while (scan.hasNext()) {

            if (collumn == collumnCount - 1) {
                scan.useDelimiter(Pattern.compile("\n"));
            }
            String logicalLine = scan.next();
            if (logicalLine.contains("\"")) {
                while (logicalLine.charAt(logicalLine.length() - 1) != '\"') {
                    logicalLine = logicalLine + "," + scan.next();
                }
                logicalLine.replaceAll("\"", "");
            }
            if (logicalLine.contains("\n"))
                logicalLine.replaceAll(" \n", "");
            if (logicalLine.contains("bcorporation.net/dir") ){
                    collumn = bCorpProfileCollumn;
            }
            switch (collumn) {
                case nameCollumn:
                    if (scan.hasNext()){
                        while (logicalLine.charAt(0) == '\uFEFF' ||
                                logicalLine.charAt(0) == '\n' ||
                                logicalLine.charAt(0) == '"') {
                            logicalLine = logicalLine.substring(1);
                        }
                    } else {
                        return business;
                    }
                    if (logicalLine.charAt(logicalLine.length() - 1) == '"') {
                        logicalLine = logicalLine.substring(0, logicalLine.length() - 1);
                    }
                    busTemp.setName(logicalLine);
                    break;
                case certifiedCollumn:
                    if (!logicalLine.contains("de-certified"))
                        busTemp.setCertified(true);
                    break;
                case bCorpProfileCollumn:
                    while (!logicalLine.contains("bcorporation.net/dir") ){
                        logicalLine = scan.next();
                    }
                    busTemp.setBcorpProfile(logicalLine);
                    break;
                case websiteCollumn:
                    busTemp.setWebsite(logicalLine);
                    break;
                case yearCollumn:
                    try {
                        busTemp.setYear(Integer.parseInt(logicalLine));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case overallScoreCollumn:
                    try {
                        busTemp.setOverallScore(Double.parseDouble(logicalLine));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
            }
            if (collumn == collumnCount - 1) {
                scan.useDelimiter(Pattern.compile(","));
            }
            collumn++;
            if (collumn == collumnCount) {
                collumn = 0;
                switch (searchType){
                    case 0:
                        if (busTemp.getWebsite().contains(searchTerm.toLowerCase())) {

                            if (busTemp.getYear() > business.getYear()) {
                                business = new Business(busTemp.getName(), busTemp.isCertified(),
                                        busTemp.getBcorpProfile(), busTemp.getWebsite(), busTemp.getYear(),
                                        busTemp.getOverallScore());
                            }
                        }
                        break;
                    case 1:
                        if (busTemp.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                            if (busTemp.getYear() > business.getYear()) {
                                business = new Business(busTemp.getName(), busTemp.isCertified(),
                                        busTemp.getBcorpProfile(), busTemp.getWebsite(), busTemp.getYear(),
                                        busTemp.getOverallScore());
                            }
                        }
                        break;
                    default:
                }
                busTemp = new Business();
            }
        }
        return business;
    }

    public static Business buildSearch(String url, String metaTag) throws FileNotFoundException {
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

        if (metaTag.toLowerCase().contains("llc"))
            metaTag = metaTag.substring(0, metaTag.length() - 3);
        if (metaTag.toLowerCase().contains("inc."))
            metaTag = metaTag.substring(0, metaTag.length() - 4);
        bus = searchBCorp(metaTag, 1);
        if (bus.getName() != null)
            return bus;
        return bus;
    }
}
