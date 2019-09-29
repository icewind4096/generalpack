package com.mindmotion.service.impl;

import com.mindmotion.converter.DDFMemory2DDFMemoryDTOConvert;
import com.mindmotion.converter.Designcode2DesigncodeDTOConvert;
import com.mindmotion.converter.Part2PartDTOConvert;
import com.mindmotion.dao.DDFMemoryDAO;
import com.mindmotion.dao.DesigncodeDAO;
import com.mindmotion.dao.PartDAO;
import com.mindmotion.domain.DDFMemory;
import com.mindmotion.domain.Designcode;
import com.mindmotion.domain.Part;
import com.mindmotion.dto.DDFMemoryDTO;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.exception.DesigncodeException;
import com.mindmotion.pack.iar.IARFileFactory;
import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.service.GeneralPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mecwa on 2019/9/26.
 */
@Service
public class GeneralPackServiceImpl implements GeneralPackService {
    @Autowired
    PartDAO partDAO;

    @Autowired
    DesigncodeDAO designcodeDAO;

    @Autowired
    DDFMemoryDAO ddfMemoryDAO;

    private final String COMPANYNAME = "mindmotion";

    // TODO: 2019/9/26 后期数据有redis中获得，启动时，先把数据调理以后放入redis, 此处先模拟一些数据
    private String getFamilyPath(String partName) {
        if (partName.substring(0, 8).equalsIgnoreCase("MM32F032")){
            if (partName.substring(9, 10).equalsIgnoreCase("6")){
                return String.format("%s\\%sx6", partName.substring(0, 6), partName.substring(0, 8));
            } else {
                return String.format("%s\\%sX4", partName.substring(0, 6), partName.substring(0, 8));
            }
        };
        return "";
    }

    @Override
    public Integer generateIARPackByPartName(String rootDirectory, String partName) {
        Part part = partDAO.findByPartname(partName);
        if (part == null){
            throw new DesigncodeException(ResultEnum.SUBPARTFAMILY_NAME_NOT_EXITS);
        }
        PartDTO partDTO = Part2PartDTOConvert.convert(part);

        Designcode designcode = designcodeDAO.findByPartNameParam(partDTO.getPartname());
        if (part == null){
            throw new DesigncodeException(ResultEnum.DESIGNCODE_NOT_EXIST);
        }
        DesigncodeDTO designcodeDTO = Designcode2DesigncodeDTOConvert.convert(designcode);

        List<DDFMemory> ddfMemoryList = ddfMemoryDAO.findAllByName(designcode.getDdfname());
        List<DDFMemoryDTO> ddfMemoryDTOList = DDFMemory2DDFMemoryDTOConvert.convert(ddfMemoryList);

        generate4Devices(rootDirectory, COMPANYNAME, partName, partDTO, designcodeDTO);

        generate4Debug(rootDirectory, COMPANYNAME, partDTO, designcode.getCorename(), ddfMemoryDTOList, designcodeDTO);

        return 0;
    }

    private Boolean generate4Debug(String rootDirectory, String companyname, PartDTO partDTO, String coreName, List<DDFMemoryDTO> ddfMemoryDTOList, DesigncodeDTO designcodeDTO) {
        String directory = IARPathUtil.getDebugFilePath(rootDirectory, companyname);
        if (IARFileFactory.makeDebugDirectory(directory) == true) {
            IARFileFactory.generateDDFFile(IARPathUtil.getDDFFileName(directory, partDTO.getPartname()), coreName, ddfMemoryDTOList, designcodeDTO, partDTO);
        }

        return false;
    }

    private Boolean generate4Devices(String rootDirectory, String company, String partName, PartDTO partDTO, DesigncodeDTO designcodeDTO) {
        String directory = IARPathUtil.getDeviceFilePath(rootDirectory, company, getFamilyPath(partName));
        if (IARFileFactory.makeDeviceDirectory(directory) == true) {
            IARFileFactory.generateMenuFile(IARPathUtil.getMenuFileName(directory, partName), partDTO);
            IARFileFactory.generateI79File(IARPathUtil.getI79FileName(directory, partName), company, partName, designcodeDTO);
            return true;
        }
        return false;
    }

    @Override
    public Integer generateKeilPackByPartName(String subFamilyName) {
        return null;
    }
}
