package com.example.demo2;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LanguageLog implements Serializable {
    // This is for composing languages
    private final String text;
    private final String formattedDate;

    public LanguageLog(String text) {
        this.text = text;
        // we don't need to send in date and time because the date and time will be when the message is created.
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat); // This ties the string to a date format instead of just a string.
        formattedDate = dateFormat.format(date);
    }
}
