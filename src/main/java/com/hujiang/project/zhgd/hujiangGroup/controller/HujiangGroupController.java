package com.hujiang.project.zhgd.hujiangGroup.controller;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.zhgd.hujiangGroup.domain.HuJiangForeman;
import com.hujiang.project.zhgd.hujiangGroup.domain.UtilArea;
import com.hujiang.project.zhgd.hujiangGroup.domain.WorkType;
import com.hujiang.project.zhgd.hujiangGroup.service.HujiangGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zhgd/hujiangGroup")
public class HujiangGroupController extends BaseController {




    @Autowired
    private HujiangGroupService hujiangGroupService;

    /**
     * 查看班组长信息
     */
    @RequestMapping("/queryForeman")
    @ResponseBody
    public Map<String, Object> queryForeman(Integer addressId,Integer workTypeId)
    {
        Map<String, Object> map = new HashMap<>();

        try {
             List<HuJiangForeman> huJiangForemanList = hujiangGroupService.queryForeman(addressId,workTypeId);
            map.put("msg",huJiangForemanList);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","加载失败！");
        }

        return map;
    }


    /**
     * 查看班组工作经验
     */
    @RequestMapping("/queryFlow")
    @ResponseBody
    public Map<String, Object> queryFlow(Integer teamId)
    {
        Map<String, Object> map = hujiangGroupService.queryFlow(teamId);

        return map;
    }


    /**
     * 地区
     */
    @RequestMapping("/queryUtil")
    @ResponseBody
    public Map<String, Object> queryUtil(UtilArea utilArea)
    {

        Map<String, Object> map = new HashMap<>();

        try {
           List<UtilArea> utilAreaList = hujiangGroupService.queryUtil(utilArea);
            map.put("msg",utilAreaList);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","加载失败！");
        }

        return map;
    }

    /**
     * 工种
     */
    @RequestMapping("/queryWorkType")
    @ResponseBody
    public Map<String, Object> queryWorkType(WorkType workType)
    {
        Map<String, Object> map = new HashMap<>();

        try {
            List<WorkType> workTypeList = hujiangGroupService.queryWorkType(workType);
            map.put("msg",workTypeList);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","加载失败！");
        }

        return map;
    }






}
