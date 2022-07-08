package com.zxo.platform.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxo.platform.model.RequestLog;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * Json Util
 *
 * @ClassName: JsonUtils
 * @Author: zzzxxxooo
 * @Date: 2022/6/10 16:07
 * @Note:
 */
public class JsonUtils {
    public JsonUtils () {
    }

    public static String beautifyJson (Object object) {
        Assert.notNull(object, "The wait beautify object cannot be null.");

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            throw new IllegalArgumentException("Unable to format json.");
        }
    }

    public static String beautifyJson (String json) {
        Assert.notNull(json, "The wait beautify json string cannot be null.");

        try {
            ObjectMapper mapper = new ObjectMapper();
            Object object = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            throw new IllegalArgumentException("Unable to format json.");
        }
    }

    public static String toJsonString (Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            throw new IllegalArgumentException("Unable to format json.");
        }
    }

    public static String fromJsonString (RequestLog requestLog, boolean flag, boolean sqlFlag) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append("-------------------- Basic Information --------------------\n");
        if (!ObjectUtils.isEmpty(requestLog.getServiceName())) {
            stringBuffer.append("Service Name    => ").append(requestLog.getServiceName()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getParentTraceId())) {
            stringBuffer.append("parentTraceId   => ").append(requestLog.getParentTraceId()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getTraceId())) {
            stringBuffer.append("traceId         => ").append(requestLog.getTraceId()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getMethodDesc())) {
            stringBuffer.append("methodCaption   => ").append(requestLog.getMethodDesc()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getRequestUri())) {
            stringBuffer.append("requestUri      => ").append(requestLog.getRequestUri()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getRequestMethod())) {
            stringBuffer.append("requestMethod   => ").append(requestLog.getRequestMethod()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getServiceIp())) {
            stringBuffer.append("serviceIp       => ").append(requestLog.getServiceIp()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getServicePort())) {
            stringBuffer.append("servicePort     => ").append(requestLog.getServicePort()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getHttpStatus())) {
            stringBuffer.append("httpStatus      => ").append(requestLog.getHttpStatus()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getStartTime())) {
            stringBuffer.append("startTime       => ").append(requestLog.getStartTime()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getEndTime())) {
            stringBuffer.append("endTime         => ").append(requestLog.getEndTime()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getTimeConsumingString())) {
            stringBuffer.append("timeConsuming   => ").append(requestLog.getTimeConsumingString()).append("\n");
        }
        stringBuffer.append("-------------------- Request Information --------------------\n");
        if (!ObjectUtils.isEmpty(requestLog.getRequestIp())) {
            stringBuffer.append("requestIp       => ").append(requestLog.getRequestIp()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getRequestHeader())) {
            stringBuffer.append("requestHeader   => ").append(beautifyJson(requestLog.getRequestHeader())).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getRequestParam())) {
            stringBuffer.append("requestParam    => ").append(requestLog.getRequestParam()).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getRequestBody())) {
            stringBuffer.append("requestBody     => ").append(requestLog.getRequestBody()).append("\n");
        }
        stringBuffer.append("-------------------- Response Information --------------------\n");
        if (!ObjectUtils.isEmpty(requestLog.getResponseHeader())) {
            stringBuffer.append("responseHeader  => ").append(beautifyJson(requestLog.getResponseHeader())).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getResponseBody())) {
            stringBuffer.append("responseBody    => ").append(beautifyJson(requestLog.getResponseBody())).append("\n");
        }
        if (!ObjectUtils.isEmpty(requestLog.getExceptionStack())) {
            stringBuffer.append("exceptionStack  => ").append(requestLog.getExceptionStack()).append("\n");
        }
        if (sqlFlag) {
            if (!ObjectUtils.isEmpty(requestLog.getGlobalInfoList())) {
                stringBuffer.append("-------------------- SQL Information --------------------\n");
                for (int i = 0; i < requestLog.getGlobalInfoList().size(); i++) {
                    stringBuffer.append("  No.             => ").append(i + 1).append("\n");
                    stringBuffer.append("  spendTime       => ").append(requestLog.getGlobalInfoList().get(i).getSpendTime()).append("\n");
                    stringBuffer.append("  affectedRows    => ").append(requestLog.getGlobalInfoList().get(i).getAffectedRows()).append("\n");
                    stringBuffer.append("  SQLString       => ").append(requestLog.getGlobalInfoList().get(i).getSqlString()).append("\n");
                    if (i + 1 != requestLog.getGlobalInfoList().size()) {
                        stringBuffer.append("  **************************************************\n");
                    }
                }
            }
        }
        if (flag) {
            stringBuffer.append("-------------------- Idea HttpClient Test Information --------------------\n");
            stringBuffer.append("### \n");
            if (!ObjectUtils.isEmpty(requestLog.getRequestParam())) {
                StringBuffer sb = new StringBuffer();
                String[] paramList = requestLog.getRequestParam().replace("{", "").replace("}", "").split(",");
                for (String params : paramList) {
                    String[] param = params.split(":");
                    sb.append(param[0]).append("=").append(param[1]).append("&");
                }

                String requestUrl = "http://" + requestLog.getServiceIp() + ":" + requestLog.getServicePort() + requestLog.getRequestUri() + "?" + sb.toString().replace("\"", "");
                stringBuffer.append(requestUrl).append("\n");
                stringBuffer.append("Content-Type: application/json \n");
            } else if (!ObjectUtils.isEmpty(requestLog.getRequestBody())) {
                String requestUrl = "http://" + requestLog.getServiceIp() + ":" + requestLog.getServicePort() + requestLog.getRequestUri();
                stringBuffer.append(requestUrl).append("\n");
                stringBuffer.append("Content-Type: application/json \n\n");
                stringBuffer.append(beautifyJson(requestLog.getRequestBody()));
            } else {
                String requestUrl = "http://" + requestLog.getServiceIp() + ":" + requestLog.getServicePort() + requestLog.getRequestUri();
                stringBuffer.append(requestUrl).append("\n");
                stringBuffer.append("Content-Type: application/json \n\n");
            }
        }
        return stringBuffer.toString();
    }

    public static <T> T fromJsonString (String json, Class<T> targetClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, targetClass);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            throw new IllegalArgumentException("Unable to format json.");
        }
    }
}