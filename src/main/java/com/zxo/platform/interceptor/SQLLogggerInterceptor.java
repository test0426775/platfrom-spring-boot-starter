package com.zxo.platform.interceptor;

import com.zxo.platform.notice.support.SQLLoggingNotice;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @ClassName: SQLLogggerInterceptor
 * @Author: zzzxxxooo
 * @Date: 2022/6/21 17:03
 * @Note:
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class SQLLogggerInterceptor implements Interceptor {
    private int slowSqlThreshold;
    private boolean isOptimizeSql;

    public SQLLogggerInterceptor () {
    }

    public SQLLogggerInterceptor (int slowSqlThreshold) {
        this.slowSqlThreshold = slowSqlThreshold;
    }

    public SQLLogggerInterceptor (boolean isOptimizeSql) {
        this.isOptimizeSql = isOptimizeSql;
    }

    public SQLLogggerInterceptor (int slowSqlThreshold, boolean isOptimizeSql) {
        this.slowSqlThreshold = slowSqlThreshold;
        this.isOptimizeSql = isOptimizeSql;
    }

    @Override
    public Object intercept (Invocation invocation) throws Throwable {
        return SQLLoggingNotice.intercept(invocation, this.slowSqlThreshold, this.isOptimizeSql);
    }

    @Override
    public Object plugin (Object target) {
        return target instanceof Executor ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties (Properties properties) {
    }
}
