package com.mindmotion.pack.iar;

import com.mindmotion.dto.PartDTO;
import com.mindmotion.pack.iar.common.IARPathUtil;
import com.mindmotion.utils.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by MMNJ002 on 2019/9/29.
 */
public class MenuFile {
    private PartDTO partDTO;

    public MenuFile(PartDTO partDTO) {
        this.partDTO = partDTO;
    }

    public boolean saveToFile(String fileName) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("optionMenuItem");
        root.addElement("tag").addText(partDTO.getTag());
        root.addElement("display").addText(partDTO.getDisplayname());
        root.addElement("data").addText(String.format("%s\\%s.i79", IARPathUtil.getSysCurDir(), partDTO.getPartname()));
        return FileUtils.saveToFile(fileName, document);
    }
}
