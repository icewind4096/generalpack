package com.mindmotion.dao;

import com.mindmotion.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mecwa on 2019/9/26.
 */
public interface PartDAO extends JpaRepository<Part, Integer> {
    Part findByPartname(String partName);

    List<Part> findAllByOrderByPartnameDesc();

    List<Part> findAllByFamilynameInOrderByPartname(List<String> familyName);
}
