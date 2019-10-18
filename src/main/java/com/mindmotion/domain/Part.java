package com.mindmotion.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by mecwa on 2019/9/26.
 */
@Entity
@Data
@DynamicUpdate(true)
public class Part {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    private String crtime;
}