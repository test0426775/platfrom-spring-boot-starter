package com.zxo.platform.trace.support;

import com.zxo.platform.excption.CustomizeException;
import com.zxo.platform.model.RequestLog;
import com.zxo.platform.trace.LogTraceIdGenerator;

import java.util.UUID;

/**
 * The default support of {@link LogTraceIdGenerator}
 *
 * @author zzzxxxooo
 */
public class DefaultLogTraceIdGenerator implements LogTraceIdGenerator {
    /**
     * Use UUID as the default traceId
     *
     * @return {@link RequestLog#getTraceId()}
     * @throws CustomizeException Exception
     */
    @Override
    public String createTraceId () throws CustomizeException {
        return UUID.randomUUID().toString();
    }
}
