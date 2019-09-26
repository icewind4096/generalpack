package com.mindmotion.service;

/**
 * Created by mecwa on 2019/9/26.
 */
public interface GeneralPackService {
    // 产生IAR pack文件
    Integer generalIARPack(String subFamilyName);

    // 产生Keil pack文件
    Integer generlKeilPack(String subFamilyName);
}
