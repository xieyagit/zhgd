package com.hujiang.project.zhgd.options;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import com.hujiang.project.zhgd.moduleToPush.service.IModuleToPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/OptionsPushApi",method = RequestMethod.POST)
public class OptionsPushApi {
    @Autowired
    private IModuleToPushService moduleToPushService;

    @PostMapping(value = "/optionsPush")
    public JSONObject optionsPush(@RequestParam(value = "onOff")Integer onOff,
                                  @RequestParam(value = "userId")Integer userId,
                                  @RequestParam(value = "privilegesId")Integer privilegesId) {
        JSONObject jsonObject = new JSONObject();
        //开
        if (onOff == 1) {
            int open = 0;
            ModuleToPush moduleToPush = new ModuleToPush();
            moduleToPush.setUserId(userId);
            moduleToPush.setPrivilegesId(privilegesId);
            ModuleToPush moduleToPushes = moduleToPushService.selectModuleToPush(moduleToPush);
            if (moduleToPushes != null) {
                ModuleToPush opens = new ModuleToPush();
                opens.setPrivilegesId(privilegesId);
                opens.setUserId(userId);
                opens.setOnOff(onOff);
                open = moduleToPushService.updateModuleToPush(opens);

            }else {
                ModuleToPush opens = new ModuleToPush();
                opens.setPrivilegesId(privilegesId);
                opens.setUserId(userId);
                opens.setOnOff(onOff);
                open = moduleToPushService.insertModuleToPush(opens);
            }
            if (open > 0) {
                jsonObject.put("msg", "推送已开启");
                jsonObject.put("code", 0);
            }else {
                jsonObject.put("msg", "推送开启失败");
                jsonObject.put("code", -1);
            }
        } else {
            int close = 0;
            ModuleToPush moduleToPush = new ModuleToPush();
            moduleToPush.setUserId(userId);
            moduleToPush.setPrivilegesId(privilegesId);
            List<ModuleToPush> moduleToPushes = moduleToPushService.selectModuleToPushList(moduleToPush);
            if (moduleToPushes != null && moduleToPushes.size() > 0) {
                ModuleToPush closes = new ModuleToPush();
                closes.setPrivilegesId(privilegesId);
                closes.setUserId(userId);
                closes.setOnOff(onOff);
                close = moduleToPushService.updateModuleToPush(closes);

            }else {
                ModuleToPush closes = new ModuleToPush();
                closes.setPrivilegesId(privilegesId);
                closes.setUserId(userId);
                closes.setOnOff(onOff);
                close = moduleToPushService.insertModuleToPush(closes);
            }
            if (close > 0) {
                jsonObject.put("msg", "推送已关闭");
                jsonObject.put("code", 0);
            }else {
                jsonObject.put("msg", "推送关闭失败");
                jsonObject.put("code", -1);
            }
        }
        return jsonObject;
    }
}
