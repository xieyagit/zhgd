package com.hujiang.project.zhgd.sbProjectVideoArea.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbJTArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbProjectVideoArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public List<SbJTArea> getVideoListJT(Integer cid){
return areaService.getVideoListJT(cid);
    }

}
