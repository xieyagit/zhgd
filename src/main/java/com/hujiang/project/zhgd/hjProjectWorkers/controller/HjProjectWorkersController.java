package com.hujiang.project.zhgd.hjProjectWorkers.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
import com.hujiang.common.utils.http.HttpUtils;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.common.AesUtils;
import com.hujiang.project.common.CommonUtils;
import com.hujiang.project.common.FuJianUtils;
import com.hujiang.project.common.LoggerUitls;
import com.hujiang.project.common.Result;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.FuJianProjectWorkerDto;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.FuJianWorkerEntryExitDto;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目工人 信息操作处理
 *
 * @author hujiang
 * @date 2019-05-19
 */
@Controller
@RequestMapping("/zhgd/hjProjectWorkers")
public class HjProjectWorkersController extends BaseController {
    private String prefix = "zhgd/hjProjectWorkers";

    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;

    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    @Autowired
    private IHjProjectService projectService;

	@Autowired
	private IHjTeamService hjTeamService;

    @RequiresPermissions("zhgd:hjProjectWorkers:view")
    @GetMapping()
    public String hjProjectWorkers() {
        return prefix + "/hjProjectWorkers";
    }

    /**
     * 查询项目工人列表
     */
    @RequiresPermissions("zhgd:hjProjectWorkers:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HjProjectWorkers hjProjectWorkers) {
        startPage();
        List<HjProjectWorkers> list = hjProjectWorkersService.selectHjProjectWorkersList(hjProjectWorkers);
        return getDataTable(list);
    }


    /**
     * 导出项目工人列表
     */
    @RequiresPermissions("zhgd:hjProjectWorkers:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjProjectWorkers hjProjectWorkers) {
        List<HjProjectWorkers> list = hjProjectWorkersService.selectHjProjectWorkersList(hjProjectWorkers);
        ExcelUtil<HjProjectWorkers> util = new ExcelUtil<HjProjectWorkers>(HjProjectWorkers.class);
        return util.exportExcel(list, "hjProjectWorkers");
    }

    /**
     * 新增项目工人
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存项目工人
     */
    @RequiresPermissions("zhgd:hjProjectWorkers:add")
    @Log(title = "项目工人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HjProjectWorkers hjProjectWorkers) {

        //福建两制新增项目工人接口
        fuJianProjectWorker(hjProjectWorkers, "add");

        return toAjax(hjProjectWorkersService.insertHjProjectWorkers(hjProjectWorkers));
    }

    private String setParam(String apiKey, String apiSecret, HjProjectWorkers hjProjectWorkers, HjProject project, HjConstructionCompany constructionCompany, HjTeam hjTeam, String type) {
        Map apiParam = null;
        if("add".equals(type)){
            apiParam = FuJianUtils.setHeader(FuJianUtils.PROJECTWORKER_UPLOAD);
        }
        if("edit".equals(type)){
            apiParam = FuJianUtils.setHeader(FuJianUtils.PROJECTWORKER_UPDATE);
        }

		apiParam.put("appid", apiKey);

		Map param = uploadParam(hjProjectWorkers, project, constructionCompany, hjTeam);

		String data = JSON.toJSONString(param);
		apiParam.put("data", data);

		String sign = CommonUtils.getSign(apiParam, apiSecret);
		apiParam.put("sign", sign);
		return JSON.toJSONString(apiParam);
	}

    private Map<String, Object> uploadParam(HjProjectWorkers hjProjectWorkers, HjProject project, HjConstructionCompany constructionCompany, HjTeam hjTeam1) {

    	Map<String, Object> map = new HashMap<>();
        //项目编码
        map.put("projectCode", project.getProjectCode());
        //统一社会信用代 码， 如果无统一社 会信用代码，则用 组织机构代码
        map.put("corpCode", constructionCompany.getSuid());
        //企业名称
        map.put("corpName", constructionCompany.getConstructionName());
        //班组编号
        map.put("teamSysNo" , hjTeam1.getTeamSysno());
        //班组名称
        map.put("teamName", hjTeam1.getTeamName());
        List<FuJianProjectWorkerDto> fuJianProjectWorkerList = new ArrayList();
        FuJianProjectWorkerDto fuJianProjectWorker = new FuJianProjectWorkerDto();
        fuJianProjectWorker.setWorkerName(hjProjectWorkers.getEmpName());
        fuJianProjectWorker.setIsTeamLeader(hjProjectWorkers.getIsTeam());
        //居民身份证
        fuJianProjectWorker.setIdCardType("01");
        fuJianProjectWorker.setIdCardNumber(hjProjectWorkers.getIdCode());
        fuJianProjectWorker.setWorkType(hjProjectWorkers.getJobName());
        //工人类型  暂时写死
        fuJianProjectWorker.setWorkRole(20);
        fuJianProjectWorker.setNation(hjProjectWorkers.getEmpNation());
        //住址   暂时取身份证地址
        fuJianProjectWorker.setAddress(hjProjectWorkers.getAccountAddress());
        //转base64
        fuJianProjectWorker.setHeadImage(BASE64DecodedMultipartFile.ImageToBase64ByOnline(hjProjectWorkers.getFaceUrl()));
        fuJianProjectWorker.setGender(hjProjectWorkers.getEmpSex());
        //政治面貌
        fuJianProjectWorker.setPoliticsType("未知");
        fuJianProjectWorker.setCellPhone(hjProjectWorkers.getEmpPhon());
        //文化程度
        fuJianProjectWorker.setCultureLevelType("未知");
        fuJianProjectWorker.setGrantOrg(hjProjectWorkers.getIdAgency());

        fuJianProjectWorkerList.add(fuJianProjectWorker);
        map.put("workerList", fuJianProjectWorkerList);
        return map;


    }

    /**
     * 修改项目工人
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        HjProjectWorkers hjProjectWorkers = hjProjectWorkersService.selectHjProjectWorkersById(id);
        mmap.put("hjProjectWorkers", hjProjectWorkers);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目工人
     */
    @RequiresPermissions("zhgd:hjProjectWorkers:edit")
    @Log(title = "项目工人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HjProjectWorkers hjProjectWorkers) {

        //福建两制修改项目工人接口
        fuJianProjectWorker(hjProjectWorkers, "edit");

        return toAjax(hjProjectWorkersService.updateHjProjectWorkers(hjProjectWorkers));
    }

    private void fuJianProjectWorker(HjProjectWorkers hjProjectWorkers, String type){
        Integer projectId = hjProjectWorkers.getProjectId();
        HjSynchronizationInformation hs = new HjSynchronizationInformation();
        hs.setProjectId(projectId);
        hs.setState(1);
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);

        Map<String, Object> projectMap = projectService.getProject(projectId);
        HjProject project = JSONObject.parseObject(JSONObject.toJSONString(projectMap), HjProject.class);

        Map<String, Object> constructionCompanyMap = hjConstructionCompanyService.selectConstructionCompany(projectId);
        HjConstructionCompany constructionCompany = JSONObject.parseObject(JSONObject.toJSONString(constructionCompanyMap), HjConstructionCompany.class);

        HjTeam hjTeam = new HjTeam();
        hjTeam.setProjectId(projectId);
        HjTeam hjTeam1 = hjTeamService.getHjTeam(hjTeam);

        String apiKey = null;
        String apiSecret = null;
        if (hList != null && hList.size() > 0) {
            apiKey = hList.get(0).getApiKey();
            apiSecret = hList.get(0).getApiSecret();
        }

        //有秘钥才去上传
        for (HjSynchronizationInformation h : hList) {
            try {
                //对接福建两制
                if ("FUJIAN".equals(h.getPlatformName())) {

                    String paramStr = setParam(apiKey, apiSecret, hjProjectWorkers, project, constructionCompany, hjTeam1, type);
                    String msg = "";
                    if("add".equals(type)){
                        msg = "福建两制新增保存项目工人信息";
                    }
                    if("edit".equals(type)){
                        msg = "福建两制修改保存项目工人信息";
                    }

                    Result result = HttpUtils.httpPostWithjson(Constants.HUJIAN_TWO_SYSTEMS, paramStr, msg);
                    if ("0".equals(result.getCode()) && result.getData() != null) {
                        if("add".equals(type)){
                            LoggerUitls.logInfo("福建两制新增保存项目工人信息成功", result.getCode(), result.getMessage(), result.getData());
                        }
                        if("edit".equals(type)){
                            LoggerUitls.logInfo("福建两制修改保存项目工人信息成功", result.getCode(), result.getMessage(), result.getData());
                        }
                    } else {
                        LoggerUitls.logInfo("福建两制新增保存项目工人信息失败", result.getCode(), result.getMessage(), result.getData());
                    }

                    //上传项目工人进/退场信息
                    String paramEntryExit = setEntryExit(apiKey, apiSecret, hjProjectWorkers, project, constructionCompany, hjTeam);
                    String msgEntryExit = "福建两制上传项目工人进/退场信息";
                    Result resultEntryExit = HttpUtils.httpPostWithjson(Constants.HUJIAN_TWO_SYSTEMS, paramEntryExit, msgEntryExit);
                    if ("0".equals(resultEntryExit.getCode()) && resultEntryExit.getData() != null) {
                        LoggerUitls.logInfo("福建两制上传项目工人进/退场信息成功", resultEntryExit.getCode(), resultEntryExit.getMessage(), resultEntryExit.getData());
                    } else {
                        LoggerUitls.logInfo("福建两制上传项目工人进/退场信息失败", resultEntryExit.getCode(), resultEntryExit.getMessage(), resultEntryExit.getData());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author xieya
     * @Description  进退场参数设置
     * @Date 2020/4/20 14:46
     * @param apiKey
     * @param apiSecret
     * @param hjProjectWorkers
     * @param project
     * @param constructionCompany
     * @param hjTeam
     * @return java.lang.String
     **/
    private String setEntryExit(String apiKey, String apiSecret, HjProjectWorkers hjProjectWorkers, HjProject project, HjConstructionCompany constructionCompany, HjTeam hjTeam) {
        Map apiParam = FuJianUtils.setHeader(FuJianUtils.WORKERENTRYEXIT_UPLOAD);

        apiParam.put("appid", apiKey);

        Map param = fuJianWorkerEntryExit(apiSecret, hjProjectWorkers, project, constructionCompany, hjTeam);

        String data = JSON.toJSONString(param);
        apiParam.put("data", data);

        String sign = CommonUtils.getSign(apiParam, apiSecret);
        apiParam.put("sign", sign);
        return JSON.toJSONString(apiParam);
    }

    private Map<String, Object> fuJianWorkerEntryExit(String apiSecret, HjProjectWorkers hjProjectWorkers, HjProject project, HjConstructionCompany constructionCompany, HjTeam hjTeam1){
        Map<String, Object> map = new HashMap<>();
        map.put("projectCode", project.getProjectCode());
        //统一社会信用代 码， 如果无统一社 会信用代码，则用 组织机构代码
        map.put("corpCode", constructionCompany.getSuid());
        //企业名称
        map.put("corpName", constructionCompany.getConstructionName());
        //班组编号
        map.put("teamSysNo" , hjTeam1.getTeamSysno());
        //班组名称
        map.put("teamName", hjTeam1.getTeamName());
        List<FuJianWorkerEntryExitDto> list = new ArrayList();
        FuJianWorkerEntryExitDto fuJianWorkerEntryExit = new FuJianWorkerEntryExitDto();

        //判断一个人是进场还是退场   退场时间为空    或者退场时间大于当前时间    进场状态
        if(hjProjectWorkers.getEndTime() == null || DateUtils.compareDate(DateUtils.stringToDate(hjProjectWorkers.getEndTime()), new Date()) == 1){
            fuJianWorkerEntryExit.setType(1);
            fuJianWorkerEntryExit.setDate(hjProjectWorkers.getStartTime());
        }else{
            fuJianWorkerEntryExit.setType(0);
            fuJianWorkerEntryExit.setDate(hjProjectWorkers.getEndTime());
        }

        //aes加密
        fuJianWorkerEntryExit.setIdCardNumber(AesUtils.encrypt(hjProjectWorkers.getIdCode(), apiSecret));
        //居民身份证
        fuJianWorkerEntryExit.setIdCardType("01");
        list.add(fuJianWorkerEntryExit);
        map.put("workerList", list);
        return map;
    }

    /**
     * 删除项目工人
     */
    @RequiresPermissions("zhgd:hjProjectWorkers:remove")
    @Log(title = "项目工人", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(hjProjectWorkersService.deleteHjProjectWorkersByIds(ids));
    }

}
