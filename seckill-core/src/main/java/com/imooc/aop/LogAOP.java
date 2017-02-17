package com.imooc.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author yan
 *         <p>
 *         采用AOP的方式处理参数问题。
 */
@Component
@Aspect
public class LogAOP {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.imooc.web.*.*(..))")
    public void aopMethod() {
    }

    @Around("aopMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String classType = joinPoint.getTarget().getClass().getName();
        //运用反射的原理创建对象
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String clazzSimpleName = clazz.getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(clazzName);
        logger.info("clazzName: " + clazzName + ", methodName:" + methodName);
        long start = System.currentTimeMillis();
        LOG.info("before method invoking!");
        BindingResult bindingResult = null;
        System.out.println("---------------");
        System.out.println("调用类：" + clazzSimpleName);
        System.out.println("调用方法：" + methodName);
        return joinPoint.proceed();
    }
}