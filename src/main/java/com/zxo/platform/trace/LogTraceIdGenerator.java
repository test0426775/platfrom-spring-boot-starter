package com.zxo.platform.trace;

import com.zxo.platform.excption.CustomizeException;

/**
 * @ClassName: LogTraceIdGenerator
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 14:42
 * @Note:
 */

public interface LogTraceIdGenerator {

    String createTraceId () throws CustomizeException;
}
