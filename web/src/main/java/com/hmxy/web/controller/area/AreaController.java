package com.hmxy.web.controller.area;

import com.hmxy.dto.AreaDTO;
import com.hmxy.http.Response;
import com.hmxy.web.service.area.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 地址接口总览
 * @author tangyouzhi
 */
@RequestMapping("/area")
@RestController
public class AreaController {

    @Autowired
    AreaService areaService;

    /**
     * 所有地址接口
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("/list")
    public Response<List<AreaDTO>> list(@RequestParam(required = false,defaultValue = "10") int pageSize,
                                        @RequestParam(required=false,defaultValue = "1") int pageNum){
        return areaService.list(pageSize,pageNum);
    }
}
