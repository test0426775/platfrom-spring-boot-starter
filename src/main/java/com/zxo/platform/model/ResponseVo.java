package com.zxo.platform.model;

import com.zxo.platform.enums.BaseCode;
import com.zxo.platform.excption.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ResponseVo
 * @Author: zzzxxxooo
 * @Date: 2022/6/20 10:21
 * @Note:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo<T> {
    private String code;
    private String msg;
    private T data;

    public ResponseVo (T data) {
        this(BaseCode.SUCCESS, data);   // 操作成功的状态码.
    }

    public ResponseVo (ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
        this.data = data;
    }

    public ResponseVo (ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }

    public ResponseVo (String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}