package com.hujiang.project.zhgd.sbGroupTalkback.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.zhgd.sbGroupTalkback.domain.SbGroupTalkback;
import com.hujiang.project.zhgd.sbGroupTalkback.service.ISbGroupTalkbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 获取集团对讲列表
 */
@RestController
@RequestMapping(value = "/provider/sbGroupTalkback",method = RequestMethod.POST)
public class SbGroupTalkbackApi extends BaseController{
        @Autowired
        private ISbGroupTalkbackService sbGroupTalkbackService;


        @PostMapping("/getAccountList")
        public List<SbGroupTalkback> getAccountList(@RequestBody SbGroupTalkback sbGroupTalkback){

            return sbGroupTalkbackService.getAccountList(sbGroupTalkback);
        }



}

