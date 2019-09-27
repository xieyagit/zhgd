package com.hujiang.project.zhgd.hujiangGroup.service;

import com.hujiang.project.zhgd.hujiangGroup.domain.HuJiangForeman;
import com.hujiang.project.zhgd.hujiangGroup.domain.HuJiangTeam;
import com.hujiang.project.zhgd.hujiangGroup.domain.UtilArea;
import com.hujiang.project.zhgd.hujiangGroup.domain.WorkType;
import com.hujiang.project.zhgd.hujiangGroup.mapper.HujiangGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 项目人员 服务层实现
 *
 * @author hujiang
 * @date 2019-04-18
 */
@Service
public class HujiangGroupServiceImpl implements HujiangGroupService {


    @Autowired
    private HujiangGroupMapper hujiangGroupMapper;

    /**
     * 地区
     * @return
     */
    @Override
    public List<UtilArea> queryUtil(UtilArea utilArea) {

        List<UtilArea> utilAreaList = hujiangGroupMapper.queryUtil(utilArea);
       return utilAreaList;
    }

    /**
     * 工种
     */
    @Override
    public List<WorkType> queryWorkType(WorkType workType) {
        List<WorkType> workTypeList = hujiangGroupMapper.queryWorkType(workType);
        return workTypeList;
    }


    /**
     * 查询班组长
     * @return
     */
    @Override
    public List<HuJiangForeman> queryForeman(Integer addressId, Integer workTypeId) {
        List<HuJiangForeman> huJiangForemanList = hujiangGroupMapper.queryForeman(addressId, workTypeId);

        List<HuJiangForeman> list = new ArrayList<>();
        if(huJiangForemanList.size() > 0){

            for (int i = 0; i < huJiangForemanList.size(); i++){

                HuJiangForeman huJiangForeman = new HuJiangForeman();
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                //数据库出入职时间处理：
                Calendar setCal = Calendar.getInstance();
                if(huJiangForemanList.get(i).getCreateTime() != null){
                    setCal.setTime(huJiangForemanList.get(i).getCreateTime());//d为员工的入职时间
                }
                setCal.set(Calendar.HOUR_OF_DAY, 0);
                setCal.set(Calendar.MINUTE, 0);
                setCal.set(Calendar.SECOND, 0);
                setCal.set(Calendar.MILLISECOND, 0);
                //天数差：
                long dayDiff =(cal.getTimeInMillis()-setCal.getTimeInMillis())/(1000*60*60*24);
                Double year = Math.ceil(dayDiff/365);
                huJiangForeman.setWorKing(year.intValue());
                huJiangForeman.setGroupName(huJiangForemanList.get(i).getGroupName());
                huJiangForeman.setUserName(huJiangForemanList.get(i).getUserName());
                huJiangForeman.setNation(huJiangForemanList.get(i).getNation());
                huJiangForeman.setPhone(huJiangForemanList.get(i).getPhone());
                huJiangForeman.setPhotoName(huJiangForemanList.get(i).getPhotoName());
                huJiangForeman.setTeamId(huJiangForemanList.get(i).getTeamId());
                huJiangForeman.setIsLeader(huJiangForemanList.get(i).getIsLeader());
                huJiangForeman.setBuildCompanyName(huJiangForemanList.get(i).getBuildCompanyName());
                huJiangForeman.setTeamName(huJiangForemanList.get(i).getTeamName());
                list.add(huJiangForeman);
            }

        }
        return list;
    }


    /**
     * 查询项目经验 人数
     */
    @Override
    public Map<String, Object> queryFlow(Integer teamId) {

        Map<String, Object> map = new HashMap<>();

        // 查询班组人数
        Integer count = hujiangGroupMapper.queryForemanCount(teamId);
        // 查询班组项目经验
        List<HuJiangTeam> teamList = hujiangGroupMapper.queryTeamList(teamId);
        map.put("count",count);
        map.put("teamList",teamList);

        return map;
    }

    @Override
    public UtilArea selectArea(Integer xmid) {
        UtilArea utilArea = hujiangGroupMapper.selectArea(xmid);
        return utilArea;
    }
}
