package com.mindmotion.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by MMNJ002 on 2019/10/11.
 */

@Entity
@Data
@DynamicUpdate(true)
public class Packlog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer type;
    private String familyname;
    private String version;
    private String descript;
    private Timestamp crtime;
}
