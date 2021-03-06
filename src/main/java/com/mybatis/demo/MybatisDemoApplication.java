package com.mybatis.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.mybatis.demo.dao")
@EnableScheduling
@EnableRetry
public class MybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

	/*@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
		FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig=new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter=fastConverter;
		return new HttpMessageConverters(converter);
	}*/
}
