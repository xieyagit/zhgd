import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.hujiang.ProviderApplication;
import com.hujiang.common.utils.AliOcrUtil;
import com.hujiang.project.api.redis.RedisDao;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.domain.HjProjectPersonnelSynchronization;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.service.IHjProjectPersonnelSynchronizationService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.ProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.project.zhgd.moredian.moredianPerson.domain.MoredianPerson;
import com.hujiang.project.zhgd.moredian.moredianPerson.service.IMoredianPersonService;
import com.hujiang.project.zhgd.utils.*;
import org.apache.shiro.web.session.HttpServletSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static com.hujiang.project.zhgd.utils.Util.aipFace;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-16 18:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ProviderApplication.class, ProviderTest.class })
public class ProviderTest {
    @Resource
    private HttpSession session;
    @Resource
    private MoredianClient moredianClient;
    @Autowired
    private IMoredianPersonService personService;
    @Autowired
    private IHjProjectWorkersService workersService;
    @Autowired
    private IHjProjectWorkersService projectWorkersService;
    @Autowired
    private IHjConstructionCompanyService constructionCompanyService;
    @Autowired
    private IHjTeamService teamService;
    @Autowired
    private APIClient apiClient;
    @Autowired
    private IHjProjectPersonnelSynchronizationService projectPersonnelSynchronizationService;

    /**
     * 退场
     * @throws Exception
     */
    @Test
    public void TC()throws Exception{
        HjProjectPersonnelSynchronization synchronization = new HjProjectPersonnelSynchronization();
        synchronization.setProjectId(26);
        List<HjProjectPersonnelSynchronization> hjProjectPersonnelSynchronizations = projectPersonnelSynchronizationService.selectHjProjectPersonnelSynchronizationList(synchronization);
        for(HjProjectPersonnelSynchronization projectPersonnelSynchronization:hjProjectPersonnelSynchronizations){
            HjProjectWorkers projectWorkers = projectWorkersService.selectHjProjectWorkersById(projectPersonnelSynchronization.getProjectWorkerId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Project_ID","44030120190627010");// 同步编号
            JSONArray jsonArray = new JSONArray();
            JSONObject data = new JSONObject();
            data.put("id_code",projectWorkers.getIdCode());
            jsonArray.add(data);
            jsonObject.put("userLeaveProject_list", jsonArray);
            String url = getUrl("3579B98E8BDD4E2CA14C7EBE69E0619A","364DEE9228234A2E90FBA7A690A5F16B","1.1","PLCC70D22774BA4C2B9F11C885946BD5C8",jsonObject.toString(),Constants.HJ_FORMALHOST + "userLeaveProject");
            String result =  apiClient.httpPostWithJSON(url,jsonObject);
            org.json.JSONObject jsonResult = new org.json.JSONObject(result);
            System.out.println(jsonResult);
        }


    }

    /**
     * 考勤
     * @throws Exception
     */
    @Test
    public void kq()throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Project_ID","44030120190627010");                                          // 同步编号
        jsonObject.put("Device_ID" , "PLCC70D22774BA4C2B9F11C885946BD5C8");                                          // 门禁设备序列号

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonData = new JSONObject();

        jsonData.put("person_type" , "1");                                                                    // 人员类型。0—临时人员（访客），1—员工
        jsonData.put("person_id" , "430511199701124514");                                          // 人员编号（上报人员实名制信息接口返回的员工编号）或身份证号
        jsonData.put("person_name" ,"刘勇");                                           // 人员姓名
        jsonData.put("passed_time" , "2019-07-02 16:59:11"); // 通过时间 ”yyyy-MM-dd hh:mm:ss”
        jsonData.put("direction" , "in");                                                                // 通行方向  in—进，out—出
        jsonData.put("way" , "1");                                                                            // 通行方式 1—人脸识别，2—虹膜识别，3—指纹识别，4—掌形识别，5—身份证识别，6—实名卡，7—异常清退（适用于人员没有通过闸机系统出工地而导致人员状态不一致的情况），8—一键开闸(需要与闸机交互)， 9—应急通道（不需要与闸机交互），10—二维码识别，11-其他方式
        // 可以为空
        jsonData.put("site_photo", "");                                                                       // 工地人脸照片数据，Base64编码，图像底部带过闸时间水印，黑底白字
        jsonData.put("longitude", "");                                                                        // 经度
        jsonData.put("latitude", "");                                                                         // 纬度
        jsonData.put("address", "");                                                                          // 位置（打考勤时所在的详细地址）
        jsonArray.add(jsonData);
        jsonObject.put("passedlog_list" , jsonArray);                                                         //通行日志数组
        String url = getUrl("3579B98E8BDD4E2CA14C7EBE69E0619A","364DEE9228234A2E90FBA7A690A5F16B","1.1","PLCC70D22774BA4C2B9F11C885946BD5C8",jsonObject.toString(),Constants.HJ_FORMALHOST + "UploadPassedLog");
        String result =  APIClient.httpPostWithJSON(url,jsonObject);
        org.json.JSONObject jsonResult = new org.json.JSONObject(result);
        System.out.println(jsonResult);
    }

    /**
     * 进场
     * @throws Exception
     */
    @Test
    public void jc()throws Exception{
        HjProjectWorkers projectWorkers = new HjProjectWorkers();
        projectWorkers.setProjectId(25);
        projectWorkers.setEnterAndRetreatCondition(0);
        List<HjProjectWorkers> workers = projectWorkersService.selectHjProjectWorkersList(projectWorkers);
//        for(HjProjectWorkers hjProjectWorkers:workers){
//            apiClient.insertHjProjectPersonnelSynchronizations(hjProjectWorkers.getId(),21,21);
//        }
        for(HjProjectWorkers hjProjectWorkers:workers){
            String empNaticeplaceBase64 = AliOcrUtil.getStrImgBase64(new URL(hjProjectWorkers.getEmpNaticeplace()));           // 身份证人脸照片
            String faceUrlBase64 = AliOcrUtil.getStrImgBase64(new URL(hjProjectWorkers.getFaceUrl()));                         // 工地采集人脸照片
            String idPhotoScanBase64 = AliOcrUtil.getStrImgBase64(new URL(hjProjectWorkers.getIdphotoScan()));                 // 身份证正面
            String idPhotoScan2Base64 = AliOcrUtil.getStrImgBase64(new URL(hjProjectWorkers.getIdphotoScan2()));               // 身份证反面
            // 参建单位
            HjConstructionCompany hjConstructionCompany = constructionCompanyService.selectHjConstructionCompanyById(hjProjectWorkers.getConstructionId());
            // 班组
            HjTeam hjTeam = teamService.selectHjTeamById(hjProjectWorkers.getWorkTypenameId());
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


            jsonObject.put("Project_ID" ,"44030120190627007");   // 同步编号
            //住建局  布吉
//            String url = getUrl("3579B98E8BDD4E2CA14C7EBE69E0619A","364DEE9228234A2E90FBA7A690A5F16B","1.1","PLCC70D22774BA4C2B9F11C885946BD5C8",jsonObject.toString(),Constants.HJ_FORMALHOST + "RegisterEmployee");
           //吉华
//            String url = getUrl("EDBFF0486BEE40FD826847797AE718A7","8C3BCD61547D40E1A95E06C15DA48FC1","1.1","PLF84F70472141487FA23BD72540EB95A8",jsonObject.toString(),Constants.HJ_FORMALHOST + "RegisterEmployee");
            //南湾
//            String url = getUrl("44E8BE2D2CC64223A2B2E5FE5C8D4685","0E35961F891543A283F1E046EE86E09A","1.1","PLB601510F1FB243D1AABDDBD5821C4635",jsonObject.toString(),Constants.HJ_FORMALHOST + "RegisterEmployee");
            //平湖
            String url = getUrl("6294074711384574BF836D54C47310EC","88C35D37C2BE4B019833AAE6B434321E","1.1","PL74CE767E37E642619662EA1CD5FC16F5",jsonObject.toString(),Constants.HJ_FORMALHOST + "RegisterEmployee");
            String result =  APIClient.httpPostWithJSON(url,jsonObject);
            org.json.JSONObject jsonResult = new org.json.JSONObject(result);
            if(jsonResult.getString("result").equals("true")) {
                //添加平台同步记录
                Integer number = apiClient.insertHjProjectPersonnelSynchronizations(hjProjectWorkers.getId(),hjProjectWorkers.getProjectId(),24);

            }

        }

    }

    public static AipFace AIPFACE = new AipFace("15080587","5p23WU4A6lSwn85lZNoEywwT","AbX5kooSBPqhnrFBf1oF2Gqt7RYhZAdx");

    /**
     * 同步项目在场人员进魔点考勤机
     * @throws Exception
     */
    @Test
    public void test1()throws Exception{
//        HjProjectWorkers workers = new HjProjectWorkers();
//        workers.setProjectId(21);//项目id
//        workers.setEnterAndRetreatCondition(0);//在场人员
//        List<HjProjectWorkers> hjProjectWorkers = workersService.selectHjProjectWorkersList(workers);
        //查询不再魔点设备的项目在场人员
        List<HjProjectWorkers> hjProjectWorkers = workersService.selectHjProjectWorkersNotMoreDianByprojectId(21);
        int i = 0;
        for(HjProjectWorkers projectWorkers:hjProjectWorkers){
            boolean b = moredianClient.enteringMoredianPerson(projectWorkers, 1);
            System.out.println(projectWorkers.getEmpName()+b);
            if(b){
                i++;
            }
        }
        System.out.println(i);

    }
//    @Test
    public void test()throws Exception{

//        if(session.getAttribute("aipFace")==null){
//            System.out.println("===================");
//            session.setAttribute("aipFace",new AipFace(Constants.BD_APP_ID, Constants.BD_API_KEY, Constants.BD_SECRET_KEY));
//        }
        System.out.println(1);
        for(int i = 0 ;i<5000;i++) {
//            String auth = getAuth(Constants.BD_API_KEY, Constants.BD_SECRET_KEY);
//            System.out.println(auth);
//        }
            AipFace aipFace = new AipFace(Constants.BD_APP_ID, Constants.BD_API_KEY, Constants.BD_SECRET_KEY);
//            AipFace aipFace =AIPFACE;

            //通过用户组id与考勤人脸图片搜索人脸库
            org.json.JSONObject resultObject = aipFace.search("https://hujiang.oss-cn-shenzhen.aliyuncs.com/in/c99s4w104156.jpg", "URL", "16ae7a5aced", new HashMap<>());
            if (resultObject.getString("error_msg").equals("SUCCESS")) {
                org.json.JSONArray jsonArray = resultObject.getJSONObject("result").getJSONArray("user_list");
                for (int s = 0; s < jsonArray.length(); s++) {
                    if (jsonArray.getJSONObject(s).getInt("score") > 70) {                  //人脸识别数据大于设置数据，考勤成功
                        String userId = jsonArray.getJSONObject(s).getString("user_id");   //获取匹配人脸的员工
                        System.out.println(i + "***********************" + userId);
                    }
                }
            }
        }
//        HjDictionaries dictionaries = new HjDictionaries();
//        JSONObject jsonParam = new JSONObject();
//        //住建
////        String url =getUrl("563AD9A8064640D8B8D7E65D817B9B12","7497BEEACCCA403FAF33FFFC568D12D0","1.1","PL7188D036CA5443CE95FD2350B7CE66B6",jsonParam.toString(), "http://ticwrapi.thit.com.cn/CWRService/DictListJobName");
////        //市政
//        String url =getUrl("6E36174385994900A4BB388D4F5DC6F0","BABDBBE02A724067A91F6B18235C8C2A","1.1","PL663F35F9B856427F992E597DE90CD5A4",jsonParam.toString(), "http://113.105.121.93:9090/CWRService/DictListJobName");
////        //工务署
//////        String url =getUrl("715C77EDC1A14BD5AFD44BB291C4BA20","2EA008880227498FB5ED749A0A57AAF2","1.0","PLF67D62E247F044038727A2F9059944F7",jsonParam.toString(), "http://szwb.sz.gov.cn:2019/CWRService/DictListEmpCategory");
////
//        String s1 = Util.httpPostWithJSON(url,jsonParam);
////        System.out.println(s1);
//        JSONObject object1 = JSONObject.parseObject(JSONObject.parseObject(s1).getString("result_data"));
//        JSONArray object = JSONArray.parseArray(object1.getString("dict_list"));
////        HjDictionaries dictionaries = new HjDictionaries();
//        for(int i=0;i<object.size();i++){
//            JSONObject jsonObject = JSONObject.parseObject(object.get(i).toString());
//////            dictionaries.setCategory("EMP_TYPR");
//////            dictionaries.setGroupTitle("人员类型");
//////            dictionaries.setTitle(jsonObject.getString("value"));
//////            dictionaries.setTag(jsonObject.getString("orders"));
//////            int i1 = hjDictionariesService.insertHjDictionaries(dictionaries);
//////            System.out.println(i1);
//            System.out.println(jsonObject);
//        }
//
//
//
//
////        for(int i=0;i<object.size();i++){
////            JSONObject jsonObject = JSONObject.parseObject(object.get(i).toString());
////            dictionaries.setCategory("BANK");
////            dictionaries.setGroupTitle("银行");
////            dictionaries.setTitle(jsonObject.getString("text"));
////            dictionaries.setTag(jsonObject.getString("value"));
////            int i1 = hjDictionariesService.insertHjDictionaries(dictionaries);
////////            System.out.println(i1);
////            System.out.println(jsonObject);
////        }
    }

    public  String getUrl(String api_secret, String api_Key,
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

}
