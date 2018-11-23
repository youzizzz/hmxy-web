package com.hmxy.web.dao.message;

import com.hmxy.dto.MessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @discripeion:
 * @author: tangyozhi
 * @date:  2018年11月7日09:49:01
 */
@Mapper
public interface MessageDao {

    /**
     * 消息类型列表
     * @param map
     * @return
     */
    List<MessageDTO> messageList(Map<String, Object> map);

    /**
     * 根据ID获取一条Message
     * @param messageId
     * @return
     */
    MessageDTO findMessageById(String messageId);

    /**
     * 新增保存一条Message
     * @param messageDTO
     * @return
     */
    Integer saveMessage(MessageDTO messageDTO);

    /**
     * 更新一条Message
     * @param messageDTO
     * @return
     */
    Integer updateMessage(MessageDTO messageDTO);

    /**
     * 批量已读
     * @param map
     * @return
     */
    Integer batchUpdateMessage(Map<String, Object> map);
}
