package com.mindmotion.service.impl;

import com.mindmotion.converter.DDFMemory2DDFMemoryDTOConvert;
import com.mindmotion.converter.Designcode2DesigncodeDTOConvert;
import com.mindmotion.converter.Part2PartDTOConvert;
import com.mindmotion.dao.DDFMemoryDAO;
import com.mindmotion.dao.DesigncodeDAO;
import com.mindmotion.dao.FamilyDAO;
import com.mindmotion.dao.PartDAO;
import com.mindmotion.domain.DDFMemory;
import com.mindmotion.domain.Designcode;
import com.mindmotion.domain.Family;
import com.mindmotion.domain.Part;
import com.mindmotion.dto.DDFMemoryDTO;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.exception.GeneratePackException;
import com.mindmotion.pack.iar.IARFileFactory;
import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.service.GeneralPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mecwa on 2019/9/26.
 */
@Service
public class GeneralPackServiceImpl implements GeneralPackService {
    @Autowired
    FamilyDAO familyDAO;

    @Autowired
    PartDAO partDAO;

    @Autowired
    DesigncodeDAO designcodeDAO;

    @Autowired
    DDFMemoryDAO ddfMemoryDAO;

    private final String COMPANYNAME = "MindMotion";

    private HashMap<String, String> partFamilyMap = new HashMap<String, String>();

    private String getFamilyFullPath(String partName) {
        if (partFamilyMap.get(partName) == null){
            refershPartNameMap();
        }
        return partFamilyMap.get(partName);
    }

    private Boolean refershPartNameMap() {
        List<Part> partList = partDAO.findAllByOrderByPartnameDesc();

        List<Family> familyList = familyDAO.findAllByOrderByIdDesc();

        partFamilyMap.clear();

        for (Part part: partList){
            partFamilyMap.put(part.getPartname(), getFamilyFullByFamilyName(part.getFamilyname(), familyList));
        }

        return true;
    }

    private String getFamilyFullByFamilyName(String familyName, List<Family> familyList) {
        Integer index0 = getFamilyByFamilyName(familyName, familyList);
        if (index0 >= 0){
            Family family = familyList.get(index0);
            if (family.getParentid() > 0){
                Integer index1 = getFamilyIndexByParentId(family.getParentid(), familyList);
                return String.format("%s\\%s", familyList.get(index1).getFamilyname(), familyList.get(index0).getFamilyname());
            } else {
                return String.format("%s", familyList.get(index0).getFamilyname());
            }
        }
        return "";
    }

    private Integer getFamilyIndexByParentId(Integer parentId, List<Family> familyList) {
        for (int i = 0; i < familyList.size(); i++) {
            if (familyList.get(i).getId().equals(parentId)){
                return i;
            }
        };
        return -1;
    }

    private Integer getFamilyByFamilyName(String familyName, List<Family> familyList) {
        for (int i = 0; i < familyList.size(); i++) {
            if (familyList.get(i).getFamilyname().equalsIgnoreCase(familyName)){
                return i;
            }
        };
        return -1;
    }

    @Override
    public Integer generateIARPackByPartName(String rootDirectory, String partName) {
        PartDTO partDTO = getPartDTOData(partName);

        DesigncodeDTO designcodeDTO = getDesignCodeDTOData(partName);

        List<DDFMemoryDTO> ddfMemoryDTOList = getDDFMemoryList(designcodeDTO.getDdfname());

        generate4Devices(rootDirectory, COMPANYNAME, partDTO, designcodeDTO);

        generate4Debug(rootDirectory, COMPANYNAME, partDTO, designcodeDTO.getCorename(), ddfMemoryDTOList, designcodeDTO);

        generate4Linker(rootDirectory, COMPANYNAME, designcodeDTO, partDTO);

        generate4FlashLoad(rootDirectory, COMPANYNAME, designcodeDTO, partDTO);

        return 0;
    }

    private List<DDFMemoryDTO> getDDFMemoryList(String ddfname) {
        List<DDFMemory> ddfMemoryList = ddfMemoryDAO.findAllByName(ddfname);
        if (ddfMemoryList == null){
            throw new GeneratePackException(ResultEnum.DESIGNCODE_NOT_EXIST);
        }
        return DDFMemory2DDFMemoryDTOConvert.convert(ddfMemoryList);
    }

    private DesigncodeDTO getDesignCodeDTOData(String partName) {
        Designcode designcode = designcodeDAO.findByPartNameParam(partName);
        if (designcode == null){
            throw new GeneratePackException(ResultEnum.DESIGNCODE_NOT_EXIST);
        }
        return Designcode2DesigncodeDTOConvert.convert(designcode);
    }

    private PartDTO getPartDTOData(String partName) {
        Part part = partDAO.findByPartname(partName);
        if (part == null){
            throw new GeneratePackException(ResultEnum.SUBPARTFAMILY_NAME_NOT_EXITS);
        }
        return Part2PartDTOConvert.convert(part);
    }

    @Override
    public Integer generateIARPackAll(String rootDirectory) {
        List<Part> partList = partDAO.findAll();
        for (Part part: partList){
            generateIARPackByPartName(rootDirectory, part.getPartname());
        }
        return 0;
    }

    private Boolean generate4FlashLoad(String rootDirectory, String companyName, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        String directory = IARPathUtil.getFlashLoadFilePath(rootDirectory, companyName);
        if (IARFileFactory.makeDebugDirectory(directory) == true) {
            IARFileFactory.generateBoardFile(IARPathUtil.getBoardFileName(rootDirectory, companyName, partDTO.getPartname()), companyName, designcodeDTO.getFlashbase(), partDTO.getFlashsize(), partDTO.getPartname());
            IARFileFactory.generateFlashFile(IARPathUtil.getFlashFileName(rootDirectory, companyName, partDTO.getPartname()), companyName, designcodeDTO, partDTO);
            IARFileFactory.generateMacFile(IARPathUtil.getMACFileName(rootDirectory, companyName, designcodeDTO.getCorename()), designcodeDTO.getCorename());
            IARFileFactory.generateOutFile(IARPathUtil.getOutFileName(rootDirectory, companyName, IARPathUtil.getOutFileNameByCoreAndRAM(designcodeDTO.getCorename(), partDTO.getRamsize())), designcodeDTO.getCorename(), partDTO.getRamsize());
            return true;
        }

        return false;
    }

    private Boolean generate4Linker(String rootDirectory, String companyName, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        String directory = IARPathUtil.getLinkerFilePath(rootDirectory, companyName);
        if (IARFileFactory.makeDebugDirectory(directory) == true) {
            IARFileFactory.generateICFFile(IARPathUtil.getICFFileName(rootDirectory, companyName, partDTO.getPartname()), designcodeDTO, partDTO);
            return true;
        }

        return false;
    }

    private Boolean generate4Debug(String rootDirectory, String companyname, PartDTO partDTO, String coreName, List<DDFMemoryDTO> ddfMemoryDTOList, DesigncodeDTO designcodeDTO) {
        String directory = IARPathUtil.getDebugFilePath(rootDirectory, companyname);
        if (IARFileFactory.makeDebugDirectory(directory) == true) {
            IARFileFactory.generateDDFFile(IARPathUtil.getDDFFileName(rootDirectory, companyname, partDTO.getPartname()), coreName, ddfMemoryDTOList, designcodeDTO, partDTO);
            IARFileFactory.generateDMACFile(IARPathUtil.getDMACFileName(rootDirectory, companyname, designcodeDTO.getDmacname()), designcodeDTO.getDmacname());
            IARFileFactory.generateProbeScript(IARPathUtil.getProbeScriptFileName(rootDirectory, companyname, "MM32"));
            IARFileFactory.generateSVDFile(IARPathUtil.getSVDFileName(rootDirectory, companyname, partDTO.getPartname()), partDTO.getPartname());
            return true;
        }

        return false;
    }

    private Boolean generate4Devices(String rootDirectory, String company, PartDTO partDTO, DesigncodeDTO designcodeDTO) {
        String directory = IARPathUtil.getDeviceFilePath(rootDirectory, company, getFamilyFullPath(partDTO.getPartname()));
        if (IARFileFactory.makeDeviceDirectory(directory) == true) {
            IARFileFactory.generateMenuFile(IARPathUtil.getMenuFileName(rootDirectory, company, getFamilyFullPath(partDTO.getPartname()), partDTO.getPartname()), partDTO);
            IARFileFactory.generateI79File(IARPathUtil.getI79FileName(rootDirectory, company, getFamilyFullPath(partDTO.getPartname()), partDTO.getPartname()), company, partDTO.getPartname(), designcodeDTO);
            return true;
        }
        return false;    }

    @Override
    public Integer generateKeilPackByPartName(String subFamilyName) {
        return null;
    }
}

