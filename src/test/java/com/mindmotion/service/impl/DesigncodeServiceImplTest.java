package com.mindmotion.service.impl;

import com.mindmotion.domain.Designcode;
import com.mindmotion.dto.DesigncodeDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mecwa on 2019/9/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DesigncodeServiceImplTest {
    @Autowired
    private DesigncodeServiceImpl designcodeService;

    @Test
    @Transactional
    public void create() throws Exception {
        DesigncodeDTO designcode = new DesigncodeDTO();
        designcode.setCode("MZ307");
        designcode.setCorename("Cortex-M3");
        designcode.setCorever("r2p1");
        designcode.setMinfreq(72000000);
        designcode.setFpu(0);
        designcode.setMpu(0);
        designcode.setSimd(0);
        designcode.setJtag(0);
        designcode.setSwd(0);
        designcode.setSwotraced0(0);
        designcode.setEndian(0);
        designcode.setThumbsupport(0);
        designcode.setArmsupport(0);
        designcode.setFlashbase(134217728);
        designcode.setRambase(536870912);
        designcode.setIntvecbase(134217728);
        designcode.setBlock(1024);
        designcode.setPage(2);
        designcode.setTrusterange(1);
        designcode.setUsesfrfilter(1);
        designcode.setOnline(1);
        designcode.setAggregate(1);
        designcode.setArgsdoc("--skip_erase -Don't erase blocks that read empty.");
        designcode.setDdfname("Cortex-M0");
        DesigncodeDTO result = designcodeService.create(designcode);
        Assert.assertEquals(result.getCode(), "MZ307");
    }

    @Test
    public void update() throws Exception {
/*
        Designcode designcode = designcodeService.findByCode("MZ310");
        designcode.setCorever("r1p0");
        Designcode result = designcodeService.update(designcode);
        Assert.assertEquals(result.getCode(), "MZ310");
         */
    }

    @Test
    public void findByCode() throws Exception {
/*
        Designcode designcode = designcodeService.findByCode("MZ310");
        Assert.assertNotNull(designcode);
         */
    }

    @Test
    public void findAll() throws Exception {
/*
        List<Designcode> designcodeList = designcodeService.findAll();
        Assert.assertEquals(2, designcodeList.size());
         */
    }

}