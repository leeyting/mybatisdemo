package com.mybatis.demo.base.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: liyao
 * @Description: 跨域配置方式二
 * @Date: Created in 2018/04/09 13:52
 */

//@Configuration
public class CorsFilter2 extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowedHeaders("x-requested-with").allowCredentials(false).maxAge(3600);
    }
}
