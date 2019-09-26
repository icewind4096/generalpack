package com.mindmotion.enums;

import lombok.Getter;

/**
 * Created by MMNJ002 on 2019/9/25.
 */
@Getter
public enum ResultEnum {
    DESIGNCODE_EXIST(10, "设计代码已存在"),
    DESIGNCODE_NOT_EXIST(20, "设计代码不存在"),
    SUBPARTFAMILY_NAME_NOT_EXITS(100, "器件不存在"),
    GENERAL_PACK_IAR_MENU_FAIL(1000, "产生IAR pack文件中Menu文件出错"),
    GENERAL_PACK_FAIL(1020, "产生PACK包失败, 未知错误"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
