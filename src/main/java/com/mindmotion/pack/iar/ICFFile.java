package com.mindmotion.pack.iar;

import com.mindmotion.utils.FileUtils;
import com.mindmotion.utils.StringUtils;

import java.math.BigInteger;
import java.text.Format;

/**
 * Created by windvalley on 2019/10/3.
 */
public class ICFFile {
    private Integer intvecBase;
    private Integer flashBase;
    private Integer flashSize;
    private Integer ramBase;
    private Integer ramSize;

    public ICFFile(Integer intvecBase, Integer flashBase, Integer flashSize, Integer ramBase, Integer ramSize) {
        this.intvecBase = intvecBase;
        this.flashBase = flashBase;
        this.flashSize = flashSize;
        this.ramBase = ramBase;
        this.ramSize = ramSize;
    }

    public Boolean saveToFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        putFileData(stringBuilder, intvecBase, flashBase, flashSize, ramBase, ramSize);
        return FileUtils.saveToFile(fileName, stringBuilder);
    }

    private void putFileData(StringBuilder stringBuilder, Integer intvecBase, Integer flashBase, Integer flashSize, Integer ramBase, Integer ramSize) {
        stringBuilder.append("/*###ICF### Section handled by ICF editor, don't touch! ****/\n");
        stringBuilder.append("/*-Editor annotation file-*/");
        stringBuilder.append("/* IcfEditorFile=\"$TOOLKIT_DIR$\\config\\ide\\IcfEditor\\cortex_v1_0.xml\" */\n");
        stringBuilder.append("/*-Specials-*/\n");
        stringBuilder.append("define symbol __ICFEDIT_intvec_start__ = 0x08000000;\n");
        stringBuilder.append("/*-Memory Regions-*/\n");
        stringBuilder.append(String.format("define symbol __ICFEDIT_region_ROM_start__ = %s;\n", StringUtils.int2HexString(flashBase, 8)));
        stringBuilder.append(String.format("define symbol __ICFEDIT_region_ROM_end__   = %s;\n", StringUtils.int2HexString(flashBase + flashSize - 1, 8)));
        stringBuilder.append(String.format("define symbol __ICFEDIT_region_RAM_start__ = %s;\n", StringUtils.int2HexString(ramBase, 8)));
        stringBuilder.append(String.format("define symbol __ICFEDIT_region_RAM_end__   = %s;\n", StringUtils.int2HexString(ramBase + ramSize - 1, 8)));
        stringBuilder.append("/*-Sizes-*/\n");
        stringBuilder.append("define symbol __ICFEDIT_size_cstack__ = 0x400;\n");
        stringBuilder.append("define symbol __ICFEDIT_size_heap__   = 0x400;\n");
        stringBuilder.append("/**** End of ICF editor section. ###ICF###*/\n");
        stringBuilder.append("define memory mem with size = 4G;\n");
        stringBuilder.append("define region ROM_region   = mem:[from __ICFEDIT_region_ROM_start__   to __ICFEDIT_region_ROM_end__];\n");
        stringBuilder.append("define region RAM_region   = mem:[from __ICFEDIT_region_RAM_start__   to __ICFEDIT_region_RAM_end__];\n");
        stringBuilder.append("define block CSTACK    with alignment = 8, size = __ICFEDIT_size_cstack__   { };\n");
        stringBuilder.append("define block HEAP      with alignment = 8, size = __ICFEDIT_size_heap__     { };\n");
        stringBuilder.append("initialize by copy { readwrite };\n");
        stringBuilder.append("do not initialize  { section .noinit };\n");
        stringBuilder.append("place at address mem:__ICFEDIT_intvec_start__ { readonly section .intvec };\n");
        stringBuilder.append("place in ROM_region   { readonly };\n");
        stringBuilder.append("place in RAM_region   { readwrite,\n");
        stringBuilder.append("                        block CSTACK, block HEAP };\n");
    }
}






