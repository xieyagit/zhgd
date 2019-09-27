package com.hujiang.project.zhgd.sbProjectVideo.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import com.hujiang.project.zhgd.sbProjectVideo.service.ISbProjectVideoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

}
