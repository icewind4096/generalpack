package com.mindmotion.converter;

import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.form.DesigncodeForm;
import org.springframework.beans.BeanUtils;

/**
 * Created by mecwa on 2019/9/29.
 */
public class DesigncodeForm2DesigncodeDTO {
    public static DesigncodeDTO convert(DesigncodeForm designcodeForm) {
        DesigncodeDTO designcodeDTO = new DesigncodeDTO();

        BeanUtils.copyProperties(designcodeForm, designcodeDTO);

        return designcodeDTO;
    }
}
