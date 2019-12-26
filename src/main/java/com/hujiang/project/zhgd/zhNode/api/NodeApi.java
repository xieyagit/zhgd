package com.hujiang.project.zhgd.zhNode.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
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
@RequestMapping(value = "/provider/Node", method = RequestMethod.POST)
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
     *
     * @param title 表名
     * @param outS  导出文件路径
     * @return
     */
    @PostMapping("exportZhNode")
    public AjaxResult exportZhNode(@RequestParam(value = "title") String title, @RequestParam(value = "outS") String outS) {
        //定义表的列名
        String[] rowsName = new String[]{"id", "节点名称", "状态", "预计开始时间", "预计结束时间", "实际开始时间", "实际结束时间"
                , "进度(%)", "管控级别", "负责人", "备注"};
        //定义表的内容
        List<Object[]> objs = new ArrayList<Object[]>();
        List<ZhNode> list = nodeService.selectZhNodeList(null);
        Object[] obj = new Object[11];
        for (ZhNode z : list) {
            obj[0] = z.getId();
            //obj[1]= z.getNumber();
            // obj[2]= z.getParentId();
            obj[1] = z.getName();
            obj[2] = z.getState();
            obj[3] = z.getPredictStart();
            obj[4] = z.getPredictEnd();
            obj[5] = z.getStart();
            obj[6] = z.getEnd();
            obj[7] = z.getProgress();
            //obj[10]= z.getContent();
            //obj[11]= z.getFound();
            //obj[12]= z.getCreatorId();
            obj[8] = z.getControlRank();
            obj[9] = z.getPrincipal();
            //obj[15]= z.getPipeliningSegment();
            obj[10] = z.getComment();
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
            util.exportExcel(title, rowsName, objs, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }


    /**
     * 导出节点计划所有列表
     *
     * @return
     */
    @PostMapping("/exportZhProgressPlan")
    public List<ZhNodePc> exportZhProgressPlan(@RequestParam(value = "progressId") int progressId) {
        List<ZhNodePc> list = new ArrayList<>();
        ZhProgressNode zpn = new ZhProgressNode();
        zpn.setProgressId(progressId);
        List<ZhProgressNode> pNode = progressNodeService.selectZhProgressNodeList(zpn);
        ZhNodePc zhNodePc;
        ZhNode z;
        for (ZhProgressNode pn : pNode) {
            zhNodePc = new ZhNodePc();
            z = nodeService.selectZhNodeById(pn.getNodeId());
            BeanUtils.copyProperties(z, zhNodePc);
            System.out.println(zhNodePc);
            list.add(zhNodePc);
        }
        return list;
    }


    /**
     * 导入节点计划详情列表
     */
    @PostMapping("importZhNode")
    public AjaxResult importZhNode(@RequestParam("file") MultipartFile file) {
        ExcelUtil util = new ExcelUtil();
        try {
            String sheetname = "zh_node";
            util.importExcel(file, sheetname, ZhNode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }

    /**
     * 查询进度节点列表
     *
     * @param node
     * @return
     */
    @PostMapping("selectZhNodeList")
    public JSONObject selectZhNodeList(@RequestBody ZhNode node) {
        JSONObject result = new JSONObject();
        //查询进度节点列表
        if (node.getPredictStart() != null && node.getPredictEnd() != null) {
            if (node.getPredictStart() == "undefined" || node.getPredictStart().equals("undefined")) {
                node.setPredictStart("");
            } else {
                node.setPredictStart(node.getPredictStart());
            }
            if (node.getPredictEnd() == "undefined" || node.getPredictEnd().equals("undefined")) {
                node.setPredictEnd("");
            } else {
                node.setPredictEnd(node.getPredictEnd());
            }
        }
        String currentTime = DateUtils.getDate();
        List<ZhNode> zhNode = null;
        if (node.getStatus() != null) {
            switch (node.getStatus()) {
                case 0:
                    node.setState(0);
                    zhNode = nodeService.selectNormalStartZhNode(node);
                    break;
                case 1:
                    node.setState(1);
                    node.setStart(currentTime);
                    zhNode = nodeService.selectNoStartZhNode(node);
                    break;
                case 2:
                    node.setState(1);
                    node.setEnd(currentTime);
                    zhNode = nodeService.selectDelayStartZhNode(node);
                    break;
                case 3:
                    node.setState(0);
                    zhNode = nodeService.selectDelayStartZhNode(node);
                    break;
                case 4:
                    node.setState(2);
                    zhNode = nodeService.selectDelayEndZhNode(node);
                    break;
                case 5:
                    node.setState(2);
                    zhNode = nodeService.selectNormalEndZhNode(node);
                    break;
                case 6:
                    node.setState(0);
                    zhNode = nodeService.selectAdvanceStartZhNode(node);
                    break;
                case 7:
                    node.setState(2);
                    zhNode = nodeService.selectNoEndZhNode(node);
                    break;
                default:
                    break;
            }
        } else {
            zhNode = nodeService.selectZhNodeList(node);
        }
        ZhProgressNode zhProgressNode = new ZhProgressNode();
        int progress;
        if (zhNode != null && zhNode.size() > 0) {
            for (int i = 0; i < zhNode.size(); i++) {
                zhProgressNode.setNodeId(zhNode.get(i).getId());
                List<ZhNodeWithProgress> zhNodeProgressList = progressNodeService.selectZhNodeProgressList(zhProgressNode);
                progress = 0;
                for (ZhNodeWithProgress zhNodeWithProgress : zhNodeProgressList) {
                    progress += zhNodeWithProgress.getNodeProgressRatio();
                }
                if (progress >= 100) {
                    zhNode.get(i).setAddAble(false);
                } else {
                    zhNode.get(i).setAddAble(true);
                }
            }
        }
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhNodeList" + "查询项目设备数：" + zhNode);
        if (zhNode != null) {
            result.put("msg", "查询节点列表成功");
            result.put("code", 0);
            result.put("data", zhNode);
        } else {
            result.put("msg", "无节点");
            result.put("code", -1);
        }
        return result;
    }

    /**
     * 查询计划节点关联列表
     *
     * @param zhProgressNode
     * @return
     */
    @PostMapping("selectZhProgressNodeList")
    public JSONObject selectZhProgressNodeList(@RequestBody ZhProgressNode zhProgressNode) {
        JSONObject result = new JSONObject();
        List<ZhProgressNode> zhProgressNodes = progressNodeService.selectZhProgressNodeList(zhProgressNode);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhNodeList" + "查询项目设备数：" + zhProgressNodes);
        if (zhProgressNodes != null) {
            result.put("msg", "查询节点关联列表成功");
            result.put("code", 0);
            result.put("data", zhProgressNodes);
        } else {
            result.put("msg", "无节点");
            result.put("code", -1);
        }
        return result;
    }

    /**
     * 查询进度计划列表
     *
     * @param zhProgressPlan
     * @return
     */
    @PostMapping("selectZhProgressPlanList")
    public JSONObject selectZhProgressPlanList(@RequestBody ZhProgressPlan zhProgressPlan) {
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
        plan.setProjectId(zhProgressPlan.getProjectId());
        List<ZhProgressPlan> zhProgressPlans = progressPlanService.selectZhProgressPlanList(plan);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhProgressPlanList" + "查询进度计划列表：" + zhProgressPlans);
        if (zhProgressPlans.size() > 0) {
            result.put("msg", "查询进度计划列表成功");
            result.put("code", 0);
            result.put("data", zhProgressPlans);
        } else {
            result.put("msg", "无进度计划");
            result.put("code", -1);
        }

        return result;
    }

    /**
     * 查询进度计划列表
     *
     * @param zhProgressPlan
     * @return
     */
    @PostMapping("selectZhProgressPlan")
    public JSONObject selectZhProgressPlan(@RequestBody ZhProgressPlan zhProgressPlan) {
        JSONObject result = new JSONObject();
        List<ZhProgressPlan> zhProgressPlans = progressPlanService.selectZhProgressPlanList(zhProgressPlan);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhProgressPlan" + "查询项目设备数：" + zhProgressPlans);
        if (zhProgressPlans.size() > 0) {
            String startTime = zhProgressPlans.get(0).getPredictStart();
            String endTime = zhProgressPlans.get(zhProgressPlans.size() - 1).getPredictEnd();
            Date currentTime = new Date();
            if (DateUtils.getDatePoorDay(currentTime, DateUtils.getDate(endTime)) > 0) {
                currentTime = DateUtils.getDate(endTime);
            }
            long alreadyTime = DateUtils.getDatePoorDay(currentTime, DateUtils.getDate(startTime));
            long totalTime = DateUtils.getDatePoorDay(DateUtils.getDate(endTime), DateUtils.getDate(startTime));
            result.put("msg", "查询进度计划列表成功");
            result.put("code", 0);
            result.put("alreadyTime", alreadyTime);
            result.put("totalTime", totalTime);
            result.put("data", zhProgressPlans);
        } else {
            result.put("msg", "无进度计划");
            result.put("code", -1);
        }

        return result;
    }

    /**
     * 查询前置节点列表
     *
     * @param zhPrepose
     * @return
     */
    @PostMapping("selectZhPreposeList")
    public JSONObject selectZhPreposeList(@RequestBody ZhPrepose zhPrepose) {
        JSONObject result = new JSONObject();
        List<ZhPrepose> zhPreposes = preposeService.selectZhPreposeList(zhPrepose);
        out.println("yant:" + zhPrepose);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectZhNodeList" + "查询前置节点：" + zhPrepose.toString());
        if (zhPrepose != null) {
            result.put("msg", "查询前置节点成功");
            result.put("code", 0);
            result.put("data", zhPreposes);
        } else {
            result.put("msg", "无前置节点");
            result.put("code", -1);
        }

        return result;
    }

    /**
     * 查询关键节点列表
     *
     * @return
     */
    @PostMapping("selectCruxZhNode")
    public JSONObject selectCruxZhNode(@RequestBody ZhNode zhNode) {
        JSONObject result = new JSONObject();
        zhNode.setCrux(true);
        List<ZhNode> nodeList = nodeService.selectCruxZhNode(zhNode);
        out.println("yant:" + nodeList);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectCruxZhNode" + "查询关键节点：" + nodeList.toString());
        if (nodeList != null) {
            result.put("msg", "查询关键节点成功");
            result.put("code", 0);
            result.put("data", nodeList);
        } else {
            result.put("msg", "无关键节点");
            result.put("code", -1);
        }
        return result;
    }

    /**
     * 查询预警节点列表
     *
     * @return
     */
    @PostMapping("selectWarningZhNode")
    public JSONObject selectWarningZhNode(@RequestBody ZhNode zhNode) {
        JSONObject result = new JSONObject();
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String time = df.format(new Date(d.getTime()));
        zhNode.setPredictStart(time);
        zhNode.setPredictEnd(time);
        List<ZhNode> ZhNode = nodeService.selectWarningZhNode(zhNode);
        out.println("yant:" + ZhNode);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectWarningZhNode" + "查询预警节点：" + ZhNode.toString());
        if (ZhNode != null) {
            result.put("msg", "查询预警节点成功");
            result.put("code", 0);
            result.put("data", ZhNode);
        } else {
            result.put("msg", "无预警节点");
            result.put("code", -1);
        }
        return result;
    }

    /**
     * 查询即将开始节点列表
     *
     * @return
     */
    @PostMapping("selectBeginZhNode")
    public JSONObject selectBeginZhNode(@RequestBody ZhNode zhNode) {
        JSONObject result = new JSONObject();
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String predictStart = df.format(new Date(d.getTime() + 7 * 24 * 60 * 60 * 1000));
        String time = df.format(new Date(d.getTime()));
        zhNode.setStart(time);
        zhNode.setPredictStart(predictStart);
        zhNode.setState(1);
        List<ZhNode> ZhNode = nodeService.selectBeginZhNode(zhNode);
        out.println("yant:" + ZhNode);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectBeginZhNode" + "查询即将开始节点：" + ZhNode.toString());
        if (ZhNode != null) {
            result.put("msg", "查询即将开始节点成功");
            result.put("code", 0);
            result.put("data", ZhNode);
        } else {
            result.put("msg", "无即将开始节点");
            result.put("code", -1);
        }
        return result;
    }

    /**
     * 查询即将完成节点列表
     *
     * @return
     */
    @PostMapping("selectEndZhNode")
    public JSONObject selectEndZhNode(@RequestBody ZhNode zhNode) {
        JSONObject result = new JSONObject();
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String predictEnd = df.format(new Date(d.getTime() + 7 * 24 * 60 * 60 * 1000));
        String time = df.format(new Date(d.getTime()));
        zhNode.setEnd(time);
        zhNode.setPredictEnd(predictEnd);
        zhNode.setState(0);
        List<ZhNode> ZhNode = nodeService.selectEndZhNode(zhNode);
        out.println("yant:" + ZhNode);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectEndZhNode" + "查询即将完成节点：" + ZhNode.toString());
        if (ZhNode != null) {
            result.put("msg", "查询即将完成节点成功");
            result.put("code", 0);
            result.put("data", ZhNode);
        } else {
            result.put("msg", "无即将完成节点");
            result.put("code", -1);
        }
        return result;
    }

    /**
     * 查询计划关联节点列表
     *
     * @return
     */
    @PostMapping("selectZhNodeProgressList")
    public JSONObject selectZhNodeProgressList(@RequestBody ZhProgressNode zhProgressNode) {
        JSONObject result = new JSONObject();
        List<ZhNodeWithProgress> node = progressNodeService.selectZhNodeProgressList(zhProgressNode);
        out.println("yant:" + node);
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectEndZhNode" + "查询计划关联节点：" + node.toString());
        if (node != null) {
            result.put("msg", "查询计划关联节点成功");
            result.put("code", 0);
            result.put("data", node);
        } else {
            result.put("msg", "无计划关联节点");
            result.put("code", -1);
        }
        return result;
    }

    /**
     * 新增保存节点计划
     */
    @PostMapping("addNode")
    public AjaxResult addNode(@RequestBody ZhNode zhNode) {
        int result = nodeService.insertZhNode(zhNode);
        if (result > 0 && zhNode.getParentId() != null) {
            updateFatherNode(zhNode);

        }
        return toAjax(result);
    }

    /**
     * 新增保存进度计划
     */
    @PostMapping("addProgressPlan")
    public AjaxResult addProgressPlan(@RequestBody ZhProgressPlan zhProgressPlan) {
        return toAjax(progressPlanService.insertZhProgressPlan(zhProgressPlan));
    }


    /**
     * 删除节点计划
     */
    @PostMapping("removeNode")
    public AjaxResult removeNode(@RequestParam(value = "id") int id) {
        ZhNode node = nodeService.selectZhNodeById(id);
        int result = nodeService.deleteZhNodeById(id);
        if (result > 0) {
            progressNodeService.deleteZhProgressNodeByNodeId(id);
            updateFatherNode(node);
        }
        return toAjax(result);
    }

    /**
     * 根据id删除进度计划
     */
    @PostMapping("/remoProgressPlan")
    public AjaxResult removeProgressPlan(@RequestParam(value = "id") int id) {
        return toAjax(progressPlanService.deleteZhProgressPlanById(id));
    }

    /**
     * 修改保存节点计划详情
     */
    @PostMapping("editNode")
    public AjaxResult editNode(@RequestBody ZhNode zhNode) {
        int result = nodeService.updateZhNode(zhNode);
        if (result > 0 && zhNode.getParentId() != null) {
            updateFatherNode(zhNode);
        }
        return toAjax(result);
    }

    /**
     * 修改计划中节点详情
     */
    @PostMapping("editNodeWithProgress")
    public AjaxResult editNodeWithProgress(@RequestBody ZhProgressNode progressNode) {
        int result = progressNodeService.updateZhProgressNode(progressNode);
        if (result > 0) {
            updateNodeProgress(progressNode.getNodeId());
            updateProgressPlan(progressNode.getProgressId());
            ZhNode node = nodeService.selectZhNodeById(progressNode.getNodeId());
            updateFatherNode(node);
        }
        return toAjax(result);
    }

    /**
     * 修改保存进度计划
     */
    @Log(title = "节点计划", businessType = BusinessType.UPDATE)
    @PostMapping("editProgressPlan")
    public AjaxResult editProgressPlan(@RequestBody ZhProgressPlan zhProgressPlan) {
        int result = progressPlanService.updateZhProgressPlan(zhProgressPlan);
        return toAjax(result);
    }


    /**
     * 添加前置节点
     */
    @Log(title = "进度计划", businessType = BusinessType.INSERT)
    @PostMapping("addPrepose")
    public AjaxResult addPrepose(@RequestBody ZhPrepose zhPrepose) {
        return toAjax(preposeService.insertZhPrepose(zhPrepose));
    }

    /**
     * 根据id删除前置节点
     */
    @Log(title = "进度计划", businessType = BusinessType.DELETE)
    @PostMapping("removePreposeById")
    public AjaxResult removePreposeById(@RequestParam(value = "id") int id) {
        return toAjax(preposeService.deleteZhPreposeById(id));
    }

    /**
     * 进度导入关联节点
     */
    @Log(title = "进度计划", businessType = BusinessType.INSERT)
    @PostMapping("addProgressNode")
    public AjaxResult addProgressNode(@RequestBody ZhProgressNode zhProgressNode) {
        int result = progressNodeService.insertZhProgressNode(zhProgressNode);
        if (result > 0) {
            updateNodeProgress(zhProgressNode.getNodeId());
            updateProgressPlan(zhProgressNode.getProgressId());
        }
        return toAjax(result);
    }

    /**
     * 删除进度中的关联节点
     */
    @Log(title = "进度计划", businessType = BusinessType.DELETE)
    @PostMapping("removeProgressNode")
    public AjaxResult removeaddProgressNode(@RequestParam(value = "id") int id) {
        ZhProgressNode zhProgressNode = progressNodeService.selectZhProgressNodeById(id);
        int result = progressNodeService.deleteZhProgressNodeById(id);
        if (result > 0) {
            updateNodeProgress(zhProgressNode.getNodeId());
            updateProgressPlan(zhProgressNode.getProgressId());
        }
        return toAjax(result);
    }

    /**
     * 查询可以导入的节点列表
     *
     * @return
     */
    @PostMapping("selectAddZhNodeList")
    public JSONObject selectAddZhNodeList(@RequestParam(value = "projectId") int projectId) {
        JSONObject result = new JSONObject();
        ZhNode node = new ZhNode();
        node.setProjectId(projectId);
        List<ZhNode> nodeList = nodeService.selectZhNodeList(node);
        ZhProgressNode zhProgressNode = new ZhProgressNode();
        int progress;
        for (int i = 0; i < nodeList.size(); i++) {
            zhProgressNode.setNodeId(nodeList.get(i).getId());
            List<ZhNodeWithProgress> zhNodeProgressList = progressNodeService.selectZhNodeProgressList(zhProgressNode);
            progress = 0;
            for (ZhNodeWithProgress zhNodeWithProgress : zhNodeProgressList) {
                progress += zhNodeWithProgress.getNodeProgressRatio();
            }
            if (progress >= 100) {
                nodeList.get(i).setAddAble(false);
            } else {
                nodeList.get(i).setAddAble(true);
            }
            nodeList.get(i).setProgressRatio(progress);
        }
        logger.info("com.hujiang.project.zhgd.zhNode.api.NodeApi.selectEndZhNode" + "查询计划可以导入的节点：" + nodeList.toString());
        if (node != null) {
            result.put("msg", "查询计划可以导入的节点");
            result.put("code", 0);
            result.put("data", nodeList);
        } else {
            result.put("msg", "无可以导入的节点");
            result.put("code", -1);
        }
        return result;
    }

    private void updateFatherNode(ZhNode node) {
        int parentId = node.getParentId();
        if (parentId == 0) {
            return;
        }
        ZhNode parentNode = nodeService.selectZhNodeById(parentId);
        List<ZhNode> zhNodeList = nodeService.selectZhNodeByParentId(parentId);
        int zhNodeProgress = 0;
        String startTime = null;
        String endTime = null;
        for (ZhNode zhNode : zhNodeList) {
            if (zhNode.getProgress() != null) {
                zhNodeProgress += zhNode.getProgress();
            }
            if (startTime == null) {
                startTime = zhNode.getStart();
            } else if (zhNode.getStart() != null && Util.getTime(startTime, zhNode.getStart()) < 0) {
                startTime = zhNode.getStart();
            }
            if (endTime == null) {
                endTime = zhNode.getEnd();
            } else if (zhNode.getEnd() != null && Util.getTime(endTime, zhNode.getEnd()) > 0) {
                endTime = zhNode.getEnd();
            }
        }
        if (startTime != null) {
            parentNode.setStart(startTime);
        } else {
            parentNode.setStart(null);
        }
        if (endTime != null) {
            parentNode.setEnd(endTime);
        } else {
            parentNode.setEnd(null);
        }
        if (zhNodeProgress > 0 && zhNodeList.size() > 0) {
            parentNode.setProgress(zhNodeProgress / zhNodeList.size());
        }
        nodeService.updateZhNode(parentNode);
    }

    /**
     * 更新节点进度
     *
     * @param nodeId
     */
    private void updateNodeProgress(int nodeId) {
        int progress = 0;
        ZhProgressNode pNode = new ZhProgressNode();
        ZhNode node = new ZhNode();
        pNode.setNodeId(nodeId);
        List<ZhNodeWithProgress> dataList = progressNodeService.selectZhNodeProgressList(pNode);
        for (ZhNodeWithProgress zhNWP : dataList) {
            if (zhNWP.getNodeProgress() != null) {
                progress += zhNWP.getNodeProgress() * zhNWP.getNodeProgressRatio();
            }
        }
        if (progress / 100 == 100) {
            node.setState(2);
            node.setEnd(DateUtils.getDate());
        }
        node.setId(nodeId);
        node.setProgress(progress / 100);
        nodeService.updateZhNode(node);
    }

    /**
     * 更新计划进度
     *
     * @param progressId
     */
    private void updateProgressPlan(int progressId) {
        ZhProgressNode pNode = new ZhProgressNode();
        pNode.setProgressId(progressId);
        List<ZhNodeWithProgress> node = progressNodeService.selectZhNodeProgressList(pNode);
        float progresss = 0;
        int number = 1;
        if (node != null && node.size() > 0) {
            number = node.size();
            for (ZhNodeWithProgress zhNode : node) {
                progresss += zhNode.getNodeProgress();
            }
        }
        float progress = progresss / number;
        ZhProgressPlan zhProgressPlan = new ZhProgressPlan();
        zhProgressPlan.setId(progressId);
        zhProgressPlan.setProgress(progress);
        progressPlanService.updateZhProgressPlan(zhProgressPlan);
    }

}
