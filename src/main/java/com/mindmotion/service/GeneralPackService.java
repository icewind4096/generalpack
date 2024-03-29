package com.mindmotion.service;

/**
 * Created by mecwa on 2019/9/26.
 */
public interface GeneralPackService {
    // 产生IAR pack文件
    Integer generateIARPackByPartName(String rootDirectory, String partName);

    // 产生IAR pack
    Integer generateIARPackAll(String rootDirectory);

    // 产生Keil pack文件
    Integer generateKeilPackByFamily(String rootDirectory, String familyName);
}
