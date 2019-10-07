package com.mindmotion.pack.iar;

import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.enums.IARSysPathEnum;
import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.StringUtils;

/**
 * Created by windvalley on 2019/10/5.
 */
public class FlashFile {
    private String companyName;
    private DesigncodeDTO designcodeDTO;
    private PartDTO partDTO;

    public FlashFile(String companyName, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        this.companyName = companyName;
        this.designcodeDTO = designcodeDTO;
        this.partDTO = partDTO;
    }

    public Boolean saveToFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        putFileData(stringBuilder, companyName, designcodeDTO, partDTO);
        return FileUtils.saveToFile(fileName, stringBuilder);
    }

    private void putFileData(StringBuilder stringBuilder, String companyName, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
        stringBuilder.append("<flash_device>\n");
        stringBuilder.append(String.format("  <exe>%s</exe>\n", IARPathUtil.getOutFileName(IARSysPathEnum.SYSTEM_PATH_TOOLKITDIR.getMessage(), companyName, getOutFileByCoreAndRAM(designcodeDTO.getCorename(), partDTO.getRamsize()))));
        stringBuilder.append(String.format("  <page>%d</page>\n", designcodeDTO.getPage()));
        stringBuilder.append(String.format("  <block>%d %s</block>\n", partDTO.getFlashsize() / 1024, StringUtils.int2HexString(designcodeDTO.getBlock(), 4)));
        stringBuilder.append(String.format("  <flash_base>%s</flash_base>\n", StringUtils.int2HexString(designcodeDTO.getFlashbase(), 8)));
        stringBuilder.append(String.format("  <macro>%s</macro>\n", IARPathUtil.getMACFileName(IARSysPathEnum.SYSTEM_PATH_TOOLKITDIR.getMessage(), companyName, getOutFileByCore(designcodeDTO.getCorename()))));
        stringBuilder.append(String.format("  <online>%d</online>\n", designcodeDTO.getOnline()));
        stringBuilder.append(String.format("  <aggregate>%d</aggregate>\n", designcodeDTO.getAggregate()));
        stringBuilder.append(String.format("  <args_doc>%s</args_doc>\n", designcodeDTO.getArgsdoc()));
        stringBuilder.append("</flash_device>\n");
    }

    // TODO: 2019/10/5 根据内核，选择Mac文件, Out文件放置于resource/packfiletemplate/iar目录下
    private String getOutFileByCore(String corename) {
        return String.format("%s", corename);
    }

    // TODO: 2019/10/5 根据内核和ram尺寸，选择Out文件, Out文件放置于resource/packfiletemplate/iar目录下
    private String getOutFileByCoreAndRAM(String corename, Integer ramsize) {
        return String.format("%s%dk", corename, ramsize / 1024);
    }

}
