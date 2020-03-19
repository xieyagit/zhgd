package com.hujiang.project.zhgd.hjTeam.pcApi;


import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.service.IHjCompanyHierarchyService;
import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.project.zhgd.hjCompanyLibrary.service.IHjCompanyLibraryService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * pc端班组表
 * @author hujiang
 * @date 2019-05-21
 */
@RestController
@RequestMapping(value = "/provider/pcCompanyLibrary" , method = RequestMethod.POST)
public class PcTeamApi  extends BaseController {
    private Logger logger = Logger.getLogger(PcTeamApi.class.getName());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private IHjTeamService hjTeamService;
    @Autowired
    private IHjConstructionCompanyService constructionCompanyService;
    @Autowired
    private IHjConstructionProjectService constructionProjectService;

    /**
     * 新建保存班组信息
     * @param
     * @return
     */
    @RequestMapping("/insertHjTeam")
    @ResponseBody
    public AjaxResult insertHjTeam(@RequestBody HjTeam hjTeam)
    {
        logger.info("保存班组信息开始---insertHjTeam---start");
        int i=hjTeamService.insertHjTeam(hjTeam);
        if(i<1){
            AjaxResult.error("保存班组信息失败");
        }
        logger.info("保存班组信息结束---insertHjTeam---end");
        return AjaxResult.success("保存班组信息成功");
    }
    /**
     * 新建保存班组信息
     * @param
     * @return
     */
    @RequestMapping("/insertHjTeamTwo")
    @ResponseBody
    public AjaxResult insertHjTeamTwo(@RequestBody String json)
    {
        JSONObject jsonResult = JSONObject.parseObject(json);
        HjTeam hjTeam = JSONObject.parseObject(jsonResult.toJSONString(), HjTeam.class);
        HjConstructionCompany hjConstructionCompany = new HjConstructionCompany();
        hjConstructionCompany.setConstructionName(hjTeam.getConstructionName());
        HjConstructionCompany constructionCompany = constructionCompanyService.getConstructionCompany(hjConstructionCompany);    //判断参见单位名称是否存在
        if(constructionCompany != null){
            HjConstructionProject constructionProject = constructionProjectService.selectHjConstructionProjectByProjectId(constructionCompany.getId());
            if(constructionProject.getProjectId().equals(hjTeam.getProjectId())){
                HjTeam team = new HjTeam();
                team.setTeamName(hjTeam.getTeamName());
                team.setConstructionId(constructionCompany.getId());
                team.setProjectId(hjTeam.getProjectId());
                team.setRemark(hjTeam.getRemark());
                int i=hjTeamService.insertHjTeam(team);
                if(i<1){
                    return AjaxResult.error("保存班组信息失败");
                }

                return AjaxResult.success();

            }else {
                return AjaxResult.error("项目ID错误");
            }

        }else {
            return AjaxResult.error("参见单位不存在");
        }
    }

    /**
     * 查询班组信息
     * @param
     * @return
     */
    @RequestMapping("/selectHjTeamId")
    @ResponseBody
    public AjaxResult selectHjTeamId( Integer id)
    {
        logger.info("查询指定班组信息开始---selectHjTeamId---start");
        HjTeam hjTeam=hjTeamService.selectHjTeamById(id);
        logger.info("查询指定班组信息结束---selectHjTeamId---end");
        return AjaxResult.success(hjTeam);
    }

    /**
     * 查询班组信息列表
     * @param
     * @return
     */
    @RequestMapping("/selectHjTeamList")
    @ResponseBody
    public AjaxResult selectHjTeamList(@RequestBody HjTeam hjTeam, PageDomain pageDomain)
    {
        logger.info("查询班组信息列表开始---selectHjTeamList---start");
        startPage();
        List<HjTeam>  hjTeamList=hjTeamService.selectHjTeamList(hjTeam);
        logger.info("查询班组信息列表结束---selectHjTeamList---end");
        return AjaxResult.success(getDataTable(hjTeamList));
    }

    /**
     * 导出班组数据源
     * @param hjTeam
     * @return
     */
    @RequestMapping("/export")
    @ResponseBody
    public  List<HjTeam> export(@RequestBody HjTeam hjTeam){
        return hjTeamService.selectHjTeamList(hjTeam);
    }
    /**
     * 修改班组信息
     * @param
     * @return
     */
    @RequestMapping("/updateHjTeam")
    @ResponseBody
    public AjaxResult updateHjTeam(@RequestBody HjTeam hjTeam)
    {
        logger.info("修改班组信息开始---updateHjTeam---start");
        hjTeam.setUpdateDate(dateFormat.format(new Date().getTime()));
        int i=hjTeamService.updateHjTeam(hjTeam);
        logger.info("修改班组信息结束---updateHjTeam---end");
        if(i<1){
            return AjaxResult.error("修改班组信息失败");
        }

        return AjaxResult.success("修改班组信息成功");
    }
    /**
     * 删除班组信息
     * @param
     * @return
     */
    @RequestMapping("/deleteHjTeam")
    @ResponseBody
    public AjaxResult deleteHjTeam(String  ids)
    {
        logger.info("删除班组信息开始---updateHjTeam---start");

        int i=hjTeamService.deleteHjTeamByIds(ids);
        logger.info("删除班组信息结束---updateHjTeam---end");
        if(i<1){
            return AjaxResult.error("删除班组信息失败");
        }

        return AjaxResult.success("删除班组信息成功");
    }


}
