package com.mindmotion.exception;

import com.mindmotion.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by MMNJ002 on 2019/9/25.
 */
@Getter
public class DesigncodeException extends RuntimeException {
    private Integer code;

    public DesigncodeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}
