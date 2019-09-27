package com.hujiang.project.zhgd.hjProject.api;

import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.face.AipFace;
import com.hujiang.common.exception.BusinessException;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import com.hujiang.project.zhgd.hjCompanyProject.service.IHjCompanyProjectService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import com.hujiang.project.zhgd.hjLogging.service.IHjLoggingService;
import com.hujiang.project.zhgd.hjProject.domain.HjCompanyProjectTemp;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.CurrentTime;
import com.hujiang.project.zhgd.utils.ZCAPIClientTwo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.out;

/**
 * @program: Provider01
 * @description: 项目接口
 * @author: Mr.LiuYong
 * @create: 2019-05-14 17:37
 **/
@RestController
@RequestMapping(value = "/provider/project",method = RequestMethod.POST)
public class ProjectApi extends BaseController {


    @Autowired
    private IHjProjectService hjProjectService;
    @Autowired
    private IHjCompanyProjectService hjCompanyProjectService;//公司项目
    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;
    @Autowired
    private IHjConstructionProjectService hjConstructionProjectService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService; //工人
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService; //考勤
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Autowired
    private IHjDictionariesService hjDictionariesService;
    @Autowired
    private IHjLoggingService hjLoggingService;
    /**
     * 查询项目信息
     * @param hjProject
     * @return
     */
    @RequestMapping("selectProjectList")
    public TableDataInfo list(HjProject hjProject,Integer cid) {
        startPage();
        out.println(cid);
        out.println(hjProject);
        List<HjProject> list = hjProjectService.selectProjectPage(hjProject,cid);
        return getDataTable(list);
    }

    /**
     * 集团看板信息
     * @param companyId
     * @param region
     * @return String
     * @author yant
     */
    @RequestMapping(value = "selectProjectArea")
    public Map<String,Object> selectProjectArea(@RequestParam(value = "companyId") Integer companyId,@RequestParam(value = "region") String region) {
        Map<String,Object> map = new HashMap<>();
        List<HjProject> listP;
        if (companyId == null || region == null || region == ""){
            map.put("error","参数不能为空");
            return map;
        }
        Map mapTemp = new HashMap();
        //参建单位总数量
        int numC = hjProjectService.infoConstructionR(companyId);
        //在场工人总数量
        int numW = hjProjectService.infoPWorkertR(companyId)*3;
        //上工总人数
        int numWorking = hjProjectService.infoPWorkingR(companyId)*30;
        //投资总金额
        double totalMoney = hjProjectService.infoHjProjectR("project_cost",companyId,region);
        BigDecimal bd1 = new BigDecimal(totalMoney);


        /*List<HjProject> listP = new ArrayList();
        //项目参建单位数量
        int numProjectC;
        //项目在场工人数量
        int numProjectW;
        //项目上工人数
        int numProjectWorking;

        //com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject(); // 创建一个json对象
        if (listP != null && listP.size() > 0) {
            for (int i = 0 ; i < listP.size() ; i++){
                HjCompanyProjectTemp temp = new HjCompanyProjectTemp();
                BeanUtils.copyProperties(listP.get(i),temp);
                numProjectC = hjConstructionProjectService.selectHjConstructionList(listP.get(i).getId()).size();
                temp.setNumProjectC(numProjectC);
                numC += numProjectC;
                numProjectW = hjProjectWorkersService.selectOnLineCount(listP.get(i).getId());
                temp.setNumProjectW(numProjectW);
                numW += numProjectW;
                numProjectWorking = hjAttendanceRecordService.selectJinRiChuQin(listP.get(i).getId()).size();
                temp.setNumProjectWorking(numProjectWorking);
                numWorking += numProjectWorking;
                mapTemp.put(i,temp);
            }

            return map;
        }*/
        map.put("numC",numC);
        map.put("numW",numW);
        map.put("numWing",numWorking);
        map.put("totalMoney",bd1);
        return map;
    }


    /**
     * 集团看板公司列表
     * @param companyId
     * @param region
     * @return String
     * @author yant
     */
    @RequestMapping(value = "selectAreaProjectList")
    public AjaxResult selectAreaProjectList(@RequestParam(value = "companyId") Integer companyId,@RequestParam(value = "region") String region) {
        List<HjProject> listP;
        AjaxResult a = new AjaxResult();
        //查找公司以region开头的地区的项目
//        startPage();
        listP = hjProjectService.selectHjProjectListR(companyId, region);
//        TableDataInfo dataTable = getDataTable(listP);

        if (listP.size() == 0){
            a.put("error","公司在该地区没有项目！");
        }
        a.put("code",0);
        a.put("data",listP);
        return a;
    }

    /**
     * 添加项目
     * @param hjProject
     * @param cid
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addProject",method = RequestMethod.POST)
    public AjaxResult addSave(HjProject hjProject, Integer cid, MultipartFile file,String remark1,String shortName1  )throws Exception
    {
        out.println(cid);


            //保存項目-------------------------
            if (file != null&&!file.isEmpty()) {
                //上传图片到oss服务器
                String url = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hjProject.getShortName() + System.currentTimeMillis() + ".jpg");
                hjProject.setProjectImage(url.substring(0,url.indexOf("?")));
            }
            //创建人脸库
            AipFace aipFace = new AipFace(Constants.BD_APP_ID, Constants.BD_API_KEY,Constants. BD_SECRET_KEY);
            //随机生成人脸库编号
            String randomNum = Long.toHexString(System.currentTimeMillis());
            JSONObject jsonObject = aipFace.groupAdd(randomNum, new HashMap<>());

            if(!jsonObject.getString("error_msg").equals("SUCCESS")){
                throw new BusinessException("人脸库创建不成功");
            }else{
                //添加人脸库
                hjProject.setFaceGroup(randomNum);
            }
            //添加项目
//            hjProject.setConstructionId(cid);//总包单位
//            hjProject.setSupervisorId(hjConstructionCompany.getId());//监理企业
            int s = hjProjectService.insertHjProject(hjProject);

        HjCompanyProject hjCompanyProject = new HjCompanyProject();
        hjCompanyProject.setCompanyId(cid);
        hjCompanyProject.setProjectId(hjProject.getId());
        hjCompanyProjectService.insertHjCompanyProject(hjCompanyProject);
        return toAjax(s);
    }

    /**
     * 修改项目
     * @param hjProject
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateProject",method = RequestMethod.POST)
    public AjaxResult editSave(HjProject hjProject, MultipartFile file)throws Exception
    {
        if (file != null&&!file.isEmpty()) {
                       //删除原图片
            AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", hjProject.getProjectImage().substring(hjProject.getProjectImage().lastIndexOf("/") + 1, hjProject.getProjectImage().length()));

            //上传图片到oss服务器
            String url = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hjProject.getShortName() + System.currentTimeMillis() + ".jpg");
            hjProject.setProjectImage(url.substring(0,url.indexOf("?")));
        }
        hjProject.setUpdateDate(CurrentTime.getCurrentTime());
        return toAjax(hjProjectService.updateHjProject(hjProject));
    }


    /**
     * 删除项目
     */
    @Log(title = "项目", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    public AjaxResult remove(String ids)
    {
        out.println(ids);
        return toAjax(hjProjectService.hiddenHjProjectByIds(ids));
    }

    /**
     * 查询项目详情
     * @param projectId
     * @return
     */
    @RequestMapping("getProject")
    public  Map<String, Object> getProject(Integer projectId) {
        return hjProjectService.getProject(projectId);
    }



    /** 智慧工地1.0工程概括*/
    @RequestMapping(value = "/getXmzk")
    public com.alibaba.fastjson.JSONObject kanban(Integer id){
        if (id != null && !id.equals("")) {
            HjProject hjProject = hjProjectService.kanbans(id);
            if (hjProject==null) {
                com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
                jsonObject.put("error", "");
                return jsonObject;
            } else {
                JSONArray jsonArray = new JSONArray();
                com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
                List<HjProject> list = hjProjectService.item(id);
                if (hjProject==null){
                    jsonObject.put("projectName", "");//项目名称
                    jsonObject.put("projectCost", "");//工程造价
                    jsonObject.put("acreage", "");//建筑面积
                    jsonObject.put("constructionName", "");//总包单位
                    jsonObject.put("name", "");
                }else {
                    jsonObject.put("projectName", hjProject.getProjectName());//项目名称
                    jsonObject.put("projectCost", hjProject.getProjectCost());//工程造价
                    jsonObject.put("acreage", hjProject.getAcreage());//建筑面积
                    jsonObject.put("constructionName", hjProject.getConstructionName());//总包单位
                    jsonObject.put("projectIntroduction",hjProject.getRemark());//工程简介
                }
                if (list.size()!=0) {
                    for (HjProject project : list) {
                        com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
                        object.put("name", project.getName());
                        jsonArray.add(object);
                    }
                }else{
                    com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
                    object.put("name","");
                    jsonArray.add(object);
                }
                jsonObject.put("subpackage", jsonArray);
                return jsonObject;
            }
        }else {
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            jsonObject.put("error", "");
            return jsonObject;
        }
    }

    /* 安全文明施工天数*/
    @PostMapping(value = "/day")
    public com.alibaba.fastjson.JSONObject day(@RequestParam("id") Integer id) throws ParseException {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        HjProject project = hjProjectService.day(id);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        Date parses = null;
        date = new Date();
        parses = df.parse(project.getStartingTime());
        Timestamp timestamps = new Timestamp(date.getTime());
        Timestamp timestamps1 = new Timestamp(parses.getTime());
        Date c1 = df.parse(String.valueOf(timestamps));
        Date c2 = df.parse(String.valueOf(timestamps1));
        long diff2 = c1.getTime() - c2.getTime();//这样得到的差值是微秒级别
        Long d = diff2 / (1000 * 60 * 60 * 24);
        Long e = (diff2 - d * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        Integer day = Integer.parseInt(String.valueOf(d));
        if (day<=0) {
            jsonObject.put("day", "0");//安全施工天数
        }else{
            jsonObject.put("day", day);//安全施工天数
        }
        return jsonObject;
    }


}
