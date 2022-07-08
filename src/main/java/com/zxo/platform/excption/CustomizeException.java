package com.zxo.platform.excption;

import com.zxo.platform.enums.BaseCode;
import lombok.Getter;

/**
 * @ClassName: CustomizeException
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 14:52
 * @Note:
 */
@Getter
public class CustomizeException extends RuntimeException {
    private String code;
    private String msg;

    public CustomizeException () {
        this(BaseCode.FAILED.getCode(), BaseCode.FAILED.getMessage());
    }

    public CustomizeException (String msg) {
        this.msg = msg;
    }

    public CustomizeException (String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CustomizeException (ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }
//    private int code;
//    private String msg;
//
//    /**
//     * Constructor initializes exception object
//     *
//     * @param message Exception message
//     */
//    public CustomizeException (String message) {
//        super(message);
//    }
//
//    /**
//     * Constructor initializes exception object
//     *
//     * @param message Exception message
//     * @param cause   {@link Throwable} stack information
//     */
//    public CustomizeException (String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public CustomizeException () {
//        this( 1001, "接口错误" );
//    }
//
//    public CustomizeException ( int code ) {
//        this.code = code;
//    }
//
//    public CustomizeException ( int code, String msg ) {
//        super( msg );
//        this.code = code;
//        this.msg = msg;
//    }
}
