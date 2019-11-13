package com.hujiang.project.zhgd.hjSafetyAbarbeitung.task;

import com.google.gson.JsonObject;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjExcessiveSafety.domain.HjExcessiveSafety;
import com.hujiang.project.zhgd.hjExcessiveSafety.service.IHjExcessiveSafetyService;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.HjSafetyAbarbeitung;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.HjSafetyNoPass;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.Project;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.service.IHjSafetyAbarbeitungService;
import com.hujiang.project.zhgd.hjSafetyArea.domain.HjSafetyArea;
import com.hujiang.project.zhgd.hjSafetyArea.service.IHjSafetyAreaService;
import com.hujiang.project.zhgd.hjSafetyCommission.domain.HjSafetyCommission;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import com.hujiang.project.zhgd.jpush.api.examples.PushExample;
import com.hujiang.project.zhgd.jpush.api.push.model.PushPayload;
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

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("SafetyTask")
//@RestController
//@RequestMapping(value = "/provider/safetyTask/",method = RequestMethod.POST)
public class SafetyTask extends AutoTaskBase {
    @Autowired
    private IHjSafetyAbarbeitungService safetyAbarbeitungService;
    @Autowired
    private IHjSystemUserService systemUserService;
    @Autowired
    private IHjSafetyAreaService safetyAreaService;
    @Autowired
    private IShortCreedNumberService shortCreedNumberService;
    @Autowired
    private IHjExcessiveSafetyService excessiveSafetyService;

    @Scheduled(cron="0 15 10 * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    belongCalendar();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
    //短信模板ID
    private static final int TEMPID = 167973;

    //    @PostMapping(value = "/belongCalendar")
    public void belongCalendar() throws ParseException {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HjSafetyAbarbeitung hjSafetyAbarbeitung = new HjSafetyAbarbeitung();
        hjSafetyAbarbeitung.setStatus(1);
        List<HjSafetyAbarbeitung> hjSafetyAbarbeitungList = safetyAbarbeitungService.selectHjSafetyAbarbeitungList(hjSafetyAbarbeitung);
        for(HjSafetyAbarbeitung safetyAbarbeitung:hjSafetyAbarbeitungList){
            HjSafetyNoPass hjSafetyNoPassList = safetyAbarbeitungService.getAfterRectificationNoPass(safetyAbarbeitung.getId());
            //设置当前时间
            Calendar date = Calendar.getInstance();
            date.setTime(sp.parse(sp.format(new Date())));
            //设置开始时间
            Calendar begin = Calendar.getInstance();
            begin.setTime(sp.parse(hjSafetyNoPassList.getSafetyCreateTime()));
            //设置结束时间
            Calendar end = Calendar.getInstance();
            end.setTime(sp.parse(safetyAbarbeitung.getSafetyDeadline()));
            //处于开始时间之后，和结束时间之前的判断
            if (date.after(begin) && date.before(end)) {
                continue;
            } else {
                HjSafetyAbarbeitung hjSafetyAbarbeitung1 = new HjSafetyAbarbeitung();
                hjSafetyAbarbeitung1.setStatus(4);
                hjSafetyAbarbeitung1.setId(safetyAbarbeitung.getId());
                int temp = safetyAbarbeitungService.alterStatus(hjSafetyAbarbeitung1);
                if (temp>0) {
                    HjSafetyArea safetyArea = safetyAreaService.selectHjSafetyAreaById(safetyAbarbeitung.getAreaId());
                    HjSystemUser initiator = systemUserService.getByAlias(safetyAbarbeitung.getInitiatorId());  //发起整改人
                    Project project = safetyAbarbeitungService.getProject(hjSafetyAbarbeitung.getConstructionId());
                    String title = "安全整改通知消息";
                    String alert = "\n"+safetyArea.getArea()
                            +"有一条整改单超时未整改，请及时处理";
                    if (!"".equals(safetyAbarbeitung.getInitiatorId()) && safetyAbarbeitung.getInitiatorId() != null) {
                        if (initiator.getAlias() != null) {
                            ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
                            shortCreedNumber.setProjectId(project.getProjectId());
                            ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);
                            if(shortCreedNumberList.getNoteNumber()>0) {
                                Map<String,String> tempPara = new HashMap<>();
                                tempPara.put("title",title);
                                tempPara.put("site",safetyArea.getArea());  //站点
                                tempPara.put("content", "有一条整改单超时未整改，请及时处理");   //内容
                                //短信
                                JSMSExample.testSendTemplateSMS(initiator.getUserPhone(),TEMPID,tempPara);
                                //更改剩余次数
                                ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                                shortCreedNumber1.setProjectId(project.getProjectId());
                                shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber()-1);
                                shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);
                                //保存巡检短信
                                HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
                                hjExcessiveSafety.setProjectId(project.getProjectId());
                                hjExcessiveSafety.setUserId(safetyAbarbeitung.getInitiatorId());
                                hjExcessiveSafety.setContent(title+":"+alert);
                                hjExcessiveSafety.setCreateTime(sp.format(new Date()));
                                excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
                            }

                        }
                    }
                }
            }
        }
    }
}
