package com.hujiang.project.zhgd.hjSafetyCommission.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.HjSafetyAbarbeitung;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.service.IHjSafetyAbarbeitungService;
import com.hujiang.project.zhgd.hjSafetyCommission.domain.HjSafetyCommission;
import com.hujiang.project.zhgd.hjSafetyCommission.service.IHjSafetyCommissionService;
import com.hujiang.project.zhgd.sbExcessiveDust.domain.SbExcessiveDust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping(value = "/provider/appCommissionApi",method = RequestMethod.POST)
public class AppCommission extends BaseController {
    @Autowired
    private IHjSafetyCommissionService safetyCommissionService;
    @Autowired
    private IHjSafetyAbarbeitungService safetyAbarbeitungService;
    @PostMapping(value = "/getCommission")
    public JSONObject getCommission(@RequestParam(value = "projectId")Integer projectId,
                                    @RequestParam(value = "userId")Integer userId,
                                    @RequestParam(value = "status")Integer status,
                                    PageDomain pageDomain){
        HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
        hjSafetyCommission.setProjectId(projectId);
        hjSafetyCommission.setUserId(userId);
        hjSafetyCommission.setStatus(status);
        startPage();
        List<HjSafetyCommission> hjSafetyCommissionList = safetyCommissionService.selectHjSafetyCommissionList(hjSafetyCommission);
        TableDataInfo dataTable = getDataTable(hjSafetyCommissionList);
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        JSONObject jsonObject = new JSONObject();
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", hjSafetyCommissionList);
        }else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }
    @PostMapping(value = "/updateCommission")
    public JSONObject updateCommission(@RequestParam(value = "id")Integer id,
                                       @RequestParam(value = "status")Integer status){
        HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
        hjSafetyCommission.setId(id);
        hjSafetyCommission.setStatus(status);
        int hjSafetyCommissionList = safetyCommissionService.updateHjSafetyCommission(hjSafetyCommission);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", hjSafetyCommissionList > 0 ? "修改成功" : "修改失败");
        jsonObject.put("code", hjSafetyCommissionList > 0 ? 0 : -1);
        return jsonObject;
    }

}
