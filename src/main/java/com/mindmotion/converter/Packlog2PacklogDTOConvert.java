package com.mindmotion.converter;

import com.mindmotion.domain.Packlog;
import com.mindmotion.dto.PacklogDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMNJ002 on 2019/10/12.
 */
public class Packlog2PacklogDTOConvert {
    public static List<PacklogDTO> convert(List<Packlog> packlogs) {
        List<PacklogDTO> packlogDTOList = new ArrayList<>();
        for (Packlog packlog: packlogs){
            PacklogDTO packlogDTO = new PacklogDTO();
            BeanUtils.copyProperties(packlog, packlogDTO);
            packlogDTOList.add(packlogDTO);
        }
        return packlogDTOList;
    }
}
