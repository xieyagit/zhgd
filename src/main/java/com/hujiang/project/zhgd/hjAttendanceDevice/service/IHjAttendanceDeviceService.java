package com.hujiang.project.zhgd.hjAttendanceDevice.service;

import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import java.util.List;

/**
 * 考勤机设备 服务层
 * 
 * @author hujiang
 * @date 2019-08-06
 */
public interface IHjAttendanceDeviceService 
{
	/**
     * 查询考勤机设备信息
     * 
     * @param id 考勤机设备ID
     * @return 考勤机设备信息
     */
	public HjAttendanceDevice selectHjAttendanceDeviceById(Integer id);
	
	/**
     * 查询考勤机设备列表
     * 
     * @param hjAttendanceDevice 考勤机设备信息
     * @return 考勤机设备集合
     */
	public List<HjAttendanceDevice> selectHjAttendanceDeviceList(HjAttendanceDevice hjAttendanceDevice);
	
	/**
     * 新增考勤机设备
     * 
     * @param hjAttendanceDevice 考勤机设备信息
     * @return 结果
     */
	public int insertHjAttendanceDevice(HjAttendanceDevice hjAttendanceDevice);
	
	/**
     * 修改考勤机设备
     * 
     * @param hjAttendanceDevice 考勤机设备信息
     * @return 结果
     */
	public int updateHjAttendanceDevice(HjAttendanceDevice hjAttendanceDevice);
	/**
	 * 修改考勤机最后连接时间
	 *
	 * @param hjAttendanceDevice 考勤机设备信息
	 * @return 结果
	 */
	public int updateHjAttendanceDeviceTwo(HjAttendanceDevice hjAttendanceDevice);

	/**
     * 删除考勤机设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjAttendanceDeviceByIds(String ids);
	
}
