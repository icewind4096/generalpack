package com.mindmotion.dao;

import com.mindmotion.domain.DDFMemory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mecwa on 2019/9/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DDFMemoryDAOTest {
    @Autowired
    private DDFMemoryDAO ddfMemoryDAO;

    @Test
    public void findAllTest() throws Exception {
        List<DDFMemory> ddfMemoryList = ddfMemoryDAO.findAll();
        System.out.println(ddfMemoryList.toString());
    }

    @Test
    public void findAllByNameTest() throws Exception {
        List<DDFMemory> ddfMemoryList = ddfMemoryDAO.findAllByName("Cortex-M3");
        System.out.println(ddfMemoryList.toString());
        Assert.assertEquals(8, ddfMemoryList.size());
    }
}