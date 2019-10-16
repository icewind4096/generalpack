package com.mindmotion.pack.iar;

import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.ResourceUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;


/**
 * Created by MMNJ002 on 2019/9/30.
 */
public class ProbeScriptFile {
    public Boolean saveToFile(String fileName) {
        try {
            return FileUtils.copyFile(getProbeScriptFileName(), fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getProbeScriptFileName() throws IOException {
        return String.format("%s\\%s.ProbeScript", IARPathUtil.getTemplatePath(), "MM32");
    }
}

