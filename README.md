mybatis使用demo, 使用注解实现增删改查
1.generator mybatis自动生成器
新建maven configuation,command line:mybatis-generator:generate -e
2.springboot执行添加option参数-Dspring.profiles.active=dat

3.使用CountDownLatch模拟多线程并发

4.分布式Session同步
方式1、在配置文件中添加一行配置
spring.session.store-type=redis
方式2、在程序启动类上添加注解
@EnableRedisHttpSession
方式3、自定义SessionConfig类，添加注解@EnableRedisHttpSession

5.springboot加载静态资源配置，前后端分离

6.文件上传

7.自定义异常

8.邮件发送
