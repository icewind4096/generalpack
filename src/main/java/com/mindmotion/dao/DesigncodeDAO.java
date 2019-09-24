package com.mindmotion.dao;

import com.mindmotion.domain.Designcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mecwa on 2019/9/22.
 */
public interface DesigncodeDAO extends JpaRepository<Designcode, Integer>{
    Designcode  findByCode(String code);
}
