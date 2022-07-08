package com.zxo.platform.configuration;

import com.zxo.platform.interceptor.SQLLogggerInterceptor;
import com.zxo.platform.properties.LoggingProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Mybatis SQL configuration add bean SQL Loggger Interceptor.
 *
 * @ClassName: SQLConfiguration
 * @Author: zzzxxxooo
 * @Date: 2022/6/21 17:12
 * @Note:
 */
@EnableConfigurationProperties({LoggingProperties.class})
@ConditionalOnClass({SqlSessionFactory.class})
public class SQLConfiguration {

    private final LoggingProperties loggingProperties;

    public SQLConfiguration (LoggingProperties loggingProperties) {
        this.loggingProperties = loggingProperties;
    }

    @Bean
    @ConditionalOnProperty(name = {"log.printQueryAndUpdateSql"}, havingValue = "true", matchIfMissing = true)
    public SQLLogggerInterceptor getLogSqlHandler () {
        if (loggingProperties.isShowSqlLog()) {
            return new SQLLogggerInterceptor(true);
        }
        return null;
    }
}
