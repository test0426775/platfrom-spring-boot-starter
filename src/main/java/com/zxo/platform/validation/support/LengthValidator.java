package com.zxo.platform.validation.support;

import cn.hutool.core.lang.Validator;
import com.zxo.platform.validation.constraints.Length;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName: MinValidator
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 10:14
 * @Note:
 */
public class LengthValidator implements ConstraintValidator<Length, String>, BaseValidator {
    private String code;
    private String message;

    private int minSize;

    private int maxSize;

    @Override
    public void initialize (Length constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.code = constraintAnnotation.code();
        this.message = constraintAnnotation.message();
        this.minSize = constraintAnnotation.minSize();
        this.maxSize = constraintAnnotation.maxSize();
    }


    @Override
    public boolean isValid (String value, ConstraintValidatorContext context) {

        if (value == null) {
            setMessage(context, code, message);
            return false;
        }

        if (maxSize < 0) {
            if (Validator.isGeneral(value, minSize)) {
                return true;
            }
        } else {
            if (Validator.isGeneral(value, minSize, maxSize)) {
                return true;
            }
        }
        setMessage(context, code, message);
        return false;
    }

}
