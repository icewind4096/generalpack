package com.mindmotion.enums;

import lombok.Getter;

/**
 * Created by MMNJ002 on 2019/9/25.
 */
@Getter
public enum ResultEnum {
    DESIGNCODE_NOT_EXIST(10, "设计代码不存在"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
