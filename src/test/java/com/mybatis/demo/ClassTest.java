package com.mybatis.demo;

import com.mybatis.demo.base.utils.MyJSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: liyao
 * @Description: 类加载之反射
 * @Date: Created in 2018/05/04 16:27
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class ClassTest {

    @Test
    public void test(){
        try {
            Class<?> aClass = this.getClass().getClassLoader().loadClass("com.mybatis.demo.base.utils.MyJSONObject");
            MyJSONObject myJSONObject = (MyJSONObject)aClass.newInstance();

            Field objName = myJSONObject.getClass().getDeclaredField("objName");
            objName.setAccessible(true);
            objName.set(myJSONObject, "asd");
            System.out.println("MyJSONObject.objName：" + myJSONObject.getObjName());

            Method setObjName = myJSONObject.getClass().getMethod("setObjName", String.class);
            setObjName.invoke(myJSONObject, "qwe");
            System.out.println("MyJSONObject.objName：" + myJSONObject.getObjName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
