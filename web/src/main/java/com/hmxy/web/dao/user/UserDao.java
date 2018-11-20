package com.hmxy.web.dao.user;

import com.hmxy.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @discripeion:
 * @author: tangyouzhi
 * @date: 2018年11月20日15:00:32
 */
@Mapper
public interface UserDao {


    /**
     * 登录
     * @param userInfoDTO
     * @return
     */
    List<UserInfoDTO> login(UserInfoDTO userInfoDTO);

    /**
     * 新增一个用户
     * @param userInfoDTO
     * @return
     */
    Integer saveUser(UserInfoDTO userInfoDTO);


    /**
     * 校验邮箱是否存在
     * @param userInfoDTO
     * @return
     */
    Integer checkUserNameExists(UserInfoDTO userInfoDTO);
}
