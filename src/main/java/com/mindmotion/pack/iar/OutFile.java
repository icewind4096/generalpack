package com.mindmotion.pack.iar;

import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;

/**
 * Created by windvalley on 2019/10/7.
 */
public class OutFile {
    private final String coreName;
    private final Integer ramSize;

    public OutFile(String coreName, Integer ramSize) {
        this.coreName = coreName;
        this.ramSize = ramSize;
    }

    public Boolean saveToFile(String fileName) {
        return FileUtils.copyFile(getFileName(coreName, ramSize), fileName, true);
    }

    private String getFileName(String coreName, Integer ramSize) {
        return String.format("%s\\%s.out", IARPathUtil.getIARTemplatePath(), IARPathUtil.getOutFileNameByCoreAndRAM(coreName, ramSize));
    }
}
