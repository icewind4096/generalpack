package com.mindmotion.pack.iar;

import com.mindmotion.enums.IARSysPathEnum;
import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.StringUtils;

/**
 * Created by windvalley on 2019/10/4.
 */
public class BoardFile {
    private String companyName;
    private Integer flashBase;
    private Integer flashSize;
    private String partName;

    public BoardFile(String companyName, Integer flashBase, Integer flashSize, String partName) {
        this.companyName = companyName;
        this.flashBase = flashBase;
        this.flashSize = flashSize;
        this.partName = partName;
    }

    public Boolean saveToFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        putFileData(stringBuilder, companyName, partName, flashBase, flashSize);
        return FileUtils.saveToFile(fileName, stringBuilder);
    }

    private void putFileData(StringBuilder stringBuilder, String companyName, String partName, Integer flashBase, Integer flashSize) {
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
        stringBuilder.append("<flash_board>\n");
        stringBuilder.append("  <pass>\n");
        stringBuilder.append(String.format("      <range>CODE %s %s</range>\n", StringUtils.int2HexString(flashBase, 8), StringUtils.int2HexString(flashBase + flashSize - 1, 8)));
        // TODO: 2019/10/7 这里使用数据库了面的FlashlaodName字段，还是使用partName拼接 
        stringBuilder.append(String.format("      <loader>%s</loader>\n", IARPathUtil.getFlashFileName(IARSysPathEnum.SYSTEM_PATH_TOOLKITDIR.getMessage(), companyName, partName)));
        stringBuilder.append("  </pass>\n");
        stringBuilder.append("</flash_board>\n");
    }
}
