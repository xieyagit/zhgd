package com.hujiang.project.zhgd.deye;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.ThreadUtils;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbCraneAddparams.api.SendCraneToPERSONNEL;
import com.hujiang.project.zhgd.sbCraneAddparams.domain.SbCraneAddparams;
import com.hujiang.project.zhgd.sbCraneAddparams.service.ISbCraneAddparamsService;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.service.ISbCraneAddrecordService;
import com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.domain.SbCraneAlarmChangeDataCrane;
import com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.service.ISbCraneAlarmChangeDataCraneService;
import com.hujiang.project.zhgd.sbCraneBasicinfo.domain.SbCraneBasicinfo;
import com.hujiang.project.zhgd.sbCraneBasicinfo.service.ISbCraneBasicinfoService;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
import com.hujiang.project.zhgd.sbCraneElectrify.domain.SbCraneElectrify;
import com.hujiang.project.zhgd.sbCraneElectrify.service.ISbCraneElectrifyService;
import com.hujiang.project.zhgd.sbCraneHeart.domain.SbCraneHeart;
import com.hujiang.project.zhgd.sbCraneHeart.service.ISbCraneHeartService;
import com.hujiang.project.zhgd.sbCraneLocatordata.domain.SbCraneLocatordata;
import com.hujiang.project.zhgd.sbCraneLocatordata.service.ISbCraneLocatordataService;
import com.hujiang.project.zhgd.sbCraneWarning.domain.SbCraneWarning;
import com.hujiang.project.zhgd.sbCraneWarning.service.ISbCraneWarningService;
import com.hujiang.project.zhgd.sbCraneWorkloop.domain.SbCraneWorkloop;
import com.hujiang.project.zhgd.sbCraneWorkloop.service.ISbCraneWorkloopService;
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.domain.SbElevatorAddbasicinfo;
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.service.ISbElevatorAddbasicinfoService;
import com.hujiang.project.zhgd.sbElevatorAddparams.api.SendElevatorToPERSONNEL;
import com.hujiang.project.zhgd.sbElevatorAddparams.domain.SbElevatorAddparams;
import com.hujiang.project.zhgd.sbElevatorAddparams.service.ISbElevatorAddparamsService;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.service.ISbElevatorAddrecordService;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.sbElevatorBinding.service.ISbElevatorBindingService;
import com.hujiang.project.zhgd.sbElevatorElectrify.domain.SbElevatorElectrify;
import com.hujiang.project.zhgd.sbElevatorElectrify.service.ISbElevatorElectrifyService;
import com.hujiang.project.zhgd.sbElevatorLocatordata.domain.SbElevatorLocatordata;
import com.hujiang.project.zhgd.sbElevatorLocatordata.service.ISbElevatorLocatordataService;
import com.hujiang.project.zhgd.sbElevatorOperator.domain.SbElevatorOperator;
import com.hujiang.project.zhgd.sbElevatorOperator.service.ISbElevatorOperatorService;
import com.hujiang.project.zhgd.sbUnloaderEquipment.domain.SbUnloaderEquipment;
import com.hujiang.project.zhgd.sbUnloaderEquipment.service.ISbUnloaderEquipmentService;
import com.hujiang.project.zhgd.sbUnloaderLocatordata.domain.SbUnloaderLocatordata;
import com.hujiang.project.zhgd.sbUnloaderLocatordata.service.ISbUnloaderLocatordataService;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.SbUnloaderRegistration;
import com.hujiang.project.zhgd.sbUnloaderRegistration.service.ISbUnloaderRegistrationService;
import com.hujiang.project.zhgd.utils.Tools;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/zhgd")
public class DeyeCraneApi {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DecimalFormat df = new DecimalFormat("0.0000");//设置保留位数
    DecimalFormat df2 = new DecimalFormat("0.00");

    private final Logger logger = LoggerFactory.getLogger(DeyeCraneApi.class);

    @Autowired
    private ISbCraneBasicinfoService sbCraneBasicinfoService;
    @Autowired
    private ISbCraneAddparamsService sbCraneAddparamsService;
    @Autowired
    private ISbCraneAddrecordService sbCraneAddrecordService;
    @Autowired
    private ISbCraneWarningService sbCraneWarningService;
    @Autowired
    private ISbCraneWorkloopService sbCraneWorkloopService;
    @Autowired
    private ISbCraneElectrifyService sbCraneElectrifyService;
    @Autowired
    private ISbCraneBindingService sbCraneBindingService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Autowired
    private ISbCraneWorkloopService iSbCraneWorkloopService;

    @Autowired
    private ISbElevatorAddbasicinfoService sbElevatorAddbasicinfoService;
    @Autowired
    private ISbElevatorAddparamsService sbElevatorAddparamsService;
    @Autowired
    private ISbElevatorAddrecordService sbElevatorAddrecordService;
    @Autowired
    private ISbElevatorElectrifyService sbElevatorElectrifyService;
    @Autowired
    private ISbElevatorOperatorService sbElevatorOperatorService;
    @Autowired
    private ISbElevatorBindingService sbElevatorBindingService;
    @Autowired
    private ISbElevatorBindingService iSbElevatorBindingService;
    @Autowired
    private IHjProjectService iHjProjectService;
    @Autowired
    private ISbCraneBindingService iSbCraneBindingService;
    //卸料注册信息
    @Autowired
    private ISbUnloaderRegistrationService registrationService;
    //塔吊GPS实时定位
    @Autowired
    private ISbCraneLocatordataService craneLocatordataService;
    //升降机GPS实时定位
    @Autowired
    private ISbElevatorLocatordataService elevatorLocatordataService;
    //卸料GPS实时定位
    @Autowired
    private ISbUnloaderLocatordataService unloaderLocatordataService;
    //卸料设备运行时长
    @Autowired
    private ISbUnloaderEquipmentService unloaderEquipmentService;
    @Autowired
    private SendElevatorToPERSONNEL sendElevatorToPERSONNEL;
    @Autowired
    private SendCraneToPERSONNEL sendCraneToPERSONNEL;
    @Autowired
    private ISbCraneHeartService sbCraneHeartService;
    @Autowired
    private ISbCraneAlarmChangeDataCraneService isbCraneAlarmChangeDataCraneService;


    /**
     * 塔吊注册帧
     */
    @RequestMapping(value = "/LoginDataCrane", method = RequestMethod.POST)
    public JSONObject loginDataCrane(@RequestBody String json) throws Exception {
        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);
        SbCraneBasicinfo sbCraneBasicinfo = new SbCraneBasicinfo();
        sbCraneBasicinfo.setHxzid(s.getString("HxzId"));
        List<SbCraneBasicinfo> sbList = sbCraneBasicinfoService.selectSbCraneBasicinfoList(sbCraneBasicinfo);
        if (sbList == null || sbList.size() == 0) {
            sbCraneBasicinfo.setMonDeviceMan(s.getString("HxzFactory"));
            sbCraneBasicinfo.setDeviceNo(Tools.encodeToMD5s(s.getString("HxzId")));
            sbCraneBasicinfoService.insertSbCraneBasicinfo(sbCraneBasicinfo);
        }
        JSONObject results = new JSONObject();
        results.put("cmd", "LoginDataCrane");
        JSONObject data = new JSONObject();
        data.put("HxzFactory", s.getString("HxzFactory"));
        data.put("HxzId", s.getString("HxzId"));
        data.put("RecordId", s.getString("HxzId"));
        data.put("ServerTime", dateFormat.format(new Date()));
        data.put("HeartBeatInterval", "30");
        data.put("WorkInterval", "20");//工作期间上传间隔
        data.put("NoWorkInterval", "20");//非工作期间上传间隔
        data.put("ErrorDelay", "90");
        data.put("LockFlag", "0");
        data.put("LeaseFlag", "0");
        data.put("LeaseStartDate", "2000-01-01");
        data.put("LeaseEndDate", "2099-01-01");
        data.put("WeightSetError", "0");
        data.put("WindSpeedSetError", "0");
        data.put("RangeSetError", "0");
        data.put("HeightSetError", "0");
        data.put("AngleSetError", "0");
        data.put("ObliguitySetError", "0");
        data.put("GpsSetError", "0");
        data.put("IdSetError", "0");
        data.put("LeasePhone", "13000000000");
        data.put("StationPhone", "13000000000");
        data.put("WorkPhone", "13000000000");
        data.put("ServerIp", "47.106.71.3");
        data.put("ServerPort", "8080");
        results.put("data", data);
        results.put("status", 0);//成功
        return results;
    }

    /**
     * 上报基础信息
     */

    @RequestMapping(value = "/BaseDataCrane", method = RequestMethod.POST)
    public JSONObject baseDataCrane(@RequestBody String json) throws Exception {
        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);
        SbCraneAddparams sbCraneAddparams = new SbCraneAddparams();
        sbCraneAddparams.setHxzid(s.getString("HxzId"));
        List<SbCraneAddparams> sbList = sbCraneAddparamsService.selectSbCraneAddparamsList(sbCraneAddparams);
        if (sbList.size() <= 0) {
            sbCraneAddparams.setDeviceNo(Tools.encodeToMD5s(s.getString("HxzId")));
            sbCraneAddparams.setName("塔式起重机");
            sbCraneAddparams.setTcMaxscope(Double.valueOf(s.getString("MaxRange")));//最大幅度
            sbCraneAddparams.setTcMaxheight(Double.valueOf(s.getString("MaxHeight")));//最大高度
            sbCraneAddparams.setTcLoadcapacity(Double.valueOf(s.getString("MaxWeight")));//最大载重
            sbCraneAddparams.setTowerType("0".equals(s.getString("CraneType")) || "1".equals(s.getString("CraneType")) ? 0 : 1);
            sbCraneAddparams.setFrontArmLength(Double.valueOf(s.getString("FrontArmLength")));  //臂长
            sbCraneAddparams.setBackArmLength(Double.valueOf(s.getString("BackArmLength")));    //尾臂长
            sbCraneAddparamsService.insertSbCraneAddparams(sbCraneAddparams);
        }
        //返回json字符串
        JSONObject results = new JSONObject();
        results.put("cmd", "BaseDataCrane");
        results.put("data", "{}");
        results.put("status", 0);//成功
        return results;
    }

    /**
     * 接收德业塔吊设备实时信息
     */

    @RequestMapping(value = "/RealtimeDataCrane", method = RequestMethod.POST)
    public JSONObject setCraneData(@RequestBody String json) throws Exception {

        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);
        //将塔吊实时数据有json转换为对象

//		System.out.println(craneData);
        //保存塔吊实时数据

        //上传工务署
//        JSONArray body=setCraneBody(s);
//        JSONArray body2=setWarningBody(s);

        //上传城安院
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(s.getString("HxzId"));
        sbCraneBinding.setScznl("CAY");
        List<SbCraneBinding> list = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        if (list.size() > 0) {
//            ThreadUtils.async(new Runnable(){
//
//                @Override
//                public void run() {
//                    try {
////                        tdsssjCay(s);
//                        tdyjCay(s);
//                    } catch (IOException e) {
//                        logger.error("城安院错误(RealtimeDataCrane): " + e.getMessage() + ", 参数错误："+s);
//                    } catch (URISyntaxException e) {
//                        logger.error("城安院错误(RealtimeDataCrane): " + e.getMessage() + ", 参数错误："+s);
//                    }
//                }
//            });
//            sendCraneToPERSONNEL.cayDate(s);
        }

//
//        setZCData(s.getString("HxzId"),body,"/crane/addRecord","crane");

//        setZCData(s.getString("HxzId"),body2,"/crane/warning","crane");

        //返回json字符串
        JSONObject results = new JSONObject();
        results.put("cmd", "RealtimeDataCrane");
        results.put("data", "{}");


        results.put("status", 0);//成功

        return results;
    }

    /**
     * 转换实时数据
     * @param data
     * @return
     */
    public JSONArray setCraneBody(JSONObject data) {
        JSONArray list = new JSONArray();
        JSONObject body = new JSONObject();
        body.put("device_no", Tools.encodeToMD5s(data.getString("HxzId")));//设备编号
        body.put("runtime", data.getString("RTime"));//时间
        body.put("load", data.getString("Weight"));//载重
        body.put("load_ratio", df2.format(Double.valueOf(data.getString("Weight")) / Double.valueOf(data.getString("RatedWeight"))));//载重比
        body.put("moment", df2.format(Double.valueOf(data.getString("Weight")) * Double.valueOf(data.getString("RRange"))));//力矩
        body.put("moment_ratio", data.getString("Moment"));//力矩比
        body.put("slewing_speed", data.getString("Angle"));//回转角度
        body.put("is_right_warning", data.getString("PosAngleAlarm"));//是否右限位报警(0.否1.是)
        body.put("is_left_warning", data.getString("NegAngleAlarm"));//是否左限位报警(0.否1.是)
        body.put("range", data.getString("RRange"));//幅度
        body.put("is_forward_warning", data.get("MinRangeAlarm"));//是否前限位报警(0.否1.是)
        body.put("is_backward_warning", data.getString("MaxRangeAlarm"));//是否后限位报警(0.否1.是)
        body.put("height", data.getString("Height"));//高度
        body.put("is_up_warning", data.getString("HeightAlarm"));//是否上限位报警(0.否1.是)

        body.put("wind_speed", data.getString("WindSpeed"));//风速 m/s
        body.put("collision_warn", data.getString("MultiAlarmAll"));//防碰撞报警（0不报警，1-报警）
        body.put("wind_warn", data.getString("WindSpeedAlarm"));//0/1 是否超风速作业报警（风速大于6级（大于13.8m/s） ，在连续两个工作循环工作,发生报警）。（0不报警，1-报警）
        body.put("rotation_warn", 0);//是否回转限位报警（0不报警，1-报警）

        SbCraneAddrecord sbCraneAddrecord = JSONObject.parseObject(body.toJSONString(), SbCraneAddrecord.class);
        sbCraneAddrecord.setHxzid(data.getString("HxzId"));
        sbCraneAddrecord.setRatedWeight(data.getDouble("RatedWeight"));
        sbCraneAddrecord.setMultiAlarmAll(Integer.parseInt(data.getString("MultiAlarmAll")));
        sbCraneAddrecordService.insertSbCraneAddrecord(sbCraneAddrecord);
        body.put("load", Double.valueOf(data.getString("Weight")) * 1000);
        list.add(body);
        sbCraneAddrecord.setLoad(Double.valueOf(data.getString("Weight")) * 1000);
        sendCraneToPERSONNEL.rcajDate(sbCraneAddrecord);
        //上传城安院
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(sbCraneAddrecord.getHxzid());
        sbCraneBinding.setScznl("CAY");
        List<SbCraneBinding> lists = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        if (lists.size() > 0) {
            sendCraneToPERSONNEL.cayDate(sbCraneAddrecord);
            sendCraneToPERSONNEL.cayWarning(sbCraneAddrecord);
        }
        return list;
    }

//    /** 上报城安院塔吊实时数据*/
//    public String tdsssjCay(JSONObject jsonObject) throws IOException, URISyntaxException {
//        Integer projectId = null;
//        String k = null;
//        SbCraneBinding sbCraneBinding = new SbCraneBinding();
//        sbCraneBinding.setHxzid(jsonObject.getString("HxzId"));
//        List<SbCraneBinding> list = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
//        HjProject hjProject = iHjProjectService.selectHjProjectById(list.get(0).getPid());
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("curpage","1");
//        jsonObject1.put("name",hjProject.getProjectName());
//        /** 区管项目*/
//        JSONObject jsonObject2 = ZCAPIClient.reportedCay2019s("authorize/getProjInfos",jsonObject1);
//        if (jsonObject2 != null) {
//            JSONObject object1 = new JSONObject();
//            object1.put("PGUID", jsonObject2.getString("xmid"));//所属项目编号
//            object1.put("Jdbh", jsonObject2.getString("jdbh"));//项目监督编号
//            object1.put("GUID",Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备号
//            object1.put("yxsk",jsonObject.getString("RTime"));//运行时刻
//            object1.put("zz",jsonObject.getInteger("Weight")*1000);//载重（KG)
//            object1.put("lj",df2.format(Double.valueOf(jsonObject.getString("Weight"))*Double.valueOf(jsonObject.getString("RRange"))));//力矩
//            object1.put("jd",jsonObject.getString("Angle"));//回转角度
//            object1.put("zxw",jsonObject.getString("NegAngleAlarm"));//是否左限位报警
//            object1.put("yxw",jsonObject.getString("PosAngleAlarm"));//是否右限位报警
//            object1.put("fd",jsonObject.getString("RRange"));//幅度
//            object1.put("qxw",jsonObject.get("MinRangeAlarm"));//是否前限位报警(0.否1.是)
//            object1.put("hxw",jsonObject.getString("MaxRangeAlarm"));//是否后限位报警(0.否1.是)
//            object1.put("gd",jsonObject.getString("Height"));//高度
//            object1.put("sxw",jsonObject.getString("HeightAlarm"));//是否上限位报警(0.否1.是)
//            object1.put("fs",jsonObject.getString("WindSpeed"));//风速
//            if (jsonObject.getInteger("PosAngleAlarm")==1 || jsonObject.getInteger("NegAngleAlarm")==1) {
//                object1.put("hzxw", "1");//是否回转限位报 警
//            }else {
//                object1.put("hzxw", "0");//是否回转限位报 警
//            }
//            JSONArray jsonArray = new JSONArray();
//            jsonArray.add(object1);
//            JSONObject object = new JSONObject();
//            object.put("PList",jsonArray);
//            k = ZCAPIClient.QGXMCAY("lifter/ele_par",object);
//        }
//        /** 市管项目*/
//        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos",jsonObject1);
//        if (xmid != null) {
//            //上报城安院升降机基本信息（开始）
//            JSONObject j = new JSONObject();
//            j.put("pguid", xmid);
//            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
//            JSONArray data = object.getJSONArray("res");
//            JSONObject datas = data.getJSONObject(0);
//            JSONObject json = new JSONObject();
//            json.put("PGUID", datas.getString("xmid"));//所属项目编号
//            json.put("Jdbh", datas.getString("jdbh"));//项目监督编号
//            json.put("GUID",Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备号
//            json.put("yxsk",jsonObject.getString("RTime"));//运行时刻
//            json.put("zz",jsonObject.getInteger("Weight")*1000);//载重
//            json.put("lj",df2.format(Double.valueOf(jsonObject.getString("Weight"))*Double.valueOf(jsonObject.getString("RRange"))));//力矩
//            json.put("ljb",jsonObject.getString("Moment"));//力矩比
//            json.put("jd",jsonObject.getString("Angle"));//回转角度
//            json.put("zxw",jsonObject.getString("NegAngleAlarm"));//是否左限位报警
//            json.put("yxw",jsonObject.getString("PosAngleAlarm"));//是否右限位报警
//            json.put("fd",jsonObject.getString("RRange"));//幅度
//            json.put("qxw",jsonObject.get("MinRangeAlarm"));//是否前限位报警(0.否1.是)
//            json.put("hxw",jsonObject.getString("MaxRangeAlarm"));//是否后限位报警(0.否1.是)
//            json.put("gd",jsonObject.getString("Height"));//高度
//            json.put("sxw",jsonObject.getString("HeightAlarm"));//是否上限位报警(0.否1.是)
//            json.put("fs",jsonObject.getString("WindSpeed"));//风速
//            if (jsonObject.getInteger("PosAngleAlarm")==1 || jsonObject.getInteger("NegAngleAlarm")==1) {
//                json.put("hzxw", "1");//是否回转限位报 警
//            }else {
//                json.put("hzxw", "0");//是否回转限位报 警
//            }
//            json.put("fpz",jsonObject.getInteger("MultiAlarmAll"));//防碰撞报警
//
//            SbCraneWorkloop sbCraneWorkloop = iSbCraneWorkloopService.selectTD(jsonObject.getString("hxzid"));
//            if (jsonObject.getInteger("WindSpeed")>= 13.8 && sbCraneWorkloop.getMaxWindSpeed()>13.8) {
//                json.put("cfszy", "1");//是否超风速作业 报警（风速大于6 级（大于13.8 m/s），在连续两个工作循环工作，发生报警）
//            }else {
//                json.put("cfszy", "0");//是否超风速作业 报警（风速大于6 级（大于13.8 m/s），在连续两个工作循环工作，发生报警）
//            }
//            json.put("sub_id",datas.getString("gcid"));//工程ID
//            JSONArray array = new JSONArray();
//            array.add(json);
//            JSONObject object1 = new JSONObject();
//            object1.put("PList",array);
//            k = ZCAPIClient.QGXMCAY("tower/tow_t_data",object1);
//        }
//        return k;
//    }

    /**
     * 转换预警数据
     * @param data
     * @return
     */
    public JSONArray setWarningBody(JSONObject data) {
        JSONArray list = new JSONArray();
        JSONObject body = new JSONObject();
        body.put("device_no", Tools.encodeToMD5s(data.getString("HxzId")));//设备号
        body.put("runtime", data.getString("RTime"));//运行时间
        body.put("load", data.getString("Weight"));//载重
        body.put("load_ratio", df.format(Double.valueOf(data.getString("Weight")) / Double.valueOf(data.getString("RatedWeight"))));//载重比
        body.put("moment", df2.format(Double.valueOf(data.getString("Weight")) * Double.valueOf(data.getString("RRange"))));//力矩
        body.put("moment_ratio", data.getString("Moment"));//力矩比
        body.put("slewing_speed", data.getString("Angle"));//回转角度
        body.put("height", data.getString("Height"));//高度
        body.put("range", data.getString("RRange"));//幅度

        body.put("magnification", data.getString("Multiple"));//倍率
        body.put("sensor_status", "0".equals(data.getString("NoError")) ? "2" : "1");//传感器状态
        body.put("wind_speed", data.getString("WindSpeed"));//风速
        body.put("normal_work_warn_status", "0".equals(data.getString("NoAlarm")) ? "2" : "1");//正常工作报警状态
        body.put("region_limit_warn_status", "0".equals(data.getString("ForbidEntryAlarm")) ? "1" : "1".equals(data.getString("ForbidEntryAlarm")) ? "2" : "");//区域限制报警状态

        SbCraneWarning sbCraneWarning = JSONObject.parseObject(body.toJSONString(), SbCraneWarning.class);
        sbCraneWarning.setHxzid(data.getString("HxzId"));
        sbCraneWarningService.insertSbCraneWarning(sbCraneWarning);
        body.put("load", Double.valueOf(data.getString("Weight")) * 1000);
        list.add(body);
        return list;
    }

    /** 转换为上传城安院塔吊预警数据*/
//    public String tdyjCay(JSONObject jsonObject) throws IOException, URISyntaxException {
//        Integer projectId = null;
//        String k = null;
//        SbCraneBinding sbCraneBinding = new SbCraneBinding();
//        sbCraneBinding.setHxzid(jsonObject.getString("HxzId"));
//        List<SbCraneBinding> list = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
//        HjProject hjProject = iHjProjectService.selectHjProjectById(list.get(0).getPid());
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("curpage","1");
//        jsonObject1.put("name",hjProject.getProjectName());
//        /** 市管项目*/
//        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos",jsonObject1);
//        JSONArray array = new JSONArray();
//        if (xmid != null) {
//            //上报城安院升降机基本信息（开始）
//            JSONObject j = new JSONObject();
//            j.put("pguid", xmid);
//            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
//            JSONArray data = object.getJSONArray("res");
//            JSONObject datas = data.getJSONObject(0);
//            JSONObject json = new JSONObject();
//            json.put("PGUID",datas.getString("xmid"));//所属项目编号
//            json.put("Jdbh",datas.getString("jdbh"));//项目监督编号
//            json.put("GUID",Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备号
//            json.put("zz",jsonObject.getString("Weight"));//载重
//            json.put("lj",df2.format(Double.valueOf(jsonObject.getString("Weight"))*Double.valueOf(jsonObject.getString("RRange"))));//力矩
//            json.put("jd",jsonObject.getString("Angle"));//回转角度
//            json.put("fd",jsonObject.getString("RRange"));//幅度
//            json.put("gd",jsonObject.getString("Height"));//高度
//            json.put("fs",jsonObject.getString("WindSpeed"));//风速
//            json.put("sub_id",datas.getString("gcid"));//工程ID
//            array.add(json);
//            JSONObject jsonObject2 = new JSONObject();
//            jsonObject2.put("PList",array);
//            k = ZCAPIClient.QGXMCAY("tower/tow_Warning",jsonObject2);
//        }
//        /** 区管项目*/
//        JSONObject jsonObject2 = ZCAPIClient.reportedCay2019s("authorize/getProjInfos",jsonObject1);
//        JSONArray jsonArray = new JSONArray();
//        if (jsonObject2 != null) {
//            JSONObject object1 = new JSONObject();
//            object1.put("PGUID", jsonObject2.getString("xmid"));//所属项目编号
//            object1.put("Jdbh", jsonObject2.getString("jdbh"));//项目监督编号
//            object1.put("GUID", Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备号
//            object1.put("zz",jsonObject.getString("Weight"));//载重
//            object1.put("lj",df2.format(Double.valueOf(jsonObject.getString("Weight"))*Double.valueOf(jsonObject.getString("RRange"))));//力矩
//            object1.put("jd",jsonObject.getString("Angle"));//回转角度
//            object1.put("fd",jsonObject.getString("RRange"));//幅度
//            object1.put("gd",jsonObject.getString("Height"));//高度
//            object1.put("fs",jsonObject.getString("WindSpeed"));//风速
//            jsonArray.add(object1);
//            JSONObject object = new JSONObject();
//            object.put("PList",jsonArray);
//            k = ZCAPIClient.QGXMCAY("tower/tow_Warning",object);
//        }
//        return k;
//    }


    /**
     * 2.4上报深圳版塔机报警数据
     */

    @RequestMapping(value = "/ShenZhenAlarmDataCrane", method = RequestMethod.POST)
    public JSONObject shenZhenAlarmDataCrane(@RequestBody String json) throws Exception {
        //返回json字符串
        JSONObject results = new JSONObject();
        results.put("cmd", "ShenZhenAlarmDataCrane");
        results.put("data", "{}");

        results.put("status", 0);//成功

        return results;
    }

    /**
     * 2.5上报GPS定位数据
     */

    @RequestMapping(value = "/GpsData", method = RequestMethod.POST)
    public JSONObject gpsData(@RequestBody String json) throws Exception {
        //返回json字符串
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonData = JSONObject.parseObject(json);
        //塔吊
        SbCraneBasicinfo sbCraneBasicinfo = new SbCraneBasicinfo();
        sbCraneBasicinfo.setHxzid(jsonData.getString("HxzId"));
        List<SbCraneBasicinfo> sbCraneBasicinfoList = sbCraneBasicinfoService.selectSbCraneBasicinfoList(sbCraneBasicinfo);
        if (sbCraneBasicinfoList.size() > 0) {
            SbCraneLocatordata sbCraneLocatordata = new SbCraneLocatordata();
            sbCraneLocatordata.setHxzId(jsonData.getString("HxzId"));
            sbCraneLocatordata.setDeviceNo(Tools.encodeToMD5s(jsonData.getString("HxzId")));
            sbCraneLocatordata.setLatitude(jsonData.getString("Latitude"));
            sbCraneLocatordata.setLongitude(jsonData.getString("Longitude"));
            int result = craneLocatordataService.insertSbCraneLocatordata(sbCraneLocatordata);
            if (result > 0) {
                jsonObject.put("cmd", "GpsData");
                jsonObject.put("data", "{}");
            }
        }
        //卸料
        SbUnloaderRegistration sbUnloaderRegistration = new SbUnloaderRegistration();
        sbUnloaderRegistration.setHxzId(jsonData.getString("HxzId"));
        List<SbUnloaderRegistration> sbUnloaderRegistrationList = registrationService.selectSbUnloaderRegistrationList(sbUnloaderRegistration);

        if (sbUnloaderRegistrationList.size() > 0) {
            SbUnloaderLocatordata sbUnloaderLocatordata = new SbUnloaderLocatordata();
            sbUnloaderLocatordata.setHxzId(jsonData.getString("HxzId"));
            sbUnloaderLocatordata.setDeviceNo(Tools.encodeToMD5s(jsonData.getString("HxzId")));
            sbUnloaderLocatordata.setLatitude(jsonData.getString("Latitude"));
            sbUnloaderLocatordata.setLongitude(jsonData.getString("Longitude"));
            unloaderLocatordataService.insertSbUnloaderLocatordata(sbUnloaderLocatordata);
            jsonObject.put("cmd", "GpsData");
            jsonObject.put("data", "{}");
        }
        //升降机
        SbElevatorAddbasicinfo sbElevatorAddbasicinfo = new SbElevatorAddbasicinfo();
        sbElevatorAddbasicinfo.setHxzid(jsonData.getString("HxzId"));
        List<SbElevatorAddbasicinfo> sbElevatorAddbasicinfoList = sbElevatorAddbasicinfoService.selectSbElevatorAddbasicinfoList(sbElevatorAddbasicinfo);
        if (sbElevatorAddbasicinfoList.size() > 0) {
            SbElevatorLocatordata sbElevatorLocatordata = new SbElevatorLocatordata();
            sbElevatorLocatordata.setHxzId(jsonData.getString("HxzId"));
            sbElevatorLocatordata.setDeviceNo(Tools.encodeToMD5s(jsonData.getString("HxzId")));
            sbElevatorLocatordata.setLatitude(jsonData.getString("Latitude"));
            sbElevatorLocatordata.setLongitude(jsonData.getString("Longitude"));
            int result = elevatorLocatordataService.insertSbElevatorLocatordata(sbElevatorLocatordata);
            if (result > 0) {
                jsonObject.put("cmd", "GpsData");
                jsonObject.put("data", "{}");
            }
        }

        jsonObject.put("cmd", "GpsData");
        jsonObject.put("data", "{}");
        jsonObject.put("status", 0);//成功

        return jsonObject;
    }

    /**
     * 2.6上报设备运行时长
     */

    @RequestMapping(value = "/RuntimeData", method = RequestMethod.POST)
    public JSONObject runtimeData(@RequestBody String json) throws Exception {
        JSONObject s = JSONObject.parseObject(json);
        SbUnloaderRegistration sbUnloaderRegistration = new SbUnloaderRegistration();
        sbUnloaderRegistration.setHxzId(s.getString("HxzId"));
        List<SbUnloaderRegistration> sbUnloaderRegistrationList = registrationService.selectSbUnloaderRegistrationList(sbUnloaderRegistration);
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(s.getString("HxzId"));
        List<SbCraneBinding> scList = sbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        //塔吊
        if (scList.size() > 0) {
            SbCraneBinding sbCraneBinding1 = new SbCraneBinding();
            sbCraneBinding1.setHxzid(s.getString("HxzId"));
            sbCraneBinding1.setScznl("CAY");
            List<SbCraneBinding> list = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding1);
            if (list.size() > 0) {
//                ThreadUtils.async(new Runnable(){
//                    @Override
//                    public void run() {
//                        try {
//                            tdtdscCay(s);
//                        } catch (IOException e) {
//                            logger.error("城安院错误塔吊(RuntimeData): " + e.getMessage() + ", 参数错误："+s);
//                        } catch (URISyntaxException e) {
//                            logger.error("城安院错误塔吊(RuntimeData): " + e.getMessage() + ", 参数错误："+s);
//                        }
//                    }
//                });
                SbCraneAddrecord sbCraneAddrecord = new SbCraneAddrecord();
                sbCraneAddrecord.setHxzid(s.getString("Hxzid"));
                sbCraneAddrecord.setDownlineTime(s.getString("DownlineTime"));
                sbCraneAddrecord.setRuntime(s.getString("OnlineTime"));
                sendCraneToPERSONNEL.cayElectrify(sbCraneAddrecord);
            }
            JSONArray body2 = electrifyCrane(s, "crane");
            setZCData(s.getString("HxzId"), body2, "/crane/electrify", "crane");
        }
        //卸料
        else if (sbUnloaderRegistrationList.size() > 0) {
            electrifyCrane(s, "unloader");
        }
        //升降机
        else {
            SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
            sbElevatorBinding.setHxzid(s.getString("HxzId"));
            sbElevatorBinding.setScznl("CAY");
            List<SbElevatorBinding> list = iSbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
            if (list.size() > 0) {
//                ThreadUtils.async(new Runnable(){
//                    @Override
//                    public void run() {
//                        try {
//                            tdtdscCay(s);
//                        } catch (IOException e) {
//                            logger.error("城安院错误升降机(RuntimeData): " + e.getMessage() + ", 参数错误："+s);
//                        } catch (URISyntaxException e) {
//                            logger.error("城安院错误升降机(RuntimeData): " + e.getMessage() + ", 参数错误："+s);
//                        }
//                    }
//                });
                SbElevatorAddrecord sbElevatorAddrecord = new SbElevatorAddrecord();
                sbElevatorAddrecord.setHxzid(s.getString("Hxzid"));
                sbElevatorAddrecord.setDownlineTime(s.getString("DownlineTime"));
                sendElevatorToPERSONNEL.cayElectrify(sbElevatorAddrecord);

            }
            JSONArray body2 = electrifyCrane(s, "elevator");
            setZCData(s.getString("HxzId"), body2, "/elevator/electrify", "elevator");
        }
        //返回json字符串
        JSONObject results = new JSONObject();
        results.put("cmd", "RuntimeData");
        results.put("data", "{}");

        results.put("status", 0);//成功

        return results;
    }

//    /** 上报塔吊通电时间接口 (城安院) */
//    public String tdtdscCay(JSONObject jsonObject) throws IOException, URISyntaxException {
//        Integer projectId = null;
//        String k = null;
//        SbCraneBinding sbCraneBinding = new SbCraneBinding();
//        sbCraneBinding.setHxzid(jsonObject.getString("HxzId"));
//        List<SbCraneBinding> list = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
//        HjProject hjProject = iHjProjectService.selectHjProjectById(list.get(0).getPid());
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("curpage","1");
//        jsonObject1.put("name",hjProject.getProjectName());
//        JSONArray body =new JSONArray();
//        JSONObject js1=new JSONObject();
//        if(StringUtils.isBlank(jsonObject.getString("DownlineTime"))){
//            js1.put("device_no",Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备号
//            js1.put("runtime",jsonObject.getString("OnlineTime"));//运行时刻
//            js1.put("operation","1");//事件类型（0 断电，1 通电）
//        }else {
//            js1.put("device_no",Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备号
//            js1.put("runtime",jsonObject.getString("DownlineTime"));//运行时刻
//            js1.put("operation","0");//事件类型（0 断电，1 通电）
//        }
//        /** 市管项目*/
//        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos",jsonObject1);
//        JSONArray array = new JSONArray();
//        if (xmid != null) {
//            //上报城安院升降机基本信息（开始）
//            JSONObject j = new JSONObject();
//            j.put("pguid", xmid);
//            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
//            JSONArray data = object.getJSONArray("res");
//            JSONObject datas = data.getJSONObject(0);
//            js1.put("PGUID", datas.getString("xmid"));//所属项目编号
//            js1.put("Jdbh", datas.getString("jdbh"));//项目监督编号
//            js1.put("sub_id", datas.getString("gcid"));//工程ID
//            array.add(js1);
//            JSONObject jsonObject2 = new JSONObject();
//            jsonObject2.put("PList",array);
//            k = ZCAPIClient.QGXMCAY("tower_t_state",jsonObject2);
//        }
//        /** 区管项目*/
//        JSONObject jsonObject2 = ZCAPIClient.reportedCay2019s("authorize/getProjInfos",jsonObject1);
//        JSONArray jsonArray = new JSONArray();
//        if (jsonObject2 != null) {
//            JSONObject object1 = new JSONObject();
//            object1.put("PGUID", jsonObject2.getString("xmid"));//所属项目编号
//            object1.put("Jdbh", jsonObject2.getString("jdbh"));//项目监督编号
//            jsonArray.add(object1);
//            JSONObject object = new JSONObject();
//            object.put("PList",jsonArray);
//            k = ZCAPIClient.QGXMCAY("tower/tower_t_state",object);
//        }
//        return k;
//    }

    /**
     * 2.7上报塔机工作循环数据
     */

    @RequestMapping(value = "/WorkDataCrane", method = RequestMethod.POST)
    public JSONObject workDataCrane(@RequestBody String json) throws Exception {
        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);
        //将塔吊实时数据有json转换为对象

        JSONArray body = workLoop(s);
        setZCData(s.getString("HxzId"), body, "crane/workLoop", "crane");

        SbCraneBinding sbCraneBinding1 = new SbCraneBinding();
        sbCraneBinding1.setHxzid(s.getString("HxzId"));
        sbCraneBinding1.setScznl("CAY");
        List<SbCraneBinding> list = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding1);
        if (list.size() > 0) {
//            ThreadUtils.async(new Runnable(){
//                @Override
//                public void run() {
//                    try {
//                        tdgzxhCay(s);
//                    } catch (IOException e) {
//                        logger.error("城安院错误(WorkDataCrane): " + e.getMessage() + ", 参数错误："+s);
//                    } catch (URISyntaxException e) {
//                        logger.error("城安院错误(WorkDataCrane): " + e.getMessage() + ", 参数错误："+s);
//                    }
//                }
//            });
            SbCraneAddrecord sbCraneAddrecord = new SbCraneAddrecord();
            sbCraneAddrecord.setHxzid(s.getString("Hxzid"));
            sbCraneAddrecord.setWorkEndTime(s.getString("WorkEndTime"));
            sbCraneAddrecord.setWorkMaxHeight(String.valueOf(Double.valueOf(Float.valueOf(s.getString("WorkMaxHeight"))) / 100));
            sendCraneToPERSONNEL.cayWorkCycle(sbCraneAddrecord);

        }

        JSONObject results = new JSONObject();
        results.put("cmd", "WorkDataCrane");
        results.put("data", "{}");

        results.put("status", 0);//成功

        return results;
    }

//    /** 上报城安院塔吊工作循环*/
//    public String tdgzxhCay(JSONObject jsonObject) throws IOException, URISyntaxException {
//        Integer projectId = null;
//        String k = null;
//        SbCraneBinding sbCraneBinding = new SbCraneBinding();
//        sbCraneBinding.setHxzid(jsonObject.getString("HxzId"));
//        List<SbCraneBinding> list = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
//        HjProject hjProject = iHjProjectService.selectHjProjectById(list.get(0).getPid());
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("curpage","1");
//        jsonObject1.put("name",hjProject.getProjectName());
//        /** 市管项目*/
//        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos",jsonObject1);
//        JSONArray array = new JSONArray();
//        if (xmid != null) {
//            //上报城安院升降机基本信息（开始）
//            JSONObject j = new JSONObject();
//            j.put("pguid", xmid);
//            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
//            JSONArray data = object.getJSONArray("res");
//            JSONObject datas = data.getJSONObject(0);
//            JSONObject json = new JSONObject();
//            json.put("PGUID",datas.getString("xmid"));//所属项目编号
//            json.put("Jdbh",datas.getString("jdbh"));//项目监督编号
//            json.put("sub_id",datas.getString("gcid"));//工程ID
//            json.put("GUID",Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备号
//            json.put("Yxsk_E",jsonObject.getString("WorkEndTime"));//工作循环结束时刻
//            json.put("ljb",Double.valueOf(Float.valueOf(jsonObject.getString("WorkMaxHeight")))/100);//工作循环中最高力矩比
//            array.add(json);
//            JSONObject jsonObject2 = new JSONObject();
//            jsonObject2.put("PList",array);
//            k = ZCAPIClient.QGXMCAY("tower/tow_cycle",jsonObject2);
//        }
//        /** 区管项目*/
//        JSONObject jsonObject2 = ZCAPIClient.reportedCay2019s("authorize/getProjInfos",jsonObject1);
//        JSONArray jsonArray = new JSONArray();
//        if (jsonObject2 != null) {
//            JSONObject object1 = new JSONObject();
//            object1.put("PGUID", jsonObject2.getString("xmid"));//所属项目编号
//            object1.put("Jdbh", jsonObject2.getString("jdbh"));//项目监督编号
//            object1.put("GUID", Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备号
//            object1.put("Yxsk_E",jsonObject.getString("WorkEndTime"));//工作循环结束时刻
//            object1.put("ljb",Double.valueOf(Float.valueOf(jsonObject.getString("WorkMaxHeight")))/100);//工作循环中最高力矩比
//            jsonArray.add(object1);
//            JSONObject object = new JSONObject();
//            object.put("PList",jsonArray);
//            k = ZCAPIClient.QGXMCAY("tower/tow_cycle",object);
//        }
//        return k;
//    }

    /**
     * 2.8上报塔机报警数据
     */

    @RequestMapping(value = "/AlarmDataCrane", method = RequestMethod.POST)
    public JSONObject alarmDataCrane(@RequestBody String json) throws Exception {
        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);
        //将塔吊实时数据有json转换为对象
        JSONObject results = new JSONObject();
        results.put("cmd", "AlarmDataCrane");
        results.put("data", "{}");
        results.put("status", 0);//成功
        return results;
    }

    /**
     * 2.9上报司机打卡记录信息
     */

    @RequestMapping(value = "/OperatorRecord", method = RequestMethod.POST)
    public JSONObject operatorRecord(@RequestBody String json) throws Exception {
        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);
        /** 对接城安院司机打卡记录信息*/

        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setHxzid(s.getString("HxzId"));
        sbElevatorBinding.setScznl("CAY");
        List<SbElevatorBinding> list = iSbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
        SbCraneBinding sbCraneBinding1 = new SbCraneBinding();
        sbCraneBinding1.setHxzid(s.getString("HxzId"));
        sbCraneBinding1.setScznl("CAY");
        List<SbCraneBinding> list1 = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding1);
//        if (list.size()>0 || list1.size()>0) {
//            ThreadUtils.async(new Runnable(){
//                @Override
//                public void run() {
//                    try {
//                        sjdkCay(s);
//                    } catch (IOException e) {
//                        logger.error("城安院错误(OperatorRecord): " + e.getMessage() + ", 参数错误："+s);
//                    } catch (URISyntaxException e) {
//                        logger.error("城安院错误(OperatorRecord): " + e.getMessage() + ", 参数错误："+s);
//                    }
//                }
//            });
        if (list1.size() > 0) {
            SbCraneAddrecord sbCraneAddrecord = new SbCraneAddrecord();
            sbCraneAddrecord.setHxzid(Tools.encodeToMD5s(s.getString("HxzId")));
            sbCraneAddrecord.setOperatorName(s.getString("workerName"));
            sbCraneAddrecord.setPunchTime(s.getString("RunchTime"));
            sbCraneAddrecord.setClosingTime(s.getString("ClosingTime"));
            sendCraneToPERSONNEL.cayPunchTheClock(sbCraneAddrecord);
        }
        if (list.size() > 0) {
            SbElevatorAddrecord sbElevatorAddrecord = new SbElevatorAddrecord();
            sbElevatorAddrecord.setHxzid(Tools.encodeToMD5s(s.getString("Hxzid")));
            sbElevatorAddrecord.setOperatorName(s.getString("workerName"));
            sbElevatorAddrecord.setPunchTime(s.getString("RunchTime"));
            sbElevatorAddrecord.setClosingTime(s.getString("ClosingTime"));
            sendElevatorToPERSONNEL.cayPunchTheClock(sbElevatorAddrecord);
        }
//        }
        //将塔吊实时数据有json转换为对象
        JSONObject results = new JSONObject();
        results.put("cmd", "OperatorRecord");
        results.put("data", "{}");
        results.put("status", 0);//成功
        return results;
    }

    /**
     * @Author xieya
     * @Description 上报心跳数据
     * @Date 2020/3/27 19:09
     * @param json
     * @return com.alibaba.fastjson.JSONObject
     **/
    @RequestMapping(value = "/heartBeatDataCrane", method = RequestMethod.POST)
    public JSONObject heartBeatDataCrane(@RequestBody String json) {
        //根据id查询   没有插入   有就跟新
        SbCraneHeart sbCraneHeart = JSON.parseObject(json, SbCraneHeart.class);
        String hxzId = sbCraneHeart.getHxzId();
        SbCraneHeart sbCraneHeart1 = sbCraneHeartService.selectByHxzId(hxzId);
        if (isEmpty(sbCraneHeart1)) {
            int add = sbCraneHeartService.insertSbCraneHeart(sbCraneHeart);
            if (add == 1) {
                logger.info("插入成功");
            }
        } else {
            int udt = sbCraneHeartService.updateSbCraneHeart(sbCraneHeart);
            if (udt == 1) {
                logger.info("插入成功");
            }
        }

        return null;
    }

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    @RequestMapping(value = "/alarmChangeDataCrane", method = RequestMethod.POST)
    public JSONObject AlarmChangeDataCrane(@RequestBody String json) throws Exception {
        //根据id查询   没有插入   有就跟新
        SbCraneAlarmChangeDataCrane sbCraneAlarmChangeDataCrane = JSON.parseObject(json, SbCraneAlarmChangeDataCrane.class);
        String hxzId = sbCraneAlarmChangeDataCrane.getHxzId();
        SbCraneAlarmChangeDataCrane sbCraneAlarmChangeDataCrane1 = isbCraneAlarmChangeDataCraneService.selectByHxzId(hxzId);

        if (isEmpty(sbCraneAlarmChangeDataCrane1)) {
            int add = isbCraneAlarmChangeDataCraneService.insertSbCraneAlarmChangeDataCrane(sbCraneAlarmChangeDataCrane);
            if (add == 1) {
                logger.info("插入成功");
            }
        } else {
            int udt = isbCraneAlarmChangeDataCraneService.updateSbCraneAlarmChangeDataCrane(sbCraneAlarmChangeDataCrane);
            if (udt == 1) {
                logger.info("插入成功");
            }
        }

        return null;
    }

    /**
     * 上传工作循环body数据
     * @param workdatacrane
     * @return
     */
    public JSONArray workLoop(JSONObject workdatacrane) {
        JSONArray body = new JSONArray();
        JSONObject js = new JSONObject();
        js.put("device_no", Tools.encodeToMD5s(workdatacrane.getString("HxzId")));//设备号
        js.put("start_time", workdatacrane.getString("WorkStartTime"));//开始时间
        js.put("end_time", workdatacrane.getString("WorkEndTime"));//结束时间
        js.put("max_moment_ratio", workdatacrane.getString("WorkMaxTorque"));//最高力矩比
//        js.put("max_wind_speed",workdatacrane.getString("WorkMaxWindSpeed"));//最大风速
//        js.put("max_height",workdatacrane.getString("WorkMaxHeight"));//最大高度
//        js.put("max_range",workdatacrane.getString("WorkMaxRange"));//最大幅度
        js.put("is_over_wind_speed", "1".equals(workdatacrane.getString("WorkWindSpeedAlarm")) ? "1" : "0");

        SbCraneWorkloop sbCraneWorkloop = JSONObject.parseObject(js.toJSONString(), SbCraneWorkloop.class);
        sbCraneWorkloop.setHxzid(workdatacrane.getString("HxzId"));
        sbCraneWorkloopService.insertSbCraneWorkloop(sbCraneWorkloop);
        body.add(js);
        return body;

    }

    /**
     * 上传塔机通电时间body数据
     */
    public JSONArray electrifyCrane(JSONObject workdatacrane, String type) {
        JSONArray body = new JSONArray();
        JSONObject js1 = new JSONObject();
        if (StringUtils.isBlank(workdatacrane.getString("DownlineTime"))) {
            js1.put("device_no", Tools.encodeToMD5s(workdatacrane.getString("HxzId")));//设备号
            js1.put("runtime", workdatacrane.getString("OnlineTime"));//运行时刻
            js1.put("operation", "1");//事件类型（0 断电，1 通电）
        } else {
            js1.put("device_no", Tools.encodeToMD5s(workdatacrane.getString("HxzId")));//设备号
            js1.put("runtime", workdatacrane.getString("DownlineTime"));//运行时刻
            js1.put("operation", "0");//事件类型（0 断电，1 通电）
        }
        if ("crane".equals(type)) {
            SbCraneElectrify sbCraneElectrify1 = JSONObject.parseObject(js1.toJSONString(), SbCraneElectrify.class);
            sbCraneElectrify1.setHxzid(workdatacrane.getString("HxzId"));
            sbCraneElectrifyService.insertSbCraneElectrify(sbCraneElectrify1);

        }
        if ("unloader".equals(type)) {
            SbUnloaderEquipment sbUnloaderEquipment = JSONObject.parseObject(js1.toJSONString(), SbUnloaderEquipment.class);
            sbUnloaderEquipment.setHxzid(workdatacrane.getString("HxzId"));
            unloaderEquipmentService.insertSbUnloaderEquipment(sbUnloaderEquipment);
        }
        if ("elevator".equals(type)) {
            SbElevatorElectrify sbElevatorElectrify = JSONObject.parseObject(js1.toJSONString(), SbElevatorElectrify.class);
            sbElevatorElectrify.setHxzid(workdatacrane.getString("HxzId"));
            sbElevatorElectrifyService.insertSbElevatorElectrify(sbElevatorElectrify);
        }
        body.add(js1);

        return body;
    }

    /**
     * 实时数据上传
     * @param
     */
    public void setZCData(String hxzId, JSONArray body, String url, String type) throws IOException, URISyntaxException {
        HjSynchronizationInformation sk;
        //塔吊数据推送
        if ("crane".equals(type)) {
            SbCraneBinding bd = new SbCraneBinding();
            bd.setIsSynchronization("1");
            bd.setHxzid(hxzId);
            List<SbCraneBinding> bList = sbCraneBindingService.selectSbCraneBindingList(bd);
            for (int i = 0; i < bList.size(); i++) {
                Integer pid = bList.get(i).getPid();
                sk = new HjSynchronizationInformation();
                sk.setProjectId(pid);
                sk.setPlatformName("WORKSBUREAU");
                sk.setState(1);
                sk.setApiType("keytype5");


                List<HjSynchronizationInformation> sList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(sk);
                if (sList.size() > 0) {
                    HjSynchronizationInformation s = sList.get(0);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("body", body);
                    String object = ZCAPIClient.reportedData2019("crane/addRecord", jsonObject);
                    JSONObject json = new JSONObject();
                    json.put("api_key", s.getApiKey());
                    json.put("api_version", "1.0");
                    json.put("timestamp", s.getCreateDate());//时间戳
                    json.put("project_code", s.getProjectNumber());//项目编码
                    json.put("eng_code", s.getEngineeringCode());//工程编码
                    json.put("signature", "47d92c9c970c45289f5800d4bd4a5c52");//对API输入参数进行MD5加密获得
                    json.put("body", body);
                    ZCAPIClient.reportedData2019(url, json);
                }
            }
        } else {
            //升降机数据推送
            SbElevatorBinding bd = new SbElevatorBinding();
            bd.setIsSynchronization("1");
            bd.setHxzid(hxzId);
            List<SbElevatorBinding> bList = sbElevatorBindingService.selectSbElevatorBindingList(bd);
            for (int i = 0; i < bList.size(); i++) {
                Integer pid = bList.get(i).getPid();
                sk = new HjSynchronizationInformation();
                sk.setProjectId(pid);
                sk.setPlatformName("WORKSBUREAU");
                sk.setState(1);
                sk.setApiType("keytype6");

                List<HjSynchronizationInformation> sList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(sk);
                if (sList.size() > 0) {
                    HjSynchronizationInformation s = sList.get(0);
                    JSONObject json = new JSONObject();
                    json.put("api_key", s.getApiKey());
                    json.put("api_version", "1.0");
                    json.put("timestamp", s.getCreateDate());//时间戳
                    json.put("project_code", s.getProjectNumber());//项目编码
                    json.put("eng_code", s.getEngineeringCode());//工程编码
                    json.put("signature", "47d92c9c970c45289f5800d4bd4a5c52");//对API输入参数进行MD5加密获得
                    json.put("body", body);
                    ZCAPIClient.reportedData2019(url, json);

                }
            }

        }

    }
    //================================升降机===============================================

    /**
     * 升降机注册帧
     */
    @RequestMapping(value = "/LoginDataElevator", method = RequestMethod.POST)
    public JSONObject loginDataElevator(@RequestBody String json) throws Exception {

        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);


        SbElevatorAddbasicinfo sbElevatorAddbasicinfo = new SbElevatorAddbasicinfo();
        sbElevatorAddbasicinfo.setHxzid(s.getString("HxzId"));
        List<SbElevatorAddbasicinfo> sbList = sbElevatorAddbasicinfoService.selectSbElevatorAddbasicinfoList(sbElevatorAddbasicinfo);
        if (sbList.size() <= 0) {
            sbElevatorAddbasicinfo.setMonDeviceMan(s.getString("HxzFactory"));
            sbElevatorAddbasicinfo.setDeviceNo(Tools.encodeToMD5s(s.getString("HxzId")));
            sbElevatorAddbasicinfoService.insertSbElevatorAddbasicinfo(sbElevatorAddbasicinfo);
        }

        //升降机注册响应帧
        JSONObject result = new JSONObject();
        result.put("cmd", "LoginDataElevator");
        result.put("data", "{}");
        //是否配置升降机基础信息

        Date date = new Date();
        result.put("status", 0);//成功
        JSONObject data = new JSONObject();

        data.put("HxzFactory", s.getString("HxzFactory"));
        data.put("HxzId", s.getString("HxzId"));
        data.put("RecordId", s.getString("HxzId"));
        data.put("ServerTime", dateFormat.format(date.getTime()));
        data.put("HeartBeatInterval", "30");
        data.put("WorkInterval", "30");
        data.put("NoWorkInterval", "60");
        data.put("ErrorDelay", "90");
        data.put("LockFlag", "0");
        data.put("LeaseFlag", "0");
        data.put("LeaseStartDate", "2000-01-01");
        data.put("LeaseEndDate", "2099-01-01");
        data.put("IdSetError", "0");
        data.put("PeopleCntSetError", "0");
        data.put("WeightSetError", "0");
        data.put("SpeedSetError", "0");
        data.put("HeightSetError", "0");
        data.put("FloorSetError", "0");
        data.put("ObliguityXSetError", "0");
        data.put("ObliguityYSetError", "0");
        data.put("WindSpeedSetError", "0");
        data.put("GpsSetError", "0");
        data.put("WirelessSetError", "0");
        data.put("LeasePhone", "13000000000");
        data.put("StationPhone", "13000000000");
        data.put("WorkPhone", "13000000000");
        data.put("ServerIp", "47.106.71.3");
        data.put("ServerPort", "8080");


        result.put("data", data);

        return result;
    }


    /**
     *上报升降机基础参数
     */
    @RequestMapping(value = "/BaseDataElevator", method = RequestMethod.POST)
    public JSONObject baseDataElevator(@RequestBody String json) throws Exception {

        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);

        SbElevatorAddparams sbElevatorAddparams = new SbElevatorAddparams();
        sbElevatorAddparams.setHxzid(s.getString("HxzId"));
        List<SbElevatorAddparams> sbList = sbElevatorAddparamsService.selectSbElevatorAddparamsList(sbElevatorAddparams);
        if (sbList == null || sbList.size() == 0) {
            sbElevatorAddparams.setDeviceNo(Tools.encodeToMD5s(s.getString("HxzId")));
            sbElevatorAddparams.setName("升降机");
            sbElevatorAddparams.setLLoadCapacity(s.getDouble("RatedWeight"));//最大载重
            sbElevatorAddparams.setLHeight(s.getDouble("RatedHeight"));//最大高度
            sbElevatorAddparamsService.insertSbElevatorAddparams(sbElevatorAddparams);
        }

        //返回json字符串
        JSONObject result = new JSONObject();
        result.put("cmd", "BaseDataElevator");
        result.put("data", "{}");
        result.put("status", 0);//成功

        return result;
    }

    /**
     * 接收升降机实时信息
     */
    @RequestMapping(value = "/RealtimeDataElevator", method = RequestMethod.POST)
    public JSONObject realtimeDataElevator(@RequestBody String json) throws Exception {

        logger.info("接收德业升降机数据开始json=" + json);
        JSONObject s = JSONObject.parseObject(json);

        SbElevatorAddparams sa = new SbElevatorAddparams();
        sa.setHxzid(s.getString("hxzid"));
        List<SbElevatorAddparams> sList = sbElevatorAddparamsService.selectSbElevatorAddparamsList(sa);

        //插入升降机实时数据
        JSONArray body = setAddRecord(s, sList.get(0).getLLoadCapacity());
        /** 对接城安院*/
        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setHxzid(s.getString("HxzId"));
        sbElevatorBinding.setScznl("CAY");
        List<SbElevatorBinding> list = iSbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
        if (list.size() > 0) {
            ThreadUtils.async(new Runnable() {
                @Override
                public void run() {
                    try {
                        elevatorCaySSSJ(s, sList.get(0).getLLoadCapacity());
                    } catch (IOException e) {
                        logger.error("城安院错误(RealtimeDataElevator): " + e.getMessage() + ", 参数错误：" + s + sList.get(0).getLLoadCapacity());
                    } catch (URISyntaxException e) {
                        logger.error("城安院错误(RealtimeDataElevator): " + e.getMessage() + ", 参数错误：" + s + sList.get(0).getLLoadCapacity());
                    }
                }
            });
            SbElevatorAddrecord sbElevatorAddrecord = new SbElevatorAddrecord();

            sendElevatorToPERSONNEL.cayDate(sbElevatorAddrecord);
        }


        setZCData(s.getString("HxzId"), body, "/elevator/addRecord", "elevator");


        JSONObject result = new JSONObject();
        result.put("cmd", "RealtimeDataElevator");
        result.put("data", "{}");

        result.put("status", 0);

        return result;
    }

    /**
     * 转换升降机实时数据
     */
    public JSONArray setAddRecord(JSONObject elevatordata, Double a) {
        JSONArray body = new JSONArray();
        JSONObject js = new JSONObject();
        js.put("device_no", Tools.encodeToMD5s(elevatordata.getString("HxzId")));//设备编号
        js.put("runtime", elevatordata.getString("RTime"));//运行时间
        js.put("laod", elevatordata.getString("Weight"));//载重
        js.put("load_ratio", df2.format(Double.valueOf(elevatordata.getString("Weight")) / a));//载重比
        js.put("operator_name", elevatordata.getString("Name"));//升降机司机名字
        js.put("height", elevatordata.getString("Height"));//高度
        js.put("status", "0");//状态(0 正常状态 1 IC 卡无效，2 安全器故障，4 上高度预警，8 上高度报警，16 非本人操作，32 监理授权，64 加节模式

        js.put("is_up_warning", elevatordata.getString("TopAlarm"));//是否上限位报警
        js.put("is_down_warning", elevatordata.getString("BottomAlarm"));//是否下限位报警
        js.put("is_safety_device_warn", elevatordata.getString("FallAlarm"));//是否安全器报警
        js.put("is_over_warning", elevatordata.getString("WeightAlarm"));//是否超重报警
        js.put("is_forward_warning", elevatordata.getString("ObliguityXAlarm"));//是否前限位报警
        js.put("is_backward_warning", elevatordata.getString("ObliguityYAlarm"));//是否后限位报警
        js.put("is_limit_warning", elevatordata.getString("is_limit_warning"));//超限位报警(0.否1.是)

        SbElevatorAddrecord sbElevatorAddrecord = JSONObject.parseObject(js.toJSONString(), SbElevatorAddrecord.class);
        sbElevatorAddrecord.setHxzid(elevatordata.getString("HxzId"));
        sbElevatorAddrecord.setPeopleCntAlarm(elevatordata.getInteger("PeopleCntAlarm"));//人数报警
        sbElevatorAddrecord.setWeightAlarm(elevatordata.getInteger("PeopleCntAlarm"));//载重报警
        sbElevatorAddrecord.setSpeedAlarm(elevatordata.getInteger("SpeedAlarm"));//速度报警
        sbElevatorAddrecord.setObliguityXAlarm(elevatordata.getInteger("ObliguityXAlarm"));//倾角X报警
        sbElevatorAddrecord.setObliguityYAlarm(elevatordata.getInteger("ObliguityYAlarm"));//倾角Y报警
        sbElevatorAddrecord.setSpeed(elevatordata.getDouble("Speed"));//速度
        sbElevatorAddrecord.setWindSpeed(elevatordata.getDouble("WindSpeed"));//风速
        sbElevatorAddrecord.setWindLevel(elevatordata.getDouble("WindLevel"));//风级
        sbElevatorAddrecord.setFloor(elevatordata.getDouble("Floor"));//楼层
        sbElevatorAddrecord.setObliguityX(elevatordata.getDouble("ObliguityX"));//倾角X
        sbElevatorAddrecord.setObliguityY(elevatordata.getDouble("ObliguityY"));//倾角Y
        sbElevatorAddrecord.setIsDownWarning(Integer.parseInt(elevatordata.getString("BottomAlarm")));   //下限位报警
        sbElevatorAddrecord.setIsUpWarning(Integer.parseInt(elevatordata.getString("TopAlarm")));    //上限位报警
        sbElevatorAddrecord.setWeightAlarm(Integer.parseInt(elevatordata.getString("WeightAlarm"))); //超重报警
        sbElevatorAddrecord.setObliguityXAlarm(Integer.parseInt(elevatordata.getString("ObliguityXAlarm")));//前限位报警
        sbElevatorAddrecord.setObliguityYAlarm(Integer.parseInt(elevatordata.getString("ObliguityYAlarm")));//后限位报警
        sbElevatorAddrecord.setPeopleCnt(elevatordata.getString("PeopleCnt"));//载重人数
        sbElevatorAddrecord.setIsLimitWarning(0);//超限位报警
        sbElevatorAddrecordService.insertSbElevatorAddrecord(sbElevatorAddrecord);
        js.put("laod", Double.valueOf(elevatordata.getString("Weight")) * 1000);
        body.add(js);
        sbElevatorAddrecord.setLaod(Double.valueOf(elevatordata.getString("Weight")) * 1000);
        sendElevatorToPERSONNEL.rcajDate(sbElevatorAddrecord);
        return body;
    }


    /**
     *22.6上报升降机工作循环数据
     */

    @RequestMapping(value = "/WorkDataElevator", method = RequestMethod.POST)
    public JSONObject workDataElevator(@RequestBody String json) throws Exception {
        //将字符串转换成json
        JSONObject s = JSONObject.parseObject(json);

        JSONArray body = setOperator(s);

        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setHxzid(s.getString("HxzId"));
        sbElevatorBinding.setScznl("CAY");
        List<SbElevatorBinding> list = iSbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
        if (list.size() > 0) {
//            ThreadUtils.async(new Runnable(){
//                @Override
//                public void run() {
//                    try {
//                        electrifyCAY(s);
//                        elevatorCayCZJL(s);
//                    } catch (IOException e) {
//                        logger.error("城安院错误(WorkDataElevator): " + e.getMessage() + ", 参数错误："+s);
//                    } catch (URISyntaxException e) {
//                        logger.error("城安院错误(WorkDataElevator): " + e.getMessage() + ", 参数错误："+s);
//                    }
//                }
//            });
            SbElevatorAddrecord sbElevatorAddrecord = new SbElevatorAddrecord();
            sbElevatorAddrecord.setHxzid(s.getString("HxzId"));
            sbElevatorAddrecord.setDownlineTime(s.getString("WorkStartTime"));//操作开始时间
            sbElevatorAddrecord.setClosingTime(s.getString("WorkEndTime"));//操作结束时间
            sbElevatorAddrecord.setLoadRatio(Double.valueOf(s.getString("MaxWeight")));//工作最大承重值
            sbElevatorAddrecord.setIsOverWarning(Integer.valueOf(s.getString("WeightAlarm")));//是否发生超载
            sendElevatorToPERSONNEL.cayWorkCycle(sbElevatorAddrecord);
        }
        setZCData(s.getString("HxzId"), body, "/elevator/operator", "elevator");


        //返回json字符串
        JSONObject result = new JSONObject();
        result.put("cmd", "WorkDataElevator");
        result.put("data", "{}");


        result.put("status", 0);//成功

        return result;
    }

    /**
     * 操作记录body数据
     * @param workdataelevator
     * @return
     */
    public JSONArray setOperator(JSONObject workdataelevator) {
        JSONArray body = new JSONArray();
        JSONObject js = new JSONObject();
        js.put("device_no", Tools.encodeToMD5s(workdataelevator.getString("HxzId")));//设备编号
//        js.put("start_time",workdataelevator.getString("WorkStartTime"));//操作开始时间
//        js.put("end_time",workdataelevator.getString("WorkEndTime"));//操作结束时间
//        js.put("max_load_value",workdataelevator.getString("MaxWeight"));//工作最大承重值
        js.put("is_over_load", workdataelevator.getString("WeightAlarm"));//是否发生超载
//        js.put("operator_name",workdataelevator.getName());//持卡人姓名
        js.put("isIllegal_operation", "0");//是否非持卡人操作

        SbElevatorOperator sbElevatorOperator = JSONObject.parseObject(js.toJSONString(), SbElevatorOperator.class);
        sbElevatorOperator.setHxzid(workdataelevator.getString("HxzId"));
        sbElevatorOperatorService.insertSbElevatorOperator(sbElevatorOperator);
        body.add(js);
        return body;
    }

    /**上报城安院司机打卡记录*/
    public String sjdkCay(JSONObject jsonObject) throws IOException, URISyntaxException {
        JSONArray body = new JSONArray();
        JSONObject js1 = new JSONObject();
        Integer projectId = null;
        String f = null;
        String i = "升降机";
        if (Integer.valueOf(jsonObject.getString("Type")) == 1) {
            i = "塔机";
        } else if (Integer.valueOf(jsonObject.getString("Type")) == 2) {
            i = "升降机";
        }
//        else if (Integer.valueOf(jsonObject.getString("Type"))==3){
//            i = "扬尘监测";
//        }else if (Integer.valueOf(jsonObject.getString("Type"))==4){
//            i = "卸料平台";
//        }
        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setHxzid(jsonObject.getString("HxzId"));
        sbElevatorBinding.setProjectId(projectId);
        List<SbElevatorBinding> list = iSbElevatorBindingService.list(sbElevatorBinding);
        HjProject hjProject = iHjProjectService.selectHjProjectById(list.get(0).getPid());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("curpage", "1");
        jsonObject1.put("name", hjProject.getProjectName());
        /** 区管项目*/

        JSONObject jsonObject2 = ZCAPIClient.cayArea("authorize/getProAndSub", jsonObject1);
        if (jsonObject2 != null) {
            js1.put("projectId", jsonObject2.getString("xmid"));//所属项目编号
            js1.put("jdbh", jsonObject2.getString("jdbh"));//项目监督编号
            js1.put("devGuid", Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备编号
            js1.put("devType", i);//设备类型（升降机/塔机）
            js1.put("workerName", jsonObject.getString("Name"));//驾驶员姓名
            js1.put("punchTime", jsonObject.getString("PunchTime"));//打卡（上机）时间
            js1.put("closingTime", jsonObject.getString("ClosingTime"));//下班（关机）时间
            body.add(js1);
            JSONObject object = new JSONObject();
            object.put("PList", body);
            f = ZCAPIClient.QGXMCAY("tower/oper_pec", object);
        }
        /** 市管项目*/
        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos", jsonObject1);
        JSONObject object1 = new JSONObject();
        JSONArray array = new JSONArray();
        if (xmid != null) {
            JSONObject j = new JSONObject();
            j.put("pguid", xmid);
            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
            JSONArray data = object.getJSONArray("res");
            JSONObject datas = data.getJSONObject(0);
            object1.put("projectId", datas.getString("xmid"));//所属项目编号
            object1.put("Jdbh", datas.getString("jdbh"));//项目监督编号
            object1.put("sub_id", datas.getString("gcid"));//工程ID
            object1.put("devGuid", Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备编号
            object1.put("devType", i);//设备类型（升降机/塔机）
            object1.put("punchTime", jsonObject.getString("PunchTime"));//打卡（上机）时间
            object1.put("closingTime", jsonObject.getString("ClosingTime"));//下班（关机）时间
            array.add(object1);
            JSONObject object2 = new JSONObject();
            object2.put("PList", array);
            f = ZCAPIClient.SGXMCAY("misInter/tower/oper_pec", jsonObject);
        }
        return f;
    }

    /**
     * 上传塔机通电时间body数据(城安院)
     */
    public String electrifyCAY(JSONObject workdatacrane) throws IOException, URISyntaxException {
        JSONArray body = new JSONArray();
        JSONObject js1 = new JSONObject();
        Integer projectId = null;
        String f = null;
        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setHxzid(workdatacrane.getString("HxzId"));
        sbElevatorBinding.setProjectId(projectId);
        List<SbElevatorBinding> list = iSbElevatorBindingService.list(sbElevatorBinding);
        HjProject hjProject = iHjProjectService.selectHjProjectById(list.get(0).getPid());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("curpage", "1");
        jsonObject1.put("name", hjProject.getProjectName());
        /** 区管项目*/
        JSONObject jsonObject2 = ZCAPIClient.cayArea("authorize/getProAndSub", jsonObject1);
        if (jsonObject2 != null) {
            js1.put("PGUID", jsonObject2.getString("xmid"));//所属项目编号
            js1.put("Jdbh", jsonObject2.getString("jdbh"));//项目监督编号
            if (StringUtils.isBlank(workdatacrane.getString("DownlineTime"))) {
                js1.put("device_no", Tools.encodeToMD5s(workdatacrane.getString("HxzId")));//设备号
                js1.put("runtime", workdatacrane.getString("OnlineTime"));//运行时刻
                js1.put("operation", "1");//事件类型（0 断电，1 通电）
            } else {
                js1.put("device_no", Tools.encodeToMD5s(workdatacrane.getString("HxzId")));//设备号
                js1.put("runtime", workdatacrane.getString("DownlineTime"));//运行时刻
                js1.put("operation", "0");//事件类型（0 断电，1 通电）
            }
            body.add(js1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PList", body);
            f = ZCAPIClient.QGXMCAY("lifter/ele_t_state", jsonObject);
        }
        /** 市管项目*/
        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos", jsonObject1);
        JSONObject object1 = new JSONObject();
        JSONArray array = new JSONArray();
        if (xmid != null) {
            JSONObject j = new JSONObject();
            j.put("pguid", xmid);
            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
            JSONArray data = object.getJSONArray("res");
            JSONObject datas = data.getJSONObject(0);
            object1.put("PGUID", datas.getString("xmid"));//所属项目编号
            object1.put("Jdbh", datas.getString("jdbh"));//项目监督编号
            object1.put("sub_id", datas.getString("gcid"));//工程ID
            if (StringUtils.isBlank(workdatacrane.getString("DownlineTime"))) {
                object1.put("device_no", Tools.encodeToMD5s(workdatacrane.getString("HxzId")));//设备号
                object1.put("runtime", workdatacrane.getString("OnlineTime"));//运行时刻
                object1.put("operation", "1");//事件类型（0 断电，1 通电）
            } else {
                object1.put("device_no", Tools.encodeToMD5s(workdatacrane.getString("HxzId")));//设备号
                object1.put("runtime", workdatacrane.getString("DownlineTime"));//运行时刻
                object1.put("operation", "0");//事件类型（0 断电，1 通电）
            }
            array.add(object1);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PList", js1);
            f = ZCAPIClient.SGXMCAY("lifter/ele_t_state", jsonObject);
        }
        return f;
    }


    /** 上报城安院升降机操作记录*/
    public JSONArray elevatorCayCZJL(JSONObject workdataelevator) throws IOException, URISyntaxException {
        JSONArray body = new JSONArray();
        JSONArray body1 = new JSONArray();
        JSONObject js = new JSONObject();
        JSONObject js1 = new JSONObject();
        Integer projectId = null;
        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setHxzid(workdataelevator.getString("HxzId"));
        sbElevatorBinding.setProjectId(projectId);
        List<SbElevatorBinding> list = iSbElevatorBindingService.list(sbElevatorBinding);
        HjProject hjProject = iHjProjectService.selectHjProjectById(list.get(0).getPid());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("curpage", "1");
        jsonObject1.put("name", hjProject.getProjectName());
        JSONArray array = new JSONArray();
        /** 对接区管项目*/
        JSONObject jsonObject2 = ZCAPIClient.cayArea("authorize/getProAndSub", jsonObject1);
        if (jsonObject2 != null) {
            JSONObject object1 = new JSONObject();
            object1.put("PGUID", jsonObject2.getString("xmid"));//所属项目编号
            object1.put("Jdbh", jsonObject2.getString("jdbh"));//项目监督编号
            object1.put("GUID", Tools.encodeToMD5s(workdataelevator.getString("HxzId")));//设备编号
            object1.put("Yxsk_S", workdataelevator.getString("WorkStartTime"));//操作开始时间
            object1.put("Yxsk_E", workdataelevator.getString("WorkEndTime"));//操作结束时间
            object1.put("zz", workdataelevator.getString("MaxWeight"));//工作最大承重值
            object1.put("cz", workdataelevator.getString("WeightAlarm"));//是否发生超载
            object1.put("Operator_A larm", "0");//是否非持卡人操作

            body1.add(object1);
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("PList", body1);
            String f = ZCAPIClient.QGXMCAY("lifter/ele_t_run", jsonObject3);
            logger.info("上传城安院lifter/ele_t_run接口返回"+f);
        }

        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos", jsonObject1);
        /** 对接市管项目*/
        if (xmid != null) {
            JSONObject j = new JSONObject();
            j.put("pguid", xmid);
            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
            JSONArray data = object.getJSONArray("res");
            JSONObject datas = data.getJSONObject(0);
            js.put("PGUID", datas.getString("xmid"));//所属项目编号
            js.put("Jdbh", datas.getString("jdbh"));//项目监督编号
            js.put("sub_id", datas.getString("gcid"));//工程ID
            js.put("GUID", Tools.encodeToMD5s(workdataelevator.getString("HxzId")));//设备编号
            js.put("Yxsk_S", workdataelevator.getString("WorkStartTime"));//操作开始时间
            js.put("Yxsk_E", workdataelevator.getString("WorkEndTime"));//操作结束时间
            js.put("zz", workdataelevator.getString("MaxWeight"));//工作最大承重值
            js.put("cz", workdataelevator.getString("WeightAlarm"));//是否发生超载
            js.put("Operator_A larm", "0");//是否非持卡人操作
            body.add(js);
            JSONObject object1 = new JSONObject();
            object1.put("PList", body);
            //上报城安院升降机操作记录
            String f = ZCAPIClient.SGXMCAY("lifter/ele_t_run", object1);
        }

        return body;
    }

    /** 上报城安院升降机实时数据（运行时间需要30s上报一次）(区管项目+非区管项目)*/
    public JSONArray elevatorCaySSSJ(JSONObject jsonObject, Double a) throws IOException, URISyntaxException {
        JSONArray body = new JSONArray();
        JSONObject pList = new JSONObject();
        Integer projectId = null;
        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setHxzid(jsonObject.getString("HxzId"));
        sbElevatorBinding.setProjectId(projectId);
        List<SbElevatorBinding> list = iSbElevatorBindingService.list(sbElevatorBinding);
        HjProject hjProject = iHjProjectService.selectHjProjectById(list.get(0).getPid());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("curpage", "1");
        jsonObject1.put("name", hjProject.getProjectName());
        //查询是不是区管项目
        JSONObject jsonObject2 = ZCAPIClient.cayArea("authorize/getProAndSub", jsonObject1);
        if (jsonObject2 != null) {
            JSONObject object1 = new JSONObject();
            object1.put("pguid", jsonObject2.getString("xmid"));//所属项目编号
            object1.put("Jdbh", jsonObject2.getString("jdbh"));//项目监督编号
            object1.put("guid", Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备ID
            Integer i = Integer.valueOf(jsonObject.getString("Weight")) * 1000;
            object1.put("zz", i);//载重（kg）
            object1.put("yxsk", jsonObject.getString("RTime"));//运行时刻
            object1.put("gd", jsonObject.getString("Height"));//高度
            int k = 0;
            if (Integer.valueOf(jsonObject.getString("NoPreAlarm")) == 0) {
                k = 0;
            } else if (Integer.valueOf(jsonObject.getString("IdError")) == 1) {
                k = 1;
            } else if (Integer.valueOf(jsonObject.getString("TopAlarm")) == 1) {
                k = 8;
            } else if (Integer.valueOf(jsonObject.getString("BottomAlarm")) == 1) {
                k = 128;
            }
            object1.put("zt", k);//状态(0 正常状态/1 IC 卡无效/2 安全器故障/4 上高度预警/8 上高度报警/16 非本人操作/32 监理授权/64 加节模式/128 下限位报警)
            object1.put("sxw", jsonObject.getString("TopAlarm"));//是否上限位报警（0-否，1-是，2-无上限位）
            object1.put("xxw", jsonObject.getString("BottomAlarm"));//是否下限位报警（0-否，1-是，2-无下限位）
            object1.put("Aqq_alarm", jsonObject.getString("FallAlarm "));//是否安全器（防坠器）报警
            object1.put("zz_alarm", jsonObject.getString("WeightAlarm"));//是否超重报警
            int qxw = 0;
            int hxw = 0;
            if (Integer.valueOf(jsonObject.getString("DoorState")) == 1) {
                qxw = 1;
                hxw = 1;
            } else if (Integer.valueOf(jsonObject.getString("DoorState")) == 2) {
                qxw = 0;
                hxw = 1;
            } else if (Integer.valueOf(jsonObject.getString("DoorState")) == 3) {
                qxw = 1;
                hxw = 0;
            }
            object1.put("qxw", qxw);//是否前（外）限位报警
            object1.put("hxw", hxw);//是否后（内）限位报警
            body.add(object1);
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("PList", body);
            String f = ZCAPIClient.QGXMCAY("lifter/ele_t_data", jsonObject3);
            logger.info("上传城安院lifter/ele_t_data接口返回数据"+ f);
        }
        /** 市管项目*/
        JSONArray array = new JSONArray();
        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos", jsonObject1);
        if (xmid != null) {
            JSONObject j = new JSONObject();
            j.put("pguid", xmid);
            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
            JSONArray data = object.getJSONArray("res");
            JSONObject datas = data.getJSONObject(0);
            JSONObject json = new JSONObject();
            json.put("pguid", datas.getString("xmid"));//所属项目编号
            json.put("Jdbh", datas.getString("jdbh"));//项目监督编号
            json.put("sub_id", datas.getString("gcid"));//工程ID
            json.put("guid", Tools.encodeToMD5s(jsonObject.getString("HxzId")));//设备ID
            Integer i = Integer.valueOf(jsonObject.getString("Weight")) * 1000;
            json.put("zz", i);//载重（kg）
            json.put("zzb", df2.format(Double.valueOf(jsonObject.getString("Weight")) / a));//载重比
            json.put("yxsk", jsonObject.getString("RTime"));//运行时刻
            json.put("gd", jsonObject.getString("Height"));//高度
            int k = 0;
            if (Integer.valueOf(jsonObject.getString("NoPreAlarm")) == 0) {
                k = 0;
            } else if (Integer.valueOf(jsonObject.getString("IdError")) == 1) {
                k = 1;
            } else if (Integer.valueOf(jsonObject.getString("TopAlarm")) == 1) {
                k = 8;
            } else if (Integer.valueOf(jsonObject.getString("BottomAlarm")) == 1) {
                k = 128;
            }
            json.put("zt", k);//状态(0 正常状态/1 IC 卡无效/2 安全器故障/4 上高度预警/8 上高度报警/16 非本人操作/32 监理授权/64 加节模式/128 下限位报警)
            json.put("sxw", jsonObject.getString("TopAlarm"));//是否上限位报警（0-否，1-是，2-无上限位）
            json.put("xxw", jsonObject.getString("BottomAlarm"));//是否下限位报警（0-否，1-是，2-无下限位）
            json.put("zz_alarm", jsonObject.getString("WeightAlarm"));//是否超重报警
            int qxw = 0;
            int hxw = 0;
            if (Integer.valueOf(jsonObject.getString("DoorState")) == 1) {
                qxw = 1;
                hxw = 1;
            } else if (Integer.valueOf(jsonObject.getString("DoorState")) == 2) {
                qxw = 0;
                hxw = 1;
            } else if (Integer.valueOf(jsonObject.getString("DoorState")) == 3) {
                qxw = 1;
                hxw = 0;
            }
            json.put("qxw", qxw);//是否前（外）限位报警
            json.put("hxw", hxw);//是否后（内）限位报警
            if (jsonObject.getString("TopAlarm").equals("1") || jsonObject.getString("BottomAlarm").equals("1")) {
                json.put("cxw", "1");//超限位报警(1报警）
            } else {
                json.put("cxw", "0");//超限位报警(1报警）
            }
            array.add(json);
            JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("PList", array);
            String sgxmsjj = ZCAPIClient.SGXMCAY("lifter/ele_t_data", jsonObject4);
            logger.info("上报城安院升降机实时数据状态：" + sgxmsjj);
        }
        if (jsonObject2 != null) {
            return body;
        } else {
            return array;
        }
    }
}
