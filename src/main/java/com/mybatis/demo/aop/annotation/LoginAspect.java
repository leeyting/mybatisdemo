package com.mybatis.demo.aop.annotation;

import com.mybatis.demo.aop.WebLogAspect;
import com.mybatis.demo.base.result.ResultFactory;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author: liyao
 * @Description: 切面配置注解式拦截
 * @Date: Created in 2018/04/11 17:05
 */

@Aspect
@Component
public class LoginAspect {

    protected static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("@annotation(com.mybatis.demo.aop.annotation.LoginAnnotation)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {
            return ResultFactory.FailedV1("无权限");
        }
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            logger.info("授权失败：", e);
        }
        return result;
    }

    @After("annotationPointcut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LoginAnnotation annotation = method.getAnnotation(LoginAnnotation.class);
        System.out.println("注解式拦截 : " + annotation.name());
    }
}