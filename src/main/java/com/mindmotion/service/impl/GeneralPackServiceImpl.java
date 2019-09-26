package com.mindmotion.service.impl;

import com.mindmotion.dao.DesigncodeDAO;
import com.mindmotion.dao.PartDAO;
import com.mindmotion.domain.Designcode;
import com.mindmotion.domain.Part;
import com.mindmotion.enums.ResultEnum;
import com.mindmotion.enums.SystemPathEnum;
import com.mindmotion.exception.DesigncodeException;
import com.mindmotion.service.GeneralPackService;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.StringUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by mecwa on 2019/9/26.
 */
@Service
public class GeneralPackServiceImpl implements GeneralPackService {
    @Autowired
    PartDAO subPartFamilyDAO;

    @Autowired
    DesigncodeDAO designcodeDAO;

    private final String COMPANYNAME = "mindmotion";

    @Override
    public Integer generalIARPack(String partName) {
        Part part = subPartFamilyDAO.findByPartname(partName);
        if (part == null){
            throw new DesigncodeException(ResultEnum.SUBPARTFAMILY_NAME_NOT_EXITS);
        }

        Designcode designcode ;//= designcodeDAO.findByCode(part.)

        String rootDirectory = "C:\\Users\\mecwa\\Desktop\\pack\\iar";

        String menuDirectory = packDirectoryName(rootDirectory, "devices", COMPANYNAME, part.getPartname());

        generalMenuFile(menuDirectory, part);

        generalI79File(menuDirectory, COMPANYNAME, part, designcode);

        return 0;
    }

    private void generalI79File(String rootDirectory, String companyName, Part part, Designcode designcode) {
        FileUtils.makeDirs(rootDirectory);
        String fileName = getI79FileName(rootDirectory, part.getPartname());
        if (generalIARPack4I79(part.getPartname(), designcode, companyName, fileName) == false){
            throw new DesigncodeException(ResultEnum.GENERAL_PACK_IAR_MENU_FAIL);
        }
    }

    private boolean generalIARPack4I79(String partName, Designcode designcode, String company, String fileName) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("FILEFORMAT\\n");
        stringBuffer.append("rev=1.6\\n");
        stringBuffer.append("\n");

        stringBuffer.append("[CHIP]");
        stringBuffer.append(String.format("%s=%s", "name", partName));
        stringBuffer.append(String.format("%s=%s", "endiansupport", getEndian(designcode.getEndian())));
        stringBuffer.append(String.format("%s=%s", "thumbsupport", StringUtils.Int2BoolString(designcode.getThumbsupport())));
        stringBuffer.append(String.format("%s=%s", "armsupport", StringUtils.Int2BoolString(designcode.getArmsupport())));
        stringBuffer.append(String.format("%s=%s", "fpu", getFPU(designcode.getFpu()));
        stringBuffer.append(String.format("%s=%s", "simd", StringUtils.Int2BoolString(designcode.getSimd())));
        stringBuffer.append(String.format("%s=%s", "ProbeScriptFile", "");
        stringBuffer.append(String.format("%s=%s", "DeviceMacros", getDMAC(company, designcode.getDmac());
        stringBuffer.append(String.format("%s=%s", "JTAG", designcode.getPartname()));
        stringBuffer.append(String.format("%s=%s", "SWD", designcode.getPartname()));
        stringBuffer.append(String.format("%s=%s", "SWO_TraceD0", designcode.getPartname()));
        stringBuffer.append("\n");

        stringBuffer.append("[CORE]");
        stringBuffer.append(String.format("%s=%s", "name", designcode.getPartname()));
        stringBuffer.append("\n");

        stringBuffer.append("[DDF FILE]");
        stringBuffer.append(String.format("%s=%s", "name", designcode.getPartname()));
        stringBuffer.append("\n");

        stringBuffer.append("[LINKER FILE]");
        stringBuffer.append(String.format("%s=%s", "name", designcode.getPartname()));
        stringBuffer.append("\n");

        stringBuffer.append("[FLASH LORDER]");
        stringBuffer.append(String.format("%s=%s", "little", designcode.getPartname()));
        stringBuffer.append("\n");

        return true;
    }

    private String getDMAC(String company, String dmac) {
        return String.format("%s\\config\\debugger\\%s\\%s", SystemPathEnum.SYSTEM_PATH_TOOLKITDIR.getMessage(), company, dmac);
    }

    private String getFPU(Integer fpu) {
        if (fpu == 0){
            return "none";
        } else {
            return "";
        }
    }

    private String getEndian(Integer endian) {
        if (endian == 0){
            return "le";
        } else {
            return "be";
        }
    }

    private String getI79FileName(String rootPath, String partName) {
        return packFileName(rootPath, partName,"menu");
    }

    private void generalMenuFile(String rootDirectory, Part part) {
        FileUtils.makeDirs(rootDirectory);
        String fileName = getMenuFileName(rootDirectory, part.getPartname());
        if (generalIARPack4menu(part, fileName) == false){
            throw new DesigncodeException(ResultEnum.GENERAL_PACK_IAR_MENU_FAIL);
        }
    }

    private String getMenuFileName(String rootPath, String partName) {
        return packFileName(rootPath, partName,"menu");
    }

    private String packFileName(String rootPath, String partName, String ext) {
        return String.format("%s\\%s.%s", rootPath, partName, ext);
    }

    private String packDirectoryName(String rootPath, String foldType, String companyName, String partName) {
        return String.format("%s\\%s\\%s\\%s", rootPath, foldType, companyName, getFamilyPath(partName));
    }


    // TODO: 2019/9/26 后期数据有redis中获得，启动时，先把数据调理以后放入redis, 此处先模拟一些数据
    private String getFamilyPath(String partName) {
        if (partName.substring(0, 8).equalsIgnoreCase("MM32F032")){
            if (partName.substring(9, 10).equalsIgnoreCase("6")){
                return String.format("%s\\%sX6", partName.substring(0, 6), partName.substring(0, 8));
            } else {
                return String.format("%s\\%sX6", partName.substring(0, 6), partName.substring(0, 8));
            }
        };
        return "";
    }

    private boolean generalIARPack4menu(Part part, String fileName) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("optionMenuItem");
        root.addElement("tag").addText(part.getTag());
        root.addElement("display").addText(part.getDisplayname());
        root.addElement("data").addText(String.format("%s\\%s", SystemPathEnum.SYSTEM_PATH_CURDIR.getMessage(), part.getPartname()));
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        try {
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File(fileName)), outputFormat);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public Integer generlKeilPack(String subFamilyName) {
        return null;
    }
}
