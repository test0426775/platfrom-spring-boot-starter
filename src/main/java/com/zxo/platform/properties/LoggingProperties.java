package com.zxo.platform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName: LoggingProperties
 * @Author: zzzxxxooo
 * @Date: 2022/6/15 9:58
 * @Note:
 */
@Data
@EnableConfigurationProperties(value = LoggingProperties.class)
@ConfigurationProperties(prefix = "platform.logging")
public class LoggingProperties {
    private boolean enable = true;

    private String basePackage;

    private boolean formatConsoleLog;

    private boolean showConsoleLog = true;

    private boolean showTestLog = false;

    private boolean showSqlLog = false;

    private String[] ignorePaths;
}
