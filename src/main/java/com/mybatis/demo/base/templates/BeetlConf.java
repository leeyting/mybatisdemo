package com.mybatis.demo.base.templates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeetlConf {
	@Bean(name = "beetlTplRender")
	public BeetlTplRender getBeetlTplRender() {
		try {
			return new BeetlTplRender();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}