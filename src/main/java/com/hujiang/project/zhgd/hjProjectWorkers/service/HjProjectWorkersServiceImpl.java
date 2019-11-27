package com.hujiang.project.zhgd.hjProjectWorkers.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.face.AipFace;
import com.hujiang.common.exception.BusinessException;
import com.hujiang.common.utils.AliOcrUtil;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.client.SystemClient;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.mapper.HjConstructionCompanyMapper;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.mapper.HjProjectMapper;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.*;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.mapper.HjSynchronizationInformationMapper;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.mapper.HjTeamMapper;
import com.hujiang.project.zhgd.hjWorkerRecord.domain.HjWorkerRecord;
import com.hujiang.project.zhgd.hjWorkerRecord.mapper.HjWorkerRecordMapper;
import com.hujiang.project.zhgd.hjWorkers.domain.HjWorkers;
import com.hujiang.project.zhgd.hjWorkers.mapper.HjWorkersMapper;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.utils.APIClient;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.MoredianClient;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjProjectWorkers.mapper.HjProjectWorkersMapper;
import com.hujiang.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * 项目工人 服务层实现
 *
 * @author hujiang
 * @date 2019-05-19
 */
@Service
@Transactional
public class HjProjectWorkersServiceImpl implements IHjProjectWorkersService {
    @Autowired
    private HjProjectWorkersMapper hjProjectWorkersMapper;
    @Autowired
    private HjWorkersMapper hjWorkersMapper;
    @Autowired
    private HjWorkerRecordMapper hjWorkerRecordMapper;
    @Autowired
    private HjProjectMapper hjProjectMapper;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private IHjDeviceProjectworkersService hjDeviceProjectworkersService;
    @Autowired
    private SystemClient client;
    @Resource
    APIClient apiClient;
    @Resource
    private MoredianClient moredianClient;
    @Autowired
    private Queue openYsQueue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private HjConstructionCompanyMapper hjConstructionCompanyMapper;
    private Logger logger = Logger.getLogger(HjProjectWorkersServiceImpl.class.getName());


    @Override
    public int updateHjProjectWorkersOutOrInTwo(Integer id,Integer tag) {
        return hjProjectWorkersMapper.updateHjProjectWorkersOutOrInTwo(id,tag);
    }

    @Override
    public HjProjectWorkers getProjectWorkersById(String idCode) {
        return hjProjectWorkersMapper.getProjectWorkersById(idCode);
    }

    @Override
    public HjProjectWorkers selectHjProjectWorkersByIdCode(String idCode,Integer projectId) {
        return hjProjectWorkersMapper.selectHjProjectWorkersByIdCode(idCode,projectId);
    }

    /**
     * 根据id查询工人信息
     * @param ids
     * @return
     */
    @Override
    public List<PdfWorkers> selectPdfWorkers(String[] ids){
        return hjProjectWorkersMapper.selectPdfWorkers(ids);
    }
    /**
     * 查询项目工人信息
     *
     * @param id 项目工人ID
     * @return 项目工人信息
     */
    @Override
    public HjProjectWorkers selectHjProjectWorkersById(Integer id) {
        return hjProjectWorkersMapper.selectHjProjectWorkersById(id);
    }

    /**
     * 查询项目工人列表
     *
     * @param hjProjectWorkers 项目工人信息
     * @return 项目工人集合
     */
    @Override
    public List<HjProjectWorkers> selectHjProjectWorkersList(HjProjectWorkers hjProjectWorkers) {
        return hjProjectWorkersMapper.selectHjProjectWorkersList(hjProjectWorkers);
    }


    /**
     * 新增项目工人
     *
     * @param hjProjectWorkers 项目工人信息
     * @return 结果
     */
    @Override
    public int insertHjProjectWorkers(HjProjectWorkers hjProjectWorkers) {
        return hjProjectWorkersMapper.insertHjProjectWorkers(hjProjectWorkers);
    }

    /**
     * 修改项目工人
     *
     * @param hjProjectWorkers 项目工人信息
     * @return 结果
     */
    @Override
    public int updateHjProjectWorkers(HjProjectWorkers hjProjectWorkers) {
        return hjProjectWorkersMapper.updateHjProjectWorkers(hjProjectWorkers);
    }

    /**
     * 删除项目工人对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHjProjectWorkersByIds(String ids) {
        return hjProjectWorkersMapper.deleteHjProjectWorkersByIds(Convert.toStrArray(ids));
    }

    /**
     * 人员信息列表
     *
     * @param empNameParam
     * @return
     */
    @Override
    public Map<String, Object> selectProjectWorkers(EmpNameParam empNameParam) {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        // 人员信息 总数
        Integer countProjectWorkers = hjProjectWorkersMapper.selectProjectWorkersCount(empNameParam);

        if (empNameParam.getPage() != null && empNameParam.getPageSize() != null) {
            empNameParam.setOffset((empNameParam.getPage() - 1) * empNameParam.getPageSize());
            empNameParam.setLimit(empNameParam.getPageSize());
        }
        // 人员信息
        List<EmpNameParam> projectWorkersParamList = hjProjectWorkersMapper.selectProjectWorkers(empNameParam);


        if (projectWorkersParamList.size() > 0) {

            List<EmpName> list = new ArrayList<>();
            for (int i = 0; i < projectWorkersParamList.size(); i++) {
                EmpName empName = new EmpName();
                empName.setId(projectWorkersParamList.get(i).getId());
                empName.setEmpName(projectWorkersParamList.get(i).getEmpName());
                empName.setProjectId(projectWorkersParamList.get(i).getProjectId());
                empName.setFaceUrl(projectWorkersParamList.get(i).getFaceUrl());
                list.add(empName);
            }
            map.put("total", countProjectWorkers);
            map.put("page", empNameParam.getPage());
            map.put("rows", list);
            result.put("code", 0);
            result.put("msg", "成功");
            result.put("data", map);
        }
        return result;
    }

    /**
     * 人员信息 所属单位
     *
     * @param projectWorkers
     * @return
     */
    @Override
    public Map<String, Object> selectConstructionProject(ProjectWorkers projectWorkers) {

        Map<String, Object> result = new HashMap<>();

        ProjectWorkers projectWorkers1 = hjProjectWorkersMapper.selectConstructionProject(projectWorkers);

        result.put("code", 0);
        result.put("msg", "成功");
        result.put("data", projectWorkers1);


        return result;
    }

    /**
     * PC人员列表
     *
     * @param projectWorkers
     * @return
     */
    @Override
    public List<ProjectWorkerPC> selectProjectWorkersListPC(HjProjectWorkers projectWorkers) {
        return hjProjectWorkersMapper.selectProjectWorkersListPC(projectWorkers);
    }

    /**
     * 人员信息 详情
     *
     * @param projectWorkersParam
     * @return
     */
    @Override
    public Map<String, Object> selectProjectWorkersDetails(ProjectWorkersParam projectWorkersParam) {

        Map<String, Object> result = new HashMap<>();
        ProjectWorkersParam projectWorkersParam1 = hjProjectWorkersMapper.selectProjectWorkersDetails(projectWorkersParam);
        result.put("code", 0);
        result.put("msg", "成功");
        result.put("data", projectWorkersParam1);
        return result;
    }

    /**
     * 查询不再魔点设备的在场项目人员
     * @param projectId
     * @return
     */
    public List<HjProjectWorkers> selectHjProjectWorkersNotMoreDianByprojectId( Integer projectId){
        return hjProjectWorkersMapper.selectHjProjectWorkersNotMoreDianByprojectId(projectId);
    }
    /**
     * 人员同步进出或退场
     *
     * @param
     * @return
     */
    @Override
    public Map<String, Object> updateHjProjectWorkersOutOrIn(String[] ids, Integer tag){
        Map<String, Object> result = new HashMap<>();
        String successIds = "";
        int success = 0;
        int error = 0;
        try {
            for (int i = 0; i < ids.length; i++) {
                //查询人员信息
                HjProjectWorkers hjProjectWorkers = hjProjectWorkersMapper.selectHjProjectWorkersById(Integer.parseInt(ids[i]));
                if (hjProjectWorkers.getEnterAndRetreatCondition().equals(tag) && tag != 2) {//人员信息和要操作的状态一致
                    error++;//获取不可执行人员数量

                } else {

                    if(tag == 0){
                        //执行相应同步信息
                        boolean re =apiClient.registerEmployeeTest(hjProjectWorkers);

                        logger.info("执行相应同步信息---"+re);
                        if(re){
                            boolean b = moredianClient.enteringMoredianPerson( hjProjectWorkers, 1);
                            logger.info("执行相应魔点 人员进场操作---"+b);
                            logger.info("海清人脸机添加人脸操作---");
                            hqAdd(hjProjectWorkers);
                        }

                        if(re == true){
                            success++;//获取可执行人员数量
                            successIds += hjProjectWorkers.getId() + ",";//获取可执行人员id
                        }
                    }else {
                        boolean re =apiClient.deleteUserLeaveProject(hjProjectWorkers);

                        logger.info("执行相应退场信息---"+re);
                        if(re){
                            boolean b = moredianClient.enteringMoredianPerson( hjProjectWorkers, 2);
                            logger.info("执行相应魔点 人员退场操作---"+b);
                            logger.info("海清人脸机删除人脸操作---");
                            hqRemove(hjProjectWorkers);
                        }

                        if(re == true){
                            success++;//获取可执行人员数量
                            successIds += hjProjectWorkers.getId() + ",";//获取可执行人员id
                        }
                    }

                }

            }
            logger.info("同步---"+success);
            if (success > 0) {
                result.put("code", 0);
                result.put("msg", tag == 1 ? success + "人退场成功," + error + "人退场失败" : success + "人进场成功," + error + "人进场失败");
            } else {
                result.put("code", -1);
                result.put("msg", tag == 1 ? "退场操作失败,所选人员都已退场" : "进场操作失败,所选人员都已进场");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public void hqAdd(HjProjectWorkers hw){
//        HjAttendanceDevice had=new HjAttendanceDevice();
//        had.setProjectId(hw.getProjectId());
//        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
//        HjDeviceProjectworkers hdpw=new HjDeviceProjectworkers();
//        hdpw.setProjectWorkersId(hw.getId());
//        List<HjDeviceProjectworkers> list;
//        for(HjAttendanceDevice h: hadList){
//            hdpw.setStatus("2");
//            hdpw.setDeviceNo(h.getDeviceNo());
//            list=hjDeviceProjectworkersService.selectHjDeviceProjectworkersList(hdpw);
//            if(list.size()>0){
//                hdpw.setStatus("1");
//                hjDeviceProjectworkersService.updateHjDeviceProjectworkersTwo(hdpw);
//            }else{
//                hdpw.setStatus("0");
//                hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hdpw);
//            }
//
//        }
        //放进消息队列执行
        JmsMessageInfo<HjProjectWorkers> messageInfo = new JmsMessageInfo<HjProjectWorkers>();
        messageInfo.setBody(hw);
        messageInfo.setType(JmsMessageType.INSERT_ATTENDANCE);
        jmsMessagingTemplate.convertAndSend(openYsQueue, JsonUtils.toJson(messageInfo));
    }
    public void hqRemove(HjProjectWorkers hw){
//        HjAttendanceDevice had=new HjAttendanceDevice();
//        had.setProjectId(hw.getProjectId());
//        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
//        Map<String,String> map=new HashMap<String,String>();
//        HjDeviceProjectworkers hdpw=new HjDeviceProjectworkers();//
//        hdpw.setStatus("2");//
//        hdpw.setProjectWorkersId(hw.getId());//
//        hjDeviceProjectworkersService.updateHjDeviceProjectworkersTwo(hdpw);//
//        for(HjAttendanceDevice h: hadList){

//         int i=   client.oneClean(h.getDeviceNo(),hw.getId());
//            System.out.println("==================删除海清人脸："+i);
//         if(i==1){
//            map.put("deviceNo",h.getDeviceNo());
//            map.put("projectWorkersId",hw.getId().toString());
//          int a=   hjDeviceProjectworkersService.deleteIds(map);
//             System.out.println("==================删除a："+a);
//         }
//        }
        JmsMessageInfo<HjProjectWorkers> messageInfo = new JmsMessageInfo<HjProjectWorkers>();
        messageInfo.setBody(hw);
        messageInfo.setType(JmsMessageType.DELETE_ATTENDANCE);
        jmsMessagingTemplate.convertAndSend(openYsQueue, JsonUtils.toJson(messageInfo));
    }
    /**
     * 修改前查询
     * @param id 人员id
     * @param
     * @return
     */
    @Override
    public Map<String, Object> queryProjectWorkers(Integer id) {
        try {
            HjProjectWorkers hjProjectWorkers = hjProjectWorkersMapper.selectHjProjectWorkersById(id);
            return AjaxResult.success(hjProjectWorkers);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(-1,"查询失败 ");
        }
    }

    /**
     * 实名制录入
     *
     * @param hjProjectWorkers
     * @return
     */
    @Override
    public Map<String, Object> insertProjectWorkers(HjProjectWorkers hjProjectWorkers) {




        HjProject hjProject = hjProjectMapper.selectHjProjectById(hjProjectWorkers.getProjectId());

        List<HjProjectWorkers> list = hjProjectWorkersMapper.selectEmpCategory(hjProjectWorkers.getProjectId());

        if(hjProject.getProjectNumber() > list.size()){
            HjProjectWorkers hjProjectWorkers1 = new HjProjectWorkers();
            hjProjectWorkers1.setProjectId(hjProjectWorkers.getProjectId()); // 项目id
            hjProjectWorkers1.setIdCode(hjProjectWorkers.getIdCode()); // 身份号
            // 实名制录入时判断项目中是否有该人员
            List<HjProjectWorkers> hjProjectWorkersList = hjProjectWorkersMapper.selectHjProjectWorkersList(hjProjectWorkers1);

            if (hjProjectWorkersList.size() > 0) {

                if (hjProjectWorkersList.get(0).getEnterAndRetreatCondition() == 0) {
                    return AjaxResult.error(-1, hjProjectWorkers.getEmpName() + "已在项目中");
                } else if (hjProjectWorkersList.get(0).getEnterAndRetreatCondition() == 1) {
                    return AjaxResult.error(-1, hjProjectWorkers.getEmpName() + "已在项目中退场,请返场");
                } else {
                    return AjaxResult.error(-1, hjProjectWorkers.getEmpName() + "已在项目中,暂未同步");
                }
            } else {
                hjProjectWorkers.setEnterAndRetreatCondition(2);
                HjConstructionCompany hcc=hjConstructionCompanyMapper.selectHjConstructionCompanyById(hjProjectWorkers.getConstructionId());
                if("1".equals(hcc.getIsUpload())){
                    hjProjectWorkers.setIsUpload("1");
                }else{
                    hjProjectWorkers.setIsUpload("0");
                }
                hjProjectWorkersMapper.insertHjProjectWorkers(hjProjectWorkers); // 加入项目工人表


                //  HjProjectWorkers hjProjectWorkers2 = this.saveFaceOnBaidu(hjProjectWorkers.getId()); // 保存到百度人脸库

                //判断是否加入工人库
                HjWorkers hjWorkers = new HjWorkers();
                hjWorkers.setIdCode(hjProjectWorkers.getIdCode());
                List<HjWorkers> hjWorkersList = hjWorkersMapper.selectHjWorkersList(hjWorkers);
                if (hjWorkersList.size() > 0) { // 工人库已存在

                    // 更新产业工人
                    HjWorkers hjWorkers1 = new HjWorkers();
                    hjWorkers1.setEmpName(hjWorkersList.get(0).getEmpName()); // 姓名
                    hjWorkers1.setIdCode(hjWorkersList.get(0).getIdCode());// 身份证号码
                    hjWorkers1.setEmpPhon(hjWorkersList.get(0).getEmpPhon()); // 手机号码
                    hjWorkers1.setEmpSex(hjWorkersList.get(0).getEmpSex()); // 性别
                    hjWorkers1.setEmpNation(hjWorkersList.get(0).getEmpNation());// 民族
                    hjWorkers1.setIdAddress(hjWorkersList.get(0).getIdAddress()); // 身份证地址
                    hjWorkers1.setIdAgency(hjWorkersList.get(0).getIdAgency()); // 签发机关
                    hjWorkers1.setIdValiddate(hjWorkersList.get(0).getIdValiddate()); // 有效期限
                    hjWorkers1.setDateOfBirth(hjWorkersList.get(0).getDateOfBirth()); // 出生日期
                    hjWorkers1.setNativePlace(hjWorkersList.get(0).getNativePlace()); // 籍贯
                    hjWorkers1.setIsTeam(hjWorkersList.get(0).getIsTeam()); // 是否班组长(0否，1是)
                    hjWorkers1.setJobName(hjWorkersList.get(0).getJobName()); // 工种名称
                    hjWorkers1.setEmpBankname(hjWorkersList.get(0).getEmpBankname()); // 开户行
                    hjWorkers1.setEmpCardnum(hjWorkersList.get(0).getEmpCardnum());// 银行账号
                    hjWorkers1.setAccountType(hjWorkersList.get(0).getAccountType());// 账户类型
                    hjWorkers1.setAccountAddress(hjWorkersList.get(0).getAccountAddress()); // 开户地址
                    hjWorkers1.setCredential(hjWorkersList.get(0).getCredential()); // 获得证书
                    hjWorkers1.setRemark(hjWorkersList.get(0).getRemark()); //  备注
                    hjWorkers1.setFaceUrl(hjWorkersList.get(0).getFaceUrl()); // 人脸照片
                    hjWorkers1.setEmpNaticeplace(hjWorkersList.get(0).getEmpNaticeplace()); // 身份证人脸照片
                    hjWorkers1.setIdphotoScan(hjWorkersList.get(0).getIdphotoScan()); // 身份证正面照片
                    hjWorkers1.setIdphotoScan2(hjWorkersList.get(0).getIdphotoScan2()); // 身份证反面照片
                    hjWorkers1.setBankCardUrl(hjWorkersList.get(0).getBankCardUrl());// 银行卡照片
                    hjWorkers1.setId(hjWorkersList.get(0).getId());
                    hjWorkersMapper.updateHjWorkers(hjWorkers1);

                    // 加入从业记录
                    HjWorkerRecord hjWorkerRecord = HjProjectWorkersServiceImpl.addHjWorkerRecord(hjProjectWorkers);
                    hjWorkerRecordMapper.insertHjWorkerRecord(hjWorkerRecord);
                } else { // 不在工人库
                    // 加入产业工人
                    HjWorkers hjWorkers1 = new HjWorkers();
                    hjWorkers1.setEmpName(hjProjectWorkers.getEmpName()); // 姓名
                    hjWorkers1.setIdCode(hjProjectWorkers.getIdCode());// 身份证号码
                    hjWorkers1.setEmpPhon(hjProjectWorkers.getEmpPhon()); // 手机号码
                    hjWorkers1.setEmpSex(hjProjectWorkers.getEmpSex()); // 性别
                    hjWorkers1.setEmpNation(hjProjectWorkers.getEmpNation());// 民族
                    hjWorkers1.setIdAddress(hjProjectWorkers.getIdAddress()); // 身份证地址
                    hjWorkers1.setIdAgency(hjProjectWorkers.getIdAgency()); // 签发机关
                    hjWorkers1.setIdValiddate(hjProjectWorkers.getIdValiddate()); // 有效期限
                    hjWorkers1.setDateOfBirth(hjProjectWorkers.getDateOfBirth()); // 出生日期
                    hjWorkers1.setNativePlace(hjProjectWorkers.getNativePlace()); // 籍贯
                    hjWorkers1.setIsTeam(hjProjectWorkers.getIsTeam()); // 是否班组长(0否，1是)
                    hjWorkers1.setJobName(hjProjectWorkers.getJobName()); // 工种名称
                    hjWorkers1.setEmpBankname(hjProjectWorkers.getEmpBankname()); // 开户行
                    hjWorkers1.setEmpCardnum(hjProjectWorkers.getEmpCardnum());// 银行账号
                    hjWorkers1.setAccountType(hjProjectWorkers.getAccountType());// 账户类型
                    hjWorkers1.setAccountAddress(hjProjectWorkers.getAccountAddress()); // 开户地址
                    hjWorkers1.setCredential(hjProjectWorkers.getCredential()); // 获得证书
                    hjWorkers1.setRemark(hjProjectWorkers.getRemark()); //  备注
                    hjWorkers1.setFaceUrl(hjProjectWorkers.getFaceUrl()); // 人脸照片
                    hjWorkers1.setEmpNaticeplace(hjProjectWorkers.getEmpNaticeplace()); // 身份证人脸照片
                    hjWorkers1.setIdphotoScan(hjProjectWorkers.getIdphotoScan()); // 身份证正面照片
                    hjWorkers1.setIdphotoScan2(hjProjectWorkers.getIdphotoScan2()); // 身份证反面照片
                    hjWorkers1.setBankCardUrl(hjProjectWorkers.getBankCardUrl());// 银行卡照片
                    hjWorkers1.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // 创建时间

                    hjWorkersMapper.insertHjWorkers(hjWorkers1);

                    // 加入从业记录
                    HjWorkerRecord hjWorkerRecord = HjProjectWorkersServiceImpl.addHjWorkerRecord(hjProjectWorkers);
                    hjWorkerRecordMapper.insertHjWorkerRecord(hjWorkerRecord);
                }
            }
            return AjaxResult.success("录入成功");
        }else {
            return AjaxResult.error(-1,  "项目管理人员已达到上线");
        }


    }

    /**
     * 是否签订
     *
     * @param signParam
     * @return
     */
    @Override
    public Map<String, Object> selectSignParam(SignParam signParam) {

        try {
            SignParam signParamList = hjProjectWorkersMapper.selectSignParam(signParam);
            return AjaxResult.success(signParamList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(-1, "查询失败！");
        }
    }


    /**
     * 保存人脸图片到人脸库
     */
    private HjProjectWorkers saveFaceOnBaidu(Integer workersId) {
        // 连接百度人脸库
        AipFace aipFace = new AipFace(Constants.BD_APP_ID
                , Constants.BD_API_KEY, Constants.BD_SECRET_KEY);
        HjProjectWorkers hjProjectWorkers = hjProjectWorkersMapper.selectHjProjectWorkersById(workersId);
        // 找到人脸库组名
        HjProject hjProject = hjProjectMapper.selectHjProjectById(hjProjectWorkers.getProjectId());
        System.out.println(hjProject);
        //保存到参建单位人脸库
        System.out.println(hjProjectWorkers.getFaceUrl());
        System.out.println(hjProject.getFaceGroup());
        System.out.println(hjProjectWorkers.getId());
        org.json.JSONObject resultObject = aipFace.addUser(hjProjectWorkers.getFaceUrl(), "URL", hjProject.getFaceGroup(), hjProjectWorkers.getId() + "", new HashMap<>());
        if (resultObject.getString("error_msg").equals("face is already exist")) {
            throw new BusinessException("人脸已存在项目人脸库");
        }
        System.out.println(resultObject);
        if (resultObject.getString("error_msg").equals("SUCCESS")) {
            hjProjectWorkers.setFaceToken(resultObject.getJSONObject("result").getString("face_token"));
            hjProjectWorkersMapper.updateHjProjectWorkers(hjProjectWorkers);
            return hjProjectWorkers;
        } else {
            throw new BusinessException("人脸创建不成功");
        }
    }


    /**
     * 从业记录
     *
     * @param hjProjectWorkers
     * @return
     */
    private static HjWorkerRecord addHjWorkerRecord(HjProjectWorkers hjProjectWorkers) {
        HjWorkerRecord hjWorkerRecord = new HjWorkerRecord();
        hjWorkerRecord.setIdCode(hjProjectWorkers.getIdCode());
        hjWorkerRecord.setProjectId(hjProjectWorkers.getProjectId());
        hjWorkerRecord.setConstructionId(hjProjectWorkers.getConstructionId());
        hjWorkerRecord.setTeamId(hjProjectWorkers.getWorkTypenameId());
        hjWorkerRecord.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return hjWorkerRecord;
    }
    /**
     * 查询指定项目的在场人数
     */
    @Override
    public Integer selectOnLineCount(Integer pid){
        return hjProjectWorkersMapper.selectOnLineCount(pid);
    }
    /**
     * 在场关键人员
     * @param pid
     * @return
     */
    @Override
    public Integer selectOnLineCountGj(Integer pid){
        return hjProjectWorkersMapper.selectOnLineCountGj(pid);    }
    /**
     * 出勤工种统计
     * @param pid
     * @return
     */
    @Override
    public List<Cqgztj> selectCqgztj(Integer pid){
        return hjProjectWorkersMapper.selectCqgztj(pid);
    }
    @Override
    public InOROut selectInOrOutKB(Integer id){
        return hjProjectWorkersMapper.selectInOrOutKB(id);
    }

    /**查找照片*/
    public HjProjectWorkers seleimg(Integer userid){
        return hjProjectWorkersMapper.seleimg(userid);
    }
    public HjProjectWorkers seleimgs(Integer userid){
        return  hjProjectWorkersMapper.seleimgs(userid);
    }

    /**看板数据*/
    public List<HjProjectWorkers> listcount(Integer projectId,String passedTime){
        return hjProjectWorkersMapper.listcount(projectId,passedTime);
    }

    public List<HjProjectWorkers> listcounts(Integer projectId, String passedTime,Integer ids){
        return hjProjectWorkersMapper.listcounts(projectId,passedTime,ids);
    }

    public HjProjectWorkers jyht(Integer projectId,Integer contract){
        return hjProjectWorkersMapper.jyht(projectId,contract);
    }
    public HjProjectWorkers jc(Integer projectId, Integer contract){
        return hjProjectWorkersMapper.jc(projectId,contract);
    }
    public HjProjectWorkers tc(Integer projectId, Integer contract){
        return hjProjectWorkersMapper.tc(projectId, contract);
    }
    public HjProjectWorkers lzqrs(Integer projectId,Integer contract){
        return hjProjectWorkersMapper.lzqrs(projectId, contract);
    }
    public HjProjectWorkers easyContract(Integer projectId){
        return hjProjectWorkersMapper.easyContract(projectId);
    }
}
