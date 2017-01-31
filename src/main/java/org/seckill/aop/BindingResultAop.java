package org.seckill.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.seckill.dto.SeckillResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author yingjun
 *
 * 采用AOP的方式处理参数问题。
 */
@Component
@Aspect
public class BindingResultAop {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* org.seckill.web.*.*(..))")
    public void aopMethod(){}

    @Around("aopMethod()")
    public Object  around(ProceedingJoinPoint joinPoint) throws Throwable{
        LOG.info("before method invoking!");
        BindingResult bindingResult = null;
        for(Object arg:joinPoint.getArgs()){
            if(arg instanceof BindingResult){
                bindingResult = (BindingResult) arg;
            }
        }
        if(bindingResult != null){
            if(bindingResult.hasErrors()){
                String errorInfo="["+bindingResult.getFieldError().getField()+"]"+bindingResult.getFieldError().getDefaultMessage();
                return new SeckillResult<Object>(false, errorInfo);
            }
        }
        return joinPoint.proceed();
    }
}
