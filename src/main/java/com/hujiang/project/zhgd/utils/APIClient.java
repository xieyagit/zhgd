package com.hujiang.project.zhgd.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.hujiang.common.utils.AliOcrUtil;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.mapper.HjConstructionCompanyMapper;
import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import com.hujiang.project.zhgd.hjLogging.mapper.HjLoggingMapper;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.mapper.HjProjectMapper;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.domain.HjProjectPersonnelSynchronization;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.mapper.HjProjectPersonnelSynchronizationMapper;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.mapper.HjProjectWorkersMapper;
import com.hujiang.project.zhgd.hjProjectWorkers.service.HjProjectWorkersServiceImpl;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.mapper.HjSynchronizationInformationMapper;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.mapper.HjTeamMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class APIClient {


    @Autowired
    private HjConstructionCompanyMapper hjConstructionCompanyMapper;
    @Autowired
    private HjTeamMapper hjTeamMapper;
    @Autowired
    private HjSynchronizationInformationMapper hjSynchronizationInformationMapper;
    @Autowired
    private HjProjectPersonnelSynchronizationMapper hjProjectPersonnelSynchronizationMapper;
    @Autowired
    private HjProjectWorkersMapper hjProjectWorkersMapper;
    @Autowired
    private HjProjectMapper hjProjectMapper;
    @Resource
    HjProjectWorkersServiceImpl h;
    @Autowired
    private HjLoggingMapper hjLoggingMapper;

    private Logger logger = LoggerFactory.getLogger(APIClient.class);


    /**
     * 退场
     * @param hjProjectWorkers 参数
     * @param
     * @return
     */
    public Boolean deleteUserLeaveProject(HjProjectWorkers hjProjectWorkers) throws Exception {

        List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(hjProjectWorkers.getProjectId());  // 项目密钥
        Integer z = null;
        Integer s = null;
        Integer g = null;
        Integer d = null;
        for(int i=0;i<list.size();i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Project_ID",list.get(i).getProjectNumber());// 同步编号
            JSONArray jsonArray = new JSONArray();
            JSONObject data = new JSONObject();
            data.put("id_code",hjProjectWorkers.getIdCode());
            jsonArray.add(data);
            jsonObject.put("userLeaveProject_list", jsonArray);

            List<HjProjectPersonnelSynchronization> hjProjectPersonnelSynchronizations = this.selectHjProjectPersonnelSynchronizations(hjProjectWorkers.getId(),hjProjectWorkers.getProjectId(),list.get(i).getId());

            if(list.get(i).getPlatformName().equals("HOUS")){  // 住建局

                if(hjProjectPersonnelSynchronizations != null && hjProjectPersonnelSynchronizations.size() > 0){
                    String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.HJ_FORMALHOST + "userLeaveProject");
                    String result =  httpPostWithJSON(url,jsonObject);
                    org.json.JSONObject jsonResult = new org.json.JSONObject(result);
                    logger.info("住建局"+jsonResult);

                    if(jsonResult.getString("result").equals("true")) {
                      Integer number = hjProjectPersonnelSynchronizationMapper.deleteHjProjectPersonnelSynchronizationById(hjProjectPersonnelSynchronizations.get(0).getId());
                      if(number == 1){
                          z = null;
                      }else {
                          z = 1;
                      }
                    }else {
                        Integer num = this.insertLog("HOUS",hjProjectWorkers,jsonResult,2,"");
                        z = 1;
                    }
                }
            }else if(list.get(i).getPlatformName().equals("MUNICIPAL")){ // 市政总

                if(hjProjectPersonnelSynchronizations != null && hjProjectPersonnelSynchronizations.size() > 0){
                    String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.ZHGD_FORMALHOST+ "userLeaveProject");
                    String resultOnt = httpPostWithJSON(url, jsonObject);
                    org.json.JSONObject jsonResult = new org.json.JSONObject(resultOnt);
                    logger.info("市政总"+jsonResult);

                    if(jsonResult.getString("result").equals("true")) {
                        Integer number = hjProjectPersonnelSynchronizationMapper.deleteHjProjectPersonnelSynchronizationById(hjProjectPersonnelSynchronizations.get(0).getId());
                        if(number == 1){
                            s = null;
                        }else {
                            s = 1;
                        }
                    }else {
                        Integer num = this.insertLog("MUNICIPAL",hjProjectWorkers,jsonResult,2,"");
                        s = 1;
                    }
                }
            }else if(list.get(i).getPlatformName().equals("WORKSBUREAU")){ // 工务署

                if(hjProjectPersonnelSynchronizations != null && hjProjectPersonnelSynchronizations.size() > 0){
                    String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.ZC_FORMALHOST+ "userLeaveProject");
                    String resultTwo = httpPostWithJSON(url, jsonObject);
                    org.json.JSONObject jsonResult = new org.json.JSONObject(resultTwo);
                    logger.info("工务署"+jsonResult);

                    if(jsonResult.getString("result").equals("true")) {
                        Integer number = hjProjectPersonnelSynchronizationMapper.deleteHjProjectPersonnelSynchronizationById(hjProjectPersonnelSynchronizations.get(0).getId());
                        if(number == 1){
                            g = null;
                        }else {
                            g = 1;
                        }
                    }else {
                        Integer num = this.insertLog("WORKSBUREAU",hjProjectWorkers,jsonResult,2,"");
                        g = 1;
                    }
                }
            }else if(list.get(i).getPlatformName().equals("DGHOUS")){ // 东莞住建局

                if(hjProjectPersonnelSynchronizations != null && hjProjectPersonnelSynchronizations.size() > 0){

                    JSONObject body=new JSONObject();
                    JSONObject body2=new JSONObject();
                    JSONArray body3 =new JSONArray();
                    HjProject hjProject=hjProjectMapper.selectHjProjectById(hjProjectWorkers.getProjectId());
                    body2.put("ItemId",hjProject.getItemId());
                    body2.put("RosterWokerId",hjProjectWorkers.getRosterWokerId());
                    body2.put("OutDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    body2.put("IsSettle","1");
                    body2.put("IsIn","1");
                    body3.add(body2);
                    body.put("Data",body3);
                    String url = getUrlDG(list.get(i).getApiKey(),body.toString(),Constants.DG_HOUS+"/UploadLeaveOffice");
                    String resultTwo = httpPostWithJSONDG(url, body);
                    org.json.JSONObject jsonResult = new org.json.JSONObject(resultTwo);
                    logger.info("东莞住建局："+hjProjectWorkers.getId()+"人员退场"+jsonResult);

                    if("1".equals(jsonResult.getString("StateCode"))) {
                        Integer number = hjProjectPersonnelSynchronizationMapper.deleteHjProjectPersonnelSynchronizationById(hjProjectPersonnelSynchronizations.get(0).getId());
                        if(number == 1){
                            d = null;
                        }else {
                            d = 1;
                        }
                    }else {
                        Integer num = this.insertLogDG("DGHOUS",hjProjectWorkers,jsonResult,2,"");
                        d = 1;
                    }
                }
            }
        }
        if(z == null && s == null && g == null && d == null){

            // 修改项目人员状态
            HjProjectWorkers hjProjectWorkersOnt = new HjProjectWorkers();
            hjProjectWorkersOnt.setId(hjProjectWorkers.getId());
            hjProjectWorkersOnt.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            hjProjectWorkersOnt.setEnterAndRetreatCondition(1);
            Integer number = hjProjectWorkersMapper.updateHjProjectWorkers(hjProjectWorkersOnt);
            if(number == 1){
                // 连接百度人脸库
                AipFace aipFace = new AipFace(Constants.BD_APP_ID, Constants.BD_API_KEY, Constants.BD_SECRET_KEY);
                // 找到人脸库组名
                HjProject hjProject = hjProjectMapper.selectHjProjectById(hjProjectWorkers.getProjectId());
                //退场成功，删除人脸库图片
//                org.json.JSONObject jsonObject1 = aipFace.faceDelete(hjProjectWorkers.getId() + "", hjProject.getFaceGroup(), hjProjectWorkers.getFaceToken(), new HashMap<>());
                org.json.JSONObject jsonObject1 = aipFace.deleteUser( hjProject.getFaceGroup(),hjProjectWorkers.getId().toString(),  new HashMap<>());
                if(jsonObject1.getString("error_msg").equals("SUCCESS")) {
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
        return false;
    }


    /**
     * 实名制上传
     * @param hjProjectWorkers 参数
     * @param
     * @return
     */
    public Boolean registerEmployeeTest(HjProjectWorkers hjProjectWorkers) throws IOException, URISyntaxException {

        String empNaticeplaceBase64 = AliOcrUtil.getStrImgBase64(new URL(hjProjectWorkers.getEmpNaticeplace()));           // 身份证人脸照片
        String faceUrlBase64 = AliOcrUtil.getStrImgBase64(new URL(hjProjectWorkers.getFaceUrl()));                         // 工地采集人脸照片
        String idPhotoScanBase64 = AliOcrUtil.getStrImgBase64(new URL(hjProjectWorkers.getIdphotoScan()));                 // 身份证正面
        String idPhotoScan2Base64 = AliOcrUtil.getStrImgBase64(new URL(hjProjectWorkers.getIdphotoScan2()));               // 身份证反面
        HjProject hjProject = hjProjectMapper.selectHjProjectById(hjProjectWorkers.getProjectId());
        // 参建单位
        HjConstructionCompany hjConstructionCompany = hjConstructionCompanyMapper.selectHjConstructionCompanyById(hjProjectWorkers.getConstructionId());
        // 班组
        HjTeam hjTeam = hjTeamMapper.selectHjTeamById(hjProjectWorkers.getWorkTypenameId());
//        退场时间  默认两年后
        String endTime = (Integer.parseInt(hjProjectWorkers.getStartTime().substring(0, 4)) + 2) + "-" + hjProjectWorkers.getStartTime().substring(5, hjProjectWorkers.getStartTime().length());
        //封装body参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id_code" , hjProjectWorkers.getIdCode());                     // 身份证号
        jsonObject.put("id_photo" , empNaticeplaceBase64);                            // 身份证照片
        jsonObject.put("emp_name" , hjProjectWorkers.getEmpName());                   // 员工姓名
        jsonObject.put("emp_phone" , hjProjectWorkers.getEmpPhon());                  // 员工手机号码
        jsonObject.put("emp_nativeplace" , hjProjectWorkers.getIdAddress());          //身份证地址
        jsonObject.put("emp_nation" , hjProjectWorkers.getEmpNation());               //民族
        jsonObject.put("pass_period" , hjProjectWorkers.getStartTime()+":"+endTime);         // 通行时间段
        jsonObject.put("match_flag" , "Y");                                           //匹配标识。’ Y’—人证匹配，’
        jsonObject.put("facephoto" , faceUrlBase64);                                  //人脸图片
        jsonObject.put("emp_company" , hjConstructionCompany.getConstructionName());  //所属单位
        jsonObject.put("work_typename" , hjTeam.getTeamName());                       //班组名称
        jsonObject.put("emp_category" , hjProjectWorkers.getEmpCategory());           //人员类型
        jsonObject.put("cwr_iskeypsn" , hjProjectWorkers.getCwrIskeypsn());           //重要人员。"1"--是，"0"--不是
        jsonObject.put("job_dept" , "");                                              //现工作部门=========
        jsonObject.put("emp_dept" , "");                                              //所属部门(人员所在单位下的部门名称)。
        jsonObject.put("job_typename" , hjProjectWorkers.getJobTypename());           //人员类别
        jsonObject.put("job_name" , hjProjectWorkers.getJobName());                   //工种
        jsonObject.put("contract_status" , "1");                                      //合同办理。"1"—是，"0"—否
        jsonObject.put("id_agency" , hjProjectWorkers.getIdAgency());                 //身份证签发机关。
        jsonObject.put("id_validdate" ,hjProjectWorkers.getIdValiddate());            //身份证有效期限。
        jsonObject.put("emp_bankname" , "");                                         //开户行=========
        jsonObject.put("emp_cardnum" ,"");                                           //卡号=========
        jsonObject.put("idphoto_scan" , idPhotoScanBase64);                          //身份证扫描件正面，Base64编码
        jsonObject.put("idphoto_scan2" , idPhotoScan2Base64);                        //身份证扫描件反面，Base64编码

        List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(hjProjectWorkers.getProjectId());  // 项目密钥
              Integer z = null;
              Integer s = null;
              Integer g = null;
              Integer d = null;

                for(int i=0;i<list.size();i++){
                    List<HjProjectPersonnelSynchronization> hjProjectPersonnelSynchronizations = this.selectHjProjectPersonnelSynchronizations(hjProjectWorkers.getId(),hjProjectWorkers.getProjectId(),list.get(i).getId());
                    System.out.println(list.get(i).getPlatformName()+"--------------");
                    if(list.get(i).getPlatformName().equals("HOUS")){  // 住建局
                        jsonObject.put("Project_ID" ,list.get(i).getProjectNumber());   // 同步编号
                        if(null == hjProjectPersonnelSynchronizations || hjProjectPersonnelSynchronizations.size() ==0){
                            //住建局
                            String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.HJ_FORMALHOST + "RegisterEmployee");
                            String result =  httpPostWithJSON(url,jsonObject);
                            org.json.JSONObject jsonResult = new org.json.JSONObject(result);
                            logger.info("住建局"+jsonResult);

                            if(jsonResult.getString("result").equals("true")) {
                                Integer number = this.insertHjProjectPersonnelSynchronizations(hjProjectWorkers.getId(),hjProjectWorkers.getProjectId(),list.get(i).getId());
                                if(number == 1){
                                    z = null;
                                }else {
                                    z = 1;
                                }
                            }else {
                                Integer num = this.insertLog("HOUS",hjProjectWorkers,jsonResult,1,"");
                                z = 1;
                            }
                        }
                    }else if(list.get(i).getPlatformName().equals("MUNICIPAL")){ // 市政总
                        jsonObject.put("Project_ID" ,list.get(i).getProjectNumber());   // 同步编号
                        System.out.println(hjProjectPersonnelSynchronizations);
                        if(null == hjProjectPersonnelSynchronizations || hjProjectPersonnelSynchronizations.size() ==0){
                            String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.ZHGD_FORMALHOST+ "RegisterEmployee");
                            String resultOnt = httpPostWithJSON(url, jsonObject);
                            org.json.JSONObject jsonResult = new org.json.JSONObject(resultOnt);
                            logger.info("市政总"+jsonResult);

                            if(jsonResult.getString("result").equals("true")) {
                                Integer number = this.insertHjProjectPersonnelSynchronizations(hjProjectWorkers.getId(),hjProjectWorkers.getProjectId(),list.get(i).getId());
                                if(number == 1){
                                    s = null;
                                }else {
                                    s = 1;
                                }
                            }else {
                                Integer num = this.insertLog("MUNICIPAL",hjProjectWorkers,jsonResult,1,"");
                                s = 1;
                            }
                        }
                    }else if(list.get(i).getPlatformName().equals("WORKSBUREAU")){ // 工务署
                        jsonObject.put("Project_ID" ,list.get(i).getProjectNumber());   // 同步编号
                        if(null == hjProjectPersonnelSynchronizations || hjProjectPersonnelSynchronizations.size() ==0){
                            String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.ZC_FORMALHOST+ "RegisterEmployee");
                            String resultTwo = httpPostWithJSON(url, jsonObject);
                            org.json.JSONObject jsonResult = new org.json.JSONObject(resultTwo);
                            logger.info("工务署"+jsonResult);

                            if(jsonResult.getString("result").equals("true")) {
                                Integer number = this.insertHjProjectPersonnelSynchronizations(hjProjectWorkers.getId(),hjProjectWorkers.getProjectId(),list.get(i).getId());
                                if(number == 1){
                                    g = null;
                                }else {
                                    g = 1;
                                }
                            }else {
                                Integer num = this.insertLog("WORKSBUREAU",hjProjectWorkers,jsonResult,1,"");
                                g = 1;
                            }
                        }
                    }else if(list.get(i).getPlatformName().equals("DGHOUS")){ // 东莞住建局

                        if(null == hjProjectPersonnelSynchronizations || hjProjectPersonnelSynchronizations.size() ==0){
                            JSONObject body=new JSONObject();
                            JSONObject body2=new JSONObject();
                            body2.put("Name",hjProjectWorkers.getEmpName());
                            body2.put("Sex","男".equals(hjProjectWorkers.getEmpSex())?"0":"1");
                            body2.put("CerfNum",hjProjectWorkers.getIdCode());
                            body2.put("TeamName", hjTeam.getTeamName());
                            body2.put("WorkerTypeId",hjProjectWorkers.getJobName());
                            body2.put("ItemId",hjProject.getItemId());
                            body2.put("InDate",hjProjectWorkers.getStartTime()+" 00:00:00");
                            body2.put("ComId",hjConstructionCompany.getComId());
                            body2.put("Photo",idPhotoScanBase64);
                            body.put("Data",body2);
                            System.out.println(body2.toString());
                            System.out.println(body.toString());
                            String url = getUrlDG(list.get(i).getApiKey(),body.toString(),Constants.DG_HOUS+"/UploadWorker");
                            String resultTwo = httpPostWithJSONDG(url, body);
                            org.json.JSONObject jsonResult = new org.json.JSONObject(resultTwo);
                            logger.info("东莞住建局"+jsonResult);

                            if("1".equals(jsonResult.getString("StateCode"))) {
                                HjProjectWorkers hp=new HjProjectWorkers();
                                hp.setId(hjProjectWorkers.getId());
                                hp.setRosterWokerId(JSONObject.parseObject(resultTwo).getJSONObject("ResultData").getInteger("RosterWokerId"));
                                hjProjectWorkersMapper.updateHjProjectWorkers(hp);
                                Integer number = this.insertHjProjectPersonnelSynchronizations(hjProjectWorkers.getId(),hjProjectWorkers.getProjectId(),list.get(i).getId());
                                if(number == 1){
                                    d = null;
                                }else {
                                    d = 1;
                                }
                            }else {
                                Integer num = this.insertLogDG("DGHOUS",hjProjectWorkers,jsonResult,1,"");
                                d = 1;
                            }
                        }
                    }
                }
                if(z == null && s == null && g == null&& d == null){
                    AipFace aipFace = new AipFace(Constants.BD_APP_ID, Constants.BD_API_KEY, Constants.BD_SECRET_KEY);
                    // 找到人脸库组名

                    //保存到参建单位人脸库
                    org.json.JSONObject resultObject = aipFace.addUser(hjProjectWorkers.getFaceUrl(), "URL", hjProject.getFaceGroup(), hjProjectWorkers.getId() + "", new HashMap<>());
                    logger.info("人脸库"+resultObject);
                    if (resultObject.getString("error_msg").equals("SUCCESS")) {
                        HjProjectWorkers hjProjectWorkerParam = new HjProjectWorkers();
                        hjProjectWorkerParam.setFaceToken(resultObject.getJSONObject("result").getString("face_token"));
                        hjProjectWorkerParam.setId(hjProjectWorkers.getId());
                        hjProjectWorkerParam.setEnterAndRetreatCondition(0);
                        Integer number = hjProjectWorkersMapper.updateHjProjectWorkers(hjProjectWorkerParam);
                        if(number == 1){
                            return true;
                        }else {
                            return false;
                        }
                    }else {
                        return false;
                    }
                }
                return false;
    }

    /**
     * 员工考勤
     * @param hjProjectWorkers 参数
     * @param
     * @return
     */
    public Boolean uploadPassedLogTest(HjProjectWorkers hjProjectWorkers,String direction,String time,String img) throws Exception {

        List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(hjProjectWorkers.getProjectId());  // 项目密钥
        Integer z = null;
        Integer s = null;
        Integer g = null;

        for(int i=0;i<list.size();i++){


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Project_ID",list.get(i).getProjectNumber());                                          // 同步编号
            jsonObject.put("Device_ID" , list.get(i).getClientSerial());                                          // 门禁设备序列号

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonData = new JSONObject();

            jsonData.put("person_type" , "1");                                                                    // 人员类型。0—临时人员（访客），1—员工
            jsonData.put("person_id" , hjProjectWorkers.getIdCode());                                          // 人员编号（上报人员实名制信息接口返回的员工编号）或身份证号
            jsonData.put("person_name" ,hjProjectWorkers.getEmpName());                                           // 人员姓名
            jsonData.put("passed_time" , time); // 通过时间 ”yyyy-MM-dd hh:mm:ss”
            jsonData.put("direction" , direction);                                                                // 通行方向  in—进，out—出
            jsonData.put("way" , "1");                                                                            // 通行方式 1—人脸识别，2—虹膜识别，3—指纹识别，4—掌形识别，5—身份证识别，6—实名卡，7—异常清退（适用于人员没有通过闸机系统出工地而导致人员状态不一致的情况），8—一键开闸(需要与闸机交互)， 9—应急通道（不需要与闸机交互），10—二维码识别，11-其他方式
            // 可以为空
            jsonData.put("site_photo", PrintJobTo.zxToWatermark(img,time));                                                                       // 工地人脸照片数据，Base64编码，图像底部带过闸时间水印，黑底白字
            jsonData.put("longitude", "");                                                                        // 经度
            jsonData.put("latitude", "");                                                                         // 纬度
            jsonData.put("address", "");                                                                          // 位置（打考勤时所在的详细地址）
            jsonArray.add(jsonData);
            jsonObject.put("passedlog_list" , jsonArray);                                                         //通行日志数组


            if(list.get(i).getPlatformName().equals("HOUS")){  // 住建局
                String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.HJ_FORMALHOST + "UploadPassedLog");
                String result =  httpPostWithJSON(url,jsonObject);
                org.json.JSONObject jsonResult = new org.json.JSONObject(result);
                Integer num = this.insertLog("HOUS",hjProjectWorkers,jsonResult,0,direction);
                if(jsonResult.getString("result").equals("true")) {
                    z = null;
                }else {
                    z = 1;
                }
            }else if(list.get(i).getPlatformName().equals("MUNICIPAL")){ // 市政总
                String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.ZHGD_FORMALHOST+ "UploadPassedLog");
                String resultOnt = httpPostWithJSON(url, jsonObject);
                org.json.JSONObject jsonResult = new org.json.JSONObject(resultOnt);
                Integer num = this.insertLog("MUNICIPAL",hjProjectWorkers,jsonResult,0,direction);
                if(jsonResult.getString("result").equals("true")) {
                    s = null;
                }else {
                    s = 1;
                }
            }else if(list.get(i).getPlatformName().equals("WORKSBUREAU")){ // 工务署

                String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.ZC_FORMALHOST+ "UploadPassedLog");
                String resultTwo = httpPostWithJSON(url, jsonObject);
                org.json.JSONObject jsonResult = new org.json.JSONObject(resultTwo);
                Integer num = this.insertLog("WORKSBUREAU",hjProjectWorkers,jsonResult,0,direction);
                if(jsonResult.getString("result").equals("true")) {
                    g = null;
                }else {
                    g = 1;
                }
            }
        }
        if(z == null && s == null && g == null){
            return true;
        }
        return false;
    }

    /**
     * 员工考勤
     * @param hjProjectWorkers 参数
     * @param
     * @return
     */
    public Boolean uploadPassedLogTest(HjProjectWorkers hjProjectWorkers,String direction,String img) throws Exception {

        List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(hjProjectWorkers.getProjectId());  // 项目密钥
        Integer z = null;
        Integer s = null;
        Integer g = null;
        Integer d = null;

        for(int i=0;i<list.size();i++){


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Project_ID",list.get(i).getProjectNumber());                                          // 同步编号
            jsonObject.put("Device_ID" , list.get(i).getClientSerial());                                          // 门禁设备序列号

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonData = new JSONObject();

            jsonData.put("person_type" , "1");                                                                    // 人员类型。0—临时人员（访客），1—员工
            jsonData.put("person_id" , hjProjectWorkers.getIdCode());                                          // 人员编号（上报人员实名制信息接口返回的员工编号）或身份证号
            jsonData.put("person_name" ,hjProjectWorkers.getEmpName());                                           // 人员姓名
            jsonData.put("passed_time" , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // 通过时间 ”yyyy-MM-dd hh:mm:ss”
            jsonData.put("direction" , direction);                                                                // 通行方向  in—进，out—出
            jsonData.put("way" , "1");                                                                            // 通行方式 1—人脸识别，2—虹膜识别，3—指纹识别，4—掌形识别，5—身份证识别，6—实名卡，7—异常清退（适用于人员没有通过闸机系统出工地而导致人员状态不一致的情况），8—一键开闸(需要与闸机交互)， 9—应急通道（不需要与闸机交互），10—二维码识别，11-其他方式
            // 可以为空
            jsonData.put("site_photo", PrintJobTo.zxToWatermark(img,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));  // 工地人脸照片数据，Base64编码，图像底部带过闸时间水印，黑底白字
            jsonData.put("longitude", "");                                                                        // 经度
            jsonData.put("latitude", "");                                                                         // 纬度
            jsonData.put("address", "");                                                                          // 位置（打考勤时所在的详细地址）
            jsonArray.add(jsonData);
            jsonObject.put("passedlog_list" , jsonArray);                                                         //通行日志数组


            if(list.get(i).getPlatformName().equals("HOUS")){  // 住建局
                    String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.HJ_FORMALHOST + "UploadPassedLog");
                    String result =  httpPostWithJSON(url,jsonObject);
                    org.json.JSONObject jsonResult = new org.json.JSONObject(result);

                    if(jsonResult.getString("result").equals("true")) {
                        z = null;
                    }else {
                        Integer num = this.insertLog("HOUS",hjProjectWorkers,jsonResult,0,direction);
                        z = 1;
                    }
            }else if(list.get(i).getPlatformName().equals("MUNICIPAL")){ // 市政总
                 String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.ZHGD_FORMALHOST+ "UploadPassedLog");
                    String resultOnt = httpPostWithJSON(url, jsonObject);
                    org.json.JSONObject jsonResult = new org.json.JSONObject(resultOnt);

                    if(jsonResult.getString("result").equals("true")) {
                        s = null;
                    }else {
                        Integer num = this.insertLog("MUNICIPAL",hjProjectWorkers,jsonResult,0,direction);
                        s = 1;
                    }
            }else if(list.get(i).getPlatformName().equals("WORKSBUREAU")){ // 工务署

                String url = getUrl(list.get(i).getApiSecret(),list.get(i).getApiKey(),"1.1",list.get(i).getClientSerial(),jsonObject.toString(),Constants.ZC_FORMALHOST+ "UploadPassedLog");
                    String resultTwo = httpPostWithJSON(url, jsonObject);
                    org.json.JSONObject jsonResult = new org.json.JSONObject(resultTwo);

                    if(jsonResult.getString("result").equals("true")) {
                        g = null;
                    }else {
                        Integer num = this.insertLog("WORKSBUREAU",hjProjectWorkers,jsonResult,0,direction);
                        g = 1;
                    }
                }else if(list.get(i).getPlatformName().equals("DGHOUS")){  // 东莞住建局
                JSONObject body=new JSONObject();
                JSONObject body2=new JSONObject();
                JSONArray body3 =new JSONArray();
                body2.put("RosterWokerId",hjProjectWorkers.getRosterWokerId());
                body2.put("CheckDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                HjProject project=hjProjectMapper.selectHjProjectById(list.get(i).getProjectId());
                body2.put("DeviceNo",project.getId()+project.getItemId());
                body2.put("Type",6);
                body2.put("ItemId",project.getItemId());
                body3.add(body2);
                body.put("Data",body3);
                String url = getUrlDG(list.get(i).getApiKey(),body.toString(),Constants.DG_HOUS + "/UploadAttendance");
                String result =  httpPostWithJSONDG(url,body);

                org.json.JSONObject jsonResult = new org.json.JSONObject(result);
                logger.info("东莞住建局"+jsonResult);


                if("1".equals(jsonResult.getString("StateCode"))) {
                    d = null;
                }else {
                    Integer num = this.insertLogDG("DGHOUS",hjProjectWorkers,jsonResult,1,"");
                    d = 1;
                }
            }
            }
        if(z == null && s == null && g == null && d == null){
            return true;
        }
        return false;
    }


    /**
     * 是否同步过
     * @param projectWorkersId 工人id
     * @param projectId 项目id
     * @param SynchronizationInformationId 密钥id
     * @return
     */
    public List<HjProjectPersonnelSynchronization> selectHjProjectPersonnelSynchronizations(Integer projectWorkersId,Integer projectId,Integer SynchronizationInformationId){
        HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization = new HjProjectPersonnelSynchronization();
        hjProjectPersonnelSynchronization.setProjectId(projectId);
        hjProjectPersonnelSynchronization.setProjectWorkerId(projectWorkersId);
        hjProjectPersonnelSynchronization.setSynchronizationInformationId(SynchronizationInformationId);
        List<HjProjectPersonnelSynchronization> hjProjectPersonnelSynchronizations = hjProjectPersonnelSynchronizationMapper.selectHjProjectPersonnelSynchronizationList(hjProjectPersonnelSynchronization);
        return hjProjectPersonnelSynchronizations;
    }

    /**
     * 添加项目人员同步信息
     * @param projectWorkersId 工人id
     * @param projectId 项目id
     * @param SynchronizationInformationId 密钥id   HjSynchronizationInformation
     * @return
     */
    public Integer insertHjProjectPersonnelSynchronizations(Integer projectWorkersId,Integer projectId,Integer SynchronizationInformationId){
        HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization = new HjProjectPersonnelSynchronization();
        hjProjectPersonnelSynchronization.setProjectId(projectId);
        hjProjectPersonnelSynchronization.setProjectWorkerId(projectWorkersId);
        hjProjectPersonnelSynchronization.setSynchronizationInformationId(SynchronizationInformationId);
        Integer number = hjProjectPersonnelSynchronizationMapper.insertHjProjectPersonnelSynchronization(hjProjectPersonnelSynchronization);
        return number;
    }


    /**
     * 密钥
     * @param projectId 项目id
     * @return
     */
    public List<HjSynchronizationInformation> queryHjSynchronizationInformation(Integer projectId){
        HjSynchronizationInformation hjSynchronizationInformation = new HjSynchronizationInformation();
        hjSynchronizationInformation.setProjectId(projectId);
        hjSynchronizationInformation.setApiType("keytype1");
        hjSynchronizationInformation.setState(1);
        List<HjSynchronizationInformation> list = hjSynchronizationInformationMapper.selectHjSynchronizationInformationList(hjSynchronizationInformation);
        return list;
    }

    /**
     * 进退场日志
     * @param tag 对接平台
     * @param hjProjectWorkers 工人信息
     * @param object 对接平台返回值
     * @param loggingType 0-考勤 1-进场 2-退场
     * @param inOut  in-上班，out-下班
     * @param
     * @return
     */
    public Integer insertLog(String tag , HjProjectWorkers hjProjectWorkers , org.json.JSONObject object, Integer loggingType,String inOut){

        if(!"1".equals(object.getString("result"))) {
            HjLogging hjLogging = new HjLogging();
            hjLogging.setProjectId(hjProjectWorkers.getProjectId());
            hjLogging.setLoggingMessage(object.getString("detail_message"));
            hjLogging.setLoggingData(object.toString());
            if(loggingType==0){
                hjLogging.setInOut("考勤失败！");
            }else if(loggingType==1){
                hjLogging.setInOut("人员进场失败！");
            }else if(loggingType==2){
                hjLogging.setInOut("人员退场失败！");
            }
            hjLogging.setUserName(hjProjectWorkers.getEmpName());
            hjLogging.setLoggingTag(tag);
            hjLogging.setLoggingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            hjLoggingMapper.insertHjLogging(hjLogging);
//            if (!inOut.equals("")) {
//                hjLogging.setInOut(inOut);
//            }
//            hjLogging.setProjectId(hjProjectWorkers.getProjectId());                                                     // 项目id
//            hjLogging.setLoggingType(loggingType);                                                                       // 0-考勤 1-进场 2-退场
//            hjLogging.setLoggingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));            // 上传时间
//            String detailMessage = object.getString("detail_message");                                              // 成功或者失败原因
//            hjLogging.setLoggingMessage(detailMessage);
//            String result = object.getString("result");                                                             // 0-成功 1-失败
//            if (result.equals("false")) {
//                hjLogging.setLoggingResult(1);
//            } else {
//                hjLogging.setLoggingResult(0);
//            }
//            hjLogging.setLoggingData(object.toString());                                                                 // 返回值
//            hjLogging.setUserId(hjProjectWorkers.getId());                                                               // 人员id
//            hjLogging.setUserName(hjProjectWorkers.getEmpName());                                                        // 人员姓名
//            hjLogging.setLoggingTag(tag);                                                                                // 对接平台
//            Integer num = hjLoggingMapper.insertHjLogging(hjLogging);
        }
        return 1;
    }
    public Integer insertLogDG(String tag , HjProjectWorkers hjProjectWorkers , org.json.JSONObject object, Integer loggingType,String inOut){

        if("false".equals(object.getString("StateCode"))) {
            HjLogging hjLogging = new HjLogging();
            hjLogging.setProjectId(hjProjectWorkers.getProjectId());
            hjLogging.setLoggingMessage(object.getString("ErrMsg"));
            hjLogging.setLoggingData(object.toString());
            if(loggingType==0){
                hjLogging.setInOut("考勤失败！");
            }else if(loggingType==1){
                hjLogging.setInOut("人员进场失败！");
            }else if(loggingType==2){
                hjLogging.setInOut("人员退场失败！");
            }
            hjLogging.setUserName(hjProjectWorkers.getEmpName());
            hjLogging.setLoggingTag(tag);
            hjLogging.setLoggingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            hjLoggingMapper.insertHjLogging(hjLogging);
        }
        return 1;
    }






    /**
     * POST报文客户端
     *
     * @param urlString
     *            调用地址字符串
     * @param jsonParam
     *            报文实体JSON
     * @author Al1en
     */
    public static String httpPostWithJSON(String urlString, JSONObject jsonParam) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        HttpPost httpPost = new HttpPost(uri);
        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
        StringEntity entity = new StringEntity(jsonParam.toString(),
                "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + urlString );
        System.out.println(respContent);
        return respContent;
    }

    /**
     * 获取完整的服务地址
     *
     * @param api_secret     授权秘钥
     * @param api_Key        授权帐号
     * @param api_Version    API协议版本，可选值:1.0
     * @param client_Serial  第三方系统编号，“PL”+32位uuid
     * @param jsonParam
     *            报文实体JSON
     * @param server
     *            服务接口
     * @author Al1en
     */
    public static String getUrl(String api_secret, String api_Key,
                                String api_Version, String client_Serial, String jsonParam,
                                String server) {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        //String timestamp = "2017-05-19 18:17:39";
        // 由于从性能出发用StringBuilder来append数据而没有选择String，并且也没有选择使用遍历拼接。
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append(api_secret).append("api_key").append(api_Key)
                .append("api_version").append(api_Version).append("body")
                .append(jsonParam).append("client_serial")
                .append(client_Serial).append("timestamp").append(timestamp)
                .append(api_secret);
        // MD5加密报文获取signature
        String serverSignature = Tools.encodeToMD5(str4MD5.toString()).toUpperCase();
        StringBuilder url = new StringBuilder();
        url.append(server).append("?api_version=").append(api_Version)
                .append("&timestamp=").append(timestamp)
                .append("&client_serial=").append(client_Serial)
                .append("&signature=").append(serverSignature)
                .append("&api_key=").append(api_Key);
        return url.toString();

    }

    /**
     * 获取东莞住建局完整的地址
     * @param token
     * @param jsonParam
     * @param server
     * @return
     */
    public static String getUrlDG(String token, String jsonParam,
                                String server) {
        String timestamp =new SimpleDateFormat("yyMMddHHmmss").format(new Date());

        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append(token).append("body")
                .append(jsonParam).append("timestamp").append(timestamp).append("token").append(token)
                .append("version").append("1.0").append(token);
        // MD5加密报文获取signature
        String serverSignature = Tools.encodeToMD5(str4MD5.toString()).toUpperCase();
        StringBuilder url = new StringBuilder();
        url.append(server).append("?version=1.0").append("&token=").append(token)
                .append("&timestamp=").append(timestamp)
                .append("&signature=").append(serverSignature);
        return url.toString();

    }


    public static String httpPostWithJSONDG(String urlString, JSONObject jsonParam) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        HttpPost httpPost = new HttpPost(uri);
        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
        StringEntity entity = new StringEntity(jsonParam.toString(),
                "UTF-8");
        entity.setContentType("textml;charset=UTF-8");
        httpPost.setEntity(entity);
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + urlString );
        System.out.println(respContent);
        return respContent;
    }
















}
