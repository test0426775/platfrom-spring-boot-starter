package com.zxo.platform.validation.support;

import cn.hutool.core.lang.Validator;
import com.zxo.platform.validation.constraints.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: EmailValidator
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 13:54
 * @Note:
 */
public class EmailValidator implements ConstraintValidator<Email, String>, BaseValidator {
    private String code;
    private String message;

    @Override
    public void initialize (Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.code = constraintAnnotation.code();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid (String value, ConstraintValidatorContext context) {
        if (Validator.isEmail(value)) {
            return true;
        }
        setMessage(context, code, message);
        return false;
    }
}
