package com.hmxy.web.controller.message;

import com.hmxy.dto.MessageDTO;
import com.hmxy.enums.ObjectEnum;
import com.hmxy.http.PageInfo;
import com.hmxy.http.Response;
import com.hmxy.web.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 消息管理
 * @author  liangj
 */
@Controller
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private MessageService messageService;

    /**
     * 信息类型分页查询
     * @author tangyouzhi
     * @param messageDTO
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/listPage",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<MessageDTO> messageListPage(MessageDTO messageDTO, int page, int limit){
        PageInfo<MessageDTO> pageInfoResult = new PageInfo<MessageDTO>();
        pageInfoResult.setPageNum(page);
        pageInfoResult.setPageSize(limit);
        pageInfoResult = messageService.MessageListPage(pageInfoResult,messageDTO);
        pageInfoResult.setCode("0");
        return pageInfoResult;
    }

    /**
     * 信息查询
     * @author tangyouzhi
     * @param messageDTO
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<MessageDTO> messageList(MessageDTO messageDTO){
        List<MessageDTO> list = new ArrayList<MessageDTO>();
        list = messageService.messageList(messageDTO);
        return list;
    }

    /**
     * 用户反馈
     * @param messageDTO
     * @return
     */
    @RequestMapping(path = "/userFeedback",method = RequestMethod.POST)
    @ResponseBody
    public Response<String> userFeedback(MessageDTO messageDTO){
        return messageService.userFeedback(messageDTO);
    }

    /**
     * 根据messageId获取一条message
     * @param messageId
     * @return
     */
    @RequestMapping(path = "/findMessageById",method = RequestMethod.GET)
    @ResponseBody
    public Response<MessageDTO> findOneMessage(String messageId){
        return messageService.findMessageById(messageId);
    }

//    /**
//     * 更新一条message
//     * @param messageDTO
//     * @return
//     */
//    @RequestMapping(path = "/updateMessage",method = RequestMethod.POST)
//    @ResponseBody
//    public Response<String> updateMessage(MessageDTO messageDTO){
//        messageDTO.setUpdateDate(new Date());
//        messageDTO.setUpdateBy(findCurrentUser().getUserId());
//        return messageService.updateMessage(messageDTO);
//    }

//    /**
//     * 批量通过
//     * @param messageIds
//     * @return
//     */
//    @RequestMapping(path = "/batchRead",method =RequestMethod.POST)
//    @ResponseBody
//    public Response<String> batchRead(String messageIds){
//        Map<String,Object> params=new ConcurrentHashMap<>();
//        params.put("messageIds",messageIds.split(","));
//        params.put("updateBy",findCurrentUser().getUserId());
//        params.put("updateDate",new Date());
//        params.put("status", ObjectEnum.Invalid.getStatus());
//        return messageService.batchUpdateMessage(params);
//    }

}
