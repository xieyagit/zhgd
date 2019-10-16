package com.hujiang.project.zhgd.sbProjectElectricityBox.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.api.controller.ApiElectricityBoxController;
import com.hujiang.project.cay.cay;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCurrentTemperature.api.SendTemperatureToPERSONNEL;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbPowerBoxAdd;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import com.hujiang.project.zhgd.utils.CommonChars;
import com.hujiang.project.zhgd.utils.Tools;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-24 11:52
 **/
@RestController
@RequestMapping(value = "/provider/ProjectElectricityBox")
public class ProjectElectricityBoxApi extends BaseController {
    @Autowired
    private ISbProjectElectricityBoxService iProjectElectricityBoxService;
    @Autowired
    private ISbProjectElectricityBoxService boxService;
    @Autowired
    private com.hujiang.project.cay.cay cay;
    @Autowired
    private ApiElectricityBoxController apiElectricityBoxController;
    @Autowired
    private SendTemperatureToPERSONNEL sendTemperatureToPERSONNEL;
    /**
     * 根据项目id获取电箱设备编号
     * @param projectId
     * @return
     */
    @PostMapping("getProjectElectricityBox")
    public JSONObject getProjectElectricityBox(@RequestParam(value = "projectId") Integer projectId){
        JSONObject result = new JSONObject();
        result.put("msg","查询成功");
        result.put("code",0);
        SbProjectElectricityBox box = new SbProjectElectricityBox();
        box.setProjectId(projectId);
        result.put("data",boxService.selectSbProjectElectricityBoxList(box));
        return result;
    }


    /**
     * 查询项目电箱列表
     */
    @PostMapping("list")
    public AjaxResult list(@RequestBody SbProjectElectricityBox sbProjectElectricityBox)
    {
        startPage();
        List<SbProjectElectricityBox> list = boxService.selectSbProjectElectricityBoxList(sbProjectElectricityBox);
        TableDataInfo dataTable = getDataTable(list);
        List<SbProjectElectricityBox> rows = ( List<SbProjectElectricityBox>)dataTable.getRows();
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 添加电箱
     * @param sbP
     * @return
     * @author yant
     */
    @RequestMapping("/addSave")
    public AjaxResult addSave(SbProjectElectricityBox sbP)
    {

        if (sbP.getProjectId() == null || sbP.getElectricityBoxId() == null ||
                sbP.getTempLimit() ==null ||
                sbP.getElecLimit() == null ||sbP.getAroundTemp() == null){
            return AjaxResult.error(500,"参数错误");
        }
        //将设备编号转换成MD5格式32位
        String electricityBoxId = Tools.encodeToMD5s(sbP.getElectricityBoxId());
        sbP.setType(1);
        sbP.setElectricityBoxName(sbP.getComments());
        int i = boxService.insertSbProjectElectricityBox(sbP);
        try {
            SbPowerBoxAdd sbPowerBoxAdd = new SbPowerBoxAdd();
            BeanUtils.copyProperties(sbP,sbPowerBoxAdd);
            sbPowerBoxAdd.setElectricityBoxId(electricityBoxId);
            sbPowerBoxAdd.setType(1);
            sbPowerBoxAdd.setInstallCompany(CommonChars.CompanyName);
            sbPowerBoxAdd.setInstalladdType(2);
            //设备参数上报
            boxService.reportedEBox(sbPowerBoxAdd);
            if (sbP.getScznl().equals("CAY")) {
                apiElectricityBoxController.reportElectricBoxParamete(sbP);
            }else if(sbP.getScznl().equals("RCAJ")){
                SbProjectElectricityBox sbox = new SbProjectElectricityBox();
                sbox.setElectricityBoxId(sbP.getElectricityBoxId());
                List<SbProjectElectricityBox> projectElectricityBoxes = iProjectElectricityBoxService.selectSbProjectElectricityBoxList(sbox);

                sbP.setElectricityBoxId(sbP.getElectricityBoxId());
                sbP.setElectricityBoxName((projectElectricityBoxes.size()+1)+"#"+sbP.getComments());
                sendTemperatureToPERSONNEL.rcajMachine(sbP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(i>0){
            return AjaxResult.success();
        }else {
            AjaxResult ajaxResult = new AjaxResult();
            return ajaxResult;
        }
    }
    @PostMapping("/addElectricityBox")
    public AjaxResult addElectricityBox(@RequestParam(value = "projectId")Integer projectId,
                                        @RequestParam(value = "electricityBoxId")String electricityBoxId,
                                        @RequestParam(value = "electricityBoxName")String electricityBoxName)
    {
       // System.out.println(sbProjectElectricityBox);
        SbProjectElectricityBox sbProjectElectricityBox = new SbProjectElectricityBox();
        sbProjectElectricityBox.setProjectId(projectId);
        sbProjectElectricityBox.setElectricityBoxId(electricityBoxId);
        sbProjectElectricityBox.setElectricityBoxName(electricityBoxName);
        sbProjectElectricityBox.setComments(electricityBoxName);
        int i = boxService.insertSbProjectElectricityBox(sbProjectElectricityBox);
        if(i>0){
            return AjaxResult.success();
        }
        return AjaxResult.error(-1,"添加电箱失败");
    }


    /**
     * 修改前查询
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestParam("id") Integer id)
    {
        SbProjectElectricityBox sbProjectElectricityBox = boxService.selectSbProjectElectricityBoxById(id);
        return AjaxResult.success(sbProjectElectricityBox);
    }

    /**
     * 修改保存项目电箱
     */
    @PostMapping("/editSave")
    public AjaxResult editSave(@RequestBody SbProjectElectricityBox sbProjectElectricityBox)
    {
        int i = boxService.updateSbProjectElectricityBox(sbProjectElectricityBox);
        if(i>0){
            return AjaxResult.success();
        }
        return AjaxResult.error(-1,"修改电箱失败");
    }

    /**
     * 删除项目电箱
     */
    @PostMapping( "/remove")
    public AjaxResult remove(Integer id,@RequestParam(value = "devCcrq",required =false)String devCcrq) throws IOException, URISyntaxException {
        if (devCcrq != null) {
            SbProjectElectricityBox sbProjectElectricityBox = boxService.selectSbProjectElectricityBoxById(id);
            if (sbProjectElectricityBox != null) {
                cay.delete(sbProjectElectricityBox.getElectricityBoxId(), sbProjectElectricityBox.getXmid(), "电箱", devCcrq, sbProjectElectricityBox.getSubId());
            }
        }
        int i = boxService.deleteSbProjectElectricityBoxByIds(id);
        if(i>0){
            return AjaxResult.success();
        }
        return AjaxResult.error(-1,"删除电箱失败");
    }


}
