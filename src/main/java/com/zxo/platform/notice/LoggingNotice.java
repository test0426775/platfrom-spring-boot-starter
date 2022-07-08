package com.zxo.platform.notice;

import com.zxo.platform.model.RequestLog;
import org.springframework.core.Ordered;

/**
 * @ClassName: LoggingNotice
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 15:41
 * @Note:
 */
public interface LoggingNotice extends Ordered {
    void notice (RequestLog minBoxLog);
}
