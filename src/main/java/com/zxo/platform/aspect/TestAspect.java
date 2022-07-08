package com.zxo.platform.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TestAspect
 * @Author: zzzxxxooo
 * @Date: 2022/6/28 16:02
 * @Note:
 */
@Aspect
@Component
public class TestAspect {
    @Around("@annotation(com.zxo.platform.validation.constraints.Test)")
    public Object loginAop (ProceedingJoinPoint joinPoint) {


        System.out.println("调用目标方法前做一些事情.....");
        Object result = null;
        try {
            //调用目标方法
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("调用目标方法后做一些事情.....");


        return result;
    }
}
