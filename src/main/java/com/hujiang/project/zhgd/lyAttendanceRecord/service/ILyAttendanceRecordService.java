package com.hujiang.project.zhgd.lyAttendanceRecord.service;

import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecordPersonnel;

import java.util.List;

/**
 * 考勤记录 服务层
 * 
 * @author hujiang
 * @date 2020-03-08
 */
public interface ILyAttendanceRecordService 
{
	/**
     * 查询考勤记录信息
     * 
     * @param id 考勤记录ID
     * @return 考勤记录信息
     */
	public LyAttendanceRecord selectLyAttendanceRecordById(Integer id);
	
	/**
     * 查询考勤记录列表
     * 
     * @param lyAttendanceRecord 考勤记录信息
     * @return 考勤记录集合
     */
	public List<LyAttendanceRecord> selectLyAttendanceRecordList(LyAttendanceRecord lyAttendanceRecord);
	public List<LyAttendanceRecord> selectLyAttendanceRecordListTwo(LyAttendanceRecord lyAttendanceRecord);

	/**
     * 新增考勤记录
     * 
     * @param lyAttendanceRecord 考勤记录信息
     * @return 结果
     */
	public int insertLyAttendanceRecord(LyAttendanceRecord lyAttendanceRecord);
	
	/**
     * 修改考勤记录
     * 
     * @param lyAttendanceRecord 考勤记录信息
     * @return 结果
     */
	public int updateLyAttendanceRecord(LyAttendanceRecord lyAttendanceRecord);
		
	/**
     * 删除考勤记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyAttendanceRecordByIds(String ids);

	public List<LyAttendanceRecordPersonnel> selectPersonnelRecordPageList(LyAttendanceRecordPersonnel lyAttendanceRecordPersonnel);
}
