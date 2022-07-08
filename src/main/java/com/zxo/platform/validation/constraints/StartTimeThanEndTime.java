package com.zxo.platform.validation.constraints;

import com.zxo.platform.validation.support.StartTimeThanEndTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: EndTimeThanStartTime
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 14:39
 * @Note:
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StartTimeThanEndTimeValidator.class})
public @interface StartTimeThanEndTime {
    /**
     * 错误代码
     */
    String code () default "00007";

    /**
     * 错误消息
     */
    String message () default "开始时间大于结束时间";

    Class<?> className ();

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
