package com.mindmotion.pack.keil.common;

/**
 * Created by MMNJ002 on 2019/10/11.
 */
public class KeilPathUtil {
    public static String getPDSCFileName(String rootDirectory, String companyName, String familyName) {
        return String.format("%s\\%s.%s_DFP%s", rootDirectory, companyName, familyName, ".pdsc");
    }

    public static String getSVDFileName(String rootDirectory, String fileName) {
        return String.format("%s/%s", rootDirectory, fileName);
    }

    public static String getSVDFilePath(String rootDirectory) {
        return String.format("%s/%s", rootDirectory, "SVD");
    }

    public static String getFLMFilePath(String rootDirectory) {
        return String.format("%s/%s", rootDirectory, "FLASH");
    }

    public static String getFLMFileName(String rootDirectory, String fileName) {
        return String.format("%s/%s", rootDirectory, fileName);
    }
}
