package com.mindmotion.utils;

import org.dom4j.Element;

/**
 * Created by MMNJ002 on 2019/10/11.
 */
public class XMLFileUtil {
    public static void appendAttribute(Element element, String key, String value){
        element.addAttribute(key, value);
    }

    public static Element appendElement(Element node, String name, String text) {
        return node.addElement(name).addText(text);
    }
}
