package com.zxo.platform.notice;


import com.zxo.platform.model.RequestLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.OrderComparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: LoggingNoticeListener
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 15:39
 * @Note:
 */
@AutoConfiguration
public class LoggingNoticeListener implements SmartApplicationListener, ApplicationContextAware {
    public static final String BEAN_NAME = "loggingNoticeListener";
    static Logger logger = LoggerFactory.getLogger(LoggingNoticeListener.class);
    private ApplicationContext applicationContext;

    @Override
    public boolean supportsEventType (Class<? extends ApplicationEvent> eventType) {
        return eventType == LoggingNoticeEvent.class;
    }

    @Override
    @Async
    public void onApplicationEvent (ApplicationEvent event) {
        LoggingNoticeEvent loggingNoticeEvent = (LoggingNoticeEvent) event;
        RequestLog requestLog = loggingNoticeEvent.getLog();
        Map<String, LoggingNotice> noticeMap = applicationContext.getBeansOfType(LoggingNotice.class);
        if (ObjectUtils.isEmpty(noticeMap)) {
            logger.warn("Don't found LoggingNotice support instance list.");
            return;
        }
        List<LoggingNotice> noticeList = new ArrayList<>(noticeMap.values());
        OrderComparator.sort(noticeList);
        noticeList.forEach(loggingNotice -> loggingNotice.notice(requestLog));
    }

    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "ApplicationContext must not be null");
        this.applicationContext = applicationContext;

    }

    @Override
    public int getOrder () {
        return Integer.MIN_VALUE;
    }
}
