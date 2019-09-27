package com.hujiang.project.zhgd.sbApiFaceAttendance.service;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbApiFaceAttendance.domain.SbApiFaceAttendance;
import java.util.List;

/**
 * 人脸摄像机考勤记录 服务层
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public interface ISbApiFaceAttendanceService 
{
	/**
     * 查询人脸摄像机考勤记录信息
     * 
     * @param id 人脸摄像机考勤记录ID
     * @return 人脸摄像机考勤记录信息
     */
	public SbApiFaceAttendance selectSbApiFaceAttendanceById(Integer id);
	
	/**
     * 查询人脸摄像机考勤记录列表
     * 
     * @param sbApiFaceAttendance 人脸摄像机考勤记录信息
     * @return 人脸摄像机考勤记录集合
     */
	public List<SbApiFaceAttendance> selectSbApiFaceAttendanceList(SbApiFaceAttendance sbApiFaceAttendance);
	
	/**
     * 新增人脸摄像机考勤记录
     * 
     * @param object 人脸摄像机考勤记录信息
     * @return 结果
     */
	JSONObject insertSbApiFaceAttendanceResult(SbApiFaceAttendance sbApiFaceAttendance);
	/**
	 * 新增人脸摄像机考勤记录
	 *
	 * @param sbApiFaceAttendance 人脸摄像机考勤记录信息
	 * @return 结果
	 */
	int insertSbApiFaceAttendance(SbApiFaceAttendance sbApiFaceAttendance);
	/**
     * 修改人脸摄像机考勤记录
     * 
     * @param sbApiFaceAttendance 人脸摄像机考勤记录信息
     * @return 结果
     */
	public int updateSbApiFaceAttendance(SbApiFaceAttendance sbApiFaceAttendance);
		
	/**
     * 删除人脸摄像机考勤记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbApiFaceAttendanceByIds(String ids);
	
}
