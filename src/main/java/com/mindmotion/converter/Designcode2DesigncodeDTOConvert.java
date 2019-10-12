package com.mindmotion.converter;

import com.mindmotion.domain.Designcode;
import com.mindmotion.dto.DesigncodeDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mecwa on 2019/9/25.
 */
public class Designcode2DesigncodeDTOConvert {
    public static DesigncodeDTO convert(Designcode designcode){
        DesigncodeDTO designcodeDTO = new DesigncodeDTO();
        BeanUtils.copyProperties(designcode, designcodeDTO);
        return designcodeDTO;
    }

    public static List<DesigncodeDTO> convert(List<Designcode> designcodeList) {
        List<DesigncodeDTO> designcodeDTOList = new ArrayList<>();
        for (Designcode designcode:designcodeList){
            designcodeDTOList.add(convert(designcode));
        }
        return designcodeDTOList;
    }
}
