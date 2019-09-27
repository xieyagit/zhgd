package com.hujiang.project.zhgd.sbVersion.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbVersion.domain.SbVersion;
import com.hujiang.project.zhgd.sbVersion.service.ISbVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/provider/versionApi",method = RequestMethod.POST)
public class VersionApi {
    @Autowired
    private ISbVersionService versionService;

    @PostMapping("/getVersion")
    public JSONObject getVersion(){
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        SbVersion sbVersion = versionService.selectSbVersion();
        if(sbVersion != null){
                jsonObject.put("versionName",sbVersion.getVersionName());
                jsonObject.put("versionNumber",sbVersion.getVersionNumber());
                jsonObject.put("versionContent",sbVersion.getVersionContent());
                jsonObject.put("url",sbVersion.getUrl());
                jsonObject.put("isMandatoryUpgrade",sbVersion.getIsMandatoryUpgrade()==1?true:false);
            result.put("msg","查询成功");
            result.put("code",0);
            result.put("data",jsonObject);
        }else {
            result.put("msg","查询失败");
            result.put("code",-1);
            result.put("data",jsonObject);
        }
        return result;
    }
}
