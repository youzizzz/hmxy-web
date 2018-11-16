package com.hmxy.web.service.shareMeet.impl;

import com.hmxy.dto.ClassIficationDTO;
import com.hmxy.dto.ShareMeetDTO;
import com.hmxy.http.HttpStatusEnum;
import com.hmxy.http.Response;
import com.hmxy.util.BeanUtil;
import com.hmxy.web.dao.shareMeet.SysShareMeetingDao;
import com.hmxy.web.service.shareMeet.ShareMeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @discripeion:
 * @author: tangyouzhi
 * @date: 2018年11月15日16:04:23
 */
@Service
public class ShareMeetServiceImpl implements ShareMeetService {

    @Autowired
    private SysShareMeetingDao shareMeetDao;

    /**
     * 分享会分页查询
     *
     * @param shareMeetDTO
     * @param pageSize
     * @param pageNum
     * @return
     * @author liangj
     */
    @Override
    public Response<List<ShareMeetDTO>> shareMeetPage(ShareMeetDTO shareMeetDTO, int pageSize, int pageNum) {
        Map<String, Object> paramMap = null;

        //实体对象转成请求map
        try {
            paramMap = BeanUtil.beanToMap(shareMeetDTO);
        } catch (Exception e) {
        }
        paramMap.put("pageSize",pageSize);
        paramMap.put("pageStart",(pageNum-1)*pageSize);
        return new Response<List<ShareMeetDTO>>().setData(shareMeetDao.shareMeetList(paramMap))
                .setMessage("查询成功!").
                        setStatusCode(HttpStatusEnum.success.getCode());
    }
}
