package com.zxo.platform.validation.constraints;

import com.zxo.platform.validation.support.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 邮箱格式校验
 *
 * @ClassName: Email
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 13:53
 * @Note:
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailValidator.class})
public @interface Email {
    /**
     * 错误代码
     */
    String code () default "00007";

    /**
     * 错误消息
     */
    String message () default "邮箱格式错误";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
