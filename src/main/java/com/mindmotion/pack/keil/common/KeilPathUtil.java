package com.mindmotion.pack.keil.common;

/**
 * Created by MMNJ002 on 2019/10/11.
 */
public class KeilPathUtil {
    public static String getDeviceFilePath(String rootDirectory, String familyName) {
        return String.format("%s\\%s", rootDirectory, familyName);
    }

    public static String getPDSCFileName(String rootDirectory, String companyName, String familyName) {
        return String.format("%s\\%s.%s_DFP%s", rootDirectory, companyName, familyName, ".pdsc");
    }
}
