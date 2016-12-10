package com.example.dllo.yohomix.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/**
 * Created by dllo on 16/12/8.
 */

public class TimeInstead {
    public static String timeString(String num) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd.yyyy");
//        Date date = new Date(Long.valueOf(num));
//        String time = simpleDateFormat.format(date);

        String time = new SimpleDateFormat("MM.dd.yyyy").format(Long.valueOf(num));
        return time;
    }
    public static String timeLong(Long num) {
        String time = new SimpleDateFormat("MM.dd.yyyy").format(num);
        return time;
    }
}
