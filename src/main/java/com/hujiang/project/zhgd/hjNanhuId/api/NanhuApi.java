package com.hujiang.project.zhgd.hjNanhuId.api;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjNanhuId.domain.HjNanhuId;
import com.hujiang.project.zhgd.hjNanhuId.service.IHjNanhuIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nanhuApi")
public class NanhuApi {
    @Autowired
    private IHjNanhuIdService hjNanhuIdService;
    @PostMapping("/getProjectId")
    public AjaxResult getProjectId(HjNanhuId hjNanhuId){
        List<HjNanhuId> list=hjNanhuIdService.selectHjNanhuIdList(hjNanhuId);
        if(list.size()>0){
         return    AjaxResult.success(list.get(0));
        }
        return AjaxResult.error("获取失败");
    }
}
