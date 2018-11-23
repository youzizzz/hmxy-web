package com.hmxy.web.service.message.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hmxy.dto.MessageDTO;
import com.hmxy.enums.ObjectEnum;
import com.hmxy.http.HttpStatusEnum;
import com.hmxy.http.PageInfo;
import com.hmxy.http.PageUtils;
import com.hmxy.http.Response;
import com.hmxy.util.BeanUtil;
import com.hmxy.util.UUIDUtil;
import com.hmxy.web.dao.message.MessageDao;
import com.hmxy.web.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @discripeion:
 * @author: liangj
 * @date: 2018年11月7日09:49:35
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public PageInfo<MessageDTO> feedBackListPage(PageInfo<MessageDTO> pageInfoResult, MessageDTO messageDTO) {
        PageHelper.startPage(pageInfoResult.getPageNum(), pageInfoResult.getPageSize());
        Map<String, Object> paramMap = null;

        //实体对象转成请求map
        try {
            paramMap = BeanUtil.beanToMap(messageDTO);
        } catch (Exception e) {
        }

        List<MessageDTO> list = new ArrayList<MessageDTO>();
        list = messageDao.feedBackList(paramMap);
        Page<MessageDTO> page = (Page) list;
        return PageUtils.convertPage(page);
    }

    /**
     * 根据ID获取一条Message
     *
     * @param messageId
     * @return
     */
    @Override
    public Response<MessageDTO> findMessageById(String messageId) {
        return new Response<MessageDTO>().setMessage("查询成功!").setStatusCode(HttpStatusEnum.success.getCode()).setData(messageDao.findMessageById(messageId));
    }

    /**
     * 新增保存一条Message
     *
     * @param messageDTO
     * @return
     */
    @Override
    public Response<String> saveMessage(MessageDTO messageDTO) {
        messageDTO.setMessageId(UUIDUtil.generateUUID());
        int result = messageDao.saveMessage(messageDTO);
        return new Response<String>().setStatusCode(HttpStatusEnum.success.getCode())
                .setMessage(result > 0 ? "消息信息新增成功" : "消息信息新增失败")
                .setData(result > 0 ? "success" : "error");
    }

    /**
     * 更新一条Message
     *
     * @param messageDTO
     * @return
     */
    @Override
    public Response<String> updateMessage(MessageDTO messageDTO) {
        int result = messageDao.updateMessage(messageDTO);
        return new Response<String>().setStatusCode(HttpStatusEnum.success.getCode())
                .setMessage(result > 0 ? "消息信息更新成功" : "消息信息更新失败")
                .setData(result > 0 ? "success" : "error");
    }

    /**
     * 批量已读
     *
     * @param map
     * @return
     */
    @Override
    public Response<String> batchUpdateMessage(Map<String, Object> map) {
        int result = messageDao.batchUpdateMessage(map);
        return new Response<String>().setStatusCode(HttpStatusEnum.success.getCode())
                .setMessage(result > 0 ? "批量已读更新成功" : "批量已读更新失败")
                .setData(result > 0 ? "success" : "error");
    }

    /**
     * 查询消息
     * @param messageDTO
     * @return
     */
    @Override
    public List<MessageDTO> feedBackList(MessageDTO messageDTO) {
        Map<String, Object> paramMap = null;

        //实体对象转成请求map
        try {
            paramMap = BeanUtil.beanToMap(messageDTO);
        } catch (Exception e) {
        }

        List<MessageDTO> list = new ArrayList<MessageDTO>();
        list = messageDao.feedBackList(paramMap);
        return list;
    }

    @Override
    public Response<String> userFeedback(MessageDTO messageDTO) {
        Date date = new Date();
        String uuid = UUIDUtil.generateUUID();
        messageDTO.setCreatorBy("577");
        messageDTO.setUpdateBy("7788");
        messageDTO.setStatus(String.valueOf(ObjectEnum.effective.getStatus()));
        messageDTO.setMessageRead(String.valueOf(ObjectEnum.effective.getStatus()));
        messageDTO.setCreatorDate(date);
        messageDTO.setUpdateDate(date);
        messageDTO.setMessageId(uuid);
        int result = messageDao.saveMessage(messageDTO);
        return new Response<String>().setStatusCode(HttpStatusEnum.success.getCode())
                .setMessage(result > 0 ? "用户反馈成功" : "用户反馈失败")
                .setData(result > 0 ? uuid : "error");
    }
}
