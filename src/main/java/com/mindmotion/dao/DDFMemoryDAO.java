package com.mindmotion.dao;

import com.mindmotion.domain.DDFMemory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mecwa on 2019/9/28.
 */
public interface DDFMemoryDAO extends JpaRepository<DDFMemory, Integer> {
    List<DDFMemory> findAll();
    List<DDFMemory> findAllByName(String name);
}
