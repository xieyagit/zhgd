package com.hujiang.project.zhgd.sbProjectDustEmission.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.consumer.TspPersonnelService;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import com.hujiang.project.cay.*;

import javax.jms.Queue;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-18 15:36
 **/
@RestController
@RequestMapping(value = "/provider/ProjectDustEmission",method = RequestMethod.POST)
public class PcProjectDustEmissionApi extends BaseController {
    private Logger logger = Logger.getLogger(PcProjectDustEmissionApi.class.getName());

    @Autowired
    private ISbProjectDustEmissionService projectDustEmissionService;

    @Autowired
    private Queue tspPersonnelQueue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private IHjProjectService iHjProjectService;

    private static final String MD_TYPEMD_TYPE = "环境";//设备类型
    @Autowired
    private  cay cay;

    @Autowired
    TspPersonnelService tspPersonnelService;

    /**
     * 根据项目id查询项目扬尘设备列表
     * @param projectId
     * @return
     */
    @PostMapping("getProjectDustEmission")
    public JSONObject getProjectDustEmission(@RequestParam(value = "projectId")Long projectId){
        JSONObject result = new JSONObject();
        SbProjectDustEmission projectDustEmission = new SbProjectDustEmission();
        projectDustEmission.setProjectId(projectId);
        //查询项目设备
        List<SbProjectDustEmission> sbProjectDustEmissions = projectDustEmissionService.selectSbProjectDustEmissionList(projectDustEmission);
        logger.info("com.hujiang.project.zhgd.sbProjectDustEmission.api.ProjectDustEmissionApi.getProjectDustEmission" +
                "查询项目设备数："+sbProjectDustEmissions.size());
        if(sbProjectDustEmissions!=null && sbProjectDustEmissions.size()>0){
            result.put("msg","查询设备列表成功");
            result.put("code",0);
            JSONArray array = new JSONArray();
            for(SbProjectDustEmission sbProjectDustEmission:sbProjectDustEmissions){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title",sbProjectDustEmission.getComments());
                jsonObject.put("sn",sbProjectDustEmission.getSn());
                jsonObject.put("jdbh",sbProjectDustEmission.getJdbh());
                jsonObject.put("videoAddress",sbProjectDustEmission.getVideoAddress() );
                array.add(jsonObject);
            }
            result.put("data",array);
        }else{
            result.put("msg","无设备");
            result.put("code",-1);
        }

        return result;
    }


    /**
     * 查询项目对应的扬尘设备SN列表
     */
    @PostMapping("/projectDustEmissionList")
    public AjaxResult projectDustEmissionList(@RequestBody SbProjectDustEmission sbProjectDustEmission)
    {
        startPage();
        List<SbProjectDustEmission> list = projectDustEmissionService.getSbProjectDustEmissionList(sbProjectDustEmission);
        return AjaxResult.success(getDataTable(list));
    }
    /**
     * 新增保存项目对应的扬尘设备SN
     */
    @PostMapping("/projectDustEmissionAddSave")
    public AjaxResult projectDustEmissionAddSave(@RequestBody SbProjectDustEmission sbProjectDustEmission) throws IOException, URISyntaxException {

//        cayTsp(sbProjectDustEmission);
        int i = projectDustEmissionService.insertSbProjectDustEmission(sbProjectDustEmission);
        if(i>0){

            String apiKey = tspPersonnelService.getApikey(sbProjectDustEmission.getProjectId());
            if(apiKey == null || !"".equals(apiKey)) {
                return success();
            }
            JmsMessageInfo<SbProjectDustEmission> message = new JmsMessageInfo<SbProjectDustEmission>();
            message.setBody(sbProjectDustEmission);
            message.setType(JmsMessageType.Machine);
            message.setProjectId(sbProjectDustEmission.getProjectId());

            jmsMessagingTemplate.convertAndSend(tspPersonnelQueue, JsonUtils.toJson(message));

            return success();
        }
        return error(-1,"添加失败");
    }

    /** 上报城安院环境监测设备信息*/
    public String cayTsp(SbProjectDustEmission sbProjectDustEmission) throws IOException, URISyntaxException {
        JSONArray body =new JSONArray();
        JSONObject js1=new JSONObject();
        Integer projectId = null;
        String f = null;
        HjProject hjProject = iHjProjectService.selectHjProjectById(Integer.valueOf(String.valueOf(sbProjectDustEmission.getProjectId())));
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("curpage","1");
        jsonObject1.put("name",hjProject.getProjectName());
        /** 区管项目*/
        if (sbProjectDustEmission.getSubId().equals("")) {
            js1.put("PROJECT_ID", sbProjectDustEmission.getXmid());//所属项目编号
            js1.put("Jdbh", sbProjectDustEmission.getJdbh());//项目监督编号
            js1.put("DEV_GUID",sbProjectDustEmission.getSn());//设备编号
            js1.put("MD_NAME",sbProjectDustEmission.getComments());//设备名称
            js1.put("MD_TYPE",MD_TYPEMD_TYPE);//设备类型（“环境”固定死）
            body.add(js1);
            JSONObject object = new JSONObject();
            object.put("PList",body);
            f = ZCAPIClient.QGXMCAY("hj/sync_hj",object);
        }
        /** 市管项目*/
        JSONObject object1 = new JSONObject();
        JSONArray array = new JSONArray();
        if (!sbProjectDustEmission.getSubId().equals("")) {
            object1.put("PROJECT_ID", sbProjectDustEmission.getXmid());//所属项目编号
            object1.put("Jdbh", sbProjectDustEmission.getJdbh());//项目监督编号
            object1.put("sub_id", sbProjectDustEmission.getSubId());//工程ID
            object1.put("DEV_GUID",sbProjectDustEmission.getSn());//设备编号
            SbProjectDustEmission dustEmission = new SbProjectDustEmission();
            dustEmission.setProjectId(sbProjectDustEmission.getProjectId());
            List<SbProjectDustEmission> emissions = projectDustEmissionService.selectProjectDustEmissionListData(dustEmission);
            object1.put("MD_NAME",(emissions.size()+1)+"#检测点");//设备名称（命名规则：阿 拉伯数字#监测点；示例： 1#监测点，2#监测点，同 个项目下数字不能重复）
            object1.put("MD_TYPE",MD_TYPEMD_TYPE);//设备类型（“环境”固定死）
            array.add(object1);
            JSONObject object2 = new JSONObject();
            object2.put("PList",array);
            f = ZCAPIClient.SGXMCAY("tower/oper_pec",object2);
        }
        return f;
    }

    /**
     * 根据id查询
     */
    @PostMapping("/projectDustEmissionById")
    public AjaxResult projectDustEmissionById(@RequestParam(value = "id") Long id)
    {
        return AjaxResult.success(projectDustEmissionService.selectSbProjectDustEmissionById(id));
    }

    /**
     * 修改保存项目对应的扬尘设备SN
     */
    @PostMapping("/projectDustEmissionEditSave")
    public AjaxResult projectDustEmissionEditSave(@RequestBody SbProjectDustEmission sbProjectDustEmission)
    {
        System.out.println(sbProjectDustEmission);
        int i = projectDustEmissionService.updateSbProjectDustEmission(sbProjectDustEmission);
        if(i>0){
            return success();
        }
        return error(-1,"修改失败");
    }

    /**
     * 删除项目对应的扬尘设备SN
     */
    @PostMapping( "/remove")
    public AjaxResult remove(@RequestParam(value = "id") Integer id,@RequestParam(value = "devCcrq",required =false)String devCcrq) throws IOException, URISyntaxException {

        if (devCcrq != null) {
            SbProjectDustEmission dustEmission = projectDustEmissionService.selectSbProjectDustEmissionById(Long.valueOf(id));
            if (dustEmission != null) {
                cay.delete(dustEmission.getSn(), dustEmission.getXmid(), "环境", devCcrq, dustEmission.getSubId());
            }
        }
        int i = projectDustEmissionService.deleteSbProjectDustEmissionByIds(id);
        if(i>0){
            return success();
        }
        return error(-1,"删除失败");
    }

    /**
     * 设置扬尘设备添加设备
     * */
    @PostMapping(value = "")
    public JSONObject addtsp(){
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }
}
