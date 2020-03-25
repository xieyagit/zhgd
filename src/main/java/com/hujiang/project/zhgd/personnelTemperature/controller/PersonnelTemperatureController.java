package com.hujiang.project.zhgd.personnelTemperature.controller;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperature;
import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperatureReq;
import com.hujiang.project.zhgd.personnelTemperature.service.IPersonnelTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName PersonnelTemperatureController
 * @Description
 * @Author xieya
 * @Date 2020/3/25  10:19
 */
public class PersonnelTemperatureController extends BaseController {

    @Autowired
    private IPersonnelTemperatureService personnelTemperatureService;


    @PostMapping("/personnelTemperatureList")
    public AjaxResult personnelTemperatureList(@RequestBody PersonnelTemperatureReq PersonnelTemperatureReq) {
        startPage();
        List<PersonnelTemperature> list = personnelTemperatureService.selectPersonnelTemperatureList(PersonnelTemperatureReq);
        return AjaxResult.success(getDataTable(list));
    }
}