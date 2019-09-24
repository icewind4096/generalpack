package com.mindmotion.service.impl;

import com.mindmotion.domain.Designcode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void create() throws Exception {
        Designcode designcode = new Designcode();
        designcode.setCode("MZ307");
        designcode.setCorename("Cortex-M3");
        designcode.setCorever("r2p1");
        designcode.setMinfreq(72000000);
        Designcode result = designcodeService.create(designcode);
        Assert.assertEquals(result.getCode(), "MZ318");
    }

    @Test
    public void update() throws Exception {
        Designcode designcode = designcodeService.findByCode("MZ310");
        designcode.setCorever("r1p0");
        Designcode result = designcodeService.update(designcode);
        Assert.assertEquals(result.getCode(), "MZ310");
    }

    @Test
    public void findByCode() throws Exception {
        Designcode designcode = designcodeService.findByCode("MZ310");
        Assert.assertNotNull(designcode);
    }

    @Test
    public void findAll() throws Exception {
        List<Designcode> designcodeList = designcodeService.findAll();
        Assert.assertEquals(2, designcodeList.size());
    }

}