package com.hujiang.project.zhgd.hjDeeppit.task;


import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjDeeppit.domain.*;
import com.hujiang.project.zhgd.hjDeeppit.service.*;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkAlarmData;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkData;
import com.hujiang.project.zhgd.hjghformwork.mapper.HighformworkDataMapper;
import com.hujiang.project.zhgd.hjghformwork.service.HighformworkAlarmDataServiceImpl;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkAlarmDataService;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkDataService;
import com.hujiang.project.zhgd.sbProjectDustEmission.task.JPushSMS;
import com.hujiang.project.zhgd.utils.DeeppitTools;
import com.hujiang.project.zhgd.utils.EncryptionUtil;
import com.hujiang.project.zhgd.utils.Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 基坑、高支模定时任务
 */
//@Component("ElectricityDeeppitTask")
    @RestController
    @RequestMapping(value = "/provider/ElectricityDeeppitTask")
public class ElectricityDeeppitTask {

    String url = "https://api.zhiwucloud.com/api/v1";
    //默认供应商id
    private  Integer SUPPLIER = 0;

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


    /**
     * 更新所有token
     * @return
     */
    //@Scheduled(cron="0 0 12 ? * WED")
    @PostMapping("tokens")
    public AjaxResult tokens(){
        List<SbProjectDeeppitStructures> pdsl = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresList(null);
        for (SbProjectDeeppitStructures p :pdsl){
            if (p.getAppD() != null || p.getAppSecret() != null){
                token(p.getAppD(),p.getAppSecret(),p.getProjectId());
            }
        }
        return AjaxResult.success();
    }

    /**
     * 获取所有用户的报警信息
     */
    //@Scheduled(cron="0 */2 * * * ?")
    @PostMapping("/insert")
    public void getStationAlarmDataAll(){

        List<SbProjectDeeppitStructures> list = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresList(null);
        System.out.println(list);
        if (list == null){
            return;
        }
        for (SbProjectDeeppitStructures s:list){
            SbDeeppitStructures deeppitStructures = new SbDeeppitStructures();
            deeppitStructures.setReservedO(s.getProjectId().toString());
            List<SbDeeppitStructures> list1 = sbDeeppitStructuresService.selectSbDeeppitStructuresList(deeppitStructures);
            for (SbDeeppitStructures ds :list1) {
                getStationAlarmData(s.getProjectId(), s.getUserId(), ds.getId());
            }
        }
    }

    /**
     * 获取token
     * @param
     * @return
     */
    @PostMapping("token")
    public AjaxResult token(@RequestParam(value = "appId") String appId, @RequestParam(value = "appSecret") String appSecret, @RequestParam(value = "projectId") Integer projectId){
        String authorization = Util.EncodeBase64(appId+":"+appSecret);

        // 创建POST请求对象
        HttpPost httpPost = new HttpPost(url+"/oauth2/token");


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
        httpPost.addHeader("Authorization", "Basic "+authorization);
        //httpPost.addHeader("Content-Type", "application/json");
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
        if(ldk != null){
            for (SbDeeppitToken t:ldk){
                t.setToken(jsonObject.getString("token"));
                sbDeeppitTokenService.updateSbDeeppitToken(t);
            }
        }else {
            int sum = sbDeeppitTokenService.insertSbDeeppitToken(st);
        }
        return AjaxResult.error();
    }

    /**
     * 获取结构物列表
     * @return
     */
    @RequestMapping(value = "getStructures" ,method = RequestMethod.GET)
    public AjaxResult getStructures(@RequestParam(value = "projectId") Integer projectId){
        AjaxResult ajaxResult = new AjaxResult();

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());
        List<SbDeeppitToken> listT = sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if(listT ==null){
            return AjaxResult.error("没有这个项目的结构件");
        }
        if (listT.size() <= 0){
            ajaxResult.put("code",0);
            ajaxResult.put("msg","token为空，请获取token");
            return ajaxResult;
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(url+"/structures/");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());
            BasicNameValuePair param = new BasicNameValuePair("token",token);
            list.add(param);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            /*
             * 添加请求头信息
             */
            httpGet.addHeader("Content-Type", "application/json");
            String s = DeeppitTools.getParams(httpGet);
            System.out.println(s);

            //将数据存入数据库
            SbDeeppitStructures ds;
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(s);
            for (int i = 0;i < jsonArray.size();i++){
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
                String key = EncryptionUtil.encryptByAES(type.getInt("id")+SUPPLIER.toString(), Md5Utils.hash(SUPPLIER.toString()));
                ds.setMasterKey(key);


                /*SbProjectDeeppitStructures deeppitStructures = new SbProjectDeeppitStructures();
                deeppitStructures.setStructuresId(jsonObj.getInt("id"));
                deeppitStructures.setProjectId(projectId);
                List<SbProjectDeeppitStructures> lpd = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresList(deeppitStructures);
                if (lpd.size() == 0){
                    sbProjectDeeppitStructuresService.insertSbProjectDeeppitStructures(deeppitStructures);
                }*/

                if(sbDeeppitStructuresService.selectSbDeeppitStructuresByMasterKey(key) == null){
                    sbDeeppitStructuresService.insertSbDeeppitStructures(ds);
                }else {
                    sbDeeppitStructuresService.updateSbDeeppitStructures(ds);
                }

            }

            ajaxResult.put("code",1);
            ajaxResult.put("msg",s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxResult.put("code",0);
        ajaxResult.put("data","查询失败");
        return ajaxResult;
    }

    /**
     * 获取监测因素列表
     * @param structuresId 结构件id
     * @return
     */
    @RequestMapping(value = "getDisplay" ,method = RequestMethod.GET)
    public AjaxResult getDisplay(@RequestParam(value = "projectId") Integer projectId,@RequestParam(value = "structuresId") Integer structuresId){
        AjaxResult ajaxResult = new AjaxResult();
        String urlS =url+"/structures/"+structuresId+"/factors";

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());
        List<SbDeeppitToken> listT= sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT.size() <= 0){
            ajaxResult.put("code",0);
            ajaxResult.put("msg","token为空，请获取token");
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
            BasicNameValuePair param1 = new BasicNameValuePair("token",token);
            BasicNameValuePair param2 = new BasicNameValuePair("display","");
            list.add(param1);
            list.add(param2);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            /*
             * 添加请求头信息
             */
            httpGet.addHeader("Content-Type", "application/json");
            String s = DeeppitTools.getParams(httpGet);
            System.out.println(s);

            //将因素数据存入数据库
            SbDeeppitDisplay sdd = new SbDeeppitDisplay();
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(s);
            for (int i = 0;i < jsonArray.size();i++){
                net.sf.json.JSONObject jsonObj = jsonArray.getJSONObject(i);
                sdd.setId(jsonObj.getInt("id"));
                sdd.setName(jsonObj.getString("name"));
                sdd.setReserved(structuresId.toString());
                sdd.setSupplier(SUPPLIER);
                String key = EncryptionUtil.encryptByAES(jsonObj.getInt("id")+SUPPLIER.toString(), Md5Utils.hash(SUPPLIER.toString()));
                sdd.setDisplayKey(key);
                //保存items信息
                //JSONArray items = (JSONArray) jsonObj.get("items");
                //sdd.setItemsIndex(items.getInt("id"));
                SbDeeppitDisplay sd = sbDeeppitDisplayService.selectSbDeeppitDisplayById(jsonObj.getInt("id"));

                if(sd == null){
                    sbDeeppitDisplayService.insertSbDeeppitDisplay(sdd);
                }else {
                    sbDeeppitDisplayService.updateSbDeeppitDisplay(sdd);
                }
            }




            ajaxResult.put("code",1);
            ajaxResult.put("msg",s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxResult.put("code",0);
        ajaxResult.put("data","查询失败");
        return ajaxResult;
    }


    /**
     * 获取结构物下已配置的监测点
     * @return
     */
    @RequestMapping(value = "getStation" ,method = RequestMethod.GET)
    public AjaxResult getStation(@RequestParam(value = "projectId") Integer projectId,@RequestParam(value = "structuresId") Integer structuresId,@RequestParam(value = "displayId") Integer displayId){
        AjaxResult ajaxResult = new AjaxResult();


        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());
        List<SbDeeppitToken> listT= sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT.size() <= 0){
            ajaxResult.put("code",0);
            ajaxResult.put("msg","token为空，请获取token");
            return ajaxResult;
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(url+"/structures/"+structuresId+"/stations");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());
            BasicNameValuePair param1 = new BasicNameValuePair("factorId",displayId.toString());
            BasicNameValuePair param2 = new BasicNameValuePair("token",token);
            list.add(param1);
            list.add(param2);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            /*
             * 添加请求头信息
             */
            httpGet.addHeader("Content-Type", "application/json");
            String s = DeeppitTools.getParams(httpGet);
            System.out.println(s);

            //将数据存入数据库
            net.sf.json.JSONArray sj = net.sf.json.JSONArray.fromObject(s);
            for (int l = 0;l  < sj.size(); l++) {
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
                    String key = EncryptionUtil.encryptByAES(group.getInt("id")+SUPPLIER.toString(), Md5Utils.hash(SUPPLIER.toString()));
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
                        String keyFactor = EncryptionUtil.encryptByAES(station.getInt("id")+SUPPLIER.toString(), Md5Utils.hash(SUPPLIER.toString()));
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

            ajaxResult.put("code",1);
            ajaxResult.put("msg",s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxResult.put("code",0);
        ajaxResult.put("data","查询失败");
        return ajaxResult;
    }



    /**
     * 获取基坑测点检测数据
     * @return
     */
    @RequestMapping(value = "getStationData" ,method = RequestMethod.GET)
    public AjaxResult getStationData(@RequestParam(value = "projectId") Integer projectId,@RequestParam(value = "structuresId") Integer structuresId,@RequestParam(value = "stations") String stations, @RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime") String endTime, @RequestParam(value = "limit") Integer limit){
        AjaxResult ajaxResult = new AjaxResult();

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());
        List<SbDeeppitToken> listT= sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if(listT.size() ==0){
            return AjaxResult.error("没有这个项目的结构件");
        }
        if (listT.size() <= 0){
            ajaxResult.put("code",0);
            ajaxResult.put("msg","token为空，请获取token");
            return ajaxResult;
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(url+"/stations/theme/data");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());
            BasicNameValuePair param1 = new BasicNameValuePair("stations",stations);
            BasicNameValuePair param2 = new BasicNameValuePair("startTime",startTime);
            BasicNameValuePair param3 = new BasicNameValuePair("endTime",endTime);
            BasicNameValuePair param4 = new BasicNameValuePair("limit",limit.toString());
            BasicNameValuePair param5 = new BasicNameValuePair("token",token);
            list.add(param1);
            list.add(param2);
            list.add(param3);
            list.add(param4);
            list.add(param5);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            /*
             * 添加请求头信息
             */
            httpGet.addHeader("Content-Type", "application/json");
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
            HjDeeppitData hjDeeppitData;

            for (int i = 0;i  < fList.size(); i++) {
                station = fList.getJSONObject(i);
                fDataList = station.getJSONArray("data");
                switch(type.getString("name")){
                    case "位移" :
                        for (int j =0;j <fDataList.size();j++){
                            fData =fDataList.getJSONObject(j);
                            hjDeeppitData =new HjDeeppitData();
                            hjDeeppitData.setFactorId(station.getInt("id"));
                            hjDeeppitData.setSubside(fData.getString("displacement"));
                            hjDeeppitData.setCreation(fData.getString("time"));
                            hjDeeppitDataService.insertHjDeeppitData(hjDeeppitData);
                        }
                        break;
                    case "深层水平位移" :
                        for (int j =0;j <fDataList.size();j++){
                            fData =fDataList.getJSONObject(j);
                            hjDeeppitData =new HjDeeppitData();
                            hjDeeppitData.setFactorId(station.getInt("id"));
                            //hjDeeppitData.setLevelX();
                            //hjDeeppitData.setLevelY();
                            //hjDeeppitData.setLevelAccumulateX();
                            //hjDeeppitData.setLevelAccumulateY();
                            hjDeeppitData.setCreation(fData.getString("time"));
                            hjDeeppitDataService.insertHjDeeppitData(hjDeeppitData);
                        }
                        break;
                    case "建筑物倾斜" :
                        for (int j =0;j <fDataList.size();j++){
                            fData =fDataList.getJSONObject(j);
                            hjDeeppitData =new HjDeeppitData();
                            hjDeeppitData.setFactorId(station.getInt("id"));
                            hjDeeppitData.setSubside(fData.getString("displacement"));
                            hjDeeppitData.setCreation(fData.getString("time"));
                            hjDeeppitDataService.insertHjDeeppitData(hjDeeppitData);
                        }
                        break;
                    case "应变原始数据" :
                        for (int j =0;j <fDataList.size();j++){
                            fData =fDataList.getJSONObject(j);
                            hjDeeppitData =new HjDeeppitData();
                            hjDeeppitData.setFactorId(station.getInt("id"));
                            hjDeeppitData.setSubside(fData.getString("displacement"));
                            hjDeeppitData.setCreation(fData.getString("time"));
                            hjDeeppitDataService.insertHjDeeppitData(hjDeeppitData);
                        }
                        break;
                    default:
                        System.out.println("无此类型因素");
                        break;

                }
            }

            ajaxResult.put("code",1);
            ajaxResult.put("msg",s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxResult.put("code",0);
        ajaxResult.put("data","获取失败！");
        return ajaxResult;
    }

    /**
     * 获取高支模测点检测数据
     * @return
     */
    @RequestMapping(value = "getHgformworkData" ,method = RequestMethod.GET)
    public AjaxResult getHgformworkData(@RequestParam(value = "projectId") Integer projectId,@RequestParam(value = "structuresId") Integer structuresId,@RequestParam(value = "stations") String stations, @RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime") String endTime, @RequestParam(value = "limit") Integer limit){
        AjaxResult ajaxResult = new AjaxResult();

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());
        List<SbDeeppitToken> listT= sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if(listT.size() ==0){
            return AjaxResult.error("没有这个项目的结构件");
        }
        if (listT.size() <= 0){
            ajaxResult.put("code",0);
            ajaxResult.put("msg","token为空，请获取token");
            return ajaxResult;
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(url+"/stations/theme/data");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());
            BasicNameValuePair param1 = new BasicNameValuePair("stations",stations);
            BasicNameValuePair param2 = new BasicNameValuePair("startTime",startTime);
            BasicNameValuePair param3 = new BasicNameValuePair("endTime",endTime);
            BasicNameValuePair param4 = new BasicNameValuePair("limit",limit.toString());
            BasicNameValuePair param5 = new BasicNameValuePair("token",token);
            list.add(param1);
            list.add(param2);
            list.add(param3);
            list.add(param4);
            list.add(param5);
            uriBuilder.setParameters(list);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 使用URL实体转换工具
            UrlEncodedFormEntity entityParam = null;
            /*
             * 添加请求头信息
             */
            httpGet.addHeader("Content-Type", "application/json");
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

            for (int i = 0;i  < fList.size(); i++) {
                station = fList.getJSONObject(i);
                fDataList = station.getJSONArray("data");
                switch(type.getString("name")){
                    case "轴力" :
                        for (int j =0;j <fDataList.size();j++){
                            fData =fDataList.getJSONObject(j);
                            highformworkData =new HighformworkData();
                            highformworkData.setFactorId(station.getInt("id"));
                            highformworkData.setForce(fData.getString("force"));
                            highformworkData.setCreation(fData.getString("time"));
                            highformworkDataService.insertHighformworkData(highformworkData);
                        }
                        break;
                    case "位移" :
                        for (int j =0;j <fDataList.size();j++){
                            fData =fDataList.getJSONObject(j);
                            highformworkData =new HighformworkData();
                            highformworkData.setFactorId(station.getInt("id"));
                            highformworkData.setDisplacement(fData.getString("displacement"));
                            highformworkData.setCreation(fData.getString("time"));
                            highformworkDataService.insertHighformworkData(highformworkData);
                        }
                        break;
                    case "模板沉降" :
                        for (int j =0;j <fDataList.size();j++){
                            fData =fDataList.getJSONObject(j);
                            highformworkData =new HighformworkData();
                            highformworkData.setFactorId(station.getInt("id"));
                            highformworkData.setSubside(fData.getString("displacement"));
                            highformworkData.setCreation(fData.getString("time"));
                            highformworkDataService.insertHighformworkData(highformworkData);
                        }
                        break;
                    case "高支模倾斜" :
                        for (int j =0;j <fDataList.size();j++){
                            fData =fDataList.getJSONObject(j);
                            highformworkData =new HighformworkData();
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

            ajaxResult.put("code",1);
            ajaxResult.put("msg",s);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxResult.put("code",0);
        ajaxResult.put("data","获取失败！");
        return ajaxResult;
    }


    /**
     * 获取用户所有的报警信息
     * @return
     */
    //@RequestMapping(value = "getStationAlarmData" ,method = RequestMethod.POST)
    public AjaxResult getStationAlarmData(@RequestParam(value = "projectId") Integer projectId,@RequestParam(value = "userId") Integer userId,@RequestParam(value = "structuresId") int structuresId){

        //获取token
        SbDeeppitToken st = new SbDeeppitToken();
        st.setAppId(projectId.toString());
        List<SbDeeppitToken> listT= sbDeeppitTokenService.selectSbDeeppitTokenList(st);
        if (listT.size() <= 0){
            return AjaxResult.error("token为空，请获取token");
        }
        try {
            // 创建POST请求对象
            URIBuilder uriBuilder = new URIBuilder(url+"/users/"+userId+"/alarms");
            /*
             * 添加请求参数
             */
            List<NameValuePair> list = new LinkedList<>();
            String token = String.valueOf(listT.get(0).getToken());
            BasicNameValuePair param1 = new BasicNameValuePair("token",token);
            BasicNameValuePair param2 = new BasicNameValuePair("limit","5");
            BasicNameValuePair param3 = new BasicNameValuePair("offset","0");
            BasicNameValuePair param4 = new BasicNameValuePair("orderBy","level");
            BasicNameValuePair param5 = new BasicNameValuePair("orderDirection","desc");
            List li = new ArrayList();
            li.add(structuresId);
            BasicNameValuePair param6 = new BasicNameValuePair("structures",li.toString());
            BasicNameValuePair param7 = new BasicNameValuePair("status","new");
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
            if (s == null){
                return AjaxResult.error("请求失败");
            }



            //将数据存入数据库
            net.sf.json.JSONObject sj = net.sf.json.JSONObject.fromObject(s);
            net.sf.json.JSONArray sAlarms = sj.getJSONArray("alarms");
            DeeppitAlarmData alarmData;
            SbDeeppitFactor deeppitFactor;
            HighformworkAlarmData highformworkAlarmData;
            for (int i = 0;i <sAlarms.size();i++){
                net.sf.json.JSONObject alarm = sAlarms.getJSONObject(i);
                JSONArray al = alarm.getJSONArray("alarms");
                for (int j = 0;j < al.size();j++) {
                    alarmData = new DeeppitAlarmData();
                    JSONObject all = al.getJSONObject(j);
                    JSONObject allSource = all.getJSONObject("source");
                    JSONObject allSourceType = all.getJSONObject("sourceType");

                    alarmData.setStructuresId(alarm.getInt("structureId"));
                    alarmData.setStructuresName(alarm.getString("structureName"));
                    alarmData.setId(all.getString("id"));
                    alarmData.setSourceId(allSource.getString("id"));
                    alarmData.setSourceName(allSource.getString("name"));
                    alarmData.setSourceTypeId(allSourceType.getInt("id"));
                    alarmData.setSourceTypeName(allSourceType.getString("name"));
                    alarmData.setAlarmTypeCode(all.getString("alarmTypeCode"));
                    alarmData.setLevel(all.getInt("level"));
                    alarmData.setContent(all.getString("content"));
                    alarmData.setCount(all.getInt("count"));
                    alarmData.setState(all.getInt("state"));

                    alarmData.setStartTime(all.getString("startTime"));
                    alarmData.setEndTime(all.getString("endTime"));
                    DeeppitAlarmData dAD = deeppitAlarmDataService.selectDeeppitAlarmDataById(all.getString("id"));
                    if (dAD == null) {
                        deeppitAlarmDataService.insertDeeppitAlarmData(alarmData);
                        System.out.println("获取报警数据"+new Date());
                        deeppitFactor = new SbDeeppitFactor();
                        deeppitFactor.setName(allSource.getString("name"));
                        List<SbDeeppitFactor> list1 = sbDeeppitFactorService.selectSbDeeppitFactorList(deeppitFactor);
                        jPushDeeppit.setTitle(alarm.getString("structureName")+"报警");
                        jPushDeeppit.setWorks(alarm.getString("structureName"));
                        jPushDeeppit.setTime(all.getString("endTime"));
                        jPushDeeppit.setAlarmType(allSource.getString("name"));
                        jPushDeeppit.setAlarmLevel(all.getString("level"));
                        jPushDeeppit.setAlarmDetails(all.getString("content"));
                        jPushDeeppit.setSn(String.valueOf(alarm.getInt("structureId")));

                        if (list1 != null){
                            jPushSMS.JPushAndJSMS(jPushDeeppit,projectId);
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
