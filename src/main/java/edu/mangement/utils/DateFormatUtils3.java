package edu.mangement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/6/2020
 * TIME : 11:00 PM
 */
public class DateFormatUtils3 {
    public static SimpleDateFormat basicDateFormat = null;
    public static SimpleDateFormat getFormat(String pattern){
        return new SimpleDateFormat(pattern);
    }
    public static Date getDateBasicType(String dateStr){
        try {
            basicDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return basicDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date parseBasicType(Date date){
        try {
            basicDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return basicDateFormat.parse(String.valueOf(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
