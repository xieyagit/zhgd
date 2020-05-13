package com.hujiang.project.zhgd.sbProjectVideoArea.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.lc.util.LcUtil;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.ProjectVideoJT;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbJTArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbProjectVideoArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.Video;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
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
 * @create: 2019-06-23 14:03
 **/
@RestController
@RequestMapping(value = "/provider/ProjectVideoAreaApi",method = RequestMethod.POST)
public class ProjectVideoAreaApi extends BaseController {
    @Autowired
    private ISbProjectVideoAreaService areaService;
    @Resource
    private YsUtil ysUtil;
    @Resource
    private LcUtil lcUtil;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    /**
     * 根据项目id获取项目视频区
     * @param projectId
     * @return
     */
    @PostMapping("getProjectVideoArea")
    public JSONObject getProjectVideoArea(@RequestParam(value = "projectId") Integer projectId){
        SbProjectVideoArea videoArea = new SbProjectVideoArea();
        videoArea.setProjectid(projectId);
        List<SbProjectVideoArea> sbProjectVideoAreas = areaService.selectSbProjectVideoAreaList(videoArea);
        JSONObject result = new JSONObject();
        if(sbProjectVideoAreas!=null && sbProjectVideoAreas.size()>0){
            result.put("msg","查询成功");
            result.put("code",0);
            result.put("data",sbProjectVideoAreas);
        }else{
            result.put("msg","查询成功,该项目无视频区");
            result.put("code",0);
        }
        return result;
    }


    /**
     * 查询项目视频区列表
     */
    @PostMapping("/list")
    public AjaxResult projectVideoAreaList(@RequestBody SbProjectVideoArea sbProjectVideoArea,
                                           @RequestParam(value = "pageNum")Integer pageNum,
                                           @RequestParam(value = "pageSize")Integer pageSize)
    {
        startPage();
        List<SbProjectVideoArea> list = areaService.selectSbProjectVideoAreaList(sbProjectVideoArea);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 新增保存项目视频区
     */
    @PostMapping("/add")
    public AjaxResult projectVideoAreaAddSave(@RequestBody SbProjectVideoArea sbProjectVideoArea)
    {
        int i = areaService.insertSbProjectVideoArea(sbProjectVideoArea);
        if(i>0){
            return success();
        }
        return error(-1,"添加失败");
    }
    /**
     * 修改前查询
     */
    @PostMapping("/edit")
    public AjaxResult projectVideoAreaEdit(Integer id)
    {
        return AjaxResult.success(areaService.selectSbProjectVideoAreaById(id));
    }

    /**
     * 修改保存项目视频区
     */
    @PostMapping("/editSave")
    public AjaxResult projectVideoAreaEditSave(@RequestBody SbProjectVideoArea sbProjectVideoArea)
    {
        int i = areaService.updateSbProjectVideoArea(sbProjectVideoArea);
        if(i>0){
            return success();
        }
        return error(-1,"修改失败");
    }

    /**
     * 删除项目视频区
     */
    @PostMapping( "/remove")
    public AjaxResult projectVideoAreaRemove(String ids)
    {
        int i = areaService.deleteSbProjectVideoAreaByIds(ids);
        if(i>0){
            return success();
        }
        return error(-1,"删除失败");
    }

/**
 * 集团获取项目列表
 */
    @PostMapping("/getVideoListJT")
    public List<SbJTArea> getVideoListJT(Integer cid) throws  Exception{
        List<SbJTArea> saList=areaService.getVideoListJT(cid);
        String token="";
        List<NameValuePair> params;
        List<Video> vList=new ArrayList<Video>();
        for(int i=0;i<saList.size();i++){
            List<ProjectVideoJT> projectList=saList.get(i).getProjectList();
            for(int j=0;j<projectList.size();j++){
                Integer pid=projectList.get(j).getPid();
                token=ysUtil.getAccessToken2(pid);
                projectList.get(j).setToken(token);
            }

        }
        return saList;
    }
    /**
     * 集团获取项目摄像头是否在线状态
     */
    @PostMapping("/getVideoListImgUrl")
    public List<Video> getVideoListImgUrl(Integer cid) throws  Exception{
        List<SbJTArea> saList=areaService.getVideoListJT(cid);

        List<NameValuePair> params;
        List<Video> vList=new ArrayList<Video>();
        for(int i=0;i<saList.size();i++) {
            List<ProjectVideoJT> projectList = saList.get(i).getProjectList();
            for (int j = 0; j < projectList.size(); j++) {
                Integer pid = projectList.get(j).getPid();
                String token = ysUtil.getAccessToken2(pid);
                projectList.get(j).setToken(token);
                List<Video> videoList = projectList.get(j).getVideoList();
                for (int n = 0; n < videoList.size(); n++) {
                    String sn = videoList.get(n).getVideoSn();
//                    String channelNo=videoList.get(n).getChannelNo();
                    if ("HIK".equals(videoList.get(n).getFactory())) {
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("accessToken", token));
                        params.add(new BasicNameValuePair("deviceSerial", sn));
//                    params.add(new BasicNameValuePair("channelNo","1"));
                        String result = ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP + "lapp/device/info", params);
                        JSONObject s = JSONObject.parseObject(result);
                        if ("200".equals(s.getString("code"))) {
                            JSONObject data = s.getJSONObject("data");
                            videoList.get(n).setIsStatus(data.getString("status"));
//                        videoList.get(n).setPicUrl(data.getString("picUrl"));
                        } else {
//                        videoList.get(n).setPicUrl("");
                            videoList.get(n).setIsStatus("0");
                        }
                        vList.add(videoList.get(n));
                    } else {
                        HjSynchronizationInformation hs = new HjSynchronizationInformation();
                        hs.setProjectId(pid);
                        hs.setPlatformName("LCGETACCESSTOKEN");
                        List<HjSynchronizationInformation> hsList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
                        if (hsList.size() > 0) {
                            HjSynchronizationInformation h = hsList.get(0);
                            Long time = (long) (new Date().getTime() / 1000);
                            String nonce = Tools.encodeToMD5(time.toString());
                            JSONObject d = new JSONObject();
                            JSONObject json = new JSONObject();
                            json.put("ver", "1.0");
                            json.put("sign", lcUtil.getSign(h, time, nonce));
                            json.put("appId", h.getApiKey());
                            json.put("time", time.toString());
                            json.put("nonce", nonce);
                            JSONObject param = new JSONObject();
                            param.put("deviceId", sn);
                            param.put("token", lcUtil.getToken(h, time, Tools.encodeToMD5(sn + time)));
//                            param.put("channelId", channelNo);
                            d.put("system", json);
                            d.put("params", param);
                            d.put("id", nonce);
                            System.out.println(d.toString());
                            String s = ZCAPIClientTwo.httpPostWithJSON(Constants.OPEN_LC + "deviceOnline", d);
                            JSONObject result=JSONObject.parseObject(s).getJSONObject("result");
                            if("0".equals(result.getString("code"))){
                                videoList.get(n).setIsStatus(result.getJSONObject("data").getString("onLine"));
                            }else{
                                videoList.get(n).setIsStatus("0");
                            }
                            vList.add(videoList.get(n));
                        }
                    }

                }

            }
        }
        return vList;
    }
    /**
     * 集团获取项目列表
     */
    @PostMapping("/getVideoProject")
    public ProjectVideoJT getVideoProject(Integer pid) throws  Exception{
        ProjectVideoJT saList=areaService.getVideoList(pid);
        String token=ysUtil.getAccessToken2(pid);
        saList.setToken(token);

        return saList;
    }

    /**
     * 项目获取项目列表
     */
    @PostMapping("/getProjectVideoImgUrl")
    public List<Video> getProjectVideoImgUrl(Integer pid) throws  Exception{
        ProjectVideoJT saList=areaService.getVideoList(pid);

        List<NameValuePair> params;
        List<Video> vList=new ArrayList<Video>();
//        for(int i=0;i<saList.size();i++) {
//            List<ProjectVideoJT> projectList = saList.get(i).getProjectList();
//            for (int j = 0; j < projectList.size(); j++) {
//                Integer projectId = saList.getPid();
        String token = ysUtil.getAccessToken2(pid);
        saList.setToken(token);
        List<Video> videoList = saList.getVideoList();
        for (int n = 0; n < videoList.size(); n++) {
            String sn = videoList.get(n).getVideoSn();
            if ("HIK".equals(videoList.get(n).getFactory())) {
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("accessToken", token));
                params.add(new BasicNameValuePair("deviceSerial", sn));
//                    params.add(new BasicNameValuePair("channelNo","1"));
                String result = ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP + "lapp/device/info", params);
                JSONObject s = JSONObject.parseObject(result);
                if ("200".equals(s.getString("code"))) {
                    JSONObject data = s.getJSONObject("data");
                    videoList.get(n).setIsStatus(data.getString("status"));
//                        videoList.get(n).setPicUrl(data.getString("picUrl"));
                } else {
//                        videoList.get(n).setPicUrl("");
                    videoList.get(n).setIsStatus("0");
                }
                vList.add(videoList.get(n));
            } else {
                HjSynchronizationInformation hs = new HjSynchronizationInformation();
                hs.setProjectId(pid);
                hs.setPlatformName("LCGETACCESSTOKEN");
                List<HjSynchronizationInformation> hsList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
                if (hsList.size() > 0) {
                    HjSynchronizationInformation h = hsList.get(0);
                    Long time = (long) (new Date().getTime() / 1000);
                    String nonce = Tools.encodeToMD5(time.toString());
                    JSONObject d = new JSONObject();
                    JSONObject json = new JSONObject();
                    json.put("ver", "1.0");
                    json.put("sign", lcUtil.getSign(h, time, nonce));
                    json.put("appId", h.getApiKey());
                    json.put("time", time.toString());
                    json.put("nonce", nonce);
                    JSONObject param = new JSONObject();
                    param.put("deviceId", sn);
                    param.put("token", lcUtil.getToken(h, time, Tools.encodeToMD5(sn + time)));
                    param.put("channelId", "0");
                    d.put("system", json);
                    d.put("params", param);
                    d.put("id", nonce);
                    System.out.println(d.toString());
                    String s = ZCAPIClientTwo.httpPostWithJSON(Constants.OPEN_LC + "deviceOnline", d);
                    JSONObject result=JSONObject.parseObject(s).getJSONObject("result");
                    if("0".equals(result.getString("code"))){
                        videoList.get(n).setIsStatus(result.getJSONObject("data").getString("onLine"));
                    }else{
                        videoList.get(n).setIsStatus("0");
                    }
                    vList.add(videoList.get(n));
                }
//                    }
//
//                }

            }
        }
        return vList;
    }
}
