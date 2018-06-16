package com.gifary.commontemplate.utils;

import java.text.DecimalFormat;

/**
 * Created by gifary on 6/14/18.
 */

public class CurrencyUtil {
    private static CurrencyUtil instance = null;

    public CurrencyUtil() {
    }

    public static CurrencyUtil getInstance(){
        if(instance==null){
            instance = new CurrencyUtil();
        }

        return instance;
    }

    public  String priceWithDecimal (double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.00");
        return formatter.format(price);
    }

    public  String priceWithoutDecimal (double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(price);
    }

    public  String priceToString(double price) {
        String toShow = priceWithoutDecimal(price);
        if (toShow.indexOf(".") > 0) {
            return priceWithDecimal(price);
        } else {
            return priceWithoutDecimal(price);
        }
    }
}
