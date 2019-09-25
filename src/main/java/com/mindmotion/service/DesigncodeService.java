package com.mindmotion.service;

import com.mindmotion.domain.Designcode;
import com.mindmotion.dto.DesigncodeDTO;
import org.springframework.data.domain.Page;

/**
 * Created by mecwa on 2019/9/22.
 */
public interface DesigncodeService {
    // 新建Designcode
    DesigncodeDTO create(DesigncodeDTO designcode);

    // 修改Designcode
    Designcode update(Designcode designcode);

    //// 查询单条记录WithCode
    Designcode findByCode(String code);

    Page<Designcode> findAll();
}
