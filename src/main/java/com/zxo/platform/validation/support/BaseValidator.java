package com.zxo.platform.validation.support;

import cn.hutool.core.text.StrFormatter;

import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: Validator
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 10:21
 * @Note:
 */
public interface BaseValidator {
    default void setMessage (ConstraintValidatorContext context, String code, String message) {
        String errorMsg = StrFormatter.format("{}|{}", code, message);
        // 禁止默认消息返回
        context.disableDefaultConstraintViolation();
        // 自定义返回消息
        context.buildConstraintViolationWithTemplate(errorMsg).addConstraintViolation();
    }
}
