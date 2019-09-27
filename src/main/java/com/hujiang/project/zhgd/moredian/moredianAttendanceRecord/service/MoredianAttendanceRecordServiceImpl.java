package com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.domain.MoredianAttendanceRecord;
import com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.mapper.MoredianAttendanceRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考勤记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-14
 */
@Service
@Transactional
public class MoredianAttendanceRecordServiceImpl implements IMoredianAttendanceRecordService 
{
	@Autowired
	private MoredianAttendanceRecordMapper moredianAttendanceRecordMapper;

	/**
     * 查询考勤记录信息
     * 
     * @param id 考勤记录ID
     * @return 考勤记录信息
     */
    @Override
	public MoredianAttendanceRecord selectMoredianAttendanceRecordById(Integer id)
	{
	    return moredianAttendanceRecordMapper.selectMoredianAttendanceRecordById(id);
	}
	
	/**
     * 查询考勤记录列表
     * 
     * @param moredianAttendanceRecord 考勤记录信息
     * @return 考勤记录集合
     */
	@Override
	public List<MoredianAttendanceRecord> selectMoredianAttendanceRecordList(MoredianAttendanceRecord moredianAttendanceRecord)
	{
	    return moredianAttendanceRecordMapper.selectMoredianAttendanceRecordList(moredianAttendanceRecord);
	}
	
    /**
     * 新增考勤记录
     * 
     * @param moredianAttendanceRecord 考勤记录信息
     * @return 结果
     */
	@Override
	public int insertMoredianAttendanceRecord(MoredianAttendanceRecord moredianAttendanceRecord)
	{
	    return moredianAttendanceRecordMapper.insertMoredianAttendanceRecord(moredianAttendanceRecord);
	}
	
	/**
     * 修改考勤记录
     * 
     * @param moredianAttendanceRecord 考勤记录信息
     * @return 结果
     */
	@Override
	public int updateMoredianAttendanceRecord(MoredianAttendanceRecord moredianAttendanceRecord)
	{
	    return moredianAttendanceRecordMapper.updateMoredianAttendanceRecord(moredianAttendanceRecord);
	}

	/**
     * 删除考勤记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianAttendanceRecordByIds(String ids)
	{
		return moredianAttendanceRecordMapper.deleteMoredianAttendanceRecordByIds(Convert.toStrArray(ids));
	}
	
}
