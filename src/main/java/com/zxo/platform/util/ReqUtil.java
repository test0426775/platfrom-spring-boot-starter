package com.zxo.platform.util;

import com.zxo.platform.enums.LoggingConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @ClassName: ReqUtil
 * @Author: zzzxxxooo
 * @Date: 2022/6/17 13:37
 * @Note:
 */
public final class ReqUtil {

    public static String getTraceId () {
        HttpServletRequest request = getRequest();
        return (String) request.getAttribute(LoggingConstant.HEADER_NAME_SPAN_ID);
    }

    public static void setMethodDesc (String methodDesc) {
        HttpServletRequest request = getRequest();
        request.setAttribute(LoggingConstant.HEADER_NAME_METHOD_DESC, methodDesc);
    }

    public static HttpServletRequest getRequest () {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getRequest();
    }

    private static HashMap<String, String> getHeaderMap (HttpServletRequest request) {
        HashMap<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        return headerMap;
    }
}
