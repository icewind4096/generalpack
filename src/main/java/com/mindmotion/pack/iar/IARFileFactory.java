package com.mindmotion.pack.iar;

import com.mindmotion.domain.DDFMemory;
import com.mindmotion.domain.Designcode;
import com.mindmotion.dto.DDFMemoryDTO;
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
import java.util.List;

/**
 * Created by mecwa on 2019/9/28.
 */
public class IARFileFactory {
    public static Boolean makeDeviceDirectory(String directoryName) {
        FileUtils.makeDirs(directoryName);
        return true;
    }

    public static Boolean generateI79File(String fileName, String companyName, String partName, DesigncodeDTO designcodeDTO) {
        I79File i79File = new I79File(companyName, partName, designcodeDTO);
        return i79File.saveToFile(fileName);
    }

    public static Boolean generateMenuFile(String fileName, PartDTO partDTO){
        MenuFile menuFile = new MenuFile(partDTO);
        return menuFile.saveToFile(fileName);
    }

    public static Boolean makeDebugDirectory(String directoryName) {
        FileUtils.makeDirs(directoryName);
        return true;
    }

    public static Boolean generateDDFFile(String fileName, String coreName, List<DDFMemoryDTO> ddfMemoryDTOList, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        DDFFile ddfFile = new DDFFile(coreName, ddfMemoryDTOList, designcodeDTO, partDTO);
        return ddfFile.saveToFile(fileName);
    }

    public static Boolean generateDMACFile(String fileName, String dmacName) {
        DMACFile dmacFile = new DMACFile(dmacName);
        return dmacFile.saveToFile(fileName);
    }

    public static Boolean generateProbeScript(String fileName) {
        ProbeScriptFile probeScriptFile = new ProbeScriptFile();
        return probeScriptFile.saveToFile(fileName);
    }

    public static Boolean generateSVDFile(String fileName, String partname) {
        SVDFile svdFile = new SVDFile(partname);
        return svdFile.saveToFile(fileName);
    }

    public static Boolean generateICFFile(String fileName, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        ICFFile icfFile = new ICFFile(designcodeDTO.getIntvecbase(), designcodeDTO.getFlashbase(), partDTO.getFlashsize()
                                     ,designcodeDTO.getRambase(), partDTO.getRamsize());
        return icfFile.saveToFile(fileName);
    }

    public static Boolean generateBoardFile(String fileName, String companyName, Integer flashbase, Integer flashsize, String partname) {
        BoardFile boardFile = new BoardFile(companyName, flashbase, flashsize, partname);
        return boardFile.saveToFile(fileName);
    }

    public static Boolean generateFlashFile(String flashFileName, String companyName, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        FlashFile flashFile = new FlashFile(companyName, designcodeDTO, partDTO);
        return flashFile.saveToFile(flashFileName);
    }
}
