package com.mindmotion.dao;

import com.mindmotion.domain.Packlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by MMNJ002 on 2019/10/11.
 */
public interface PacklogDAO extends JpaRepository<Packlog, Integer> {
    List<Packlog> findAllByFamilynameOrderByVersionAsc(String familyName);
}

