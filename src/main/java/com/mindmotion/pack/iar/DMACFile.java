package com.mindmotion.pack.iar;

import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;

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
        try {
            return FileUtils.copyFile(getDMACFileName(dmacFileName), fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getDMACFileName(String dmacName) throws IOException {
        return String.format("%s\\%s.dmac", IARPathUtil.getIARTemplatePath(), dmacName);
    }
}
