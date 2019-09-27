package com.hujiang.project.xiamenyizhitong;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.xh.Api.xhApi;
import com.hujiang.project.xh.tokenApi.TokenApi;
import com.hujiang.project.xh.utils.HttpUtilsXh;
import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjArea.service.IHjAreaService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import com.hujiang.project.zhgd.hjCompanyProject.service.IHjCompanyProjectService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 一指通接口
 * @author hujiang
 * @date 2019-05-21
 */
@Controller
@RequestMapping(value = "/provider/yizhittong" , method = RequestMethod.POST)
public class XiamenyiZhiTongApi extends BaseController {

    @Autowired
    private IHjProjectService hjProjectService;
    @Autowired
    private IHjAreaService hjAreaService;
    @Autowired
    private IHjCompanyProjectService hjCompanyProjectService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjDictionariesService hjDictionariesService;

    private String url="http://gd.17hr.net:8018/APP/HJSystem/ApiXmDetail.ashx?token=";
    /**
     * 获取一指通项目
     * @param
     * @return
     */
    @RequestMapping("/getProject")
    @ResponseBody
    public void getProject() throws Exception
    {
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("account=OKrnYmcc0/c=&callSoft=SZHJ&method=GetXMData&xmcode=&psd=OKrnYmcc0/c=&ticket=ZRo8ZiwudAGRwFkNkywQQtXGV/KlIc4rhJ5/q6Pt1q44kgbHmvTKQw==&timestamp=")
                .append(simpleDateFormat);
        String token= Tools.encodeToMD5(str4MD5.toString());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("method","GetXMData");
        jsonObject.put("xmcode","");
        jsonObject.put("xmid","");
        jsonObject.put("xmnum","");
        jsonObject.put("ticket","ZRo8ZiwudAGRwFkNkywQQtXGV/KlIc4rhJ5/q6Pt1q44kgbHmvTKQw==");
        jsonObject.put("callSoft","SZHJ");
        jsonObject.put("account","OKrnYmcc0/c=");
        jsonObject.put("psd","OKrnYmcc0/c=");

        //获取一指通的数据
        String result= HttpUtilsXh.xhHttpPost(url+token,jsonObject.toString(),"");
        JSONObject  results = JSONObject.parseObject(result);

        if("0".equals(results.getString("success"))){
            JSONArray list=JSONArray.parseArray(results.getString("data"));
            HjProject hjProject;
            HjCompanyProject hjCompanyProject;
            JSONObject  a;
            HjArea hjArea=new HjArea();
            hjArea.setType("CITY");
            for(Object o:list){
                a=JSONObject.parseObject(o.toString());
                hjProject=new HjProject();
                hjProject.setProjectName(a.getString("DeptName"));//项目名
                hjProject.setProjectAddress(a.getString("Location"));//地址
                hjProject.setRemark(a.getString("DeptID"));//他们的项目id放在我们的备注里
                //区域
                hjArea.setTitle(a.getString("AreaName"));
                List<HjArea> aList= hjAreaService.selectHjAreaList(hjArea);
                if(aList.size()>0){
                    hjProject.setProjectRegion(aList.get(0).getId()+","+aList.get(0).getParentId());
                }else{
                    hjProject.setProjectRegion(a.getString("AreaName"));
                }
                //保存项目信息
                hjProjectService.insertHjProject(hjProject);
                //公司项目
                hjCompanyProject=new HjCompanyProject();
                hjCompanyProject.setCompanyId(41);//公司，默认深圳一指通公司
                hjCompanyProject.setProjectId(hjProject.getId());//项目id
                hjCompanyProjectService.insertHjCompanyProject(hjCompanyProject);

            }
        }


    }
    /**
     * 获取一指通项目人员
     * @param
     * @return
     */
    @RequestMapping("/getProjectWorks")
    @ResponseBody
    public void getProjectWorks() throws Exception
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        StringBuilder str4MD5 = new StringBuilder();

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("method","GetZaiChanRS");


        jsonObject.put("ticket","ZRo8ZiwudAGRwFkNkywQQtXGV/KlIc4rhJ5/q6Pt1q44kgbHmvTKQw==");
        jsonObject.put("callSoft","SZHJ");
        jsonObject.put("account","OKrnYmcc0/c=");
        jsonObject.put("psd","OKrnYmcc0/c=");

        String str1="account=OKrnYmcc0/c=&callSoft=SZHJ&method=GetZaiChanRS&xmcode=";

        String str2="&psd=OKrnYmcc0/c=&ticket=ZRo8ZiwudAGRwFkNkywQQtXGV/KlIc4rhJ5/q6Pt1q44kgbHmvTKQw==&timestamp=";

        String token;
        HjCompanyProject hjCompanyProject=new HjCompanyProject();
        hjCompanyProject.setCompanyId(41);
        List<HjCompanyProject> cList=hjCompanyProjectService.selectHjCompanyProjectList(hjCompanyProject);
        for(int i=cList.size()-1; i<cList.size();i++){
            HjProject hjProject=hjProjectService.selectHjProjectById(cList.get(i).getProjectId());
            jsonObject.put("xmcode",hjProject.getRemark());
            token=Tools.encodeToMD5(str1+hjProject.getRemark()+str2+simpleDateFormat.format(new Date()));
            String result= HttpUtilsXh.xhHttpPost(url+token,jsonObject.toString(),"");
            JSONObject  results = JSONObject.parseObject(result);
            if("0".equals(results.getString("success"))){
                JSONArray list=JSONArray.parseArray(results.getString("data"));
                JSONObject  a;
                HjProjectWorkers hjProjectWorkers;
                HjDictionaries hjDictionaries;
                for(Object o:list){
                    a=JSONObject.parseObject(o.toString());
                    hjProjectWorkers=new HjProjectWorkers();
                    hjProjectWorkers.setEmpName(a.getString("Name"));//姓名
                    hjProjectWorkers.setIdCode(a.getString("Shenfenzheng"));//身份证
                    hjProjectWorkers.setEmpPhon(a.getString("ShoujiHaoma"));//手机号码
                    hjProjectWorkers.setEnterAndRetreatCondition(0);//在场状态
                    hjProjectWorkers.setProjectId(cList.get(i).getProjectId());//所属项目
                    //工种
                    hjDictionaries=new HjDictionaries();
                    hjDictionaries.setCategory("WORK_TYPE");
                    hjDictionaries.setTitle(a.getString("Zhicheng"));
                    List<HjDictionaries> dList=hjDictionariesService.selectHjDictionariesList(hjDictionaries);
                    if(dList.size()>0){
                        hjProjectWorkers.setJobName(dList.get(0).getTag());
                    }else {
                        hjProjectWorkers.setJobName("9531DEDC24FA502F3FDE72D720D0942E");
                    }
                    hjProjectWorkersService.insertHjProjectWorkers(hjProjectWorkers);

                }




            }

        }



        //获取一指通的数据
//        String result= HttpUtilsXh.xhHttpPost(url+token,jsonObject.toString(),"");
//        JSONObject  results = JSONObject.parseObject(result);
//
//        if("0".equals(results.getString("success"))){
//            JSONArray list=JSONArray.parseArray(results.getString("data"));
//            HjProject hjProject;
//            HjCompanyProject hjCompanyProject;
//            JSONObject  a;
//            HjArea hjArea=new HjArea();
//            hjArea.setType("CITY");
//            for(Object o:list){
//                a=JSONObject.parseObject(o.toString());
//                hjProject=new HjProject();
//                hjProject.setProjectName(a.getString("DeptName"));//项目名
//                hjProject.setProjectAddress(a.getString("Location"));//地址
//                hjProject.setRemark(a.getString("DeptID"));//他们的项目id放在我们的备注里
//                //区域
//                hjArea.setTitle(a.getString("AreaName"));
//                List<HjArea> aList= hjAreaService.selectHjAreaList(hjArea);
//                if(aList.size()>0){
//                    hjProject.setProjectRegion(aList.get(0).getId()+","+aList.get(0).getParentId());
//                }else{
//                    hjProject.setProjectRegion(a.getString("AreaName"));
//                }
//                //保存项目信息
//                hjProjectService.insertHjProject(hjProject);
//                //公司项目
//                hjCompanyProject=new HjCompanyProject();
//                hjCompanyProject.setCompanyId(41);//公司，默认深圳一指通公司
//                hjCompanyProject.setProjectId(hjProject.getId());//项目id
//                hjCompanyProjectService.insertHjCompanyProject(hjCompanyProject);
//
//            }
//        }


    }


}
