package com.mindmotion.converter;

import com.mindmotion.domain.DDFMemory;
import com.mindmotion.dto.DDFMemoryDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMNJ002 on 2019/9/29.
 */
public class DDFMemory2DDFMemoryDTOConvert {
    public static List<DDFMemoryDTO> convert(List<DDFMemory> ddfMemoryList){
        List<DDFMemoryDTO> ddfMemoryDTOList = new ArrayList<>();
        for (DDFMemory ddfMemory: ddfMemoryList){
            DDFMemoryDTO ddfMemoryDTO = new DDFMemoryDTO();
            BeanUtils.copyProperties(ddfMemory, ddfMemoryDTO);
            ddfMemoryDTOList.add(ddfMemoryDTO);
        }
        return ddfMemoryDTOList;
    }
}
