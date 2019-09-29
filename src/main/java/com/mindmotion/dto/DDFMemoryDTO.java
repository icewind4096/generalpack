package com.mindmotion.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by MMNJ002 on 2019/9/29.
 */
@Data
public class DDFMemoryDTO {
    private Integer id;
    private String name;
    private String keytype;
    private String caption;
    private String adrspace;
    private String startadr;
    private String endadr;
    private String acctype;
    private String width;
    private Timestamp crtime;
}
