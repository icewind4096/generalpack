package com.mindmotion.dao;

import com.mindmotion.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by MMNJ002 on 2019/10/10.
 */
public interface FamilyDAO extends JpaRepository<Family, Integer> {
    List<Family> findAllByOrderByIdAsc();
    List<Family> findAllByParentidOrderById(Integer parentid);

    @Query(nativeQuery=true, value = "select * from family where (parentid = -1) and (familyname = :familyName)")
    Family findByFamilynameParam(@Param("familyName") String familyName);
}
