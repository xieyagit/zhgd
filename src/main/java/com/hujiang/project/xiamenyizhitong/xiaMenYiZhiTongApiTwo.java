package com.hujiang.project.xiamenyizhitong;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.xh.Api.xhApi;
import com.hujiang.project.xh.tokenApi.TokenApi;
import com.hujiang.project.xh.utils.HttpUtilsXh;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
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
 * 从一指通通过星河的接口获取数据，不需要对接
 * @author hujiang
 * @date 2019-05-21
 */
@Controller
@RequestMapping(value = "/provider/yizhitong" , method = RequestMethod.POST)
public class xiaMenYiZhiTongApiTwo extends BaseController {

    @Autowired
    private IHjProjectService hjProjectService;
    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;
    @Autowired
    private IHjConstructionProjectService hjConstructionProjectService;
    @Autowired
    private IHjTeamService hjTeamService;
    @Autowired
    private IHjDictionariesService hjDictionariesService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @Resource
    private xhApi xhHttpApi;
@Resource
private TokenApi tokenApi;
    /**
     * 获取一指通项目
     * @param xmcode 一指通项目编号
     * @return
     */
    @RequestMapping("/project")
    @ResponseBody
    public void getProject(String  xmcode,String api_key_szzjt,String api_secret_szzjt) throws Exception
    {
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("xmcode=").append(xmcode)
                .append("&xmjc=").append("API")
                .append("&api_key_szzjt=").append(api_key_szzjt)
                .append("&api_secret_szzjt=").append(api_secret_szzjt)
                .append("&timestamp=").append(simpleDateFormat);
        String token=tokenApi.getMD5(str4MD5.toString());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("xmjc","API");
        jsonObject.put("xmcode",xmcode);
        jsonObject.put("start","2017-01-01 00:00");
        jsonObject.put("end", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        //获取一指通的数据
        String result= HttpUtilsXh.xhHttpPost(Constants.YIZHITONG+"/ApiService/GetProjectInfo.ashx?token="+token,jsonObject.toString(),"");
        JSONObject  results = JSONObject.parseObject(result);
        String data=results.getString("data");
        data=data.replace("\\","");
//        System.out.println(data);
        JSONArray list=JSONArray.parseArray(data);
        JSONObject  a;
        HjProject hjProject;
        for(Object o:list){
            hjProject=new HjProject();
            a  = JSONObject.parseObject(o.toString());
            hjProject.setProjectName(a.getString("DeptName"));
            hjProject.setShortName(a.getString("ShortName"));
            hjProject.setProjectPrincipal(a.getString("0"));
            hjProject.setPhone(a.getString("0"));
            hjProject.setProjectType("1");
            hjProject.setProjectState("0");
            hjProject.setProjectNumber(1000);
            hjProject.setProjectRegion(a.getString("DeptFullName"));
            hjProject.setBuilderLicense(a.getString("DeptCode"));
            hjProject.setProjectAddress(a.getString("Location"));
            hjProject.setStartingTime(a.getString("StartDate"));
            hjProject.setFinishTime(a.getString("EndDate"));
            hjProject.setConstructionId(0);
            hjProject.setSupervisorId(0);
            hjProject.setAcreage(a.getString("Scope"));
            hjProject.setProjectCost(a.getString("Price"));
            hjProject.setLongitude(a.getString("Longitude"));
            hjProject.setLatitude(a.getString("Latitude"));
            hjProject.setRemark(a.getString("DataID"));
            hjProjectService.insertHjProject(hjProject);

        }
    }
    /**
     * 获取一指通参建单位
     * @param xmcode 一指通项目编号
     * @return
     */
    @RequestMapping("/Company")
    @ResponseBody
    public void getCompany(String  xmcode,String api_key_szzjt,String api_secret_szzjt,Integer pid) throws Exception
    {

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("xmjc","API");
        jsonObject.put("xmcode",xmcode);
        jsonObject.put("start","2019-01-01 00:00");
        jsonObject.put("end", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("xmcode=").append(xmcode)
                .append("&xmjc=").append("API")
                .append("&api_key_szzjt=").append(api_key_szzjt)
                .append("&api_secret_szzjt=").append(api_secret_szzjt)
                .append("&timestamp=").append(simpleDateFormat);
        String token=tokenApi.getMD5(str4MD5.toString());
        //获取一指通的数据
        String result= HttpUtilsXh.xhHttpPost(Constants.YIZHITONG+"/ApiService/SubContractorInfo.ashx?token="+token,jsonObject.toString(),"");
        JSONObject  results = JSONObject.parseObject(result);
        String data=results.getString("data");
        data=data.replace("\\","");
//        System.out.println(data);
        JSONArray list=JSONArray.parseArray(data);
        JSONObject  a;
        HjDictionaries hd;
        HjConstructionProject hcp;
        for(Object o:list){
            HjConstructionCompany     hc=new HjConstructionCompany();
            hd=new HjDictionaries();
            a  = JSONObject.parseObject(o.toString());
            hc.setRemark(a.getString("FBDataID"));
            List<HjConstructionCompany> hcList=hjConstructionCompanyService.selectHjConstructionCompanyList(hc);
            if(hcList.size()>0) {
                hc.setConstructionName(a.getString("Name"));
                hc.setShortName(a.getString("ShortName"));
                hc.setLegalPerson(a.getString("OrgOwner"));
                hc.setSuid(a.getString("OrgCode_XY"));
                hc.setCreateDate(a.getString("RCDate"));
                //判断参建单位类型
                if ("监理单位".equals(a.getString("FenBaoType"))) {
                    hc.setCompanyType(2);
                } else if ("管理单位".equals(a.getString("FenBaoType"))) {
                    hc.setCompanyType(3);
                }else if ("总承包单位".equals(a.getString("FenBaoType"))) {
                    hc.setCompanyType(4);
                } else {
                    hd.setTitle(a.getString("FenBaoType"));
                    hd.setCategory("UNIT_TYPE");
                    List<HjDictionaries> hdList = hjDictionariesService.selectHjDictionariesList(hd);
                    if (hdList.size() > 0) {
                        hc.setCompanyType(hdList.get(0).getId());
                    } else {
                        hc.setCompanyType(1);
                    }
                }

                hc.setContacts(a.getString("FuZeRen"));
                hc.setMobilePhone(a.getString("FuZeRenPhone"));
                hjConstructionCompanyService.insertHjConstructionCompany(hc);

                hcp = new HjConstructionProject();
                hcp.setProjectId(pid);
                hcp.setConstructionId(hc.getId());
                hjConstructionProjectService.insertHjConstructionProject(hcp);
            }
        }
    }

    /**
     * 获取一指通班组
     * @param xmcode 一指通项目编号
     * @return
     */
    @RequestMapping("/team")
    @ResponseBody
    public void getTeam(String  xmcode,String api_key_szzjt,String api_secret_szzjt,Integer pid) throws Exception
    {
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("xmjc","API");
        jsonObject.put("xmcode",xmcode);
        jsonObject.put("start","2019-01-01 00:00");
        jsonObject.put("end",simpleDateFormat);

        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("xmcode=").append(xmcode)
                .append("&xmjc=").append("API")
                .append("&api_key_szzjt=").append(api_key_szzjt)
                .append("&api_secret_szzjt=").append(api_secret_szzjt)
                .append("&timestamp=").append(simpleDateFormat);
        String token=tokenApi.getMD5(str4MD5.toString());
        //获取一指通的数据
        String result= HttpUtilsXh.xhHttpPost(Constants.YIZHITONG+"/ApiService/TeamInfo.ashx?token="+token,jsonObject.toString(),"");
        JSONObject  results = JSONObject.parseObject(result);
        String data=results.getString("data");
        data=data.replace("\\","");
//        System.out.println(data);
        JSONArray list=JSONArray.parseArray(data);
        JSONObject  a;
        HjConstructionCompany     hc;
        HjTeam ht;
        for(Object o:list){
            hc=new HjConstructionCompany();
            ht=new HjTeam();
            a  = JSONObject.parseObject(o.toString());
            ht.setRemark(a.getString("BZDataID"));
            ht.setTeamName(a.getString("Name"));
            ht.setProjectId(pid);

            hc.setRemark(a.getString("FBDataID"));
            HjConstructionCompany hjConstructionCompany=hjConstructionCompanyService.selectHjConstructionCompanyList(hc).get(0);
            ht.setConstructionId(hjConstructionCompany.getId());
            hjTeamService.insertHjTeam(ht);
        }
    }
    /**
     * 获取一指通人员
     * @param xmcode 一指通项目编号
     * @return
     */
    @RequestMapping("/user")
    @ResponseBody
    public void getProjectUser(String  xmcode,Integer pid) throws Exception {

        //参数
        String token=tokenApi.yiZhiTongTokenTwo(pid);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("xmjc","API");
        jsonObject.put("xmcode",xmcode);
        jsonObject.put("start","2010-01-01 00:00");
        jsonObject.put("end", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        //获取一指通的数据
        String result= HttpUtilsXh.xhHttpPost(Constants.YIZHITONG+"/ApiService/GetUser.ashx?token="+token,jsonObject.toString(),"");
        JSONObject  results = JSONObject.parseObject(result);
        String data=results.getString("data");
        data=data.replace("\\","");
        JSONArray list=JSONArray.parseArray(data);
        JSONObject  a;
//        JSONObject b=new JSONObject();

        HjProjectWorkers workers;
        HjDictionaries dict;

        List<HjDictionaries> dictList;
        HjConstructionCompany hc=new HjConstructionCompany();
        HjTeam ht=new HjTeam();
        HjConstructionCompany hcs;
        HjTeam hts;

        for(Object o:list){
            workers=new HjProjectWorkers();
            a  = JSONObject.parseObject(o.toString());
            if("1".equals(a.getString("Status"))) {
                workers.setRemark(a.getString("Kaoqinhao"));//id,一指通工号
                workers.setEmpName(a.getString("Name"));
                //转换工种
                dict = new HjDictionaries();
                dict.setCategory("WORK_TYPE");
                dict.setTitle(a.getString("Zhicheng"));
                dictList = hjDictionariesService.selectHjDictionariesList(dict);
                if (dictList.size() > 0) {
                    workers.setJobName(dictList.get(0).getTag());
//                    b.put("professionId", dictList.get(0).getTag());
//                    b.put("professionName", dictList.get(0).getTitle());
                } else {
                    workers.setJobName("9531DEDC24FA502F3FDE72D720D0942E");
//                    b.put("professionId", "00001");
//                    b.put("professionName", "普工");
                }
                workers.setEnterAndRetreatCondition("1".equals(a.getString("Status"))?0:1);
                workers.setIdCode(a.getString("Shenfenzheng"));
                workers.setEmpPhon(a.getString("ShoujiHaoma"));
                workers.setEmpSex(a.getString("Xingbie"));
                workers.setEmpNation(a.getString("Mingzu"));
                workers.setIdAddress(a.getString("Jiguan"));
                workers.setIdAgency(a.getString("id_agency"));
                workers.setIdValiddate(a.getString("id_validdate"));
                workers.setNativePlace(a.getString("Jiguan"));
                workers.setStartTime(a.getString("RuzhiRq"));
                workers.setEndTime(a.getString("LiZhiRq"));
                workers.setProjectId(pid);//所属项目ID

                hc.setRemark(a.getString("FBDataID"));
                //获取参建单位
                hcs = hjConstructionCompanyService.selectHjConstructionCompanyList(hc).get(0);
                workers.setConstructionId(hcs.getId());//参建单位ID
//                b.put("unitId", hcs.getId());
//                b.put("unitName", hcs.getConstructionName());
                //获取班组
                ht.setRemark(a.getString("BZDataID"));
                hts = hjTeamService.selectHjTeamList(ht).get(0);
                workers.setWorkTypenameId(hts.getId());

                workers.setIsTeam("1".equals(a.getString("TeamWorkerType")) ? 1 : 0);
                workers.setCwrIskeypsn("0");
                workers.setJobTypename("4");
                workers.setEmpCategory("00");
                workers.setIfContract(!"".equals(a.getString("HetongHao")) && a.getString("HetongHao") != "" ? 1 : 0);
                workers.setFaceUrl(Constants.YIZHITONGIMG + "/" + a.getString("EmpPhotoNow"));
                //String imag="data:image/jpeg;base64,"
                //在线图片转base64
//                b.put("headPicture",BASE64DecodedMultipartFile.getStrImgBase64(new URL(Constants.YIZHITONGIMG + "/" + a.getString("EmpPhotoNow"))));
                workers.setEmpNaticeplace(Constants.YIZHITONGIMG + "/" + a.getString("EmpPhoto"));
                workers.setIdphotoScan(Constants.YIZHITONGIMG + "/" + a.getString("ShenFenZPhoto"));
                workers.setIdphotoScan2(Constants.YIZHITONGIMG + "/" + a.getString("ShenFenFPhoto"));
                workers.setBankCardUrl(Constants.YIZHITONGIMG + "/" + a.getString("BankZPhoto"));

//                b.put("userId", a.getString("Kaoqinhao"));
//                b.put("phone", a.getString("ShoujiHaoma"));
//                b.put("displayName", a.getString("Name"));
//                b.put("idcard", a.getString("Shenfenzheng"));


                hjProjectWorkersService.insertHjProjectWorkers(workers);
//                System.out.println("人员信息：：："+b);
//                xhHttpApi.queryWorkType(b,pid.toString());
            }
        }

    }

    @RequestMapping("/test")
    @ResponseBody
    public void test() throws  Exception{
//        org.json.JSONObject b=new org.json.JSONObject();
//        b.put("userId","1");
//        b.put("professionId","00001");
//        b.put("professionName","普工");
//        b.put("unitId","ad");
//        b.put("unitName","劳务");
//        b.put("phone","10000000000");
//        b.put("displayName","刘勇");
//        b.put("idcard","400000196502020000");
//        b.put("headPicture",BASE64DecodedMultipartFile.getStrImgBase64(new URL("http://gd.17hr.net:8018/Resources/RsEmpPhoto/164/Emp_ShenFenZPhoto_513024197208224552_20180822105227.JPEG")));
//        System.out.println("============"+b.toString());
//        xhHttpApi.queryWorkType(b,pid);

        //获取一个小时内的考勤数据
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date=new Date();
//        String endTime=dateFormat.format(date.getTime());
//        Calendar beforeTime = Calendar.getInstance();
//        beforeTime.add(Calendar.MINUTE, -59);//
//        String startTime=dateFormat.format(beforeTime.getTime());

        String start="2019-07-09 12:00";
        String end="2019-07-09 23:00";

        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setPlatformName("YIZHITONG2");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        if(hsList.size()>0){
            String pid;
            String token;
                for(HjSynchronizationInformation hsi:hsList ){
                    pid=hsi.getProjectId().toString();
                    token=tokenApi.yiZhiTongTokenTwo(Integer.valueOf(pid));
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("xmjc","API");
                    jsonObject.put("xmcode",hsi.getProjectNumber());
                    jsonObject.put("start",start);
                    jsonObject.put("end", end);
                    //获取一指通的数据
                    String result= HttpUtilsXh.xhHttpPost(Constants.YIZHITONG+"/ApiService/GetCockIn.ashx?token="+token,jsonObject.toString(),"");
                    JSONObject  results = JSONObject.parseObject(result);
                    String data=results.getString("data");

                    if(!"".equals(data)&&data!=""){
                        data=data.replace("\\","");
                        JSONArray list=JSONArray.parseArray(data);
                      HjProjectWorkers hp;
                      JSONObject a;
//                      JSONObject b;
//                       JSONArray c=new JSONArray();
                      for(Object o:list){
                          hp=new HjProjectWorkers();
//                          b=new JSONObject();
                          HjAttendanceRecord hr;
                          a  = JSONObject.parseObject(o.toString());
                          hp.setIdCode(a.getString("Shenfenzheng"));
                          List<HjProjectWorkers> workersList=hjProjectWorkersService.selectHjProjectWorkersList(hp);
                            if(workersList.size()>0){
//                                b.put("userId",workersList.get(0).getRemark());
//                                b.put("phone",workersList.get(0).getEmpPhon());
//                                b.put("type","1".equals(a.getString("inout"))?2:"2".equals(a.getString("inout"))?1:0);

                                hr=new HjAttendanceRecord();
                                hr.setProjectId(workersList.get(0).getProjectId());
                                hr.setEmployeeId(workersList.get(0).getId());
                                hr.setPassedTime(a.getString("CheckTime").replace("/","-"));
                                hr.setUploadTime(a.getString("CheckTime").replace("/","-"));
                                hr.setWay(1);
                                hr.setDirection("1".equals(a.getString("inout"))?"in":"2".equals(a.getString("inout"))?"out":"0");

//                                System.out.println(b);
//                                c.add(b);
                                hjAttendanceRecordService.insertHjAttendanceRecord(hr);
                            }


                      }
//                      xhHttpApi.setRecord(c,pid);


                    }
                }
        }

    }

    @RequestMapping("/test2")
    @ResponseBody
    public void test2() throws  Exception {
    HjProjectWorkers hw=new HjProjectWorkers();
    hw.setProjectId(16);
    List<HjProjectWorkers> list=hjProjectWorkersService.selectHjProjectWorkersList(hw);
    for(HjProjectWorkers hws:list){

        xhHttpApi.deleteUsers("16",hws.getRemark());


    }



    }
}
