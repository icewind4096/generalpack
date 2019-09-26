package com.mindmotion.dao;

import com.mindmotion.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mecwa on 2019/9/26.
 */
public interface PartDAO extends JpaRepository<Part, Integer> {
    Part findByPartname(String partName);
}
