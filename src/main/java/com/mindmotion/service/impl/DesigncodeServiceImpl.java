package com.mindmotion.service.impl;

import com.mindmotion.dao.DesigncodeDAO;
import com.mindmotion.domain.Designcode;
import com.mindmotion.service.DesigncodeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Designcode create(Designcode designcode) {
        return designcodeDAO.save(designcode);
    }

    @Override
    public Designcode update(Designcode designcode) {
        return designcodeDAO.save(designcode);
    }

    @Override
    public Designcode findByCode(String designCode) {
        return designcodeDAO.findByCode(designCode);
    }

    @Override
    public List<Designcode> findAll() {
        return designcodeDAO.findAll();
    }
}
