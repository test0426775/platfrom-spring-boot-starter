package com.zxo.platform.validation.support;

import cn.hutool.core.util.ReflectUtil;
import com.zxo.platform.validation.constraints.StartTimeThanEndTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

/**
 * @ClassName: EndTimeThanStartTimeValidator
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 14:47
 * @Note:
 */
public class StartTimeThanEndTimeValidator implements ConstraintValidator<StartTimeThanEndTime, Object>, BaseValidator {
    private String code;
    private String message;
    private Long startTime;
    private Long endTime;

    private Class<?> className;

    @Override
    public void initialize (StartTimeThanEndTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.code = constraintAnnotation.code();
        this.message = constraintAnnotation.message();
        this.className = constraintAnnotation.className();
    }

    @Override
    public boolean isValid (Object jsonObject, ConstraintValidatorContext context) {
        Method[] methods = ReflectUtil.getMethods(className);
        System.out.println(methods);
        ReflectUtil.invoke(ReflectUtil.newInstance(className), "setName", "setName");
        return true;
    }

}
