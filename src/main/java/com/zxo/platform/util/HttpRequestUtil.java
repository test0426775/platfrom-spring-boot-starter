package com.zxo.platform.util;


import com.zxo.platform.wrapper.RequestWrapper;
import com.zxo.platform.wrapper.ResponseWrapper;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Http Request Util
 *
 * @ClassName: HttpRequestUtil
 * @Author: zzzxxxooo
 * @Date: 2022/6/10 16:07
 * @Note:
 */
public class HttpRequestUtil {
    public static boolean isMultipart (ServletRequest request) {
        String contentType = request.getContentType();
        return contentType != null && contentType.toLowerCase().startsWith("multipart/");
    }

    /**
     * get request ip address
     *
     * @param request http request instance
     * @return ip address
     */
    public static String getIp (HttpServletRequest request) {
        Assert.notNull(request, "request instance is null.");
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.hasLength(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            int index = XFor.indexOf(",");
            if (index != -1) {
                return XFor.substring(0, index);
            } else {
                return XFor;
            }
        }
        XFor = Xip;
        if (StringUtils.hasLength(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            return XFor;
        }
        if (!StringUtils.hasLength(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasLength(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasLength(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (!StringUtils.hasLength(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!StringUtils.hasLength(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

    /**
     * get all request header
     *
     * @param request http request instance
     * @return map
     */
    public static Map<String, String> getRequestHeaders (HttpServletRequest request) {
        Assert.notNull(request, "request instance is null.");
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String headerName = enumeration.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        return headers;
    }

    /**
     * get all response header
     *
     * @param response http response instance
     * @return map
     */
    public static Map<String, String> getResponseHeaders (HttpServletResponse response) {
        Assert.notNull(response, "response instance is null.");
        Map<String, String> headers = new HashMap<>();
        for (String headerName : response.getHeaderNames()) {
            String headerValue = response.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        return headers;
    }

    /**
     * get request header by name
     *
     * @param request    http request instance
     * @param headerName header name
     * @return header value
     */
    public static String getHeader (HttpServletRequest request, String headerName) {
        Assert.notNull(request, "request instance is null.");
        Assert.notNull(headerName, "request header name is null.");
        return request.getHeader(headerName);
    }

    /**
     * get request body content
     *
     * @param request http request instance
     * @return request body content
     */
    public static String getRequestBody (HttpServletRequest request) {
        Assert.notNull(request, "request instance is null.");
        RequestWrapper requestWrapper;
        if (request instanceof RequestWrapper) {
            requestWrapper = (RequestWrapper) request;
        } else {
            requestWrapper = new RequestWrapper(request);
        }
        return requestWrapper.getBody();
    }

    /**
     * get response body content
     *
     * @param response http response instance
     * @return response body content
     */
    public static String getResponseBody (HttpServletResponse response) throws IOException {
        if (response instanceof ResponseWrapper responseWrapper) {
            byte[] copy = responseWrapper.getCopy();
            return new String(copy, ResponseWrapper.DEFAULT_CHARACTER_ENCODING);
        } else {
            ResponseWrapper responseWrapper = new ResponseWrapper(response);
            return new String(responseWrapper.getCopy(), ResponseWrapper.DEFAULT_CHARACTER_ENCODING);
        }
    }

    /**
     * get request path param
     *
     * @param request http request instance
     * @return path param
     */
    public static Map<String, String> getPathParams (HttpServletRequest request) {
        Assert.notNull(request, "request instance is null.");
        Map<String, String> map = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

    /**
     * get request uri
     *
     * @param request http request instance
     * @return request uri
     */
    public static String getUri (HttpServletRequest request) {
        Assert.notNull(request, "request instance is null.");
        return request.getRequestURI();
    }
}