package com.mybatis.demo.base.email;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyao
 * @Description: 邮件接口
 * @Date: Created in 2018/05/03 11:24
 */

public interface EmailService {

    /**
     * 发送简单邮件
     *
     * @param sendTo  收件人地址
     * @param title   邮件标题
     * @param content 邮件内容
     */
    Boolean sendSimpleMail(String sendTo, String title, String content) throws Exception;

    /**
     * 发送包含HTML文本和图片的邮件
     *
     * @param sendTo              收件人地址
     * @param title               邮件标题
     * @param content             邮件内容
     * @param htmlImages<文件名，附件> 图片列表
     */
    Boolean sendHtmlMail(String sendTo, String title, String content, List<Pair<String, File>> htmlImages) throws Exception;

    /**
     * 发送带附件邮件
     *
     * @param sendTo              收件人地址
     * @param title               邮件标题
     * @param content             邮件内容
     * @param attachments<文件名，附件> 附件列表
     */
    Boolean sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments) throws Exception;

    /**
     * 发送模板邮件
     *
     * @param sendTo              收件人地址
     * @param title               邮件标题
     * @param content<key,        内容> 邮件内容
     * @param attachments<文件名，附件> 附件列表
     */
    Boolean sendTemplateMail(String sendTo, String title, Map<String, Object> content, List<Pair<String, File>> attachments) throws Exception;

}
