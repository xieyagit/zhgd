package com.hujiang.project.zhgd.hjProjectWorkers.api;


import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.hujiang.common.utils.AliOcrUtil;
import com.hujiang.common.utils.FaceMatchUtil;
import com.hujiang.common.utils.StringUtil;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.EmpNameParam;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.ProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.ProjectWorkersParam;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.SignParam;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.Constants;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 人员信息
 *
 * @author hujiang
 * @date 2019-05-20
 */
@RestController
@RequestMapping(value = "/provider/projectWorkersApi", method = RequestMethod.POST)
public class ProjectWorkersApi {
    @Autowired
    private IHjConstructionCompanyService constructionCompanyService;

    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;

    @Autowired
    private IHjProjectService hjProjectService;

    @Autowired
    private IHjTeamService teamService;

    @Autowired
    private IHjConstructionProjectService constructionProjectService;

    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;


    /**
     * 人员信息列表
     *
     * @param empNameParam
     * @return
     */
    @RequestMapping("/selectProjectWorkers")
    @ResponseBody
    public Map<String, Object> selectProjectWorkers(@RequestBody EmpNameParam empNameParam) {
        Map<String, Object> result = hjProjectWorkersService.selectProjectWorkers(empNameParam);
        return result;
    }


    /**
     * 人员信息 详情
     *
     * @param projectWorkersParam
     * @return
     */
    @RequestMapping("/selectProjectWorkersDetails")
    @ResponseBody
    public Map<String, Object> selectProjectWorkersDetails(@RequestBody ProjectWorkersParam projectWorkersParam) {
        Map<String, Object> result = hjProjectWorkersService.selectProjectWorkersDetails(projectWorkersParam);
        return result;
    }


    /**
     * 人员信息 所属单位
     *
     * @param projectWorkers
     * @return
     */
    @RequestMapping("/selectConstructionProject")
    @ResponseBody
    public Map<String, Object> selectConstructionProject(@RequestBody ProjectWorkers projectWorkers) {
        Map<String, Object> result = hjProjectWorkersService.selectConstructionProject(projectWorkers);
        return result;
    }

    /**
     * 人员信息 是否签订
     *
     * @param signParam
     * @return
     */
    @RequestMapping("/selectSignParam")
    @ResponseBody
    public Map<String, Object> selectSignParam(@RequestBody SignParam signParam) {
        return hjProjectWorkersService.selectSignParam(signParam);
    }

    /**
     * 实名制录入
     *
     * @param hjProjectWorkers
     * @return
     */
    @RequestMapping("/insertProjectWorkers")
    @ResponseBody
    public Map<String, Object> insertProjectWorkers(@RequestBody HjProjectWorkers hjProjectWorkers) {
        if (hjProjectWorkers.getCwrIskeypsn() == null) {//是否重要人员
            hjProjectWorkers.setCwrIskeypsn("1");
        }
        if (hjProjectWorkers.getIfContract() == null) {//是否办理合同
            hjProjectWorkers.setCwrIskeypsn("1");
        }
        return hjProjectWorkersService.insertProjectWorkers(hjProjectWorkers);
    }

    /**
     * 实名制录入
     * 对外接口
     *
     * @param json
     * @return
     */
    @RequestMapping("/insertProjectWorkersTwo")
    @ResponseBody
    public Map<String, Object> insertProjectWorkersTwo(@RequestBody String json) throws IOException {
        JSONObject jsonResult = JSONObject.parseObject(json);
        HjProjectWorkers hjProjectWorkers = JSONObject.parseObject(jsonResult.toJSONString(), HjProjectWorkers.class);
        Map<String, Object> map = new HashMap<>();
        if (hjProjectWorkers.getCwrIskeypsn() == null) {//是否重要人员
            hjProjectWorkers.setCwrIskeypsn("1");
        }
        if (hjProjectWorkers.getIfContract() == null) {//是否办理合同
            hjProjectWorkers.setCwrIskeypsn("1");
        }
        HjConstructionCompany hjConstructionCompany = new HjConstructionCompany();
        hjConstructionCompany.setConstructionName(hjProjectWorkers.getConstructionName());
        HjConstructionCompany constructionCompany = constructionCompanyService.getConstructionCompany(hjConstructionCompany);
        if (constructionCompany != null) {
            HjConstructionProject constructionProject = constructionProjectService.selectHjConstructionProjectByProjectId(constructionCompany.getId());
            if (constructionProject.getProjectId().equals(hjProjectWorkers.getProjectId())) {
                if (jsonResult.getString("faceUrl") != null && !"".equals(jsonResult.getString("faceUrl"))) {
                    MultipartFile faceUrlFile = BASE64DecodedMultipartFile.base64ToMultipartOnt(jsonResult.getString("faceUrl").substring(jsonResult.getString("faceUrl").indexOf(",") + 1));
                    String faceImgurl = AliyunOSSClientUtil.uploadFileImg(faceUrlFile, "hujiang", jsonResult.getString("idCode") + System.currentTimeMillis() + ".jpg");
                    String faceUrl = faceImgurl.substring(0, faceImgurl.lastIndexOf("?"));
                    hjProjectWorkers.setFaceUrl(faceUrl);
                }
                if (jsonResult.getString("empNaticeplace") != null && !"".equals(jsonResult.getString("empNaticeplace"))) {
                    MultipartFile empFile = BASE64DecodedMultipartFile.base64ToMultipartOnt(jsonResult.getString("empNaticeplace").substring(jsonResult.getString("empNaticeplace").indexOf(",") + 1));
                    String empImgurl = AliyunOSSClientUtil.uploadFileImg(empFile, "hujiang", jsonResult.getString("idCode") + System.currentTimeMillis() + ".jpg");
                    String empNaticeplace = empImgurl.substring(0, empImgurl.lastIndexOf("?"));
                    hjProjectWorkers.setEmpNaticeplace(empNaticeplace);
                }
                if (jsonResult.getString("idphotoScan") != null && !"".equals(jsonResult.getString("idphotoScan"))) {
                    MultipartFile photoScanFile = BASE64DecodedMultipartFile.base64ToMultipartOnt(jsonResult.getString("idphotoScan").substring(jsonResult.getString("idphotoScan").indexOf(",") + 1));
                    String photoScanImgurl = AliyunOSSClientUtil.uploadFileImg(photoScanFile, "hujiang", jsonResult.getString("idCode") + System.currentTimeMillis() + ".jpg");
                    String photoScanUrl = photoScanImgurl.substring(0, photoScanImgurl.lastIndexOf("?"));
                    hjProjectWorkers.setIdphotoScan(photoScanUrl);
                }
                if (jsonResult.getString("idphotoScan2") != null && !"".equals(jsonResult.getString("idphotoScan2"))) {
                    MultipartFile photoScan2File = BASE64DecodedMultipartFile.base64ToMultipartOnt(jsonResult.getString("idphotoScan2").substring(jsonResult.getString("idphotoScan2").indexOf(",") + 1));
                    String photoScan2Imgurl = AliyunOSSClientUtil.uploadFileImg(photoScan2File, "hujiang", jsonResult.getString("idCode") + System.currentTimeMillis() + ".jpg");
                    String photoScan2Url = photoScan2Imgurl.substring(0, photoScan2Imgurl.lastIndexOf("?"));
                    hjProjectWorkers.setIdphotoScan2(photoScan2Url);
                }
                if (jsonResult.getString("bankCardUrl") != null && !"".equals(jsonResult.getString("bankCardUrl"))) {
                    MultipartFile bankCardFile = BASE64DecodedMultipartFile.base64ToMultipartOnt(jsonResult.getString("bankCardUrl").substring(jsonResult.getString("bankCardUrl").indexOf(",") + 1));
                    String bankCardImgurl = AliyunOSSClientUtil.uploadFileImg(bankCardFile, "hujiang", jsonResult.getString("idCode") + System.currentTimeMillis() + ".jpg");
                    String bankCardUrl = bankCardImgurl.substring(0, bankCardImgurl.lastIndexOf("?"));
                    hjProjectWorkers.setBankCardUrl(bankCardUrl);
                }
                if (jsonResult.getString("jobName") != null && !"".equals(jsonResult.getString("jobName"))) {
                    hjProjectWorkers.setJobName(jsonResult.getString("jobName"));
                }
                if (jsonResult.getString("empCategory") != null && !"".equals(jsonResult.getString("empCategory"))) {
                    hjProjectWorkers.setEmpCategory(jsonResult.getString("empCategory"));
                }
                //根据项目ID和班组名称查询班组ID
                HjTeam hjTeam = new HjTeam();
                hjTeam.setTeamName(hjProjectWorkers.getWorkTypenameName());
                hjTeam.setConstructionId(constructionCompany.getId());
                hjTeam.setProjectId(hjProjectWorkers.getProjectId());
                HjTeam team = teamService.getHjTeam(hjTeam);

                hjProjectWorkers.setConstructionId(constructionCompany.getId());
                hjProjectWorkers.setWorkTypenameId(team.getId());
                hjProjectWorkers.setJobTypename("4");
                hjProjectWorkersService.insertProjectWorkers(hjProjectWorkers);
                map.put("msg", "成功");
                map.put("code", 0);
            } else {
                map.put("msg", "项目ID错误");
                map.put("code", -1);
                // return AjaxResult.error("项目ID错误");
            }

        } else {
            map.put("msg", "失败");
            map.put("code", -1);
        }


        return map;
    }

    @PostMapping(value = "/addAttendanceRecord")
    public Map<String, Object> addAttendanceRecord(@RequestBody String json) throws IOException {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sp.format(new Date());      //获取当前时间
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        HjAttendanceRecord hjAttendanceRecord = JSONObject.parseObject(jsonResult.toJSONString(), HjAttendanceRecord.class);
        String idCode = jsonResult.getString("idCode");
        HjProjectWorkers hjProjectWorkers = hjProjectWorkersService.getProjectWorkersById(idCode);
        hjAttendanceRecord.setEmployeeId(hjProjectWorkers.getId());
        hjAttendanceRecord.setProjectId(hjProjectWorkers.getProjectId());
        hjAttendanceRecord.setWay(1);
        hjAttendanceRecord.setCreateDate(new Date());
        hjAttendanceRecord.setUploadTime(format);
        if (jsonResult.getString("sitePhoto") != null && !"".equals(jsonResult.getString("sitePhoto"))) {
            MultipartFile sitePhotoFile = BASE64DecodedMultipartFile.base64ToMultipartOnt(jsonResult.getString("sitePhoto").substring(jsonResult.getString("sitePhoto").indexOf(",") + 1));
            String photoScan2Imgurl = AliyunOSSClientUtil.uploadFileImg(sitePhotoFile, "hujiang", jsonResult.getString("idCode") + System.currentTimeMillis() + ".jpg");
            String photoScan2Url = photoScan2Imgurl.substring(0, photoScan2Imgurl.lastIndexOf("?"));
            hjAttendanceRecord.setSitePhoto(photoScan2Url);
        }
        int result = hjAttendanceRecordService.insertHjAttendanceRecord(hjAttendanceRecord);
        if (result > 0) {
            map.put("msg", "成功");
            map.put("code", 0);
        } else {
            map.put("msg", "失败");
            map.put("code", -1);
        }
        return map;
    }


    /**
     * 人员信息 修改
     *
     * @param hjProjectWorkers
     * @return
     */
    @RequestMapping("/updateProjectWorkers")
    @ResponseBody
    public Map<String, Object> updateProjectWorkers(HjProjectWorkers hjProjectWorkers,
                                                    MultipartFile file) throws Exception {
        try {

            // 更新照片
            if (file != null) {
                String name = "hj";
                String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(), "hujiang", name.trim() + "/");  // 文件夹名称
                String filename = StringUtil.getRandomStringByLength(6) + new SimpleDateFormat("HHmmss")                                               //文件名称
                        .format(new Date()) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileUrl = AliyunOSSClientUtil.uploadFileImg(file, folder, filename);   // 文件上传
                if (!"".equals(fileUrl)) {
                    String url = fileUrl.substring(0, fileUrl.lastIndexOf("?"));
                    // 照片是否存在
                    HjProjectWorkers hjProjectWorkers1 = hjProjectWorkersService.selectHjProjectWorkersById(hjProjectWorkers.getId());
                    // 人脸对比
                    Integer number = FaceMatchUtil.contrast(url, hjProjectWorkers1.getFaceUrl());
                    if (number == 1) {
                        if (hjProjectWorkers1.getEnterAndRetreatCondition() == 0) {  // 已同步
                            // 在项目中查询用户组
                            HjProject hjProject = hjProjectService.selectHjProjectById(hjProjectWorkers1.getProjectId());
                            // 更新百度人脸库
                            AipFace aipFace = new AipFace(Constants.BD_APP_ID, Constants.BD_API_KEY, Constants.BD_SECRET_KEY);
                            org.json.JSONObject resultObject = aipFace.updateUser(url, "URL", hjProject.getFaceGroup(), hjProjectWorkers.getId().toString(), new HashMap<>());
                            if (resultObject.getString("error_msg").equals("SUCCESS")) {
                                hjProjectWorkers.setFaceToken(resultObject.getJSONObject("result").getString("face_token"));
                                FaceMatchUtil.deleteUrl(hjProjectWorkers1.getFaceUrl()); // 删除阿里云图片
                                hjProjectWorkers.setFaceUrl(url);
                                hjProjectWorkersService.updateHjProjectWorkers(hjProjectWorkers);
                                return AjaxResult.success("修改成功！");
                            } else {
                                FaceMatchUtil.deleteUrl(url); // 删除阿里云图片
                                return AjaxResult.error(-1, "更新失败！");
                            }
                        } else {  // 未同步
                            FaceMatchUtil.deleteUrl(hjProjectWorkers1.getFaceUrl()); // 删除阿里云图片
                            hjProjectWorkers.setFaceUrl(url);
                            hjProjectWorkersService.updateHjProjectWorkers(hjProjectWorkers);
                            return AjaxResult.success("修改成功！");
                        }
                    } else {
                        FaceMatchUtil.deleteUrl(url); // 删除阿里云图片
                        return AjaxResult.error(-1, "修改失败，人脸不匹配！");
                    }
                } else {
                    return AjaxResult.error(-1, "修改失败！");
                }
            } else {
                hjProjectWorkersService.updateHjProjectWorkers(hjProjectWorkers);
                return AjaxResult.success("修改成功！");
            }
        } catch (Exception e) {
            e.getStackTrace();
            return AjaxResult.error(-1, "修改失败！");
        }
    }

    /**
     * 人员银行卡信息 修改
     *
     * @return
     */
    @RequestMapping("/updateProjectWorkersBank")
    @ResponseBody
    public Map<String, Object> updateProjectWorkersBank(@RequestParam("id") int id,@RequestParam("bankName") String bankName,@RequestParam("cardNum") String cardNum,@RequestParam("file") MultipartFile file) {
        if (file != null) {
            String name = "hj";
            String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(), "hujiang", name.trim() + "/");  // 文件夹名称
            String filename = StringUtil.getRandomStringByLength(6) + new SimpleDateFormat("HHmmss")                                               //文件名称
                    .format(new Date()) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            try {
                String fileUrl = AliyunOSSClientUtil.uploadFileImg(file, folder, filename);   // 文件上传
                String url = fileUrl.substring(0, fileUrl.lastIndexOf("?"));
                HjProjectWorkers hjProjectWorkers = hjProjectWorkersService.selectHjProjectWorkersById(id);
                if (hjProjectWorkers!=null){
                    hjProjectWorkers.setEmpBankname(bankName);
                    hjProjectWorkers.setEmpCardnum(cardNum);
                    hjProjectWorkers.setBankCardUrl(url);
                    hjProjectWorkersService.updateHjProjectWorkers(hjProjectWorkers);
                    return AjaxResult.success("添加成功！");
                }else {
                    return AjaxResult.error(-1, "人员不存在！");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.error(-1, "添加失败！");
            }
        }
        return AjaxResult.error(-1, "添加失败！");
    }

    /**
     * 证件识别(身份证正反面,银行卡)
     *
     * @param configStr 正反面(face,back,card)
     * @return
     */
    @RequestMapping(value = "/getAliOcrIdCard")
    @ApiOperation("获取身份证正反面,银行卡信息")
    @ResponseBody
    public Map<String, Object> getAliOcrIdcard(
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "configStr", required = true) String configStr
    ) throws Exception {
        System.out.println(configStr);
        //ios bug解决
        if("face,face".equals(configStr)){
            configStr = "face";
        }
        if("back,back".equals(configStr)){
            configStr = "back";
        }
        if("card,card".equals(configStr)){
            configStr = "card";
        }
        // 文件夹名称
        String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(), "hujiang", configStr.trim() + "/");
        //文件名称
        String filename = StringUtil.getRandomStringByLength(6) + new SimpleDateFormat("HHmmss")
                .format(new Date()) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 文件上传
        String fileUrl = AliyunOSSClientUtil.uploadFileImg(file, folder, filename);
        if (!"".equals(fileUrl)) {
            String name = fileUrl.substring(0, fileUrl.lastIndexOf("?"));
            try {
                // 文件是否为空
                if (configStr.equals("card")) {
                    URL imageUrl = new URL(name);
                    // 银行卡
                    Map<String, String> map = AliOcrUtil.getAliOcrBankcard(imageUrl);
                    map.put("url", name);
                    return AjaxResult.success(map);
                } else {
                    URL imageUrl = new URL(name);

                    Map<String, String> map = AliOcrUtil.getAliOcrIdcard(imageUrl, configStr.replaceAll("\"", ""));
                    map.put("url", name);
                    return AjaxResult.success(map);
                }

            } catch (Exception e) {
                e.printStackTrace();
                FaceMatchUtil.deleteUrl(name); // 删除阿里云图片
                return AjaxResult.error(-1, "请求图片有误，解析失败");
            }
        }
        return null;
    }


    /**
     * 百度云人脸对比
     */
    @RequestMapping(value = "/faceVerify")
    @ApiOperation("百度云人脸对比")
    @ResponseBody
    public Map<String, Object> faceVerify(@RequestParam("imageUrl1") String imageUrl1, @RequestParam("imageUrl2") String imageUrl2) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Integer number = FaceMatchUtil.contrast(imageUrl1, imageUrl2);
            if (number == 1) {
                map.put("result", "true");
            } else {
                map.put("result", "false");
            }
            return AjaxResult.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(-1, "认证失败！");
        }
    }


    /**
     * 人证对比
     */
    @RequestMapping(value = "/queryWitnessComparison")
    @ApiOperation("人证对比")
    @ResponseBody
    public Map<String, Object> queryWitnessComparison(
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "url", required = true) String url
    ) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        String name = "hj";
        String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(), "hujiang", name.trim() + "/");  // 文件夹名称
        String filename = StringUtil.getRandomStringByLength(6) + new SimpleDateFormat("HHmmss")                                               //文件名称
                .format(new Date()) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileUrl = AliyunOSSClientUtil.uploadFileImg(file, folder, filename);   // 文件上传
        if (!"".equals(fileUrl)) {
            String nameUrl = fileUrl.substring(0, fileUrl.lastIndexOf("?"));
            try {
                Integer number = FaceMatchUtil.contrast(url, nameUrl);
                if (number == 1) {
                    map.put("result", "true");
                    map.put("url", nameUrl);
                } else {
                    FaceMatchUtil.deleteUrl(nameUrl); // 删除阿里云图片
                    map.put("result", "false");
                }
                return AjaxResult.success(map);
            } catch (Exception e) {
                e.printStackTrace();
                FaceMatchUtil.deleteUrl(nameUrl); // 删除阿里云图片
                return AjaxResult.error(-1, "认证失败！");
            }
        }
        return null;
    }


}
