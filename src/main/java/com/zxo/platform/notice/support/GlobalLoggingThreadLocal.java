package com.zxo.platform.notice.support;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.zxo.platform.model.GlobalInfo;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: GlobalLoggingThreadLocal
 * @Author: zzzxxxooo
 * @Date: 2022/6/20 16:04
 * @Note:
 */
public class GlobalLoggingThreadLocal {
    // public static final TransmittableThreadLocal<String> GLOBAL_LOGS = new TransmittableThreadLocal();
    /**
     * GlobalLog {@link ThreadLocal} define
     */
    public static final TransmittableThreadLocal<List<GlobalInfo>> GLOBAL_LOGS = new TransmittableThreadLocal();


    public static List<GlobalInfo> getGlobalLogs () {
        if (ObjectUtils.isEmpty(GLOBAL_LOGS.get())) {
            return new ArrayList<>();
        }
        return GLOBAL_LOGS.get();
    }


    public static void addGlobalLogs (GlobalInfo globalInfo) {
        List<GlobalInfo> globalLogs = getGlobalLogs();
        if (ObjectUtils.isEmpty(globalLogs)) {
            globalLogs = new LinkedList();
        }
        globalLogs.add(globalInfo);
        GLOBAL_LOGS.set(globalLogs);
    }

    public static void remove () {
        GLOBAL_LOGS.remove();
    }
}
