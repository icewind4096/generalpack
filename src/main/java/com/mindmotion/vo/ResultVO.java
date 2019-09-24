package com.mindmotion.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by mecwa on 2019/9/23.
 */
@Data
public class ResultVO<T> {
    //错误代码
    private Integer code;

    //提示信息
    private String message;

    //返回的数据
    private T data;
}
