package com.mindmotion.service.impl;

import com.mindmotion.converter.*;
import com.mindmotion.dao.*;
import com.mindmotion.domain.*;
import com.mindmotion.dto.*;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.exception.GeneratePackException;
import com.mindmotion.pack.SVDFile;
import com.mindmotion.pack.iar.IARFileFactory;
import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.pack.keil.KeilFileFactory;
import com.mindmotion.pack.keil.common.KeilPathUtil;
import com.mindmotion.service.GeneralPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    PacklogDAO packLogDAO;

    private final String COMPANYNAME = "MindMotion";

    private HashMap<String, String> partFamilyMap = new HashMap<String, String>();
    private FamilyDTO familyDTOData;

    private String getFamilyFullPath(String partName) {
        if (partFamilyMap.get(partName) == null){
            refershPartNameMap();
        }
        return partFamilyMap.get(partName);
    }

    private Boolean refershPartNameMap() {
        List<Part> partList = partDAO.findAllByOrderByPartnameDesc();

        List<Family> familyList = familyDAO.findAllByOrderByIdAsc();

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

        DesigncodeDTO designcodeDTO = getDesignCodeDTODataByPartName(partName);

        List<DDFMemoryDTO> ddfMemoryDTOList = getDDFMemoryList(designcodeDTO.getDdfname());

        generate4IARDevices(rootDirectory, COMPANYNAME, partDTO, designcodeDTO);

        generate4IARDebug(rootDirectory, COMPANYNAME, partDTO, designcodeDTO.getCorename(), ddfMemoryDTOList, designcodeDTO);

        generate4IARLinker(rootDirectory, COMPANYNAME, designcodeDTO, partDTO);

        generate4IARFlashLoad(rootDirectory, COMPANYNAME, designcodeDTO, partDTO);

        return 0;
    }

    private List<DDFMemoryDTO> getDDFMemoryList(String ddfname) {
        List<DDFMemory> ddfMemoryList = ddfMemoryDAO.findAllByName(ddfname);
        if (ddfMemoryList == null){
            throw new GeneratePackException(ResultEnum.DESIGNCODE_NOT_EXIST);
        }
        return DDFMemory2DDFMemoryDTOConvert.convert(ddfMemoryList);
    }

    private DesigncodeDTO getDesignCodeDTODataByCode(String code) {
        Designcode designcode = designcodeDAO.findByCode(code);
        if (designcode == null){
            throw new GeneratePackException(ResultEnum.DESIGNCODE_NOT_EXIST);
        }
        return Designcode2DesigncodeDTOConvert.convert(designcode);
    }

    private DesigncodeDTO getDesignCodeDTODataByPartName(String partName) {
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

    private Boolean generate4IARFlashLoad(String rootDirectory, String companyName, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        String directory = IARPathUtil.getFlashLoadFilePath(rootDirectory, companyName);
        if (IARFileFactory.makeDebugDirectory(directory) == true) {
            IARFileFactory.generateBoardFile(IARPathUtil.getBoardFileName(rootDirectory, companyName, partDTO.getPartname()), companyName, designcodeDTO.getFlashbase(), partDTO.getFlashsize(), partDTO.getPartname());
            IARFileFactory.generateFlashFile(IARPathUtil.getFlashFileName(rootDirectory, companyName, partDTO.getPartname()), companyName, designcodeDTO, partDTO);
            IARFileFactory.generateMacFile(IARPathUtil.getMACFileName(rootDirectory, companyName, designcodeDTO.getCorename()), designcodeDTO.getCorename());
            IARFileFactory.generateOutFile(IARPathUtil.getOutFileName(rootDirectory, companyName, partDTO.getIarflashload()), partDTO.getIarflashload());
            return true;
        }

        return false;
    }

    private Boolean generate4IARLinker(String rootDirectory, String companyName, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        String directory = IARPathUtil.getLinkerFilePath(rootDirectory, companyName);
        if (IARFileFactory.makeDebugDirectory(directory) == true) {
            IARFileFactory.generateICFFile(IARPathUtil.getICFFileName(rootDirectory, companyName, partDTO.getPartname()), designcodeDTO, partDTO);
            return true;
        }

        return false;
    }

    private Boolean generate4IARDebug(String rootDirectory, String companyname, PartDTO partDTO, String coreName, List<DDFMemoryDTO> ddfMemoryDTOList, DesigncodeDTO designcodeDTO) {
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

    private Boolean generate4IARDevices(String rootDirectory, String company, PartDTO partDTO, DesigncodeDTO designcodeDTO) {
        String directory = IARPathUtil.getDeviceFilePath(rootDirectory, company, getFamilyFullPath(partDTO.getPartname()));
        if (IARFileFactory.makeDeviceDirectory(directory) == true) {
            IARFileFactory.generateMenuFile(IARPathUtil.getMenuFileName(rootDirectory, company, getFamilyFullPath(partDTO.getPartname()), partDTO.getPartname()), partDTO);
            IARFileFactory.generateI79File(IARPathUtil.getI79FileName(rootDirectory, company, getFamilyFullPath(partDTO.getPartname()), partDTO.getPartname()), company, partDTO.getPartname(), designcodeDTO);
            return true;
        }
        return false;    }

    @Override
    public Integer generateKeilPackByFamily(String rootDirectory, String familyName) {
        FamilyDTO familyDTO = getFamilyDTOData(familyName);

        List<FamilyDTO> familyDTOList = getSubFamilyDTODataByFamliyName(familyDTO.getId());
        if (familyDTOList.size() == 0){
            familyDTOList.add(familyDTO);
        }

        DesigncodeDTO designcodeDTO = getDesignCodeDTODataByCode(familyDTO.getDesigncode());

        List<PacklogDTO> packlogDTOs = getPacklogDTODataByFamilyName(familyName);

        List<PartDTO> partDTOList = getPartDTODataByFamilyNameList(getFamilyNames(familyDTOList));

        generate4KeilPDSC(rootDirectory, COMPANYNAME, familyDTO, familyDTOList, designcodeDTO, packlogDTOs, partDTOList);

        generate4KeilSVD(rootDirectory, partDTOList);

        generate4KeilFLM(rootDirectory, partDTOList);

        return 0;
    }

    private Boolean generate4KeilFLM(String rootDirectory, List<PartDTO> partDTOList) {
        String directory = KeilPathUtil.getFLMFilePath(rootDirectory);
        if (IARFileFactory.makeDeviceDirectory(directory) == true) {
            for (PartDTO partDTO : partDTOList) {
                KeilFileFactory.generateFLMFile(KeilPathUtil.getFLMFileName(directory, partDTO.getKeilflashload()), partDTO.getKeilflashload());
            }
        }
        return true;
    }

    private Boolean generate4KeilSVD(String rootDirectory, List<PartDTO> partDTOList) {
        String directory = KeilPathUtil.getSVDFilePath(rootDirectory);
        if (IARFileFactory.makeDeviceDirectory(directory) == true) {
            for (PartDTO partDTO : partDTOList) {
                KeilFileFactory.generateSVDFile(KeilPathUtil.getSVDFileName(directory, partDTO.getSvd()), partDTO.getPartname());
            }
        }
        return true;
    }

    private List<PartDTO> getPartDTODataByFamilyNameList(List<String> familyNameList) {
        List<Part> partList = partDAO.findAllByFamilynameInOrderByPartname(familyNameList);
        return Part2PartDTOConvert.convert(partList);
    }

    private List<String> getFamilyNames(List<FamilyDTO> familyDTOList) {
        List<String> familyNameList = new ArrayList<>();
        for (FamilyDTO familyDTO: familyDTOList){
            familyNameList.add(familyDTO.getFamilyname());
        }
        return familyNameList;
    }

    private List<FamilyDTO> getSubFamilyDTODataByFamliyName(Integer parentId) {
        List<Family> familyList = familyDAO.findAllByParentidOrderById(parentId);
        return Family2FamilyDTOConvert.convert(familyList);
    }

    private List<PacklogDTO> getPacklogDTODataByFamilyName(String familyName) {
        List<Packlog> packlogs = packLogDAO.findAllByFamilynameOrderByVersionAsc(familyName);
        return Packlog2PacklogDTOConvert.convert(packlogs);
    }

    private Boolean generate4KeilPDSC(String rootDirectory, String companyName, FamilyDTO familyDTO, List<FamilyDTO> familyDTOList, DesigncodeDTO designcodeDTO, List<PacklogDTO> packlogDTOs, List<PartDTO> partDTOList) {
        if (KeilFileFactory.makeRootDirectory(rootDirectory) == true) {
            KeilFileFactory.generatePDSCFile(companyName, familyDTO, familyDTOList, designcodeDTO, KeilPathUtil.getPDSCFileName(rootDirectory, companyName, familyDTO.getFamilyname()), packlogDTOs, partDTOList);
        }
        return true;
    }

    public FamilyDTO getFamilyDTOData(String familyName) {
        Family family = familyDAO.findByFamilynameParam(familyName);
        if (family == null){
            throw new GeneratePackException(ResultEnum.FAMILY_NAME_NOT_EXITS);
        }
        return Family2FamilyDTOConvert.convert(family);
    }
}
