package com.mindmotion.dao;

import com.mindmotion.domain.Designcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mecwa on 2019/9/22.
 */
public interface DesigncodeDAO extends JpaRepository<Designcode, Integer>{
    Designcode findByCode(String code);

    Page<Designcode> findAll(Pageable pageable);
}
