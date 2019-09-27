package com.hujiang.project.zhgd.hjStatistics.pcApi;


import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.service.IHjCompanyHierarchyService;
import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.project.zhgd.hjCompanyLibrary.service.IHjCompanyLibraryService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjStatistics.service.IHjStatisticsService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * pc端首页
 * @author hujiang
 * @date 2019-05-21
 */
@Controller
@RequestMapping(value = "/provider/pcLzIndex" , method = RequestMethod.POST)
public class PcIndexApi extends BaseController {
    private Logger logger = Logger.getLogger(PcIndexApi.class.getName());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private IHjStatisticsService hjStatisticsService;
    @Autowired
    private IHjProjectService hjProjectService;
    /**
     * 根据id查询项目信息
     * @param
     * @return
     */
    @RequestMapping("/selectIndex")
    @ResponseBody
    public AjaxResult selectIndex(Integer pid)
    {
        logger.info("根据id查询项目相关信息统计开始---selectIndex---start");
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Date date=new Date();
        String time=dateFormat.format(date.getTime());
        Map<String,String> map =new HashMap<String,String>();
        map.put("pid",pid.toString());
        map.put("time",time);
        //管理人员总人数
        resultMap.put("glzrs",hjStatisticsService.selectOneThree(map));
        //管理人员在场人数
        resultMap.put("glzcrs",hjStatisticsService.selectOneOne(map));
        //管理人员考勤人数
        resultMap.put("glkqrs",hjStatisticsService.selectOneTwo(map));//慢
        //工人总人数
        resultMap.put("grzrs",hjStatisticsService.selectTwoThree(map));
        //工人在场人数
        Integer grzcrs=hjStatisticsService.selectTwoOne(map);
        resultMap.put("grzcrs",grzcrs);
        //工人考勤人数
        resultMap.put("grkqrs",hjStatisticsService.selectTwoTwo(map));

        //工人安全教育未培训
        Integer graqjy=hjStatisticsService.selectFour(map);
        resultMap.put("grwpx",graqjy);
        //未培训率
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String num = df.format((float)graqjy*100/grzcrs);
        if(grzcrs==0){
            resultMap.put("grwpxl",0);
        }else{
            resultMap.put("grwpxl",num);
        }
        //最近工人十天考勤统计DAY_OF_MONTH
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.DAY_OF_MONTH, -1);// 10天之前的时间
        String endTime=dateFormat.format(beforeTime.getTime());
        beforeTime.add(Calendar.DAY_OF_MONTH, -9);// 1天之前的时间
        String startTime=dateFormat.format(beforeTime.getTime());
        Map<String,String> paramMap=new HashMap<String,String>();
        paramMap.put("projectId",pid.toString());
        paramMap.put("type","2");
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        resultMap.put("grkqtj",hjStatisticsService.selectHjStatisticsListTwo(paramMap));
        //最近管理人员十天考勤统计
        paramMap.put("type","1");
        resultMap.put("glkqtj",hjStatisticsService.selectHjStatisticsListTwo(paramMap));

        HjProject hp=hjProjectService.selectHjProjectById(pid);
        resultMap.put("projectName",hp.getShortName());//项目名
        resultMap.put("status",hp.getProjectState());//项目状态
        logger.info("根据id查询项目相关信息统计结束---selectIndex---end");
        return AjaxResult.success(resultMap);
    }

}
