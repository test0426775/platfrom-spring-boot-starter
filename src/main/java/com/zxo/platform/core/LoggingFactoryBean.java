package com.zxo.platform.core;


import com.zxo.platform.trace.LogTraceIdGenerator;
import com.zxo.platform.trace.support.SequenceLogTraceIdGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: LoggingFactoryBean
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 13:27
 * @Note:
 */
@Configuration
public class LoggingFactoryBean implements EnvironmentAware, InitializingBean, ApplicationContextAware {
    private final ArrayList<String> ignorePaths = new ArrayList<String>() {
        {
            add("/error");
        }
    };
    @Nullable
    private Environment environment;
    private ApplicationContext applicationContext;
    private LogTraceIdGenerator traceGenerator;
    //    {
//        {
//            add(HttpStatus.NOT_FOUND);
//        }
//    };
    private List<HttpStatus> ignoreHttpStatus = new ArrayList();
    private String serviceName;
    private Integer servicePort;
    private String serviceAddress;
    private RestTemplate restTemplate;
    private boolean showConsoleLog;
    private boolean formatConsoleLog;

    private boolean showTestLog;

    private boolean showSqlLog;

    public LoggingFactoryBean () {
        this.traceGenerator = new SequenceLogTraceIdGenerator();
//        this.setFormatConsoleLog(loggingProperties.isFormatConsoleLog());
    }

    @Override
    public void afterPropertiesSet () throws Exception {
        this.serviceName = environment.getProperty("spring.application.name");

        Assert.notNull(this.serviceName, "Please add the 【spring.application.name】 configuration in the application.yml or application.properties");
        String serverPort = environment.getProperty("server.port");
        Assert.notNull(serverPort, "Please add the 【server.port】 configuration in the application.yml or application.properties");
        this.servicePort = Integer.valueOf(serverPort);
        InetAddress inetAddress = InetAddress.getLocalHost();
        this.serviceAddress = inetAddress.getHostAddress();
        this.restTemplate = new RestTemplate();
//        this.restTemplate.setInterceptors(Arrays.asList(new LoggingRestTemplateInterceptor()));
//        this.loggingAdminReport = new LoggingAdminReportSupport(this);
    }

    public LogTraceIdGenerator getTraceGenerator () {
        return traceGenerator;
    }

    public void setTraceGenerator (LogTraceIdGenerator traceGenerator) {
        this.traceGenerator = traceGenerator;
    }

    public String getServiceName () {
        return serviceName;
    }

    public void setServiceName (String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServicePort () {
        return servicePort;
    }

    public void setServicePort (Integer servicePort) {
        this.servicePort = servicePort;
    }

    public String getServiceAddress () {
        return serviceAddress;
    }

    public void setServiceAddress (String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public RestTemplate getRestTemplate () {
        return restTemplate;
    }

    public void setRestTemplate (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void setEnvironment (Environment environment) {
        Assert.notNull(environment, "Environment must not be null");
        this.environment = environment;
    }

    public List<HttpStatus> getIgnoreHttpStatus () {
        return ignoreHttpStatus;
    }

    public void setIgnoreHttpStatus (List<HttpStatus> ignoreHttpStatus) {
        this.ignoreHttpStatus = ignoreHttpStatus;
    }

    public ApplicationContext getApplicationContext () {
        return applicationContext;
    }

    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "ApplicationContext must not be null");
        this.applicationContext = applicationContext;
    }

    public boolean isShowConsoleLog () {
        return showConsoleLog;
    }

    public void setShowConsoleLog (boolean showConsoleLog) {
        this.showConsoleLog = showConsoleLog;
    }

    public boolean isFormatConsoleLog () {
        return formatConsoleLog;
    }

    public void setFormatConsoleLog (boolean formatConsoleLog) {
        this.formatConsoleLog = formatConsoleLog;
    }

    public List<String> getIgnorePaths () {
        return ignorePaths;
    }

    public void setIgnorePaths (String[] ignorePaths) {
        if (!ObjectUtils.isEmpty(ignorePaths)) {
            this.ignorePaths.addAll(Arrays.asList(ignorePaths));
        }
    }

    public boolean isShowTestLog () {
        return showTestLog;
    }

    public void setShowTestLog (boolean showTestLog) {
        this.showTestLog = showTestLog;
    }

    public boolean isShowSqlLog () {
        return showSqlLog;
    }

    public void setShowSqlLog (boolean showSqlLog) {
        this.showSqlLog = showSqlLog;
    }
}
