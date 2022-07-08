package com.zxo.platform.validation.constraints;

import com.zxo.platform.validation.support.CitizenIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 身份证号码校验
 *
 * @ClassName: CitizenId
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 13:35
 * @Note:
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CitizenIdValidator.class})
public @interface CitizenId {
    /**
     * 错误代码
     */
    String code () default "00007";

    /**
     * 错误消息
     */
    String message () default "身份证号码错误";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
