package com.mindmotion.service.impl;

import com.mindmotion.service.GeneralPackService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by mecwa on 2019/9/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneralPackServiceImplTest {
    @Autowired
    GeneralPackServiceImpl generalPackService;

    @Test
    public void generalIARPack() throws Exception {
        Integer value = generalPackService.generateIARPackByPartName("MM32F032K6U6");
        Assert.assertEquals(0, 0);
    }

    @Test
    public void generlKeilPack() throws Exception {
    }
}