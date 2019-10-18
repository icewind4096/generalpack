package com.mindmotion.pack.keil;

import com.mindmotion.pack.keil.common.KeilPathUtil;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.ResourceUtils;

/**
 * Created by MMNJ002 on 2019/10/15.
 */
public class FLMFile {
    private String sourceFileName;

    public FLMFile(String fileName){
        this.sourceFileName = fileName;
    }

    public Boolean saveToFile(String fileName) {
        return FileUtils.copyFile(getFileName(sourceFileName), fileName, true);
    }

    private String getFileName(String fileName) {
        return KeilPathUtil.getFLMFileName(String.format("%s\\%s", ResourceUtils.getTemplateRoot(), "KEIL"), fileName);
    }
}
