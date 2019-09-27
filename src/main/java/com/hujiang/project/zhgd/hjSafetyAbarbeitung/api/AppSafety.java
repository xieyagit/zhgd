package com.hujiang.project.zhgd.hjSafetyAbarbeitung.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjExcessiveSafety.domain.HjExcessiveSafety;
import com.hujiang.project.zhgd.hjExcessiveSafety.service.IHjExcessiveSafetyService;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.*;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.service.IHjSafetyAbarbeitungService;
import com.hujiang.project.zhgd.hjSafetyArea.domain.HjSafetyArea;
import com.hujiang.project.zhgd.hjSafetyArea.service.IHjSafetyAreaService;
import com.hujiang.project.zhgd.hjSafetyCommission.domain.HjSafetyCommission;
import com.hujiang.project.zhgd.hjSafetyCommission.service.IHjSafetyCommissionService;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import com.hujiang.project.zhgd.jpush.api.examples.PushExample;
import com.hujiang.project.zhgd.jpush.api.push.model.PushPayload;
import com.hujiang.project.zhgd.jsms.api.JSMSExample;
import com.hujiang.project.zhgd.shortCreedNumber.domain.ShortCreedNumber;
import com.hujiang.project.zhgd.shortCreedNumber.service.IShortCreedNumberService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/provider/safetyAppApi",method = RequestMethod.POST)
public class AppSafety extends BaseController {
    @Autowired
    private IHjSafetyAbarbeitungService safetyAbarbeitungService;
    @Autowired
    private IHjSystemUserService systemUserService;
    @Autowired
    private IHjSafetyAreaService safetyAreaService;
    @Autowired
    private IHjConstructionCompanyService constructionCompanyService;
    @Autowired
    private IHjExcessiveSafetyService excessiveSafetyService;
    @Autowired
    private IShortCreedNumberService shortCreedNumberService;
    @Autowired
    private IHjSafetyCommissionService safetyCommissionService;
    @Autowired
    private IHjSystemUserService hjSystemUserService;
//    @Resource
//    private PushExample pushExample;
//    private static final Map<String,String> extras = new HashMap<>();
    private static final int SPONSORTEMPID = 167990;    //发起整改模板ID
    private static final int RECTIFYID = 168031;        //整改模板ID
    private static final int REVIEWID = 167992;         //复查ID

    @PostMapping(value = "/updateAlias")
    public JSONObject updateAlias(@RequestParam(value = "userId")int userId,
                                  @RequestParam(value = "userAlias")String alias){
        JSONObject jsonObject = new JSONObject();
        int result = hjSystemUserService.updateAlias(userId, alias);
        jsonObject.put("msg",result>0 ? "修改成功":"修改失败");
        jsonObject.put("code",result>0 ? 0:-1);
        return jsonObject;
    }
    /**
     * 获取信息
     *
     * @param projectId
     * @return
     */
    @PostMapping(value = "/getSafetyList")
    public JSONObject getSafetyList(@RequestParam(value = "projectId") Integer projectId) {
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        List<Safety> constructionList = safetyAbarbeitungService.getConstructionList(projectId);  //责任分包单位
        List<Safety> hiddenList = safetyAbarbeitungService.getHiddenList(); //隐患
        List<Safety> gradeList = safetyAbarbeitungService.getGradeList();   //问题级别
        if (constructionList != null && constructionList.size() > 0) {
            JSONArray constructionArray = new JSONArray();
            for (Safety construction : constructionList) {
                JSONObject constructionMap = new JSONObject();
                constructionMap.put("constructionId", construction.getConstructionId());
                constructionMap.put("constructionName", construction.getConstructionName());
                List<Safety> areaList = safetyAbarbeitungService.getAreaList(construction.getConstructionId());     //检查区域
                if (areaList != null && areaList.size() > 0) {
                    JSONArray areaArray = new JSONArray();
                    for (Safety area : areaList) {
                        JSONObject areaMap = new JSONObject();
                        areaMap.put("areaId", area.getAreaId());
                        areaMap.put("areaName", area.getAreaName());
                        List<Safety> userList = safetyAbarbeitungService.getUserList(area.getAreaId());     //人员
                        if (userList != null && userList.size() > 0) {
                            JSONArray userArray = new JSONArray();
                            for (Safety user : userList) {
                                JSONObject userMap = new JSONObject();
                                userMap.put("userId", user.getUserId());
                                userMap.put("userName", user.getUserName());
                                userArray.add(userMap);
                            }
                            areaMap.put("userList", userArray);
                        } else {
                            areaMap.put("userList", userList);
                        }
                        areaArray.add(areaMap);
                    }
                    constructionMap.put("areaList", areaArray);
                } else {
                    constructionMap.put("areaList", areaList);
                }
                constructionArray.add(constructionMap);
            }
            result.put("constructionList", constructionArray);
        } else {
            jsonObject.put("msg", "查询失败");
            jsonObject.put("code", 0);
            jsonObject.put("data", Collections.emptyList());
        }

        if (hiddenList != null && hiddenList.size() > 0) {
            JSONArray hiddenArray = new JSONArray();
            for (Safety hidden : hiddenList) {
                JSONObject hiddenMap = new JSONObject();
                hiddenMap.put("hiddenId", hidden.getHiddenId());
                hiddenMap.put("hiddenName", hidden.getHiddenName());
                hiddenArray.add(hiddenMap);
            }
            result.put("hiddenList", hiddenArray);
        }
        if (gradeList != null && gradeList.size() > 0) {
            JSONArray gradeArray = new JSONArray();
            for (Safety grade : gradeList) {
                JSONObject gradeMap = new JSONObject();
                gradeMap.put("gradeId", grade.getGradeId());
                gradeMap.put("gradeName", grade.getGradeName());
                gradeArray.add(gradeMap);
            }
            result.put("gradeList", gradeArray);
        }
        jsonObject.put("msg", "查询成功");
        jsonObject.put("code", 0);
        jsonObject.put("data", result);
        return jsonObject;
    }

    /**
     * 安全监管--发起整改
     *
     * @param hjSafetyAbarbeitung
     * @throws IOException
     * @return
     */
    @PostMapping(value = "/sponsorSafety")
    public JSONObject sponsorSafety(@RequestBody HjSafetyAbarbeitung hjSafetyAbarbeitung) throws IOException {
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sp.format(new Date());      //获取当前时间
        HjSafetyAbarbeitung safetyAbarbeitung = new HjSafetyAbarbeitung();
        if (hjSafetyAbarbeitung.getFile()!=null && hjSafetyAbarbeitung.getFile().length>0) {
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < hjSafetyAbarbeitung.getFile().length; i++) {
                String photoName = hjSafetyAbarbeitung.getFile()[i].getOriginalFilename();
                String url = AliyunOSSClientUtil.uploadFileImg(hjSafetyAbarbeitung.getFile()[i], "zhgd_img", photoName);
                String path = url.substring(0, url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
                sb.append(path);
                sb.append(",");
            }
            String safetyPhotos = sb.toString().substring(0,sb.toString().length()-1);
            safetyAbarbeitung.setSafetyPhotos(safetyPhotos);                                        //file      必填
        }else{
            safetyAbarbeitung.setSafetyPhotos(hjSafetyAbarbeitung.getSafetyPhotos());
        }

        if(hjSafetyAbarbeitung.getMake()!= null &&hjSafetyAbarbeitung.getMake().length>0 ){
            StringBuffer sb2=new StringBuffer();
            for (int i = 0;i<hjSafetyAbarbeitung.getMake().length;i++){
                sb2.append(hjSafetyAbarbeitung.getMake()[i]);
                sb2.append(",");
            }
            String makeId = (sb2.toString().substring(0,sb2.toString().length()-1));
            safetyAbarbeitung.setMakeId(makeId);           //抄送人
        }else {
            safetyAbarbeitung.setMakeId(null);
        }
            safetyAbarbeitung.setDifferentiate(1);  //安全监管
            safetyAbarbeitung.setStatus(1);     //状态--待整改
            safetyAbarbeitung.setConstructionId(hjSafetyAbarbeitung.getConstructionId());   //责任分包公司    必填
            safetyAbarbeitung.setAreaId(hjSafetyAbarbeitung.getAreaId());                   //检查区域      必填

            safetyAbarbeitung.setHiddenId(hjSafetyAbarbeitung.getHiddenId());           //安全隐患ID   必填
            safetyAbarbeitung.setSafetyDescribe(hjSafetyAbarbeitung.getSafetyDescribe());       //问题描述 必填
            safetyAbarbeitung.setProblemGradeId(hjSafetyAbarbeitung.getProblemGradeId());   //问题级别 必填
            safetyAbarbeitung.setInitiatorId(hjSafetyAbarbeitung.getInitiatorId());     //发起整改人ID    必填 当前登录用户ID
            safetyAbarbeitung.setRectifyId(hjSafetyAbarbeitung.getRectifyId());         //整改人ID       必填
            safetyAbarbeitung.setReviewId(hjSafetyAbarbeitung.getReviewId());           //复查人ID     必填
            safetyAbarbeitung.setSafetyDeadline(hjSafetyAbarbeitung.getSafetyDeadline());   //最后整改期限 必填

            safetyAbarbeitung.setSafetyRequire(hjSafetyAbarbeitung.getSafetyRequire());       //整改备注/要求 必填
            safetyAbarbeitung.setInitiatorTime(format);     //检查时间
            int temp = safetyAbarbeitungService.sponsorSafety(safetyAbarbeitung);
            int safetyId = safetyAbarbeitung.getId();

            if (temp > 0) {
                Map<String,String> extras = new HashMap<>();
                extras.put("skip", "18");   //权限
                extras.put("append", String.valueOf(safetyId));   //巡检ID
                HjSafetyNoPass hjSafetyNoPass = new HjSafetyNoPass();
                hjSafetyNoPass.setSafetyId(safetyId);
                hjSafetyNoPass.setSafetyName("待整改第1条");
                hjSafetyNoPass.setSafetyCreateTime(format);
                int temp2 = safetyAbarbeitungService.sponsorSafetyTwo(hjSafetyNoPass);
                HjSafetyArea safetyArea = safetyAreaService.selectHjSafetyAreaById(safetyAbarbeitung.getAreaId());
                HjSystemUser rectify = systemUserService.getByAlias(safetyAbarbeitung.getRectifyId());
                HjSystemUser initiator = systemUserService.getByAlias(safetyAbarbeitung.getInitiatorId());
                Project project = safetyAbarbeitungService.getProject(safetyAbarbeitung.getConstructionId());
                String title="安全整改通知书";
                String alert ="\n"+initiator.getUserName()+"于"+format+"在"+safetyArea.getArea()
                        +"发起一条整改。";
                if(temp2 >0){
                    Map<String,String> tempPara = new HashMap<>();
                    tempPara.put("title",title);
                    tempPara.put("userName",initiator.getUserName());  //用户
                    tempPara.put("time", format);   //时间
                    tempPara.put("area",safetyArea.getArea());
                    //发起整改人
                    if(!"".equals(safetyAbarbeitung.getRectifyId())&& safetyAbarbeitung.getRectifyId() != null){
                        if(rectify.getAlias()!=null && rectify.getAlias() !="") {
                            boolean isApnsProduction = false;
                            PushExample pushExample = new PushExample();
                            //推送
                            PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                                    (alert,rectify.getAlias(),title,isApnsProduction,extras);
                            pushExample.testSendPush(payload);

                            //将消息保存--通知页面
                           HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
                            hjSafetyCommission.setInitiator(initiator.getUserName());
                            hjSafetyCommission.setRectify(rectify.getUserName());
                            hjSafetyCommission.setTitle(safetyAbarbeitung.getSafetyDescribe());
                            hjSafetyCommission.setCreateTime(format);
                            hjSafetyCommission.setStatus(1);
                            hjSafetyCommission.setProjectId(hjSafetyAbarbeitung.getProjectId());
                            hjSafetyCommission.setUserId(safetyAbarbeitung.getRectifyId());
                            hjSafetyCommission.setDifferentiate(1);
                            hjSafetyCommission.setSafetyId(safetyId);
                            safetyCommissionService.insertHjSafetyCommission(hjSafetyCommission);



                            //判断当前项目短信剩余条数是否大于0
                            ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
                            if(project!=null){
                                shortCreedNumber.setProjectId(project.getProjectId());
                                ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);
                                if(shortCreedNumberList.getNoteNumber()>0 && shortCreedNumberList.getNoteNumber() != null) {
                                //短信
                                JSMSExample.testSendTemplateSMS(rectify.getUserPhone(), SPONSORTEMPID, tempPara);
                                ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                                shortCreedNumber1.setProjectId(project.getProjectId());
                                shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber()-1);
                                shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);

                                //保存巡检短信
                                HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
                                hjExcessiveSafety.setProjectId(project.getProjectId());
                                hjExcessiveSafety.setUserId(safetyAbarbeitung.getRectifyId());
                                hjExcessiveSafety.setContent(title + ":" + alert);
                                hjExcessiveSafety.setCreateTime(format);
                                excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
                            }
                            }
                        }
                    }
                    //抄送人
                    if( hjSafetyAbarbeitung.getMake() != null && hjSafetyAbarbeitung.getMake().length>0){
                        for (int i = 0;i<hjSafetyAbarbeitung.getMake().length;i++){
                            HjSystemUser make = systemUserService.getByAlias(hjSafetyAbarbeitung.getMake()[i]);
                            if(hjSafetyAbarbeitung.getMake()[i] == hjSafetyAbarbeitung.getRectifyId() ||
                                    hjSafetyAbarbeitung.getMake()[i]  == hjSafetyAbarbeitung.getReviewId() ||
                                     hjSafetyAbarbeitung.getMake()[i] == hjSafetyAbarbeitung.getInitiatorId()
                            ){
                                continue;
                            }
                            else {
                                if(make.getAlias()!=null) {
                                    JsonObject intent = new JsonObject();
                                    intent.add("url",null);
                                    boolean isApnsProduction = false;
                                    PushExample pushExample = new PushExample();
                                    //推送
                                    PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                                            (alert,make.getAlias(),title,isApnsProduction,extras);
                                    pushExample.testSendPush(payload);

                                    //将消息保存--通知页面
                                    HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
                                    hjSafetyCommission.setInitiator(initiator.getUserName());
                                    hjSafetyCommission.setRectify(rectify.getUserName());
                                    hjSafetyCommission.setTitle(safetyAbarbeitung.getSafetyDescribe());
                                    hjSafetyCommission.setCreateTime(format);
                                    hjSafetyCommission.setStatus(1);
                                    hjSafetyCommission.setProjectId(hjSafetyAbarbeitung.getProjectId());
                                    hjSafetyCommission.setUserId(make.getId());
                                    hjSafetyCommission.setDifferentiate(1);
                                    hjSafetyCommission.setSafetyId(safetyId);
                                    safetyCommissionService.insertHjSafetyCommission(hjSafetyCommission);



                                    //判断当前项目短信剩余条数是否大于0
                                    ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
                                    if(project!=null){
                                        shortCreedNumber.setProjectId(project.getProjectId());


                                        ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);
                                        if(shortCreedNumberList.getNoteNumber()>0 && shortCreedNumberList.getNoteNumber() != null) {
                                            //短信
                                            JSMSExample.testSendTemplateSMS(make.getUserPhone(), SPONSORTEMPID, tempPara);
                                            ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                                            shortCreedNumber1.setProjectId(project.getProjectId());
                                            shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber()-1);
                                            shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);
                                            //保存巡检短信信息
                                            HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
                                            hjExcessiveSafety.setProjectId(project.getProjectId());
                                            hjExcessiveSafety.setUserId(make.getId());
                                            hjExcessiveSafety.setContent(title + ":" + alert);
                                            hjExcessiveSafety.setCreateTime(format);
                                            excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    jsonObject.put("msg", "新增成功");
                    jsonObject.put("code", 0);

                }else {
                    jsonObject.put("msg", "新增失败");
                    jsonObject.put("code", -1);
                }

            }

        return jsonObject;
    }




    /**
     * 列表
     *
     * @param projectId
     * @return
     */
    @PostMapping(value = "/getAfterRectification")
    public JSONObject getAfterRectification(@RequestParam(value = "projectId") Integer projectId,
                                            @RequestParam(value = "status") Integer status,
                                            @RequestParam(value = "differentiate")Integer differentiate,
                                            @RequestParam(value = "userId")Integer userId,
                                            PageDomain pageDomain) {
        JSONObject jsonObject = new JSONObject();
        startPage();
        List<SafetyRectification> safetyRectificationList = safetyAbarbeitungService.getAfterRectificationList(projectId, status, differentiate, userId);
        TableDataInfo dataTable = getDataTable(safetyRectificationList);
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            if (status == 1) {    //待整改
                JSONArray safetyArray = new JSONArray();
                if (safetyRectificationList != null && safetyRectificationList.size() > 0) {
                    for (SafetyRectification safetyRectification : safetyRectificationList) {
                        JSONObject safetyMap = new JSONObject();
                        safetyMap.put("areaName", safetyRectification.getAreaName());  //检查区域0
                        safetyMap.put("safetyRequire", safetyRectification.getSafetyRequire());    //整改要求
                        safetyMap.put("initiatorName", safetyRectification.getInitiatorName());    //检查人
                        safetyMap.put("safetyDeadline", safetyRectification.getSafetyDeadline());  //整改期限
                        if (safetyRectification.getSafetyPhotos() != null) {
                            List safetyPhotos = Arrays.asList(safetyRectification.getSafetyPhotos().split(","));//根据逗号分隔转化为list
                            safetyMap.put("safetyPhotos", safetyPhotos);      //整改照片路径
                        } else {
                            safetyMap.put("safetyPhotos", Collections.emptyList());      //整改照片路径
                        }
                        safetyMap.put("gradeName", safetyRectification.getGradeName());        //问题严重级别
                        safetyMap.put("rectifyName", safetyRectification.getRectifyName());    //整改人
                        safetyMap.put("safetyName", safetyRectification.getSafetyDescribe());       //整改第几条
                        safetyMap.put("status", "待整改");
                        safetyMap.put("reviewName", null);        //复查人
                        HjSafetyNoPass hjSafetyNoPass = safetyAbarbeitungService.getAfterRectificationNoPass(safetyRectification.getSafetyId());
                        if (hjSafetyNoPass != null) {
                            safetyMap.put("safetyId", hjSafetyNoPass.getSafetyId());

                            safetyMap.put("safetyCreateTime", hjSafetyNoPass.getSafetyCreateTime());   //整改单创建时间
                        }
                        safetyArray.add(safetyMap);
                    }
                    jsonObject.put("msg", "查询成功");
                    jsonObject.put("code", 0);
                    jsonObject.put("data", safetyArray);
                } else {
                    jsonObject.put("msg", "查询失败");
                    jsonObject.put("code", -1);
                    jsonObject.put("data", Collections.emptyList());
                }

        } else if (status == 2) {  //待复查

                if (safetyRectificationList != null && safetyRectificationList.size() > 0) {
                    JSONArray safetyArray = new JSONArray();
                    for (SafetyRectification safetyRectification : safetyRectificationList) {
                        JSONObject safetyMap = new JSONObject();
                        safetyMap.put("areaName", safetyRectification.getAreaName());  //检查区域
                        safetyMap.put("safetyRequire", safetyRectification.getSafetyRequire());    //整改要求
                        safetyMap.put("initiatorName", safetyRectification.getInitiatorName());    //检查人
                        safetyMap.put("safetyDeadline", safetyRectification.getSafetyDeadline());  //整改期限
                        if (safetyRectification.getSafetyPhotos() != null) {
                            List safetyPhotos = Arrays.asList(safetyRectification.getSafetyPhotos().split(","));//根据逗号分隔转化为list
                            safetyMap.put("safetyPhotos", safetyPhotos);      //整改照片路径
                        } else {
                            safetyMap.put("safetyPhotos", Collections.emptyList());      //整改照片路径
                        }
                        safetyMap.put("gradeName", safetyRectification.getGradeName());        //问题严重级别
                        safetyMap.put("rectifyName", null);    //整改人
                        safetyMap.put("reviewName", safetyRectification.getReviewName());        //复查人
                        safetyMap.put("safetyName", safetyRectification.getSafetyDescribe());       //整改第几条
                        safetyMap.put("status", "待复查");
                        HjSafetyNoPass hjSafetyNoPass = safetyAbarbeitungService.getAfterRectificationNoPass(safetyRectification.getSafetyId());
                        if (hjSafetyNoPass != null) {
                            safetyMap.put("safetyId", hjSafetyNoPass.getSafetyId());
                            safetyMap.put("safetyCreateTime", hjSafetyNoPass.getRectifyTime());   //整改单创建时间
                        }
                        safetyArray.add(safetyMap);
                    }
                    jsonObject.put("msg", "查询成功");
                    jsonObject.put("code", 0);
                    jsonObject.put("data", safetyArray);
                } else {
                    jsonObject.put("msg", "查询失败");
                    jsonObject.put("code", -1);
                    jsonObject.put("data", Collections.emptyList());
                }
        } else if (status == 3) {     //已完成
            JSONArray safetyArray = new JSONArray();
                if (safetyRectificationList != null && safetyRectificationList.size() > 0) {

                    for (SafetyRectification safetyRectification : safetyRectificationList) {
                        JSONObject safetyMap = new JSONObject();
                        safetyMap.put("areaName", safetyRectification.getAreaName());  //检查区域
                        if (safetyRectification.getSafetyPhotos() != null) {
                            List safetyPhotos = Arrays.asList(safetyRectification.getSafetyPhotos().split(","));//根据逗号分隔转化为list
                            safetyMap.put("safetyPhotos", safetyPhotos);      //整改照片路径
                        } else {
                            safetyMap.put("safetyPhotos", Collections.emptyList());      //整改照片路径
                        }
                        safetyMap.put("reviewName", safetyRectification.getReviewName());        //复查人

                        safetyMap.put("safetyRequire", null);    //整改要求
                        safetyMap.put("initiatorName", null);    //检查人
                        safetyMap.put("safetyDeadline", null);  //整改期限
                        safetyMap.put("gradeName", null);        //问题严重级别
                        safetyMap.put("rectifyName", null);    //整改人
                        safetyMap.put("safetyName", safetyRectification.getSafetyDescribe());       //整改第几条
                        safetyMap.put("status", "已完成");
                        HjSafetyNoPass hjSafetyNoPass = safetyAbarbeitungService.getAfterRectificationNoPass(safetyRectification.getSafetyId());
                        if (hjSafetyNoPass != null) {
                            safetyMap.put("safetyId", hjSafetyNoPass.getSafetyId());
                            if(hjSafetyNoPass.getReviewTime() == null){
                                safetyMap.put("safetyCreateTime", hjSafetyNoPass.getSafetyCreateTime());   //整改单创建时间
                            }else {
                                safetyMap.put("safetyCreateTime", hjSafetyNoPass.getReviewTime());   //整改单创建时间
                            }
                        }
                        safetyArray.add(safetyMap);
                    }
                    jsonObject.put("msg", "查询成功");
                    jsonObject.put("code", 0);
                    jsonObject.put("data", safetyArray);
                } else {
                    jsonObject.put("msg", "查询失败");
                    jsonObject.put("code", -1);
                    jsonObject.put("data", Collections.emptyList());
                }
        }
    } else {
        jsonObject.put("msg", "查询成功");
        jsonObject.put("code", 0);
        jsonObject.put("data", Collections.emptyList());
    }
        return jsonObject;
    }


    /**
     * 进行整改
     * @param hjSafetyNoPass  file  rectifyResult  safetyId
     * @return
     */
    @PostMapping(value = "alterSafety")
    public JSONObject alterSafety(@RequestBody HjSafetyNoPass hjSafetyNoPass)throws IOException{
        JSONObject jsonObject = new JSONObject();
        String title = "整改回复单";
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sp.format(new Date());
        HjSafetyAbarbeitung safetyAbarbeitung = new HjSafetyAbarbeitung();
        HjSafetyNoPass safetyNoPass = new HjSafetyNoPass();
        HjSafetyNoPass hjSafetyNoPassList = safetyAbarbeitungService.getAfterRectificationNoPass(hjSafetyNoPass.getSafetyId());
        HjSafetyAbarbeitung hjSafetyAbarbeitung = safetyAbarbeitungService.selectHjSafetyAbarbeitungById(hjSafetyNoPass.getSafetyId());
        HjSystemUser initiator = systemUserService.getByAlias(hjSafetyAbarbeitung.getInitiatorId());  //发起整改人
        HjSystemUser rectify = systemUserService.getByAlias(hjSafetyAbarbeitung.getRectifyId());    //整改人
        HjSystemUser review = systemUserService.getByAlias(hjSafetyAbarbeitung.getReviewId());  //复查人

        Project project = safetyAbarbeitungService.getProject(hjSafetyAbarbeitung.getConstructionId()); //项目
        HjSafetyArea safetyArea = safetyAreaService.selectHjSafetyAreaById(hjSafetyAbarbeitung.getAreaId());
        if(!hjSafetyNoPass.getUserId().equals(hjSafetyAbarbeitung.getRectifyId())){
            jsonObject.put("msg", "没有权限");
            jsonObject.put("code", -1);
        }else {
            if (hjSafetyNoPassList != null) {
                safetyAbarbeitung.setStatus(2);
                safetyAbarbeitung.setId(hjSafetyNoPass.getSafetyId());
                int temp = safetyAbarbeitungService.alterStatus(safetyAbarbeitung);
                if (temp > 0) {
                    int count = safetyAbarbeitungService.getSafetyCount(hjSafetyNoPass.getSafetyId());
                    StringBuffer sb = new StringBuffer();
                    //照片
                    if(hjSafetyNoPass.getFile() != null && hjSafetyNoPass.getFile().length>0) {
                        for (int i = 0; i < hjSafetyNoPass.getFile().length; i++) {
                            String photoName = hjSafetyNoPass.getFile()[i].getOriginalFilename();
                            String url = AliyunOSSClientUtil.uploadFileImg(hjSafetyNoPass.getFile()[i], "zhgd_img", photoName);
                            String path = url.substring(0, url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
                            sb.append(path);
                            sb.append(",");
                        }
                        String safetyPhotos = sb.toString().substring(0, sb.toString().length() - 1);
                        safetyNoPass.setRectifyPhotos(safetyPhotos);
                    }else {
                        safetyNoPass.setRectifyPhotos(hjSafetyNoPass.getRectifyPhotos());
                    }

                    safetyNoPass.setRectifyResult(hjSafetyNoPass.getRectifyResult());
                    safetyNoPass.setRectifyTime(format);
                    safetyNoPass.setSafetyName("待复查第" + count + "条");
                    safetyNoPass.setId(hjSafetyNoPassList.getId());
                    int result = safetyAbarbeitungService.alterSafetyNoPass(safetyNoPass);
                    Map<String,String> extras = new HashMap<>();
                    if(hjSafetyAbarbeitung.getDifferentiate()==1){
                        extras.put("skip", "18");   //安全权限
                        extras.put("append", String.valueOf(hjSafetyNoPass.getSafetyId()));   //巡检单ID
                        title = "安全整改回复单";
                    }else if(hjSafetyAbarbeitung.getDifferentiate()==2){
                        title = "质量整改回复单";
                        extras.put("skip", "24");   //质量权限
                        extras.put("append", String.valueOf(hjSafetyNoPass.getSafetyId()));   //巡检单ID
                    }


                    String alert = "已按" + initiator.getUserName() + "要求完成了" + safetyArea.getArea() + "的整改工作，现上报，请予以复查。";
                    if (result > 0) {
                        if (!"".equals(hjSafetyAbarbeitung.getReviewId()) && hjSafetyAbarbeitung.getReviewId() != null) {
                            if (review.getAlias() != null) {

                                JsonObject intent = new JsonObject();
                                intent.add("url", null);
                                boolean isApnsProduction = false;
                                ///推送-复查人
                                PushExample pushExample = new PushExample();

                                PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                                        (alert, review.getAlias(), title,  isApnsProduction,extras);
                                pushExample.testSendPush(payload);

                                HjSafetyCommission safetyCommission = new HjSafetyCommission();
                                safetyCommission.setSafetyId(hjSafetyNoPass.getSafetyId());
                                safetyCommission.setStatus(0);
                                safetyCommission.setUserId(hjSafetyAbarbeitung.getRectifyId());
                                safetyCommissionService.updateHjSafetyCommission(safetyCommission);

                                //将消息保存--通知页面
                                HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
                                hjSafetyCommission.setInitiator(initiator.getUserName());
                                hjSafetyCommission.setRectify(rectify.getUserName());
                                hjSafetyCommission.setTitle(hjSafetyAbarbeitung.getSafetyDescribe()+"已整改");
                                hjSafetyCommission.setCreateTime(format);
                                hjSafetyCommission.setStatus(1);
                                hjSafetyCommission.setProjectId(project.getProjectId());
                                hjSafetyCommission.setUserId(hjSafetyAbarbeitung.getReviewId());
                                hjSafetyCommission.setDifferentiate(hjSafetyAbarbeitung.getDifferentiate());
                                hjSafetyCommission.setSafetyId(hjSafetyNoPass.getSafetyId());
                                safetyCommissionService.insertHjSafetyCommission(hjSafetyCommission);



                                //判断当前项目短信剩余条数是否大于0
                                ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
                                if(project!=null){
                                    shortCreedNumber.setProjectId(project.getProjectId());
                                    ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);
                                    if(shortCreedNumberList.getNoteNumber()>0 && shortCreedNumberList.getNoteNumber() != null) {
                                    //短信
                                    Map<String, String> tempPara = new HashMap<>();
                                    tempPara.put("title", title);
                                    tempPara.put("userName", initiator.getUserName());  //用户
                                    tempPara.put("area", safetyArea.getArea());

                                    JSMSExample.testSendTemplateSMS(review.getUserPhone(), RECTIFYID, tempPara);
                                    ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                                    shortCreedNumber1.setProjectId(project.getProjectId());
                                    shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber()-1);
                                    shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);

                                    //保存巡检短信信息
                                    HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
                                    hjExcessiveSafety.setProjectId(project.getProjectId());
                                    hjExcessiveSafety.setUserId(hjSafetyAbarbeitung.getReviewId());
                                    hjExcessiveSafety.setContent(title + ":" + alert);
                                    hjExcessiveSafety.setCreateTime(format);
                                    excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
                                }
                                }
                            }
                        }
                        jsonObject.put("msg", "新增成功");
                        jsonObject.put("code", 0);
                    }
                }
            } else {
                jsonObject.put("msg", "新增失败");
                jsonObject.put("code", -1);
            }
        }

        return jsonObject;
    }

    /**
     * 复查人通过/未通过
     * @param hjSafetyNoPass safetyId file reviewResult
     * @return
     * @throws IOException
     */
    @PostMapping(value = "alertReviewPass")
    public JSONObject alertReviewPass(@RequestBody HjSafetyNoPass hjSafetyNoPass) throws IOException{
        JSONObject jsonObject = new JSONObject();
        String title = "复查回复单";
        int status= 1;
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sp.format(new Date());
        HjSafetyAbarbeitung safetyAbarbeitung = new HjSafetyAbarbeitung();
        HjSafetyNoPass safetyNoPass = new HjSafetyNoPass();
        HjSafetyNoPass hjSafetyNoPassList = safetyAbarbeitungService.getAfterRectificationNoPass(hjSafetyNoPass.getSafetyId());
        HjSafetyAbarbeitung safety = safetyAbarbeitungService.getDescribe(hjSafetyNoPass.getSafetyId());
        HjSafetyAbarbeitung hjSafetyAbarbeitung = safetyAbarbeitungService.selectHjSafetyAbarbeitungById(hjSafetyNoPass.getSafetyId());
        HjSystemUser initiator = systemUserService.getByAlias(hjSafetyAbarbeitung.getInitiatorId());  //发起整改人
        HjSystemUser rectify = systemUserService.getByAlias(hjSafetyAbarbeitung.getRectifyId());    //整改人
        HjSystemUser review = systemUserService.getByAlias(hjSafetyAbarbeitung.getReviewId());  //复查人
        Project project = safetyAbarbeitungService.getProject(hjSafetyAbarbeitung.getConstructionId()); //项目
        HjSafetyArea safetyArea = safetyAreaService.selectHjSafetyAreaById(hjSafetyAbarbeitung.getAreaId());
        StringBuffer sb = new StringBuffer();
        if(!hjSafetyNoPass.getUserId().equals(hjSafetyAbarbeitung.getReviewId())){
            jsonObject.put("msg", "没有权限");
            jsonObject.put("code", -1);
        }else {
            //照片
            if(hjSafetyNoPass.getFile() != null && hjSafetyNoPass.getFile().length>0) {
                for (int i = 0; i < hjSafetyNoPass.getFile().length; i++) {
                    String photoName = hjSafetyNoPass.getFile()[i].getOriginalFilename();
                    String url = AliyunOSSClientUtil.uploadFileImg(hjSafetyNoPass.getFile()[i], "zhgd_img", photoName);
                    String path = url.substring(0, url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
                    sb.append(path);
                    sb.append(",");
                }
                String reviewPath = sb.toString().substring(0, sb.toString().length() - 1);
                safetyNoPass.setReviewPath(reviewPath);     //现场照片
            }else {
                safetyNoPass.setReviewPath(hjSafetyNoPass.getReviewPath());     //现场照片
            }
            if (hjSafetyNoPassList != null) {
                safetyAbarbeitung.setStatus(hjSafetyNoPass.getStatus());
                safetyAbarbeitung.setId(hjSafetyNoPass.getSafetyId());
                //状态
                int temp = safetyAbarbeitungService.alterStatus(safetyAbarbeitung);
                int result = 0;
                if (temp > 0) {
                    String describe = safety.getSafetyDescribe();

                    //通过
                    if (3==hjSafetyNoPass.getStatus()) {
                        status = 0;
                        safetyNoPass.setSafetyName(describe + "已验收");
                        safetyNoPass.setReviewOpinions("通过");       //复查结果
                        safetyNoPass.setReviewResult(hjSafetyNoPass.getReviewResult());         //复查意见

                        safetyNoPass.setReviewTime(format);    //时间
                        safetyNoPass.setId(hjSafetyNoPassList.getId());
                        result = safetyAbarbeitungService.alertReviewPassTwo(safetyNoPass);


                    //未通过
                    } else if (1 == hjSafetyNoPass.getStatus()) {
                        safetyNoPass.setSafetyName("未通过");
                        safetyNoPass.setReviewOpinions("未通过");   //复查结果
                        safetyNoPass.setReviewResult(hjSafetyNoPass.getReviewResult());         //复查意见
                        safetyNoPass.setReviewTime(format);    //时间
                        safetyNoPass.setId(hjSafetyNoPassList.getId());
                        result = safetyAbarbeitungService.alertReviewPassTwo(safetyNoPass);
                        if (result > 0) {
                            int count = safetyAbarbeitungService.getSafetyCount(hjSafetyNoPass.getSafetyId());
                            int sum = count + 1;
                            safetyNoPass.setSafetyId(hjSafetyNoPass.getSafetyId());
                            safetyNoPass.setSafetyName("待整改第" + sum + "条");
                            safetyNoPass.setSafetyCreateTime(format);
                            safetyAbarbeitungService.sponsorReviewNoPass(safetyNoPass);

                        }
                    }
                    Map<String,String> extras = new HashMap<>();
                    if(hjSafetyAbarbeitung.getDifferentiate()==1){
                        extras.put("skip", "18");   //安全权限
                        extras.put("append", String.valueOf(hjSafetyNoPass.getSafetyId()));   //巡检单ID
                        title = "安全复查回复单";
                    }else if(hjSafetyAbarbeitung.getDifferentiate()==2){
                        extras.put("skip", "24");   //质量权限
                        extras.put("append", String.valueOf(hjSafetyNoPass.getSafetyId()));   //巡检单ID
                        title = "质量复查回复单";
                    }

                    String alert = safetyArea.getArea() + ":" + describe
                            + "\n复查意见：" + safetyNoPass.getReviewResult()
                            + "\n复查人：" + review.getUserName();
                    if (result > 0) {
                        Map<String, String> tempPara = new HashMap<>();
                        tempPara.put("title", title);
                        tempPara.put("describe", "“" + safetyArea.getArea() + "”" + describe);
                        tempPara.put("reviewResult", safetyNoPass.getReviewResult());
                        tempPara.put("userName", review.getUserName());  //用户

                        if (!"".equals(hjSafetyAbarbeitung.getRectifyId()) && hjSafetyAbarbeitung.getRectifyId() != null) {
                            if (rectify.getAlias() != null) {
                                boolean isApnsProduction = false;
                                //推送
                                PushExample pushExample = new PushExample();
                                PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                                        (alert, rectify.getAlias(),title,isApnsProduction,extras);
                                pushExample.testSendPush(payload);

                                HjSafetyCommission safetyCommission = new HjSafetyCommission();
                                safetyCommission.setSafetyId(hjSafetyNoPass.getSafetyId());
                                safetyCommissionService.deleteSafetyCommission(safetyCommission);



                                //将消息保存--通知页面
                                HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
                                hjSafetyCommission.setInitiator(initiator.getUserName());
                                hjSafetyCommission.setRectify(rectify.getUserName());
                                hjSafetyCommission.setTitle(safetyNoPass.getReviewResult());
                                hjSafetyCommission.setCreateTime(format);
                                hjSafetyCommission.setStatus(status);
                                hjSafetyCommission.setProjectId(project.getProjectId());
                                hjSafetyCommission.setUserId(hjSafetyAbarbeitung.getRectifyId());
                                hjSafetyCommission.setDifferentiate(hjSafetyAbarbeitung.getDifferentiate());
                                hjSafetyCommission.setSafetyId(hjSafetyNoPass.getSafetyId());
                                safetyCommissionService.insertHjSafetyCommission(hjSafetyCommission);




                                ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
                                if(project!=null){
                                    shortCreedNumber.setProjectId(project.getProjectId());
                                ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);
                                if(shortCreedNumberList.getNoteNumber()>0 && shortCreedNumberList.getNoteNumber() != null) {

                                    //短信
                                    JSMSExample.testSendTemplateSMS(rectify.getUserPhone(), REVIEWID, tempPara);
                                    //更改剩余次数
                                    ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                                    shortCreedNumber1.setProjectId(project.getProjectId());
                                    shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber()-1);
                                    shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);
                                    //保存短息信息
                                    HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
                                    hjExcessiveSafety.setProjectId(project.getProjectId());
                                    hjExcessiveSafety.setUserId(hjSafetyAbarbeitung.getRectifyId());
                                    hjExcessiveSafety.setContent(title + ":" + alert);
                                    hjExcessiveSafety.setCreateTime(format);
                                    excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
                                }
                                }
                            }
                        }
                        //抄送人
                        if( hjSafetyAbarbeitung.getMakeId() != null){
                            List<Integer> makes = Arrays.stream(hjSafetyAbarbeitung.getMakeId().split(",")).map(string -> Integer
                                    .parseInt(string.trim())).collect(Collectors.toList());
                            for (int i = 0;i< makes.size();i++){
                                HjSystemUser make = systemUserService.getByAlias(makes.get(i));
                                if(makes.get(i) == hjSafetyAbarbeitung.getRectifyId() ||
                                        makes.get(i)  == hjSafetyAbarbeitung.getReviewId() ||
                                        makes.get(i) == hjSafetyAbarbeitung.getInitiatorId()){
                                    continue;
                                }
                                else {
                                    if(make.getAlias()!=null) {
                                        JsonObject intent = new JsonObject();
                                        intent.add("url",null);
                                        boolean isApnsProduction = false;
                                        PushExample pushExample = new PushExample();
                                        //推送
                                        PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                                                (alert,make.getAlias(),title,isApnsProduction,extras);
                                        pushExample.testSendPush(payload);

                                        //将消息保存--通知页面
                                        HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
                                        hjSafetyCommission.setInitiator(initiator.getUserName());
                                        hjSafetyCommission.setRectify(rectify.getUserName());
                                        hjSafetyCommission.setTitle(safetyNoPass.getReviewResult());
                                        hjSafetyCommission.setCreateTime(format);
                                        hjSafetyCommission.setStatus(status);
                                        hjSafetyCommission.setProjectId(project.getProjectId());
                                        hjSafetyCommission.setUserId(make.getId());
                                        hjSafetyCommission.setDifferentiate(1);
                                        hjSafetyCommission.setSafetyId(hjSafetyNoPass.getSafetyId());
                                        safetyCommissionService.insertHjSafetyCommission(hjSafetyCommission);



                                        //判断当前项目短信剩余条数是否大于0
                                        ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
                                        if(project!=null){
                                            shortCreedNumber.setProjectId(project.getProjectId());
                                            ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);
                                            if(shortCreedNumberList.getNoteNumber()>0 && shortCreedNumberList.getNoteNumber() != null) {
                                                //短信
                                                JSMSExample.testSendTemplateSMS(make.getUserPhone(), SPONSORTEMPID, tempPara);
                                                //更改剩余次数
                                                ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                                                shortCreedNumber1.setProjectId(project.getProjectId());
                                                shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber()-1);
                                                shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);
                                                //保存巡检短信信息
                                                HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
                                                hjExcessiveSafety.setProjectId(project.getProjectId());
                                                hjExcessiveSafety.setUserId(make.getId());
                                                hjExcessiveSafety.setContent(title + ":" + alert);
                                                hjExcessiveSafety.setCreateTime(format);
                                                excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                }
                jsonObject.put("msg", "新增成功");
                jsonObject.put("code", 0);
            }
        }
        return jsonObject;
    }



    /**
     * 详情
     * @param safetyId
     * @return
     */
    @PostMapping(value = "getAfterRectificationDetails")
    public JSONObject getAfterRectificationDetails(@RequestParam(value = "safetyId")Integer safetyId){
        JSONObject jsonObject = new JSONObject();
        SafetyRectification safetyRectification = safetyAbarbeitungService.getAfterRectificationDetails(safetyId);
        if(safetyRectification != null ) {
            JSONObject safetyMap = new JSONObject();
            safetyMap.put("status",safetyRectification.getStatus());
            safetyMap.put("safetyId", safetyRectification.getSafetyId());        //整改ID
            safetyMap.put("safetyCreateTime", safetyRectification.getSafetyCreateTime());        //创建时间
            safetyMap.put("hiddenName", safetyRectification.getHiddenName());    //隐患类型
            safetyMap.put("areaName", safetyRectification.getAreaName());        //检查区域
            safetyMap.put("constructionName", safetyRectification.getConstructionName());    //分包单位
            safetyMap.put("gradeName", safetyRectification.getGradeName());      //问题级别名称
            safetyMap.put("safetyDescribe", safetyRectification.getSafetyDescribe());        //问题描述

            if (safetyRectification.getSafetyPhotos() != null) {
                List safetyPhotos = Arrays.asList(safetyRectification.getSafetyPhotos().split(","));//根据逗号分隔转化为list
                safetyMap.put("safetyPhotos", safetyPhotos);      //整改照片路径
            } else {
                safetyMap.put("safetyPhotos", Collections.emptyList());      //整改照片路径
            }

            safetyMap.put("initiatorName", safetyRectification.getInitiatorName());    //检查人
            safetyMap.put("initiatorTime", safetyRectification.getInitiatorTime());          //检查时间
            safetyMap.put("rectifyName", safetyRectification.getRectifyName());  //整改人名称
            safetyMap.put("safetyDeadline", safetyRectification.getSafetyDeadline());            //整改时间
            safetyMap.put("safetyRequire", safetyRectification.getSafetyRequire());                //整改要求
            safetyMap.put("rectifyResult", safetyRectification.getRectifyResult());                //整改要求
            if (safetyRectification.getRectifyPhotos() != null){
                List rectifyPhotos = Arrays.asList(safetyRectification.getRectifyPhotos().split(","));//根据逗号分隔转化为list
                safetyMap.put("rectifyPhotos", rectifyPhotos);
            }else {
                safetyMap.put("rectifyPhotos", Collections.emptyList());
            }
            safetyMap.put("reviewName", safetyRectification.getReviewName());
            safetyMap.put("reviewOpinions", safetyRectification.getReviewOpinions());
            safetyMap.put("reviewResult", safetyRectification.getReviewResult());
            if(safetyRectification.getReviewPath() != null) {
                List reviewPath = Arrays.asList(safetyRectification.getReviewPath().split(","));//根据逗号分隔转化为list
                safetyMap.put("reviewPath", reviewPath);
            }else {
                safetyMap.put("reviewPath", Collections.emptyList());
            }
            JSONArray makeArray = new JSONArray();
            if(safetyRectification.getMakeId()!=null){
                List makeList = Arrays.asList(safetyRectification.getMakeId().split(","));//根据逗号分隔转化为list
                    for (int i = 0;i<makeList.size();i++){
                        HjSystemUser make = systemUserService.getByAlias(Integer.valueOf(makeList.get(i).toString()));
                        makeArray.add(make.getUserName());

                    }
                safetyMap.put("makeName",makeArray);        //抄送人
            }else {
                safetyMap.put("makeName",Collections.emptyList());        //抄送人
            }


            List<HjSafetyNoPass> hjSafetyNoPassList = safetyAbarbeitungService.getAfterRectificationDetailsList(safetyId);
            JSONArray safetyArray = new JSONArray();
            for (HjSafetyNoPass hjSafetyNoPass:hjSafetyNoPassList){
                JSONObject initiatorMap = new JSONObject();
                JSONObject rectifyMap = new JSONObject();
                JSONObject reviewMap = new JSONObject();
                if(hjSafetyNoPass.getReviewTime() != null){
                    reviewMap.put("engineer","安全工程师");
                    reviewMap.put("title","复查"+hjSafetyNoPass.getReviewOpinions());
                    reviewMap.put("name",hjSafetyNoPass.getReviewName());
                    reviewMap.put("time",hjSafetyNoPass.getReviewTime());
                    safetyArray.add(reviewMap);
                }
                if(hjSafetyNoPass.getRectifyTime() != null){
                    rectifyMap.put("engineer","项目负责人");
                    rectifyMap.put("title","整改完成");
                    rectifyMap.put("name",hjSafetyNoPass.getRectifyName());
                    rectifyMap.put("time",hjSafetyNoPass.getRectifyTime());
                    safetyArray.add(rectifyMap);
                }
                initiatorMap.put("engineer","安全工程师");
                initiatorMap.put("title","发起安全整改");
                initiatorMap.put("name",hjSafetyNoPass.getInitiatorName()); //安全工程师
                initiatorMap.put("time",hjSafetyNoPass.getSafetyCreateTime());
                safetyArray.add(initiatorMap);




            }
            safetyMap.put("list",safetyArray);

            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",safetyMap);
        }else{
            jsonObject.put("msg","查询失败");
            jsonObject.put("code",-1);
            jsonObject.put("data",Collections.emptyList());
        }
        return jsonObject;
    }


    /**
     * 质量监管--合格/不合格
     *
     * @param hjSafetyAbarbeitung
     * @return
     * @throws IOException
     */
    @PostMapping(value = "sponsorQuality")
   public JSONObject sponsorQuality(@RequestBody HjSafetyAbarbeitung hjSafetyAbarbeitung,MultipartFile[] file) throws IOException {
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sp.format(new Date());      //获取当前时间
        HjSafetyAbarbeitung safetyAbarbeitung = new HjSafetyAbarbeitung();
        //照片
        if (file != null && file.length>0 ) {
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < file.length; i++) {
                String photoName = file[i].getOriginalFilename();
                String url = AliyunOSSClientUtil.uploadFileImg(file[i], "zhgd_img", photoName);
                String path = url.substring(0, url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
                sb.append(path);
                sb.append(",");
            }
            String safetyPhotos = sb.toString().substring(0,sb.toString().length()-1);
            safetyAbarbeitung.setSafetyPhotos(safetyPhotos);                                        //file      必填
        }else{
            safetyAbarbeitung.setSafetyPhotos(hjSafetyAbarbeitung.getSafetyPhotos());
        }
        //抄送人
        if(hjSafetyAbarbeitung.getMake() != null && hjSafetyAbarbeitung.getMake().length>0){
            StringBuffer sb2=new StringBuffer();
            for (int i = 0;i<hjSafetyAbarbeitung.getMake().length;i++){
                sb2.append(hjSafetyAbarbeitung.getMake()[i]);

                sb2.append(",");
            }
            String makeId = (sb2.toString().substring(0,sb2.toString().length()-1));
            safetyAbarbeitung.setMakeId(makeId);           //抄送人ID 必填
        }else {
            safetyAbarbeitung.setMakeId(null);
        }

        //合格
        if(hjSafetyAbarbeitung.getResult() ==1) {
            safetyAbarbeitung.setDifferentiate(2);
            safetyAbarbeitung.setStatus(3);     //状态--合格
            safetyAbarbeitung.setConstructionId(hjSafetyAbarbeitung.getConstructionId());   //责任分包公司    必填
            safetyAbarbeitung.setAreaId(hjSafetyAbarbeitung.getAreaId());                   //检查区域      必填
            safetyAbarbeitung.setProblemGradeId(hjSafetyAbarbeitung.getProblemGradeId());   //问题级别 必填
            safetyAbarbeitung.setSafetyDescribe(hjSafetyAbarbeitung.getSafetyDescribe());       //问题描述 必填
            safetyAbarbeitung.setInitiatorId(hjSafetyAbarbeitung.getInitiatorId());     //发起整改人ID    必填 当前登录用户ID
            safetyAbarbeitung.setReviewId(hjSafetyAbarbeitung.getInitiatorId());           //复查人ID     必填
            safetyAbarbeitung.setInitiatorTime(format);     //检查时间
            int temp = safetyAbarbeitungService.sponsorSafety(safetyAbarbeitung);

                if (temp > 0) {
                    int safetyId = safetyAbarbeitung.getId();
                    HjSafetyNoPass hjSafetyNoPass = new HjSafetyNoPass();
                    hjSafetyNoPass.setSafetyId(safetyId);
                    hjSafetyNoPass.setSafetyName("合格");
                    hjSafetyNoPass.setSafetyCreateTime(format);
                    hjSafetyNoPass.setReviewTime(format);
                    int temp2 = safetyAbarbeitungService.sponsorSafetyTwo(hjSafetyNoPass);
                    jsonObject.put("msg", temp2 > 0 ? "新增成功" : "新增失败");
                    jsonObject.put("code", temp2 > 0 ? 0 : -1);

                }
        //不合格
        }else if (hjSafetyAbarbeitung.getResult() == 2 ){
            safetyAbarbeitung.setDifferentiate(2);
            safetyAbarbeitung.setStatus(1);     //状态--不合格
            safetyAbarbeitung.setConstructionId(hjSafetyAbarbeitung.getConstructionId());   //责任分包公司    必填
            safetyAbarbeitung.setAreaId(hjSafetyAbarbeitung.getAreaId());                   //检查区域      必填
            safetyAbarbeitung.setProblemGradeId(hjSafetyAbarbeitung.getProblemGradeId());   //问题级别 必填
            safetyAbarbeitung.setSafetyDescribe(hjSafetyAbarbeitung.getSafetyDescribe());       //问题描述 必填
            safetyAbarbeitung.setInitiatorId(hjSafetyAbarbeitung.getInitiatorId());     //发起整改人ID    必填 当前登录用户ID
            safetyAbarbeitung.setRectifyId(hjSafetyAbarbeitung.getRectifyId());         //整改人ID       必填
            safetyAbarbeitung.setReviewId(hjSafetyAbarbeitung.getReviewId());           //复查人ID     必填
            safetyAbarbeitung.setSafetyDeadline(hjSafetyAbarbeitung.getSafetyDeadline());   //最后整改期限 必填
            safetyAbarbeitung.setSafetyRequire(hjSafetyAbarbeitung.getSafetyRequire());       //整改备注/要求 必填
            safetyAbarbeitung.setInitiatorTime(format);     //检查时间
            int temp = safetyAbarbeitungService.sponsorSafety(safetyAbarbeitung);
                if (temp > 0) {
                    int safetyId = safetyAbarbeitung.getId();
                    Map<String,String> extras = new HashMap<>();
                    extras.put("skip", "24");   //质量权限
                    extras.put("append", String.valueOf(safetyId));   //巡检单ID

                    HjSafetyArea safetyArea = safetyAreaService.selectHjSafetyAreaById(safetyAbarbeitung.getAreaId());
                    HjSystemUser initiator = systemUserService.getByAlias(safetyAbarbeitung.getInitiatorId());
                    HjSystemUser rectify = systemUserService.getByAlias(safetyAbarbeitung.getRectifyId());
                    Project project = safetyAbarbeitungService.getProject(safetyAbarbeitung.getConstructionId());
                    HjSafetyNoPass hjSafetyNoPass = new HjSafetyNoPass();

                    hjSafetyNoPass.setSafetyId(safetyId);
                    hjSafetyNoPass.setSafetyName("待整改第1条");
                    hjSafetyNoPass.setSafetyCreateTime(format);
                    String title="质量整改通知书";
                    String alert ="\n"+initiator.getUserName()+"于"+format+"在"+safetyArea.getArea()
                            +"发起一条整改";
                    Map<String,String> tempPara = new HashMap<>();
                    tempPara.put("title",title);
                    tempPara.put("userName",initiator.getUserName());  //用户
                    tempPara.put("time", format);   //时间
                    tempPara.put("area",safetyArea.getArea());
                    if(rectify.getAlias()!=null) {
                        boolean isApnsProduction = false;
                        //推送
                        PushExample pushExample = new PushExample();
                        PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                                (alert,rectify.getAlias(),title,isApnsProduction,extras);
                        pushExample.testSendPush(payload);

                        //将消息保存--通知页面
                        HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
                        hjSafetyCommission.setInitiator(initiator.getUserName());
                        hjSafetyCommission.setRectify(rectify.getUserName());
                        hjSafetyCommission.setTitle(hjSafetyAbarbeitung.getSafetyDescribe());
                        hjSafetyCommission.setCreateTime(format);
                        hjSafetyCommission.setStatus(1);
                        hjSafetyCommission.setProjectId(project.getProjectId());
                        hjSafetyCommission.setUserId(hjSafetyAbarbeitung.getRectifyId());
                        hjSafetyCommission.setDifferentiate(2);
                        hjSafetyCommission.setSafetyId(safetyId);
                        safetyCommissionService.insertHjSafetyCommission(hjSafetyCommission);


                        ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
                        if(project!=null){
                            shortCreedNumber.setProjectId(project.getProjectId());

                        ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);
                        if(shortCreedNumberList.getNoteNumber()>0 && shortCreedNumberList.getNoteNumber() != null) {
                            //短信
                            JSMSExample.testSendTemplateSMS(rectify.getUserPhone(),SPONSORTEMPID,tempPara);
                            //更改剩余次数
                            ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                            shortCreedNumber1.setProjectId(project.getProjectId());
                            shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber()-1);
                            shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);
                            //保存巡检短信
                            HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
                            hjExcessiveSafety.setProjectId(project.getProjectId());
                            hjExcessiveSafety.setUserId(safetyAbarbeitung.getRectifyId());
                            hjExcessiveSafety.setContent(title+":"+alert);
                            hjExcessiveSafety.setCreateTime(format);
                            excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
                        }
                        }
                    }
                    if( hjSafetyAbarbeitung.getMake() != null && hjSafetyAbarbeitung.getMake().length>0){

                        for (int i = 0;i<hjSafetyAbarbeitung.getMake().length;i++) {
                            HjSystemUser make = systemUserService.getByAlias(hjSafetyAbarbeitung.getMake()[i]);

                            if (hjSafetyAbarbeitung.getMake()[i] == hjSafetyAbarbeitung.getRectifyId() ||
                                    hjSafetyAbarbeitung.getMake()[i] == hjSafetyAbarbeitung.getReviewId() ||
                                    hjSafetyAbarbeitung.getMake()[i] == hjSafetyAbarbeitung.getInitiatorId()
                            ) {
                                continue;
                            } else {
                            if (make.getAlias() != null) {
                                JsonObject intent = new JsonObject();
                                intent.add("url", null);
                                boolean isApnsProduction = false;
                                PushExample pushExample = new PushExample();
                                //推送
                                PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                                        (alert, make.getAlias(), title, isApnsProduction, extras);
                                pushExample.testSendPush(payload);

                                //将消息保存--通知页面
                                HjSafetyCommission hjSafetyCommission = new HjSafetyCommission();
                                hjSafetyCommission.setInitiator(initiator.getUserName());
                                hjSafetyCommission.setRectify(rectify.getUserName());
                                hjSafetyCommission.setTitle(safetyAbarbeitung.getSafetyDescribe());
                                hjSafetyCommission.setCreateTime(format);
                                hjSafetyCommission.setStatus(1);
                                hjSafetyCommission.setProjectId(project.getProjectId());
                                hjSafetyCommission.setUserId(make.getId());
                                hjSafetyCommission.setDifferentiate(2);
                                hjSafetyCommission.setSafetyId(safetyId);
                                safetyCommissionService.insertHjSafetyCommission(hjSafetyCommission);


                                //判断当前项目短信剩余条数是否大于0
                                ShortCreedNumber shortCreedNumber = new ShortCreedNumber();
                                if (project != null) {
                                    shortCreedNumber.setProjectId(project.getProjectId());


                                    ShortCreedNumber shortCreedNumberList = shortCreedNumberService.selectShortCreedNumber(shortCreedNumber);
                                    if (shortCreedNumberList.getNoteNumber() > 0 && shortCreedNumberList.getNoteNumber() != null) {
                                        //短信
                                        JSMSExample.testSendTemplateSMS(make.getUserPhone(), SPONSORTEMPID, tempPara);
                                        ShortCreedNumber shortCreedNumber1 = new ShortCreedNumber();
                                        shortCreedNumber1.setProjectId(project.getProjectId());
                                        shortCreedNumber1.setNoteNumber(shortCreedNumberList.getNoteNumber() - 1);
                                        shortCreedNumberService.updateShortCreedNumber(shortCreedNumber1);
                                        //保存巡检短信信息
                                        HjExcessiveSafety hjExcessiveSafety = new HjExcessiveSafety();
                                        hjExcessiveSafety.setProjectId(project.getProjectId());
                                        hjExcessiveSafety.setUserId(make.getId());
                                        hjExcessiveSafety.setContent(title + ":" + alert);
                                        hjExcessiveSafety.setCreateTime(format);
                                        excessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety);
                                    }
                                }
                            }
                        }
                        }
                    }


                    int temp2 = safetyAbarbeitungService.sponsorSafetyTwo(hjSafetyNoPass);
                    jsonObject.put("msg", temp2 > 0 ? "新增成功" : "新增失败");
                    jsonObject.put("code", temp2 > 0 ? 0 : -1);

                }
        }else{
            jsonObject.put("msg","新增失败");
            jsonObject.put("code",-1);
        }
        return jsonObject;
    }



}
