package com.gifary.commontemplate.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gifary on 6/14/18.
 */

public class DateUtils {
    private static DateUtils instance = null;

    public DateUtils() {
    }

    public static DateUtils getInstance(){
        if(instance==null){
            instance = new DateUtils();
        }

        return instance;
    }

    public String fullFormatDate(String stringDate){
        String text="";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(stringDate));
            // Then get the day of week from the Date based on specific locale.
            text= new SimpleDateFormat("EE, yyyy-MM-dd", Locale.ENGLISH).format(date);
        }catch (ParseException e){

        }
        return text;
    }

    public String today(){
        String text="";
        Date date =new Date();
        text= new SimpleDateFormat("EE, yyyy-MM-dd", Locale.ENGLISH).format(date);
        return text;
    }
}
