package com.hujiang.project.zhgd.hjProject.xieTowSystem;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.utils.Util;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

public class xieTowSystem {

    @Autowired
    private IHjConstructionCompanyService iHjConstructionCompanyService;

    //对接惠州住建局两制(上传项目信息)
    public String xieTow(HjProject hjProject, JSONObject jsonObject) throws IOException, URISyntaxException {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject body = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        array.add(body);
        object.put("api_key",jsonObject.getString("api_key"));
        object.put("api_version",jsonObject.getString("api_version"));
        object.put("body",array);
        String xie = ZCAPIClient.xieTwoSystems("UploadSmz/UploadItemInfo",object);
        object.put("signature",xie);//拼接签名
        object.put("projectCode","");//项目统一编号（修改项目信息时必填）
        HjConstructionCompany constructionCompany = new HjConstructionCompany();
        constructionCompany.setConstructionName(jsonObject.getString("constructionName"));
        HjConstructionCompany hjConstructionCompany = iHjConstructionCompanyService.getConstructionCompany(constructionCompany);
        if (hjConstructionCompany!= null){
            //总承包单位统一社会信用代码，如果无统一社会信用代码，则填写组织机构代码
            if (hjConstructionCompany.getSuid() != null){
                object.put("contractorCorpCode",hjConstructionCompany.getSuid());//总承包单位统一社会信用代码
            } else {
                object.put("contractorCorpCode",hjConstructionCompany.getOrganizationCode());//总承包组织机构代码
            }
        }
        object.put("contractorCorpName",jsonObject.getString("constructionName"));
        object.put("name",hjProject.getProjectName());
        object.put("description","");//项目简介
        object.put("category","项目分类。参考国标(基础数据类型数据字典)");//项目分类。参考国标(基础数据类型数据字典)
        object.put("string","");//建设单位名称
        object.put("buildCorpCode","");//建设单位统一社会信用代码，如果无统一社会信用代码，则填写组织机构代码
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("prjName",jsonObject.getString("prjName"));//工程名称
        jsonObject1.put("builderLicenseNum",jsonObject.getString("builderLicenseNum"));//施工许可证编号。
        jsonArray.add(jsonObject1);
        object.put("builderLicenses",jsonArray);//施工许可证。JSON数组
        object.put("buildPlanNum","");//建设用地规划许可证编号。
        object.put("prjPlanNum","");//建设工程规划许可证编号
        object.put("areaCode","项目所在地。。参考国标（行政区划字典）");//项目所在地。。参考国标（行政区划字典）
        object.put("invest","");//总投资，单位：（万元）
        object.put("buildingArea","");//总面积，单位：平方米
        object.put("buildingLength","");//总长度，单位：米
        object.put("startDate",hjProject.getStartingTime());//开工日期，精确到天，格式：yyyy-MM-dd
        object.put("completeDate","");//竣工日期，精确到天，格式：yyyy-MM-dd
        object.put("linkMan","");//联系人姓名
        object.put("linkPhone","");//联系人办公电话
        object.put("prjStatus","项目状态。参考国标(基础数据类型数据字典)");//项目状态。参考国标(基础数据类型数据字典)
        object.put("lat","");//WGS84经度
        object.put("lng","");//WGS84纬度
        object.put("address","");//项目地点
        object.put("approvalNum","");//立项文号
        object.put("approvalLevelNum","");//立项级别。参考国标(基础数据类型数据字典)
        object.put("prjSize","");//建设规模。参考国标(基础数据类型数据字典)
        object.put("propertyNum","");//建设性质。参考国标(基础数据类型数据字典)
        object.put("functionNum","");//工程用途。参考国标(基础数据类型数据字典)
        object.put("thirdPartyProjectCode","供应商为项目创建的编码，同一个系统不能重复编码");//供应商为项目创建的编码，同一个系统不能重复编码
        String s = Util.httpPostWithJSON("UploadSmz/UploadItemInfo",object);
        return s;
    }
}
