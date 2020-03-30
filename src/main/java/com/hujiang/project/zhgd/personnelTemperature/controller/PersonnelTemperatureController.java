package com.hujiang.project.zhgd.personnelTemperature.controller;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.ProjectWorkerPC;
import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperature;
import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperatureReq;
import com.hujiang.project.zhgd.personnelTemperature.service.IPersonnelTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PersonnelTemperatureController
 * @Description
 * @Author xieya
 * @Date 2020/3/25  10:19
 */
@RestController
@RequestMapping(value = "/provider/personnelTemperature", method = RequestMethod.POST)
public class PersonnelTemperatureController extends BaseController {

    @Autowired
    private IPersonnelTemperatureService personnelTemperatureService;


    @PostMapping("/personnelTemperatureList")
    public AjaxResult personnelTemperatureList(@RequestBody PersonnelTemperatureReq personnelTemperatureReq) {
        startPage();
        List<PersonnelTemperature> list = personnelTemperatureService.selectPersonnelTemperatureList(personnelTemperatureReq);
        return AjaxResult.success(getDataTable(list));
    }

}