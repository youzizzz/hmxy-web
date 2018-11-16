package com.hmxy.web.dao.shareMeet;

import com.hmxy.dto.ClassIficationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @discripeion:
 * @author: liangj
 * @date: 2018/11/6 10:22
 */
@Mapper
public interface ShareMeetTypeDao {

    /**
     * 分享会类型分页
     * @param map
     * @return
     */
    List<ClassIficationDTO> shareMeetTypeList(Map<String, Object> map);

    /**
     * 分享会类型新增
     * @param classIficationDTO
     * @return
     */
    int shareMeetTypeAdd(ClassIficationDTO classIficationDTO);

    /**
     * 分享会类型更新
     * @param classIficationDTO
     * @return
     */
    int shareMeetTypeUpdate(ClassIficationDTO classIficationDTO);

    /**
     * 通过id获取分享会类型
     * @param cfId
     * @return
     */
    List<ClassIficationDTO> getShareMeetTypeById(String cfId);
}
