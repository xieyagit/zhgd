package com.hujiang.project.zhgd.hjTeam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.http.HttpUtils;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.common.CommonUtils;
import com.hujiang.project.common.FuJianUtils;
import com.hujiang.project.common.LoggerUitls;
import com.hujiang.project.common.Result;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.CurrentTime;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班组 信息操作处理
 *
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjTeam")
public class HjTeamController extends BaseController {
    private String prefix = "zhgd/hjTeam";

    @Autowired
    private IHjTeamService hjTeamService;
    @Autowired
    private IHjProjectService hjProjectService;
    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    @RequiresPermissions("zhgd:hjTeam:view")
    @GetMapping()
    public String hjTeam() {
        return prefix + "/hjTeam";
    }

    /**
     * 查询班组列表
     */
    @RequiresPermissions("zhgd:hjTeam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HjTeam hjTeam) {
        startPage();
        List<HjTeam> list = hjTeamService.selectHjTeamList(hjTeam);
        return getDataTable(list);
    }


    /**
     * 导出班组列表
     */
    @RequiresPermissions("zhgd:hjTeam:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjTeam hjTeam) {
        List<HjTeam> list = hjTeamService.selectHjTeamList(hjTeam);
        ExcelUtil<HjTeam> util = new ExcelUtil<HjTeam>(HjTeam.class);
        return util.exportExcel(list, "hjTeam");
    }

    /**
     * 新增班组
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存班组
     */
    @RequiresPermissions("zhgd:hjTeam:add")
    @Log(title = "班组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HjTeam hjTeam) {
        fujianTeam(hjTeam, "add");

        return toAjax(hjTeamService.insertHjTeam(hjTeam));
    }

    private void fujianTeam(HjTeam hjTeam, String type) {
        try {
            //同步参建单位
            HjSynchronizationInformation hs = new HjSynchronizationInformation();
            hs.setProjectId(hjTeam.getProjectId());
            hs.setState(1);
            hs.setApiType("keytype1");

            List<HjSynchronizationInformation> hList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
            String apiKey = null;
            String apiSecret = null;
            if (hList != null && hList.size() > 0) {
                apiKey = hList.get(0).getApiKey();
                apiSecret = hList.get(0).getApiSecret();
            }

            //有秘钥才去上传
            for (HjSynchronizationInformation h : hList) {
                //对接福建两制
                if ("FUJIAN".equals(h.getPlatformName())) {
                    Map<String, Object> projectMap = hjProjectService.getProject(hjTeam.getProjectId());
                    HjProject project = JSONObject.parseObject(JSONObject.toJSONString(projectMap), HjProject.class);

                    HjConstructionCompany constructionCompany = hjConstructionCompanyService.selectHjConstructionCompanyById(hjTeam.getConstructionId());

                    String paramStr = uploadParam(apiKey, apiSecret, project, constructionCompany, hjTeam, type);

                    String msg = "";
                    if ("add".equals(type)) {
                        msg = "福建两制上传班组信息";
                    }
                    if ("edit".equals(type)) {
                        msg = "福建两制跟新班组信息";
                    }

                    Result result = HttpUtils.httpPostWithjson(Constants.HUJIAN_TWO_SYSTEMS, paramStr, msg);
                    if ("0".equals(result.getCode()) && result.getData() != null) {
                        String requestSerialCode = (String) JSON.parseObject((String) result.getData()).get("requestSerialCode");

                        if ("add".equals(type)) {
                            String resultStr1 = queryParam(apiKey, apiSecret, requestSerialCode);
                            Result resultQuery = JSONObject.parseObject(resultStr1, Result.class);
                            if ("0".equals(resultQuery.getCode())) {
                                //异步请求得到班组编号跟新数据库
                                JSONObject jsonObjects = JSON.parseObject((String) result.getData());
                                JSONObject ja = jsonObjects.getJSONObject("result");
                                String teamSysno = (String) ja.get("teamSysno");
                                hjTeam.setTeamSysno(teamSysno);
                                hjTeamService.updateHjTeam(hjTeam);
                                LoggerUitls.logInfo("福建两制上传班组信息成功", resultQuery.getCode(), resultQuery.getMessage(), resultQuery.getData());
                            } else {
                                LoggerUitls.logInfo("福建两制上传项目信息失败", resultQuery.getCode(), resultQuery.getMessage(), resultQuery.getData());
                            }
                        } else {
                            LoggerUitls.logInfo("福建两制跟新班组信息成功", result.getCode(), result.getMessage(), result.getData());
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String uploadParam(String apiKey, String apiSecret, HjProject project, HjConstructionCompany constructionCompany, HjTeam hjTeam, String type) {
        Map apiParam = null;
        if ("add".equals(type)) {
            apiParam = FuJianUtils.setHeader(FuJianUtils.TEAM_UPLOAD);
        }
        if ("edit".equals(type)) {
            apiParam = FuJianUtils.setHeader(FuJianUtils.TEAM_UPDATE);
        }

        apiParam.put("appid", apiKey);

        Map<String, Object> param = new HashMap<>();
        //项目编码
        param.put("projectCode", project.getProjectCode());
        //统一社会信用代码，如果无统一社会信用代码，则用组织机构代码
        param.put("corpCode", constructionCompany.getSuid());
        //企业名称
        param.put("corpName", constructionCompany.getConstructionName());
        //班组名称，同一个项目和参建单位下面不能重复
        param.put("teamName", hjTeam.getTeamName());

        String data = JSON.toJSONString(param);

        apiParam.put("data", data);
        String sign = CommonUtils.getSign(apiParam, apiSecret);
        apiParam.put("sign", sign);
        return JSON.toJSONString(apiParam);
    }

    private String queryParam(String apiKey, String apiSecret, String requestSerialCode) {
        Map apiParam = FuJianUtils.setHeader(FuJianUtils.TEAM_UPDATE);

        apiParam.put("appid", apiKey);
        Map<String, Object> param = new HashMap<>();
        param.put("requestSerialCode", requestSerialCode);
        String data = JSON.toJSONString(param);
        apiParam.put("data", data);

        String sign = CommonUtils.getSign(apiParam, apiSecret);
        apiParam.put("sign", sign);
        return JSON.toJSONString(apiParam);
    }

    /**
     * 修改班组
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        HjTeam hjTeam = hjTeamService.selectHjTeamById(id);
        mmap.put("hjTeam", hjTeam);
        return prefix + "/edit";
    }

    /**
     * 修改保存班组
     * @return
     */
    @RequiresPermissions("zhgd:hjTeam:edit")
    @Log(title = "班组", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HjTeam hjTeam) {
        fujianTeam(hjTeam, "edit");

        hjTeam.setUpdateDate(CurrentTime.getCurrentTime());
        return toAjax(hjTeamService.updateHjTeam(hjTeam));
    }

    /**
     * 删除班组
     */
    @RequiresPermissions("zhgd:hjTeam:remove")
    @Log(title = "班组", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(hjTeamService.deleteHjTeamByIds(ids));
    }

}
