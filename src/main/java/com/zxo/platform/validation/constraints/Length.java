package com.zxo.platform.validation.constraints;

import com.zxo.platform.validation.support.LengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Min
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 10:13
 * @Note:
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {LengthValidator.class})
public @interface Length {
    /**
     * 错误代码
     */
    String code () default "00007";

    /**
     * 错误消息
     */
    String message () default "参数校验错误";

    int minSize () default 1;

    int maxSize () default -1;

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
