package com.mindmotion.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by mecwa on 2019/9/28.
 */
@Data
public class PartDTO {
    private Integer id;
    private String familyname;
    private String partname;
    private String flashloadname;
    private String tag;
    private String displayname;
    private Integer freq;
    private Integer flashsize;
    private Integer ramsize;
    private String ips;
    private String svd;
    private String iarflashload;
    private String keilflashload;
    private Timestamp crtime;
}
