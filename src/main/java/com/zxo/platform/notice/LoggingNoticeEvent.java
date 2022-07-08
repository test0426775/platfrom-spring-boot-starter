package com.zxo.platform.notice;

import com.zxo.platform.model.RequestLog;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName: LoggingNoticeEvent
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 15:36
 * @Note:
 */
@Getter
public class LoggingNoticeEvent extends ApplicationEvent {
    /**
     * ApiBoot Logging Object
     */
    private RequestLog log;

    public LoggingNoticeEvent (Object source, RequestLog log) {
        super(source);
        this.log = log;
    }
}
