package com.mybatis.demo.aop.annotation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: liyao
 * @Description: aop配置类
 * @Date: Created in 2018/04/11 17:01
 */

@Configuration //注册被spring管理
@ComponentScan("com.mybatis.demo.controller") //指定扫描范围
@EnableAspectJAutoProxy //注解开启对aspectJ的支持
public class AopConfig {
}
