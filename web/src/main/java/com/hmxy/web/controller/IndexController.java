package com.hmxy.web.controller;


import com.hmxy.util.LogUtil;
import com.hmxy.web.service.EmailService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  起始controller
 */
@Controller
public class IndexController {

    private static final Logger log=LogUtil.getLogger(IndexController.class);

    @Autowired
    private EmailService emailService;


    /**
     * 跳转到index页面
     * @return
     */
    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String index() {
        log.info("");
        return "index";
    }

}
