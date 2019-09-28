package com.mindmotion.utils;

import java.io.File;

/**
 * Created by mecwa on 2019/9/26.
 */
public class FileUtils {
    public static void makeDirs(String directoryName){
        File directory = new File(directoryName);
        if (directory.exists() == false){
            directory.mkdirs();
        }
    }
}
