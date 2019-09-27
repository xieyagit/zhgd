package com.hujiang.project.zhgd.hjSafetyAbarbeitung.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.*;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.service.IHjSafetyAbarbeitungService;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/safetyPcApi",method = RequestMethod.POST)
public class PcSafety extends BaseController {
    @Autowired
    private IHjSafetyAbarbeitungService safetyAbarbeitungService;
    @Autowired
    private IHjSystemUserService systemUserService;


    @PostMapping(value = "/statisticsCount")
    public JSONObject statisticsCount(@RequestParam(value = "differentiate") Integer differentiate,
                                      @RequestParam(value = "projectId") Integer projectId,
                                      @RequestParam(value = "startTime",required = false)String startTime,
                                      @RequestParam(value = "endTime",required = false)String endTime){
        JSONObject jsonObject = new JSONObject();
        JSONObject yieldMap = new JSONObject();
        int qualifiedCount = safetyAbarbeitungService.statisticsCount(projectId,3,differentiate,startTime,endTime); //合格/已完成数
        int rectifyCount = safetyAbarbeitungService.statisticsCount(projectId,1,differentiate,startTime,endTime);   //未整改
        int reviewCount = safetyAbarbeitungService.statisticsCount(projectId,2,differentiate,startTime,endTime);    //待复查
        int pastCount = safetyAbarbeitungService.statisticsCount(projectId,4,differentiate,startTime,endTime);      //超期未整改

        PcYield pcYield = safetyAbarbeitungService.getQualifiedList(projectId,differentiate, startTime, endTime);
        yieldMap.put("qualifiedCount",qualifiedCount);
        yieldMap.put("rectifyCount",rectifyCount);
        yieldMap.put("reviewCount",reviewCount);
        yieldMap.put("pastCount",pastCount);
        yieldMap.put("checkdSum",pcYield.getCheckdSum());
        yieldMap.put("noQualified",pcYield.getNoQualified());
        yieldMap.put("qualified",pcYield.getQualified());
        yieldMap.put("yield",pcYield.getYield());
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        jsonObject.put("data",yieldMap);
        return jsonObject;
    }

    /**
     * 检查记录查询信息
     * @param projectId
     * @return
     */
    @PostMapping(value = "/getInformationList")
    public JSONObject getInformationList(@RequestParam(value = "projectId") Integer projectId){
        JSONObject jsonObject = new JSONObject();
        List<Safety> constructionList = safetyAbarbeitungService.getConstructionList(projectId);
        if(constructionList != null){
            JSONArray constructionArray = new JSONArray();
            for (Safety construction:constructionList){
                JSONObject constructionMap = new JSONObject();
                constructionMap.put("constructionId",construction.getConstructionId());
                constructionMap.put("constructionName",construction.getConstructionName());
                constructionArray.add(constructionMap);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",constructionArray);
        }else{
            jsonObject.put("msg","该项目下没有分包单位");
            jsonObject.put("code",0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }

    /**
     * 检查记录
     * @param status
     * @param initiatorTime
     * @param initiatorName
     * @param rectifyName
     * @param reviewName
     * @param constructionId
     * @param areaId
     * @param problemGradeId
     * @param differentiate
     * @return
     */
    @PostMapping(value = "/getInspectionRecordList")
    public JSONObject getInspectionRecordList(@RequestParam(value = "projectId") Integer projectId,
                                              @RequestParam(value = "hiddenId",required = false)Integer hiddenId,
                                              @RequestParam(value = "status",required = false)Integer status,
                                              @RequestParam(value = "initiatorTime",required = false)String initiatorTime,
                                              @RequestParam(value = "initiatorName",required = false)String initiatorName,
                                              @RequestParam(value = "rectifyName",required = false)String rectifyName,
                                              @RequestParam(value = "reviewName",required = false)String reviewName,
                                              @RequestParam(value = "constructionId",required = false)Integer constructionId,
                                              @RequestParam(value = "areaId",required = false)Integer areaId,
                                              @RequestParam(value = "problemGradeId",required = false)Integer problemGradeId,
                                              @RequestParam(value = "differentiate")Integer differentiate
                                              ){


        JSONObject jsonObject = new JSONObject();
        HjSystemUser initiator = systemUserService.getByUser(initiatorName);
        Integer initiatorId =null;
        if(initiator != null) {
            initiatorId  = initiator.getId();
        }
        HjSystemUser rectify = systemUserService.getByUser(rectifyName);
        Integer rectifyId =null;
        if(rectify != null) {
            rectifyId  = rectify.getId();
        }
        HjSystemUser review = systemUserService.getByUser(reviewName);
        Integer reviewId =null;
        if(review != null) {
            reviewId  = review.getId();
        }
        startPage();
        List<PcInspectionRecord> inspectionRecordList =
                safetyAbarbeitungService.getInspectionRecordList(projectId,hiddenId,status, initiatorTime,initiatorId,
                        rectifyId,reviewId , constructionId, areaId, problemGradeId, differentiate);
            TableDataInfo dataTable = getDataTable(inspectionRecordList);
            //处理分页数据
            List<PcInspectionRecord> rows = (List<PcInspectionRecord>)dataTable.getRows();
            if(rows!=null&& rows.size()>0){
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("total",dataTable.getTotal());//总记录数
            jsonObject.put("data",rows);
        }else {
            jsonObject.put("msg","查询失败");
            jsonObject.put("code",-1);
            jsonObject.put("data",Collections.emptyList());
        }

        return jsonObject;
    }

    /**
     * 检查记录详情
     * @param safetyId
     * @return
     */
    @PostMapping(value = "/getInspectionRecordDetails")
    public JSONObject getInspectionRecordDetails(@RequestParam(value = "safetyId")Integer safetyId){
        JSONObject jsonObject = new JSONObject();
        SafetyRectification safetyRectification = safetyAbarbeitungService.getAfterRectificationDetails(safetyId);
        if(safetyRectification != null ) {
            JSONObject safetyMap = new JSONObject();
            //质量检查
            safetyMap.put("safetyId", safetyRectification.getSafetyId());        //整改ID
            safetyMap.put("status",safetyRectification.getStatus());        //状态
            safetyMap.put("initiatorTime", safetyRectification.getInitiatorTime());          //检查时间
            safetyMap.put("safetyDescribe", safetyRectification.getSafetyDescribe());        //问题标题
            safetyMap.put("gradeName", safetyRectification.getGradeName());      //问题级别名称
            safetyMap.put("areaName", safetyRectification.getAreaName());        //检查区域
            safetyMap.put("hiddenName", safetyRectification.getHiddenName());    //隐患类型
            safetyMap.put("constructionName", safetyRectification.getConstructionName());    //分包单位
            safetyMap.put("initiatorName", safetyRectification.getInitiatorName());    //检查人
            if (safetyRectification.getSafetyPhotos() != null) {
                List safetyPhotos = Arrays.asList(safetyRectification.getSafetyPhotos().split(","));//根据逗号分隔转化为list
                safetyMap.put("safetyPhotos", safetyPhotos);      //整改照片
            } else {
                safetyMap.put("safetyPhotos", Collections.emptyList());      //整改照片
            }
            //整改通知
            safetyMap.put("rectifyName", safetyRectification.getRectifyName());  //整改人
            safetyMap.put("safetyDeadline", safetyRectification.getSafetyDeadline());            //整改时限
            safetyMap.put("safetyRequire", safetyRectification.getSafetyRequire());                //整改要求
            safetyMap.put("safetyCreateTime", safetyRectification.getSafetyCreateTime());        //创建时间

            //第一次整改
            safetyMap.put("rectifyResult", safetyRectification.getRectifyResult());                //处理情况
            if (safetyRectification.getRectifyPhotos() != null){
                List rectifyPhotos = Arrays.asList(safetyRectification.getRectifyPhotos().split(","));//根据逗号分隔转化为list
                safetyMap.put("rectifyPhotos", rectifyPhotos);                      //整改图片
            }else {
                safetyMap.put("rectifyPhotos", Collections.emptyList());             //整改图片
            }
            safetyMap.put("rectifyTime",safetyRectification.getRectifyTime());      //整改时间

            //复查
            safetyMap.put("reviewOpinions", safetyRectification.getReviewOpinions()); //复查意见
            safetyMap.put("reviewResult", safetyRectification.getReviewResult());       //复查结果
            safetyMap.put("reviewName", safetyRectification.getReviewName());           //复查人
            if(safetyRectification.getReviewPath() != null) {
                List reviewPath = Arrays.asList(safetyRectification.getReviewPath().split(","));//根据逗号分隔转化为list
                safetyMap.put("reviewPath", reviewPath);            //复查照片
            }else {
                safetyMap.put("reviewPath", Collections.emptyList());       //复查照片
            }
            safetyMap.put("reviewTime",safetyRectification.getReviewTime());    //复查时间
            JSONArray makeArray = new JSONArray();
            if(safetyRectification.getMakeId()!=null){
                List makeList = Arrays.asList(safetyRectification.getMakeId().split(","));//根据逗号分隔转化为list
                for (int i = 0;i<makeList.size();i++){
                    HjSystemUser make = systemUserService.getByAlias(Integer.valueOf(makeList.get(i).toString()));
                    makeArray.add(make.getUserName());

                }
                safetyMap.put("makeName",makeArray);        //抄送人
            }else {
                safetyMap.put("makeName",Collections.emptyList());        //抄送人
            }


            List<HjSafetyNoPass> hjSafetyNoPassList = safetyAbarbeitungService.getAfterRectificationDetailsList(safetyId);
            JSONArray safetyArray = new JSONArray();
            for (HjSafetyNoPass hjSafetyNoPass:hjSafetyNoPassList){
                JSONObject initiatorMap = new JSONObject();
                JSONObject rectifyMap = new JSONObject();
                JSONObject reviewMap = new JSONObject();
                if(hjSafetyNoPass.getReviewTime() != null){
                    reviewMap.put("engineer","安全工程师");
                    reviewMap.put("title","复查"+hjSafetyNoPass.getReviewOpinions());
                    reviewMap.put("name",hjSafetyNoPass.getReviewName());
                    reviewMap.put("time",hjSafetyNoPass.getReviewTime());
                    safetyArray.add(reviewMap);
                }
                if(hjSafetyNoPass.getRectifyTime() != null){
                    rectifyMap.put("engineer","项目负责人");
                    rectifyMap.put("title","整改完成");
                    rectifyMap.put("name",hjSafetyNoPass.getRectifyName());
                    rectifyMap.put("time",hjSafetyNoPass.getRectifyTime());
                    safetyArray.add(rectifyMap);
                }
                initiatorMap.put("engineer","安全工程师");
                initiatorMap.put("title","发起安全整改");
                initiatorMap.put("name",hjSafetyNoPass.getInitiatorName()); //安全工程师
                initiatorMap.put("time",hjSafetyNoPass.getSafetyCreateTime());
                safetyArray.add(initiatorMap);
            }
            safetyMap.put("list",safetyArray);

            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",safetyMap);
        }else{
            jsonObject.put("msg","查询失败");
            jsonObject.put("code",-1);
            jsonObject.put("data",Collections.emptyList());
        }
        return jsonObject;
    }

    /**
     * 查询分包单位
     * @param projectId
     * @return
     */
    @PostMapping(value = "/getConstructionList")
    public JSONObject getConstructionList(@RequestParam(value = "projectId") Integer projectId){
        JSONObject jsonObject = new JSONObject();
        List<Safety> constructionList = safetyAbarbeitungService.getConstructionList(projectId);
        if(constructionList != null && constructionList.size()>0){
            JSONArray constructionArray = new JSONArray();
            for (Safety construction:constructionList){
                JSONObject constructionMap = new JSONObject();
                constructionMap.put("constructionId",construction.getConstructionId());
                constructionMap.put("constructionName",construction.getConstructionName());
                constructionArray.add(constructionMap);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",constructionArray);
        }else{
            jsonObject.put("msg","该项目下没有分包单位");
            jsonObject.put("code",0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }
    /**
     * 查询检查区域
     * @param constructionId
     * @return
     */
    @PostMapping(value = "/getAreaList")
    public JSONObject getAreaList(@RequestParam(value = "constructionId") Integer constructionId){
        JSONObject jsonObject = new JSONObject();
        List<Safety> areaList = safetyAbarbeitungService.getAreaList(constructionId);     //检查区域
        if(areaList != null && areaList.size()>0){
            JSONArray areaArray = new JSONArray();
            for (Safety area:areaList){
                JSONObject areaMap = new JSONObject();
                areaMap.put("areaId",area.getAreaId());
                areaMap.put("areaName",area.getAreaName());
                areaArray.add(areaMap);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",areaArray);
        }else{
            jsonObject.put("msg","该分包单位下没有检查区域");
            jsonObject.put("code",0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }


    /**
     * 整改单管理
     * @return
     */
    @PostMapping(value = "/getManagementList")
    public JSONObject getManagementList(@RequestParam(value = "projectId") Integer projectId,
                                        @RequestParam(value = "status",required = false)Integer status,
                                        @RequestParam(value = "initiatorTime",required = false)String initiatorTime,
                                        @RequestParam(value = "initiatorName",required = false)String initiatorName,
                                        @RequestParam(value = "rectifyName",required = false)String rectifyName,
                                        @RequestParam(value = "reviewName",required = false)String reviewName,
                                        @RequestParam(value = "constructionId",required = false)Integer constructionId,
                                        @RequestParam(value = "differentiate")Integer differentiate){

        JSONObject jsonObject = new JSONObject();
        //整改人
        Integer initiatorId =null;
        if(initiatorName != null && !("").equals(initiatorName)){
            HjSystemUser initiator = systemUserService.getByUser(initiatorName);
            if(initiator != null) {
                initiatorId  = initiator.getId();
            }else {
                jsonObject.put("msg","查询失败");
                jsonObject.put("code",-1);
                jsonObject.put("data",Collections.emptyList());
                return jsonObject;
            }
        }
        Integer rectifyId =null;
        if(rectifyName != null && !("").equals(rectifyName)){
            HjSystemUser rectify = systemUserService.getByUser(rectifyName);
            if(rectify != null) {
                rectifyId  = rectify.getId();
            }else {
                jsonObject.put("msg","查询失败");
                jsonObject.put("code",-1);
                jsonObject.put("data",Collections.emptyList());
                return jsonObject;
            }
        }
        Integer reviewId =null;
        if(reviewName != null && !("").equals(reviewName)){
            HjSystemUser review = systemUserService.getByUser(reviewName);
            if(review != null) {
                reviewId  = review.getId();
            }else {
                jsonObject.put("msg","查询失败");
                jsonObject.put("code",-1);
                jsonObject.put("data",Collections.emptyList());
                return jsonObject;
            }
        }
        startPage();
        List<PcManagement> managementList = safetyAbarbeitungService.getManagementList(projectId,status,initiatorId,
                rectifyId, reviewId, constructionId,differentiate);


        TableDataInfo dataTable = getDataTable(managementList);
        //处理分页数据
        List<PcManagement> rows = (List<PcManagement>)dataTable.getRows();
        if(rows!=null&& rows.size()>0){
            JSONArray jsonArray = new JSONArray();
            for (PcManagement pcManagement1:rows){
                JSONObject mMap = new JSONObject();
                PcManagement pcManagement = safetyAbarbeitungService.getManagementByTime(initiatorTime,pcManagement1.getSafetyId());
                mMap.put("safetyId",pcManagement1.getSafetyId());
                mMap.put("initiatorName",pcManagement1.getInitiatorName());
                mMap.put("constructionName",pcManagement1.getConstructionName());
                mMap.put("initiatorTime",pcManagement.getInitiatorTime());
                mMap.put("status",pcManagement1.getStatus());
                jsonArray.add(mMap);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("total",dataTable.getTotal());//总记录数
            jsonObject.put("data",jsonArray);
        }else {
            jsonObject.put("msg","查询失败");
            jsonObject.put("code",-1);
            jsonObject.put("data",Collections.emptyList());
        }
        return jsonObject;
    }

    /**
     * 整改单详情
     * @param safetyId
     * @return
     */
    @PostMapping(value = "/getManagementDetails")
    public JSONObject getManagementDetails(@RequestParam(value = "safetyId")Integer safetyId){
        JSONObject jsonObject = new JSONObject();
        SafetyRectification safetyRectification = safetyAbarbeitungService.getAfterRectificationDetails(safetyId);
        if(safetyRectification != null ) {
            JSONObject safetyMap = new JSONObject();
            //质量检查
            safetyMap.put("safetyId", safetyRectification.getSafetyId());        //整改ID
            safetyMap.put("status",safetyRectification.getStatus());        //状态

            safetyMap.put("initiatorId",safetyRectification.getInitiatorId());
            safetyMap.put("rectifyId",safetyRectification.getRectifyId());
            safetyMap.put("reviewId",safetyRectification.getReviewId());
            safetyMap.put("initiatorTime", safetyRectification.getInitiatorTime());          //检查时间
            safetyMap.put("rectifyTime",safetyRectification.getRectifyTime());
            safetyMap.put("reviewTime",safetyRectification.getReviewTime());


            //隐患明细
            safetyMap.put("safetyDescribe", safetyRectification.getSafetyDescribe());        //问题标题
            safetyMap.put("safetyRequire",safetyRectification.getSafetyRequire());
            safetyMap.put("gradeName", safetyRectification.getGradeName());      //问题级别名称
            safetyMap.put("areaName", safetyRectification.getAreaName());        //检查区域
            safetyMap.put("hiddenName", safetyRectification.getHiddenName());    //隐患类型
            safetyMap.put("initiatorName", safetyRectification.getInitiatorName());    //检查人
            safetyMap.put("rectifyName", safetyRectification.getRectifyName());  //整改人
            safetyMap.put("constructionName", safetyRectification.getConstructionName());    //分包单位
            safetyMap.put("safetyDeadline", safetyRectification.getSafetyDeadline());            //整改时限\

            safetyMap.put("hiddenId",safetyRectification.getHiddenId());
            safetyMap.put("areaId",safetyRectification.getAreaId());

            if(safetyRectification.getMakeId()!=null && safetyRectification.getMakeId().length()>0){
                StringBuffer sb2 = new StringBuffer();
//                List makeList = Arrays.asList(safetyRectification.getMakeId().split(","));//根据逗号分隔转化为list

                List<String> terms = Arrays.asList(safetyRectification.getMakeId().split(","));
                List<Integer> intTerms = Lists.newArrayList();
                for (String term : terms) {
                intTerms.add(Integer.parseInt(term));
                }
                safetyMap.put("makeId",intTerms);        //抄送人
            }else {
                safetyMap.put("makeId",Collections.emptyList());        //抄送人
            }
            List<Safety> areaList = safetyAbarbeitungService.getAreaList(safetyRectification.getConstructionId());     //检查区域
            if (areaList != null && areaList.size() > 0) {
                JSONArray areaArray = new JSONArray();
                for (Safety area : areaList) {
                    JSONObject areaMap = new JSONObject();
                    areaMap.put("areaId", area.getAreaId());
                    areaMap.put("areaNames", area.getAreaName());
                    List<Safety> userList = safetyAbarbeitungService.getUserList(area.getAreaId());     //人员
                    if (userList != null && userList.size() > 0) {
                        JSONArray userArray = new JSONArray();
                        for (Safety user : userList) {
                            JSONObject userMap = new JSONObject();
                            userMap.put("userId", user.getUserId());
                            userMap.put("userName", user.getUserName());
                            userArray.add(userMap);
                        }
                        areaMap.put("userList", userArray);
                    } else {
                        areaMap.put("userList", Collections.emptyList());
                    }
                    areaArray.add(areaMap);
                }
                safetyMap.put("areaList", areaArray);
            } else {
                safetyMap.put("areaList", Collections.emptyList());
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",safetyMap);
        }else{
            jsonObject.put("msg","查询失败");
            jsonObject.put("code",-1);
            jsonObject.put("data",Collections.emptyList());
        }
        return jsonObject;
    }

    @PostMapping(value = "/getHiddenList")
    public JSONObject getHiddenList(){
        JSONObject jsonObject = new JSONObject();
        List<Safety> hiddenList = safetyAbarbeitungService.getHiddenList(); //隐患
        if(hiddenList != null){
            JSONArray hiddenArray = new JSONArray();
            for (Safety hidden:hiddenList){
                JSONObject hiddenMap = new JSONObject();
                hiddenMap.put("hiddenId",hidden.getHiddenId());
                hiddenMap.put("hiddenName",hidden.getHiddenName());
                hiddenArray.add(hiddenMap);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",hiddenArray);
        }else{
            jsonObject.put("msg","该项目下没有分包单位");
            jsonObject.put("code",0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }

    @PostMapping(value = "/updateSafety")
    public JSONObject updateSafety(@RequestParam(value = "safetyId") Integer safetyId,
                                   @RequestParam(value = "initiatorId",required = false)Integer initiatorId,
                                   @RequestParam(value = "rectifyId",required = false)Integer rectifyId,
                                   @RequestParam(value = "reviewId",required = false)Integer reviewId,
                                   @RequestParam(value = "make",required = false)Integer[] makes,
                                   @RequestParam(value = "areaId",required = false)Integer areaId,
                                   @RequestParam(value = "safetyDeadline",required = false)String safetyDeadline,
                                   @RequestParam(value = "hiddenId",required = false)Integer hiddenId,
                                   @RequestParam(value = "safetyRequire",required = false)String safetyRequire,
                                   @RequestParam(value = "initiatorTime",required = false)String initiatorTime,
                                   @RequestParam(value = "rectifyTime",required = false)String rectifyTime,
                                   @RequestParam(value = "reviewTime",required = false)String reviewTime)
            {
        JSONObject jsonObject = new JSONObject();
        UpdateSafety updateSafety = new UpdateSafety();
        HjSafetyNoPass hjSafetyNoPass = safetyAbarbeitungService.getAfterRectificationNoPass(safetyId);

        if(makes != null && makes.length>0) {
            StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < makes.length; i++) {
                //HjSystemUser make = systemUserService.getByUser(makes[i]);
                sb2.append(makes[i]);
                sb2.append(",");
            }
            String makeId = (sb2.toString().substring(0, sb2.toString().length() - 1));
            updateSafety.setMakeId(makeId);
        }else {
            updateSafety.setMakeId(null);
        }   //抄送人
        if(initiatorTime != null && !("").equals(initiatorTime)) {
            updateSafety.setInitiatorTime(initiatorTime);
        }
        else {
            updateSafety.setInitiatorTime(null);
        }   //检查人
        if(rectifyTime != null && !("").equals(rectifyTime)){
            if(hjSafetyNoPass.getRectifyTime()==null){
                jsonObject.put("msg","还未整改，修改日期失败");
                jsonObject.put("code",-1);
                return jsonObject;
            }else {
               updateSafety.setRectifyTime(rectifyTime);
            }
        }else {
            updateSafety.setRectifyTime(null);
        }

        //整改人
        if(reviewTime != null && !("").equals(reviewTime)){
            if(hjSafetyNoPass.getReviewTime()==null){
                jsonObject.put("msg","还未复查，修改日期失败");
                jsonObject.put("code",-1);
                return jsonObject;
            }else {
                updateSafety.setReviewTime(reviewTime);
            }
        }else {
            updateSafety.setReviewTime(null);
        }
          //复查人

                updateSafety.setId(safetyId);       //ID
                updateSafety.setSafetyId(hjSafetyNoPass.getId());
                updateSafety.setInitiatorId(initiatorId);     //检查人
                updateSafety.setRectifyId(rectifyId);         //整改人
                updateSafety.setReviewId(reviewId);       //复查人
                updateSafety.setAreaId(areaId);         //检查区域
                updateSafety.setSafetyDeadline(safetyDeadline);     //整改期限
                updateSafety.setHiddenId(hiddenId);         //检查类型
                updateSafety.setSafetyRequire(safetyRequire);       //整改要求
                int safetyUpdate = safetyAbarbeitungService.updateSafety(updateSafety);
                if(safetyUpdate>0){
                   safetyAbarbeitungService.updateNoPass(updateSafety);
                   jsonObject.put("msg","成功");
                   jsonObject.put("code",0);
                }

        return jsonObject;
    }
    @PostMapping(value = "/deleteSafety")
    public JSONObject deleteSafety(@RequestParam("safetyId")Integer safetyId){
        JSONObject jsonObject = new JSONObject();
        int noPass = safetyAbarbeitungService.deleteNoPass(safetyId);
        if(noPass>0){
            int safety = safetyAbarbeitungService.deleteSafety(safetyId);
            if(safety>0){
                jsonObject.put("msg","成功");
                jsonObject.put("code",0);
            }else{
                jsonObject.put("msg","失败");
                jsonObject.put("code",-1);
            }
        }
        return jsonObject;
    }
}
