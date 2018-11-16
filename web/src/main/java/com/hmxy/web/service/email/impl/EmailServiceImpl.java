package com.hmxy.web.service.email.impl;

import com.hmxy.web.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 邮箱服务实现类
 * @author  tangyouzhi
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * 邮箱模板
     */
    private static final String EMAIL_CONTENT_TEMPLATE="欢迎来到IT峰汇网!您的验证码为: %s,请在三十分钟内进行填写!";

    /**
     * 标题模板
     */
    private static final String EMAIL_TITLE_TEMPLATE="it峰汇网邮箱验证通知邮件";

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;


    /**
     * 发送带验证码邮箱到指定收件人
     * @param to 收件人邮箱
     * @param title 标题
     * @param verCode 验证码
     */
    @Override
    public void sendEmail(String to, String title, String verCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(null==title?EMAIL_TITLE_TEMPLATE:title);
        message.setText(String.format(EMAIL_CONTENT_TEMPLATE,verCode));

        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
