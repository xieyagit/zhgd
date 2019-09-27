package com.hujiang.project.zhgd.zhNode.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.utils.ExcelUtil;
import com.hujiang.project.zhgd.utils.Tools;
import com.hujiang.project.zhgd.utils.Util;
import com.hujiang.project.zhgd.zhNode.domain.*;
import com.hujiang.project.zhgd.zhNode.service.IZhNodeService;
import com.hujiang.project.zhgd.zhNode.service.IZhPreposeService;
import com.hujiang.project.zhgd.zhNode.service.IZhProgressNodeService;
import com.hujiang.project.zhgd.zhNode.service.IZhProgressPlanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.System.out;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-18 15:36
 **/
@RestController
@RequestMapping(value = "/provider/Node",method = RequestMethod.POST)
public class NodeApi extends BaseController {
    private Logger logger = Logger.getLogger(NodeApi.class.getName());
    @Autowired
    private IZhNodeService nodeService;
    @Autowired
    private IZhProgressPlanService progressPlanService;
    @Autowired
    private IZhPreposeService preposeService;
    @Autowired
    private IZhProgressNodeService progressNodeService;


    /**
     * 导出节点计划所有列表
     * @param title  表名
     * @param outS 导出文件路径
     * @return
     */
    @PostMapping("exportZhNode")
    public AjaxResult exportZhNode(@RequestParam(value = "title")String title,@RequestParam(value = "outS")String outS)
    {
        //定义表的列名
        String[] rowsName = new String[] { "id", "节点名称", "状态", "预计开始时间", "预计结束时间", "实际开始时间", "实际结束时间"
                , "进度(%)","管控级别", "负责人","备注"};
        //定义表的内容
        List<Object[]> objs =  new ArrayList<Object[]>();
        List<ZhNode> list = nodeService.selectZhNodeList(null);
        Object[] obj = new Object[11];
        for (ZhNode z :list){
            obj[0]= z.getId();
            //obj[1]= z.getNumber();
           // obj[2]= z.getParentId();
            obj[1]= z.getName();
            obj[2]= z.getState();
            obj[3]= z.getPredictStart();
            obj[4]= z.getPredictEnd();
            obj[5]= z.getStart();
            obj[6]= z.getEnd();
            obj[7]= z.getProgress();
            //obj[10]= z.getContent();
            //obj[11]= z.getFound();
            //obj[12]= z.getCreatorId();
            obj[8]= z.getControlRank();
            obj[9]= z.getPrincipal();
            //obj[15]= z.getPipeliningSegment();
            obj[10]= z.getComment();
            objs.add(obj);
        }
        title = "test1";

        ExcelUtil util = new ExcelUtil();
        Boolean b = null;
        try {
            // 定义表的标题
            //fileName= new String("测试excel文档.xlsx".getBytes("UTF-8"),"iso-8859-1");
            // 定义表的路径
            OutputStream out = new FileOutputStream(outS);
            util.exportExcel(title,rowsName,objs,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }


    /**
     * 导出节点计划所有列表
     * @return
     */
    @PostMapping("/exportZhProgressPlan")
    public List<ZhNodePc> exportZhProgressPlan(@RequestParam(value = "progressId")int progressId)
    {
        ZhNodePc zhNodePc = new ZhNodePc();
        List<ZhNodePc> list = new ArrayList<>();
        ZhProgressNode zpn = new ZhProgressNode();
        zpn.setProgressId(progressId);
        List<ZhProgressNode> pNode = progressNodeService.selectZhProgressNodeList(zpn);
        for (ZhProgressNode pn :pNode){
            ZhNode z = nodeService.selectZhNodeById(pn.getNodeId());
            BeanUtils.copyProperties(z,zhNodePc);
            System.out.println(zhNodePc);
            list.add(zhNodePc);
        }
        return list;
    }


    /**
     * 导入节点计划详情列表
     */
    @PostMapping("importZhNode")
    public AjaxResult importZhNode(@RequestParam("file") MultipartFile file)
    {
        ExcelUtil util = new ExcelUtil();
        try {
            String sheetname = "zh_node";
            util.importExcel(file,sheetname,ZhNode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }

    /**
     * 查询进度节点列表
     * @param node
     * @return
     */
    @PostMapping("selectZhNodeList")
    public JSONObject selectZhNodeList(@RequestBody ZhNode node){
        JSONObject result = new JSONObject();
        //查询进度节点列表
        if (node.getPredictStart() != null && node.getPredictEnd() != null){
            if (node.getPredictStart() == "undefined" || node.getPredictStart().equals("undefined")){
                node.setPredictStart("");
            }else {
                node.setPredictStart(node.getPredictStart());
            }
            if (node.getPredictEnd() == "undefined" || node.getPredictEnd().equals("undefined")){
                node.setPredictEnd("");
            }else {
                node.setPredictEnd(node.getPredictEnd());
            }
        }
        List<ZhNode> zhNode = nodeService.selectZhNodeList(node);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhNodeList" + "查询项目设备数："+zhNode);
        if(zhNode!=null){
            result.put("msg","查询节点列表成功");
            result.put("code",0);
            result.put("data",zhNode);
        }else{
            result.put("msg","无节点");
            result.put("code",-1);
        }

        return result;
    }

    /**
     * 查询计划节点关联列表
     * @param zhProgressNode
     * @return
     */
    @PostMapping("selectZhProgressNodeList")
    public JSONObject selectZhProgressNodeList(@RequestBody ZhProgressNode zhProgressNode){
        JSONObject result = new JSONObject();
        List<ZhProgressNode> zhProgressNodes = progressNodeService.selectZhProgressNodeList(zhProgressNode);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhNodeList" + "查询项目设备数："+zhProgressNodes);
        if(zhProgressNodes!=null){
            result.put("msg","查询节点关联列表成功");
            result.put("code",0);
            result.put("data",zhProgressNodes);
        }else{
            result.put("msg","无节点");
            result.put("code",-1);
        }

        return result;
    }

    /**
     * 查询进度计划列表
     * @param zhProgressPlan
     * @return
     */
    @PostMapping("selectZhProgressPlanList")
    public JSONObject selectZhProgressPlanList(@RequestBody ZhProgressPlan zhProgressPlan){
        JSONObject result = new JSONObject();
        ZhProgressPlan plan = new ZhProgressPlan();
        if (zhProgressPlan.getPredictStart() != null && zhProgressPlan.getPredictEnd() != null) {
            if (zhProgressPlan.getPredictStart() == "undefined" || zhProgressPlan.getPredictStart().equals("undefined")) {
                plan.setPredictStart("");
            } else {
                plan.setPredictStart(zhProgressPlan.getPredictStart());
            }
            if (zhProgressPlan.getPredictEnd() == "undefined" || zhProgressPlan.getPredictEnd().equals("undefined")) {
                plan.setPredictEnd("");
            } else {
                plan.setPredictEnd(zhProgressPlan.getPredictEnd());
            }
        }
        plan.setId(zhProgressPlan.getId());
        plan.setName(zhProgressPlan.getName());
        plan.setState(zhProgressPlan.getState());
        plan.setStart(zhProgressPlan.getStart());
        plan.setEnd(zhProgressPlan.getEnd());
        plan.setCreatorId(zhProgressPlan.getCreatorId());
        List<ZhProgressPlan> zhProgressPlans = progressPlanService.selectZhProgressPlanList(plan);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhNodeList" + "查询项目设备数："+zhProgressPlans);
        if(zhProgressPlans.size()>0){
            result.put("msg","查询进度计划列表成功");
            result.put("code",0);
            result.put("data",zhProgressPlans);
        }else{
            result.put("msg","无进度计划");
            result.put("code",-1);
        }

        return result;
    }

    /**
     * 查询前置节点列表
     * @param zhPrepose
     * @return
     */
    @PostMapping("selectZhPreposeList")
    public JSONObject selectZhPreposeList(@RequestBody ZhPrepose zhPrepose){
        JSONObject result = new JSONObject();
        List<ZhPrepose> zhPreposes = preposeService.selectZhPreposeList(zhPrepose);
        out.println("yant:"+zhPrepose);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhNodeList" + "查询前置节点："+zhPrepose.toString());
        if(zhPrepose!=null){
            result.put("msg","查询前置节点成功");
            result.put("code",0);
            result.put("data",zhPreposes);
        }else{
            result.put("msg","无前置节点");
            result.put("code",-1);
        }

        return result;
    }



    /**
     * 新增保存节点计划
     */
    @PostMapping("addNode")
    public AjaxResult addNode(@RequestBody ZhNode zhNode )
    {
		return toAjax(nodeService.insertZhNode(zhNode));
    }

    /**
     * 新增保存进度计划
     */
    @PostMapping("addProgressPlan")
    public AjaxResult addProgressPlan(@RequestBody ZhProgressPlan zhProgressPlan )
    {

        return toAjax(progressPlanService.insertZhProgressPlan(zhProgressPlan));
    }



    /**
     * 删除节点计划
     */
    @PostMapping( "removeNode")
    public AjaxResult removeNode(@RequestParam(value = "id")int id)
    {
        return toAjax(nodeService.deleteZhNodeById(id));
    }

    /**
     * 根据id删除进度计划
     */
    @PostMapping("/remoProgressPlan")
    public AjaxResult removeProgressPlan(@RequestParam(value = "id")int id)
    {
        return toAjax(progressPlanService.deleteZhProgressPlanById(id));
    }

    /**
     * 修改保存节点计划详情
     */
    @PostMapping("editNode")
    public AjaxResult editNode(@RequestBody ZhNode zhNode) throws ParseException
    {
        if (zhNode.getStart() != null && zhNode.getStart() != "") {
            ZhNode node = new ZhNode();
            node.setId(zhNode.getId());
            SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
            List<ZhNode> list = nodeService.selectZhNodeList(node);

            Calendar date = Calendar.getInstance();
            date.setTime(sp.parse(sp.format(new Date())));

            Calendar begin = Calendar.getInstance();
            begin.setTime(sp.parse(zhNode.getStart()));

            Calendar end = Calendar.getInstance();
            end.setTime(sp.parse(list.get(0).getPredictStart()));

            if (begin.equals(end)) {
                zhNode.setState(0);
            } else if (begin.after(end)) {
                zhNode.setState(3);
            } else if (date.after(begin)) {
                zhNode.setState(2);
            }
        }
        if (zhNode.getEnd() != null && zhNode.getEnd()!="")
        {
            ZhNode node = new ZhNode();
            node.setId(zhNode.getId());
            SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
            List<ZhNode> list = nodeService.selectZhNodeList(node);

            Calendar date = Calendar.getInstance();
            date.setTime(sp.parse(sp.format(new Date())));

            Calendar begin = Calendar.getInstance();
            begin.setTime(sp.parse(zhNode.getEnd()));

            Calendar end = Calendar.getInstance();
            end.setTime(sp.parse(list.get(0).getPredictEnd()));

            if (begin.equals(end)){
                zhNode.setState(5);
            }else if (begin.after(end)){
                zhNode.setState(4);
            }else if (begin.before(end)){
                zhNode.setState(5);
            }
        }
        return toAjax(nodeService.updateZhNode(zhNode));
    }

    /**
     * 修改保存进度计划
     */
    @Log(title = "节点计划", businessType = BusinessType.UPDATE)
    @PostMapping("editProgressPlan")
    public AjaxResult editProgressPlan(@RequestBody ZhProgressPlan zhProgressPlan)
    {
        return toAjax(progressPlanService.updateZhProgressPlan(zhProgressPlan));
    }


    /**
     * 添加前置节点
     */
    @Log(title = "进度计划", businessType = BusinessType.INSERT)
    @PostMapping("addPrepose")
    public AjaxResult addPrepose(@RequestBody ZhPrepose zhPrepose)
    {
        return toAjax(preposeService.insertZhPrepose(zhPrepose));
    }
    /**
     * 根据id删除前置节点
     */
    @Log(title = "进度计划", businessType = BusinessType.DELETE)
    @PostMapping("removePreposeById")
    public AjaxResult removePreposeById(@RequestParam(value = "id") int id)
    {
        return toAjax(preposeService.deleteZhPreposeById(id));
    }

    /**
     * 进度导入关联节点
     */
    @Log(title = "进度计划", businessType = BusinessType.INSERT)
    @PostMapping("addProgressNode")
    public AjaxResult addProgressNode(@RequestBody ZhProgressNode zhProgressNode)
    {
        return toAjax(progressNodeService.insertZhProgressNode(zhProgressNode));
    }
    /**
     * 删除进度中的关联节点
     */
    @Log(title = "进度计划", businessType = BusinessType.DELETE)
    @PostMapping("removeProgressNode")
    public AjaxResult removeaddProgressNode(@RequestParam(value = "id") int id)
    {
        return toAjax(progressNodeService.deleteZhProgressNodeById(id));
    }


}
