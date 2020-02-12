package com.hujiang.project.zhgd.sbProjectVideo.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.lc.util.LcUtil;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.HjSynchronizationInformationServiceImpl;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import com.hujiang.project.zhgd.sbProjectVideo.service.ISbProjectVideoService;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.Video;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Tools;
import com.hujiang.project.zhgd.utils.ZCAPIClientTwo;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-23 14:10
 **/
@RestController
@RequestMapping(value = "/provider/ProjectVideo",method = RequestMethod.POST)
public class ProjectVideo extends BaseController {

    @Autowired
    private ISbProjectVideoService videoService;
    @Resource
    private YsUtil ysUtil;
    @Resource
    private LcUtil lcUtil;
    @Autowired
    private IHjSynchronizationInformationService synchronizationInformationService;
    /**
     * 根据项目视频区id获取视频信息
     * @param areaId
     * @return
     */
    @PostMapping("getProjectVideo")
    public JSONObject getProjectVideo(@RequestParam(value = "areaId") Integer areaId){
        SbProjectVideo video = new SbProjectVideo();
        video.setAreaId(areaId);
        List<SbProjectVideo> sbProjectVideos = videoService.selectSbProjectVideoList(video);
        JSONObject result = new JSONObject();

        if(sbProjectVideos!=null && sbProjectVideos.size()>0){
            result.put("msg","查询成功");
            result.put("code",0);
            result.put("data",sbProjectVideos);
        }else{
            result.put("msg","查询成功,该视频区无设备");
            result.put("code",0);
        }
        return result;
    }

    /**
     * 删除项目视频
     */
    @PostMapping( "/deleteSbProjectVideoByIds")
    public AjaxResult deleteSbProjectVideoByIds(@RequestParam(value = "ids") String ids)
    {
        return toAjax(videoService.deleteSbProjectVideoByIds(ids));
    }

    /**
     * 查询工区视频列表
     */
    @PostMapping("/projectVideoList")
    public AjaxResult projectVideoList(@RequestBody SbProjectVideo sbProjectVideo)
    {
        startPage();
        List<SbProjectVideo> list = videoService.selectSbProjectVideoList(sbProjectVideo);
        return AjaxResult.success(getDataTable(list));
    }
    /**
     * 查询项目所有视频列表
     */
    @PostMapping("/selectSbProjectVideoByProjectId")
    public AjaxResult selectSbProjectVideoByProjectId(@RequestParam(value = "projectId") Integer projectId)
    {
        startPage();
        List<SbProjectVideo> list = videoService.selectSbProjectVideoByProjectId(projectId);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 新增保存项目视频
     */
    @PostMapping("/projectVideoAddSave")
    public AjaxResult  projectVideoAddSave(@RequestBody SbProjectVideo sbProjectVideo)
    {
        return toAjax(videoService.insertSbProjectVideo(sbProjectVideo));
    }

    /**
     * 根据id查询视频信息
     */
    @PostMapping("/selectProjectVideoById")
    public AjaxResult selectProjectVideoById(@RequestParam(value = "id") Integer id)
    {
        return AjaxResult.success(videoService.selectSbProjectVideoById(id));
    }

    /**
     * 修改保存项目视频
     */
    @PostMapping("/projectVideoEditSave")
    public AjaxResult projectVideoEditSave(@RequestBody SbProjectVideo sbProjectVideo)
    {
        return toAjax(videoService.updateSbProjectVideo(sbProjectVideo));
    }

    /**
     * 云端控制
     * @param pid
     * @param deviceSerial
     * @param direction
     */
    @PostMapping("/ysCloudControldirection")
    public void ysCloudControldirection(@RequestParam(value = "pid") Integer pid, @RequestParam(value = "deviceSerial") String deviceSerial, @RequestParam(value = "direction") Integer direction, @RequestBody Video video) throws  Exception{
        SbProjectVideo sbProjectVideo=new SbProjectVideo();
        sbProjectVideo.setVideoSn(deviceSerial);
        List<SbProjectVideo> spvList=videoService.selectSbProjectVideoList(sbProjectVideo);
        if(spvList.size()>0) {
            if("HIK".equals(spvList.get(0).getFactory())) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("accessToken", ysUtil.getAccessToken2(pid)));
                params.add(new BasicNameValuePair("deviceSerial", deviceSerial));
                if(!"".equals(video.getChannelNo())&&video.getChannelNo()!=null){
                    params.add(new BasicNameValuePair("channelNo", video.getChannelNo()));
                }else{
                    params.add(new BasicNameValuePair("channelNo", "1"));
                }

                params.add(new BasicNameValuePair("speed", "1"));
                params.add(new BasicNameValuePair("direction", direction.toString()));
                ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP + "lapp/device/ptz/start", params);

                ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP + "lapp/device/ptz/stop", params);
            }else{
                HjSynchronizationInformation hs=new HjSynchronizationInformation();
                hs.setProjectId(pid);
                hs.setPlatformName("LCGETACCESSTOKEN");
                List<HjSynchronizationInformation> hsList=synchronizationInformationService.selectHjSynchronizationInformationList(hs);
                if(hsList.size()>0){
                    HjSynchronizationInformation h=hsList.get(0);
                    Long time=(long)(new Date().getTime()/1000);
                    String nonce=Tools.encodeToMD5(time.toString());
                    JSONObject d=new JSONObject();
                    JSONObject json=new JSONObject();
                    json.put("ver","1.0");
                    json.put("sign",lcUtil.getSign(h,time,nonce));
                    json.put("appId",h.getApiKey());
                    json.put("time",time.toString());
                    json.put("nonce",nonce);
                    JSONObject params=new JSONObject();
                    params.put("deviceId",deviceSerial);
                    params.put("token",lcUtil.getToken(h,time, Tools.encodeToMD5(deviceSerial+time)));
                    if(!"".equals(video.getChannelNo())&&video.getChannelNo()!=null) {
                        params.put("channelId", video.getChannelNo());
                    }else{
                        params.put("channelId", "0");
                    }
                    params.put("operation",direction.toString());
                    params.put("duration",300);
                    d.put("system",json);
                    d.put("params",params);
                    d.put("id",nonce);
                    ZCAPIClientTwo.httpPostWithJSON(Constants.OPEN_LC+"controlMovePTZ",d);

                }

            }
        }
    }

    /**
     * 更新摄像头坐标
     * @param sbProjectVideo
     * @return
     */
    @PostMapping("/updateVideoCoordinate")
    public AjaxResult updateVideoCoordinate(@RequestBody SbProjectVideo sbProjectVideo){
        int i=videoService.updateVideoCoordinate(sbProjectVideo);
        if(i>0){
            return  AjaxResult.success("坐标更新成功");
        }
        return  AjaxResult.error("坐标更新失败");
    }
}
