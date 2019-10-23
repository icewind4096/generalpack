package com.mindmotion.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mindmotion.converter.Family2FamilyDTOConvert;
import com.mindmotion.dao.FamilyDAO;
import com.mindmotion.domain.Family;
import com.mindmotion.dto.FamilyDTO;
import com.mindmotion.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MMNJ002 on 2019/10/21.
 */
@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private FamilyDAO familyDAO;

    @Override
    public JsonArray getFamilyTreeWithJSON() {
        List<FamilyDTO> familyList = Family2FamilyDTOConvert.convert(familyDAO.findAllByOrderByIdAsc());
        return listToJSONTree(familyList, null);
    }

    private JsonArray listToJSONTree(List<FamilyDTO> familyList, FamilyDTO family) {
        JsonArray jsonArray = null;
        for (FamilyDTO familyDTO: familyList){
            if (familyDTO.getParentid() == getId(family)){
                if (jsonArray == null){
                    jsonArray = new JsonArray();
                }
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", familyDTO.getFamilyname());
                jsonObject.addProperty("core", familyDTO.getDesigncode());
                JsonArray childrenArray = listToJSONTree(familyList, familyDTO);
                if (childrenArray != null){
                    jsonObject.add("children", childrenArray);
                }
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }

    private Integer getId(FamilyDTO familyDTO) {
        return familyDTO == null ? -1 : familyDTO.getId();
    }
}
