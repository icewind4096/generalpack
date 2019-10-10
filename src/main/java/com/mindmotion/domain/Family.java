package com.mindmotion.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by MMNJ002 on 2019/10/10.
 */

@Entity
@Data
public class Family {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer parentid;
    private String designcode;
    private String familyname;
    private String descript;
    private Integer flashloadid;
    private Timestamp crtime;
}
