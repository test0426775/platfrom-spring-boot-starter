package com.zxo.platform.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName: StackTraceUtil
 * @Author: zzzxxxooo
 * @Date: 2022/6/13 15:20
 * @Note:
 */
public class StackTraceUtil {
    public static String getStackTrace (Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        String massage = null;
        try {
            throwable.printStackTrace(pw);
            massage = sw.toString();
        } finally {
            pw.close();
        }

        return massage;
    }
}
