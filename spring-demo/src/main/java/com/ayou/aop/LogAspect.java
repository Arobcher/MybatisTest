package com.ayou.aop;

import com.ayou.pojo.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Before("execution(* com.ayou.service.UserService.deleteUser(*)) && args(user)")
    public void beforeAdvice(User user) {
        System.out.println("前置增强处理");
    }

    @AfterReturning("execution(* com.ayou.service.UserService.deleteUser(*)) && args(user)")
    public void afterReturningAdvice(User user) {
        System.out.println("后置增强处理");
    }

    @AfterThrowing(value = "execution(* com.ayou.service.UserService.deleteUserException(*)) && args(user)", throwing = "ex")
    public void afterThrowingAdvice(Exception ex, User user) {
        System.out.println("异常增强处理: 在目标后方抛出异常后执行");
        System.out.println("异常信息: " + ex.getMessage());
    }

    @Around("execution(* com.ayou.service.UserService.deleteUser(*)) && args(user)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, User user) throws Throwable {
        System.out.println("环绕增强处理, 目标方法: " + joinPoint.getSignature().getName());
        Object result = null;
        try {
            System.out.println("环绕增强处理: 执行前");
            result = joinPoint.proceed();
            System.out.println("环绕增强处理: 执行后");
        } catch (Throwable t) {
            System.out.println("环绕增强处理: 目标方法抛出异常");
            throw t;
        }
        return result;
    }


}
