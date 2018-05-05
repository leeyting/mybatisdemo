package com.mybatis.demo;

import java.lang.reflect.Field;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/05/05 21:32
 */

public class IntegerTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        System.out.println("a=" + a + ",b=" + b);
        swap(a, b);
        System.out.println("a=" + a + ",b=" + b);
    }

    private static void swap(Integer num1, Integer num2) {
        try {
            int temp = num1;
            Field value = Integer.class.getDeclaredField("value");
            value.setAccessible(true);
            value.set(num1, num2);
            value.set(num2, new Integer(temp));
//
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
