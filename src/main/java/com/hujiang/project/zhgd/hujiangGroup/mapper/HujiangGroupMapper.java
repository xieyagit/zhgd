package com.hujiang.project.zhgd.hujiangGroup.mapper;


import com.hujiang.project.zhgd.hujiangGroup.domain.HuJiangForeman;
import com.hujiang.project.zhgd.hujiangGroup.domain.HuJiangTeam;
import com.hujiang.project.zhgd.hujiangGroup.domain.UtilArea;
import com.hujiang.project.zhgd.hujiangGroup.domain.WorkType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索班组长 数据层
 *
 * @author hujiang
 * @date 2019-04-18
 */
public interface HujiangGroupMapper {

    /**
     * 地区
     */
    List<UtilArea> queryUtil(UtilArea utilArea);

    /**
     * 工种
     */
    List<WorkType> queryWorkType(WorkType workType);

    /**
     * 查询班组长
     */
    List<HuJiangForeman> queryForeman(@Param("addressId") Integer addressId, @Param("workTypeId") Integer workTypeId);

    /**
     * 班组人数
     */
    Integer queryForemanCount(@Param("teamId") Integer teamId);

    /**
     * 项目经验
     */
    List<HuJiangTeam> queryTeamList(@Param("teamId") Integer teamId);

    UtilArea selectArea(@Param("xmid") Integer xmid);
}
