package com.hmxy.web.service.area.impl;

import com.hmxy.dto.AreaDTO;
import com.hmxy.http.HttpStatusEnum;
import com.hmxy.http.Response;
import com.hmxy.util.RedisUtil;
import com.hmxy.web.dao.area.AreaDao;
import com.hmxy.web.service.area.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @discripeion:
 * @author: tangyouzhi
 * @date: 2018年11月7日15:53:45
 */

@Service
public class AreaServiceImpl implements AreaService {


    @Autowired
    AreaDao areaDao;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 区域分类查询
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public Response<List<AreaDTO>> list(int pageSize, int pageNum) {
        Map<String, Object> paramMap = new ConcurrentHashMap<>();
        paramMap.put("pageSize",pageSize);
        paramMap.put("pageStart",(pageNum-1)*pageSize);
        return new Response<List<AreaDTO>>().setData(areaDao.areaList(paramMap))
                .setMessage("查询成功!").
                setStatusCode(HttpStatusEnum.success.getCode());
    }
}
