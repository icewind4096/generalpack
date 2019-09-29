package com.mindmotion.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by mecwa on 2019/9/29.
 */
@Data
public class DesigncodeForm {
    @NotEmpty(message = "代码必填")
    private String code;

    @NotEmpty(message = "内核名称必填")
    private String corename;

    @NotEmpty(message = "内核版本必填")
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
    private String dmacname;
}

/*
  `code` varchar(16) NOT NULL,
  `corename` varchar(16) NOT NULL,
  `corever` varchar(16) NOT NULL,
  `minfreq` int(11) NOT NULL,
  `fpu` int(11) NOT NULL,
  `mpu` int(11) NOT NULL,
  `simd` int(11) NOT NULL,
  `jtag` int(11) NOT NULL,
  `swd` int(11) NOT NULL,
  `swotraced0` int(11) NOT NULL,
  `endian` int(11) NOT NULL,
  `thumbsupport` int(11) NOT NULL,
  `armsupport` int(11) NOT NULL,
  `flashbase` int(11) NOT NULL,
  `rambase` int(11) NOT NULL,
  `intvecbase` int(11) NOT NULL,
  `block` int(11) NOT NULL,
  `page` int(11) NOT NULL,
  `trusterange` int(11) NOT NULL,
  `useSfrfilter` int(11) NOT NULL,
  `online` int(11) NOT NULL,
  `aggregate` int(11) NOT NULL,
  `argsdoc` varchar(64) NOT NULL,
  `ddfname` varchar(32) NOT NULL,
  `dmacname` varchar(32) NOT NULL,
 */