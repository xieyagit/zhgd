package com.hujiang.project.rcanRealName.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.AutoTaskBase;
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
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Tools;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@RestController
//@RequestMapping(value = "/provider/zxc")
@Component
public class RcanRealNameTask extends AutoTaskBase {
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;
    @Autowired
    private IHjTeamService hjTeamService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjDictionariesService hjDictionariesService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @Resource
    private TokenApi tokenApi;
    @Autowired
    private IHjConstructionProjectService hjConstructionProjectService;

    @Scheduled(cron="0 0 4 * * ? ")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    setProjectUser();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }

    @Scheduled(cron="0 0/30 * * * ? ")
    public void task2() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    setJiLu();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }

    @PostMapping(value = "/v")
    public void  setProjectUser() throws Exception {

        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setPlatformName("YIZHITONG5");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        for(HjSynchronizationInformation hs2: hsList){
            getProjectUser(hs2.getProjectNumber(),hs2.getApiKey(),hs2.getApiSecret(),hs2.getProjectId());
        }
        //主动回收一次
        System.gc();
    }

    /**
     * 同步考勤记录
     * @throws Exception
     */
    @PostMapping(value = "/b")
    public void setJiLu()throws Exception{
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setPlatformName("YIZHITONG5");
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
        beforeTime.add(Calendar.MINUTE, -30);//
        String startTime=dateFormat.format(beforeTime.getTime());
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setPlatformName("PERSONNEL");
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

                            body2.put("person_type",h.getApiKey()+Tools.encodeToMD5s(a.getString("Shenfenzheng")+a.getString("CheckTime")));
                            body2.put("person_id",a.getString("Shenfenzheng"));
                            body2.put("person_name",a.getString("Name"));
                            body2.put("passed_time",time);
                            body2.put("direction","1".equals(a.getString("inout")) ? "in" : "out");
                            body2.put("way","1");
                            body2.put("site_photo",BASE64DecodedMultipartFile.ImageToBase64ByOnline(a.getString("photopath")));
                            ja.add(body2);
                            body.put("passedlog_list",ja);

                            String abc=     dockingRCAJ(body.toString(),h,"/api/attendance/post");
                            JSONObject a1=JSONObject.parseObject(abc);
                            String b1=a1.getString("success");
                            if("true".equals(b1)){

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
                            }

                        }


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
        hs.setPlatformName("PERSONNEL");
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
                if(hcsList.size()<=0){
                    //添加参见单位
                    setCompany(xmcode,api_key_szzjt,api_secret_szzjt,pid);
                }
                List<HjConstructionCompany>   hcsList2 = hjConstructionCompanyService.selectHjConstructionCompanyList(hc);
                HjConstructionCompany  hcs=hcsList2.get(0);
                //班组是否存在
                ht.setRemark(a.getString("BZDataID"));
                List<HjTeam> htsList=hjTeamService.selectHjTeamList(ht);
                if(htsList.size()<=0) {
                    //添加班组
                    setTeam(xmcode,api_key_szzjt,api_secret_szzjt,pid);
                }
                List<HjTeam> htsList2=hjTeamService.selectHjTeamList(ht);
                HjTeam  hts = htsList2.get(0);

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
                            JSONArray body=getBodyJC(a,hcs,hts,apiKey);
                            String s=dockingRCAJ(body.toString(),h,"/api/person/post");
                            JSONObject resultJson=JSONObject.parseObject(s);
                            String b=resultJson.getString("success");
                            if("true".equals(b)){

                                p.setEnterAndRetreatCondition(0);
                                hjProjectWorkersService.updateHjProjectWorkers(p);
                            }
                        }else{
                            //如果是离场，就人员退场，本地更新状态
                            JSONObject body=getBodyLC(a);
                            String s=dockingRCAJ(body.toString(),h,"/api/Person/Remove");
                            JSONObject resultJson=JSONObject.parseObject(s);
                            String b=resultJson.getString("success");
                            if("true".equals(b)){

                                p.setEnterAndRetreatCondition(1);
                                hjProjectWorkersService.updateHjProjectWorkers(p);
                            }
                        }
                    }

                }else{
                    //如果人员不存在，则看状态，如在场，则进场保存，若离场，则保存
                    if("1".equals(statusYZT)){
                        //进场保存
                        JSONArray body=getBodyJC(a,hcs,hts,apiKey);
                        String s=dockingRCAJ(body.toString(),h,"/api/person/post");
                        JSONObject resultJson=JSONObject.parseObject(s);
                        String b=resultJson.getString("success");
                        if("true".equals(b)){

                            getPW(a,workers,pid,hcs,hts);
                            hjProjectWorkersService.insertHjProjectWorkers(workers);
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
    public void setTeam(String  xmcode,String api_key_szzjt,String api_secret_szzjt,Integer pid) throws Exception {
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
        HjConstructionCompany hc;
        HjTeam ht;
        for(Object o:list) {
            hc = new HjConstructionCompany();
            ht = new HjTeam();
            a = JSONObject.parseObject(o.toString());
            ht.setRemark(a.getString("BZDataID"));

            ht.setProjectId(pid);
            List<HjTeam> hjList = hjTeamService.selectHjTeamList(ht);
            //没有的班组添加上传
            if (hjList.size() <= 0) {
                hc.setRemark(a.getString("FBDataID"));
                HjConstructionCompany hjConstructionCompany = hjConstructionCompanyService.selectHjConstructionCompanyList(hc).get(0);
                ht.setConstructionId(hjConstructionCompany.getId());
                HjDictionaries hd = new HjDictionaries();
                hd.setTitle(a.getString("Name").substring(0, 2));
                hd.setCategory("TEAM_TYPE_HOUS");
                List<HjDictionaries> hdList = hjDictionariesService.selectHjDictionariesList(hd);
                if (hdList.size() > 0) {
                    ht.setTeamType(hdList.get(0).getTag());
                    ht.setTeamName(hdList.get(0).getTitle());
                } else {
                    ht.setTeamType("B47A5CAC0D751E04D18356AC2ADCA830");
                    ht.setTeamName("其他");
                }
                hjTeamService.insertHjTeam(ht);
            }
        }
    }
    public void setCompany(String  xmcode,String api_key_szzjt,String api_secret_szzjt,Integer pid) throws Exception
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
            hc.setProjectId(pid);
            List<HjConstructionCompany> hcList=hjConstructionCompanyService.selectHjConstructionCompanyList(hc);
            if(hcList.size()<=0) {
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
            workers.setJobName("6744ABB15D854F52BAB5829F2D9D6BA0");

        }
        workers.setEnterAndRetreatCondition("1".equals(a.getString("Status")) ? 0 : 1);
        workers.setIdCode(a.getString("Shenfenzheng"));
        workers.setEmpPhon(a.getString("ShoujiHaoma"));
        workers.setEmpSex("男".equals(a.getString("Xingbie"))?"男":"女");
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
    private JSONObject getBodyLC(JSONObject a) {
        JSONObject body=new JSONObject();
        JSONArray ja=new JSONArray();
        JSONObject body2=new JSONObject();
        body2.put("id_code",a.getString("Shenfenzheng"));//身份证
        ja.add(body2);
        body.put("userLeaveProject_list",ja);
        return body;
    }
    private String dockingRCAJ(String json,HjSynchronizationInformation hs,String url1) throws URISyntaxException, IOException {



        CloseableHttpClient client;
        URL url = new URL(Constants.RCAJ+url1);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);

        HttpPost http=new HttpPost(uri);

        http.addHeader("AppKey", hs.getApiKey());

        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。

        StringEntity entity = new StringEntity(json, "UTF-8");
        entity.setContentType("application/json");
        http.setEntity(entity);

        HttpResponse resp = client.execute(http);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + Constants.RCAJ+url1);
        System.out.println(respContent);
        return respContent;
    }

    private JSONArray getBodyJC(JSONObject a,HjConstructionCompany hc,HjTeam ht,String apiKey) throws  Exception{
        JSONObject body=new JSONObject();
        JSONArray body1=new JSONArray();
        body.put("id_code",a.getString("Shenfenzheng"));//身份证号
        body.put("id_photo", BASE64DecodedMultipartFile.ImageToBase64ByOnline(Constants.YIZHITONGIMG + "/" + a.getString("EmpPhoto")));//身份证头像
        body.put("emp_name",a.getString("Name"));//姓名
        body.put("emp_phone",a.getString("ShoujiHaoma"));//手机号码
        body.put("emp_nativeplace",a.getString("JiatingDz"));//身份证地址
        body.put("emp_nation",a.getString("Mingzu"));//民族
        body.put("pass_period",a.getString("RuzhiRq"));//入场日期
        body.put("match_flag","Y");//匹配标识。’ Y’—人证匹配，’N’—不匹配，3次采集比对都没通过
        body.put("facephoto",BASE64DecodedMultipartFile.ImageToBase64ByOnline(Constants.YIZHITONGIMG + "/" + a.getString("EmpPhotoNow")));//人员照片
        body.put("emp_company",hc.getConstructionName());//所属单位名称

        body.put("work_typename",ht.getTeamType());//班组id

        body.put("emp_category",getEmpCategory(hc));//人员类型


        HjDictionaries hd=selectJob(a.getString("Zhicheng"));
        if(hd!=null) {
            body.put("job_name", hd.getTag());//工种类型
            body.put("cwr_iskeypsn",getCwr(hd));//重要人员
        }else{
            body.put("job_name", "6744ABB15D854F52BAB5829F2D9D6BA0");//工种类型
            body.put("cwr_iskeypsn","0");
        }
        body.put("job_typename","1");//工种/岗位


        body.put("contract_status",!"".equals(a.getString("HetongHao")) && a.getString("HetongHao") != "" ? 1 : 0);//是否签合同
        body.put("id_agency",a.getString("id_agency"));//签发机关
        body.put("id_validdate",a.getString("id_validdate"));//证件有效期
        body.put("idphoto_scan",BASE64DecodedMultipartFile.ImageToBase64ByOnline(Constants.YIZHITONGIMG + "/" + a.getString("ShenFenZPhoto")));//
        body.put("idphoto_scan2",BASE64DecodedMultipartFile.ImageToBase64ByOnline(Constants.YIZHITONGIMG + "/" + a.getString("ShenFenFPhoto")));//
        body1.add(body);
        return body1;
    }
    private String getCwr(HjDictionaries hd){
        String is=hd.getTag();
        if("BE86667205808221FAD7ED510E2A8004".equals(is)||"DD8FE97F3E1FA3B7F71C894F0D7B49A1".equals(is)||"3BE4F91A3C3E4BF3E3E14E9A143C65C7".equals(is)||"E303B2F084CCAFA5A32CDC68571CC121".equals(is)||"764A5294753B5FCE846BD5C64C3874D1".equals(is)||"4BCE1F084E5E2413384C149B42B0D072".equals(is)||"6C0A7C1254EB51E1A94CCD318253562B".equals(is)){
            return "1";
        }
        return "0";
    }
    private  HjDictionaries selectJob(String zhiCheng){
        HjDictionaries hds=new HjDictionaries();
        hds.setCategory("WORK_TYPE");
        List<HjDictionaries> hdList=hjDictionariesService.selectHjDictionariesList(hds);

        for(HjDictionaries hd:hdList){

            if(hd.getTitle().indexOf(zhiCheng)!=-1){
                return hd;
            }
        }
        return null;
    }
    private String getEmpCategory(HjConstructionCompany hc){
        if(hc.getCompanyType()==3){
            return "01";
        }else if(hc.getCompanyType()==2){
            return "02";
        }else if(hc.getCompanyType()==4){
            return "03";
        }else{
            return "00";
        }
    }
}
