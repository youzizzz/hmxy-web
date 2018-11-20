package com.hmxy.web.service.user.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmxy.dto.UserInfoDTO;
import com.hmxy.http.HttpStatusEnum;
import com.hmxy.http.Response;
import com.hmxy.util.MD5Util;
import com.hmxy.util.RedisUtil;
import com.hmxy.web.dao.user.UserDao;
import com.hmxy.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @discripeion:
 * @author: liangj
 * @date: 2018/11/20 15:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Response<String> checkMailbox(UserInfoDTO userInfoDTO) {
        int count = 0;
        count = userDao.checkUserNameExists(userInfoDTO);
        if(count>=1){
            return new Response<String>().setMessage("此邮箱已经是验证邮箱,不可再次校验!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        return new Response<String>().setMessage("此邮箱可用作验证邮箱").setStatusCode(HttpStatusEnum.success.getCode());
    }

    @Override
    public Response<String> register(UserInfoDTO userInfoDTO) {
        //密码md5加密
        String password = MD5Util.MD5(userInfoDTO.getPassword());
        userInfoDTO.setPassword(password);
        int count = 0;
        count = userDao.saveUser(userInfoDTO);
        if(count<1){
            return new Response<String>().setMessage("系统异常,注册失败!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        return new Response<String>().setMessage("注册成功!").setStatusCode(HttpStatusEnum.success.getCode());
    }

    @Override
    public Response<String> login(UserInfoDTO userInfoDTO){
        //密码md5加密
        String password = MD5Util.MD5(userInfoDTO.getPassword());
        userInfoDTO.setPassword(password);
        List<UserInfoDTO> list = new ArrayList<UserInfoDTO>();
        list = userDao.login(userInfoDTO);
        if(null!=list&&list.size()==1){
            ObjectMapper objectMapper=new ObjectMapper();
            try {
                String userString=objectMapper.writeValueAsString(userInfoDTO);
                redisUtil.set(userInfoDTO.getUserId(),userString,30L, TimeUnit.MINUTES);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return new Response<String>().setMessage("登录成功!").setStatusCode(HttpStatusEnum.success.getCode());
        }
        return new Response<String>().setMessage("账号或者密码错误!").setStatusCode(HttpStatusEnum.success.getCode());
    }
}
