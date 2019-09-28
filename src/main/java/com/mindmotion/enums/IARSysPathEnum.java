package com.mindmotion.enums;

import lombok.Getter;

/**
 * Created by mecwa on 2019/9/26.
 */
@Getter
public enum IARSysPathEnum {
    SYSTEM_PATH_CURDIR(10, "$CUR_DIR$"),
    SYSTEM_PATH_TOOLKITDIR(20, "$TOOLKIT_DIR$");
    ;

    private Integer code;
    private String message;

    IARSysPathEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
