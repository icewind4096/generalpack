package com.mindmotion.pack.iar;

import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.ResourceUtils;

import java.io.IOException;

/**
 * Created by MMNJ002 on 2019/9/30.
 */
public class DMACFile {
    private String dmacFileName;

    public DMACFile(String dmacName) {
        this.dmacFileName = dmacName;
    }

    public Boolean saveToFile(String fileName) {
        return FileUtils.copyFile(getDMACFileName(dmacFileName), fileName, true);
    }

    private String getDMACFileName(String dmacName) {
        return String.format("%s\\%s.dmac", IARPathUtil.getTemplatePath(), dmacName);
    }
}
