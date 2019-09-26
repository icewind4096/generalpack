package com.mindmotion.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 设计项目代码
 * Created by mecwa on 2019/9/22.
 */
@Entity
@Data
@DynamicUpdate(true)
public class Designcode {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String corename;
    private String corever;
    private Integer minfreq;
    private Integer fpu;
    private Integer mpu;
    private Integer simd;
    private Integer jtag;
    private Integer swd;
    private Integer swotraced0;
    private Integer endian;
    private Integer thumbsupport;
    private Integer armsupport;
    private Integer flashbase;
    private Integer rambase;
    private Integer intvecbase;
    private Integer block;
    private Integer page;
    private Integer trusterange;
    private Integer usesfrfilter;
    private Integer online;
    private Integer aggregate;
    private String argsdoc;
    private String ddfname;
    private String dmac;
    private Timestamp crtime;

    public Designcode(){

    }
}
