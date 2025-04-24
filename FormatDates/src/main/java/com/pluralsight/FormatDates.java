package com.pluralsight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDates {
    public static void main(String[] args) {
        String[] dateFormats={
                "MM/dd/yyyy",
                "yyyy-MM-dd",
                "MMMM d, yyyy",
                "EEEE, MMM d, yyyy  H:mm",
                "H:mm 'on' dd-MMM-yyyy"
        };
        DateTimeFormatter[] formatters={
                DateTimeFormatter.ofPattern(dateFormats[0]),
                DateTimeFormatter.ofPattern(dateFormats[1]),
                DateTimeFormatter.ofPattern(dateFormats[2]),
                DateTimeFormatter.ofPattern(dateFormats[3]),
                DateTimeFormatter.ofPattern(dateFormats[4])
        };
        LocalDateTime today=LocalDateTime.now();
        ZonedDateTime gmtTime=ZonedDateTime.now(ZoneId.of("GMT"));
        ZonedDateTime myTime= ZonedDateTime.now(ZoneId.of("America/Chicago"));
        System.out.println(today.format(formatters[0]));
        System.out.println(today.format(formatters[1]));
        System.out.println(today.format(formatters[2]));
        System.out.println(gmtTime.format(formatters[3]));
        System.out.println(myTime.format(formatters[4]));

    }
}
