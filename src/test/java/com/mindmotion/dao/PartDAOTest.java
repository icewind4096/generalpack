package com.mindmotion.dao;

import com.mindmotion.domain.Part;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mecwa on 2019/9/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PartDAOTest {
    @Autowired
    private PartDAO partsDAO;

    @Test
    public void findBySubFamilyName() throws Exception {
        Part part = partsDAO.findByPartname("MM32F032K6U61");
        Assert.assertNotNull(part);
    }
}

