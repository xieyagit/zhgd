package com.hujiang.project.common;

import com.hujiang.common.utils.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *@ClassName FuJianUtils
 *@Description 对接福建两制接口  基本信息类
 *@Author xieya
 *@Date 2020/4/17  11:37
 */
public class FuJianUtils {

    /**上传项目信息*/
    public static String PROJECT_UPLOAD = "Project.Upload";
    /**查询项目信息*/
    public static String PROJECT_UQERY = "Project.Query";
    /**上传参建单位信息*/
    public static String PROJECT_SUBCONTRACTOR_UPLOAD = "ProjectSubContractor.Upload";
    /**修改参建单位信息*/
    public static String PROJECT_SUBCONTRACTOR_UPDATE = "ProjectSubContractor.Update";
    /**上传班组信息*/
    public static String TEAM_UPLOAD = "Team.Upload";
    /**修改班组信息*/
    public static String TEAM_UPDATE = "Team.Update";
    /**上传项目工人信息*/
    public static String PROJECTWORKER_UPLOAD = "ProjectWorker.Upload";
    /**修改项目工人信息*/
    public static String PROJECTWORKER_UPDATE = "ProjectWorker.Update";
    /**上传项目工人进/退场信息*/
    public static String WORKERENTRYEXIT_UPLOAD = "WorkerEntryExit.Upload";
    /**上传工人合同信息*/
    public static String WORKERCONTRACT_UPLOAD = "WorkerContract.Upload";
    /**上传工人考勤*/
    public static String WORKERATTENDANCE_UPLOAD = "WorkerAttendance.Upload";
    /**版本信息*/
    public static String VERSION = "v1.0";
    /**数据格式*/
    public static String FORMAT = "json";

    /**
     * @Author xieya
     * @Description 设置公共参数
     * @Date 2020/4/17 14:36
     * @return java.util.Map<java.lang.String, java.lang.String>
     **/
    public static Map<String, String> setHeader(String method) {
        Map<String, String> apiParam = new HashMap<>();

        apiParam.put("method", method);
        apiParam.put("version", VERSION);
        String nonce = UUID.randomUUID().toString().replaceAll("-", "");
        apiParam.put("nonce", nonce);
        apiParam.put("format", FORMAT);
        apiParam.put("timestamp", DateUtils.dateToString(new Date(), "yyyyMMddHHmmss"));
        return apiParam;
    }
}