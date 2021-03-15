package com.example.ihelp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static String convertToString(Long date){
        String convertedDate = "";
        Date d = new Date(date);
        SimpleDateFormat spdf = new SimpleDateFormat("MM/dd/YYYY");
        convertedDate = spdf.format(d);
        return convertedDate;
    }
}
