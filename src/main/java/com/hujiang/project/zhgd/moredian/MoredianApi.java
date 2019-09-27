package com.hujiang.project.zhgd.moredian;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import com.hujiang.project.zhgd.moredian.moredianOrgDevice.domain.MoredianOrgDevice;
import com.hujiang.project.zhgd.moredian.moredianOrgDevice.service.IMoredianOrgDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 魔点设备
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-17 16:54
 **/
@RestController
@RequestMapping(value = "api/moredian")
public class MoredianApi {

    @Autowired
    private IMoredianOrgDeviceService orgDeviceService;
    @Autowired
    private IMoredianOrgService orgService;


    /**
     * 获取设备密码
     * @return
     */
    @GetMapping("getMorePassword")
    public String getMorePassword(String sn,String timestamp){


        //根据sn获取设备及设备所属机构
        MoredianOrgDevice orgDevice = new MoredianOrgDevice();
        orgDevice.setDeviceSn(sn);
        List<MoredianOrgDevice> moredianOrgDevices = orgDeviceService.selectMoredianOrgDeviceList(orgDevice);
        if(moredianOrgDevices!=null&&moredianOrgDevices.size()>0){
            MoredianOrgDevice moredianOrgDevice = moredianOrgDevices.get(0);
            //根据机构id获取机构信息
            MoredianOrg org = new MoredianOrg();
            org.setId(moredianOrgDevice.getOrgId());
            List<MoredianOrg> moredianOrgs = orgService.selectMoredianOrgList(org);
            if(moredianOrgs!=null && moredianOrgs.size()>0){
                MoredianOrg org1 = moredianOrgs.get(0);
                //获取机构accessToken
                String orgAccessToken = MoredianMethod.getOrgAccessToken(org1.getOrgAuthKey(), org1.getOrgId());
                //获取设备密码
                JSONObject object = new JSONObject();
                object.put("deviceId",moredianOrgDevice.getDeviceId());
                object.put("timestamp",timestamp);
                JSONObject dynamicPwd = MoredianMethod.getDynamicPwd(orgAccessToken, object);
                if(dynamicPwd.getString("message").equals("操作成功")){
                    return dynamicPwd.getString("data");
                }
            }
        }
        return "{msg:设备sn错误}";
    }
}
