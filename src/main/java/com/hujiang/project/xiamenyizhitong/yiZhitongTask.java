package com.hujiang.project.xiamenyizhitong;


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
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
//@RestController
//@RequestMapping(value = "/provider/o")
public class yiZhitongTask extends AutoTaskBase {
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Resource
    private TokenApi tokenApi;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    private Logger logger = Logger.getLogger(yiZhitongTask.class.getName());

    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;
    @Autowired
    private IHjTeamService hjTeamService;

    @Autowired
    private IHjDictionariesService hjDictionariesService;


    @Autowired
    private IHjConstructionProjectService hjConstructionProjectService;


    @Scheduled(cron="0 0 5 * * ? ")
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
    @Scheduled(cron="0 0/30 * * * ?")
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
    /**
     * 每个小时获取一指通考勤记录
     */
//    @PostMapping(value = "/a")
    public void  setProjectUser() throws Exception {

        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setPlatformName("YIZHITONG4");
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
//    @PostMapping(value = "/b")
    public void setJiLu()throws Exception{
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setState(1);
        hs.setPlatformName("YIZHITONG4");
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
            String token= Tools.encodeToMD5(str4MD5.toString());
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




                            hr=new HjAttendanceRecord();
                            hr.setProjectId(workersList.get(0).getProjectId());
                            hr.setEmployeeId(workersList.get(0).getId());
                            hr.setSitePhoto(a.getString("photopath"));
                            hr.setPassedTime(time);
                            hr.setDirection("1".equals(a.getString("inout")) ? "in" : "2".equals(a.getString("inout")) ? "out" : "0");
                            //判断是否重复插入
                            List<HjAttendanceRecord> aList=hjAttendanceRecordService.selectHjAttendanceRecordList(hr);
                            if(aList.size()<=0) {


                                hr.setWay(1);

                                hjAttendanceRecordService.insertHjAttendanceRecord(hr);
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

                        p.setEnterAndRetreatCondition(0);
                        hjProjectWorkersService.updateHjProjectWorkers(p);

                    }else{
                        //如果是离场，就人员退场，本地更新状态

                        p.setEnterAndRetreatCondition(1);
                        hjProjectWorkersService.updateHjProjectWorkers(p);
                    }
                }


            }else{
                //如果人员不存在，则看状态，如在场，则进场保存，若离场，则保存

                //进场保存


                getPW(a,workers,pid,hcs,hts);
                hjProjectWorkersService.insertHjProjectWorkers(workers);



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
}
