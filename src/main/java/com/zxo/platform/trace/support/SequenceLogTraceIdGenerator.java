package com.zxo.platform.trace.support;

import com.zxo.platform.excption.CustomizeException;
import com.zxo.platform.sequence.Sequence;
import com.zxo.platform.trace.LogTraceIdGenerator;

/**
 * @ClassName: SequenceLogTraceIdGenerator
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 14:42
 * @Note:
 */
public class SequenceLogTraceIdGenerator implements LogTraceIdGenerator {
    private static final int DEFAULT_DATA_CENTER_ID = 1;
    private Sequence sequence;

    public SequenceLogTraceIdGenerator () {
        this(DEFAULT_DATA_CENTER_ID);
    }

    public SequenceLogTraceIdGenerator (int dataCenterId) {
        this.sequence = new Sequence(dataCenterId);
    }

    @Override
    public String createTraceId () throws CustomizeException {
        return String.valueOf(sequence.nextId());
    }
}
