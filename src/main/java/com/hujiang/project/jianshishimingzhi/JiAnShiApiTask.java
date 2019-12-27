package com.hujiang.project.jianshishimingzhi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.ZCAPIClientTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
//@RestController
//@RequestMapping(value = "/provider/jianshiTask")
public class JiAnShiApiTask extends AutoTaskBase {

    @Scheduled(cron="0 0/20 * * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    setProjectWorkers();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }

    @Autowired
    private IHjSynchronizationInformationService synchronizationInformationService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private IHjDeviceProjectworkersService hjDeviceProjectworkersService;

    /**
     * 定时获取增量
     */
//    @PostMapping(value = "/a")
    public void  setProjectWorkers() throws  Exception{
        HjSynchronizationInformation h=new HjSynchronizationInformation();
        h.setState(1);
        h.setPlatformName("JIANSHI");
        h.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=synchronizationInformationService.selectHjSynchronizationInformationList(h);
        for(HjSynchronizationInformation hs2: hsList){
            projectWorker(hs2);
        }
    }
    public void projectWorker(HjSynchronizationInformation hs)throws  Exception{
            Integer pid=hs.getProjectId();
            String url= Constants.JIANSHI_SHIMINGZHI+"/attendance.rest/attendanceIncrement";
            HjAttendanceDevice hd=new HjAttendanceDevice();
            hd.setProjectId(pid);
            hd.setStatus("1");
            List<HjAttendanceDevice> hdList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(hd);
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -20);//
        String lastModifyTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeTime.getTime());

            JSONObject json=new JSONObject();
            json.put("projectCode",hs.getProjectNumber());
            json.put("keyId",hs.getClientSerial());
            json.put("projectKeyId",hs.getApiKey());
            json.put("lastModifyTime",lastModifyTime);

            //获取第一页数据
            String result=getResult(json,url,hs,0);
            JSONObject s=JSONObject.parseObject(result);
            if("0".equals(s.getString("code"))){
                JSONObject page=s.getJSONObject("page");
                JSONArray content=s.getJSONArray("content");
                //保存人员
                setProjectWorker(content,pid,hdList);
                if(page.getInteger("totalPages")>1){
                    String results;
                    JSONObject ss;
                    JSONArray contents;
                    for(int i=1;i<page.getInteger("totalPages");i++){
                        results=getResult(json,url,hs,i);
                        ss=JSONObject.parseObject(results);
                        contents=ss.getJSONArray("content");
                        setProjectWorker(contents,pid,hdList);
                    }
                }
            }


    }

    /**
     * 保存人员
     * @param content
     */
    public void setProjectWorker(JSONArray content,Integer pid,List<HjAttendanceDevice> hdList)throws  Exception{
        HjProjectWorkers workers2;
        HjProjectWorkers workers;
        JSONObject a;
        HjDeviceProjectworkers hdp;
        for (Object o : content) {
            a = JSONObject.parseObject(o.toString());
            workers2 = new HjProjectWorkers();
            workers2.setIdCode(a.getString("idCard"));
            List<HjProjectWorkers> hpwList=hjProjectWorkersService.selectHjProjectWorkersList(workers2);
            if(hpwList.size()>0){
                String status1 = a.getString("status");

                workers=hpwList.get(0);
                String status2=workers.getEnterAndRetreatCondition().toString();
                workers.setProjectId(pid);
                workers.setEmpName(a.getString("wokerName"));
                workers.setIdCode(a.getString("idCard"));
                workers.setEmpSex("0".equals(a.getString("gender")) ? "男" : "女");
                workers.setEmpNation(a.getString("nation").replaceAll("族", ""));
                workers.setDateOfBirth(a.getString("birthday"));
                workers.setIdAddress(a.getString("address"));
                workers.setEmpPhon(a.getString("cellPhone"));
                workers.setJobName(a.getString("workTypeCode"));
                workers.setIdAgency(a.getString("grantOrg"));
                workers.setEnterAndRetreatCondition(a.getInteger("status"));
                workers.setEmpCategory("未配置".equals(a.getString("workTypeCode")) || "".equals(a.getString("workTypeCode")) ? "03" : "00");
                String photo = a.getString("photo");
                MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(photo);
                String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", a.getString("idCard") + System.currentTimeMillis() + ".jpg");
                String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
                workers.setFaceUrl(name);
                hjProjectWorkersService.updateHjProjectWorkers(workers);
                String status;
                //若状态一致，则修改更新，若不一致，0，新增，1，删除
                if(status1.equals(status2)){
                    status="0";
                }else{
                    if("0".equals(status1)){
                        status="0";
                    }else{
                        status="2";
                    }
                }
                for(int j=0;j<hdList.size();j++){
                    hdp=new HjDeviceProjectworkers();
                    hdp.setProjectWorkersId(workers.getId());
                    hdp.setDeviceNo(hdList.get(j).getDeviceNo());
                    hdp.setStatus(status);

                        hjDeviceProjectworkersService.updateHjDeviceProjectworkersTwo(hdp);

                }


            }else {
                workers=new HjProjectWorkers();
                workers.setProjectId(pid);
                workers.setEmpName(a.getString("wokerName"));
                workers.setIdCode(a.getString("idCard"));
                workers.setEmpSex("0".equals(a.getString("gender")) ? "男" : "女");
                workers.setEmpNation(a.getString("nation").replaceAll("族", ""));
                workers.setDateOfBirth(a.getString("birthday"));
                workers.setIdAddress(a.getString("address"));
                workers.setEmpPhon(a.getString("cellPhone"));
                workers.setJobName(a.getString("workTypeCode"));
                workers.setIdAgency(a.getString("grantOrg"));
                workers.setEnterAndRetreatCondition(a.getInteger("status"));
                workers.setEmpCategory("未配置".equals(a.getString("workTypeCode")) || "".equals(a.getString("workTypeCode")) ? "03" : "00");
                String photo = a.getString("photo");
                MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(photo);
                String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", a.getString("idCard") + System.currentTimeMillis() + ".jpg");
                String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
                workers.setFaceUrl(name);

                int i = hjProjectWorkersService.insertHjProjectWorkers(workers);
                if(i>0){
                    for(int j=0;j<hdList.size();j++){
                        hdp=new HjDeviceProjectworkers();
                        hdp.setProjectWorkersId(workers.getId());
                        hdp.setDeviceNo(hdList.get(j).getDeviceNo());
                        hdp.setStatus("0");
                        hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hdp);
                    }
            }

            }


        }
    }
    /**
     * 获取人员数据
     */
    public String getResult(JSONObject json,String url,HjSynchronizationInformation hs,int page)throws Exception{
        String systemTime=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String signature=JiAnShiUtil.getSignature(hs.getEngineeringCode(),hs.getProjectNumber(),systemTime,hs.getApiSecret());

        json.put("page",page);
        json.put("systemTime",systemTime);
        json.put("signature",signature);
        String result=  ZCAPIClientTwo.httpPostWithJSON(url,json);
        return result;
    }

}
