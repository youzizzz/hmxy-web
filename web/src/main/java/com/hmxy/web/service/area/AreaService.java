package com.hmxy.web.service.area;


import com.hmxy.dto.AreaDTO;
import com.hmxy.http.PageInfo;
import com.hmxy.http.Response;

import java.util.List;

/**
 * 地址管理
 *
 * @author tangyouzhi
 */
public interface AreaService {

    /**
     * 区域分类查询
     * @param pageSize
     * @param pageNum
     * @return
     */
    Response<List<AreaDTO>> list(int pageSize,int pageNum);

}
