package com.zxo.platform.interceptor;

import com.zxo.platform.trace.LogTraceIdGenerator;

/**
 * @ClassName: LoggingAbstractInterceptor
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 14:16
 * @Note:
 */
public interface LoggingInterceptor {
    /**
     * create new traceId
     * {@link LogTraceIdGenerator}
     *
     * @return traceId
     */
    String createTraceId ();

}
