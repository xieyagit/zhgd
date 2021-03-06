package com.hujiang.project.zhgd.lyAttendanceRecord.mapper;

import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecordPersonnel;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyRecordExport;

import java.util.List;
import java.util.Map;

/**
 * 考勤记录 数据层
 * 
 * @author hujiang
 * @date 2020-03-08
 */
public interface LyAttendanceRecordMapper 
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
     * 删除考勤记录
     * 
     * @param id 考勤记录ID
     * @return 结果
     */
	public int deleteLyAttendanceRecordById(Integer id);
	
	/**
     * 批量删除考勤记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyAttendanceRecordByIds(String[] ids);

	public List<LyAttendanceRecordPersonnel> selectPersonnelRecordPageList(LyAttendanceRecordPersonnel lyAttendanceRecordPersonnel);
	public List<LyRecordExport> selectRecordExport(LyRecordExport lyRecordExport);

	public int getRecordCount(Map<String,String> map);
}