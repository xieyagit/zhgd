package com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.service;

import com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.domain.MoredianAttendanceRecord;

import java.util.List;

/**
 * 考勤记录 服务层
 * 
 * @author hujiang
 * @date 2019-05-14
 */
public interface IMoredianAttendanceRecordService 
{
	/**
     * 查询考勤记录信息
     * 
     * @param id 考勤记录ID
     * @return 考勤记录信息
     */
	public MoredianAttendanceRecord selectMoredianAttendanceRecordById(Integer id);
	
	/**
     * 查询考勤记录列表
     * 
     * @param moredianAttendanceRecord 考勤记录信息
     * @return 考勤记录集合
     */
	public List<MoredianAttendanceRecord> selectMoredianAttendanceRecordList(MoredianAttendanceRecord moredianAttendanceRecord);
	
	/**
     * 新增考勤记录
     * 
     * @param moredianAttendanceRecord 考勤记录信息
     * @return 结果
     */
	public int insertMoredianAttendanceRecord(MoredianAttendanceRecord moredianAttendanceRecord);
	
	/**
     * 修改考勤记录
     * 
     * @param moredianAttendanceRecord 考勤记录信息
     * @return 结果
     */
	public int updateMoredianAttendanceRecord(MoredianAttendanceRecord moredianAttendanceRecord);
		
	/**
     * 删除考勤记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianAttendanceRecordByIds(String ids);
	
}
