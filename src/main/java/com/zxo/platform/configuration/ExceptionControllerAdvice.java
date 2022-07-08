package com.zxo.platform.configuration;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.text.StrSplitter;
import com.zxo.platform.enums.BaseCode;
import com.zxo.platform.excption.CustomizeException;
import com.zxo.platform.model.GlobalInfo;
import com.zxo.platform.model.ResponseVo;
import com.zxo.platform.notice.support.GlobalLoggingThreadLocal;
import com.zxo.platform.util.StackTraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.UnexpectedTypeException;
import java.util.List;

/**
 * @ClassName: ExceptionControllerAdvice
 * @Author: zzzxxxooo
 * @Date: 2022/6/20 10:49
 * @Note: 继承
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    static Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    private ResponseVo<String> getErrorInfo (String code, String message) {
        if (ObjectUtils.isEmpty(code)) {
            return new ResponseVo<>(BaseCode.UNDEFINED_ERROR.getCode(), message);
        }
        return new ResponseVo<>(code, message);
    }

    /**
     * 处理自定义异常.
     *
     * @return 自定义异常
     */
    @ExceptionHandler(CustomizeException.class)
    public ResponseVo<String> apiExceptionHandler (CustomizeException ex) {
        StackTraceUtil.getStackTrace(ex);
        return this.getErrorInfo(ex.getCode(), ex.getMsg());
    }

    /**
     * 处理实体校验异常.
     *
     * @return 实体校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo<String> methodArgumentNotValidExceptionHandler (MethodArgumentNotValidException ex) {
        // 从异常对象中拿到ObjectError对象
        String objectError = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        if (ObjectUtils.isEmpty(objectError)) {
            return this.getErrorInfo(BaseCode.CHECK_FAIL.getCode(), BaseCode.CHECK_FAIL.getMessage());
        }
        List<String> errorList = StrSplitter.split(objectError, "|", true, true);
        if (errorList.size() == 2) {
            return this.getErrorInfo(errorList.get(0), errorList.get(1));
        }
        return this.getErrorInfo(BaseCode.CHECK_FAIL.getCode(), BaseCode.CHECK_FAIL.getMessage());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseVo<String> unexpectedTypeException (UnexpectedTypeException ex) {
        GlobalLoggingThreadLocal.GLOBAL_LOGS.set(List.of(new GlobalInfo(StackTraceUtil.getStackTrace(ex), "exception")));
        return new ResponseVo<>(BaseCode.ONLY_STRING);
    }

    /**
     * 处理404的异常.
     * methodArgumentNotValidExceptionHandler
     *
     * @return 错误信息
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseVo<String> noHandlerFoundException (NoHandlerFoundException ex) {
        GlobalLoggingThreadLocal.GLOBAL_LOGS.set(List.of(new GlobalInfo(StackTraceUtil.getStackTrace(ex), "exception")));
        return new ResponseVo<>(BaseCode.PAGE_NOT_FOUND);
    }

    /**
     * 处理请求方法错误的异常.
     *
     * @return 错误信息
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVo<String> httpRequestMethodNotSupportedException (HttpRequestMethodNotSupportedException ex) {
        logger.error(StackTraceUtil.getStackTrace(ex));
        String errorInfo = StrFormatter.format("不支持请求方法{}, 支持的方法有 {}", ex.getMethod(), ex.getSupportedMethods());
        return new ResponseVo<>(BaseCode.REQUEST_METHOD_ERROR.getCode(), errorInfo);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseVo<String> invalidDataAccessApiUsageException (InvalidDataAccessApiUsageException ex) {
        GlobalLoggingThreadLocal.GLOBAL_LOGS.set(List.of(new GlobalInfo(StackTraceUtil.getStackTrace(ex), "exception")));
        return new ResponseVo<>(BaseCode.ARGS_ERROR.getCode(), ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseVo<String> httpMessageNotReadableException (HttpMessageNotReadableException ex) {
        GlobalLoggingThreadLocal.GLOBAL_LOGS.set(List.of(new GlobalInfo(StackTraceUtil.getStackTrace(ex), "exception")));
        return new ResponseVo<>(BaseCode.ARGS_ERROR, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseVo<String> methodArgumentTypeMismatchException (MethodArgumentTypeMismatchException ex) {
        GlobalLoggingThreadLocal.GLOBAL_LOGS.set(List.of(new GlobalInfo(StackTraceUtil.getStackTrace(ex), "exception")));
        String errorInfo = StrFormatter.format("请求参数【{}】的类型错误，需要的类型是 {}",
                ex.getName(), ex.getRequiredType());
        return new ResponseVo<>(BaseCode.ARGS_ERROR.getCode(), errorInfo);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseVo<String> missingServletRequestParameter (MissingServletRequestParameterException ex) {
        GlobalLoggingThreadLocal.GLOBAL_LOGS.set(List.of(new GlobalInfo(StackTraceUtil.getStackTrace(ex), "exception")));
        String errorInfo = StrFormatter.format("缺少请求参数。参数名称:{}，参数类型:{}",
                ex.getParameterName(), ex.getParameterType());
        return new ResponseVo<>(BaseCode.ARGS_ERROR.getCode(), errorInfo);
    }

    /**
     * 未知错误的异常.
     *
     * @return 未知错误
     */
    @ExceptionHandler(Exception.class)
    public ResponseVo<Object> handleException (Exception ex) {
        GlobalLoggingThreadLocal.GLOBAL_LOGS.set(List.of(new GlobalInfo(StackTraceUtil.getStackTrace(ex), "exception")));
        // 返回错误信息
        String errorInfo = StrFormatter.format("未知错误：{}", ex.getMessage());
        return new ResponseVo<>(BaseCode.FAILED.getCode(), errorInfo);
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo<>( BaseCode.FAILED ));
    }
}
