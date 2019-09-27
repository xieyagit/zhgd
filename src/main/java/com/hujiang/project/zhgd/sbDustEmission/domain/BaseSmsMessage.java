package com.hujiang.project.zhgd.sbDustEmission.domain;

import com.hujiang.project.zhgd.hjExcessiveSafety.domain.HjExcessiveSafety;
import com.hujiang.project.zhgd.hjExcessiveSafety.service.IHjExcessiveSafetyService;
import com.hujiang.project.zhgd.jsms.api.JSMSExample;
import com.hujiang.project.zhgd.sbExcessiveDust.domain.SbExcessiveDust;
import com.hujiang.project.zhgd.sbExcessiveDust.service.ISbExcessiveDustService;
import com.hujiang.project.zhgd.shortCreedNumber.domain.ShortCreedNumber;
import com.hujiang.project.zhgd.shortCreedNumber.service.IShortCreedNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public abstract class BaseSmsMessage<T> {

    private static final String GRADE="严重";

    public Integer getPrivilegesId() {
        return privilegesId;
    }

    protected void setPrivilegesId(Integer privilegesId) {
        this.privilegesId = privilegesId;
    }

    private Integer privilegesId;

    @Autowired
    protected IHjExcessiveSafetyService excessiveSafetyService;

    @Autowired
    private ISbExcessiveDustService excessiveDustService;

    @Autowired
    private IShortCreedNumberService shortCreedNumberService;

    public abstract void push(Integer userId, Integer projectId, String alias,
                              boolean isApnsProduction, String userPhone);


    /**
     *  将推送消息保存到数据库方法
     */
    protected void saveExcessive(String saveTitle,String sn,Integer projectId,Integer userId,String saveAlert, Integer privilegesId){

        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SbExcessiveDust sbExcessiveDust = new SbExcessiveDust();
        sbExcessiveDust.setTitle(saveTitle);
        sbExcessiveDust.setProjectId(projectId);
        sbExcessiveDust.setSn(sn);
        sbExcessiveDust.setUserId(userId);
        sbExcessiveDust.setAlert(saveAlert);
        sbExcessiveDust.setCreateTime(sp.format(new Date()));
        sbExcessiveDust.setGrade(GRADE);
        sbExcessiveDust.setPrivilegesId(privilegesId);
        sbExcessiveDust.setStatus(1);
        excessiveDustService.insertSbExcessiveDust(sbExcessiveDust);
    }

    /**
     *  将短信消息保存到数据库方法
     */
    protected void saveExcessiveSafety(Integer projectId,Integer userId,String format, String content){

        //保存扬尘短信信息
        HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
        hjExcessiveSafety.setProjectId(projectId);
        hjExcessiveSafety.setUserId(userId);
        hjExcessiveSafety.setContent(content);
        hjExcessiveSafety.setCreateTime(format);
        excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
    }

    protected void shortCreedNumber(String userPhone, Integer projectId, Integer userId, Map<String,String> tempPara, Integer tempId, String content){
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sp.format(new Date());
        ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
        shortCreedNumber.setProjectId(projectId);
        ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);   //根据项目Id查询项目是否有购买短信条数
        if(shortCreedNumberList!=null) {
            if (shortCreedNumberList.getNoteNumber() != null && shortCreedNumberList.getNoteNumber() > 0) {     //判断是否还有剩余条数
                //电话号码等于11位
                if (userPhone.length() == 11) {
                    //发送短信
                    JSMSExample.testSendTemplateSMS(userPhone, tempId, tempPara);

                    ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                    shortCreedNumber1.setProjectId(projectId);
                    shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber() - 1);  //根据项目Id扣除短信条数
                    shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);
                    this.saveExcessiveSafety(projectId, userId, format, content);    //保存发送的短信记录
                } else {
                    return;
                }
            }
        }

    }
}
