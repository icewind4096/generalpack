package com.mindmotion.service.impl;

import com.mindmotion.dao.DesigncodeDAO;
import com.mindmotion.domain.Designcode;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.exception.DesigncodeException;
import com.mindmotion.service.DesigncodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mecwa on 2019/9/22.
 */
@Service
public class DesigncodeServiceImpl implements DesigncodeService {
    @Autowired
    private DesigncodeDAO designcodeDAO;

    @Override
    public DesigncodeDTO create(DesigncodeDTO designcodeDTO) {
        Designcode result = findByCode(designcodeDTO.getCode());
        if (result == null){
            Designcode designcode = new Designcode();
            BeanUtils.copyProperties(designcodeDTO, designcode);
            designcode = designcodeDAO.save(designcode);
            BeanUtils.copyProperties(designcode, designcodeDTO);
            return designcodeDTO;
        } else {
            throw new DesigncodeException(ResultEnum.DESIGNCODE_EXIST);
        }
    }

    @Override
    public Designcode update(Designcode designcode) {
        return designcodeDAO.save(designcode);
    }

    @Override
    public Designcode findByCode(String code) {
        return designcodeDAO.findByCode(code);
    }

    @Override
    public Page<Designcode> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "code");
        Pageable pageable = PageRequest.of(0, 2, sort);
        return designcodeDAO.findAll(pageable);
    }
}
