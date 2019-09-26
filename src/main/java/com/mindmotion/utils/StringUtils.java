package com.mindmotion.utils;

/**
 * Created by mecwa on 2019/9/26.
 */
public class StringUtils {
    public static String Int2BoolString(Integer value){
        if (value == 0){
            return "false";
        } else {
            return "true";
        }
    }
}
