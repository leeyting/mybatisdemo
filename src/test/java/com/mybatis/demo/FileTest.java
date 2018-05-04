package com.mybatis.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/04/29 23:44
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Configuration
public class FileTest {

    @Value("${config.front.view}")
    private String path;

    /**
     * 文件上传测试
     */
    @Test
    public void uploadTest() throws Exception {
        File f = new File("F:\\home\\bea1\\fv\\pages\\1.jpg");
        FileCopyUtils.copy(f, new File(path + "/images/1.jpg"));
    }
}
