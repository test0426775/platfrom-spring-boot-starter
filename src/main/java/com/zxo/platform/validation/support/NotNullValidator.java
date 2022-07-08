package com.zxo.platform.validation.support;

import cn.hutool.core.text.StrFormatter;
import com.zxo.platform.validation.constraints.NotNull;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: NotNullValidator
 * @Author: zzzxxxooo
 * @Date: 2022/6/23 17:30
 * @Note:
 */
public class NotNullValidator implements ConstraintValidator<NotNull, Object> {
    private String code;
    private String message;

    @Override
    public void initialize (NotNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.code = constraintAnnotation.code();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid (Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (ObjectUtils.isEmpty(object)) {
            String errorMsg = StrFormatter.format("{}|{}", code, message);
            // 禁止默认消息返回
            constraintValidatorContext.disableDefaultConstraintViolation();
            // 自定义返回消息
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMsg).addConstraintViolation();
            return false;
        }
        return true;
    }
}
