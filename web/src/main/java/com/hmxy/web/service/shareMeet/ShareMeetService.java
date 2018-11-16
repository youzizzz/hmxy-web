package com.hmxy.web.service.shareMeet;

import com.hmxy.dto.ShareDetailDTO;
import com.hmxy.dto.ShareMeetDTO;
import com.hmxy.http.PageInfo;
import com.hmxy.http.Response;

import java.util.List;

/**
 * @discripeion:
 * @author: liangj
 * @date: 2018/11/9 17:03
 */
public interface ShareMeetService {
    /**
     *
     * 分享会分页查询
     * @author liangj
     * @param
     * @return
     */
    Response<List<ShareMeetDTO>> shareMeetPage(ShareMeetDTO shareMeetDTO,int pageSize,int pageNum);
}
