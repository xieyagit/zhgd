package com.hujiang.project.zhgd.hujiangGroup.service;

import com.hujiang.project.zhgd.hujiangGroup.domain.HuJiangForeman;
import com.hujiang.project.zhgd.hujiangGroup.domain.UtilArea;
import com.hujiang.project.zhgd.hujiangGroup.domain.WorkType;

import java.util.List;
import java.util.Map;

/**
 * 搜索班组长 服务层
 *
 * @author hujiang
 * @date 2019-04-18
 */
public interface HujiangGroupService {

    /**
     * 地区
     * @return
     */
    List<UtilArea> queryUtil(UtilArea utilArea);

    /**
     * 工种
     */
    List<WorkType> queryWorkType(WorkType workType);

    /**
     * 查询班组长
     * @return
     */
    List<HuJiangForeman> queryForeman(Integer addressId, Integer workTypeId);

    /**
     * 查询项目经验 人数
     */
    Map<String, Object> queryFlow(Integer teamId);


    UtilArea selectArea(Integer xmid);
}
