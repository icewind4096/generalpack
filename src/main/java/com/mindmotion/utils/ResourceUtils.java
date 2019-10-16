package com.mindmotion.utils;

/**
 * Created by MMNJ002 on 2019/10/15.
 */
public class ResourceUtils {
    public static String getTemplateRoot() {
        return FileUtils.getResourcePath("packfiletemplate");
    }

    public static String getSVDTemplatePath() {
        return String.format("%s\\common", getTemplateRoot());
    }
}
