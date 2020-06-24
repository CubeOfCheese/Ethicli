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

    // Controls Searches of all Data Sources
    @GetMapping("/score/{company}")
    public Business masterController(@PathVariable("company") String companyName) throws IOException {
        Business business = new Business();
        business.update(searchDataSource(companyName, "Top 50 Online Retailers & Manual Scores - Sheet1.csv", 11, 1, -1, -1, -1, -1, -1, -1, 2, 4, 5, 6, 7, 8, 9, 10, -1, -1, -1, false, false, false));
        business.update(searchDataSource(companyName, "bcorp.csv", 6, 0, 2, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 5, 4, false, false, false));
        business.update(searchDataSource(companyName, "Financial Contributions-Companies Supporting Black Lives.csv", 5, 0, -1, -1, 4, 1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, false, true, false));
        business.update(searchDataSource(companyName, "Black-Owned Businesses-Black-Owned Online Businesses.csv", 3, 0, -1, 2, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, false, false, true));
        business.update(searchDataSource(companyName, "bluesign-reference-list.txt", 1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, true, false, false));
        return business;
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

    // Compares terms with and without unnecessary characters
    public boolean compare(String searchTerm, String dataTerm) {
        if (dataTerm.toLowerCase().contains(searchTerm.toLowerCase()))
            return true;
        if (prepareSearchTerm(dataTerm).toLowerCase().contains(prepareSearchTerm(searchTerm).toLowerCase()))
            return true;
        return false;
    }

    // Removes unnecessary characters from searchTerm
    public String prepareSearchTerm(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        searchTerm = charRemove(searchTerm, ',');
        searchTerm = charRemove(searchTerm, ':');
        searchTerm = charRemove(searchTerm, ';');
        searchTerm = charRemove(searchTerm, '.');
        searchTerm = charRemove(searchTerm, '-');
        searchTerm = charRemove(searchTerm, '_');
        searchTerm = charRemove(searchTerm, ' ');
        return searchTerm;
    }

    // Searches Data Source: Collumns of data source should be specified by order 0, 1, 2,.. If collumn is not present in Data Source write -1.
    public Business searchDataSource(String searchTerm, String filename, final int collumnCount, final int nameCollumn, int bcorpProfileCollumn,
                                            int websiteCollumn, int supportsBLMSourceCollumn, int supportsBLMEntityCollumn, int supportsBLMContributionCollumn,
                                            int companyTypeCollumn, int betterBusinessBureauCollumn, int corporateCriticScoreCollumn, int goodOnYouScoreCollumn,
                                            int environmentScoreCollumn, int textilesScoreCollumn, int animalsScoreCollumn, int laborScoreCollumn,
                                            int averageScoreCollumn, int bcorpCertifiedCollumn, int bcorpScoreCollumn, int bcorpCertYearCollumn,
                                            boolean bluesignPartnerCollumn, boolean supportsBLMCollumn, boolean blackOwnedBusinessCollumn) throws IOException {
        Business busTemp = new Business(); // Business temp obj for search and compare
        Business business = new Business(); // Business obj for matching data of most recent .year
        Resource resource = new ClassPathResource(filename);
        InputStream file = resource.getInputStream();
        BufferedReader br = null;
        String line = "";
        String dataToken = ""; // Data that is eventually sent to specified business values
        int collumn = 0; // Collumn index for cycling through data
        boolean doubleQuoteRecognizer = false; // To resolve .csv 'double quote when comma is present' issue
        String[] brLine;
        try {
            br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) { // cycles through line by line
                if (bluesignPartnerCollumn){
                    brLine = line.split("\n");
                } else {
                    brLine = line.split(",");
                }
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
                        if (!dataToken.isEmpty()) {
                            if (collumn == nameCollumn) {
                                if (bluesignPartnerCollumn) {
                                    String nameTemp [] = dataToken.split(",");
                                    dataToken = nameTemp[0];
                                }
                                if (dataToken.contains("\"")) { // Removes leading and trailing "\""
                                    dataToken = dataToken.substring(1, dataToken.length() - 1);
                                }
                                busTemp.setName(dataToken);
                                busTemp.setBlackOwnedBusiness(blackOwnedBusinessCollumn);
                                busTemp.setSupportsBLM(supportsBLMCollumn);
                                busTemp.setBluesignPartner(bluesignPartnerCollumn);
                            } else if (collumn == bcorpProfileCollumn) {
                                busTemp.setBcorpProfile(dataToken);
                            } else if (collumn == websiteCollumn) {
                                busTemp.setWebsite(dataToken);
                            } else if (collumn == supportsBLMSourceCollumn) {
                                busTemp.setSupportsBLMSource(dataToken);
                            } else if (collumn == supportsBLMEntityCollumn) {
                                busTemp.setSupportsBLMEntity(dataToken);
                            } else if (collumn == supportsBLMContributionCollumn) {
                                busTemp.setSupportsBLMContribution(dataToken);
                            } else if (collumn == companyTypeCollumn) {
                                busTemp.setCompanyType(dataToken);
                            } else if (collumn == betterBusinessBureauCollumn) {
                                busTemp.setBetterBusinessBureau(dataToken);
                            } else if (collumn == corporateCriticScoreCollumn) {
                                try {
                                    busTemp.setCorporateCriticScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (collumn == goodOnYouScoreCollumn) {
                                try {
                                    busTemp.setGoodOnYouScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (collumn == environmentScoreCollumn) {
                                try {
                                    busTemp.setEnvironmentScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (collumn == textilesScoreCollumn) {
                                try {
                                    busTemp.setTextilesScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (collumn == animalsScoreCollumn) {
                                try {
                                    busTemp.setAnimalsScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (collumn == laborScoreCollumn) {
                                try {
                                    busTemp.setLaborScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (collumn == averageScoreCollumn) {
                                try {
                                    busTemp.setAverageScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (collumn == bcorpCertifiedCollumn) {
                                if (!dataToken.contains("de-certified"))
                                    busTemp.setBcorpCertified(true);
                            } else if (collumn == bcorpScoreCollumn) {
                                try {
                                    busTemp.setBcorpScore(Double.parseDouble(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            } else if (collumn == bcorpCertYearCollumn) {
                                try {
                                    busTemp.setBcorpCertYear(Integer.parseInt(dataToken));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    ++collumn;
                    if (collumn == collumnCount) { // End of Cycle indicator
                        collumn = 0; // Restarts Cycle
                        if (busTemp.getWebsite() != null) {
                            if (compare(searchTerm, busTemp.getWebsite())) {
                                business = busTemp;
                            }
                        }
                        if (busTemp.getName() != null) {
                            if (compare(searchTerm, busTemp.getName())) {
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
}
