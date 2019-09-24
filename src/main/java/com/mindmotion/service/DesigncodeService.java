package com.mindmotion.service;

import com.mindmotion.domain.Designcode;

import java.util.List;

/**
 * Created by mecwa on 2019/9/22.
 */
public interface DesigncodeService {
    Designcode create(Designcode designcode);

    Designcode update(Designcode designcode);

    Designcode findByCode(String designCode);

    List<Designcode> findAll();
}
