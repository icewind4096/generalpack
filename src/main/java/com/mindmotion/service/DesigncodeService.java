package com.mindmotion.service;

import com.mindmotion.dto.DesigncodeDTO;
import org.springframework.data.domain.Page;

/**
 * Created by mecwa on 2019/9/22.
 */
public interface DesigncodeService {
    // 新建Designcode
    DesigncodeDTO create(DesigncodeDTO designcode);

    // 修改Designcode
    DesigncodeDTO update(DesigncodeDTO designcode);

    // 查询单条记录WithCode
    DesigncodeDTO findByCode(String code);

    // DesignCode分页列表
    Page<DesigncodeDTO> findAll();
}
