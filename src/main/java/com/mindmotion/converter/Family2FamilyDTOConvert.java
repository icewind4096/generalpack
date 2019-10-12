package com.mindmotion.converter;

import com.mindmotion.domain.Family;
import com.mindmotion.domain.Part;
import com.mindmotion.dto.FamilyDTO;
import com.mindmotion.dto.PartDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMNJ002 on 2019/10/12.
 */
public class Family2FamilyDTOConvert {
    public static FamilyDTO convert(Family family){
        FamilyDTO familyDTO = new FamilyDTO();
        BeanUtils.copyProperties(family, familyDTO);
        return familyDTO;
    }

    public static List<FamilyDTO> convert(List<Family> familyList) {
        List<FamilyDTO> familyDTOList = new ArrayList<>();
        for (Family family: familyList){
            familyDTOList.add(convert(family));
        }
        return familyDTOList;
    }
}
