package com.zxo.platform.core;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.zxo.platform.model.RequestLog;

/**
 * @ClassName: LogThreadLocal
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 13:16
 * @Note:
 */
public class LogThreadLocal {
    private static final TransmittableThreadLocal<RequestLog> LOGS = new TransmittableThreadLocal<>();

    public static RequestLog get () {
        return LOGS.get();
    }

    public static void set (RequestLog requestLog) {
        LOGS.set(requestLog);
    }

    public static void remove () {
        LOGS.remove();
    }
}
