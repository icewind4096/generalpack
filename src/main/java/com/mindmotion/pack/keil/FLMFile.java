package com.mindmotion.pack.keil;

import com.mindmotion.pack.keil.common.KeilPathUtil;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.ResourceUtils;

/**
 * Created by MMNJ002 on 2019/10/15.
 */
public class FLMFile {
    private Integer flashSize;

    public FLMFile(Integer flashSize){
        this.flashSize = flashSize;
    }

    public Boolean saveToFile(String fileName) {
        return FileUtils.copyFile(getFileNameByFlashSize(flashSize), fileName, true);
    }

    private String getFileNameByFlashSize(Integer flashSize) {
        return KeilPathUtil.getFLMFileName(String.format("%s\\%s", ResourceUtils.getTemplateRoot(), "KEIL"), flashSize);
    }
}
