package com.hujiang.project.zhgd.dustEmissionThresholdValue.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.domain.DustEmissionThresholdValue;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.service.IDustEmissionThresholdValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-07-08 18:15
 **/
@RestController
@RequestMapping(value = "/provider/DustEmissionThresholdValueAPI")
public class DustEmissionThresholdValueAPI extends BaseController {
    @Autowired
    private IDustEmissionThresholdValueService thresholdValueService;

    /**
     * 查询
     * @param thresholdValue
     * @return
     */
    @PostMapping("getThresholdValue")
    public AjaxResult getThresholdValue( @RequestBody DustEmissionThresholdValue thresholdValue){
        //根据项目id查询是否设置阈值
        List<DustEmissionThresholdValue> dustEmissionThresholdValues = thresholdValueService.selectDustEmissionThresholdValueList(thresholdValue);
        if(dustEmissionThresholdValues==null || dustEmissionThresholdValues.size()==0){
            //没有添加则设置默认值
            int i = thresholdValueService.insertDustEmissionThresholdValue(thresholdValue);
            System.out.println(i);
        }
        return AjaxResult.success(thresholdValueService.selectDustEmissionThresholdValueList(thresholdValue).get(0));
    }

    /**
     * 修改
     * @param thresholdValue
     * @return
     */
    @PostMapping("updateThresholdValue")
    public AjaxResult updateThresholdValue(@RequestBody DustEmissionThresholdValue thresholdValue){
        int i = thresholdValueService.updateDustEmissionThresholdValue(thresholdValue);
        if(i>0){
            return success();
        }
        return error(-1,"修改失败");
    }
}
