package com.zxo.platform.configuration;

import com.zxo.platform.core.LoggingFactoryBean;
import com.zxo.platform.interceptor.support.LoggingWebInterceptor;
import com.zxo.platform.properties.LoggingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebConfiguration
 * @Author: zzzxxxooo
 * @Date: 2022/6/15 15:22
 * @Note:
 */
@EnableConfigurationProperties({LoggingProperties.class})
public class WebConfiguration implements WebMvcConfigurer {

    private final LoggingProperties loggingProperties;
    @Autowired
    private LoggingFactoryBean loggingFactoryBean;

    public WebConfiguration (LoggingProperties loggingProperties) {
        this.loggingProperties = loggingProperties;
    }

    public void addInterceptors (InterceptorRegistry registry) {
        if (this.loggingProperties.isEnable()) {
            loggingFactoryBean.setFormatConsoleLog(this.loggingProperties.isFormatConsoleLog());
            loggingFactoryBean.setShowConsoleLog(this.loggingProperties.isShowConsoleLog());
            loggingFactoryBean.setIgnorePaths(this.loggingProperties.getIgnorePaths());
            loggingFactoryBean.setShowTestLog(this.loggingProperties.isShowTestLog());
            loggingFactoryBean.setShowSqlLog(this.loggingProperties.isShowSqlLog());

            registry.addInterceptor(new LoggingWebInterceptor(loggingFactoryBean))
                    .addPathPatterns("/**")
                    .excludePathPatterns("/error/**")
                    .excludePathPatterns("/swagger-resources/**")
                    .excludePathPatterns("/v2/api-docs/**");
        }
    }

    @Bean
    public String basePackage () {
        return loggingProperties.getBasePackage();
    }
}
