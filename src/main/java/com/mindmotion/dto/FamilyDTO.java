package com.mindmotion.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by MMNJ002 on 2019/10/12.
 */
@Data
public class FamilyDTO {
    private Integer id;
    private Integer parentid;
    private String designcode;
    private String familyname;
    private String descript;
    private Integer flashloadid;
    private Timestamp crtime;
}
