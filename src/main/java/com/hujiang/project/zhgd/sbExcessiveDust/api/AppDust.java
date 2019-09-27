package com.hujiang.project.zhgd.sbExcessiveDust.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjSafetyCommission.service.IHjSafetyCommissionService;
import com.hujiang.project.zhgd.sbExcessiveDust.domain.SbExcessiveDust;
import com.hujiang.project.zhgd.sbExcessiveDust.service.ISbExcessiveDustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/appDustApi",method = RequestMethod.POST)
public class AppDust extends BaseController {
    @Autowired
    private ISbExcessiveDustService excessiveDustService;
    @Autowired
    private IHjSafetyCommissionService safetyCommissionService;
    @PostMapping(value = "/getExcessive")
    public JSONObject getExcessive(@RequestParam(value = "projectId")Integer projectId,
                                   @RequestParam(value = "userId")Integer userId,
                                   @RequestParam(value = "status")Integer status,
                                   PageDomain pageDomain){
        startPage();
        SbExcessiveDust sbExcessiveDust = new SbExcessiveDust();
        sbExcessiveDust.setProjectId(projectId);
        sbExcessiveDust.setUserId(userId);
        sbExcessiveDust.setStatus(status);
        List<SbExcessiveDust> sbExcessiveDustList = excessiveDustService.selectSbExcessiveDustList(sbExcessiveDust);
        TableDataInfo dataTable = getDataTable(sbExcessiveDustList);
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        JSONObject jsonObject = new JSONObject();
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", sbExcessiveDustList);
        }else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }

    @PostMapping(value = "/updateExcessive")
    public JSONObject updateExcessive(@RequestParam(value = "id")Integer id,
                                      @RequestParam(value = "status")Integer status){
        SbExcessiveDust sbExcessiveDust = new SbExcessiveDust();
        sbExcessiveDust.setId(id);
        sbExcessiveDust.setStatus(status);
        int hjSafetyCommissionList = excessiveDustService.updateSbExcessiveDust(sbExcessiveDust);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", hjSafetyCommissionList > 0 ? "修改成功" : "修改失败");
        jsonObject.put("code", hjSafetyCommissionList > 0 ? 0 : -1);
        return jsonObject;
    }

    @PostMapping(value = "/getCount")
    public JSONObject getCount(@RequestParam(value = "projectId")Integer projectId,@RequestParam(value = "userId")Integer userId){
        JSONObject jsonObject = new JSONObject();
        JSONObject count = new JSONObject();
        int excessiveCount = excessiveDustService.getExcessiveCount(projectId, userId);
        int commissionCount = safetyCommissionService.getCommissionCount(projectId, userId);
            count.put("excessiveCount",excessiveCount);
            count.put("commissionCount",commissionCount);
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",count);


        return jsonObject;
    }
}
