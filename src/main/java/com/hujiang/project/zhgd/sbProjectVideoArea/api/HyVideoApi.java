package com.hujiang.project.zhgd.sbProjectVideoArea.api;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hyvideo")
public class HyVideoApi {
    @Autowired
    private ISbProjectVideoAreaService sbProjectVideoAreaService;
    @PostMapping("/getVideoList")
    public AjaxResult getVideoList(Integer cid){
        return AjaxResult.success(sbProjectVideoAreaService.getPvideoList(cid));
    }
}
