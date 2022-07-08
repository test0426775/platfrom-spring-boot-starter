package com.zxo.platform.util;

import org.springframework.util.AntPathMatcher;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: UrlUtils
 * @Author: zzzxxxooo
 * @Date: 2022/6/17 15:31
 * @Note:
 */
public class UrlUtils {

    public static boolean isIgnore (List<String> ignoreUrls, String uri) {
        Iterator iterator = ignoreUrls.iterator();

        boolean isMatcher;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            String ignoreUri = (String) iterator.next();
            AntPathMatcher matcher = new AntPathMatcher();
            isMatcher = matcher.match(ignoreUri, uri);
        } while (!isMatcher);

        return true;
    }
}
