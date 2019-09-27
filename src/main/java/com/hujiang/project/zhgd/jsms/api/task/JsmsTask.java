package com.hujiang.project.zhgd.jsms.api.task;

import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import com.hujiang.project.zhgd.jsms.api.JSMSExample;
import com.hujiang.project.zhgd.shortCreedNumber.domain.ShortCreedNumber;
import com.hujiang.project.zhgd.shortCreedNumber.service.IShortCreedNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Component("jSmsTask")
@RestController
@RequestMapping(value = "/provider/jSmsTask",method = RequestMethod.POST)
public class JsmsTask {
    @Autowired
    private IShortCreedNumberService shortCreedNumberService;
    @Autowired
    private IHjProjectUserService projectUserService;
    @Autowired
    private IHjSystemUserService systemUserService;
    @Autowired
    private IHjProjectService projectService;

    private static final int OUTTEMPID = 168184;


//        @Scheduled(cron="0 0/5 * * * ? ")
    @PostMapping(value = "JSMS")
    public void JSMS() {
        List<ShortCreedNumber> shortCreedNumberList = shortCreedNumberService.selectShortCreedNumberList(null);
        List<HjProjectUser> hjProjectUserList = projectUserService.selectHjProjectUserList(null);  //查询这个项目下的所有人
        if (shortCreedNumberList == null || shortCreedNumberList.size() < 1) {
            return;
        }
        for (ShortCreedNumber shortCreedNumber : shortCreedNumberList) {
            List<HjProjectUser> myProjectUserList = hjProjectUserList.stream()
                    .filter(a -> a.getUserId().equals(shortCreedNumber.getProjectId()))
                    .collect(Collectors.toList());
            if (hjProjectUserList == null || hjProjectUserList.size() < 1) {
                return;
            }
            for (HjProjectUser h : myProjectUserList) {
                HjProject projectName = projectService.selectHjProjectById(h.getProjectId());
                HjSystemUser user = systemUserService.getByAlias(h.getUserId());
                Map<String, String> tempPara = new HashMap<>();
                tempPara.put("title", "短信条数不足");
                tempPara.put("project", projectName.getProjectName());  //站点
                tempPara.put("content", "短信条数已不足以发送短信，请联系管理人员及时充值！");   //内容
                //短信
                JSMSExample.testSendTemplateSMS(user.getUserPhone(), OUTTEMPID, tempPara);
            }
        }

    }

}
