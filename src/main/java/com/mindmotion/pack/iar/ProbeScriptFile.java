package com.mindmotion.pack.iar;

import com.mindmotion.utils.FileUtils;

import java.io.IOException;

/**
 * Created by MMNJ002 on 2019/9/30.
 */
public class ProbeScriptFile {
    public Boolean saveToFile(String fileName) {
        return FileUtils.copyFile(getProbeScriptFileName(), fileName, true);
    }

    private String getProbeScriptFileName() {
        Resource resource = new ClassPathResource("packfiletemplate\\iar");
        return String.format("%s\\%s.dmac", resource.getURI().getPath(), dmacName);
    }
}

