package com.mindmotion.enums;

import lombok.Getter;

/**
 * 大端还是小端
 * Created by mecwa on 2019/9/25.
 */
@Getter
public enum EndianEnum {
    BIGENDIAN(0, "大端"),
    SMALLENDIAN(1, "小端"),
    ;

    private Integer code;

    private String message;

    EndianEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}
