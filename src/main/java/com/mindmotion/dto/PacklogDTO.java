package com.mindmotion.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by MMNJ002 on 2019/10/12.
 */
@Data
public class PacklogDTO {
    private Integer id;
    private Integer type;
    private String familyname;
    private String version;
    private String descript;
    private Timestamp crtime;
}
