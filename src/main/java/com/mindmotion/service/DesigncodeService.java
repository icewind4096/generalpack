package com.mindmotion.service;

import com.mindmotion.domain.Designcode;

import java.util.List;

/**
 * Created by mecwa on 2019/9/22.
 */
public interface DesigncodeService {
    // 新建Designcode
    Designcode create(Designcode designcode);

    // 修改Designcode
    Designcode update(Designcode designcode);

    //// 查询单条记录WithCode
    Designcode findByCode(String code);

    List<Designcode> findAll();
}
