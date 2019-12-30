package com.hujiang.project.zhgd.sbProjectVideoArea.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.ProjectVideoJT;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbJTArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbProjectVideoArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.Video;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
@RequestMapping(value = "/provider/ProjectVideoAreaApi",method = RequestMethod.POST)
public class ProjectVideoAreaApi extends BaseController {
    @Autowired
    private ISbProjectVideoAreaService areaService;
    @Resource
    private YsUtil ysUtil;
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
    public AjaxResult projectVideoAreaList(@RequestBody SbProjectVideoArea sbProjectVideoArea)
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
        String token=ysUtil.getAccessToken2(saList.get(0).getProjectList().get(0).getPid());
        List<NameValuePair> params;
        List<Video> vList=new ArrayList<Video>();
        for(int i=0;i<saList.size();i++){
            List<ProjectVideoJT> projectList=saList.get(i).getProjectList();
            for(int j=0;j<projectList.size();j++){
                projectList.get(j).setToken(token);

            }

        }
        return saList;
    }
    /**
     * 集团获取项目列表
     */
    @PostMapping("/getVideoListImgUrl")
    public List<Video> getVideoListImgUrl(Integer cid) throws  Exception{
        List<SbJTArea> saList=areaService.getVideoListJT(cid);
        String token=ysUtil.getAccessToken2(saList.get(0).getProjectList().get(0).getPid());
        List<NameValuePair> params;
        List<Video> vList=new ArrayList<Video>();
        for(int i=0;i<saList.size();i++){
            List<ProjectVideoJT> projectList=saList.get(i).getProjectList();
            for(int j=0;j<projectList.size();j++){
                projectList.get(j).setToken(token);
                List<Video> videoList=projectList.get(j).getVideoList();
                for(int n=0;n<videoList.size();n++){
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("accessToken",token ));
                    params.add(new BasicNameValuePair("deviceSerial",videoList.get(n).getVideoSn() ));
//                    params.add(new BasicNameValuePair("channelNo","1"));
                    String result=      ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP +"lapp/device/info",params);
                    JSONObject s=JSONObject.parseObject(result);
                    if("200".equals(s.getString("code"))){
                        JSONObject data=s.getJSONObject("data");
                        videoList.get(n).setIsStatus(data.getString("status"));
//                        videoList.get(n).setPicUrl(data.getString("picUrl"));
                    }else{
//                        videoList.get(n).setPicUrl("");
                        videoList.get(n).setIsStatus("0");
                    }
                    vList.add(videoList.get(n));
                }

            }

        }
        return vList;
    }
}
