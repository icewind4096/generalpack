package com.mindmotion.service.impl;

import com.mindmotion.converter.Designcode2DesigncodeDTOConvert;
import com.mindmotion.converter.Part2PartDTOConvert;
import com.mindmotion.dao.DDFMemoryDAO;
import com.mindmotion.dao.DesigncodeDAO;
import com.mindmotion.dao.PartDAO;
import com.mindmotion.domain.DDFMemory;
import com.mindmotion.domain.Designcode;
import com.mindmotion.domain.Part;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.exception.DesigncodeException;
import com.mindmotion.pack.iar.IARFile;
import com.mindmotion.service.GeneralPackService;
import com.mindmotion.utils.FileUtils;
import org.hibernate.engine.jdbc.internal.DDLFormatterImpl;
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
    private final String ROOTDIRECTORY = "C:\\Users\\mecwa\\Desktop\\pack\\iar";

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
    public Integer generateIARPackByPartName(String partName) {
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

        generate4Devices(ROOTDIRECTORY, COMPANYNAME, partName, partDTO, designcodeDTO);

        generate4Debug(ROOTDIRECTORY, COMPANYNAME, partName, designcode.getCorename(), ddfMemoryList);

        return 0;
    }

    private Boolean generate4Debug(String rootDirectory, String companyname, String partName, String coreName, List<DDFMemory> ddfMemoryList) {
        String directory = IARFile.getDebugFilePath(rootDirectory, COMPANYNAME);
        if (IARFile.makeDebugDirectory(directory) == true) {
        }

        return false;
    }

    private Boolean generate4Devices(String rootDirectory, String company, String partName, PartDTO partDTO, DesigncodeDTO designcodeDTO) {
        String directory = IARFile.getDeviceFilePath(rootDirectory, company, getFamilyPath(partName));
        if (IARFile.makeDeviceDirectory(directory) == true) {
            IARFile.generateMenuFile(IARFile.getMenuFileName(directory, partName), partDTO);
            IARFile.generateI79File(IARFile.getI79FileName(directory, partName), company, partName, designcodeDTO);
            return true;
        }
        return false;
    }

    @Override
    public Integer generateKeilPackByPartName(String subFamilyName) {
        return null;
    }
}
