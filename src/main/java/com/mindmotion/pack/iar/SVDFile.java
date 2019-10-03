package com.mindmotion.pack.iar;

import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;

import java.io.IOException;

/**
 * Created by windvalley on 2019/10/3.
 */
public class SVDFile {
    private String partName;

    public SVDFile(String partName) {
        this.partName= partName;
    }

    public Boolean saveToFile(String fileName) {
            return FileUtils.copyFile(getFileNameByPartName(partName), fileName, true);
    }

    private String getFileNameByPartName(String partName) {
        // TODO: 2019/10/3 根据partName决定取什么svd， 规则再讨论
        return String.format("%s\\%s", IARPathUtil.getIARTemplatePath(), "MM32F031C8T.svd");
    }
}
