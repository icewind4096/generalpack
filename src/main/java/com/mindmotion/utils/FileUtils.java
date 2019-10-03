package com.mindmotion.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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

    public static Boolean copyFile(String sourceFileName, String targetFileName, Boolean replaceEn) {
        try {
            File source = new File(sourceFileName);

            File target = new File(targetFileName);

            Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getResourcePath(String path) {
        Resource resource = new ClassPathResource(path);
        try {
            return resource.getURI().getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
