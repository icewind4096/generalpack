package com.mindmotion.utils;

/**
 * Created by mecwa on 2019/9/26.
 */
public class StringUtils {
    public static String int2BoolString(Integer value){
        if (value == 0){
            return "false";
        } else {
            return "true";
        }
    }

    public static String int2HexString(Integer value, Integer width, Boolean preEn, Boolean posEn, Boolean lowCase){
        String exp = "%0" + String.valueOf(width) + "x";
        exp = String.format(exp, value);
        if (preEn == true) {
            exp = "0x" + exp;
        }
        if (posEn == true){
            exp = exp + "h";
        }
        if (lowCase == false){
            exp = exp.toUpperCase();
        }
        return exp;
    }
}
