package com.mindmotion.pack.iar;

import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.ResourceUtils;

/**
 * Created by windvalley on 2019/10/7.
 */
public class OutFile {
    private String sourceFileName;

    public OutFile(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public Boolean saveToFile(String fileName) {
        return FileUtils.copyFile(getFileName(sourceFileName), fileName, true);
    }

    private String getFileName(String sourceFileName) {
        return String.format("%s\\%s", IARPathUtil.getTemplatePath(), sourceFileName);
    }
}
