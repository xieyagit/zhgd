package com.hujiang.project.zhgd.lyAttendanceRecord.service;

import java.util.List;

import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecordPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.lyAttendanceRecord.mapper.LyAttendanceRecordMapper;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.service.ILyAttendanceRecordService;
import com.hujiang.common.support.Convert;

/**
 * 考勤记录 服务层实现
 * 
 * @author hujiang
 * @date 2020-03-08
 */
@Service
public class LyAttendanceRecordServiceImpl implements ILyAttendanceRecordService 
{
	@Autowired
	private LyAttendanceRecordMapper lyAttendanceRecordMapper;

	/**
     * 查询考勤记录信息
     * 
     * @param id 考勤记录ID
     * @return 考勤记录信息
     */
    @Override
	public LyAttendanceRecord selectLyAttendanceRecordById(Integer id)
	{
	    return lyAttendanceRecordMapper.selectLyAttendanceRecordById(id);
	}
	
	/**
     * 查询考勤记录列表
     * 
     * @param lyAttendanceRecord 考勤记录信息
     * @return 考勤记录集合
     */
	@Override
	public List<LyAttendanceRecord> selectLyAttendanceRecordList(LyAttendanceRecord lyAttendanceRecord)
	{
	    return lyAttendanceRecordMapper.selectLyAttendanceRecordList(lyAttendanceRecord);
	}
	@Override
	public List<LyAttendanceRecord> selectLyAttendanceRecordListTwo(LyAttendanceRecord lyAttendanceRecord)
	{
	    return lyAttendanceRecordMapper.selectLyAttendanceRecordListTwo(lyAttendanceRecord);
	}
	
    /**
     * 新增考勤记录
     * 
     * @param lyAttendanceRecord 考勤记录信息
     * @return 结果
     */
	@Override
	public int insertLyAttendanceRecord(LyAttendanceRecord lyAttendanceRecord)
	{
	    return lyAttendanceRecordMapper.insertLyAttendanceRecord(lyAttendanceRecord);
	}
	
	/**
     * 修改考勤记录
     * 
     * @param lyAttendanceRecord 考勤记录信息
     * @return 结果
     */
	@Override
	public int updateLyAttendanceRecord(LyAttendanceRecord lyAttendanceRecord)
	{
	    return lyAttendanceRecordMapper.updateLyAttendanceRecord(lyAttendanceRecord);
	}

	/**
     * 删除考勤记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLyAttendanceRecordByIds(String ids)
	{
		return lyAttendanceRecordMapper.deleteLyAttendanceRecordByIds(Convert.toStrArray(ids));
	}

	//pc通行记录
	@Override
	public List<LyAttendanceRecordPersonnel> selectPersonnelRecordPageList(LyAttendanceRecordPersonnel lyAttendanceRecordPersonnel){
		return lyAttendanceRecordMapper.selectPersonnelRecordPageList(lyAttendanceRecordPersonnel);
	}
}
