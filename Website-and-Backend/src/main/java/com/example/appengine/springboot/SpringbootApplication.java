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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@RestController
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    // Controls Searches of all Data Sources
    @GetMapping("/score/{company}")
    public Business masterController(@PathVariable("company") String companyName, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Business business = new Business();
        if (validateURL(companyName)) {
            business.update(searchDataSource(companyName, "Corrections - Sheet1.csv", 2, 0, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1,  -1, -1,false, false, false, false, false, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "Top 50 Online Retailers & Manual Scores - Sheet1.csv", 12, 1, -1, 2, -1, -1, -1, -1, 3, 4, 5, -1, 6, 7, 8, 9, 10, 11, -1, -1, -1, -1, -1, -1,-1,  -1, -1,false, false, false, false, false, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "EPA's Green Power Partners - Sheet1.csv", 5, 0, -1, -1, -1, -1, -1, 1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1,  -1, -1,false, false, false, false, false, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "B Corp Impact Data.csv", 11, 0, 3, 4, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6, 7,8,9,10, 5,  -1, -1,false, false, false, false, false, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "Financial Contributions-Companies Supporting Black Lives.csv", 5, 0, -1, -1, 4, 1, 2, -1, -1, -1, -1, -1,-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1,  -1, -1,false, true, false, false, false, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "Black-OwnedOnlineBusinesses.csv", 3, 0, -1, 2, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1,  -1, -1,false, false, true, false, false, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "bluesign-reference-list.txt", 1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1,  -1, -1,true, false, false, false, false, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "POC-Owned Businesses (1).csv", 3, 0, -1, 2, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1,  -1, -1,false, false, false, true, false, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "Native-Owned Online Businesses.csv", 3, 0, -1, 2, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1,  -1, -1,false, false, false, false, true, false, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "Certified Vegan Retailers.csv", 3,0,-1, 1, -1, -1, -1, -1,  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  -1, -1,false, false, false, false, false,true, false, false, false, false, false, false));
            business.update(searchDataSource(companyName, "Cruelty-Free (Ethical Elephant Directory).csv", 5,0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,1, -1, false, false, false, false, false, false, true, false, false, false, false, false));
            business.update(searchDataSource(companyName, "Certified Humane.csv", 3, 0, -1, 1,  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, false, false, false, false, false, false, false, true, false, false, false, false));
            business.update(searchDataSource(companyName, "Certified Wildlife Friendly products.csv", 2, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, false, false, false, false, false, false, false, false, true, false, false, false));
            business.update(searchDataSource(companyName, "Choose Cruelty Free (CCF) Certified.csv", 5, 0, -1, 1,  -1, -1,  -1, 3,  -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2,  false, false, false, false, false, false, false, false, false, true, false, false));
            business.update(searchDataSource(companyName, "Companies that test on animals.csv", 2, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, false, false, false, false, false, false, false, false, false, false, true, false));
            business.update(searchDataSource(companyName, "Leaping Bunny Approved Brands.csv", 3, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, false, false, false, false, false, false, false, false, false, false, false, true));
            business.calculate();
        }
        return business;
    }

    // Compares companyName to Blocked URLS - Sheet.csv
    public boolean validateURL(String companyName) throws IOException {
        Resource resource = new ClassPathResource("Blocked URLS - Sheet1.csv");
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) { // cycles through line by line
                if (companyName.equals(line))
                    return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Removes all occurences of char target from String name
    public String charRemove(String name, char target) {
        char tempArray [] = name.toCharArray();
        String output = "";
        for (int a = 0;  a < name.length(); ++a) {
            if (tempArray[a] != target)
                output += tempArray[a];
        }
        return output;
    }

    // Compares searchTerm with business.website if it exists, if no match is found, it compares with business.name
    public boolean compare(String searchTerm, Business business) {
        searchTerm = searchTerm.toLowerCase();
        if (business.getWebsite() != null) {  // Compares searchTerm with business.website
            String website = business.getWebsite().toLowerCase();
            if (website.contains("http://")) {
                website = website.substring(7, website.length());
            } else if (website.contains("https://")) {
                website = website.substring(8, website.length());
            }
            if (website.contains("www.")) {
                website = website.substring(4, website.length());
            }
            if (website.length() > searchTerm.length()) {
                if (searchTerm.equals(website.substring(0, searchTerm.length()))
                        && website.charAt(searchTerm.length()) == '.') {
                    return true;
                }
            }
        }
        // Compare searchTerm with business.name - Checks matches of words in business.name individually and together
        String nameArray[] = prepareSearchTerm(business.getName().toLowerCase()).split(" ");
        String nameToken;
        for (int a = 0; a < nameArray.length; ++a) {
            nameToken ="";
            for (int b = a; b < nameArray.length; ++b) {
                nameToken += nameArray[b];
                if (searchTerm.equals(nameToken))
                    return true;
            }
        }
        return false;
    }

    // Removes unnecessary characters from searchTerm
    public String prepareSearchTerm(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        searchTerm = charRemove(searchTerm, ',');
        searchTerm = charRemove(searchTerm, '\'');
        searchTerm = charRemove(searchTerm, ':');
        searchTerm = charRemove(searchTerm, ';');
        searchTerm = charRemove(searchTerm, '.');
        searchTerm = charRemove(searchTerm, '-');
        searchTerm = charRemove(searchTerm, '_');
        searchTerm = charRemove(searchTerm, '&');
        return searchTerm;
    }

    // Searches Data Source: Columns of data source should be specified by order 0, 1, 2,.. If column is not present in Data Source write -1.
    public Business searchDataSource(String searchTerm, String filename, final int columnCount, final int nameColumn, int bcorpProfileColumn,
                                     int websiteColumn, int supportsBLMSourceColumn, int supportsBLMEntityColumn, int supportsBLMContributionColumn,
                                     int companyTypeColumn, int betterBusinessBureauColumn, int corporateCriticScoreColumn, int goodOnYouScoreColumn,
                                     int greenPowerPercentageColumn, int environmentScoreColumn, int textilesScoreColumn, int animalsScoreColumn,
                                     int laborScoreColumn, int socialScoreColumn, int overallScoreColumn, int bcorpCertifiedColumn, int bcorpScoreColumn,
                                     int bcorpCommunityScoreColumn, int bcorpEnvironmentsScoreColumn, int bcorpGovernanceScoreColumn, int bcorpWorkersScoreColumn,
                                     int bcorpCertYearColumn, int ethicalElephantTypeColumn, int chooseCrueltyFreeVeganColumn,
                                     boolean bluesignPartnerColumn, boolean supportsBLMColumn, boolean blackOwnedBusinessColumn,
                                     boolean pocOwnedBusiness, boolean nativeOwnedBusiness, boolean veganDotOrgCertified, boolean ethicalElephantCrueltyFree, boolean certifiedHumane,
                                     boolean wildlifeFriendlyCertified, boolean chooseCrueltyFreeCertified, boolean ethicalElephantTestsOnAnimals,
                                     boolean leapingBunnyCertified) throws IOException {
        Business busTemp = new Business(); // Business temp obj for search and compare
        Business business = new Business(); // Business obj for matching data of most recent .year
        Resource resource = new ClassPathResource(filename);
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        String dataToken = ""; // Data that is eventually sent to specified business values
        int column = 0; // Column index for cycling through data
        boolean doubleQuoteRecognizer = false; // To resolve .csv 'double quote when comma is present' issue
        String[] brLine;
        try {
            br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) { // cycles through line by line
                if (line.charAt(line.length() - 1) == ',') {
                    line += "0";
                }
                if (bluesignPartnerColumn){
                    brLine = line.split("\n");
                } else {
                    brLine = line.split(",");
                }
                for (int brCount = 0; brCount < brLine.length; ++brCount) { // cycles through line split by ","
                    if (!doubleQuoteRecognizer) {
                        if (brLine[brCount].contains("\"")) { // Start of double quote
                            doubleQuoteRecognizer = true;
                            dataToken = brLine[brCount];
                            --column;
                        } else {
                            dataToken = brLine[brCount];
                        }
                    } else {
                        dataToken = dataToken + "," + brLine[brCount]; // Merges double quote data
                        if (brLine[brCount].contains("\"")) { // End of qouble quote
                            doubleQuoteRecognizer = false;
                        } else{
                            --column;
                        }
                    }
                    if (!doubleQuoteRecognizer) { // Allows incomplete dataTokens to pass through unnassigned
                        if (!dataToken.isEmpty() && !dataToken.equals("ND")) {
                            if (column == nameColumn) {
                                if (bluesignPartnerColumn) {
                                    String nameTemp [] = dataToken.split(",");
                                    dataToken = nameTemp[0];
                                }
                                if (dataToken.contains("\"")) { // Removes leading and trailing "\""
                                    dataToken = dataToken.substring(1, dataToken.length() - 1);
                                }
                                busTemp.setName(dataToken);
                                busTemp.setBlackOwnedBusiness(blackOwnedBusinessColumn);
                                busTemp.setSupportsBLM(supportsBLMColumn);
                                busTemp.setBluesignPartner(bluesignPartnerColumn);
                                busTemp.setPocOwnedBusiness(pocOwnedBusiness);
                                busTemp.setNativeOwnedBusiness(nativeOwnedBusiness);
                                busTemp.setVeganDotOrgCertified(veganDotOrgCertified);
                                busTemp.setEthicalElephantCrueltyFree(ethicalElephantCrueltyFree);
                                busTemp.setCertifiedHumane(certifiedHumane);
                                busTemp.setWildlifeFriendlyCertified(wildlifeFriendlyCertified);
                                busTemp.setChooseCrueltyFreeCertified(chooseCrueltyFreeCertified);
                                busTemp.setEthicalElephantTestsOnAnimals(ethicalElephantTestsOnAnimals);
                                busTemp.setLeapingBunnyCertified(leapingBunnyCertified);
                            } else if (column == websiteColumn) {
                                busTemp.setWebsite(dataToken);
                            } else if (column == supportsBLMSourceColumn) {
                                busTemp.setSupportsBLMSource(dataToken);
                            } else if (column == supportsBLMEntityColumn) {
                                busTemp.setSupportsBLMEntity(charRemove(dataToken, '"'));
                            } else if (column == supportsBLMContributionColumn) {
                                busTemp.setSupportsBLMContribution(charRemove(dataToken, '"'));
                            } else if (column == companyTypeColumn) {
                                busTemp.setCompanyType(dataToken);
                            } else if (column == betterBusinessBureauColumn) {
                                busTemp.setBetterBusinessBureau(dataToken);
                            } else if (column == corporateCriticScoreColumn) {
                                try {
                                    busTemp.setCorporateCriticScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == goodOnYouScoreColumn) {
                                try {
                                    busTemp.setGoodOnYouScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == greenPowerPercentageColumn) {
                                dataToken = charRemove(dataToken, '%');
                                try {
                                    busTemp.setGreenPowerPercentage(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == environmentScoreColumn) {
                                try {
                                    busTemp.setEnvironmentScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == textilesScoreColumn) {
                                try {
                                    busTemp.setTextileScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == animalsScoreColumn) {
                                try {
                                    busTemp.setAnimalsScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == laborScoreColumn) {
                                try {
                                    busTemp.setLaborScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == socialScoreColumn) {
                                try {
                                    busTemp.setSocialScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == overallScoreColumn) {
                                try {
                                    busTemp.setOverallScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == bcorpCertifiedColumn) {
                                if (!dataToken.contains("de-certified"))
                                    busTemp.setBcorpCertified(true);
                            } else if (column == bcorpCommunityScoreColumn) {
                                try {
                                    busTemp.setBcorpCommunityScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == bcorpEnvironmentsScoreColumn) {
                                try {
                                    busTemp.setBcorpEnvironmentScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == bcorpGovernanceScoreColumn) {
                                try {
                                    busTemp.setBcorpGovernanceScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == bcorpWorkersScoreColumn) {

                                try {
                                    busTemp.setBcorpWorkerScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == bcorpScoreColumn) {
                                try {
                                    busTemp.setBcorpScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == bcorpCertYearColumn) {
                                try {
                                    busTemp.setBcorpCertYear(Integer.parseInt(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (column == bcorpProfileColumn) {
                                if (dataToken.contains("bcorporation.net")) {
                                    busTemp.setBcorpProfile(dataToken);
                                }
                            } else if (column == ethicalElephantTypeColumn) {
                                if (dataToken.isEmpty()) {
                                    busTemp.setEthicalElephantType("");
                                } else {
                                    busTemp.setEthicalElephantType(dataToken);
                                }
                            } else if (column == chooseCrueltyFreeVeganColumn) {
                                if (dataToken.contains("vegan"))
                                    busTemp.setChooseCrueltyFreeVegan(true);
                            }
                        }
                    }
                    ++column;
                    if (column == columnCount) { // End of Cycle indicator
                        column = 0; // Restarts Cycle
                        if (compare(searchTerm, busTemp)) {
                            business.update(busTemp);
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
}
