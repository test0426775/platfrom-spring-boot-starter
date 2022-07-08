package com.zxo.platform.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxo.platform.enums.BaseCode;
import com.zxo.platform.excption.CustomizeException;
import com.zxo.platform.model.ResponseVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName: GlobalController
 * @Author: zzzxxxooo
 * @Date: 2022/6/20 10:04
 * @Note:
 */
// @ControllerAdvice(basePackages = "#{@basePackage}")
@ControllerAdvice
//@ControllerAdvice(basePackageClasses = "com.zxo.platform.test.TestController")
// @ControllerAdvice
public class GlobalController implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports (MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !(returnType.getGenericParameterType().equals(ResponseVo.class));
    }

    @Override
    public Object beforeBodyWrite (Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new ResponseVo<>(data));
            } catch (JsonProcessingException e) {
                throw new CustomizeException(BaseCode.REQUEST_RESULT_ERROR);
            }
        }
        if (data instanceof ResponseVo) {
            return data;
        }
        return new ResponseVo<>(data);
    }
}
