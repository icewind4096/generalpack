package com.mindmotion.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public static boolean saveToFile(String fileName, StringBuilder stringBuilder){
        File file = new File(fileName);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
