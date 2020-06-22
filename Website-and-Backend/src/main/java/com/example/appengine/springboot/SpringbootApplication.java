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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@RestController
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    // Controls Searches of all data Sources
    @GetMapping("/score/{company}")
    public Business masterController(@PathVariable("company") String companyName) throws IOException {
        final int searchWordMax = 3;
        companyName = prepareSearchTerm(companyName);
        String companyNameArray [] = companyName.split("_");
        String searchWordsArray [] =
                new String[((companyNameArray.length < searchWordMax) ? companyNameArray.length : searchWordMax)];
        for (int a = 0; a < searchWordsArray.length; ++a) {
            searchWordsArray[a] = companyNameArray[a];
        }
        Business business = new Business();
        for (int a = searchWordsArray.length; a > 0; --a) {
            String searchWord = searchWordsArray[0];
            for (int b = 1; b < a; ++b) {
                searchWord += " " + searchWordsArray[b];
            }
            business.update(searchBCorp(searchWord));
            business.update(isBlueSignPartner(searchWord));
            business.update(searchSupportsBLM(searchWord));
            business.update(searchBlackOwnedBusiness(searchWord));
            if (business.getName() != null) {
                a = 0;
            }
        }
        return business;
    }

    // Searches bluesign-reference-list.txt
    public Business isBlueSignPartner(String companyName) throws IOException {
        boolean bluesignPartner = false;
        String name = "";
        Resource resource = new ClassPathResource("bluesign-reference-list.txt");
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        try {
            br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                line = prepareSearchTerm(line);
                String[] company = line.split(csvSplitBy);
                if (company[0].toLowerCase().contains(companyName)) {
                    bluesignPartner = true;
                    name = company[0];
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
        if (name != "")
            business.setName(name);
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
                                    busTemp.setBcorpCertYear(Integer.parseInt(dataToken));
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
                        if ( compareTerms(searchTerm, busTemp.getName().toLowerCase())) {
                            if (busTemp.getBcorpCertYear() > business.getBcorpCertYear()) { // only updates data if the .year is the most recent
                                business = busTemp;
                            }
                        }
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

    public String charRemove(String name, char target) {
        char tempArray [] = name.toCharArray();
        String output = "";
        for (int a = 0;  a < name.length(); ++a) {
            if (tempArray[a] != target)
                output += tempArray[a];
        }
        return output;
    }

    public String prepareSearchTerm(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        searchTerm = charRemove(searchTerm, ',');
        searchTerm = charRemove(searchTerm, ':');
        searchTerm = charRemove(searchTerm, ';');
        searchTerm = charRemove(searchTerm, '.');

        String output = "";
        String flaggedWords[] = {"llc", "limited", "ltd", "inc", "the",  "&", "and",
                "co", "company", "co-op", "of", "is", "a", "an", "from"};
        String tempArray[] = searchTerm.split("_");
        boolean flagged = false;
        for (int a = 0; a < tempArray.length; ++a) {

            for (int b = 0; b < flaggedWords.length; ++b) {
                if (tempArray[a].equals(flaggedWords[b])) {
                    flagged = true;
                    System.out.println("test");
                }
            }
            if (flagged == false) {
                output += tempArray[a] + "_";
            }
            flagged = false;
        }
        output = output.substring(0, output.length() - 1);
        return output;
    }

    // Searches Financial Contributions-Companies Supporting Black Lives.csv
    public static Business searchSupportsBLM(String searchTerm) throws IOException {
        // Financial Contributions-Companies Supporting Black Lives.csv collumn headers in order
        final int nameCollumn = 0;
        final int entityCollumn = 1;
        final int contributionCollumn = 2;
        final int dateAccessedCollumn = 3;
        final int sourceCollumn = 4;
        final int collumnCount = 5;

        Business busTemp = new Business(); // Business temp obj for search and compare
        Business business = new Business(); // Business obj for matching data of most recent .year

        Resource resource = new ClassPathResource("Financial Contributions-Companies Supporting Black Lives.csv");
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        String dataToken = ""; // Data that is eventually sent to specified business values
        int collumn = 0; // Collumn index for cycling through data
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
                        switch (collumn) { // Determines dataToken finds correct Business obj variable
                            case nameCollumn:
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
                                busTemp.setSupportsBLM(true);
                                break;
                            case entityCollumn:
                                busTemp.setSupportsBLMEntity(dataToken);
                                break;
                            case contributionCollumn:
                                busTemp.setSupportsBLMEntity(dataToken + " " + busTemp.getSupportsBLMEntity());
                                break;
                            case dateAccessedCollumn: // Currently unused data
                                break;
                            case sourceCollumn:
                                busTemp.setSupportsBLMSource(dataToken);
                                break;
                            default:
                        }
                    }
                    ++collumn;
                    if (collumn == collumnCount) { // End of Cycle indicator
                        collumn = 0; // Restarts Cycle
                        // compares searchTerm with name and website
                        if ( compareTerms(searchTerm, busTemp.getName().toLowerCase())) {
                            business = busTemp;
                        }
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

    // Searches Financial Contributions-Companies Supporting Black Lives.csv
    public static Business searchBlackOwnedBusiness(String searchTerm) throws IOException {
        // Financial Contributions-Companies Supporting Black Lives.csv collumn headers in order
        final int nameCollumn = 0;
        final int companyTypeCollumn = 1;
        final int websiteCollumn = 2;
        final int collumnCount = 3;

        Business busTemp = new Business(); // Business temp obj for search and compare
        Business business = new Business(); // Business obj for matching data of most recent .year

        Resource resource = new ClassPathResource("Black-Owned Businesses-Black-Owned Online Businesses.csv");
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        String dataToken = ""; // Data that is eventually sent to specified business values
        int collumn = 0; // Collumn index for cycling through data
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
                        switch (collumn) { // Determines dataToken finds correct Business obj variable
                            case nameCollumn:
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
                                busTemp.setBlackOwnedBusiness(true);
                                break;
                            case companyTypeCollumn:
                                busTemp.setCompanyType(dataToken);
                                break;
                            case websiteCollumn:
                                busTemp.setWebsite(dataToken);
                                break;
                            default:
                        }
                    }
                    ++collumn;
                    if (collumn == collumnCount) { // End of Cycle indicator
                        collumn = 0; // Restarts Cycle
                        // compares searchTerm with name and website
                        if ( compareTerms(searchTerm, busTemp.getName().toLowerCase())) {
                            business = busTemp;
                        }
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

    public static boolean compareTerms(String searchTerm, String dataTerm) {
        String searchTermArray [] = searchTerm.split("_");
        String dataTermArray [] = dataTerm.split("_");
        String searchToken = "";
        int searchDepth = 3;
        if (searchTerm.equals(dataTerm)) {
            return true;
        }
        for (int a = searchTermArray.length; a > 0; --a) {
            searchToken = searchTermArray[0];
            for (int b = 1; b < a; ++b) {
                searchToken += "_" + searchTermArray[b];
            }
            if (dataTerm.contains(searchToken)) {
                if (searchToken.length() > searchDepth) {
                    return true;
                } else {
                    for (int c = 0; c  < searchTermArray.length; ++c) {
                        for (int d = 0; d < dataTermArray.length; ++d) {
                            if (searchTermArray[c] == dataTermArray[d]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
