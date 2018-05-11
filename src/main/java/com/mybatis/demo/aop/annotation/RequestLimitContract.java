package com.mybatis.demo.aop.annotation;

import com.mybatis.demo.base.result.ResultFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/05/11 16:10
 */

@Aspect
@Component
public class RequestLimitContract {
    private static final Logger logger = LoggerFactory.getLogger(RequestLimitContract.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.mybatis.demo.aop.annotation.RequestLimit)")
    public void requestPointcut() {
    }

    @Around(value = "requestPointcut() && @annotation(requestLimit)", argNames = "joinPoint, requestLimit")
    public Object interceptor(ProceedingJoinPoint joinPoint, RequestLimit requestLimit) throws Throwable {
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");

        String url = request.getRequestURL().toString();
        String key = "req_limit_".concat(url).concat("_" + token);
        long count = redisTemplate.opsForValue().increment(key, 1);
        if (count == 1) {
            redisTemplate.expire(key, requestLimit.time(), TimeUnit.MILLISECONDS);
        }
        if (count > requestLimit.count()) {
            logger.info("用户访问地址[" + url + "]超过了限定的次数[" + requestLimit.count() + "]");
            return ResultFactory.FailedV1("用户访问地址[" + url + "]超过了限定的次数[" + requestLimit.count() + "]");
        }

        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            logger.info("授权失败：", e);
        }
        return result;
    }

}
