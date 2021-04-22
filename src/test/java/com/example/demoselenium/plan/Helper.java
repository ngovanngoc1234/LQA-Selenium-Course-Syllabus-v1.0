package com.example.demoselenium.plan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    public static  void pause( long s) throws Exception {
        Thread.sleep(s);
    }

    public static void printCurrentTime() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dateTimeFormat.format(now));
    }
}
