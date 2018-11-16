package com.hmxy.web.dao.area;

import com.hmxy.dto.AreaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @discripeion:
 * @author: tangyozhi
 * @date: 2018年11月7日15:50:07
 */
@Mapper
public interface AreaDao {

    /**
     * 地址类型列表
     *
     * @param map
     * @return
     */
    List<AreaDTO> areaList(Map<String, Object> map);


    /**
     * 校验地址是否存在
     * @param areaDTO
     * @return
     */
    Integer checkCityExists(AreaDTO areaDTO);

    /**
     * 新增地址
     *
     * @param areaDTO
     * @return
     */
    Integer addCity(AreaDTO areaDTO);

    /**
     * 修改地址信息
     * @param areaDTO
     * @return
     */
    Integer updateCity(AreaDTO areaDTO);

    /**
     * 根据ID查询一个地址信息
     * @param areId
     * @return
     */
    AreaDTO findOneArea(String areId);
}
