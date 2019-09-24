package com.mindmotion.dao;

import com.mindmotion.domain.Designcode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mecwa on 2019/9/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DesigncodeDAOTest {
    @Autowired
    private DesigncodeDAO designcodeDAO;

    @Test
    public void findOneTest(){
        Designcode designcode = designcodeDAO.findById(1).get();
        System.out.println(designcode.toString());
    }

    @Test
    @Transactional
    public void saveTest(){
        Designcode designcode = new Designcode();
        designcode.setCode("MZ309");
        designcode.setCorename("Cortex-M0");
        designcode.setCorever("c1p0");
        Designcode result = designcodeDAO.save(designcode);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest(){
        Designcode designcode = designcodeDAO.findById(2).get();
        designcode.setMinfreq(48000000);
        designcodeDAO.save(designcode);
    }

    @Test
    public void findByCodeTest(){
        Designcode designcode = designcodeDAO.findByCode("MZ310");
        System.out.println(designcode);
        Assert.assertNotNull(designcode);
    }
}