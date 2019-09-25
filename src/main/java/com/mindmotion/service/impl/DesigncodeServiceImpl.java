package com.mindmotion.service.impl;

import com.mindmotion.converter.Designcode2DesigncodeDTOConvert;
import com.mindmotion.dao.DesigncodeDAO;
import com.mindmotion.domain.Designcode;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.exception.DesigncodeException;
import com.mindmotion.service.DesigncodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mecwa on 2019/9/22.
 */
@Service
public class DesigncodeServiceImpl implements DesigncodeService {
    @Autowired
    private DesigncodeDAO designcodeDAO;

    public DesigncodeDTO edit(DesigncodeDTO designcodeDTO) {
        Designcode designcode = new Designcode();
        BeanUtils.copyProperties(designcodeDTO, designcode);
        designcode = designcodeDAO.save(designcode);
        BeanUtils.copyProperties(designcode, designcodeDTO);
        return designcodeDTO;
    }

    @Override
    public DesigncodeDTO create(DesigncodeDTO designcodeDTO) {
        DesigncodeDTO result = findByCode(designcodeDTO.getCode());
        if (result == null){
            return edit(designcodeDTO);
        } else {
            throw new DesigncodeException(ResultEnum.DESIGNCODE_EXIST);
        }
    }

    @Override
    public DesigncodeDTO update(DesigncodeDTO designcodeDTO)
    {
        DesigncodeDTO result = findByCode(designcodeDTO.getCode());
        if (result != null){
            return edit(designcodeDTO);
        } else {
            throw new DesigncodeException(ResultEnum.DESIGNCODE_NOT_EXIST);
        }
    }

    @Override
    public DesigncodeDTO findByCode(String code)
    {
        Designcode designcode = designcodeDAO.findByCode(code);

        DesigncodeDTO designcodeDTO = new DesigncodeDTO();

        BeanUtils.copyProperties(designcode, designcodeDTO);

        return designcodeDTO;
    }

    @Override
    public Page<DesigncodeDTO> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "code");

        Pageable pageable = PageRequest.of(0, 2, sort);

        Page<Designcode> designcodePage = designcodeDAO.findAll(pageable);

        List<DesigncodeDTO> designcodeDTOList = Designcode2DesigncodeDTOConvert.convert(designcodePage.getContent());

        Page<DesigncodeDTO> designcodeDTOPage = new PageImpl<DesigncodeDTO>(designcodeDTOList, pageable, designcodePage.getTotalElements());

        return designcodeDTOPage;
    }
}
