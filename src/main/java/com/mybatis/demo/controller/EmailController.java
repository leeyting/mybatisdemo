package com.mybatis.demo.controller;

import com.mybatis.demo.base.email.EmailService;
import com.mybatis.demo.base.result.ResultFactory;
import com.mybatis.demo.base.result.ResultV1;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/05/03 15:07
 */

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendSimpleMail")
    public ResultV1 sendSimpleMail(@RequestParam String sendTo, @RequestParam String title, @RequestParam String content) {
        try {
            Boolean success = emailService.sendSimpleMail(sendTo, title, content);
            if (success) {
                return ResultFactory.SuccessV1("发送成功");
            }
        } catch (Exception e) {
            logger.error("发送失败", e);
        }
        return ResultFactory.FailedV1("发送失败");
    }

    @PostMapping("/sendHtmlMail")
    public ResultV1 sendHtmlMail(@RequestParam String sendTo, @RequestParam String title, @RequestParam String content) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("<html><head></head>");
            sb.append("<body><h1>关于放假的通知</h1><p>五一放假三天</p>");
            sb.append("<img src=\"cid:image1\"/></body>");
            sb.append("</html>");
            content = sb.toString();
            List<Pair<String, File>> files = new ArrayList<>();
            files.add(Pair.of("image1", new File("E:/1.jpg")));
            Boolean success = emailService.sendHtmlMail(sendTo, title, content, files);
            if (success) {
                return ResultFactory.SuccessV1("发送成功");
            }
        } catch (Exception e) {
            logger.error("发送失败", e);
        }
        return ResultFactory.FailedV1("发送失败");
    }

    @PostMapping("/sendAttachmentsMail")
    public ResultV1 sendAttachmentsMail(@RequestParam String sendTo, @RequestParam String title, @RequestParam String content) {
        try {
            List<Pair<String, File>> files = new ArrayList<>();
            files.add(Pair.of("image1", new File("E:/1.jpg")));
            files.add(Pair.of("image2", new File("E:/2.jpg")));
            Boolean success = emailService.sendAttachmentsMail(sendTo, title, content, files);
            if (success) {
                return ResultFactory.SuccessV1("发送成功");
            }
        } catch (Exception e) {
            logger.error("发送失败", e);
        }
        return ResultFactory.FailedV1("发送失败");
    }


}
