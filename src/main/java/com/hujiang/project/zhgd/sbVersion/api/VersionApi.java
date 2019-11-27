package com.hujiang.project.zhgd.sbVersion.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbVersion.domain.SbVersion;
import com.hujiang.project.zhgd.sbVersion.service.ISbVersionService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/provider/versionApi", method = RequestMethod.POST)
public class VersionApi {
    @Autowired
    private ISbVersionService versionService;

    @PostMapping("/getVersion")
    public JSONObject getVersion() {
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        SbVersion sbVersion = versionService.selectSbVersion();
        if (sbVersion != null) {
            jsonObject.put("versionName", sbVersion.getVersionName());
            jsonObject.put("versionNumber", sbVersion.getVersionNumber());
            jsonObject.put("versionContent", sbVersion.getVersionContent());
            jsonObject.put("url", sbVersion.getUrl());
            jsonObject.put("isMandatoryUpgrade", sbVersion.getIsMandatoryUpgrade() == 1 ? true : false);
            result.put("msg", "查询成功");
            result.put("code", 0);
            result.put("data", jsonObject);
        } else {
            result.put("msg", "查询失败");
            result.put("code", -1);
            result.put("data", jsonObject);
        }
        return result;
    }

    @PostMapping(value = "/updateSbVersion")
    public AjaxResult updateSbVersion(SbVersion sbVersion, MultipartFile file) {
        if (file != null) {
            String photoName = file.getOriginalFilename();
            try {
                String url = AliyunOSSClientUtil.uploadFileImg(file, "android/apk/", photoName);
                sbVersion.setUrl(url);
                sbVersion.setCreateTime(DateUtils.getTime());
                versionService.insertSbVersion(sbVersion);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return AjaxResult.success();
        } else {
            return AjaxResult.error("没有apk文件");
        }
    }

    @PostMapping(value = "/deleteSbVersion")
    public AjaxResult deleteSbVersionById(Integer id) {
        SbVersion sbVersion = versionService.selectSbVersionById(id);
        if (sbVersion != null) {
            AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", sbVersion.getUrl());
            versionService.deleteSbVersionById(id);
            return AjaxResult.success();
        } else {
            return AjaxResult.error("没有版本信息");
        }
    }

}
