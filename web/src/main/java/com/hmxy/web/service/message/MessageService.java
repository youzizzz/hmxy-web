package com.hmxy.web.service.message;

import com.hmxy.dto.MessageDTO;
import com.hmxy.http.PageInfo;
import com.hmxy.http.Response;

import java.util.List;
import java.util.Map;

/**
 * @discripeion:
 * @author: tangyouzhi
 * @date: 2018年11月7日09:49:21
 */
public interface MessageService {

    /**
     *
     * 消息类型分页查询
     * @author liangj
     * @param pageInfoResult
     * @return
     */
    PageInfo<MessageDTO> feedBackListPage(PageInfo<MessageDTO> pageInfoResult, MessageDTO messageDTO);

    /**
     * 根据ID获取一条Message
     * @param messageId
     * @return
     */
    Response<MessageDTO> findMessageById(String messageId);

    /**
     * 新增保存一条Message
     * @param messageDTO
     * @return
     */
    Response<String> saveMessage(MessageDTO messageDTO);

    /**
     * 更新一条Message
     * @param messageDTO
     * @return
     */
    Response<String> updateMessage(MessageDTO messageDTO);

    /**
     * 批量已读
     * @param map
     * @return
     */
    Response<String> batchUpdateMessage(Map<String, Object> map);

    /**
     * 消息查询
     * @param messageDTO
     * @return
     */
    List<MessageDTO> feedBackList(MessageDTO messageDTO);

    /**
     * 用户反馈
     * @param messageDTO
     * @return
     */
    Response<String> userFeedback(MessageDTO messageDTO);
}
