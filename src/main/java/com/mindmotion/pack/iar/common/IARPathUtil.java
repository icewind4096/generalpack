package com.mindmotion.pack.iar.common;

import com.mindmotion.dto.PartDTO;
import com.mindmotion.enums.IARSysPathEnum;

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

    private static String getLinkerFilePath(String rootDirectory, String companyName) {
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

    public static String getMenuFileName(String directory, String partName) {
        return getPartFileName(directory, partName, ".menu");
    }

    public static String getBoardFileName(String directory, String companyName, String partName) {
        return getPartFileName(getFlashLoadFilePath(directory, companyName), partName, ".board");
    }

    public static String getLinkerFileName(String directory, String companyName, String partName) {
        return getPartFileName(getLinkerFilePath(directory, companyName), partName, ".icf");
    }

    public static String getDDFFileName(String companyName, String partName) {
        return getPartFileName(companyName, partName, ".ddf");
    }

    public static String getI79FileName(String directory, String partName) {
        return getPartFileName(directory, partName, ".i79");
    }

    public static String getDMACFileName(String directory, String dmacName) {
        return getPartFileName(directory, dmacName, ".dmac");
    }

    public static String getProbeScriptFileName(String directory, String probeScriptName) {
        return getPartFileName(directory, probeScriptName, ".ProbeScript");
    }
}
