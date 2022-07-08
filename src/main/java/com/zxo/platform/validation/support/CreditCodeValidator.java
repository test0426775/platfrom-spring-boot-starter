package com.zxo.platform.validation.support;

import cn.hutool.core.lang.Validator;
import com.zxo.platform.validation.constraints.CreditCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: CreditCodeValidator
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 13:49
 * @Note:
 */
public class CreditCodeValidator implements ConstraintValidator<CreditCode, String>, BaseValidator {
    private String code;
    private String message;

    @Override
    public void initialize (CreditCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.code = constraintAnnotation.code();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid (String value, ConstraintValidatorContext context) {
        if (Validator.isCreditCode(value)) {
            return true;
        }
        setMessage(context, code, message);
        return false;
    }
}
