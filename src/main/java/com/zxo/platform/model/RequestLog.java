package com.zxo.platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RequestLog
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 13:11
 * @Note:
 */
@Data
public class RequestLog {
    /**
     * the formatted service name
     */
    private String serviceName;
    /**
     * trace id
     */
    private String traceId;
    /**
     * parent span id
     */
    private String parentTraceId;
    /**
     * request uri
     */
    private String requestUri;
    /**
     * request method
     */
    private String requestMethod;
    /**
     * http status code
     */
    private int httpStatus;
    /**
     * request ip
     */
    private String requestIp;
    /**
     * service ip address
     */
    private String serviceIp;
    /**
     * service port
     */
    private String servicePort;
    /**
     * start time
     */
    private Long startTime;

    /**
     * end time
     */
    private Long endTime;

    /**
     * this request time consuming
     */
    @JsonIgnore
    private long timeConsuming;
    @JsonProperty(value = "time_consuming")
    private String timeConsumingString;
    /**
     * request headers json value
     */
    private String requestHeader;
    /**
     * request param
     */
    private String requestParam;
    /**
     * request body
     */
    private String requestBody;
    /**
     * response headers json value
     */
    private String responseHeader;
    /**
     * response body
     */
    private String responseBody;

    private String methodDesc;
    /**
     * exception stack
     */
    @JsonProperty(value = "exception_stack")
    private String exceptionStack;

    private List<GlobalInfo> globalInfoList;
//    /**
//     * Global method log list
//     */
//    private List<GlobalLog> globalLogs;
    /**
     * The request logs create time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime = LocalDateTime.now();   // Obtains the current date-time from the system clock in the default time-zone.

    public void setTimeConsumingString () {
        if (this.timeConsuming >= 1000 && this.timeConsuming < 60000) {   // this request time consuming
            this.timeConsumingString = String.format("%.2f", (float) timeConsuming / 1000) + "秒";   // Returns a formatted string using the specified format string and arguments.
        } else if (timeConsuming >= 60000 && timeConsuming < 3600000) {   // this request time consuming
            this.timeConsumingString = String.format("%.2f", (float) timeConsuming / 60000) + "分钟";   // Returns a formatted string using the specified format string and arguments.
        } else if (timeConsuming > 3600000) {   // this request time consuming
            this.timeConsumingString = String.format("%.2f", (float) timeConsuming / 3600000) + "小时";   // Returns a formatted string using the specified format string and arguments.
        } else {
            this.timeConsumingString = timeConsuming + "毫秒";   // this request time consuming
        }
    }

    public void setExceptionStack (String exceptionStack) {
        this.exceptionStack = exceptionStack;   // exception stack
    }

    public void setExceptionStack (Map<String, List<GlobalInfo>> globalInfo) {
        if (globalInfo.containsKey("exception")) {   // Returns } if this map contains a mapping for the specified key.  More formally, returns } if and only if
            this.exceptionStack = globalInfo.get("exception").get(0).getErrorMessage();   // Returns the element at the specified position in this list.
        }
    }

    public void setGlobalInfoList (Map<String, List<GlobalInfo>> globalInfo) {
        if (globalInfo.containsKey("sql")) {   // Returns } if this map contains a mapping for the specified key.  More formally, returns } if and only if
            this.globalInfoList = globalInfo.get("sql");   // Returns the value to which the specified key is mapped, or } if this map contains no mapping for the key.
        }
    }
}

