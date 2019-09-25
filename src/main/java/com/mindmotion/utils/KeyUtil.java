package com.mindmotion.utils;

import java.util.Random;

/**
 * Created by mecwa on 2019/9/25.
 */
public class KeyUtil {
    /*
    生成主键
    格式时间+随机数
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        System.currentTimeMillis();

        Integer value = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(value);
    }
}
