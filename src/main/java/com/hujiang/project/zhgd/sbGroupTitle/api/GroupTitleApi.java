package com.hujiang.project.zhgd.sbGroupTitle.api;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbGroupTitle.domain.SbGroupTitle;
import com.hujiang.project.zhgd.sbGroupTitle.service.ISbGroupTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/provider/groupTitleApi")
public class GroupTitleApi {
    @Autowired
    private ISbGroupTitleService sbGroupTitleService;
    @PostMapping(value = "/getTitle")
    public AjaxResult getTitle(@RequestBody SbGroupTitle groupTitle){

        return AjaxResult.success(sbGroupTitleService.selectSbGroupTitleList(groupTitle));
    }
}
