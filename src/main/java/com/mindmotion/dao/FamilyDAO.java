package com.mindmotion.dao;

import com.mindmotion.domain.Family;
import com.mindmotion.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by MMNJ002 on 2019/10/10.
 */
public interface FamilyDAO extends JpaRepository<Family, Integer> {
    List<Family> findAllByOrderByIdDesc();
}
