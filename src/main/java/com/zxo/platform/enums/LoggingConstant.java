package com.zxo.platform.enums;

public interface LoggingConstant {
    /**
     * TraceId Header Name
     */
    String HEADER_NAME_TRACE_ID = "ZXO-LOGGING-X-TRACE-ID";
    /**
     * Parent SpanId Header Name
     */
    String HEADER_NAME_SPAN_ID = "ZXO-LOGGING-X-SPAN-ID";

    String HEADER_NAME_METHOD_DESC = "ZXO-LOGGING-X-METHOD-DESC";

    String JOIN = "|";
}
