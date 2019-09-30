package com.mindmotion.pack.iar;

import com.mindmotion.utils.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by MMNJ002 on 2019/9/30.
 */
public class DMACFile {
    private String dmacFileName;

    public DMACFile(String dmacName) {
        this.dmacFileName = dmacName;
    }

    public Boolean saveToFile(String fileName) {
        try {
            return FileUtils.copyFile(getDMACFileName(dmacFileName), fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getDMACFileName(String dmacName) throws IOException {
        Resource resource = new ClassPathResource("packfiletemplate\\iar");
        return String.format("%s\\%s.dmac", resource.getURI().getPath(), dmacName);
    }
}
