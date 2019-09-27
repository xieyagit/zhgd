package com.hujiang.project.zhgd.sbProjectDustEmission.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.consumer.TspPersonnelService;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
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
        List<SbProjectDustEmission> list = projectDustEmissionService.selectSbProjectDustEmissionList(sbProjectDustEmission);
        return AjaxResult.success(getDataTable(list));
    }
    /**
     * 新增保存项目对应的扬尘设备SN
     */
    @PostMapping("/projectDustEmissionAddSave")
    public AjaxResult projectDustEmissionAddSave(@RequestBody SbProjectDustEmission sbProjectDustEmission)
    {
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
    public AjaxResult remove(@RequestParam(value = "ids") String ids)
    {
        int i = projectDustEmissionService.deleteSbProjectDustEmissionByIds(ids);
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
