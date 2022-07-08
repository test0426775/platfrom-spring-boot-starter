package com.zxo.platform.validation.constraints;

import com.zxo.platform.validation.support.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Mobile
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 14:14
 * @Note:
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MobileValidator.class})
public @interface Mobile {
    /**
     * 错误代码
     */
    String code () default "00007";

    /**
     * 错误消息
     */
    String message () default "手机号码格式错误";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
