package com.zxo.platform.validation.support;

import cn.hutool.core.lang.Validator;
import com.zxo.platform.validation.constraints.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: MobileValidator
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 14:26
 * @Note:
 */
public class MobileValidator implements ConstraintValidator<Mobile, String>, BaseValidator {
    private String code;
    private String message;

    @Override
    public void initialize (Mobile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.code = constraintAnnotation.code();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid (String value, ConstraintValidatorContext context) {
        if (Validator.isMobile(value)) {
            return true;
        }
        setMessage(context, code, message);
        return false;
    }
}
