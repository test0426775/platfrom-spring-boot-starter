package com.zxo.platform.validation.constraints;

import com.zxo.platform.validation.support.NotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: NotNull
 * @Author: zzzxxxooo
 * @Date: 2022/6/23 13:59
 * @Note:
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotNullValidator.class})
public @interface NotNull {
    /**
     * 错误代码
     */
    String code () default "00007";

    /**
     * 错误消息
     */
    String message () default "参数为空或长度小于0";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
