package com.mindmotion.service.impl;

import com.mindmotion.dto.DesigncodeDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by mecwa on 2019/9/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DesigncodeServiceImplTest {
    @Autowired
    private DesigncodeServiceImpl designcodeService;

    @Test
    @Transactional
    public void create() throws Exception {
        DesigncodeDTO designcodeDTO = new DesigncodeDTO();
        designcodeDTO.setCode("MZ307");
        designcodeDTO.setCorename("Cortex-M3");
        designcodeDTO.setCorever("r2p1");
        designcodeDTO.setMinfreq(72000000);
        designcodeDTO.setFpu(0);
        designcodeDTO.setMpu(0);
        designcodeDTO.setSimd(0);
        designcodeDTO.setJtag(0);
        designcodeDTO.setSwd(0);
        designcodeDTO.setSwotraced0(0);
        designcodeDTO.setEndian(0);
        designcodeDTO.setThumbsupport(0);
        designcodeDTO.setArmsupport(0);
        designcodeDTO.setFlashbase(134217728);
        designcodeDTO.setRambase(536870912);
        designcodeDTO.setIntvecbase(134217728);
        designcodeDTO.setBlock(1024);
        designcodeDTO.setPage(2);
        designcodeDTO.setTrusterange(1);
        designcodeDTO.setUsesfrfilter(1);
        designcodeDTO.setOnline(1);
        designcodeDTO.setAggregate(1);
        designcodeDTO.setArgsdoc("--skip_erase -Don't erase blocks that read empty.");
        designcodeDTO.setDdfname("Cortex-M0");
        DesigncodeDTO result = designcodeService.create(designcodeDTO);
        Assert.assertEquals(result.getCode(), "MZ307");
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void findByCode() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

}