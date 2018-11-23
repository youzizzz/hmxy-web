package com.hmxy.web.service.user.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmxy.dto.UserInfoDTO;
import com.hmxy.enums.ObjectEnum;
import com.hmxy.http.HttpStatusEnum;
import com.hmxy.http.Response;
import com.hmxy.util.LogUtil;
import com.hmxy.util.MD5Util;
import com.hmxy.util.RedisUtil;
import com.hmxy.util.UUIDUtil;
import com.hmxy.web.dao.user.UserDao;
import com.hmxy.web.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @discripeion:
 * @author: liangj
 * @date: 2018/11/20 15:42
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LogUtil.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Response<String> checkMailbox(UserInfoDTO userInfoDTO) {
        int count = 0;
        userInfoDTO.setStatus(String.valueOf(ObjectEnum.effective.getStatus()));
        count = userDao.checkUserNameExists(userInfoDTO);
        if(count>=1){
            log.error("此邮箱已经是验证邮箱,不可再次校验!");
            return new Response<String>().setMessage("此邮箱已经是验证邮箱,不可再次校验!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        return new Response<String>().setMessage("此邮箱可用作验证邮箱").setStatusCode(HttpStatusEnum.success.getCode());
    }

    @Override
    public Response<String> register(UserInfoDTO userInfoDTO,String verifyCode) {
        if(StringUtils.isBlank(userInfoDTO.getEmail())){
            return new Response<String>().setMessage("邮箱不能为空!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        if(StringUtils.isBlank(verifyCode)){
            return new Response<String>().setMessage("验证码不能为空!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        if(StringUtils.isBlank(userInfoDTO.getPassword())){
            return new Response<String>().setMessage("密码不能为空!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        //获取redis中对应的的验证码
        String verCode = (String) redisUtil.get(userInfoDTO.getEmail());
        if(StringUtils.isBlank(verCode)){
            return new Response<String>().setMessage("验证码已失效!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        if(!verCode.equals(verifyCode)){
            return new Response<String>().setMessage("验证码错误!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        //密码md5加密
        String password = MD5Util.MD5(userInfoDTO.getPassword());
        String uuid = UUIDUtil.generateUUID();
        Date date = new Date();
        userInfoDTO.setPassword(password);
        userInfoDTO.setUserId(uuid);
        userInfoDTO.setCreatorBy(uuid);
        userInfoDTO.setUpdateBy(uuid);
        userInfoDTO.setCreatorDate(date);
        userInfoDTO.setUpdateDate(date);
        userInfoDTO.setStatus(String.valueOf(ObjectEnum.effective.getStatus()));
        int count = 0;
        count = userDao.saveUser(userInfoDTO);
        if(count<1){
            log.error("系统异常,注册失败!");
            return new Response<String>().setMessage("系统异常,注册失败!").setStatusCode(HttpStatusEnum.error.getCode());
        }
        //删除redis中对应的的验证码
        redisUtil.del(userInfoDTO.getEmail());
        return new Response<String>().setMessage("注册成功!").setStatusCode(HttpStatusEnum.success.getCode());
    }

    @Override
    public Response<String> login(UserInfoDTO userInfoDTO){
        //密码md5加密
        String password = MD5Util.MD5(userInfoDTO.getPassword());
        userInfoDTO.setPassword(password);
        userInfoDTO.setStatus(String.valueOf(ObjectEnum.effective.getStatus()));
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
        log.error("账号或者密码错误!");
        return new Response<String>().setMessage("账号或者密码错误!").setStatusCode(HttpStatusEnum.error.getCode());
    }
}
