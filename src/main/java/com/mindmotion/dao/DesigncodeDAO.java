package com.mindmotion.dao;

import com.mindmotion.domain.Designcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by mecwa on 2019/9/22.
 */
public interface DesigncodeDAO extends JpaRepository<Designcode, Integer>{
    Designcode findByCode(String code);

    Page<Designcode> findAll(Pageable pageable);

    @Query(nativeQuery=true, value = "select a.* from designcode a where a.code in (select designcode from family b where b.familyname in (select familyname from part where partname = :partName))")
    Designcode findByPartNameParam(@Param("partName") String partName);
}
