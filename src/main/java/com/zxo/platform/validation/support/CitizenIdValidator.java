package com.zxo.platform.validation.support;

import cn.hutool.core.lang.Validator;
import com.zxo.platform.validation.constraints.CitizenId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: CitizenIdValidator
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 13:36
 * @Note:
 */
public class CitizenIdValidator implements ConstraintValidator<CitizenId, String>, BaseValidator {
    private String code;
    private String message;

    @Override
    public void initialize (CitizenId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.code = constraintAnnotation.code();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid (String value, ConstraintValidatorContext context) {
        if (Validator.isCitizenId(value)) {
            return true;
        }
        setMessage(context, code, message);
        return false;
    }
}
