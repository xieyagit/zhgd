package com.hujiang.project.xiamenandgongwushu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.xiamenandgongwushu.task.GWSTask;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.ZCAPIClientTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/gwsApi")
public class XMAndGWSApi {
    @Autowired
    private IHjDictionariesService hjDictionariesService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;

    /**
     * 保存班组类型
     */
    @PostMapping("/setTeamType")
    public void setTeamType() {
        //工务署班组类型
        String s = "{\"result\":\"true\",\"code\":\"00\",\"status\":\"200\",\"message\":\"成功\",\"result_data\":{\"dict_list\":[{\"orders\":\"10\",\"value\":\"电气设备安装调试工\"},{\"orders\":\"11\",\"value\":\"管道工\"},{\"orders\":\"12\",\"value\":\"变电安装工\"},{\"orders\":\"13\",\"value\":\"建筑电工\"},{\"orders\":\"14\",\"value\":\"司泵工\"},{\"orders\":\"15\",\"value\":\"挖掘铲运和桩工机械司机\"},{\"orders\":\"16\",\"value\":\"桩机操作工\"},{\"orders\":\"17\",\"value\":\"起重信号工\"},{\"orders\":\"18\",\"value\":\"建筑起重机械安装拆卸工\"},{\"orders\":\"19\",\"value\":\"装饰装修工\"},{\"orders\":\"2\",\"value\":\"钢筋工\"},{\"orders\":\"20\",\"value\":\"室内成套设施安装工\"},{\"orders\":\"21\",\"value\":\"建筑门窗幕墙安装工\"},{\"orders\":\"22\",\"value\":\"幕墙制作工\"},{\"orders\":\"23\",\"value\":\"防水工\"},{\"orders\":\"24\",\"value\":\"石工\"},{\"orders\":\"25\",\"value\":\"泥塑工\"},{\"orders\":\"26\",\"value\":\"电焊工\"},{\"orders\":\"27\",\"value\":\"爆破工\"},{\"orders\":\"28\",\"value\":\"除尘工\"},{\"orders\":\"29\",\"value\":\"测量放线工\"},{\"orders\":\"3\",\"value\":\"架子工\"},{\"orders\":\"30\",\"value\":\"线路架设工\"},{\"orders\":\"31\",\"value\":\"古建筑传统木工\"},{\"orders\":\"32\",\"value\":\"古建筑传统瓦工\"},{\"orders\":\"33\",\"value\":\"古建筑传统石工\"},{\"orders\":\"34\",\"value\":\"古建筑传统彩画工\"},{\"orders\":\"35\",\"value\":\"古建筑传统油工\"},{\"orders\":\"36\",\"value\":\"金属工\"},{\"orders\":\"37\",\"value\":\"管理人员\"},{\"orders\":\"38\",\"value\":\"杂工\"},{\"orders\":\"39\",\"value\":\"其它\"},{\"orders\":\"4\",\"value\":\"混凝土工\"},{\"orders\":\"40\",\"value\":\"木工\"},{\"orders\":\"41\",\"value\":\"机械司机\"},{\"orders\":\"42\",\"value\":\"高级熟练工\"},{\"orders\":\"5\",\"value\":\"模板工\"},{\"orders\":\"6\",\"value\":\"机械设备安装工\"},{\"orders\":\"7\",\"value\":\"通风工\"},{\"orders\":\"8\",\"value\":\"起重工\"},{\"orders\":\"9\",\"value\":\"安装钳工\"}]}}\n";
        JSONObject jsonObject = JSONObject.parseObject(s);
        String a = jsonObject.getString("result_data");
        JSONObject b = JSONObject.parseObject(a);
        String data = b.getString("dict_list");
        JSONArray datas = JSONArray.parseArray(data);
        JSONObject c;
        HjDictionaries dict;
        for (Object o : datas) {
            c = JSONObject.parseObject(o.toString());
            dict = new HjDictionaries();
            dict.setTag(c.getString("orders"));
            dict.setTitle(c.getString("value"));

            dict.setGroupTitle("工务署工种类型");
            dict.setCategory("WORK_TYPE_GWS");
            hjDictionariesService.insertHjDictionaries(dict);

        }
    }

    @PostMapping(value = "/test")
    public void a() {
        HjProjectWorkers hp = new HjProjectWorkers();
        hp.setProjectId(4);
        List<HjProjectWorkers> hList = hjProjectWorkersService.selectHjProjectWorkersList(hp);
        HjAttendanceRecord ha;
        for (HjProjectWorkers h : hList) {
            ha = new HjAttendanceRecord();
            ha.setProjectId(4);
            ha.setEmployeeId(h.getId());
            ha.setPassedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            ha.setDirection("in");
            ha.setWay(1);
            ha.setSitePhoto(h.getFaceUrl());
            ha.setUploadTime(ha.getPassedTime());

            hjAttendanceRecordService.insertHjAttendanceRecord(ha);
        }

    }

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    @PostMapping(value = "/b")
    public void b() throws Exception {
        HjSynchronizationInformation hs = new HjSynchronizationInformation();
        hs.setState(1);
        hs.setProjectId(389);
        hs.setPlatformName("WORKSBUREAU");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hs2List = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        //有秘钥才需要对接
        if (hs2List.size() > 0) {
            HjSynchronizationInformation h = hs2List.get(0);
            JSONObject body = new JSONObject();
            body.put("Company_Name", "中建三局第一建设工程有限责任公司");
            body.put("SUID", "914201007483157744");
            body.put("Project_ID", h.getEngineeringCode());
            body.put("exit_time","2020-04-07 00:00:00");
//            JSONArray like=new JSONArray();
//            JSONObject a = new JSONObject();
//            a.put("id_code","430381199602160411");
//            a.put("exit_time","2020-04-07 00:00:00");
//            like.add(a);
//            body.put("userLeaveProject_list",like);
            String abc = dockingGWS(body, h, "ProjectRemoveCompany");
            System.out.println("222"+abc);
        }

    }
    @PostMapping(value = "/c")
    public void c() throws Exception {
        HjSynchronizationInformation hs = new HjSynchronizationInformation();
        hs.setState(1);
        hs.setProjectId(389);
        hs.setPlatformName("WORKSBUREAU");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hs2List = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        //有秘钥才需要对接
        if (hs2List.size() > 0) {
            HjSynchronizationInformation h = hs2List.get(0);
            HjProjectWorkers hw=new HjProjectWorkers();
            hw.setProjectId(389);
            hw.setEnterAndRetreatCondition(0);
            hw.setConstructionId(10406);
            List<HjProjectWorkers> list=hjProjectWorkersService.selectHjProjectWorkersList(hw);
            HjProjectWorkers hpw;
            for(int i=0;i<list.size();i++){
                hpw=list.get(i);
                JSONObject body = new JSONObject();
                JSONArray like=new JSONArray();
            JSONObject a = new JSONObject();
            a.put("id_code",hpw.getIdCode());
            a.put("exit_time","2020-04-07 00:00:00");
            like.add(a);
            body.put("userLeaveProject_list",like);
                String abc = dockingGWS(body, h, "userLeaveProject");
                if("true".equals(JSONObject.parseObject(abc).getString("result"))){
                    hpw.setEnterAndRetreatCondition(1);
                    hjProjectWorkersService.updateHjProjectWorkers(hpw);
                }
            }
        }
    }
    private String dockingGWS(JSONObject json,HjSynchronizationInformation hs,String url) throws  URISyntaxException, IOException
    {

        url= Constants.ZC_FORMALHOST+url;
        //添加工程编号
        json.put("Project_ID",hs.getEngineeringCode());
        System.out.println(json);
        //拼装完整URL
        String s= ZCAPIClientTwo.getUrl(hs.getApiSecret(),hs.getApiKey(),"1.0",hs.getApiKey(),json.toString(),url);
//        System.out.println(s);
        //访问接口得到返回值
        String result= ZCAPIClientTwo.httpPostWithJSON(s,json);

        return result;
    }
}
