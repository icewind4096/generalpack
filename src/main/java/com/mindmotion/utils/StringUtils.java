package com.mindmotion.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by mecwa on 2019/9/26.
 */
public class StringUtils {
    public static Integer bool2Int(Boolean value){
        return (value == true ? 1 : 0);
    }

    public static String int2BoolString(Integer value){
        return (value == 0 ? "false" : "true");
    }

    public static String int2HexString(Integer value, Integer width, Boolean preEn, Boolean lowCase){
        String exp = width == 0 ? "%x" : "%0" + String.valueOf(width) + "x";
        exp = String.format(exp, value);
        exp = preEn == true ? "0x" + exp : exp + "h";
        if (lowCase == false){
            exp = exp.toUpperCase();
        }
        return exp;
    }

    public static String int2HexString(Integer value, Integer width){
        return int2HexString(value, width, true, true);
    }

    public static String convertTimestamp2YYYYMMDD(Timestamp timestamp){
        return convertTimestatmpWithPattern(timestamp, "yyyy-dd-mm");

    }

    private static String convertTimestatmpWithPattern(Timestamp timestamp, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(timestamp);
    }
}
