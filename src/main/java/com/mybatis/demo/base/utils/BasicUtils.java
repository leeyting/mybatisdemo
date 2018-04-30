package com.mybatis.demo.base.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/04/30 22:55
 */
public class BasicUtils {

    public static void EchoPlainUTF8(HttpServletResponse response, String text) {
        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().print(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
