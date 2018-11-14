package com.hmxy.web.service;

/**
 * 邮箱服务
 * @author tangyouzhi
 */
public interface EmailService {

    /**
     * 发送带验证码邮箱到指定收件人
     * @param to 收件人邮箱
     * @param title 标题
     * @param verCode 验证码
     */
    void sendEmail(String to,String title,String verCode);
}
