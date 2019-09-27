package com.hujiang.project.zhgd.hjAttendanceRecord.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.exception.BusinessException;
import com.hujiang.common.utils.AliyunOSSClientUtil;
import com.hujiang.common.utils.FaceMatchUtil;
import com.hujiang.common.utils.StringUtil;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.DongTai;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.Param;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.mapper.HjProjectMapper;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.TCount;
import com.hujiang.project.zhgd.hjProjectWorkers.mapper.HjProjectWorkersMapper;
import com.hujiang.project.zhgd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjAttendanceRecord.mapper.HjAttendanceRecordMapper;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.common.support.Convert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 考勤记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjAttendanceRecordServiceImpl implements IHjAttendanceRecordService 
{
	@Autowired
	private HjAttendanceRecordMapper hjAttendanceRecordMapper;

	@Autowired
	private HjProjectWorkersMapper hjProjectWorkersMapper; // 项目

	@Autowired
	private HjProjectMapper hjProjectMapper;
	@Resource
	APIClient apiClient;
	private Logger logger = Logger.getLogger(HjAttendanceRecordServiceImpl.class.getName());
	/**
	 * 获取最早一条上班考勤记录
	 * @param hjAttendanceRecord
	 * @return
	 */
	public HjAttendanceRecord selectHjAttendanceRecordListIn(HjAttendanceRecord hjAttendanceRecord){
		return hjAttendanceRecordMapper.selectHjAttendanceRecordListIn(hjAttendanceRecord);
	}

	/**
	 * 获取最新一条下班考勤记录
	 * @param hjAttendanceRecord
	 * @return
	 */
	public HjAttendanceRecord selectHjAttendanceRecordListOut(HjAttendanceRecord hjAttendanceRecord){
		return hjAttendanceRecordMapper.selectHjAttendanceRecordListOut(hjAttendanceRecord);
	}
	/**
	 * 执行保存工人上班工时
	 * @param empId
	 * @param empName
	 * @param projectId
	 */
	public void addTime(Integer empId,String empName,Integer projectId){
		hjAttendanceRecordMapper.addTime(empId, empName, projectId);
	}
	/**
	 * 根据人员id查询该日期是否有进出考勤
	 * @param id
	 * @param time
	 * @return
	 */
	public List<HjAttendanceRecord> selectHjAttendanceRecordInAndOut(Integer id,String time){
		return hjAttendanceRecordMapper.selectHjAttendanceRecordInAndOut(id, time);
	}
	/**
     * 查询考勤记录信息
     * 
     * @param id 考勤记录ID
     * @return 考勤记录信息
     */
    @Override
	public HjAttendanceRecord selectHjAttendanceRecordById(Integer id)
	{
	    return hjAttendanceRecordMapper.selectHjAttendanceRecordById(id);
	}
	
	/**
     * 查询考勤记录列表
     * 
     * @param hjAttendanceRecord 考勤记录信息
     * @return 考勤记录集合
     */
	@Override
	public List<HjAttendanceRecord> selectHjAttendanceRecordList(HjAttendanceRecord hjAttendanceRecord)
	{
	    return hjAttendanceRecordMapper.selectHjAttendanceRecordList(hjAttendanceRecord);
	}
	
    /**
     * 新增考勤记录
     * 
     * @param hjAttendanceRecord 考勤记录信息
     * @return 结果
     */
	@Override
	public int insertHjAttendanceRecord(HjAttendanceRecord hjAttendanceRecord)
	{
	    return hjAttendanceRecordMapper.insertHjAttendanceRecord(hjAttendanceRecord);
	}
	
	/**
     * 修改考勤记录
     * 
     * @param hjAttendanceRecord 考勤记录信息
     * @return 结果
     */
	@Override
	public int updateHjAttendanceRecord(HjAttendanceRecord hjAttendanceRecord)
	{
	    return hjAttendanceRecordMapper.updateHjAttendanceRecord(hjAttendanceRecord);
	}

	/**
     * 删除考勤记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjAttendanceRecordByIds(String ids)
	{
		return hjAttendanceRecordMapper.deleteHjAttendanceRecordByIds(Convert.toStrArray(ids));
	}

	/**
	 * 劳务工人考勤情况
	 * @param projectId 项目id
	 * @return
	 */
	@Override
	public Map<String, Object> selectWorkerAttendance(Integer projectId) {

		// 返回值
		Map<String, Object> result = new HashMap<>();

		try {
			//项目总人数
			HjProjectWorkers hjProjectWorkers = new HjProjectWorkers();
			hjProjectWorkers.setProjectId(projectId);
			hjProjectWorkers.setEmpCategory("00");
			List<HjProjectWorkers> hjProjectWorkersList = hjProjectWorkersMapper.selectHjProjectWorkersList(hjProjectWorkers);

			//在场人数
			Integer sceneNumber = hjProjectWorkersMapper.selectsCeneNumber(projectId);
			// 今日考勤人数
			Integer attendanceNumber = hjAttendanceRecordMapper.selectWorkerAttendance(projectId);

			Map<String, Object> map = new HashMap<>();
			// 项目总人数
			map.put("totalNumber",hjProjectWorkersList.size());
			// 今日现场人数
			map.put("sceneNumber",sceneNumber);
			// 今日考勤人数
			map.put("attendanceNumber",attendanceNumber);
			return AjaxResult.success(map);
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败");
		}
	}

	/**
	 * 管理人员考勤情况
	 * @param projectId 项目id
	 * @return
	 */
	@Override
	public Map<String, Object> selectAdministration(Integer projectId) {

		// 返回值
		Map<String, Object> result = new HashMap<>();

		try {
			//项目总人数
			HjProjectWorkers hjProjectWorkers = new HjProjectWorkers();
			hjProjectWorkers.setProjectId(projectId);
			Integer hjProjectWorkersList = hjProjectWorkersMapper.selectHjProjectWorkersListEmp(hjProjectWorkers);

			//管理人员进场数
			Integer sceneNumber = hjProjectWorkersMapper.selectsAdministrationNumber(projectId);

			// 今日管理人员考勤人数
			Integer attendanceNumber = hjAttendanceRecordMapper.selectAdministrationAttendance(projectId);

			Map<String, Object> map = new HashMap<>();
			// 项目总人数
			map.put("totalNumber",hjProjectWorkersList);

			// 管理人员进场数
			map.put("sceneNumber",sceneNumber);
			// 今日管理人员考勤
			map.put("attendanceNumber",attendanceNumber);
			return AjaxResult.success(map);
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败");
		}
	}


	/**
	 * 人脸考勤
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> insertAdministration(HjAttendanceRecord hjAttendanceRecord, MultipartFile file) {
		String nameUel = "";
		try {
			  String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(), "hujiang", hjAttendanceRecord.getDirection().trim()+"/");  // 文件夹名称
			  String filename= StringUtil.getRandomStringByLength(6)+new SimpleDateFormat("HHmmss")                                               //文件名称
					  .format(new Date())+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			  String fileUrl = AliyunOSSClientUtil.uploadFileImg(file, folder,filename);
			  if(!"".equals(fileUrl)) {
				  nameUel = fileUrl.substring(0,fileUrl.lastIndexOf("?"));// 文件上传

				  // 根据项目id 查询 人脸组id
				  HjProject hjProject = hjProjectMapper.selectHjProjectById(hjAttendanceRecord.getProjectId());
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				  com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray();
				  com.alibaba.fastjson.JSONObject resultObject = new com.alibaba.fastjson.JSONObject();
				  try{
					  JSONObject jsonObject = new JSONObject();
					  jsonObject.put("image",nameUel);//k考勤照片
					  jsonObject.put("image_type","URL");
					  jsonObject.put("group_id_list",hjProject.getFaceGroup());//人脸库
					  String s = Util.httpPostWithJSON("https://aip.baidubce.com/rest/2.0/face/v3/search?access_token="+ AuthService.getAuth(), jsonObject);
					  resultObject = com.alibaba.fastjson.JSONObject.parseObject(s);
					  com.alibaba.fastjson.JSONObject object1 = com.alibaba.fastjson.JSONObject.parseObject(resultObject.getString("result"));
					  jsonArray = com.alibaba.fastjson.JSONArray.parseArray(object1.getString("user_list"));//校验成功，获取返回结果
				  }catch (Exception e){
					  throw  new BusinessException("人脸识别异常：" + resultObject.toString());
				  }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

				  if (resultObject.getString("error_msg").equals("SUCCESS")) {
//					  org.json.JSONArray jsonArray = resultObject.getJSONObject("result").getJSONArray("user_list");
					  for (int i = 0; i < jsonArray.size(); i++) {
						  if (jsonArray.getJSONObject(i).getLong("score") > Constants.ATTENDANCESCORE) {                  //人脸识别数据大于设置数据，考勤成功
							  String userId = jsonArray.getJSONObject(i).getString("user_id");   //获取匹配人脸的员工
							  HjProjectWorkers hjProjectWorkers = hjProjectWorkersMapper.selectHjProjectWorkersById(Integer.parseInt(userId));
							  if (hjProjectWorkers != null) {
								  if (hjProjectWorkers.getEnterAndRetreatCondition() == 0) {

								  	  boolean re = apiClient.uploadPassedLogTest(hjProjectWorkers,hjAttendanceRecord.getDirection(),nameUel);
									  logger.info(re+"****************");
								  	  if(re == true){
										  hjAttendanceRecord.setCreateDate(new Date());  // 保存到考勤记录
										  hjAttendanceRecord.setWay(1);
										  hjAttendanceRecord.setPassedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
										  hjAttendanceRecord.setEmployeeId(Integer.parseInt(userId));
										  hjAttendanceRecord.setSitePhoto(nameUel);
										  hjAttendanceRecord.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
										  int i1 = hjAttendanceRecordMapper.insertHjAttendanceRecord(hjAttendanceRecord);
										  logger.info("考勤成功！添加考勤记录："+i1);
										  return AjaxResult.success(hjProjectWorkers.getEmpName() + "考勤成功！");
									  }else {
										  hjAttendanceRecord.setCreateDate(new Date());  // 保存到考勤记录
										  hjAttendanceRecord.setWay(1);
										  hjAttendanceRecord.setPassedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
										  hjAttendanceRecord.setEmployeeId(Integer.parseInt(userId));
										  hjAttendanceRecord.setSitePhoto(nameUel);
										  int i1 = hjAttendanceRecordMapper.insertHjAttendanceRecord(hjAttendanceRecord);
										  logger.info("考勤成功,未同步！添加考勤记录："+i1);
										  return AjaxResult.success(hjProjectWorkers.getEmpName() + "考勤成功,未同步！");
									  }
								  } else {
									  FaceMatchUtil.deleteUrl(nameUel); // 删除阿里云图片
									  return AjaxResult.error(-1, "考勤失败，该人员已退场！");
								  }
							  } else {
								  FaceMatchUtil.deleteUrl(nameUel); // 删除阿里云图片
								  return AjaxResult.error(-1, "考勤失败,该考勤人员无考勤权限！");
							  }
						  }else {
							  FaceMatchUtil.deleteUrl(nameUel); // 删除阿里云图片
							  return AjaxResult.error(-1, "考勤失败,人脸库中未查询到与该员工相似人脸！");
						  }
					  }
				  } else if (resultObject.getString("error_msg").equals("match user is not found")) {
					  FaceMatchUtil.deleteUrl(nameUel); // 删除阿里云图片
					  return AjaxResult.error(-1, "考勤失败,人脸库中未查询到与该员工相似人脸！");
				  }
			  }else {
				  FaceMatchUtil.deleteUrl(nameUel); // 删除阿里云图片
				  return AjaxResult.error(-1, "考勤失败！");
			  }
		  }catch (Exception e){
          	e.printStackTrace();
			  FaceMatchUtil.deleteUrl(nameUel); // 删除阿里云图片
			  return AjaxResult.error(-1,"考勤失败！");
		  }
          return null;
	}



@Override
	public List<Param> selectAttendanceRecordListTwo(Param param){
		return hjAttendanceRecordMapper.selectAttendanceRecordListTwo(param);
}

	@Override
	public List<HjAttendanceRecord> selectAttendanceRecordListThree(Integer userId){
		return hjAttendanceRecordMapper.selectAttendanceRecordListThree(userId);
	}
	/**
	 * 今日出勤情况
	 * @param pid
	 * @return
	 */
	@Override
	public  List<HjAttendanceRecord> selectJinRiChuQin(Integer pid){
		return  hjAttendanceRecordMapper.selectJinRiChuQin(pid);
	}
	/**
	 * 指定项目关键岗位出勤
	 * @param pid
	 * @return
	 */
	@Override
	public  List<HjAttendanceRecord> selectGJGWChuQin(Integer pid){
		return hjAttendanceRecordMapper.selectGJGWChuQin(pid);
	}
	/**
	 * 工人考勤动态
	 * @param pid
	 * @return
	 */
	@Override
	public List<DongTai> selectGRKQDongTai(Integer pid){
		return hjAttendanceRecordMapper.selectGRKQDongTai(pid);
	}
	/**
	 * 管理人员考勤动态
	 * @param pid
	 * @return
	 */
	@Override
	public  List<DongTai> selectGLKQDongTai(Integer pid){
		return hjAttendanceRecordMapper.selectGLKQDongTai(pid);
	}
	/**
	 * 根据项目id获取参建单位今日考勤人员统计
	 * @param pid
	 * @return
	 */
	@Override
	public List<TCount> getKQCount(Integer pid){
		return hjAttendanceRecordMapper.getKQCount(pid);
	}

	/**
	 * 根据项目id获取参建单位在场人员统计
	 * @param pid
	 * @return
	 */
	@Override
	public  List<TCount> getZCCount(Integer pid){
		return hjAttendanceRecordMapper.getZCCount(pid);
	}
	/**
	 * 在场实时人数
	 * @param pid
	 * @return
	 */
	@Override
	public  List<HjAttendanceRecord> selectZCSSRS( Integer pid){
		return hjAttendanceRecordMapper.selectZCSSRS(pid);
	}


	public List<HjAttendanceRecord> list(Integer projectId,String passedTime){
		return  hjAttendanceRecordMapper.list(projectId,passedTime);
	}
	public List<HjAttendanceRecord> lists(Integer projectId,String jobName){
		return  hjAttendanceRecordMapper.lists(projectId,jobName);
	}

	public List<HjAttendanceRecord> turnover(Integer projectId,String passedTime){
		return hjAttendanceRecordMapper.turnover(projectId,passedTime);
	}
	public List<HjAttendanceRecord> turnovers(Integer projectId){
		return hjAttendanceRecordMapper.turnovers(projectId);
	}
	/** 项目出勤统计*/
	public List<HjAttendanceRecord> item(Integer projectId,String passedTime){
		return hjAttendanceRecordMapper.item(projectId,passedTime);
	}
	public HjAttendanceRecord its(Integer id){
		return hjAttendanceRecordMapper.its(id);
	}
	public List<HjAttendanceRecord> itemin(Integer id,String passedTime){
		return hjAttendanceRecordMapper.itemin(id,passedTime);
	}public List<HjAttendanceRecord> itemout(Integer id,String passedTime){
	return hjAttendanceRecordMapper.itemout(id,passedTime);
}

	public List<HjAttendanceRecord> labour(Integer projectId,String passedTime,String passedTimes){
		return hjAttendanceRecordMapper.labour(projectId,passedTime,passedTimes);
	}
	public List<HjAttendanceRecord> attendance(Integer projectId){
		return hjAttendanceRecordMapper.attendance(projectId);
	}
	public List<HjAttendanceRecord> attendances(Integer projectId){
		return hjAttendanceRecordMapper.attendances(projectId);
	}

	@Override
	public List<HjAttendanceRecord> items(Integer projectId, String passedTime) {
		return hjAttendanceRecordMapper.items(projectId,passedTime);
	}
	@Override
	public List<HjAttendanceRecord> ite(Integer projectId, String passedTime) {
		return hjAttendanceRecordMapper.ite(projectId,passedTime);
	}
}
