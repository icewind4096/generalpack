package com.mindmotion.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by MMNJ002 on 2019/10/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyServiceImplTest {
    @Autowired
    private FamilyServiceImpl familyService;

    @Test
    public void getFamilyTreeWithJSONTest() throws Exception {
        System.out.println(familyService.getFamilyTreeWithJSON());
    }
}
