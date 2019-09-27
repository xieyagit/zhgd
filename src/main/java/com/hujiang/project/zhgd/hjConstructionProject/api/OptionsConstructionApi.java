package com.hujiang.project.zhgd.hjConstructionProject.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.sbArea.domain.OptionsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/provider/OptionsConstructionApi",method = RequestMethod.POST)
public class OptionsConstructionApi extends BaseController {
    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;


    /**
     * 查询参建单位列表
     * @param projectId 项目id
     * @return
     */
    @PostMapping("/getConstructionList")
    public JSONObject getConstructionList(Integer projectId)
    {
        startPage();
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("projectId",projectId);
        JSONObject jsonObject = new JSONObject();
        startPage();
        List<HjConstructionCompany> constructionCompanyList= hjConstructionCompanyService.selectHjConstructionCompanyListTwo(map);
        TableDataInfo dataTable = getDataTable(constructionCompanyList);
        //处理分页数据
        List<OptionsUser> rows = (List<OptionsUser>)dataTable.getRows();
        if(rows!=null&& rows.size()>0){
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("total",dataTable.getTotal());//总记录数
            jsonObject.put("data",rows);
        }else {
            jsonObject.put("msg","查询失败");
            jsonObject.put("code",-1);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }
}
