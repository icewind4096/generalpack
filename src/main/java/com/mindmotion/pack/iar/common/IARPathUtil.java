package com.mindmotion.pack.iar.common;

import com.mindmotion.enums.IARSysPathEnum;
import com.mindmotion.utils.FileUtils;

/**
 * Created by MMNJ002 on 2019/9/29.
 */
public class IARPathUtil {
    public static String getDeviceFilePath(String rootDirectory, String companyName, String familyName){
        return getPartDirectory(getDiretoryByType(rootDirectory, "devices", companyName), familyName);
    }

    public static String getDebugFilePath(String rootDirectory, String companyName){
        return getDiretoryByType(rootDirectory, "debugger", companyName);
    }

    public static String getLinkerFilePath(String rootDirectory, String companyName) {
        return getDiretoryByType(rootDirectory, "linker", companyName);
    }

    private static String getFlashLoadFilePath(String rootDirectory, String companyName) {
        return getDiretoryByType(rootDirectory, "flashloader", companyName);
    }

    private static String getDiretoryByType(String rootDirectory, String foldType, String companyName) {
        return String.format("%s\\%s\\%s\\", rootDirectory, foldType, companyName);
    }

    private static String getPartDirectory(String typeDirectory, String familyName) {
        return String.format("%s\\%s\\", typeDirectory, familyName);
    }

    private static String getPartFileName(String filePath, String partName, String ext) {
        return String.format("%s%s%s", filePath, partName, ext);
    }

    public static String getSysCurDir() {
        return IARSysPathEnum.SYSTEM_PATH_CURDIR.getMessage();
    }

    public static String getSysTookKitDir() {
        return String.format("%s\\%s", IARSysPathEnum.SYSTEM_PATH_TOOLKITDIR.getMessage(), "config");
    }

    public static String getMenuFileName(String directory, String companyName, String familyName, String partName) {
        return getPartFileName(getDeviceFilePath(directory, companyName, familyName), partName, ".menu");
    }

    public static String getBoardFileName(String directory, String companyName, String partName) {
        return getPartFileName(getFlashLoadFilePath(directory, companyName), partName, ".board");
    }

    public static String getICFFileName(String directory, String companyName, String partName) {
        return getPartFileName(getLinkerFilePath(directory, companyName), partName, ".icf");
    }

    public static String getDDFFileName(String directory, String companyName, String partName) {
        return getPartFileName(getDebugFilePath(directory, companyName), partName, ".ddf");
    }

    public static String getI79FileName(String directory, String companyName, String familyName, String partName) {
        return getPartFileName(getDeviceFilePath(directory, companyName, familyName), partName, ".i79");
    }

    public static String getDMACFileName(String directory, String companyName, String dmacName) {
        return getPartFileName(getDebugFilePath(directory, companyName), dmacName, ".dmac");
    }

    public static String getProbeScriptFileName(String directory, String companyName, String probeScriptName) {
        return getPartFileName(getDebugFilePath(directory, companyName), probeScriptName, ".ProbeScript");
    }

    public static String getSVDFileName(String directory, String companyName, String partname) {
        return getPartFileName(getDebugFilePath(directory, companyName), partname, ".svd");
    }

    public static String getIARTemplatePath() {
        return FileUtils.getResourcePath("packfiletemplate\\iar");
    }
}
