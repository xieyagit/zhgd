package com.hujiang.project.zhgd.hjProjectWorkers.api;


import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.hujiang.common.support.Convert;
import com.hujiang.common.utils.AliOcrUtil;
import com.hujiang.common.utils.AliyunOSSClientUtil;
import com.hujiang.common.utils.FaceMatchUtil;
import com.hujiang.common.utils.StringUtil;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.*;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.MoredianClient;
import com.hujiang.project.zhgd.utils.Util;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 人员信息
 *
 * @author hujiang
 * @date 2019-05-20
 */
@RestController
@RequestMapping(value = "/provider/pc/projectWorkersApi", method = RequestMethod.POST)
public class PC_ProjectWorkersApi extends BaseController {

    private Logger logger = Logger.getLogger(PC_ProjectWorkersApi.class.getName());
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;

    @Autowired
    private IHjProjectService hjProjectService;
    @Resource
    private MoredianClient moredianClient;

    /**
     * 查询项目工人列表
     */
    @PostMapping("/list")
    public TableDataInfo list(HjProjectWorkers hjProjectWorkers) {
        startPage();
        List<ProjectWorkerPC> list = hjProjectWorkersService.selectProjectWorkersListPC(hjProjectWorkers);
        return getDataTable(list);
    }

    /**
     * pdf导出查询
     * @param ids
     * @return
     */
    @PostMapping("/selectPdfWorkers")
    public List<PdfWorkers> selectPdfWorkers(String ids){
        return hjProjectWorkersService.selectPdfWorkers(Convert.toStrArray(ids));
    }

    /**
     * 导出工人 数据源
     * @param hjProjectWorkers
     * @return
     */
    @PostMapping("/export")
    public List<ProjectWorkerPC> export(@RequestBody HjProjectWorkers hjProjectWorkers)
    {
        return hjProjectWorkersService.selectProjectWorkersListPC(hjProjectWorkers);
    }
    /**
     * 修改保存项目工人
     */
    @Log(title = "项目工人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editSave(HjProjectWorkers hjProjectWorkers)throws Exception
    {
        int i = hjProjectWorkersService.updateHjProjectWorkers(hjProjectWorkers);
        if(i>0){
            boolean b = moredianClient.enteringMoredianPerson( hjProjectWorkersService.selectHjProjectWorkersById(hjProjectWorkers.getId()), 3);
            logger.info("执行相应魔点 人员修改操作---"+b);
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error(-1,"修改失败");
    }

    /**
     * 同步人员 进出或退场
     * @param tag
     * @param ids
     * @return
     */
    @PostMapping(value = "outOrIn")
    public  Map<String,Object> outOrIn(Integer tag,String ids){
        logger.info(ids+"同步人员 进出或退场"+tag+"开始");
        Map<String, Object> map = hjProjectWorkersService.updateHjProjectWorkersOutOrIn(Convert.toStrArray(ids), tag);
        logger.info(ids+"同步人员 进出或退场"+tag+"结束");
        return map;
    }

    /**
     * 同步人员 进出或退场
     * @return
     */
    @PostMapping(value = "outOrInTwo")
    public  Map<String,Object> outOrInTwo(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(json);
        String idCode = jsonObject.getString("idCode");
        Integer projectId = jsonObject.getInteger("projectId");
        Integer tag = 1;
        HjProjectWorkers hjProjectWorkers = hjProjectWorkersService.selectHjProjectWorkersByIdCode(idCode,projectId);
        int result = hjProjectWorkersService.updateHjProjectWorkersOutOrInTwo(hjProjectWorkers.getId(), tag);
        if(result>0){
            map.put("msg","成功");
            map.put("code",0);
        }else {
            map.put("msg","失败");
            map.put("code",-1);
        }

        return map;
    }

    /**
     * 修改前查询
     * @param id 人员id
     * @param
     * @return
     */
    @PostMapping(value = "queryProjectWorkers")
    public  Map<String,Object> queryProjectWorkers(Integer id){
        return hjProjectWorkersService.queryProjectWorkers(id);
    }










    /**
     * 实名制录入
     *
     * @param hjProjectWorkers
     * @return
     */
//    @RequestMapping(value = "/insertProjectWorkers")
//    public Map<String, Object> insertProjectWorkers(HjProjectWorkers hjProjectWorkers, MultipartFile face,  MultipartFile scan, MultipartFile scan2, MultipartFile bank) throws IOException {
//        HjProjectWorkers hjProjectWorkers1 = new HjProjectWorkers();
//        hjProjectWorkers1.setProjectId(hjProjectWorkers.getProjectId()); // 项目id
//        hjProjectWorkers1.setIdCode(hjProjectWorkers.getIdCode()); // 身份号
//        hjProjectWorkers1.setConstructionId(hjProjectWorkers.getConstructionId()); // 参建单位
//        // 实名制录入时判断项目中是否有该人员
//        List<HjProjectWorkers>  hjProjectWorkersList = hjProjectWorkersService.selectHjProjectWorkersList(hjProjectWorkers1);
//        if(hjProjectWorkersList.size()>0){
//            if(hjProjectWorkersList.get(0).getEnterAndRetreatCondition() == 0){
//                return AjaxResult.error(-1,hjProjectWorkers.getEmpName()+"已在项目中");
//            }else if(hjProjectWorkersList.get(0).getEnterAndRetreatCondition() == 1){
//                return AjaxResult.error(-1,hjProjectWorkers.getEmpName()+"已在项目中退场,请返场");
//            }else{
//                return AjaxResult.error(-1,hjProjectWorkers.getEmpName()+"已在项目中,暂未同步");
//            }
//        }
//
//        String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(),
//                "hujiang", "workers/");
//        if (face != null) {
//            //人脸照片
//            String filename = StringUtil.getRandomStringByLength(6) + new SimpleDateFormat("HHmmss").format(new Date()) + face.getOriginalFilename().substring(face.getOriginalFilename
//                    ().lastIndexOf("."));
//            String FileImgurl = AliyunOSSClientUtil.uploadFileImg(face, folder, filename);
//            if (!"".equals(FileImgurl)) {
//                String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
//                hjProjectWorkers.setFaceUrl(name);
//            }
//        }
//
//        if (scan != null) {
////身份证正面照片
//            String filename = StringUtil.getRandomStringByLength(6) + new SimpleDateFormat("HHmmss").format(new Date()) + scan.getOriginalFilename().substring(scan.getOriginalFilename
//                    ().lastIndexOf("."));
//            String FileImgurl = AliyunOSSClientUtil.uploadFileImg(scan, folder, filename);
//            if (!"".equals(FileImgurl)) {
//                String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
//                hjProjectWorkers.setIdphotoScan(name);
//                //身份证人脸照片
//                hjProjectWorkers.setEmpNaticeplace(name);
//            }
//        }
//
//        if (scan2 != null) {
////身份证反面照片
//            String filename = StringUtil.getRandomStringByLength(6) + new SimpleDateFormat("HHmmss").format(new Date()) + scan2.getOriginalFilename().substring(scan2.getOriginalFilename
//                    ().lastIndexOf("."));
//            String FileImgurl = AliyunOSSClientUtil.uploadFileImg(scan2, folder, filename);
//            if (!"".equals(FileImgurl)) {
//                String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
//                hjProjectWorkers.setIdphotoScan2(name);
//            }
//        }
//        if (bank != null) {
////银行卡照片
//            String filename = StringUtil.getRandomStringByLength(6) + new SimpleDateFormat("HHmmss").format(new Date()) + bank.getOriginalFilename().substring(bank.getOriginalFilename
//                    ().lastIndexOf("."));
//            String FileImgurl = AliyunOSSClientUtil.uploadFileImg(bank, folder, filename);
//            if (!"".equals(FileImgurl)) {
//                String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
//                hjProjectWorkers.setBankCardUrl(name);
//            }
//        }
//
//        if (hjProjectWorkers.getCwrIskeypsn() == null) {//是否重要人员
//            hjProjectWorkers.setCwrIskeypsn("1");
//        }
//        if (hjProjectWorkers.getIfContract() == null) {//是否办理合同
//            hjProjectWorkers.setCwrIskeypsn("1");
//        }
//        return hjProjectWorkersService.insertProjectWorkers(hjProjectWorkers);
//    }


    /** 看板1.0数据*/
    @PostMapping(value = "/getKQCount")
    public JSONObject kanban(Integer projectId){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        String passedTime = dateNowStr;
        String passtime = "";
        List<HjProjectWorkers> records = hjProjectWorkersService.listcount(projectId,passtime);
        JSONObject jsonObject = new JSONObject();
        if (records.size()==0){
            jsonObject.put("sum", "0");          //项目在场人员
        }else {
            jsonObject.put("sum", records.get(0).getCount());          //项目在场人员
        }
        Integer ids = 00;
        int s= 0;
        List<HjProjectWorkers> record = hjProjectWorkersService.listcounts(projectId,passedTime,ids);
        if (record.size()==0) {
            jsonObject.put("workerCheck", "0");        //今日工人考勤
        }else{
            jsonObject.put("workerCheck", record.size());        //今日工人考勤
            s= s+record.size();
        }
        ids = 1;
        record = hjProjectWorkersService.listcounts(projectId,passedTime,ids);
        if (record.size()==0){
            jsonObject.put("managerCheck", "0");        //今日管理考勤人数
        }else {
            jsonObject.put("managerCheck", record.size());        //今日管理考勤人数
            s= s+record.size();
        }
        Integer contract = null;
        HjProjectWorkers projectWorkers = hjProjectWorkersService.jyht(projectId,contract);
        Integer l = Integer.valueOf(projectWorkers.getCount());
        if (l==0){
            jsonObject.put("bfb", "0%");
        }else {
            Object util = Util.accuracy(s,records.get(0).getCount(),0);
            String i = String.valueOf(util+"%");
            jsonObject.put("bfb", i);
        }
        if (s>0){
            jsonObject.put("kq",s);//今日考勤总数
        }else{
            jsonObject.put("kq",0);//今日考勤总数
        }
        JSONObject object = new JSONObject();
        object.put("xcry",jsonObject);
        return object;
    }


    @PostMapping(value = "/getDataCount")
    public JSONObject jyht(Integer projectId){
        JSONObject jsonObject = new JSONObject();
        JSONObject object = new JSONObject();
        //劳动合同
        Integer contract = null;
        HjProjectWorkers projectWorkers = hjProjectWorkersService.jyht(projectId,contract);
        if (projectWorkers.getCount()==0){
            jsonObject.put("total", "");//共录入人
        }else {
            jsonObject.put("total", projectWorkers.getCount());//共录入人
        }
        contract = 1;
        HjProjectWorkers projectWorkers1 = hjProjectWorkersService.jyht(projectId,contract);
        if (projectWorkers1.getCount()==0){
            jsonObject.put("ht", "0"); //已签订
        }else {
            jsonObject.put("ht", projectWorkers1.getCount()); //已签订
        }
        contract = 0;
        HjProjectWorkers projectWorkers2 = hjProjectWorkersService.jyht(projectId,contract);
        if (projectWorkers2.getCount()==0){
            jsonObject.put("wq", "0");//未签订
            jsonObject.put("hg", "");            //是否合格
            jsonObject.put("bfb", "0");
            object.put("contract", jsonObject);
        }else {
            jsonObject.put("wq", projectWorkers2.getCount());//未签订

        }
        double q = Double.valueOf(projectWorkers1.getCount());
        double l = Double.valueOf(projectWorkers.getCount());
        Object util = Util.accuracy(q, l, 0);
        String i = String.valueOf(util + "%");
        if (q == 0 ){
            jsonObject.put("bfb", "0%");
        }else {
            jsonObject.put("bfb", i);
        }
        double p = projectWorkers.getCount();
        String sb = "不合格";
        if (i.equals("100%")) {
            sb = "合格";
        }
        if (p==0){
            jsonObject.put("hg", "");            //是否合格
        }else {
            jsonObject.put("hg", sb);            //是否合格
        }

        object.put("contract", jsonObject);
        //进场确认书
        contract = null;
        JSONObject jsonObject1 = new JSONObject();
        HjProjectWorkers projectWorkers3 = hjProjectWorkersService.jc(projectId,contract);
        if (projectWorkers3.getCount()==0){
            jsonObject1.put("total", "0");//共录入人
        }else {
            jsonObject1.put("total", projectWorkers3.getCount());//共录入人
        }
        contract = 1;
        HjProjectWorkers projectWorkers4 = hjProjectWorkersService.jc(projectId,contract);
        if (projectWorkers4.getCount()==0){
            jsonObject1.put("ht","0"); //已签订
        }else {
            jsonObject1.put("ht", projectWorkers4.getCount()); //已签订
        }
        contract = 0;
        HjProjectWorkers projectWorkers5 = hjProjectWorkersService.jc(projectId,contract);
        if (projectWorkers5.getCount()==0){
            jsonObject1.put("wq", "0");//未签订
        }else {
            jsonObject1.put("wq", projectWorkers5.getCount());//未签订
        }
        p = projectWorkers3.getCount();

        q = Double.valueOf(projectWorkers4.getCount());
        l = Double.valueOf(projectWorkers3.getCount());
        util = Util.accuracy(q,l,0);
        i = String.valueOf(util+"%");
        if (q==0){
            jsonObject1.put("bfb", "");
        }else {
            jsonObject1.put("bfb", i);
        }
        sb = "不合格";
        if (i.equals("100%")){
            sb = "合格";
        }
        if (p==0){
            jsonObject1.put("hg", "");            //是否合格
        }else {
            jsonObject1.put("hg", sb);            //是否合格
        }

        object.put("workConfirm",jsonObject1);

        //退场确认书
        contract = null;
        JSONObject jsonObject2 = new JSONObject();
        HjProjectWorkers projectWorkers6 = hjProjectWorkersService.tc(projectId,contract);
        if (projectWorkers6.getCount()==0) {
            jsonObject2.put("total", "0");//共录入人
        }else{
            jsonObject2.put("total", projectWorkers6.getCount());//共录入人
        }
        contract = 1;
        HjProjectWorkers projectWorkers7 = hjProjectWorkersService.tc(projectId,contract);
        if (projectWorkers7.getCount()==0){
            jsonObject2.put("ht", "0"); //已签订
        }else {
            jsonObject2.put("ht", projectWorkers7.getCount()); //已签订
        }
        contract = 0;
        HjProjectWorkers projectWorkers8 = hjProjectWorkersService.tc(projectId,contract);
        if (projectWorkers8.getCount()==0){
            jsonObject2.put("wq","0");//未签订
        }else {
            jsonObject2.put("wq", projectWorkers8.getCount());//未签订
        }
        p = projectWorkers6.getCount();
        q = Double.valueOf(projectWorkers7.getCount());
        l = Double.valueOf(projectWorkers6.getCount());
        util = Util.accuracy(q,l,0);
        i = String.valueOf(util+"%");
        if (q == 0){
            jsonObject2.put("bfb", "");
        }else {
            jsonObject2.put("bfb", i);
        }
        sb = "不合格";
        if (i.equals("100%")){
            sb = "合格";
        }
        if (p==0){
            jsonObject2.put("hg", "");            //是否合格
        }else {
            jsonObject2.put("hg", sb);            //是否合格
        }

        object.put("entrance",jsonObject2);
        //两制确认书
        contract = null;
        JSONObject jsonObject3 = new JSONObject();
        HjProjectWorkers projectWorkers9 = hjProjectWorkersService.lzqrs(projectId,contract);
        if(projectWorkers9.getCount()==0){
            jsonObject3.put("total","");//共录入人
        }else{
            jsonObject3.put("total",projectWorkers9.getCount());//共录入人
        }
        contract = 1;
        HjProjectWorkers projectWorkers10 = hjProjectWorkersService.lzqrs(projectId,contract);
        if (projectWorkers10.getCount()==0){
            jsonObject3.put("ht", ""); //已签订
        }else {
            jsonObject3.put("ht", projectWorkers10.getCount()); //已签订
        }
        contract = 0;
        HjProjectWorkers projectWorkers11 = hjProjectWorkersService.lzqrs(projectId,contract);
        jsonObject3.put("wq",projectWorkers11.getCount());//未签订
        p = projectWorkers9.getCount();
        q = Double.valueOf(projectWorkers10.getCount());
        l = Double.valueOf(projectWorkers9.getCount());
        util = Util.accuracy(q,l,0);
        i = String.valueOf(util+"%");
        if (q==0){
            jsonObject3.put("bfb", "");
        }else {
            jsonObject3.put("bfb", i);
        }
        sb = "不合格";
        if (i.equals("100%")){
            sb = "合格";
        }
        if (projectWorkers9.getCount()==0){
            jsonObject3.put("hg", "");            //是否合格
        }else {
            jsonObject3.put("hg", sb);            //是否合格
        }

        object.put("exit_pdf",jsonObject3);
        return object;
    }
}
