package com.hujiang.project.jianshishimingzhi;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/jianshi")
public class JiAnShiApi {

    @Autowired
    private IHjSynchronizationInformationService synchronizationInformationService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private IHjDeviceProjectworkersService hjDeviceProjectworkersService;
    @PostMapping(value = "/projectWorker")
    public void projectWorker(Integer pid)throws  Exception{
        HjSynchronizationInformation h=new HjSynchronizationInformation();
        h.setProjectId(pid);
        h.setState(1);
        h.setPlatformName("JIANSHI");
        h.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=synchronizationInformationService.selectHjSynchronizationInformationList(h);
        if(hsList.size()>0){
            HjSynchronizationInformation hs=hsList.get(0);
            String url= Constants.JIANSHI_SHIMINGZHI+"/attendance.rest/projectWorker";
            HjAttendanceDevice hd=new HjAttendanceDevice();
            hd.setProjectId(pid);
            hd.setStatus("1");
            List<HjAttendanceDevice> hdList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(hd);
            JSONObject json=new JSONObject();
            json.put("projectCode",hs.getProjectNumber());
            json.put("keyId",hs.getClientSerial());
            json.put("projectKeyId",hs.getApiKey());

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
    }

    /**
     * 保存人员
     * @param content
     */
    public void setProjectWorker(JSONArray content,Integer pid,List<HjAttendanceDevice> hdList)throws  Exception{
        HjProjectWorkers workers;
        JSONObject a;
        HjDeviceProjectworkers hdp;
        for (Object o : content) {
            workers = new HjProjectWorkers();
            a = JSONObject.parseObject(o.toString());
            workers.setProjectId(pid);
            workers.setEmpName(a.getString("wokerName"));
            workers.setIdCode(a.getString("idCard"));
            workers.setEmpSex("0".equals(a.getString("gender"))?"男":"女");
            workers.setEmpNation(a.getString("nation").replaceAll("族",""));
            workers.setDateOfBirth(a.getString("birthday"));
            workers.setIdAddress(a.getString("address"));
            workers.setEmpPhon(a.getString("cellPhone"));
            workers.setJobName(a.getString("workTypeCode"));
            workers.setIdAgency(a.getString("grantOrg"));
            workers.setEnterAndRetreatCondition(a.getInteger("status"));
            workers.setEmpCategory("未配置".equals(a.getString("workTypeCode"))||"".equals(a.getString("workTypeCode"))?"03":"00");
            String photo=a.getString("photo");
            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(photo);
            String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", a.getString("idCard")+ System.currentTimeMillis() + ".jpg");
            String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
            workers.setFaceUrl(name);
            int i=hjProjectWorkersService.insertHjProjectWorkers(workers);
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
