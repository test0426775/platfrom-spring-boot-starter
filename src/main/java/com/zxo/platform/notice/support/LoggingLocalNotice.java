package com.zxo.platform.notice.support;

import com.zxo.platform.core.LoggingFactoryBean;
import com.zxo.platform.model.RequestLog;
import com.zxo.platform.notice.LoggingNotice;
import com.zxo.platform.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName: LoggingLocalNotice
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 15:43
 * @Note:
 */
@AutoConfiguration
public class LoggingLocalNotice implements LoggingNotice {
    /**
     * the bean name of {@link LoggingLocalNotice}
     */
    public static final String BEAN_NAME = "loggingLocalNotice";
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(LoggingLocalNotice.class);
    /**
     * Logging factory bean {@link LoggingFactoryBean}
     */
    private final LoggingFactoryBean loggingFactoryBean;

    public LoggingLocalNotice (LoggingFactoryBean loggingFactoryBean) {
        this.loggingFactoryBean = loggingFactoryBean;
    }

    /**
     * Output formatted log information according to configuration in console
     * {@link LoggingNotice}
     *
     * @param requestLog ApiBoot Log
     */
    @Override
    public void notice (RequestLog requestLog) {
        if (loggingFactoryBean.isShowConsoleLog()) {
            this.extractLogError(requestLog, loggingFactoryBean.isFormatConsoleLog());
        }
    }

    private void extractLogError (RequestLog requestLog, boolean flag) {
        if (ObjectUtils.isEmpty(requestLog.getExceptionStack())) {
            if (flag) {
                logger.info("Request Log Report，Logging Content：{}", JsonUtils.fromJsonString(requestLog, loggingFactoryBean.isShowTestLog(), loggingFactoryBean.isShowSqlLog()));
            } else {
                logger.info("Request Log Report，Logging Content：{}", JsonUtils.beautifyJson(requestLog));
            }
        } else {
            if (flag) {
                logger.error("Request Log Report，Logging Content：{}", JsonUtils.fromJsonString(requestLog, loggingFactoryBean.isShowTestLog(), loggingFactoryBean.isShowSqlLog()));
            } else {
                logger.error("Request Log Report，Logging Content：{}", JsonUtils.toJsonString(requestLog));
            }
        }
    }

    @Override
    public int getOrder () {
        return HIGHEST_PRECEDENCE;
    }
}
