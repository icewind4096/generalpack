package com.mindmotion.converter;

import com.mindmotion.domain.Part;
import com.mindmotion.dto.PartDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mecwa on 2019/9/28.
 */
public class Part2PartDTOConvert {
    public static PartDTO convert(Part part){
        PartDTO partDTO = new PartDTO();
        BeanUtils.copyProperties(part, partDTO);
        return partDTO;
    }

    public static List<PartDTO> convert(List<Part> partList) {
        List<PartDTO> partDTOList = new ArrayList<>();
        for (Part part: partList){
            partDTOList.add(convert(part));
        }
        return partDTOList;
    }
}
