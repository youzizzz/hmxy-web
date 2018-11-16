package com.hmxy.web.service.shareMeet.impl;

import com.hmxy.dto.ClassIficationDTO;
import com.hmxy.http.HttpStatusEnum;
import com.hmxy.http.Response;
import com.hmxy.web.dao.shareMeet.ShareMeetTypeDao;
import com.hmxy.web.service.shareMeet.ShareMeetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tangyouzhi
 * @date 2018年11月15日15:19:31
 */
@Service
public class ShareMeetTypeServiceImpl implements ShareMeetTypeService {

    @Autowired
    ShareMeetTypeDao shareMeetTypeDao;


    /**
     * 获得所有分类
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Response<List<ClassIficationDTO>> list(int pageSize, int pageNum) {
        Map<String, Object> paramMap = new ConcurrentHashMap<>();
        paramMap.put("pageSize",pageSize);
        paramMap.put("pageStart",(pageNum-1)*pageSize);
        return new Response<List<ClassIficationDTO>>().setData(shareMeetTypeDao.shareMeetTypeList(paramMap))
                .setMessage("查询成功!").
                        setStatusCode(HttpStatusEnum.success.getCode());
    }
}
