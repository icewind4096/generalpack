package com.mindmotion.pack.iar;

import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;

/**
 * Created by windvalley on 2019/10/7.
 */
public class MacFile {
    private String coreName;
    public MacFile(String coreName) {
        this.coreName = coreName;
    }


    public Boolean saveToFile(String fileName) {
        return FileUtils.copyFile(getMACFileName(coreName), fileName, true);
    }

    private String getMACFileName(String coreName) {
        return String.format("%s\\%s.mac", IARPathUtil.getIARTemplatePath(), coreName);
    }
}
