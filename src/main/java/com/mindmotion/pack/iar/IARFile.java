package com.mindmotion.pack.iar;

import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.enums.IARSysPathEnum;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mecwa on 2019/9/28.
 */
public class IARFile {
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

    public static boolean makeDeviceDirectory(String directoryName) {
        FileUtils.makeDirs(directoryName);
        return true;
    }

    public static Boolean generateMenuFile(String fileName, PartDTO partDTO){
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("optionMenuItem");
        root.addElement("tag").addText(partDTO.getTag());
        root.addElement("display").addText(partDTO.getDisplayname());
        root.addElement("data").addText(String.format("%s\\%s", getSysCurDir(), partDTO.getPartname()));
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File(fileName)), outputFormat);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String getMenuFileName(String directory, String partName) {
        return getPartFileName(directory, partName, ".menu");
    }

    public static boolean generateI79File(String fileName, String company, String partName, DesigncodeDTO designcodeDTO) {
        StringBuilder stringBuilder = getI79FileData(company, partName, designcodeDTO);
        return FileUtils.saveToFile(fileName, stringBuilder);
    }

    private static StringBuilder getI79FileData(String companyName, String partName, DesigncodeDTO designcodeDTO) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[FILEFORMAT]\n");
        stringBuilder.append("rev=1.6\n");
        stringBuilder.append("\n");

        stringBuilder.append("[CHIP]\n");
        stringBuilder.append(String.format("%s=%s\n", "name", partName));
        stringBuilder.append(String.format("%s=%s\n", "endiansupport", getEndian(designcodeDTO.getEndian())));
        stringBuilder.append(String.format("%s=%s\n", "thumbsupport", StringUtils.Int2BoolString(designcodeDTO.getThumbsupport())));
        stringBuilder.append(String.format("%s=%s\n", "armsupport", StringUtils.Int2BoolString(designcodeDTO.getArmsupport())));
        stringBuilder.append(String.format("%s=%s\n", "fpu", getFPU(designcodeDTO.getFpu())));
        stringBuilder.append(String.format("%s=%s\n", "simd", StringUtils.Int2BoolString(designcodeDTO.getSimd())));
        stringBuilder.append(String.format("%s=%s\n", "ProbeScriptFile", getProbeScriptFileName(companyName)));
        stringBuilder.append(String.format("%s=%s\n", "DeviceMacros", getDMACFileName(companyName, partName)));
        stringBuilder.append(String.format("%s=%s\n", "JTAG", StringUtils.Int2BoolString(designcodeDTO.getJtag())));
        stringBuilder.append(String.format("%s=%s\n", "SWD", StringUtils.Int2BoolString(designcodeDTO.getSwd())));
        stringBuilder.append(String.format("%s=%s\n", "SWO_TraceD0", StringUtils.Int2BoolString(designcodeDTO.getSwotraced0())));
        stringBuilder.append("\n");

        stringBuilder.append("[CORE]\n");
        stringBuilder.append(String.format("%s=%s\n", "name", designcodeDTO.getCorename()));
        stringBuilder.append("\n");

        stringBuilder.append("[DDF FILE]\n");
        stringBuilder.append(String.format("%s=%s\n", "name", getDDFFileName(companyName, partName)));
        stringBuilder.append("\n");

        stringBuilder.append("[LINKER FILE]\n");
        stringBuilder.append(String.format("%s=%s\n", "name", getLinkerFileName(companyName, partName)));
        stringBuilder.append("\n");

        stringBuilder.append("[FLASH LORDER]\n");
        stringBuilder.append(String.format("%s=%s\n", "little", getBoardFileName(companyName, partName)));
        stringBuilder.append("\n");

        return stringBuilder;
    }

    private static String getBoardFileName(String companyName, String partName) {
        return getPartFileName(getFlashLoadFilePath(getSysTookKitDir(), companyName), partName, ".board");
    }

    private static String getLinkerFileName(String companyName, String partName) {
        return getPartFileName(getLinkerFilePath(getSysTookKitDir(), companyName), partName, ".icf");
    }

    private static String getDDFFileName(String companyName, String partName) {
        return getPartFileName(companyName, partName, ".ddf");
    }

    private static String getDMACFileName(String companyName, String partName) {
        return getPartFileName(getDebugFilePath(getSysTookKitDir(), companyName), partName, ".dmac");
    }

    private static String getProbeScriptFileName(String companyName) {
        return getPartFileName(getDebugFilePath(getSysTookKitDir(), companyName), "MM32", ".ProbeScript");
    }

    private static String getFPU(Integer fpu) {
        return fpu == 0 ? "none" : "";
    }

    private static String getEndian(Integer endian) {
        return endian == 0 ? "le" : "be";
    }

    public static String getI79FileName(String directory, String partName) {
        return getPartFileName(directory, partName, ".i79");
    }

    public static boolean makeDebugDirectory(String directoryName) {
        FileUtils.makeDirs(directoryName);
        return true;
    }
}
