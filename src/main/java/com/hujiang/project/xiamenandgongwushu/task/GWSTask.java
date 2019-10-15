package com.hujiang.project.xiamenandgongwushu.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.xh.Api.xhApi;
import com.hujiang.project.xh.utils.HttpUtilsXh;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.project.zhgd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * 从一指通获取数据，需要上传至工务署
 */
//
//    @RestController
//    @RequestMapping(value = "/provider/a",method = RequestMethod.POST)
//@Component
public class GWSTask {
    private Logger logger = Logger.getLogger(GWSTask.class.getName());
  @Autowired
  private IHjSynchronizationInformationService hjSynchronizationInformationService;
  @Autowired
  private IHjConstructionCompanyService hjConstructionCompanyService;
  @Autowired
  private IHjConstructionProjectService hjConstructionProjectService;
  @Autowired
  private IHjDictionariesService hjDictionariesService;
  @Autowired
  private IHjTeamService hjTeamService;
  @Autowired
  private IHjProjectWorkersService hjProjectWorkersService;
  @Autowired
  private IHjAttendanceRecordService hjAttendanceRecordService;

    /**
     * 同步参建单位定时器
     * @throws URISyntaxException
     * @throws IOException
     */

//    @RequestMapping("/b")
//    @Scheduled(cron="0 0 0 * * ? ")
    public void setCompany() throws  URISyntaxException, IOException{
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setPlatformName("YIZHITONG3");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        for(HjSynchronizationInformation hs2: hsList){
            getCompany(hs2.getProjectNumber(),hs2.getApiKey(),hs2.getApiSecret(),hs2.getProjectId());
        }
        //主动回收一次
        System.gc();
    }

    /**
     * 同步班组定时器
     * @throws Exception
     */

//    @RequestMapping("/c")
//    @Scheduled(cron="0 0 1 * * ? ")
    public void setTeam()throws Exception{
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setPlatformName("YIZHITONG3");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        for(HjSynchronizationInformation hs2: hsList){
            getTeam(hs2.getProjectNumber(),hs2.getApiKey(),hs2.getApiSecret(),hs2.getProjectId());
        }
        //主动回收一次
        System.gc();
    }
    /**
     * 同步项目人员定时器
     * @throws Exception
     */

//    @RequestMapping("/d")
//    @Scheduled(cron="0 0 2 * * ? ")
    public void setProjectWorkers()throws Exception{
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setPlatformName("YIZHITONG3");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        for(HjSynchronizationInformation hs2: hsList){
           getProjectUser(hs2.getProjectNumber(),hs2.getApiKey(),hs2.getApiSecret(),hs2.getProjectId());
        }
        //主动回收一次
        System.gc();
    }
    /**
     * 同步考勤记录定时器
     * @throws Exception
     */

//    @RequestMapping("/e")
//    @Scheduled(cron="0 0/15 * * * ? ")
    public void setJiLu()throws Exception{
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setPlatformName("YIZHITONG3");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        for(HjSynchronizationInformation hs2: hsList){
            getJiLu(hs2.getProjectNumber(),hs2.getApiKey(),hs2.getApiSecret(),hs2.getProjectId());
        }
        //主动回收一次
        System.gc();
    }
    private void getJiLu(String  xmcode,String api_key_szzjt,String api_secret_szzjt,Integer pid) throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String endTime=dateFormat.format(date.getTime());
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -15);//
        String startTime=dateFormat.format(beforeTime.getTime());
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setPlatformName("WORKSBUREAU");
        hs.setApiType("keytype1");
        hs.setProjectId(pid);
        hs.setState(1);
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        if(hsList.size()>0){
            HjSynchronizationInformation h=hsList.get(0);

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("xmjc","API");
            jsonObject.put("xmcode",xmcode);
            jsonObject.put("start",startTime);
//            jsonObject.put("end","2019-07-29 14:00");

            StringBuilder str4MD5 = new StringBuilder();
            str4MD5.append("xmcode=").append(xmcode)
                    .append("&xmjc=").append("API")
                    .append("&api_key_szzjt=").append(api_key_szzjt)
                    .append("&api_secret_szzjt=").append(api_secret_szzjt)
                    .append("&timestamp=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            String token=Tools.encodeToMD5(str4MD5.toString());
                //获取一指通的数据
                String result= HttpUtilsXh.xhHttpPost(Constants.YIZHITONG+"/ApiService/GetCockIn.ashx?token="+token,jsonObject.toString(),"");
//               String s="{\"success\":0,\"msg\":\"成功\",\"laststart\":\"2019-07-20 14:12:10\",\"data\":\"[{\\\"Shenfenzheng\\\":\\\"450127199308160618\\\",\\\"Name\\\":\\\"郑振达\\\",\\\"MachineAlias\\\":\\\"22出\\\",\\\"machine_sn\\\":\\\"1310356\\\",\\\"CheckTime\\\":\\\"2019/7/19 10:29:36\\\",\\\"inout\\\":\\\"2\\\",\\\"Location\\\":\\\"深圳市\\\",\\\"Longitude\\\":\\\"\\\",\\\"Latitude\\\":\\\"\\\",\\\"photopath\\\":\\\"http://gd.17hr.net:8018/Ephotoes/20190719/20190719102937838-19070001.JPEG\\\",\\\"photoname\\\":\\\"20190719102937838-19070001.JPEG\\\",\\\"AddTime\\\":\\\"2019-07-19 10:29:58\\\"},{\\\"Shenfenzheng\\\":\\\"450127199308160618\\\",\\\"Name\\\":\\\"郑振达\\\",\\\"MachineAlias\\\":\\\"22进\\\",\\\"machine_sn\\\":\\\"1307140\\\",\\\"CheckTime\\\":\\\"2019/7/19 10:30:04\\\",\\\"inout\\\":\\\"1\\\",\\\"Location\\\":\\\"深圳市\\\",\\\"Longitude\\\":\\\"\\\",\\\"Latitude\\\":\\\"\\\",\\\"photopath\\\":\\\"http://gd.17hr.net:8018/Ephotoes/20190719/20190719103006369-19070001.JPEG\\\",\\\"photoname\\\":\\\"20190719103006369-19070001.JPEG\\\",\\\"AddTime\\\":\\\"2019-07-19 10:32:15\\\"}]\"}";
                JSONObject  results = JSONObject.parseObject(result);
                String data=results.getString("data");
                data=data.replace("\\","");
                JSONArray list=JSONArray.parseArray(data);
                if(!"".equals(data)&&data!=null){
                    HjProjectWorkers hp;
                    JSONObject a;
                    JSONObject body;
                    JSONArray ja;
                    JSONObject body2;
                    JSONArray c=new JSONArray();
                    HjAttendanceRecord hr;
                    for(Object o:list){
                        hp=new HjProjectWorkers();


                        a  = JSONObject.parseObject(o.toString());
                        hp.setIdCode(a.getString("Shenfenzheng"));
                        hp.setProjectId(pid);
                        List<HjProjectWorkers> workersList=hjProjectWorkersService.selectHjProjectWorkersList(hp);
                        if(workersList.size()>0){
                            HjProjectWorkers hpw=workersList.get(0);
                            if(hpw.getEnterAndRetreatCondition()==0)
                            {
                                body=new JSONObject();
                                ja=new JSONArray();
                                body2=new JSONObject();
                                String time=a.getString("CheckTime").replace("/","-");
                                String hh= time.substring(time.lastIndexOf(" ")+1,time.indexOf(":"));
                                if(hh.length()<=1){
                                    time=time.replace(" "," 0");
                                }
                                String s1=time.substring(0,time.indexOf("-")+1);
                                String s2=time.substring(time.indexOf("-")+1,time.lastIndexOf("-")+1);
                                String s3=time.substring(time.lastIndexOf("-")+1);
                                if(s2.length()<=2){
                                    s2="0"+s2;
                                }
                                if(s3.length()<=10){
                                    s3="0"+s3;
                                }
                                time=s1+s2+s3;


                                body.put("Device_ID",Tools.encodeToMD5s(a.getString("machine_sn")));

                                body2.put("data_id",h.getApiKey()+Tools.encodeToMD5s(a.getString("Shenfenzheng")+a.getString("CheckTime")));
                                body2.put("person_id",a.getString("Shenfenzheng"));
                                body2.put("person_name",a.getString("Name"));
                                body2.put("passed_time",time);
                                body2.put("direction","1".equals(a.getString("inout")) ? "in" : "out");
                                body2.put("way","1");
                                body2.put("site_photo",ZCgetImageId.getImageId(h.getApiKey(),a.getString("photopath")));
                                ja.add(body2);
                                body.put("passedlog_list",ja);
                            logger.info(body.toJSONString());
                           String abc=     dockingGWS(body,h,"UploadPassedLog");
                                JSONObject a1=JSONObject.parseObject(abc);
                                String b1=a1.getString("result");
                                if("true".equals(b1)){
                                    logger.info("============"+a.getString("Name")+"===="+time+"====该考记录上传成功");
                            hr=new HjAttendanceRecord();
                            hr.setProjectId(workersList.get(0).getProjectId());
                            hr.setEmployeeId(workersList.get(0).getId());
                            hr.setSitePhoto(a.getString("photopath"));
                            hr.setPassedTime(time);
                            hr.setDirection("1".equals(a.getString("inout")) ? "in" : "2".equals(a.getString("inout")) ? "out" : "0");
                            //判断是否重复插入
                            List<HjAttendanceRecord> aList=hjAttendanceRecordService.selectHjAttendanceRecordList(hr);
                            if(aList.size()<=0) {

                                hr.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                hr.setWay(1);

                                hjAttendanceRecordService.insertHjAttendanceRecord(hr);
                            }
                            }else{
                                    logger.info("============"+a.getString("Name")+"===="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"====该考记录上传失败");
                                    logger.info("============失败原因："+a1.getString("message"));
                                }

                        }


                    }



                }
            }
        }
    }
    /**
     * 从一指通获取参建单位
     * @param xmcode
     * @param api_key_szzjt
     * @param api_secret_szzjt
     * @param pid
     * @throws Exception
     */
    private void getCompany(String  xmcode,String api_key_szzjt,String api_secret_szzjt,Integer pid) throws URISyntaxException, IOException
    {
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setProjectId(pid);
        hs.setPlatformName("WORKSBUREAU");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hs2List=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        //有秘钥才需要对接
        if(hs2List.size()>0) {
            HjSynchronizationInformation h = hs2List.get(0);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("xmjc","API");
        jsonObject.put("xmcode",xmcode);
        jsonObject.put("start","2019-01-01 00:00");
//        jsonObject.put("end", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("xmcode=").append(xmcode)
                .append("&xmjc=").append("API")
                .append("&api_key_szzjt=").append(api_key_szzjt)
                .append("&api_secret_szzjt=").append(api_secret_szzjt)
                .append("&timestamp=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        String token=Tools.encodeToMD5(str4MD5.toString());
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
        JSONObject body;
        for(Object o:list){
            HjConstructionCompany     hc=new HjConstructionCompany();

            a  = JSONObject.parseObject(o.toString());
            hc.setRemark(a.getString("FBDataID"));
            List<HjConstructionCompany> hcList=hjConstructionCompanyService.selectHjConstructionCompanyList(hc);
            //如果已存在该参建单位，则跳过
            if(hcList.size()<=0) {
                hd=new HjDictionaries();
                //上传工务署的body数据
                body=new JSONObject();
                body.put("Company_Name",a.getString("Name"));
                body.put("SUID",a.getString("OrgCode_XY"));
                body.put("Legal_Person",a.getString("OrgOwner"));
                body.put("entry_time",simpleDateFormat);
                //保存本地的数据
                hc.setConstructionName(a.getString("Name"));
                hc.setShortName(a.getString("ShortName"));
                hc.setLegalPerson(a.getString("OrgOwner"));
                hc.setSuid(a.getString("OrgCode_XY"));
                hc.setCreateDate(a.getString("RCDate"));
                //判断参建单位类型
                if ("监理单位".equals(a.getString("FenBaoType"))) {
                    hc.setCompanyType(2);
                    body.put("Type","007");
                } else if ("管理单位".equals(a.getString("FenBaoType"))) {
                    hc.setCompanyType(3);
                    body.put("Type","008");
                } else {
                    hd.setTitle(a.getString("FenBaoType"));
                    hd.setCategory("UNIT_TYPE");
                    List<HjDictionaries> hdList = hjDictionariesService.selectHjDictionariesList(hd);
                    if (hdList.size() > 0) {
                        hc.setCompanyType(hdList.get(0).getId());
                        String tag=hdList.get(0).getTag();
                        String[] q=tag.split("-");
                        body.put("Type",q[0]);
                    } else {
                        hc.setCompanyType(1);
                        body.put("Type","006");
                    }
                }
//               body.put("Type",hc.getCompanyType());
                hc.setContacts(a.getString("FuZeRen"));
                hc.setMobilePhone(a.getString("FuZeRenPhone"));
                System.out.println(body.toJSONString());

                    //对接工务署，成功才保存在本地
                    String abc = dockingGWS(body, h, "AddCompany");
                    JSONObject resultJson=JSONObject.parseObject(abc);
                    String b=resultJson.getString("result");

                    if ("true".equals(b)) {
                        logger.info("====项目："+pid+"=="+a.getString("Name")+"==对接工务署成功=======================");
                        hjConstructionCompanyService.insertHjConstructionCompany(hc);

                        hcp = new HjConstructionProject();
                        hcp.setProjectId(pid);
                        hcp.setConstructionId(hc.getId());
                        hjConstructionProjectService.insertHjConstructionProject(hcp);
                    }else{
                        logger.info("====项目："+pid+"=="+a.getString("Name")+"==对接工务署失败=======================");
                        logger.info("=============RUL:"+Constants.ZC_FORMALHOST+"AddCompany");
                        logger.info("=============原因："+resultJson.getString("message"));
                        logger.info("=============时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        if("企业已经存在这个工程中".equals(resultJson.getString("message"))){
                            hjConstructionCompanyService.insertHjConstructionCompany(hc);

                            hcp = new HjConstructionProject();
                            hcp.setProjectId(pid);
                            hcp.setConstructionId(hc.getId());
                            hjConstructionProjectService.insertHjConstructionProject(hcp);
                        }
                    }
                }
            }
        }
    }

    /**
     * 从一指通获取班组
     * @param xmcode
     * @param api_key_szzjt
     * @param api_secret_szzjt
     * @param pid
     * @throws Exception
     */
    public void getTeam(String  xmcode,String api_key_szzjt,String api_secret_szzjt,Integer pid) throws Exception
    {
        //该项目的对接秘钥
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setProjectId(pid);
        hs.setPlatformName("WORKSBUREAU");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hs2List=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        //有秘钥才需要对接
        if(hs2List.size()>0) {
            HjSynchronizationInformation h = hs2List.get(0);
            //查询班组类型
            HjDictionaries hd=new HjDictionaries();
            hd.setCategory("TEAM_TYPE");
            List<HjDictionaries> hdList=hjDictionariesService.selectHjDictionariesList(hd);

        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("xmjc","API");
        jsonObject.put("xmcode",xmcode);
        jsonObject.put("start","2019-01-01 00:00");
//        jsonObject.put("end",simpleDateFormat);

        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("xmcode=").append(xmcode)
                .append("&xmjc=").append("API")
                .append("&api_key_szzjt=").append(api_key_szzjt)
                .append("&api_secret_szzjt=").append(api_secret_szzjt)
                .append("&timestamp=").append(simpleDateFormat);
        String token=Tools.encodeToMD5(str4MD5.toString());
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
        JSONObject body;
        for(Object o:list){
            hc=new HjConstructionCompany();
            ht=new HjTeam();
            a  = JSONObject.parseObject(o.toString());
            ht.setRemark(a.getString("BZDataID"));
            List<HjTeam> hjList=hjTeamService.selectHjTeamList(ht);
            //没有的班组添加上传
            if(hjList.size()<=0) {
                body=new JSONObject();
                body.put("team_name",a.getString("Name"));
                //适配合适的班组类型
                HjDictionaries hd2=  selectTeamType(a.getString("Name"),hdList);
                if(hd2==null){
                    body.put("team_type_order","006");
                    body.put("team_type_name","普工班");
                    ht.setTeamType("006");
                }else{
                    body.put("team_type_order",hd2.getTag());
                    body.put("team_type_name",hd2.getTitle());
                    ht.setTeamType(hd2.getTag());
                }

                ht.setTeamName(a.getString("Name"));


                ht.setProjectId(pid);
                hc.setRemark(a.getString("FBDataID"));
                List<HjConstructionCompany> hcList=hjConstructionCompanyService.selectHjConstructionCompanyList(hc);
                if(hcList.size()>0){
                HjConstructionCompany hjConstructionCompany = hcList.get(0);
                ht.setConstructionId(hjConstructionCompany.getId());
                body.put("emp_company",hjConstructionCompany.getConstructionName());
                body.put("SUID",hjConstructionCompany.getSuid());
                String s=dockingGWS(body,h,"AddTeam");
                JSONObject resultJson=JSONObject.parseObject(s);
                String b=resultJson.getString("result");

                if("true".equals(b)){
                    logger.info("====项目："+pid+"==班组："+a.getString("Name")+"对接工务署成功=======================");
                    String datas=resultJson.getString("result_data");
                    JSONObject dsa=JSONObject.parseObject(datas);
                    ht.setTeamId(dsa.getString("team_id"));

                    hjTeamService.insertHjTeam(ht);
                }else {
                    logger.info("====项目：" + pid + "==班组：" + a.getString("Name") +  "对接工务署失败=======================");
                    logger.info("=============原因：" + resultJson.getString("message"));
                    logger.info("=============时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }
                }else{
                    logger.info("====项目："+pid+"=="+a.getString("Name")+"==未找到对应参建单位=======================");
                }

            }
        }
    }
    }

    /**
     * 获取一指通人员
     * @param xmcode 一指通项目编号
     * @return
     */
    public void getProjectUser(String  xmcode,String api_key_szzjt,String api_secret_szzjt,Integer pid) throws Exception {
        //该项目的对接秘钥
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setProjectId(pid);
        hs.setPlatformName("WORKSBUREAU");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hs2List=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        if(hs2List.size()>0) {
            HjSynchronizationInformation h = hs2List.get(0);
            String apiKey=h.getApiKey();
            //参数
            String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("xmjc", "API");
            jsonObject.put("xmcode", xmcode);
            jsonObject.put("start", "2010-01-01 00:00");
            jsonObject.put("end", simpleDateFormat);

            StringBuilder str4MD5 = new StringBuilder();
            str4MD5.append("xmcode=").append(xmcode)
                    .append("&xmjc=").append("API")
                    .append("&api_key_szzjt=").append(api_key_szzjt)
                    .append("&api_secret_szzjt=").append(api_secret_szzjt)
                    .append("&timestamp=").append(simpleDateFormat);
            String token = Tools.encodeToMD5(str4MD5.toString());
            //获取一指通的数据
            String result = HttpUtilsXh.xhHttpPost(Constants.YIZHITONG + "/ApiService/GetUser.ashx?token=" + token, jsonObject.toString(), "");
            JSONObject results = JSONObject.parseObject(result);
            String data = results.getString("data");
            data = data.replace("\\", "");
            JSONArray list = JSONArray.parseArray(data);
            JSONObject a;
//            JSONObject body;

            HjProjectWorkers workers;



            HjConstructionCompany hc;
            HjTeam ht;



            for (Object o : list) {
//                body = new JSONObject();
                workers = new HjProjectWorkers();
                hc=  new HjConstructionCompany();
                ht= new HjTeam();
                a = JSONObject.parseObject(o.toString());
                //是否有
                hc.setRemark(a.getString("FBDataID"));
                //参建单位是否存在
             List<HjConstructionCompany>   hcsList = hjConstructionCompanyService.selectHjConstructionCompanyList(hc);
                if(hcsList.size()>0){
                    HjConstructionCompany  hcs=hcsList.get(0);
                    //班组是否存在
                    ht.setRemark(a.getString("BZDataID"));
                    List<HjTeam> htsList=hjTeamService.selectHjTeamList(ht);
                    if(htsList.size()>0) {
                        HjTeam  hts = htsList.get(0);

                        // 人员是否存在
                        workers.setRemark(a.getString("Kaoqinhao"));//id,一指通工号
                        workers.setProjectId(pid);
                        List<HjProjectWorkers> hp1List = hjProjectWorkersService.selectHjProjectWorkersList(workers);
                        String statusYZT=a.getString("Status");
                        //人员存在的话根据状态操作
                        if(hp1List.size()>0){
                            HjProjectWorkers p=hp1List.get(0);
                            //人员本地状态
                            String statusBD=p.getEnterAndRetreatCondition().toString();
                            //人员存在但状态不一致，根据状态进行进场或退出操作
                            if(statusBD.equals(statusYZT)){
                                //如果是在场，就进场,本地更新状态
                                if("1".equals(statusYZT)){
                                        JSONObject body=getBodyJC(a,hcs,hts,apiKey);
                                    String s=dockingGWS(body,h,"RegisterEmployee");
                                    JSONObject resultJson=JSONObject.parseObject(s);
                                    String b=resultJson.getString("result");
                                    if("true".equals(b)){
                                        logger.info("========="+a.getString("Name")+":成功=============================");
                                        p.setEnterAndRetreatCondition(0);
                                        hjProjectWorkersService.updateHjProjectWorkers(p);
                                    }else{
                                        logger.info("========="+a.getString("Name")+":失败=============================");
                                    }
                                }else{
                                    //如果是离场，就人员退场，本地更新状态
                                    JSONObject body=getBodyLC(a);
                                    String s=dockingGWS(body,h,"userLeaveProject");
                                    JSONObject resultJson=JSONObject.parseObject(s);
                                    String b=resultJson.getString("result");
                                    if("true".equals(b)){
                                        logger.info("========="+a.getString("Name")+":成功=============================");
                                        p.setEnterAndRetreatCondition(1);
                                        hjProjectWorkersService.updateHjProjectWorkers(p);
                                    }else{
                                        logger.info("========="+a.getString("Name")+":失败=============================");
                                    }
                                }
                            }

                        }else{
                         //如果人员不存在，则看状态，如在场，则进场保存，若离场，则保存
                                if("1".equals(statusYZT)){
                                    //进场保存
                                    JSONObject body=getBodyJC(a,hcs,hts,apiKey);
                                    String s=dockingGWS(body,h,"RegisterEmployee");
                                    JSONObject resultJson=JSONObject.parseObject(s);
                                    String b=resultJson.getString("result");
                                    if("true".equals(b)){
                                        logger.info("========="+a.getString("Name")+":成功=============================");
                                        getPW(a,workers,pid,hcs,hts);
                                        hjProjectWorkersService.insertHjProjectWorkers(workers);
                                    }else{
                                        logger.info("========="+a.getString("Name")+":失败=============================");
                                    }

                                }else{
                                    //只保存
                                    getPW(a,workers,pid,hcs,hts);
                                    hjProjectWorkersService.insertHjProjectWorkers(workers);
                                }

                        }


                    }
                }
            }
        }
    }

private void getPW(JSONObject a,HjProjectWorkers workers,Integer pid,HjConstructionCompany hcs,HjTeam hts){
    workers.setEmpName(a.getString("Name"));
    //转换工种
    HjDictionaries dict = new HjDictionaries();
    dict.setCategory("WORK_TYPE");
    dict.setTitle(a.getString("Zhicheng"));
    List<HjDictionaries>  dictList = hjDictionariesService.selectHjDictionariesList(dict);
    if (dictList.size() > 0) {
        workers.setJobName(dictList.get(0).getTag());

    } else {
        workers.setJobName("9531DEDC24FA502F3FDE72D720D0942E");

    }
    workers.setEnterAndRetreatCondition("1".equals(a.getString("Status")) ? 0 : 1);
    workers.setIdCode(a.getString("Shenfenzheng"));
    workers.setEmpPhon(a.getString("ShoujiHaoma"));
    workers.setEmpSex(a.getString("Xingbie"));
    workers.setEmpNation(a.getString("Mingzu"));
    workers.setIdAddress(a.getString("JiatingDz"));
    workers.setIdAgency(a.getString("id_agency"));
    workers.setIdValiddate(a.getString("id_validdate"));
    workers.setNativePlace(a.getString("Jiguan"));
    workers.setStartTime(a.getString("RuzhiRq"));
    workers.setEndTime(a.getString("LiZhiRq"));
    workers.setProjectId(pid);//所属项目ID


    workers.setConstructionId(hcs.getId());//参建单位ID
//                b.put("unitId", hcs.getId());
//                b.put("unitName", hcs.getConstructionName());

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

}
private JSONObject getBodyJC(JSONObject a,HjConstructionCompany hc,HjTeam ht,String apiKey) throws  Exception{
        JSONObject body=new JSONObject();
        body.put("id_code",a.getString("Shenfenzheng"));//身份证号
        body.put("id_photo", ZCgetImageId.getImageId(apiKey,Constants.YIZHITONGIMG + "/" + a.getString("EmpPhoto")));//身份证头像
        body.put("emp_name",a.getString("Name"));//姓名
        body.put("emp_phone",a.getString("ShoujiHaoma"));//手机号码
        body.put("emp_nativeplace",a.getString("JiatingDz"));//身份证地址
        body.put("emp_nation",a.getString("Mingzu"));//民族
        body.put("pass_period",a.getString("RuzhiRq"));//入场日期
        body.put("match_flag","Y");//匹配标识。’ Y’—人证匹配，’N’—不匹配，3次采集比对都没通过
        body.put("facephoto",ZCgetImageId.getImageId(apiKey,Constants.YIZHITONGIMG + "/" + a.getString("EmpPhotoNow")));//人员照片
        body.put("emp_company",hc.getConstructionName());//所属单位名称
        body.put("SUID",hc.getSuid());//所属单位信用代码
        body.put("team_id",ht.getTeamId());//班组id
        body.put("team_name",ht.getTeamName());//班组名称
        body.put("emp_category","00");//人员类型
        HjDictionaries hd=selectJob(ht,a.getString("Zhicheng"));
        if(hd!=null) {
            body.put("job_order", hd.getTag());//工种类型
            body.put("job_name", hd.getTitle());
        }else{
            body.put("job_order", "39");//工种类型
            body.put("job_name", "其它");
        }
        body.put("contract_status",!"".equals(a.getString("HetongHao")) && a.getString("HetongHao") != "" ? 1 : 0);//是否签合同
        body.put("id_agency",a.getString("id_agency"));//签发机关
        body.put("id_validdate",a.getString("id_validdate"));//证件有效期
        body.put("emp_bankname",a.getString("BankName"));//开户行
        body.put("emp_cardnum",a.getString("BankAcount"));//开户账号
        return body;
}

    private JSONObject getBodyLC(JSONObject a) throws  Exception{
        JSONObject body=new JSONObject();
        JSONArray ja=new JSONArray();
        JSONObject body2=new JSONObject();
        body2.put("id_code",a.getString("Shenfenzheng"));//身份证
        body2.put("exit_time",a.getString("LiZhiRq")+" 00:00:00");//离场日期
        ja.add(body2);
        body.put("userLeaveProject_list",ja);
return body;
    }

    /**
     * 适配工种
     * @return
     */
    private  HjDictionaries selectJob(HjTeam ht,String zhiCheng){
        HjDictionaries hds=new HjDictionaries();
        hds.setCategory("WORK_TYPE_GWS");
        List<HjDictionaries> hdList=hjDictionariesService.selectHjDictionariesList(hds);
        String teamName=ht.getTeamName();
        teamName=teamName.substring(teamName.lastIndexOf("-")+1);
        teamName=teamName.replaceAll("班","");
        for(HjDictionaries hd:hdList){
            if(hd.getTitle().indexOf(teamName)!=-1){
                return hd;
            }
            if(hd.getTitle().indexOf(zhiCheng)!=-1){
                return hd;
            }
        }
        return null;
    }






    /**
     * 适配合适班组类型
     * @param teamName
     * @param hdList
     * @return
     */
    private HjDictionaries selectTeamType(String teamName,List<HjDictionaries> hdList){
        teamName=teamName.substring(teamName.lastIndexOf("-")+1);
        teamName=teamName.replaceAll("班组","");
        teamName=teamName.replaceAll("班","");
        for(HjDictionaries hd: hdList){
            if(hd.getTitle().indexOf(teamName)!=-1){
                return hd;
            }
        }
    return null;
}

    /**
     * 对接工务署
     * @param json body数据
     * @param hs 秘钥
     * @param url 路径
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    private String dockingGWS(JSONObject json,HjSynchronizationInformation hs,String url) throws  URISyntaxException,IOException{

        url=Constants.ZC_FORMALHOST+url;
        //添加工程编号
        json.put("Project_ID",hs.getEngineeringCode());
        System.out.println(json);
        //拼装完整URL
        String s= ZCAPIClientTwo.getUrl(hs.getApiSecret(),hs.getApiKey(),"1.0",hs.getApiKey(),json.toString(),url);
//        System.out.println(s);
        //访问接口得到返回值
       String result= ZCAPIClientTwo.httpPostWithJSON(s,json);

        return result;
    }

    @PostMapping("aq")
    public void as()throws  Exception {
        String url = "http://szwb.sz.gov.cn:2018/CWRService/UploadPassedLog";
        JSONObject json = JSONObject.parseObject("{\"Device_ID\":\"81BAE9C763A71270597CFC8DE375AAE3\",\"passedlog_list\":[{\"data_id\":\"26c83cdfd82a40d28b347bf098ba863eADD1B903EC063C17ECEC294F0A1FE535\",\"person_name\":\"黄先创\",\"passed_time\":\"2019-8-4 09:21:44\",\"way\":\"1\",\"site_photo\":\"08cca34b00b5415981fd6c4d5013f4bd\",\"person_id\":\"440582199510286657\",\"direction\":\"out\"}]}");
        json.put("Project_ID", "XM2016048402");
        String s = ZCAPIClientTwo.getUrl("fb2ba818f6b74b1fa84854a01ae8905f", "26c83cdfd82a40d28b347bf098ba863e", "1.0", "26c83cdfd82a40d28b347bf098ba863e", json.toString(), url);
        System.out.println(json.toJSONString());
        String result = ZCAPIClientTwo.httpPostWithJSON(s, json);
        System.out.println(result);
    }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date date = new Date();
//        String endTime = dateFormat.format(date.getTime());
//        Calendar beforeTime = Calendar.getInstance();
//        beforeTime.add(Calendar.MINUTE, -15);//
//        String startTime = dateFormat.format(beforeTime.getTime());
//        System.out.println(startTime);
////        List<HjSynchronizationInformation> hsList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
////        if (hsList.size() > 0) {
////            HjSynchronizationInformation h = hsList.get(0);
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("xmjc", "API");
//            jsonObject.put("xmcode", "XM0000478");
//            jsonObject.put("start", startTime);
////            jsonObject.put("end","2019-07-29 14:00");
//        System.out.println("=================");
//        System.out.println(jsonObject);
//            StringBuilder str4MD5 = new StringBuilder();
//            str4MD5.append("xmcode=").append("XM0000478")
//                    .append("&xmjc=").append("API")
//                    .append("&api_key_szzjt=").append("9dde9e95f6d74822933738a4cf42e3ff")
//                    .append("&api_secret_szzjt=").append("485de7f28c2a49e7bae86a1dcae7229a")
//                    .append("&timestamp=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
//            String token = Tools.encodeToMD5(str4MD5.toString());
//            //获取一指通的数据
//            String result = HttpUtilsXh.xhHttpPost(Constants.YIZHITONG + "/ApiService/GetCockIn.ashx?token=" + token, jsonObject.toString(), "");
////               String s="{\"success\":0,\"msg\":\"成功\",\"laststart\":\"2019-07-20 14:12:10\",\"data\":\"[{\\\"Shenfenzheng\\\":\\\"450127199308160618\\\",\\\"Name\\\":\\\"郑振达\\\",\\\"MachineAlias\\\":\\\"22出\\\",\\\"machine_sn\\\":\\\"1310356\\\",\\\"CheckTime\\\":\\\"2019/7/19 10:29:36\\\",\\\"inout\\\":\\\"2\\\",\\\"Location\\\":\\\"深圳市\\\",\\\"Longitude\\\":\\\"\\\",\\\"Latitude\\\":\\\"\\\",\\\"photopath\\\":\\\"http://gd.17hr.net:8018/Ephotoes/20190719/20190719102937838-19070001.JPEG\\\",\\\"photoname\\\":\\\"20190719102937838-19070001.JPEG\\\",\\\"AddTime\\\":\\\"2019-07-19 10:29:58\\\"},{\\\"Shenfenzheng\\\":\\\"450127199308160618\\\",\\\"Name\\\":\\\"郑振达\\\",\\\"MachineAlias\\\":\\\"22进\\\",\\\"machine_sn\\\":\\\"1307140\\\",\\\"CheckTime\\\":\\\"2019/7/19 10:30:04\\\",\\\"inout\\\":\\\"1\\\",\\\"Location\\\":\\\"深圳市\\\",\\\"Longitude\\\":\\\"\\\",\\\"Latitude\\\":\\\"\\\",\\\"photopath\\\":\\\"http://gd.17hr.net:8018/Ephotoes/20190719/20190719103006369-19070001.JPEG\\\",\\\"photoname\\\":\\\"20190719103006369-19070001.JPEG\\\",\\\"AddTime\\\":\\\"2019-07-19 10:32:15\\\"}]\"}";
//            JSONObject results = JSONObject.parseObject(result);
//
//        }
//    @PostMapping("aq")
//    public void asb()throws  Exception {
//      String a=  PrintJobTo.zxToWatermark("http://gd.17hr.net:8018/Ephotoes/20190719/20190719102937838-19070001.JPEG","2019-8-1 11:47:00");
////     String as=ZCgetImageId.getImageIds("26c83cdfd82a40d28b347bf098ba863e","http://gd.17hr.net:8018/Ephotoes/20190719/20190719102937838-19070001.JPEG","2019-8-1 15:53:00");
//        System.out.println(a);
//    }
}
