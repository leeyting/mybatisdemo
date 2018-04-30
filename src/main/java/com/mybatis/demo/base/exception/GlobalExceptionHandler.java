package com.mybatis.demo.base.exception;

import com.mybatis.demo.base.result.ResultFactory;
import com.mybatis.demo.base.utils.BasicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: liyao
 * @Description: 统一异常处理
 * @Date: Created in 2018/04/30 22:16
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public String handleError(Exception e, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        logger.error("ExceptionHandler", e);
        if (e instanceof ParamAssertException) {
            BasicUtils.EchoPlainUTF8(response, ResultFactory.FailedV1(e.getMessage()).toJsonString());
        } else if (e instanceof MultipartException) {
            BasicUtils.EchoPlainUTF8(response, ResultFactory.FailedV1("文件上传失败").toJsonString());
        } else {
            BasicUtils.EchoPlainUTF8(response, ResultFactory.FailedV1("系统错误").toJsonString());
            logger.error("ExceptionHandler", e);
        }
        return null;
    }
}
