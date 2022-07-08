package com.zxo.platform.validation.constraints;

import com.zxo.platform.validation.support.CreditCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 统一社会信用代码校验
 *
 * @ClassName: CreditCode
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 13:49
 * @Note:
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CreditCodeValidator.class})
public @interface CreditCode {
    /**
     * 错误代码
     */
    String code () default "00007";

    /**
     * 错误消息
     */
    String message () default "统一社会信用代码错误";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
