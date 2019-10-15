package com.hujiang.project.zhgd.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.xh.tokenApi.TokenApi;
import com.hujiang.project.xh.utils.HttpUtilsXh;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/asd")
public class APIClientDG {
    @Autowired
    private IHjDictionariesService hjDictionariesService;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
            private    IHjDeviceProjectworkersService hjDeviceProjectworkersService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Resource
    private TokenApi tokenApi;
    @Autowired
    private IHjTeamService hjTeamService;
    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;
    @RequestMapping(value = "/f")
    public  void main123() throws Exception {
//        System.out.println("wer");
//        String s=new String(Files.readAllBytes(new File("C:\\a.txt").toPath()));
//
//        JSONObject ss=JSONObject.parseObject(s);
//        JSONArray data=ss.getJSONArray("data");
//        HjDictionaries d;
//        for(Object o: data){
//            JSONObject type=JSONObject.parseObject(o.toString());
//            if("5".equals(type.getString("type"))){
//                d=new HjDictionaries();
//                d.setTag(type.getString("orders"));
//                d.setGroupTitle("班组名称");
//                d.setCategory("TEAM_TYPE_HOUS");
//                d.setTitle(type.getString("value"));
//                hjDictionariesService.insertHjDictionaries(d);
//            }
//        }
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setProjectId(351);
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        HjProjectWorkers hpw=new HjProjectWorkers();
        hpw.setProjectId(351);
        hpw.setEnterAndRetreatCondition(0);
        List<HjProjectWorkers> hpwList=hjProjectWorkersService.selectHjProjectWorkersList(hpw);
        System.out.println(hadList.size()+"======="+hpwList.size());
        HjDeviceProjectworkers hdpw;
        for( HjAttendanceDevice d:hadList){
            for(HjProjectWorkers w: hpwList){
                hdpw=new HjDeviceProjectworkers();
                hdpw.setStatus("0");
                hdpw.setDeviceNo(d.getDeviceNo());
                hdpw.setProjectWorkersId(w.getId());
                hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hdpw);
            }
        }


//        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("xmjc","API");
//        jsonObject.put("xmcode",xmcode);
//        jsonObject.put("start","2019-01-01 00:00");
//        jsonObject.put("end",simpleDateFormat);
//
//        StringBuilder str4MD5 = new StringBuilder();
//        str4MD5.append("xmcode=").append(xmcode)
//                .append("&xmjc=").append("API")
//                .append("&api_key_szzjt=").append(api_key_szzjt)
//                .append("&api_secret_szzjt=").append(api_secret_szzjt)
//                .append("&timestamp=").append(simpleDateFormat);
//        String token=tokenApi.getMD5(str4MD5.toString());
//        //获取一指通的数据
//        String result= HttpUtilsXh.xhHttpPost(Constants.YIZHITONG+"/ApiService/TeamInfo.ashx?token="+token,jsonObject.toString(),"");
//        JSONObject  results = JSONObject.parseObject(result);
//        String data=results.getString("data");
//        data=data.replace("\\","");
////        System.out.println(data);
//        JSONArray list=JSONArray.parseArray(data);
//        JSONObject  a;
//        HjConstructionCompany hc;
//        HjTeam ht;
//        for(Object o:list){
//            hc=new HjConstructionCompany();
//            ht=new HjTeam();
//            a  = JSONObject.parseObject(o.toString());
//            ht.setRemark(a.getString("BZDataID"));
//
//            ht.setProjectId(pid);
//
//            hc.setRemark(a.getString("FBDataID"));
//            HjConstructionCompany hjConstructionCompany=hjConstructionCompanyService.selectHjConstructionCompanyList(hc).get(0);
//            ht.setConstructionId(hjConstructionCompany.getId());
//            HjDictionaries hd=new HjDictionaries();
//            hd.setTitle(a.getString("Name").substring(0,2));
//            hd.setCategory("TEAM_TYPE_HOUS");
//            List<HjDictionaries> hdList=hjDictionariesService.selectHjDictionariesList(hd);
//            if(hdList.size()>0){
//                ht.setTeamType(hdList.get(0).getTag());
//                ht.setTeamName(hdList.get(0).getTitle());
//            }else{
//                ht.setTeamType("B47A5CAC0D751E04D18356AC2ADCA830");
//                ht.setTeamName("其他");
//            }
//            hjTeamService.insertHjTeam(ht);
//        }
    }
}
