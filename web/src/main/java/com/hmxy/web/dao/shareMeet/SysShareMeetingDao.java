package com.hmxy.web.dao.shareMeet;

import com.hmxy.dto.ShareMeetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @discripeion:
 * @author: tangyouzhi
 * @date: 2018年11月15日16:01:32
 */
@Mapper
public interface SysShareMeetingDao {
    /**
     * 分享会分页
     * @param map
     * @return
     */
    List<ShareMeetDTO> shareMeetList(Map<String, Object> map);
}
