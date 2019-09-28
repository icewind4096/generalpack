package com.mindmotion.service.impl;

import com.mindmotion.converter.Designcode2DesigncodeDTOConvert;
import com.mindmotion.converter.Part2PartDTOConvert;
import com.mindmotion.dao.DesigncodeDAO;
import com.mindmotion.dao.PartDAO;
import com.mindmotion.domain.Designcode;
import com.mindmotion.domain.Part;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.exception.DesigncodeException;
import com.mindmotion.pack.iar.IARFile;
import com.mindmotion.service.GeneralPackService;
import com.mindmotion.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mecwa on 2019/9/26.
 */
@Service
public class GeneralPackServiceImpl implements GeneralPackService {
    @Autowired
    PartDAO partDAO;

    @Autowired
    DesigncodeDAO designcodeDAO;

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
    public Integer generateIARPackByPartName(String partName) {
        String rootDirectory = "C:\\Users\\mecwa\\Desktop\\pack\\iar";

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

        String directory = IARFile.getDeviceFilePath(rootDirectory, COMPANYNAME, getFamilyPath(partName));
        if (IARFile.makeDeviceDirectory(directory) == true) {
            IARFile.generateMenuFile(IARFile.getMenuFileName(directory, partName), partDTO);
            IARFile.generateI79File(IARFile.getI79FileName(directory, partName), COMPANYNAME, partName, designcodeDTO);
        }

        return 0;
    }

    @Override
    public Integer generateKeilPackByPartName(String subFamilyName) {
        return null;
    }
}
