package com.hmxy.web.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hmxy.dto.UserInfoDTO;
import com.hmxy.http.Response;

/**
 * @discripeion:
 * @author: liangj
 * @date: 2018/11/20 15:41
 */
public interface UserService {

    /**
     * 校验邮箱是否重复
     * @param userInfoDTO
     * @return
     */
    Response<String> checkMailbox(UserInfoDTO userInfoDTO);

    /**
     * 注册
     * @param userInfoDTO
     * @return
     */
    Response<String> register(UserInfoDTO userInfoDTO,String verifyCode);

    /**
     * 登录
     * @param userInfoDTO
     * @return
     */
    Response<String> login(UserInfoDTO userInfoDTO);
}
