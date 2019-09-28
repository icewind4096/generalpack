package com.mindmotion.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by mecwa on 2019/9/28.
 */
@Entity
@Data
public class DDFMemory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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