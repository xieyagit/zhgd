package com.hujiang.project.zhgd.hjDeviceProjectworkers.service;

import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkersParam;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;

import java.util.List;
import java.util.Map;

/**
 * 考勤设备人员 服务层
 * 
 * @author hujiang
 * @date 2019-08-08
 */
public interface IHjDeviceProjectworkersService 
{
	/**
     * 查询考勤设备人员信息
     * 
     * @param id 考勤设备人员ID
     * @return 考勤设备人员信息
     */
	public HjDeviceProjectworkers selectHjDeviceProjectworkersById(Integer id);
	
	/**
     * 查询考勤设备人员列表
     * 
     * @param hjDeviceProjectworkers 考勤设备人员信息
     * @return 考勤设备人员集合
     */
	public List<HjDeviceProjectworkers> selectHjDeviceProjectworkersList(HjDeviceProjectworkers hjDeviceProjectworkers);
	//退场向人脸机删除人脸时验证指令的专用方法
		public List<HjDeviceProjectworkers> selectHjDeviceProjectworkersListTwo(HjDeviceProjectworkers hjDeviceProjectworkers);

	/**
     * 新增考勤设备人员
     * 
     * @param hjDeviceProjectworkers 考勤设备人员信息
     * @return 结果
     */
	public int insertHjDeviceProjectworkers(HjDeviceProjectworkers hjDeviceProjectworkers);
	
	/**
     * 修改考勤设备人员
     * 
     * @param hjDeviceProjectworkers 考勤设备人员信息
     * @return 结果
     */
	public int updateHjDeviceProjectworkers(HjDeviceProjectworkers hjDeviceProjectworkers);
	public int updateHjDeviceProjectworkersTwo(HjDeviceProjectworkers hjDeviceProjectworkers);

	/**
     * 删除考勤设备人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjDeviceProjectworkersByIds(String ids);

	public List<HjProjectWorkers> selectHjProjectworkersList(Map<String,String> param);
	public List<HjDeviceProjectworkersParam> selectHjProjectworkersListTwo(Map<String,String> param);
	public int deleteIds(Map<String,String> param);
	public int deleteHjDeviceProjectworkersTwo(HjDeviceProjectworkers hjDeviceProjectworkers);
	public List<HjDeviceProjectworkersParam> selectHjProjectworkersListThree(Map<String,String> param);
}
