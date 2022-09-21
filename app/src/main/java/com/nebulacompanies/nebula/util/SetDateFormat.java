package com.nebulacompanies.nebula.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Palak Mehta on 28-Nov-17.
 */

public class SetDateFormat {

    //public static Date inDate;
    //public static String outDate;

    static SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
    //static SimpleDateFormat inputFormat1 = new SimpleDateFormat("MMM dd yyyy  mm:ssa", Locale.ENGLISH);
    static SimpleDateFormat inputFormat1 = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
    static SimpleDateFormat inputFormat2 = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
    static SimpleDateFormat inputFormat3 = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
    static SimpleDateFormat inputFormat4 = new SimpleDateFormat("yyyy-MM-dd");

    static SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    public static String SetDateFormat(String inputDate) {
        //inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        //outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date inDate;
        String outDate = "";

        try {
            inDate = inputFormat.parse(inputDate);
            outDate = outputFormat.format(inDate);
            System.out.println(outDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return outDate;
    }

    public static String SetDateFormat1(String inputDate) {
        //inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        //outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date inDate;
        String outDate = "";

        try {
            inDate = inputFormat1.parse(inputDate);
            outDate = outputFormat.format(inDate);
            System.out.println(outDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return outDate;
    }

    public static String SetDateFormat2(String inputDate) {
        //inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        //outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date inDate;
        String outDate = "";

        try {
            inDate = inputFormat2.parse(inputDate);
            outDate = outputFormat.format(inDate);
            System.out.println(outDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return outDate;
    }

    public static String SetDateFormat3(String inputDate) {
        //inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        //outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date inDate;
        String outDate = "";

        try {
            inDate = inputFormat3.parse(inputDate);
            outDate = outputFormat.format(inDate);
            System.out.println(outDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return outDate;
    }

    public static String GetEpoachTime(long epochtime) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String str = sdf.format(new Date(epochtime * 1000L));
        return str;
    }


   /* @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp * 1000);
    }*/

    /*public static long SetGmtTime(String dateString) {
        long convertedDate = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            convertedDate = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }
*/

    public static String SetGmtTime(long gmt_time) {
        String gmtDate;

        if(gmt_time == 0){
            gmtDate = "";
        }
        else {

            Date date = new Date(gmt_time * 1000);
            SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR, +5);
            cal.set(Calendar.MINUTE, +30);
            gmtDate = dateFormatGmt.format(cal.getTime());
            System.out.println("Current Date Time After adding 5 hours and 30 minutes : " + dateFormatGmt.format(cal.getTime()));
        }

        return gmtDate;
    }

    public static long getLongDate(String date) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = null;
        try {
            date1 = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long dateInLong = date1.getTime() / 1000;
        return dateInLong;
    }
}
