package com.mindmotion.pack.keil;

import com.mindmotion.domain.Packlog;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.FamilyDTO;
import com.mindmotion.dto.PacklogDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.StringUtils;
import com.mindmotion.utils.XMLFileUtil;
import org.apache.el.lang.ELArithmetic;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by MMNJ002 on 2019/10/11.
 */

public class PDSCFile {
    private String companyName;
    private FamilyDTO familyDTO;
    private List<FamilyDTO> familyDTOList;
    private DesigncodeDTO designcodeDTO;
    private List<PacklogDTO> packlogDTOList;
    private final List<PartDTO> partDTOList;

    private final String DVENDOR = "MindMotion:132";
    private final String DEVICEDESCRIPT = "MindMotions' %s series delivers 32-bit performance while providing the essentials of the MM32 family, particularly for cost-sensitive applications. %s MCUs combine real-time performance, low-power operation, and the advanced architecture and peripherals associated with the MM32 platform available. This series of products is highly competitive in traditional 8-bit and 16-bit markets and eliminates the need to manage different architectures and the associated development overhead.\n" +
                                          "Typical applications include application control and user interfaces, handheld equipment, A/V receivers and digital TV, PC peripherals, gaming and GPS platforms, industrial applications, PLCs, inverters, printers, scanners, alarm systems, video intercoms, and HVACs\n";

    public PDSCFile(String companyName, FamilyDTO familyDTO, List<FamilyDTO> familyDTOList, DesigncodeDTO designcodeDTO, List<PacklogDTO> packlogDTOList, List<PartDTO> partDTOList) {
        this.companyName = companyName;
        this.familyDTO = familyDTO;
        this.familyDTOList = familyDTOList;
        this.designcodeDTO = designcodeDTO;
        this.packlogDTOList = packlogDTOList;
        this.partDTOList = partDTOList;
    }

    public Boolean saveToFile(String fileName) {
        Document document = DocumentHelper.createDocument();

        Element element = document.addElement("package");

        generateBase(element, companyName, familyDTO, packlogDTOList);

        generateDevices(element, familyDTOList, familyDTO, designcodeDTO, DVENDOR, DEVICEDESCRIPT, partDTOList);

        return FileUtils.saveToFile(fileName, document);
    }

    private void generateDevices(Element element, List<FamilyDTO> familyDTOList, FamilyDTO familyDTO, DesigncodeDTO designcodeDTO, String dvendor, String descript, List<PartDTO> partDTOList) {
        Element familyElement = generateFamily(generateDevicesNode(element), familyDTO, designcodeDTO, dvendor, descript);
        if (familyDTOList != null){
            for (FamilyDTO family: familyDTOList){
                generateSubFamily(familyElement, designcodeDTO, family, partDTOList);
            }
        }
    }

    private void generateSubFamily(Element familyElement, DesigncodeDTO designcodeDTO, FamilyDTO familyDTO, List<PartDTO> partDTOList) {
        Element subFamilyElement = generateSubFamilyBase(familyElement, familyDTO.getFamilyname());
        for (PartDTO partDTO: partDTOList){
            generateDevice(subFamilyElement, designcodeDTO, partDTO);
        }
    }

    private void generateDevice(Element subFamilyElement, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        Element element = generateDeviceNode(subFamilyElement, partDTO.getPartname());
        generateMemory(element, "IROM", designcodeDTO.getFlashbase(), partDTO.getFlashsize(), true, true);
        XMLFileUtil.appendElement(element, "memory", "");

    }

    private void generateMemory(Element node, String name, Integer address, Integer size, boolean startUp, boolean defaultValue) {
        Element element = XMLFileUtil.appendElement(node, "memory", "");
        XMLFileUtil.appendAttribute(element, "id", name);
        XMLFileUtil.appendAttribute(element, "start", String.valueOf(address));
        XMLFileUtil.appendAttribute(element, "size", String.valueOf(size));
        XMLFileUtil.appendAttribute(element, "startup", String.valueOf(StringUtils.bool2Int(startUp)));
        XMLFileUtil.appendAttribute(element, "default", String.valueOf(StringUtils.bool2Int(defaultValue)));
    }

    private Element generateDeviceNode(Element subFamilyElement, String partName) {
        return XMLFileUtil.appendElement(subFamilyElement, partName, "");
    }

    private Element generateSubFamilyBase(Element familyElement, String familyname) {
        return XMLFileUtil.appendElement(familyElement, "subFamily", "");
    }

    private Element generateFamily(Element element, FamilyDTO familyDTO, DesigncodeDTO designcodeDTO, String DVENDOR, String descript) {
        Element familyElement = generateFamilyBase(element, familyDTO.getFamilyname(), DVENDOR);
        generateFamilyProcessor(familyElement, designcodeDTO);
        generateFamilyDescript(familyElement, familyDTO.getFamilyname(), descript);
        return familyElement;
    }

    private void generateFamilyDescript(Element root, String familyName, String descript) {
        XMLFileUtil.appendElement(root, "description", String.format(descript, familyName, familyName));
    }

    private void generateFamilyProcessor(Element root, DesigncodeDTO designcodeDTO) {
        Element element = XMLFileUtil.appendElement(root, "processor", "");
        XMLFileUtil.appendAttribute(element, "Dcore", designcodeDTO.getCorename());
        XMLFileUtil.appendAttribute(element, "DcoreVersion", designcodeDTO.getCorever());
        XMLFileUtil.appendAttribute(element, "Dfpu", designcodeDTO.getFpu().toString());
        XMLFileUtil.appendAttribute(element, "Dmpu", designcodeDTO.getMpu().toString());
        XMLFileUtil.appendAttribute(element, "Dendian", getEndian(designcodeDTO.getEndian()));
    }

    private static String getEndian(Integer endian) {
        return endian == 0 ? "Little-endian" : "Big-endian";
    }

    private Element generateFamilyBase(Element root, String familyName, String dvendor) {
        Element element = XMLFileUtil.appendElement(root, "family", "");
        XMLFileUtil.appendAttribute(element, "Dfamily", String.format("%s Series", familyName));
        XMLFileUtil.appendAttribute(element, "Dvendor", dvendor);
        return element;
    }

    private Element generateDevicesNode(Element element) {
        return XMLFileUtil.appendElement(element, "devices", "");
    }

    private void generateBase(Element element, String companyName, FamilyDTO familyDTO, List<PacklogDTO> packlogDTOList) {
        generatePackage(element, companyName, familyDTO.getFamilyname(), familyDTO.getDescript());
        generateRelease(element, packlogDTOList);
        generateKey(element, familyDTO.getFamilyname());
    }

    private void generateKey(Element element, String familyName) {
        Element elementKeys = XMLFileUtil.appendElement(element, "keywords", "");
        XMLFileUtil.appendElement(elementKeys, "keywords", "MindMotion");
        XMLFileUtil.appendElement(elementKeys, "keywords", "Device Support");
        XMLFileUtil.appendElement(elementKeys, "keywords", "Device Family Package MindMotion");
        XMLFileUtil.appendElement(elementKeys, "keywords", familyName);
        XMLFileUtil.appendElement(elementKeys, "keywords", "MM32");
    }

    private void generateRelease(Element node, List<PacklogDTO> packlogList) {
        if (packlogList != null){
            Element elementReleases = XMLFileUtil.appendElement(node, "releases", "");
            for (PacklogDTO packlog: packlogList){
                Element element = XMLFileUtil.appendElement(elementReleases, "release", packlog.getDescript());
                XMLFileUtil.appendAttribute(element, "version", packlog.getVersion());
                XMLFileUtil.appendAttribute(element, "date", StringUtils.convertTimestamp2YYYYMMDD(packlog.getCrtime()));
            }
        }
    }

    private void generatePackage(Element element, String companyName, String familyName, String descript) {
        XMLFileUtil.appendElement(element, "vendor", companyName);
        XMLFileUtil.appendElement(element, "name", familyName);
        XMLFileUtil.appendElement(element, "url", "");
        XMLFileUtil.appendElement(element, "description", descript);
    }
}