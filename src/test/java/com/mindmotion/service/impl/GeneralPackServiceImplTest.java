package com.mindmotion.service.impl;

import com.mindmotion.service.GeneralPackService;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.ZipCompressFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;

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
    public void generateIARPackByPartNameTest() throws Exception {
        Resource resource = new ClassPathResource("");
        String rootDirectory = resource.getURI().getPath() + "\\MM32_EWARM_Pack\\";
        Integer value = generalPackService.generateIARPackByPartName(rootDirectory,"MM32L051");
        FileUtils.zipDirectory(String.format("%s\\MM32_EWARM_Pack.zip", FileUtils.getParentDirectory(rootDirectory)), rootDirectory);
        FileUtils.delDirectorys(rootDirectory);
        Assert.assertEquals(Integer.valueOf(0), value);
    }

    @Test
    public void generateIARPackAllTest() throws Exception {
        Resource resource = new ClassPathResource("");
        String rootDirectory = resource.getFile() + "\\MM32_EWARM_Pack\\";
        Integer value = generalPackService.generateIARPackAll(rootDirectory);
        FileUtils.zipDirectory(String.format("%s\\MM32_EWARM_Pack.zip", FileUtils.getParentDirectory(rootDirectory)), rootDirectory);
        FileUtils.delDirectorys(rootDirectory);
        Assert.assertEquals(Integer.valueOf(0), value);
    }

    @Test
    public void generlKeilPackByFamilyTest() throws Exception {
        String familyName = "MM32L3";
        String version = "1.0.0";
        Resource resource = new ClassPathResource("");
        String rootDirectory = resource.getFile().getPath() + "\\MM32_Keil_Pack\\" + familyName;
        Integer value = generalPackService.generateKeilPackByFamily(rootDirectory, familyName);
        FileUtils.zipDirectory(String.format("%s\\MindMotion.%s_DFP.%s.pack", resource.getFile().getPath(), familyName, version), rootDirectory);
        FileUtils.delDirectorys(resource.getFile().getPath() + "\\MM32_Keil_Pack\\");
        Assert.assertEquals(Integer.valueOf(0), value);
    }
}
