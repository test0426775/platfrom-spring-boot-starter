package com.zxo.platform.interceptor;

import com.zxo.platform.core.LoggingFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: LoggingAbstractInterceptor
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 14:16
 * @Note:
 */
public class LoggingAbstractInterceptor {
    static Logger logger = LoggerFactory.getLogger(LoggingAbstractInterceptor.class);
    private LoggingFactoryBean factoryBean;

    public LoggingFactoryBean getFactoryBean () {
        return factoryBean;
    }

    public void setFactoryBean (LoggingFactoryBean factoryBean) {
        this.factoryBean = factoryBean;
    }

//    /**
//     * create new traceId {@link LogTraceIdGenerator}
//     *
//     * @return traceId
//     * @see DefaultLogTraceIdGenerator
//     */
//    @Override
//    public String createTraceId() {
//        return factoryBean.getTraceGenerator().createTraceId();
//    }
//
//    @Override
//    public String createSpanId () {
//        return null;
//    }

//    /**
//     * create new spanId {@link LogSpanIdGenerator}
//     *
//     * @return spanId
//     * @see DefaultLogSpanIdGenerator
//     */
//    @Override
//    public String createSpanId() {
//        return factoryBean.getSpanGenerator().createSpanId();
//    }
}
