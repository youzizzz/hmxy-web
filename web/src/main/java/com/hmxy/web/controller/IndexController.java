package com.hmxy.web.controller;

import com.hmxy.http.HttpStatusEnum;
import com.hmxy.http.Response;
import com.hmxy.util.LogUtil;
import com.hmxy.util.RedisUtil;
import com.hmxy.util.UUIDUtil;
import com.hmxy.web.service.email.EmailService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @discripeion:
 * @author: liangj
 * @date: 2018/11/20 15:26
 */
@RequestMapping("/index")
@RestController
public class IndexController {

    private static final Logger log = LogUtil.getLogger(IndexController.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/sendEmail")
    public Response<String> sendEmail(String email){
        String verCode = UUIDUtil.generateUUID().substring(0,6);
        try{
            emailService.sendEmail(email,null,verCode);
            redisUtil.set(email,verCode,30L, TimeUnit.MINUTES);
        }catch(Exception e){
            log.error("邮件发送失败!");
            return new Response<String>().setMessage("邮件发送失败!").setStatusCode(HttpStatusEnum.error.getCode());
        }

        return new Response<String>().setMessage("邮件发送成功!").setStatusCode(HttpStatusEnum.success.getCode());
    }

}
