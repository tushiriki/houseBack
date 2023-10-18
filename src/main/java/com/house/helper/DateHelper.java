package com.house.helper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateHelper {
    private static String textConversion(Date date, String type) {
        String strDate = "";
        String format = "yyyy-MM-dd";

        if (type.equalsIgnoreCase("time")) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (date != null) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(format);
                strDate = dateFormat.format(date);
            } catch (Exception e) {
                System.out.println("can not be converted to text:" + e.getMessage());
            }

        }
        return strDate;
    }

    public static Date toDate(String date_as_string) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date convert = null;
        try {
            if (date_as_string != null && !date_as_string.equals("")) {
                convert = format.parse(date_as_string);
            }
   
        } catch (ParseException e) {
            System.out.println("Fail to convert to date: " + e.getMessage());
        }
        return convert;
    }







    public static String toText(Date date) {
        return textConversion(date, "");
    }


    public static Date now() {
        Date date = new Date();
        return date;
    }



}
