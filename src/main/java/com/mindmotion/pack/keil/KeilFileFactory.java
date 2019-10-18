package com.mindmotion.pack.keil;

import com.mindmotion.domain.Family;
import com.mindmotion.domain.Packlog;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.FamilyDTO;
import com.mindmotion.dto.PacklogDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.pack.SVDFile;
import com.mindmotion.utils.FileUtils;

import java.util.List;

/**
 * Created by MMNJ002 on 2019/10/11.
 */
public class KeilFileFactory {
    public static boolean makeRootDirectory(String directoryName) {
        FileUtils.makeDirs(directoryName);
        return true;
    }

    public static Boolean generatePDSCFile(String companyName, FamilyDTO familyDTO, List<FamilyDTO> familyDTOList, DesigncodeDTO designcodeDTO, String fileName, List<PacklogDTO> packlogDTOList, List<PartDTO> partDTOList) {
        PDSCFile pdscFile = new PDSCFile(companyName, familyDTO, familyDTOList, designcodeDTO, packlogDTOList, partDTOList);
        return pdscFile.saveToFile(fileName);
    }

    public static Boolean generateSVDFile(String fileName, String partname) {
        SVDFile svdFile = new SVDFile(partname);
        return svdFile.saveToFile(fileName);
    }

    public static Boolean generateFLMFile(String targetFileName, String sourceFileName) {
        FLMFile flmFile = new FLMFile(sourceFileName);
        return flmFile.saveToFile(targetFileName);
    }
}
