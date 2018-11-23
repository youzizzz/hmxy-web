package com.hmxy.web.controller.user;

import com.hmxy.dto.UserInfoDTO;
import com.hmxy.http.Response;
import com.hmxy.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @discripeion:
 * @author: liangj
 * @date: 2018/11/20 15:24
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/checkMailbox")
    public Response<String> checkMailbox(UserInfoDTO userInfoDTO){
        return userService.checkMailbox(userInfoDTO);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response<String> login(UserInfoDTO userInfoDTO){
        return userService.login(userInfoDTO);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Response<String> register(UserInfoDTO userInfoDTO,String verifyCode){
        return userService.register(userInfoDTO,verifyCode);
    }

}
