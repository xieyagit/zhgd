package com.hujiang.project.cay;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/provider",method = RequestMethod.POST)
public class cay {

    @Autowired
    private IHjProjectService iHjProjectService;

    @RequestMapping(value = "/cay",method = RequestMethod.POST)
    public JSONObject cay(@RequestParam("projectId") Integer projectId) throws IOException, URISyntaxException {
        JSONObject object = new JSONObject();
        HjProject hjProject = iHjProjectService.selectHjProjectById(projectId);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("curpage","1");
        jsonObject1.put("name",hjProject.getProjectName());
        /** 区管项目*/
        JSONObject jsonObject2 = ZCAPIClient.reportedCay2019s("authorize/getProjInfos",jsonObject1);
        if (jsonObject2.getString("jdbh")!=null){
            if (jsonObject2.getString("jdbh")!=null){
                object.put("jdbh",jsonObject2.getString("jdbh"));
                object.put("xmid",jsonObject2.getString("xmid"));
            } else {
                object.put("jdbh","");
                object.put("xmid","");
            }
        } else {
            /** 市管项目*/
            String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos",jsonObject1);
            if (xmid != null) {
                JSONObject j = new JSONObject();
                j.put("pguid", xmid);
                JSONObject object1 = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
                JSONArray data = object1.getJSONArray("res");
                JSONObject datas = data.getJSONObject(0);
                object.put("jdbh",datas.getString("jdbh"));
                object.put("xmid",datas.getString("xmid"));
                object.put("subId",datas.getString("gcid"));
            } else {
                object.put("jdbh","");
                object.put("xmid","");
                object.put("subId","");
            }
        }
        return object;
    }

    @RequestMapping(value = "/caySbDelete")
    public JSONObject delete(@RequestParam("sn") String sn,@RequestParam("xmid") String xmid,@RequestParam("devType") String devType,
                             @RequestParam("devCcrq") String devCcrq,@RequestParam("subId") String subId) throws IOException, URISyntaxException {
        JSONObject object = new JSONObject();
        JSONObject object1 = new JSONObject();
        object.put("ProjectID",xmid);
        object.put("Dev_Type",devType);
        object.put("Dev_GUID",sn);
        object.put("DEV_CCRQ",devCcrq);
        if (subId != null) {
            object.put("sub_id", subId);
            JSONArray array = new JSONArray();
            array.add(object);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PList",array);
            String f = ZCAPIClient.QGXMCAY("tower/dev_leave", jsonObject);
            object1.put("mag",f);
        }else{
            JSONArray array = new JSONArray();
            array.add(object);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PList",array);
            String f = ZCAPIClient.SGXMCAY("tower/dev_leave",jsonObject);
            object1.put("mag",f);
        }
        return object1;
    }
}
