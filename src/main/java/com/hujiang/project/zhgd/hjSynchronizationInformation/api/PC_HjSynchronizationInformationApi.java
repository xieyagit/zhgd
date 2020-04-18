package com.hujiang.project.zhgd.hjSynchronizationInformation.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
import com.hujiang.common.utils.http.HttpUtils;
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
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.CurrentTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-27 15:58
 **/
@RestController
@RequestMapping(value = "/provider/synchronizationInformationApi", method = RequestMethod.POST)
public class PC_HjSynchronizationInformationApi extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PC_HjSynchronizationInformationApi.class);

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Autowired
    private IHjProjectService hjProjectService;

    /**
     * 查询项目两制同步列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody HjSynchronizationInformation hjSynchronizationInformation) {
        startPage();
        List<HjSynchronizationInformation> list = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hjSynchronizationInformation);
        return getDataTable(list);
    }

    /**
     * 新增保存项目两制同步
     */
    @Log(title = "项目两制同步", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addSave(@RequestBody HjSynchronizationInformation hjSynchronizationInformation) {

        //对接福建两制   1判断有没有秘钥    有的话就上传   没有就不上传（秘钥有一个状态   开启了才上传）
        //根据项目id查询秘钥表   HjSynchronizationInformation
        String apiKey = hjSynchronizationInformation.getApiKey();
        String apiSecret = hjSynchronizationInformation.getApiSecret();
        int state = hjSynchronizationInformation.getState();
        Integer projectId = hjSynchronizationInformation.getProjectId();
        //判断有没有开启
        if (state == 1 && apiKey != null && apiSecret != null && projectId != null) {
            try {
                Map<String, Object> map = hjProjectService.getProject(projectId);
                if (map != null && map.size() > 0) {
                    String builderLicense = (String) map.get("builderLicense");
                    String paramStr = uploadParam(apiKey, apiSecret, builderLicense);
                    String msg = "福建两制上传项目信息";
                    Result result = HttpUtils.httpPostWithjson(Constants.HUJIAN_TWO_SYSTEMS, paramStr, msg);
                    if ("0".equals(result.getCode()) && result.getData() != null) {

                        String requestSerialCode = (String) JSON.parseObject(result.getData().toString()).get("requestSerialCode");

                        String resultStr1 = queryParam(apiKey, apiSecret, requestSerialCode);
                        Result resultQuery = JSONObject.parseObject(resultStr1, Result.class);
                        if ("0".equals(resultQuery.getCode())) {
                            LoggerUitls.logInfo("福建两制上传项目信息成功", resultQuery.getCode(), resultQuery.getMessage(), resultQuery.getData());
                            //项目编码
                            String projectCode = (String) JSON.parseObject(resultQuery.getData().toString()).get("projectCode");
                            HjProject hjProject = new HjProject();
                            hjProject.setId((Integer) map.get("id"));
                            hjProject.setProjectCode(projectCode);
                            //跟新项目编码
                            hjProjectService.updateHjProject(hjProject);
                        } else {
                            LoggerUitls.logInfo("福建两制上传项目信息失败", resultQuery.getCode(), resultQuery.getMessage(), resultQuery.getData());
                        }
                    }
                    LoggerUitls.logInfo(msg, result.getCode(), result.getMessage(), result.getData());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(hjSynchronizationInformation);
        return toAjax(hjSynchronizationInformationService.insertHjSynchronizationInformation(hjSynchronizationInformation));
    }

    /**
     * @Author xieya
     * @Description 异步查询入参组装
     * @Date 2020/4/17 14:37
     * @param apiKey
     * @param apiSecret
     * @param requestSerialCode 请求序列编码
     * @return java.lang.String
     **/
    private String queryParam(String apiKey, String apiSecret, String requestSerialCode) {
        Map apiParam = FuJianUtils.setHeader(FuJianUtils.PROJECT_UQERY);

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
     * @Author xieya
     * @Description 上传项目入参组装
     * @Date 2020/4/17 11:54
     * @param apiKey
     * @param apiSecret
     * @param builderLicense
     * @return java.util.Map<java.lang.String, java.lang.String>
     **/
    private String uploadParam(String apiKey, String apiSecret, String builderLicense) {
        Map apiParam = FuJianUtils.setHeader(FuJianUtils.PROJECT_UPLOAD);
        apiParam.put("appid", apiKey);
        Map<String, Object> param = new HashMap<>();
        //aes加密
        String aesBuilderLicense = AesUtils.encrypt(builderLicense, apiSecret);
        param.put("builderLicenseNum", aesBuilderLicense);

        String data = JSON.toJSONString(param);

        apiParam.put("data", data);
        String sign = CommonUtils.getSign(apiParam, apiSecret);
        apiParam.put("sign", sign);
        return JSON.toJSONString(apiParam);
    }

    /**
     * 修改保存项目两制同步
     */
    @Log(title = "项目两制同步", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editSave(@RequestBody HjSynchronizationInformation hjSynchronizationInformation) {
        hjSynchronizationInformation.setUpdateDate(CurrentTime.getCurrentTime());
        return toAjax(hjSynchronizationInformationService.updateHjSynchronizationInformation(hjSynchronizationInformation));
    }

    /**
     * 删除项目两制同步
     */
    @Log(title = "项目两制同步", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public AjaxResult remove(@RequestParam("ids") String ids) {
        return toAjax(hjSynchronizationInformationService.deleteHjSynchronizationInformationByIds(ids));
    }
}
