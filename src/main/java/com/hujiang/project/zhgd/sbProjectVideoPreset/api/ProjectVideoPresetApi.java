package com.hujiang.project.zhgd.sbProjectVideoPreset.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.sbProjectVideo.service.ISbProjectVideoService;
import com.hujiang.project.zhgd.sbProjectVideoPreset.domain.SbProjectVideoPreset;
import com.hujiang.project.zhgd.sbProjectVideoPreset.service.ISbProjectVideoPresetService;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-23 14:03
 **/
@RestController
@RequestMapping(value = "/provider/ProjectVideoPresetApi",method = RequestMethod.POST)
public class ProjectVideoPresetApi extends BaseController {

    @Resource
    private YsUtil ysUtil;
    @Autowired
    private ISbProjectVideoPresetService sbProjectVideoPresetService;
/**
 * 新增预置点
 */
    @PostMapping("/insertPreset")
    public AjaxResult insertPreset(Integer pid, String deviceSerial) throws Exception{
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("accessToken",ysUtil.getAccessToken2(pid) ));
        params.add(new BasicNameValuePair("deviceSerial",deviceSerial));
        params.add(new BasicNameValuePair("channelNo","1"));
        String result=   ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP +"lapp/device/preset/add",params);
        JSONObject s=JSONObject.parseObject(result);
        String code=s.getString("code");
        if("200".equals(code)){
            Integer index=s.getJSONObject("data").getInteger("index");
            SbProjectVideoPreset sp=new SbProjectVideoPreset();
            sp.setPresetIndex(index);
            sp.setPresetName("预置点"+index);
            sp.setVideoSn(deviceSerial);
            sbProjectVideoPresetService.insertSbProjectVideoPreset(sp);
            return AjaxResult.success("添加预置点成功！");
        }

        return AjaxResult.error("添加预置点失败！");
    }
    /**
     * 清除预置点
     */
    @PostMapping("/clearPreset")
    public AjaxResult clearPreset(Integer pid, String deviceSerial,Integer  index) throws Exception{
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("accessToken",ysUtil.getAccessToken2(pid) ));
        params.add(new BasicNameValuePair("deviceSerial",deviceSerial));
        params.add(new BasicNameValuePair("index",index.toString()));
        params.add(new BasicNameValuePair("channelNo","1"));
        String result=   ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP +"lapp/device/preset/clear",params);
        JSONObject s=JSONObject.parseObject(result);
        String code=s.getString("code");
        if("200".equals(code)){
            SbProjectVideoPreset sp=new SbProjectVideoPreset();
            sp.setPresetIndex(index);
            sp.setVideoSn(deviceSerial);
            sbProjectVideoPresetService.deleteSbProjectVideoPreset(sp);
            return AjaxResult.success("清除预置点成功！");
        }
        return AjaxResult.error("清除预置点失败！");
    }
    /**
     * 查询预置点
     */
    @PostMapping("/selectPreset")
    public AjaxResult selectPreset(@RequestBody SbProjectVideoPreset sp)  {
       List<SbProjectVideoPreset> spList=sbProjectVideoPresetService.selectSbProjectVideoPresetList(sp);
return AjaxResult.success(spList);
    }
    /**
     * 清除预置点
     */
    @PostMapping("/callPreset")
    public void callPreset(Integer pid, String deviceSerial,Integer  index) throws Exception {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("accessToken", ysUtil.getAccessToken2(pid)));
        params.add(new BasicNameValuePair("deviceSerial", deviceSerial));
        params.add(new BasicNameValuePair("index", index.toString()));
        params.add(new BasicNameValuePair("channelNo", "1"));
        String result = ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP + "lapp/device/preset/move", params);
    }
}
