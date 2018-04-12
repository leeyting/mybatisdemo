package com.mybatis.demo.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: liyao
 * @Description: 设置Sping的上下文
 * @Date: Created in 2018/04/12 10:33
 */

@Component
public class SpringContextAware implements ApplicationContextAware {

    private static ApplicationContext appContext;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextAware.appContext = context;
    }

    public static Object getBean(String beanName) throws BeansException {
        if (SpringContextAware.appContext == null || StringUtils.isEmpty(beanName)) {
            return null;
        }
        return SpringContextAware.appContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> paramClass) throws BeansException {
        if (SpringContextAware.appContext == null || StringUtils.isEmpty(beanName) || paramClass == null) {
            return null;
        }
        return SpringContextAware.appContext.getBean(beanName, paramClass);
    }

    public static <T> T getBean(Class<T> paramClass) throws BeansException {
        if (SpringContextAware.appContext == null || paramClass == null) {
            return null;
        }
        return SpringContextAware.appContext.getBean(paramClass);
    }

    public static Object getBean(String beanName, Object... paramVarArgs) throws BeansException {
        if (SpringContextAware.appContext == null || StringUtils.isEmpty(beanName)) {
            return null;
        }
        return SpringContextAware.appContext.getBean(beanName, paramVarArgs);
    }
}
