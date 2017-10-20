package com.example.admin.pollycodechallenge.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Functions {
    public static String DateConversionToSorHorMorDate(String s){
        DateFormat format = new SimpleDateFormat("E MMMM dd HH:mm:ss Z yyyy", Locale.US);
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = Calendar.getInstance().getTime();
        long diff = now.getTime() - date.getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if(days > 0)
            return new SimpleDateFormat("dd MMMM").format(date);
        else if(hours > 0)
            return hours + " h";
        else if(minutes > 0)
            return minutes + " min";
        else if(seconds > 0)
            return seconds + " s";
        else
            return "now";
    }
}
