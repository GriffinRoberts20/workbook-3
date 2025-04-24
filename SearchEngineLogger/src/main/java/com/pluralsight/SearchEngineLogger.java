package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SearchEngineLogger {
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static String logFilePath = "src/main/resources/logs.txt";
    static String timeZone= "America/Chicago";

    public static void main(String[] args) {
        searchEngine();
    }

    //Logs time of launches, searches, exits, as well as the content of the searches
    public static void searchEngine() {
        //initialize variables
        String search;
        //log launch
        logAction("launch");
        System.out.println("Welcome to Stealing Your Data Search Engine");
        MyUtils.printDivider(30);
        //search engine running
        boolean searching = true;
        while (searching) {
            //get search query
            search = MyUtils.askQuestionGetString("Enter a search term (X to exit): ");
            //close and log if prompted to
            if (search.equalsIgnoreCase("x")) {
                System.out.println("Closing...\nThanks for the data!");
                logAction("exit");
                break;
            }
            //log search
            logAction("search : " + search);
        }
    }

    //gets local date time for given timezone, in the input format
    public static String getTime(DateTimeFormatter formatter) {
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of(timeZone));
        return time.format(formatter);
    }

    //writes input to log with timestamp
    public static void logAction(String action) {
        try {
            //initialize writer
            FileWriter fw = new FileWriter(logFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            //write action with timestamp to log
            bufferedWriter.write(getTime(dateTimeFormatter) + " " + action + "\n");
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
