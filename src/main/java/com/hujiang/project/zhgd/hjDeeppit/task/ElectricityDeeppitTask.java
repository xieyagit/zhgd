
package com.hujiang.project.zhgd.hjDeeppit.task;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjDeeppit.domain.DeeppitAlarmData;
import com.hujiang.project.zhgd.hjDeeppit.domain.DeeppitPushData;
import com.hujiang.project.zhgd.hjDeeppit.domain.HjDeeppitData;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplay;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitFactor;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitGroup;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitToken;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbProjectDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbStationsListData;
import com.hujiang.project.zhgd.hjDeeppit.service.IDeeppitAlarmDataService;
import com.hujiang.project.zhgd.hjDeeppit.service.IHjDeeppitDataService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitDisplayService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitFactorService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitGroupService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitStructuresService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitTokenService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbProjectDeeppitStructuresService;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkAlarmData;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkData;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkAlarmDataService;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkDataService;
import com.hujiang.project.zhgd.sbProjectDustEmission.task.JPushSMS;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.DeeppitTools;
import com.hujiang.project.zhgd.utils.EncryptionUtil;
import com.hujiang.project.zhgd.utils.Util;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基坑、高支模定时任务
 */
@RestController
@RequestMapping(value = "/provider/ElectricityDeeppitTask")
@Component("ElectricityDeeppitTask")
public class ElectricityDeeppitTask extends AutoTaskBase {

    private static final Logger logger = LoggerFactory.getLogger(ElectricityDeeppitTask.class);

    //默认供应商id
    private Integer SUPPLIER = 0;

    @Autowired
    private DeeppitPushData jPushDeeppit;
    @Resource
    private JPushSMS jPushSMS;

    @Autowired
    ISbDeeppitTokenService sbDeeppitTokenService;
    @Autowired
    ISbDeeppitStructuresService sbDeeppitStructuresService;
    @Autowired
    ISbDeeppitDisplayService sbDeeppitDisplayService;
    @Autowired
    IHjDeeppitDataService hjDeeppitDataService;
    @Autowired
    ISbDeeppitFactorService sbDeeppitFactorService;
    @Autowired
    ISbDeeppitGroupService sbDeeppitGroupService;
    @Autowired
    ISbProjectDeeppitStructuresService sbProjectDeeppitStructuresService;
    @Autowired
    private IDeeppitAlarmDataService deeppitAlarmDataService;
    @Autowired
    private IHighformworkAlarmDataService highformworkAlarmDataService;
    @Autowired
    private IHighformworkDataService highformworkDataService;


    @Scheduled(cron = "0 0 12 ? * WED")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    tokens();
                } catch (Exception e) {
                    // logger
                }
            }
        });
    }

    @Scheduled(cron = "0 */2 * * * ?")
    public void task2() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    getStationAlarmDataAll();
                } catch (Exception e) {
                    // logger
                }
            }
        });
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void task3() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    insertStationDate();
                } catch (Exception e) {
                    // logger
                }
            }
        });
    }

    /**
     * 更新所有token
     * @return
     */
    @PostMapping("tokens")
    public AjaxResult tokens() {
        List<SbProjectDeeppitStructures> pdsl = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresList(null);
        for (SbProjectDeeppitStructures p : pdsl) {
            if (p.getAppD() != null || p.getAppSecret() != null) {
                token(p.getAppD(), p.getAppSecret(), p.getProjectId());
            }
        }
        return AjaxResult.success();
    }

    /**
     * 获取所有用户的报警信息
     */
    @PostMapping("/insert")
    public void getStationAlarmDataAll() {

        List<SbProjectDeeppitStructures> list = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresList(null);
        System.out.println(list);
        if (list == null) {
            return;
        }
        for (SbProjectDeeppitStructures s : list) {
            SbDeeppitStructures deeppitStructures = new SbDeeppitStructures();
            deeppitStructures.setReservedO(s.getProjectId().toString());
            List<SbDeeppitStructures> list1 = sbDeeppitStructuresService.selectSbDeeppitStructuresList(deeppitStructures);
            for (SbDeeppitStructures ds : list1) {
                getStationAlarmData(s.getProjectId(), s.getUserId(), ds.getId());
            }
        }
    }

    /**
     * 获取token--1
     * @param
     * @return
     */
    @PostMapping("token")
    public AjaxResult token(@RequestParam(value = "appId") String appId,
                            @RequestParam(value = "appSecret") String appSecret,
                            @RequestParam(value = "projectId") Integer projectId) {
        String authorization = Util.EncodeBase64(appId + ":" + appSecret);

        // 创建POST请求对象
        HttpPost httpPost = new HttpPost(Constants.LIFTING_PIT + "/oauth2/token");


        /*
         * 添加请求参数
         */
        // 创建请求参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param = new BasicNameValuePair("grant_type", "client_credentials");
        list.add(param);
        // 使用URL实体转换工具
        UrlEncodedFormEntity entityParam = null;
        try {
            entityParam = new UrlEncodedFormEntity(list, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*
         * 添加请求头信息
         */
        httpPost.addHeader("Authorization", "Basic " + authorization);
        httpPost.setEntity(entityParam);
        String s = DeeppitTools.postParams(httpPost);
        System.out.println(s);

        //将token存入数据库
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(s);
        SbDeeppitToken st = new SbDeeppitToken();
        st.setToken(jsonObject.getString("token"));
        st.setExpiration(jsonObject.getString("expires_in"));
        st.setAppId(projectId.toString());

        SbDeeppitToken dk = new SbDeeppitToken();
        dk.setAppId(projectId.toString());
        List<SbDeeppitToken> ldk = sbDeeppitTokenService.selectSbDeeppitTokenList(dk);

        if (ldk.size() > 0) {
            for (SbDeeppitToken t : ldk) {
                t.setToken(jsonObject.getString("token"));
                sbDeeppitTokenService.updateSbDeeppitToken(t);
            }
        } else {
            sbDeeppitTokenService.insertSbDeeppitToken(st);
        }
        return AjaxResult.success();
    }

    /**
     * 获取结构物列表--2
     * @return
     */
    @RequestMapping(value = "getStructures", method = RequestMethod.GET)
    public AjaxResult getStructures(@RequestParam(value = "projectId") Integer projectId) {
        AjaxResult ajaxResult = new AjaxResult();

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());
        List<SbDeeppitToken> listT = sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT == null) {
            return AjaxResult.error("没有这个项目的结构件");
        }
        if (listT.size() <= 0) {
            ajaxResult.put("code", 0);
            ajaxResult.put("msg", "token为空，请获取token");
            return ajaxResult;
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(Constants.LIFTING_PIT + "/structures/");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());
            BasicNameValuePair param = new BasicNameValuePair("token", token);
            list.add(param);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;

            String s = DeeppitTools.getParams(httpGet);
            System.out.println(s);

            //将数据存入数据库
            SbDeeppitStructures ds;
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(s);
            for (int i = 0; i < jsonArray.size(); i++) {
                net.sf.json.JSONObject jsonObj = jsonArray.getJSONObject(i);
                ds = new SbDeeppitStructures();
                ds.setId(jsonObj.getInt("id"));
                ds.setName(jsonObj.getString("name"));
                ds.setLatitude(jsonObj.getString("latitude"));
                ds.setLongitude(jsonObj.getString("longitude"));
                ds.setPortrait(jsonObj.getString("portrait"));

                net.sf.json.JSONObject type = (net.sf.json.JSONObject) jsonObj.get("type");
                ds.setTypeId(type.getInt("id"));
                ds.setTypeName(type.getString("name"));
                ds.setReservedO(projectId.toString());
                ds.setSupplier(SUPPLIER);
                String key = EncryptionUtil.encryptByAES(type.getInt("id") + SUPPLIER.toString(), Md5Utils.hash(SUPPLIER.toString()));
                ds.setMasterKey(key);


                /*SbProjectDeeppitStructures deeppitStructures = new SbProjectDeeppitStructures();
                deeppitStructures.setStructuresId(jsonObj.getInt("id"));
                deeppitStructures.setProjectId(projectId);
                List<SbProjectDeeppitStructures> lpd = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresList(deeppitStructures);
                if (lpd.size() == 0){
                    sbProjectDeeppitStructuresService.insertSbProjectDeeppitStructures(deeppitStructures);
                }*/

                if (sbDeeppitStructuresService.selectSbDeeppitStructuresByMasterKey(key) == null) {
                    sbDeeppitStructuresService.insertSbDeeppitStructures(ds);
                } else {
                    sbDeeppitStructuresService.updateSbDeeppitStructures(ds);
                }

            }

            ajaxResult.put("code", 1);
            ajaxResult.put("msg", s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxResult.put("code", 0);
        ajaxResult.put("data", "查询失败");
        return ajaxResult;
    }

    /**
     * 获取监测因素列表--3
     * @param structuresId 结构件id
     * @return
     */
    @RequestMapping(value = "getDisplay", method = RequestMethod.GET)
    public AjaxResult getDisplay(@RequestParam(value = "projectId") Integer projectId,
                                 @RequestParam(value = "structuresId") Integer structuresId) {
        AjaxResult ajaxResult = new AjaxResult();
        String urlS = Constants.LIFTING_PIT + "/structures/" + structuresId + "/factors";

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());

        List<SbDeeppitToken> listT = sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT.size() <= 0) {
            ajaxResult.put("code", 0);
            ajaxResult.put("msg", "token为空，请获取token");
            return ajaxResult;
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(urlS);
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());

            BasicNameValuePair param1 = new BasicNameValuePair("token", token);
            BasicNameValuePair param2 = new BasicNameValuePair("display", "");
            list.add(param1);
            list.add(param2);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            String s = DeeppitTools.getParams(httpGet);
            System.out.println(s);

            //将因素数据存入数据库
            SbDeeppitDisplay sdd = new SbDeeppitDisplay();
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(s);

            for (int i = 0; i < jsonArray.size(); i++) {
                net.sf.json.JSONObject jsonObj = jsonArray.getJSONObject(i);
                sdd.setId(jsonObj.getInt("id"));
                sdd.setName(jsonObj.getString("name"));
                sdd.setReserved(structuresId.toString());
                sdd.setSupplier(SUPPLIER);

                String key = EncryptionUtil.encryptByAES(jsonObj.getInt("id") + SUPPLIER.toString(), Md5Utils.hash(SUPPLIER.toString()));

                sdd.setDisplayKey(key);
                //保存items信息
                //JSONArray items = (JSONArray) jsonObj.get("items");
                //sdd.setItemsIndex(items.getInt("id"));
                SbDeeppitDisplay sd = sbDeeppitDisplayService.selectSbDeeppitDisplayById(jsonObj.getInt("id"));

                if (sd == null) {
                    sbDeeppitDisplayService.insertSbDeeppitDisplay(sdd);
                } else {
                    sbDeeppitDisplayService.updateSbDeeppitDisplay(sdd);
                }
            }


            ajaxResult.put("code", 1);
            ajaxResult.put("msg", s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }

        ajaxResult.put("code", 0);
        ajaxResult.put("data", "查询失败");
        return ajaxResult;
    }


    /**
     * 获取结构物下已配置的监测点--4
     * @return
     */
    @RequestMapping(value = "getStation", method = RequestMethod.GET)
    public AjaxResult getStation(@RequestParam(value = "projectId") Integer projectId,
                                 @RequestParam(value = "structuresId") Integer structuresId,
                                 @RequestParam(value = "displayId") Integer displayId) {
        AjaxResult ajaxResult = new AjaxResult();


        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());
        List<SbDeeppitToken> listT = sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT.size() <= 0) {
            ajaxResult.put("code", 0);
            ajaxResult.put("msg", "token为空，请获取token");
            return ajaxResult;
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(Constants.LIFTING_PIT + "/structures/" + structuresId + "/stations");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());

            BasicNameValuePair param1 = new BasicNameValuePair("factorId", displayId.toString());
            BasicNameValuePair param2 = new BasicNameValuePair("token", token);
            list.add(param1);
            list.add(param2);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            String s = DeeppitTools.getParams(httpGet);
            System.out.println(s);

            //将数据存入数据库
            net.sf.json.JSONArray sj = net.sf.json.JSONArray.fromObject(s);

            for (int l = 0; l < sj.size(); l++) {
                net.sf.json.JSONObject displays = sj.getJSONObject(l);
                net.sf.json.JSONArray display = displays.getJSONArray("groups");
                SbDeeppitGroup dg;
                SbDeeppitFactor df;

                //获取测点分组
                for (int i = 0; i < display.size(); i++) {
                    net.sf.json.JSONObject group = display.getJSONObject(i);
                    dg = new SbDeeppitGroup();
                    dg.setId(group.getInt("id"));
                    dg.setName(group.getString("name"));
                    dg.setReserved(displays.getString("factorId"));
                    dg.setSupplier(SUPPLIER);

                    String key = EncryptionUtil.encryptByAES(group.getInt("id") + SUPPLIER.toString(), Md5Utils.hash(SUPPLIER.toString()));

                    dg.setGroupKey(key);
                    SbDeeppitGroup sd = sbDeeppitGroupService.selectSbDeeppitGroupById(group.getInt("id"));
                    if (sd == null) {
                        sbDeeppitGroupService.insertSbDeeppitGroup(dg);
                    } else {
                        sbDeeppitGroupService.updateSbDeeppitGroup(dg);
                    }

                    //测点列表
                    net.sf.json.JSONArray stations = group.getJSONArray("stations");
                    for (int j = 0; j < stations.size(); j++) {
                        net.sf.json.JSONObject station = stations.getJSONObject(j);
                        df = new SbDeeppitFactor();
                        df.setId(station.getInt("id"));
                        df.setName(station.getString("name"));
                        df.setPortrait("portrait");
                        df.setLabels("labels");
                        df.setReserved(group.getString("id"));
                        df.setSupplier(SUPPLIER);

                        String keyFactor = EncryptionUtil.encryptByAES(station.getInt("id") + SUPPLIER.toString(), Md5Utils.hash(SUPPLIER.toString()));

                        df.setFactorKey(key);
                        SbDeeppitFactor sdf = sbDeeppitFactorService.selectSbDeeppitFactorById(station.getInt("id"));
                        if (sdf == null) {
                            sbDeeppitFactorService.insertSbDeeppitFactor(df);
                        } else {
                            sbDeeppitFactorService.updateSbDeeppitFactor(df);
                        }

                    }

                }
            }

            ajaxResult.put("code", 1);
            ajaxResult.put("msg", s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxResult.put("code", 0);
        ajaxResult.put("data", "查询失败");
        return ajaxResult;
    }

    @RequestMapping(value = "getStationData", method = RequestMethod.GET)
    public void insertStationDate() {
        List<SbStationsListData> pdsl = sbProjectDeeppitStructuresService.selectSbStationsList();
        String startStr = DateUtils.startDate();
        String endStr = DateUtils.endDate();
        for (SbStationsListData p : pdsl) {
            if (p.getFactorId() != null) {
                getStationData(p.getProjectId(), p.getFactorId().toString(), startStr, endStr);
            }
        }
    }

    /**
     * 获取基坑测点检测数据
     * @return
     */
    @RequestMapping(value = "getStationDatas", method = RequestMethod.GET)
    public void getStationData(@RequestParam(value = "projectId") Integer projectId,
                               @RequestParam(value = "stations") String stations,
                               @RequestParam(value = "startTime") String startTime,
                               @RequestParam(value = "endTime") String endTime) {

        AjaxResult ajaxResult = new AjaxResult();

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());

        List<SbDeeppitToken> listT = sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT.size() == 0) {
            return;
        }
        if (listT.size() <= 0) {
            ajaxResult.put("code", 0);
            ajaxResult.put("msg", "token为空，请获取token");
            return;
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(Constants.LIFTING_PIT + "/stations/theme/data");

            String token = String.valueOf(listT.get(0).getToken());

            List<NameValuePair> list = setParam(stations, startTime, endTime, token);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            String result = DeeppitTools.getParams(httpGet);

            //将数据存入数据库
            JSONObject sj = JSONObject.parseObject(result);
            //监测项
            JSONObject items = sj.getJSONObject("items");
            JSONArray fList = sj.getJSONArray("stations");
            JSONObject station;
            JSONArray fDataList;
            JSONObject fData;
            HjDeeppitData hjDeeppitData;

            List<HjDeeppitData> hjDeeppitDataList = hjDeeppitDataService.selectHjDeeppitDataByTime();
            for (int i = 0; i < fList.size(); i++) {
                station = fList.getJSONObject(i);
                fDataList = station.getJSONArray("data");
                Integer factorId = station.getIntValue("id");
                if (items.get("waterLevel") != null || items.get("force") != null) {
                    for (int j = 0; j < fDataList.size(); j++) {
                        fData = fDataList.getJSONObject(j);
                        String creation = fData.getString("time");
                        List<HjDeeppitData> hjDeeppitDatas = hjDeeppitDataList.stream().filter(a -> a.getFactorId().equals(factorId) && a.getCreation().equals(creation)).collect(Collectors.toList());
                        hjDeeppitData = new HjDeeppitData();
                        hjDeeppitData.setFactorId(station.getIntValue("id"));
                        hjDeeppitData.setFactorForce(fData.getString("force"));
                        hjDeeppitData.setWaterLevel(fData.getString("waterLevel"));
                        hjDeeppitData.setCreation(fData.getString("time"));
                        if (hjDeeppitDatas.size() <= 0) {
                            hjDeeppitDataService.insertHjDeeppitData(hjDeeppitData);
                        }else{
                            hjDeeppitDataService.updateHjDeeppitData(hjDeeppitData);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<NameValuePair> setParam(String stations, String startTime, String endTime, String token) {
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("stations", stations);
        BasicNameValuePair param2 = new BasicNameValuePair("startTime", startTime);
        BasicNameValuePair param3 = new BasicNameValuePair("endTime", endTime);
        BasicNameValuePair param5 = new BasicNameValuePair("token", token);
        list.add(param1);
        list.add(param2);
        list.add(param3);
        list.add(param5);
        return list;
    }

    /**
     * 获取高支模测点检测数据
     * @return
     */
    @RequestMapping(value = "getHgformworkData", method = RequestMethod.GET)
    public AjaxResult getHgformworkData(@RequestParam(value = "projectId") Integer projectId, @RequestParam(value = "structuresId") Integer structuresId, @RequestParam(value = "stations") String stations, @RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime") String endTime, @RequestParam(value = "limit") Integer limit) {
        AjaxResult ajaxResult = new AjaxResult();

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());

        List<SbDeeppitToken> listT = sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT.size() == 0) {
            return AjaxResult.error("没有这个项目的结构件");
        }
        if (listT.size() <= 0) {
            ajaxResult.put("code", 0);
            ajaxResult.put("msg", "token为空，请获取token");
            return ajaxResult;
        }
        try {
            // 创建POST请求对象

            URIBuilder uriBuilder = new URIBuilder(Constants.LIFTING_PIT + "/stations/theme/data");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());

            BasicNameValuePair param1 = new BasicNameValuePair("stations", stations);
            BasicNameValuePair param2 = new BasicNameValuePair("startTime", startTime);
            BasicNameValuePair param3 = new BasicNameValuePair("endTime", endTime);
            BasicNameValuePair param4 = new BasicNameValuePair("limit", limit.toString());
            BasicNameValuePair param5 = new BasicNameValuePair("token", token);

            list.add(param1);
            list.add(param2);
            list.add(param3);
            list.add(param4);
            list.add(param5);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            String s = DeeppitTools.getParams(httpGet);
            System.out.println(s);

            //将数据存入数据库
            net.sf.json.JSONObject sj = net.sf.json.JSONObject.fromObject(s);
            net.sf.json.JSONArray fList = sj.getJSONArray("stations");
            net.sf.json.JSONObject types = sj.getJSONObject("items");
            net.sf.json.JSONObject type = types.getJSONObject("displacement");
            net.sf.json.JSONObject station;
            net.sf.json.JSONArray fDataList;
            net.sf.json.JSONObject fData;
            HighformworkData highformworkData;

            for (int i = 0; i < fList.size(); i++) {
                station = fList.getJSONObject(i);
                fDataList = station.getJSONArray("data");
                switch (type.getString("name")) {
                    case "轴力":
                        for (int j = 0; j < fDataList.size(); j++) {
                            fData = fDataList.getJSONObject(j);
                            highformworkData = new HighformworkData();
                            highformworkData.setFactorId(station.getInt("id"));
                            highformworkData.setForce(fData.getString("force"));
                            highformworkData.setCreation(fData.getString("time"));
                            highformworkDataService.insertHighformworkData(highformworkData);
                        }
                        break;

                    case "位移":
                        for (int j = 0; j < fDataList.size(); j++) {
                            fData = fDataList.getJSONObject(j);
                            highformworkData = new HighformworkData();
                            highformworkData.setFactorId(station.getInt("id"));
                            highformworkData.setDisplacement(fData.getString("displacement"));
                            highformworkData.setCreation(fData.getString("time"));
                            highformworkDataService.insertHighformworkData(highformworkData);
                        }
                        break;
                    case "模板沉降":
                        for (int j = 0; j < fDataList.size(); j++) {
                            fData = fDataList.getJSONObject(j);
                            highformworkData = new HighformworkData();
                            highformworkData.setFactorId(station.getInt("id"));
                            highformworkData.setSubside(fData.getString("displacement"));
                            highformworkData.setCreation(fData.getString("time"));
                            highformworkDataService.insertHighformworkData(highformworkData);
                        }
                        break;

                    case "高支模倾斜":
                        for (int j = 0; j < fDataList.size(); j++) {
                            fData = fDataList.getJSONObject(j);
                            highformworkData = new HighformworkData();
                            highformworkData.setFactorId(station.getInt("id"));
                            highformworkData.setTiltX(fData.getString("x"));
                            highformworkData.setTiltY(fData.getString("y"));
                            highformworkData.setCreation(fData.getString("time"));
                            highformworkDataService.insertHighformworkData(highformworkData);
                        }
                        break;
                    default:
                        System.out.println("无此类型因素");
                        break;

                }
            }

            ajaxResult.put("code", 1);
            ajaxResult.put("msg", s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }

        ajaxResult.put("code", 0);
        ajaxResult.put("data", "获取失败！");
        return ajaxResult;
    }


    /**
     * 获取用户所有的报警信息
     * @return
     */
    //@RequestMapping(value = "getStationAlarmData" ,method = RequestMethod.POST)
    public AjaxResult getStationAlarmData(@RequestParam(value = "projectId") Integer projectId,
                                          @RequestParam(value = "userId") Integer userId,
                                          @RequestParam(value = "structuresId") int structuresId) {

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());

        List<SbDeeppitToken> listT = sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT.size() <= 0) {
            return AjaxResult.error("token为空，请获取token");
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(Constants.LIFTING_PIT + "/users/" + userId + "/alarms");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());

            BasicNameValuePair param1 = new BasicNameValuePair("token", token);
            BasicNameValuePair param2 = new BasicNameValuePair("limit", "5");
            BasicNameValuePair param3 = new BasicNameValuePair("offset", "0");
            BasicNameValuePair param4 = new BasicNameValuePair("orderBy", "level");
            BasicNameValuePair param5 = new BasicNameValuePair("orderDirection", "desc");
            List li = new ArrayList();
            li.add(structuresId);
            BasicNameValuePair param6 = new BasicNameValuePair("structures", li.toString());
            BasicNameValuePair param7 = new BasicNameValuePair("status", "new");

            list.add(param1);
            list.add(param2);
            list.add(param3);
            list.add(param4);
            list.add(param5);
            list.add(param6);
            list.add(param7);
            uriBuilder.setParameters(list);

            HttpPost httpPost = new HttpPost(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            /*
             * 添加请求头信息
             */
            httpPost.addHeader("Content-Type", "application/json");

            String s = DeeppitTools.postParams(httpPost);
            System.out.println(s);

            if (s == null) {
                return AjaxResult.error("请求失败");
            }


            //将数据存入数据库
            JSONObject sj = JSONObject.parseObject(s);
            JSONArray sAlarms = sj.getJSONArray("alarms");
            DeeppitAlarmData alarmData;
            SbDeeppitFactor deeppitFactor;
            HighformworkAlarmData highformworkAlarmData;

            for (int i = 0; i < sAlarms.size(); i++) {
                JSONObject alarm = sAlarms.getJSONObject(i);
                JSONArray al = (JSONArray) alarm.get("alarms");
                for (int j = 0; j < al.size(); j++) {
                    alarmData = new DeeppitAlarmData();
                    JSONObject all = al.getJSONObject(j);
                    JSONObject allSource = all.getJSONObject("source");
                    JSONObject allSourceType = all.getJSONObject("sourceType");

                    alarmData.setStructuresId(alarm.getIntValue("structureId"));
                    alarmData.setStructuresName(alarm.getString("structureName"));
                    alarmData.setId(all.getString("id"));
                    alarmData.setSourceId(allSource.getString("id"));
                    alarmData.setSourceName(allSource.getString("name"));
                    alarmData.setSourceTypeId(allSourceType.getIntValue("id"));
                    alarmData.setSourceTypeName(allSourceType.getString("name"));
                    alarmData.setAlarmTypeCode(all.getString("alarmTypeCode"));
                    alarmData.setLevel(all.getIntValue("level"));
                    alarmData.setContent(all.getString("content"));
                    alarmData.setCount(all.getIntValue("count"));
                    alarmData.setState(all.getIntValue("state"));

                    alarmData.setStartTime(all.getString("startTime"));
                    alarmData.setEndTime(all.getString("endTime"));
                    DeeppitAlarmData dAD = deeppitAlarmDataService.selectDeeppitAlarmDataById(all.getString("id"));
                    if (dAD == null) {
                        deeppitAlarmDataService.insertDeeppitAlarmData(alarmData);
                        System.out.println("获取报警数据" + new Date());
                        deeppitFactor = new SbDeeppitFactor();
                        deeppitFactor.setName(allSource.getString("name"));
                        List<SbDeeppitFactor> list1 = sbDeeppitFactorService.selectSbDeeppitFactorList(deeppitFactor);
                        jPushDeeppit.setTitle(alarm.getString("structureName") + "报警");

                        jPushDeeppit.setWorks(alarm.getString("structureName"));
                        jPushDeeppit.setTime(all.getString("endTime"));
                        jPushDeeppit.setAlarmType(allSource.getString("name"));
                        jPushDeeppit.setAlarmLevel(all.getString("level"));
                        jPushDeeppit.setAlarmDetails(all.getString("content"));

                        jPushDeeppit.setSn(String.valueOf(alarm.getIntValue("structureId")));

                        if (list1 != null) {
                            jPushSMS.JPushAndJSMS(jPushDeeppit, projectId);
                        }
                    }/*else if (highformworkAlarmDataService.selectHighformworkAlarmDataList()){

                    }*/
                }

            }
            return AjaxResult.success(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.error("查询失败");
    }


}
