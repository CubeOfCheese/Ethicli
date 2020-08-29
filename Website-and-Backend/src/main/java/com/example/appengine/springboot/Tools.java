package com.example.appengine.springboot;

public class Tools {
    public static String [] csvToStringArray(String content) {
        String [] splitByDoubleComma;
        String output = "";
        boolean quoteDetected = false;
        if (content.contains("\"\""))
            content.replaceAll("\"\"", "@@DOUBLEQUOTES@@");
        splitByDoubleComma = content.split(",");
        for (int a = 0; a < splitByDoubleComma.length; ++a) {
            if (splitByDoubleComma[a].contains("\""))
                quoteDetected = !quoteDetected;
            if (quoteDetected) {
                output += splitByDoubleComma[a] + ",";
            } else {
                output += splitByDoubleComma[a] + "@@DELIMITER@@";
            }
        }
        return output
                .replaceAll("\"", "")
                .replaceAll("@@DOUBLEQUOTES@@", "\"")
                .split("@@DELIMITER@@");
    }
}
