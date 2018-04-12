package com.mybatis.demo;

import com.mybatis.demo.base.context.SpringContextAware;
import com.mybatis.demo.base.redis.standalone.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisDemoApplicationTests {

	@Test
	public void contextLoads() {
		RedisUtil redisUtil = SpringContextAware.getBean(RedisUtil.class);
		System.out.println(redisUtil.get("name"));
	}

}
