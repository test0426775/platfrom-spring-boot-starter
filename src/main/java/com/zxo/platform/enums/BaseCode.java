package com.zxo.platform.enums;

import com.zxo.platform.excption.ErrorCode;
import lombok.Getter;

/**
 * @ClassName: ErrorCode
 * @Author: zzzxxxooo
 * @Date: 2022/6/20 10:25
 * @Note:
 */
@Getter
public enum BaseCode implements ErrorCode {
    /**
     * 操作成功的状态码.
     */
    SUCCESS("0000", "没有错误"),
    /**
     * 未知错误的状态码.
     */
    FAILED("S001", "未知错误"),
    REQUEST_RESULT_ERROR("S002", "包装返回类型失败"),
    UNDEFINED_ERROR("S003", "未定义错误类型"),
    PAGE_NOT_FOUND("S004", "资源不存在"),
    REQUEST_METHOD_ERROR("S005", "不支持该请求方法"),
    ARGS_ERROR("S006", "请求参数不正确！"),
    CHECK_FAIL("S007", "参数校验失败！"),

    ONLY_STRING("C008", "校验类型设置错误！");

    /**
     * The Code.
     */
    private final String code;
    /**
     * The Msg.
     */
    private final String message;

    /**
     * Instantiates a new Error code.
     *
     * @param code the code
     * @param msg  the msg
     */
    BaseCode (String code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
