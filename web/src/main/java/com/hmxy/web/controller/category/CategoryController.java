package com.hmxy.web.controller.category;


import com.hmxy.dto.ClassIficationDTO;
import com.hmxy.http.Response;
import com.hmxy.web.service.shareMeet.ShareMeetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类获取
 * @author  tangyouzhi
 */
@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    ShareMeetTypeService shareMeetTypeService;

    /**
     * 获得所有分类
     * @return
     */
    @RequestMapping("/list")
    public Response<List<ClassIficationDTO>> findAllCategory(@RequestParam(required = false,defaultValue = "10") int pageSize,
                                                             @RequestParam(required=false,defaultValue = "1") int pageNum){
        return shareMeetTypeService.list(pageSize,pageNum);
    }
}
