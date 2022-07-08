package com.zxo.platform.interceptor.support;

import com.zxo.platform.core.LogThreadLocal;
import com.zxo.platform.core.LoggingFactoryBean;
import com.zxo.platform.enums.LoggingConstant;
import com.zxo.platform.interceptor.LoggingAbstractInterceptor;
import com.zxo.platform.model.GlobalInfo;
import com.zxo.platform.model.RequestLog;
import com.zxo.platform.notice.LoggingNoticeEvent;
import com.zxo.platform.notice.support.GlobalLoggingThreadLocal;
import com.zxo.platform.util.HttpRequestUtil;
import com.zxo.platform.util.JsonUtils;
import com.zxo.platform.util.StackTraceUtil;
import com.zxo.platform.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: LoggingWebInterceptor
 * @Author: zzzxxxooo
 * @Date: 2022/6/15 15:37
 * @Note:
 */
public class LoggingWebInterceptor extends LoggingAbstractInterceptor implements HandlerInterceptor {
    static Logger logger = LoggerFactory.getLogger(LoggingWebInterceptor.class);

    private final LoggingFactoryBean factoryBean;

    public LoggingWebInterceptor (LoggingFactoryBean factoryBean) {
        this.setFactoryBean(factoryBean);
        this.factoryBean = factoryBean;
    }

    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestLog log = new RequestLog();
        if (ObjectUtils.isEmpty(log) || this.checkIgnore(HttpRequestUtil.getUri(request))) {
            return true;
        }

        try {
            log.setRequestIp(HttpRequestUtil.getIp(request));
            log.setRequestUri(HttpRequestUtil.getUri(request));
            log.setRequestMethod(request.getMethod());
            Map requestParams = HttpRequestUtil.getPathParams(request);
            log.setRequestParam(!ObjectUtils.isEmpty(requestParams) ? JsonUtils.toJsonString(requestParams) : null);
            if (!HttpRequestUtil.isMultipart(request)) {
                String requestBody = HttpRequestUtil.getRequestBody(request);
                log.setRequestBody(!ObjectUtils.isEmpty(requestBody) ? requestBody : null);
            }
            log.setRequestHeader(JsonUtils.toJsonString(HttpRequestUtil.getRequestHeaders(request)));
            log.setHttpStatus(response.getStatus());
            log.setStartTime(System.currentTimeMillis());
            log.setServiceName(factoryBean.getServiceName());
            log.setServicePort(String.valueOf(factoryBean.getServicePort()));
            InetAddress inetAddress = InetAddress.getLocalHost();
            log.setServiceIp(inetAddress.getHostAddress());
            log.setParentTraceId(extractTraceId(request));
            String traceId = getOrCreateTraceId(request);
            log.setTraceId(traceId);
            request.setAttribute(LoggingConstant.HEADER_NAME_SPAN_ID, traceId);
        } catch (Exception e) {
            log.setExceptionStack(StackTraceUtil.getStackTrace(e));
        } finally {
            LogThreadLocal.set(log);
        }
        return true;
    }

    @Override
    public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            RequestLog log = LogThreadLocal.get();

            if (ObjectUtils.isEmpty(log) || this.checkIgnore(HttpRequestUtil.getUri(request))) {
                return;
            }
            Map<String, List<GlobalInfo>> globalInfo = GlobalLoggingThreadLocal.getGlobalLogs()
                    .stream().collect(Collectors.groupingBy(GlobalInfo::getTag));

            log.setHttpStatus(response.getStatus());
            // set exception stack
            if (!ObjectUtils.isEmpty(ex)) {
                logger.debug("Request Have Exception，Execute Update HttpStatus.");
                log.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                log.setExceptionStack(globalInfo);
            }
            if (!this.checkIgnoreHttpStatus(log.getHttpStatus())) {

                log.setMethodDesc((String) request.getAttribute(LoggingConstant.HEADER_NAME_METHOD_DESC));
                log.setEndTime(System.currentTimeMillis());
                log.setTimeConsuming(log.getEndTime() - log.getStartTime());
                log.setTimeConsumingString();
                Map responseHeaders = HttpRequestUtil.getResponseHeaders(response);
                log.setResponseHeader(!ObjectUtils.isEmpty(responseHeaders) ? JsonUtils.toJsonString(responseHeaders) : null);
                log.setResponseBody(HttpRequestUtil.getResponseBody(response));
                log.setExceptionStack(globalInfo);
                log.setGlobalInfoList(globalInfo);
                factoryBean.getApplicationContext().publishEvent(new LoggingNoticeEvent(this, log));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            LogThreadLocal.remove();
            GlobalLoggingThreadLocal.GLOBAL_LOGS.remove();
        }
    }

    private String getOrCreateTraceId (HttpServletRequest request) {
        // get traceId from request header
        String traceId = extractTraceId(request);
        // if request header don't have traceId create new traceId
        if (ObjectUtils.isEmpty(traceId)) {
            logger.debug("Request Header Don't Have TraceId，Create New TraceId Now.");
            traceId = factoryBean.getTraceGenerator().createTraceId();
        }
        logger.debug("Request TraceId：{}", traceId);
        return traceId;
    }

    private String extractTraceId (HttpServletRequest request) {
        // get traceId from request header
        String traceId = HttpRequestUtil.getHeader(request, LoggingConstant.HEADER_NAME_TRACE_ID);
        return traceId;
    }

    private boolean checkIgnoreHttpStatus (int httpStatusCode) {
        HttpStatus httpStatus = HttpStatus.valueOf(httpStatusCode);
        List<HttpStatus> ignoreHttpStatus = factoryBean.getIgnoreHttpStatus();
        return ignoreHttpStatus.contains(httpStatus);
    }

    private boolean checkIgnore (String uri) {
        return UrlUtils.isIgnore(factoryBean.getIgnorePaths(), uri);
    }
}
