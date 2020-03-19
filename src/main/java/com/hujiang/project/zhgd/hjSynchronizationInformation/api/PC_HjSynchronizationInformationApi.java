package com.hujiang.project.zhgd.hjSynchronizationInformation.api;

import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.CurrentTime;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-27 15:58
 **/
@RestController
@RequestMapping(value = "/provider/synchronizationInformationApi",method = RequestMethod.POST)
public class PC_HjSynchronizationInformationApi extends BaseController {
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    /**
     * 查询项目两制同步列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody HjSynchronizationInformation hjSynchronizationInformation)
    {
        startPage();
        List<HjSynchronizationInformation> list = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hjSynchronizationInformation);
        return getDataTable(list);
    }

    /**
     * 新增保存项目两制同步
     */
    @Log(title = "项目两制同步", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addSave( @RequestBody HjSynchronizationInformation hjSynchronizationInformation)
    {
        System.out.println(hjSynchronizationInformation);
        return toAjax(hjSynchronizationInformationService.insertHjSynchronizationInformation(hjSynchronizationInformation));
    }

    /**
     * 修改保存项目两制同步
     */
    @Log(title = "项目两制同步", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editSave( @RequestBody HjSynchronizationInformation hjSynchronizationInformation)
    {
        hjSynchronizationInformation.setUpdateDate(CurrentTime.getCurrentTime());
        return toAjax(hjSynchronizationInformationService.updateHjSynchronizationInformation(hjSynchronizationInformation));
    }

    /**
     * 删除项目两制同步
     */
    @Log(title = "项目两制同步", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    public AjaxResult remove(@RequestParam("ids") String ids)
    {
        return toAjax(hjSynchronizationInformationService.deleteHjSynchronizationInformationByIds(ids));
    }
}
