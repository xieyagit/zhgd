package com.hujiang.project.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import com.hujiang.project.zhgd.sbDoorLock.domain.SbDoorLock;
import com.hujiang.project.zhgd.sbDoorLock.service.ISbDoorLockService;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import com.hujiang.project.zhgd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * 电箱接口
 */
@RestController
@RequestMapping(value = "/provider/electricityBox/get", method = RequestMethod.GET)
public class ApiElectricityBoxController {

    @Autowired
    private ISbProjectElectricityBoxService iProjectElectricityBoxService;
    @Autowired
    private ISbDoorLockService iDoorLockService;

    @Autowired
    private ISbProjectElectricityBoxService iSbProjectElectricityBoxService;

    @Autowired
    private ISbCurrentTemperatureService iCurrentTemperatureService;
    private Logger logger = Logger.getLogger(ApiElectricityBoxController.class.getName());

    /**
     * 根据项目id获取项目电箱列表
     *
     * @param pid
     * @return
     */
    @RequestMapping("list")
    public Object getProjectElectricityBox(String pid) {
        Integer pid1 = 0;
        try {
            pid1 = Integer.parseInt(pid);
        } catch (Exception e) {
            return Util.ErrorReturnedValue("没有此类型的项目id(pid为整数)");
        }
        SbProjectElectricityBox p = new SbProjectElectricityBox();
        p.setProjectId(pid1);
        JSONArray jsonArray = new JSONArray();
        List<SbProjectElectricityBox> projectElectricityBoxes = iProjectElectricityBoxService.selectSbProjectElectricityBoxList(p);
        for (SbProjectElectricityBox peb : projectElectricityBoxes) {
            JSONObject object = new JSONObject();
            object.put("id", peb.getId());
            object.put("projectId", peb.getProjectId());
            object.put("comments", peb.getComments());
            object.put("electricityBoxId", peb.getElectricityBoxId());
            jsonArray.add(object);
        }
        return jsonArray;
    }

    /**
     * 根据设备id获取电箱数据
     *
     * @param electricityBoxId
     * @param time
     * @return
     */
    @RequestMapping("message")
    public Object getElectricityBoxRecord(String electricityBoxId, @RequestParam(required = false) String time) {
        //设置设备id
        SbCurrentTemperature c = new SbCurrentTemperature();
        c.setElectricityBoxId(electricityBoxId);
        SbCurrentTemperature currentTemperature1 = iCurrentTemperatureService.selectCurrentTemperature(c);
        JSONObject n = new JSONObject();
        n.put("id", currentTemperature1.getId());
        n.put("electricityBoxId", currentTemperature1.getElectricityBoxId());
        n.put("current", currentTemperature1.getCurrent());
        n.put("envirwarm", currentTemperature1.getEnvirwarm());
        n.put("awarm", currentTemperature1.getAwarm());
        n.put("bwarm", currentTemperature1.getBwarm());
        n.put("cwarm", currentTemperature1.getCwarm());
        n.put("nwarm", currentTemperature1.getNwarm());
        n.put("tm", this.getSubTime(currentTemperature1.getTm()));
        //时间
        if (time != null) {
            c.setTm(time);
        }
        //根据设备id查询温度记录
        List<SbCurrentTemperature> currentTemperatures = iCurrentTemperatureService.selectSbCurrentTemperatureList(c);
        JSONArray jsonArray = new JSONArray();

        if (currentTemperatures.size() > 0) {
            for (SbCurrentTemperature crr : currentTemperatures) {
                JSONObject json = new JSONObject();
                json.put("id", crr.getId());
                json.put("electricityBoxId", crr.getElectricityBoxId());
                json.put("current", crr.getCurrent());
                json.put("envirwarm", crr.getEnvirwarm());
                json.put("awarm", crr.getAwarm());
                json.put("bwarm", crr.getBwarm());
                json.put("cwarm", crr.getCwarm());
                json.put("nwarm", crr.getNwarm());
                json.put("tm", this.getSubTime(crr.getTm()));
                jsonArray.add(json);
            }


        }

        SbDoorLock d = new SbDoorLock();
        d.setElectricityBoxId(electricityBoxId);
        //时间
        if (time != null) {
            d.setHandleTime(time);
        }
        //根据设备id查询门开关记录
        List<SbDoorLock> doorLocks = iDoorLockService.selectSbDoorLockList(d);
        JSONArray jsonArray1 = new JSONArray();
        if (doorLocks.size() > 0) {
            for (SbDoorLock dk : doorLocks) {
                JSONObject json = new JSONObject();
                json.put("id", dk.getId());
                json.put("electricityBoxId", dk.getElectricityBoxId());
                json.put("lockType", dk.getLockType());
                json.put("doorType", dk.getDoorType());
                json.put("handleTime", dk.getHandleTime());
                json.put("opendoorTime", this.getSubTime(dk.getOpendoorTime()));
                json.put("closedoorTime", this.getSubTime(dk.getClosedoorTime()));
                json.put("openlockTime", this.getSubTime(dk.getOpenlockTime()));
                json.put("closelockTime", this.getSubTime(dk.getCloselockTime()));
                jsonArray1.add(json);
            }
        }
        JSONObject json = new JSONObject();
        json.put("currentTemperatures", jsonArray);
        json.put("doorLocks", jsonArray1);
        json.put("new", n);
        return json;
    }

    /**
     * 根据项目id获取电箱状态数据
     *
     * @param pid
     * @return
     */
    @RequestMapping("getElectricBoxState")
    public JSONObject getElectricBoxState(String pid) throws Exception {
        JSONObject result = new JSONObject();
        Integer pid1 = 0;
        try {
            pid1 = Integer.parseInt(pid);
        } catch (Exception e) {
            return Util.ErrorReturnedValue("没有此类型的项目id(pid为整数)");
        }
        SbProjectElectricityBox p = new SbProjectElectricityBox();
        p.setProjectId(pid1);
        JSONArray jsonArray = new JSONArray();
        List<SbProjectElectricityBox> projectElectricityBoxes = iProjectElectricityBoxService.selectSbProjectElectricityBoxList(p);
        System.out.println(projectElectricityBoxes.size());
        for (SbProjectElectricityBox peb : projectElectricityBoxes) {
            //电箱编号
            String electricityBoxId = peb.getElectricityBoxId();
            //截取后八位
            String substring = electricityBoxId.substring(electricityBoxId.length() - 8, electricityBoxId.length());
            SimpleDateFormat sp1 = new SimpleDateFormat("yyyy-MM-dd");
            //获取今天日期
            String jr = sp1.format(new Date());
            //明日日期
            Date d1 = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
            String mr = sp1.format(d1);//获取考勤日期
            //请求url 获取电箱温度数据
            String s = Util.httpPostWithJSON(Constants.TempEelcHistory + "?id=" + substring + "&startTime=" + jr + " 00:00:00&endTime=" + mr, new JSONObject());
            //获取的原始数据
            JSONObject originalData = JSONObject.parseObject(s);
            JSONArray data = (JSONArray) originalData.get("data");
            if (data.size() != 0) {
                JSONObject o = (JSONObject) data.get(data.size() - 1);
                result.put("envirwarm", o.get("envirwarm"));//电箱温度
                result.put("current", o.get("current"));//电箱漏电流
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(o.getString("tm"));//数据更新时间
                Date afterDate1 = new Date(date.getTime() + 120000);//加两分钟
                Date parse = df.parse(sdf.format(afterDate1));//数据时间
                Date parse1 = df.parse(sdf.format(new Date()));//当前时间
                if (parse.getTime() > parse1.getTime()) {//数据更新时间加两分钟大于当前系统时间
                    //设备正常
                    logger.info("设备正常");
                    result.put("sb", "正常");
                } else {
                    //设备异常
                    logger.info("设备异常");
                    result.put("sb", "异常");
                }
            } else {
                result.put("envirwarm", "0");//电箱温度
                result.put("current", "0");//电箱漏电流
                result.put("sb", "异常");
            }
            SbDoorLock d = new SbDoorLock();
            d.setElectricityBoxId(electricityBoxId);
            result.put("kg", iDoorLockService.selectSbDoorLockList(d).get(0).getDoorType());
            JSONArray jsonArray1 = new JSONArray();
            List<SbDoorLock> doorLocks = iDoorLockService.selectSbDoorLockList(d);
            if (doorLocks.size() > 0) {
                for (SbDoorLock dk : doorLocks) {
                    JSONObject json = new JSONObject();
                    json.put("id", dk.getId());
                    json.put("electricityBoxId", dk.getElectricityBoxId());
                    json.put("lockType", dk.getLockType());
                    json.put("doorType", dk.getDoorType());
                    json.put("handleTime", dk.getHandleTime());
                    json.put("opendoorTime", this.getSubTime(dk.getOpendoorTime()));
                    json.put("closedoorTime", this.getSubTime(dk.getClosedoorTime()));
                    json.put("openlockTime", this.getSubTime(dk.getOpenlockTime()));
                    json.put("closelockTime", this.getSubTime(dk.getCloselockTime()));
                    jsonArray1.add(json);
                }
            }
            result.put("kgjl", jsonArray1);
        }
        return result;
    }

    /**
     * 上报配电箱参数接口(深圳市城安院)
     *
     * @param sbP
     * @return JSONObject
     */
    public JSONObject reportElectricBoxParamete(SbProjectElectricityBox sbP) throws IOException, URISyntaxException {
        //        // 判断项目ID的，设备ID是否存在d
        List<SbProjectElectricityBox> iSbProjectElectricityBoxs = iSbProjectElectricityBoxService.selectByProjectIdAndHxzId(sbP);

        JSONObject resultJson = new JSONObject();

        // 区管项目
        JSONObject regionJsonObject = new JSONObject();
        List<JSONObject> regionJsons = new ArrayList<>();
        if (sbP.getSubId()==null && sbP.getSubId().equals("")) {
            JSONObject object = new JSONObject();
            object.put("PROJECT_ID", sbP.getXmid());// 项目编号
            object.put("Jdbh", sbP.getJdbh());// 项目监督编号
            object.put("DEV_GUID", Tools.encodeToMD5s(sbP.getElectricityBoxId()));// 设备编码
            object.put("DEV_TYPE", sbP.getDevType());// 设备类型
            object.put("DEV_TYPE_NAME", "");// 设备类型名称
            object.put("COMPANY_NAME", sbP.getCompanyName());// 设备安装单位
            object.put("COMPANY_ADDRESS", "");// 公司地址
            object.put("INSTALL_ADDRESS", sbP.getInstallAddress());// 安装地址
            object.put("INSTALL_ADDRTYPE", sbP.getInstallAddrtype());// 字典：生活区、施工现场、配电房
            object.put("DTU_ID", Tools.encodeToMD5s(sbP.getElectricityBoxId()));// DTU 标识
            object.put("LONGTITUDE", "");// 经度
            object.put("LATITUDE", "");// 纬度
            object.put("CREATE_DATE", "");// 创建时间
            object.put("PHOTO_PATH", "");// 设备照片路径（多张照片以“,”分隔)
            object.put("TEMP_LIMIT", sbP.getTempLimit());// 电缆温度限值
            object.put("ELEC_LIMIT", sbP.getElecLimit());// 漏电流限值
            object.put("AROUND_TEMP_LIMIT", sbP.getAroundTemp());// 周围环境温度限值
            regionJsons.add(object);
            regionJsonObject.put("PList", regionJsons);
            try {
                // 上报配电箱参数
                String xmid = ZCAPIClient.QGXMCAY("/pdx/pdxParams", regionJsonObject);
                System.out.println("上报配电箱参数：" + xmid);
            } catch (Exception e) {
                e.printStackTrace();
                resultJson.put("res", "ERROR");
                resultJson.put("errcode", 40002);
                resultJson.put("msg", "出现异常");
                return resultJson;
            }
        } else {
            // 市管项目
            JSONObject cityJsonObject = new JSONObject();
            List<JSONObject> cityJsons = new ArrayList<>();
            JSONObject object = new JSONObject();
            object.put("PROJECT_ID", sbP.getXmid());// 项目编号
            object.put("Jdbh", sbP.getJdbh());// 项目监督编号
            object.put("DEV_GUID", Tools.encodeToMD5s(sbP.getElectricityBoxId()));// 设备编码
            object.put("DEV_TYPE", sbP.getDevType());// 设备类型
            object.put("DEV_TYPE_NAME", "");// 设备类型名称
            object.put("COMPANY_NAME", sbP.getCompanyName());// 设备安装单位
            object.put("COMPANY_ADDRESS", "");// 公司地址
            object.put("INSTALL_ADDRESS", sbP.getInstallAddress());// 安装地址
            object.put("INSTALL_ADDRTYPE", sbP.getInstallAddrtype());// 字典：生活区、施工现场、配电房
            object.put("DTU_ID", Tools.encodeToMD5s(sbP.getElectricityBoxId()));// DTU 标识
            object.put("LONGTITUDE", "");// 经度
            object.put("LATITUDE", "");// 纬度
            object.put("CREATE_DATE", "");// 创建时间
            object.put("PHOTO_PATH", "");// 设备照片路径（多张照片以“,”分隔)
            object.put("TEMP_LIMIT", sbP.getTempLimit());// 电缆温度限值
            object.put("ELEC_LIMIT", sbP.getElecLimit());// 漏电流限值
            object.put("AROUND_TEMP_LIMIT", sbP.getAroundTemp());// 周围环境温度限值
            object.put("sub_id", sbP.getSubId());
            cityJsons.add(object);

            cityJsonObject.put("PList", cityJsons);
            try {
                // 上报配电箱参数
                String xmid = ZCAPIClient.SGXMCAY("/pdx/pdxParams", cityJsonObject);
                System.out.println("上报配电箱参数：" + xmid);
            } catch (Exception e) {
                e.printStackTrace();
                resultJson.put("res", "ERROR");
                resultJson.put("errcode", 40002);
                resultJson.put("msg", "出现异常");
                return resultJson;
            }
            resultJson.put("res", "OK");
            resultJson.put("errcode", 0);
            resultJson.put("msg", "保存成功");
            return resultJson;
        }
        return resultJson;
    }

    /**
     * 上报配电箱状态接口
     *
     * @param sc
     * @return JSONObject
     */
    public JSONObject reportElectricBoxState(SbCurrentTemperature sc,int wran_type) {
        JSONObject resultJson = new JSONObject();
        // 判断项目ID的，设备ID是否存在d
        SbProjectElectricityBox sbProjectElectricityBox = new SbProjectElectricityBox();
        sbProjectElectricityBox.setElectricityBoxId(sc.getElectricityBoxId());
        List<SbProjectElectricityBox> iSbProjectElectricityBoxs = iSbProjectElectricityBoxService.selectByProjectIdAndHxzId(sbProjectElectricityBox);
        if (iSbProjectElectricityBoxs != null) {
            String status = "正常";
            if (wran_type == 1) {
                status = "报警";
            }
            String alert = "表示无报警";
            if (wran_type == 1) {
                alert = "表示漏电报警";
            }
            List channelDatas = new ArrayList();
            // 设置设备id
            SbCurrentTemperature c = new SbCurrentTemperature();
            c.setElectricityBoxId(sc.getElectricityBoxId());
            SbCurrentTemperature currentTemperature = iCurrentTemperatureService.selectCurrentTemperature(c);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 区管项目
            JSONObject regionJsonObject = new JSONObject();
            List<JSONObject> regionJsons = new ArrayList<>();
            if (iSbProjectElectricityBoxs.get(0).getSubId() != null && !iSbProjectElectricityBoxs.get(0).getSubId().equals("")) {

                JSONObject object = new JSONObject();
                object.put("PROJECT_ID", sc.getElectricityBoxId());// 项目ID;


                object.put("Jdbh", iSbProjectElectricityBoxs.get(0).getJdbh());// 监督号
                object.put("DEV_GUID", Tools.encodeToMD5s(sc.getElectricityBoxId()));//设备ID
                object.put("DEV_OPERATE_TIME", df.format(new Date()));// 上传日期精确到时分秒
                object.put("DEV_STATUS", status);   // 0：正常1：报警 2：未连接 4：故障 8：屏蔽
                object.put("WRAN_TYPE", alert);   // 0 表示无报警，1 表示漏电报警，2 表示温度报警，3 表示两者都报警
                if (wran_type != 0) {
                    object.put("WARN_ID", alert);// WARN_TYPE 不等于 0 时此字段必填，唯一标识，同一次报警 ID 相同
                }
                object.put("TEMP_XL_a", sc.getAwarm());  // 线缆 a 温度
                object.put("TEMP_XL_b", sc.getBwarm());  // 线缆 b 温度
                object.put("TEMP_XL_c", sc.getCwarm());  // 线缆 c 温度
                object.put("TEMP_XL_n", sc.getNwarm()); // 线缆 n 温度
                object.put("ELEC_LIMIT", sc.getCurrent());    // 漏电流
                object.put("TEMP_HJ", sc.getEnvirwarm());     // 环境温度
                object.put("ChannelDatas", channelDatas);   // 渠道数据
                regionJsons.add(object);

                regionJsonObject.put("PList", regionJsons);
            } else {
                resultJson.put("res", "FAIL");
                resultJson.put("errcode", -2);
                resultJson.put("msg", "请求列表数据中的项目 ID 为空值");
                return resultJson;
            }
            try {
                // 上报配电箱状态
                String xmid = ZCAPIClient.QGXMCAY("/pdx/pdxState", regionJsonObject);
                System.out.println("上报配电箱状态：" + xmid);
            } catch (Exception e) {
                e.printStackTrace();
                resultJson.put("res", "FAIL");
                resultJson.put("errcode", 4002);
                resultJson.put("msg", "系统异常");
                return resultJson;
            }
            // 市管项目
            JSONObject cityJsonObject = new JSONObject();
            List<JSONObject> cityJsons = new ArrayList<>();
            for (SbProjectElectricityBox box : iSbProjectElectricityBoxs) {
                JSONObject object = new JSONObject();
                object.put("PROJECT_ID", box.getProjectId());// 项目ID;
                object.put("Jdbh", box.getJdbh());// 监督号
                object.put("DEV_GUID", Tools.encodeToMD5s(sc.getElectricityBoxId()));// 设备ID
                object.put("DEV_OPERATE_TIME", df.format(new Date()));// 上传日期精确到时分秒
                object.put("DEV_STATUS", status);   // 0：正常1：报警 2：未连接 4：故障 8：屏蔽
                object.put("WRAN_TYPE", alert);   // 0 表示无报警，1 表示漏电报警，2 表示温度报警，3 表示两者都报警
                if (wran_type != 0) {
                    object.put("WARN_ID", alert);// WARN_TYPE 不等于 0 时此字段必填，唯一标识，同一次报警 ID 相同
                }
                object.put("TEMP_XL_a", sc.getAwarm());  // 线缆 a 温度
                object.put("TEMP_XL_b", sc.getBwarm());  // 线缆 b 温度
                object.put("TEMP_XL_c", sc.getCwarm());  // 线缆 c 温度
                object.put("TEMP_XL_n", sc.getNwarm()); // 线缆 n 温度
                object.put("ELEC_LIMIT", sc.getCurrent());    // 漏电流
                object.put("TEMP_HJ", sc.getEnvirwarm());     // 环境温度
                object.put("ChannelDatas", channelDatas);
                object.put("sub_id", box.getSubId());
                cityJsons.add(object);
            }
            cityJsonObject.put("PList", cityJsons);
            try {
                // 上报配电箱参数
                String xmid = ZCAPIClient.SGXMCAY("/pdx/pdxState", cityJsonObject);
                System.out.println("上报配电箱状态：" + xmid);
            } catch (Exception e) {
                e.printStackTrace();
                resultJson.put("res", "FAIL");
                resultJson.put("errcode", 4002);
                resultJson.put("msg", "系统异常");
                return resultJson;
            }
            resultJson.put("res", "OK");
            resultJson.put("errcode", 0);
            resultJson.put("msg", "保存成功");
        }
        return resultJson;
    }


    /**
     * 截取时间小时：分
     *
     * @param time
     * @return
     */
    private String getSubTime(String time) {
        return time.substring(11, 16);
    }

}
