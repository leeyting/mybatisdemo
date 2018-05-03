package com.mybatis.demo.base.email;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyao
 * @Description: 发送email实现类
 * @Date: Created in 2018/05/03 11:28
 */

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private JavaMailSender mailSender;
//    @Autowired
//    private VelocityEngine velocityEngine;

    public Boolean sendSimpleMail(String sendTo, String title, String content) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        return true;
    }

    @Override
    public Boolean sendHtmlMail(String sendTo, String title, String content, List<Pair<String, File>> htmlImages) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //构件MimeMessage 对象，并设置在发送给收信人之前给自己（发送方）抄送一份，解决554 DT:SPM错误
        mimeMessage.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(emailConfig.getEmailFrom()));
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailConfig.getEmailFrom());
        helper.setTo(sendTo);
        helper.setSubject(title);
        helper.setText(content, true);

        if (htmlImages != null) {
            for (Pair<String, File> pair : htmlImages) {
                helper.addInline(pair.getLeft(), new FileSystemResource(pair.getRight()));
            }
        }
        mailSender.send(mimeMessage);
        return true;
    }

    public Boolean sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailConfig.getEmailFrom());
        helper.setTo(sendTo);
        helper.setSubject(title);
        helper.setText(content);

        if (attachments != null) {
            for (Pair<String, File> pair : attachments) {
                helper.addAttachment(pair.getLeft(), new FileSystemResource(pair.getRight()));
            }
        }
        mailSender.send(mimeMessage);
        return true;
    }

    public Boolean sendTemplateMail(String sendTo, String title, Map<String, Object> content, List<Pair<String, File>> attachments) throws Exception {
       /* MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailConfig.getEmailFrom());
        helper.setTo(sendTo);
        helper.setSubject(title);

        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "UTF-8", content);
        helper.setText(text, true);

        if (attachments != null) {
            for (Pair<String, File> pair : attachments) {
                helper.addAttachment(pair.getLeft(), new FileSystemResource(pair.getRight()));
            }
        }
        mailSender.send(mimeMessage);*/
        return true;
    }
}
