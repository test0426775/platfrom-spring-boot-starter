package com.zxo.platform.filter;

import com.zxo.platform.util.HttpRequestUtil;
import com.zxo.platform.wrapper.RequestWrapper;
import com.zxo.platform.wrapper.ResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: LoggingBodyFilter
 * @Author: zzzxxxooo
 * @Date: 2022/6/20 15:44
 * @Note:
 */
@WebFilter(urlPatterns = "/*", filterName = "loggingBodyFilter")
public class LoggingBodyFilter implements Filter {
    /**
     * the bean name of {@link LoggingBodyFilter}
     */
    public static final String BEAN_NAME = "loggingBodyFilter";

    /**
     * Wrapper Body
     * replace http request body instance
     * replace http response body instance
     *
     * @param request     http request
     * @param response    http response
     * @param filterChain filter chain
     * @throws IOException      ioException
     * @throws ServletException servlet Exception
     */
    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!HttpRequestUtil.isMultipart(request)) {
            RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
            ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
            filterChain.doFilter(requestWrapper, responseWrapper);
            responseWrapper.flushBuffer();
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
