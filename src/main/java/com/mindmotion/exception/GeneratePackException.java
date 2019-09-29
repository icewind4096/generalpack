package com.mindmotion.exception;

import com.mindmotion.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by MMNJ002 on 2019/9/25.
 */
@Getter
public class GeneratePackException extends RuntimeException {
    private Integer code;

    public GeneratePackException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}
