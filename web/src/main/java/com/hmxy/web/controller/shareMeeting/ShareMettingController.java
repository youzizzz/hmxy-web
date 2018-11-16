package com.hmxy.web.controller.shareMeeting;


import com.hmxy.dto.ShareMeetDTO;
import com.hmxy.http.Response;
import com.hmxy.web.service.shareMeet.ShareMeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分享会接口
 */
@RequestMapping("/share")
@RestController
public class ShareMettingController {

    @Autowired
    ShareMeetService shareMeetService;

    /**
     * 根据条件获取分享会信息
     * @param shareMeetDTO
     * @return
     */
    @RequestMapping("/list")
    public Response<List<ShareMeetDTO>> list(ShareMeetDTO shareMeetDTO, @RequestParam(required = false,defaultValue = "10") int pageSize,
                                             @RequestParam(required=false,defaultValue = "1") int pageNum){
        return shareMeetService.shareMeetPage(shareMeetDTO,pageSize,pageNum);
    }

}
