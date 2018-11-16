package com.hmxy.web.service.shareMeet;

import com.hmxy.dto.ClassIficationDTO;
import com.hmxy.http.PageInfo;
import com.hmxy.http.Response;

import java.util.List;

/**
 * @discripeion:
 * @author: tangyouzhi
 * @date 2018年11月15日15:17:23
 */
public interface ShareMeetTypeService {

    /**
     * 获得所有分类
     * @param pageSize
     * @param pageNum
     * @return
     */
    Response<List<ClassIficationDTO>> list(int pageSize,int pageNum);

}
