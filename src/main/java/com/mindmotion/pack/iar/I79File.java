package com.mindmotion.pack.iar;

import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.StringUtils;

import static com.mindmotion.pack.iar.common.IARPathUtil.getDebugFilePath;
import static com.mindmotion.pack.iar.common.IARPathUtil.getSysTookKitDir;

/**
 * Created by MMNJ002 on 2019/9/29.
 */
public class I79File {
    private String companyName;
    private String partName;
    private DesigncodeDTO designcodeDTO;

    public I79File(String companyName, String partName, DesigncodeDTO designcodeDTO) {
        this.companyName = companyName;
        this.partName = partName;
        this.designcodeDTO = designcodeDTO;
    }

    public boolean saveToFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        putI79FileData(stringBuilder, companyName, partName, designcodeDTO);
        return FileUtils.saveToFile(fileName, stringBuilder);
    }

    private void putI79FileData(StringBuilder stringBuilder, String companyName, String partName, DesigncodeDTO designcodeDTO) {
        putFileFormatData(stringBuilder);

        putChipData(stringBuilder, partName, companyName, designcodeDTO);

        putCoreData(stringBuilder, designcodeDTO.getCorename());

        putDDFData(stringBuilder, getDDFFileName(companyName, partName));

        putLinkerData(stringBuilder, IARPathUtil.getICFFileName(getSysTookKitDir(), companyName, partName));

        putFlashLoaderData(stringBuilder, IARPathUtil.getBoardFileName(getSysTookKitDir(), companyName, partName));
    }

    private String getDDFFileName(String companyName, String partName) {
        return String.format("%s\\%s%s", companyName, partName, ".DDF");
    }

    private void putFlashLoaderData(StringBuilder stringBuilder, String boardFileName) {
        stringBuilder.append("[FLASH LOADER]\n");
        stringBuilder.append(String.format("%s=%s\n", "little", boardFileName));
        stringBuilder.append("\n");
    }

    private void putLinkerData(StringBuilder stringBuilder, String linkerFileName) {
        stringBuilder.append("[LINKER FILE]\n");
        stringBuilder.append(String.format("%s=%s\n", "name", linkerFileName));
        stringBuilder.append("\n");
    }

    private void putDDFData(StringBuilder stringBuilder, String ddfFileName) {
        stringBuilder.append("[DDF FILE]\n");
        stringBuilder.append(String.format("%s=%s\n", "name", ddfFileName));
        stringBuilder.append("\n");
    }

    private void putCoreData(StringBuilder stringBuilder, String corename) {
        stringBuilder.append("[CORE]\n");
        stringBuilder.append(String.format("%s=%s\n", "name", corename));
        stringBuilder.append("\n");
    }

    private void putChipData(StringBuilder stringBuilder, String partName, String companyName, DesigncodeDTO designcodeDTO) {
        stringBuilder.append("[CHIP]\n");
        stringBuilder.append(String.format("%s=%s\n", "name", partName));
        stringBuilder.append(String.format("%s=%s\n", "endiansupport", getEndian(designcodeDTO.getEndian())));
        stringBuilder.append(String.format("%s=%s\n", "thumbsupport", StringUtils.int2BoolString(designcodeDTO.getThumbsupport())));
        stringBuilder.append(String.format("%s=%s\n", "armsupport", StringUtils.int2BoolString(designcodeDTO.getArmsupport())));
        stringBuilder.append(String.format("%s=%s\n", "fpu", getFPU(designcodeDTO.getFpu())));
        stringBuilder.append(String.format("%s=%s\n", "simd", StringUtils.int2BoolString(designcodeDTO.getSimd())));
        stringBuilder.append(String.format("%s=%s\n", "ProbeScriptFile", getProbeScriptFileName(companyName)));
        stringBuilder.append(String.format("%s=%s\n", "DeviceMacros", IARPathUtil.getDMACFileName(getSysTookKitDir(), companyName, designcodeDTO.getDmacname())));
        stringBuilder.append(String.format("%s=%s\n", "JTAG", StringUtils.int2BoolString(designcodeDTO.getJtag())));
        stringBuilder.append(String.format("%s=%s\n", "SWD", StringUtils.int2BoolString(designcodeDTO.getSwd())));
        stringBuilder.append(String.format("%s=%s\n", "SWO_TraceD0", StringUtils.int2BoolString(designcodeDTO.getSwotraced0())));
        stringBuilder.append("\n");
    }

    public String getProbeScriptFileName(String companyName) {
        return String.format("%s%s%s", getDebugFilePath(getSysTookKitDir(), companyName), "MM32", ".ProbeScript");
    }

    private static String getFPU(Integer fpu) {
        return fpu == 0 ? "none" : "";
    }

    private static String getEndian(Integer endian) {
        return endian == 0 ? "le" : "be";
    }

    private static void putFileFormatData(StringBuilder stringBuilder) {
        stringBuilder.append("[FILEFORMAT]\n");
        stringBuilder.append("rev=1.6\n");
        stringBuilder.append("\n");
    }
}
