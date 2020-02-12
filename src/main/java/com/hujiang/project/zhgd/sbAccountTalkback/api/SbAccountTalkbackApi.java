package com.hujiang.project.zhgd.sbAccountTalkback.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbAccountTalkback.domain.SbAccountTalkback;
import com.hujiang.project.zhgd.sbAccountTalkback.service.ISbAccountTalkbackService;
import com.hujiang.project.zhgd.sbGroupTalkback.domain.SbGroupTalkback;
import com.hujiang.project.zhgd.sbGroupTalkback.service.ISbGroupTalkbackService;
import com.hujiang.project.zhgd.sbGroupTalkback.ziputil.Zip;
import com.hujiang.project.zhgd.utils.FTPUtil;
import com.hujiang.project.zhgd.utils.Util;
import com.hujiang.project.zhgd.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取集团对讲列表
 */
@RestController
@RequestMapping(value = "/provider/sbAccountTalkback")
public class SbAccountTalkbackApi extends BaseController{
        @Autowired
        private ISbAccountTalkbackService sbAccountTalkbackService;


        @PostMapping("/getAccountListPage")
        public AjaxResult getAccountListPage(@RequestParam(value = "cpid") Integer cpid,@RequestParam(value = "isIdType") String isIdType,@RequestBody SbAccountTalkback sbAccountTalkback,@RequestParam(value = "pageSize") Integer pageSize,@RequestParam(value = "pageNum") Integer pageNum){
            Map<String ,String> map=new HashMap<>();
            map.put("cpid",cpid.toString());
            map.put("isIdType",isIdType);
            map.put("name",sbAccountTalkback.getAtName());
                startPage();
                List<SbAccountTalkback> atList=sbAccountTalkbackService.getAccountListPage(map);
            return AjaxResult.success(getDataTable(atList));
        }
}

