package com.mindmotion.pack.iar;

import com.mindmotion.dto.DDFMemoryDTO;
import com.mindmotion.dto.DesigncodeDTO;
import com.mindmotion.dto.PartDTO;
import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.StringUtils;

import java.util.List;

/**
 * Created by MMNJ002 on 2019/9/29.
 */
public class DDFFile {
    private String coreName;
    private List<DDFMemoryDTO> ddfMemoryDTOList;
    private DesigncodeDTO designcodeDTO;
    private PartDTO partDTO;

    public DDFFile(String coreName, List<DDFMemoryDTO> ddfMemoryDTOList, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        this.coreName = coreName;
        this.ddfMemoryDTOList = ddfMemoryDTOList;
        this.designcodeDTO = designcodeDTO;
        this.partDTO = partDTO;
    }

    public boolean saveToFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        putFileData(stringBuilder, coreName, ddfMemoryDTOList, designcodeDTO, partDTO);
        return FileUtils.saveToFile(fileName, stringBuilder);
    }

    private void putFileData(StringBuilder stringBuilder, String coreName, List<DDFMemoryDTO> ddfMemoryDTOList, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        putCoreData(stringBuilder, coreName);
        putMemorysData(stringBuilder, ddfMemoryDTOList, designcodeDTO, partDTO);
    }

    private void putMemorysData(StringBuilder stringBuilder, List<DDFMemoryDTO> ddfMemoryDTOList, DesigncodeDTO designcodeDTO, PartDTO partDTO) {
        stringBuilder.append(";; Memory information ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;\n");
        stringBuilder.append(";;\n");
        stringBuilder.append(";; Used to define address zones within the ARM address space (Memory).\n");
        stringBuilder.append(";;\n");
        stringBuilder.append(";;   Name      may be almost anything\n");
        stringBuilder.append(";;   AdrSpace  must be Memory\n");
        stringBuilder.append(";;   StartAdr  start of memory block\n");
        stringBuilder.append(";;   EndAdr    end of memory block\n");
        stringBuilder.append(";;   AccType   type of access, read-only (R), read-write (RW) or SFR (W)\n");
        stringBuilder.append("\n");

        stringBuilder.append("[Memory]\n");
        stringBuilder.append(";;                 Name            AdrSpace        StartAdr        EndAdr          AccType   Width\n");
        for (DDFMemoryDTO ddfMemory: ddfMemoryDTOList){
            stringBuilder.append(String.format("%-16s = %-16s%-16s%-16s%-16s%-16s\n",
                                 ddfMemory.getKeytype(), ddfMemory.getCaption(), ddfMemory.getAdrspace(), ddfMemory.getStartadr(),
                                 ddfMemory.getEndadr(),  ddfMemory.getAcctype(),ddfMemory.getWidth()));
        }
        stringBuilder.append(String.format("%-16s = %-16s%-16s%-16s%-16s%-16s\n",
                "Memory", "Flash", "Memory", StringUtils.int2HexString(designcodeDTO.getFlashbase(), 8), StringUtils.int2HexString(designcodeDTO.getFlashbase() + partDTO.getFlashsize() - 1, 8), "R", ""));
        stringBuilder.append(String.format("%-16s = %-16s%-16s%-16s%-16s%-16s\n",
                "Memory", "RAM", "Memory", StringUtils.int2HexString(designcodeDTO.getRambase(), 8), StringUtils.int2HexString(designcodeDTO.getRambase() + partDTO.getRamsize() - 1, 8), "RW", ""));
        stringBuilder.append("\n");

        stringBuilder.append(String.format("%s=%s\n", "TrustedRanges", StringUtils.int2BoolString(designcodeDTO.getTrusterange())));
        stringBuilder.append(String.format("%s=%s\n", "UseSfrFilter", StringUtils.int2BoolString(designcodeDTO.getUsesfrfilter())));
        stringBuilder.append("\n");

        stringBuilder.append("[SfrInclude]\n");
        stringBuilder.append(String.format("%s=%s.svd\n", "File", partDTO.getPartname()));
    }

    private void putCoreData(StringBuilder stringBuilder, String coreName) {
        if (coreName.equals("Cortex-M0") == true){
            putCoreData4M0(stringBuilder);
        } else {
            putCoreData4M3(stringBuilder);
        }
    }

    private void putCoreData4M3(StringBuilder stringBuilder) {
        stringBuilder.append(";; Architecture extensions\n");
        stringBuilder.append("#include weak \"..\\ARM\\ARMv7-M-ETM.ddf\"\n");
        stringBuilder.append("#include weak \"..\\ARM\\ARMv7-M-TPIU.ddf\"\n");
        stringBuilder.append("#include weak \"..\\ARM\\ARMv7-M-MPU.ddf\"\n");
        stringBuilder.append("\n");
    }

    private void putCoreData4M0(StringBuilder stringBuilder) {
        stringBuilder.append(";; Architecture extensions\n");
        stringBuilder.append("#include weak \"..\\ARM\\ARMv6-M-SYST.ddf\"\n");
        stringBuilder.append("\n");
    }
}
