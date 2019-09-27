package com.hujiang.project.zhgd.hjReport.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjReport.domain.HjReport;
import com.hujiang.project.zhgd.hjReport.domain.HjReportPc;
import com.hujiang.project.zhgd.hjReport.service.IHjReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/provider/baogao")
public class ReportApi extends BaseController {

    @Autowired
    IHjReportService iHjReportService;

    @RequestMapping(value = "/select")
    @ResponseBody
    public JSONObject select(@RequestBody HjReport hjReport){
        startPage();
        JSONObject jsonObject = new JSONObject();
        List<HjReport> result = iHjReportService.select(hjReport);
        TableDataInfo dataTable = getDataTable(result);
        jsonObject.put("total",dataTable.getTotal());
        jsonObject.put("msg", "查询成功");
        jsonObject.put("code", 1);
        jsonObject.put("data", dataTable);
        return jsonObject;
    }


    @PostMapping(value = "/add")
    public JSONObject add(@RequestBody HjReport hjReport){
        JSONObject jsonObject = new JSONObject();
        Integer result = iHjReportService.insterall(hjReport);
        if (result>0) {
            jsonObject.put("msg", "添加成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", result);
        }else{
            jsonObject.put("msg", "添加失败");
            jsonObject.put("code", 1);
            jsonObject.put("data", result);
        }
        return jsonObject;
    }

    @PostMapping(value = "/delete")
    public JSONObject delete(@RequestParam(value = "id") Integer id){
        JSONObject jsonObject = new JSONObject();
        Integer result = iHjReportService.delete(id);
        jsonObject.put("msg", "删除成功");
        jsonObject.put("code", 1);
        jsonObject.put("data", result);
        return jsonObject;
    }



    /**
     * 导出工作汇报
     * @return
     */
    @PostMapping("/export")
//    @ResponseBody
    public List<HjReportPc> export(@RequestBody HjReport hjReport)
    {
        return iHjReportService.selects(hjReport);
    }
}
