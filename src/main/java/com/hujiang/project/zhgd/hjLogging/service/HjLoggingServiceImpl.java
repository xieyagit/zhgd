package com.hujiang.project.zhgd.hjLogging.service;

import java.util.List;
import java.util.Map;

import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjLogging.mapper.HjLoggingMapper;
import com.hujiang.common.support.Convert;

/**
 * 考勤，实名制日志 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-14
 */
@Service@Transactional
public class HjLoggingServiceImpl implements IHjLoggingService 
{
	@Autowired
	private HjLoggingMapper hjLoggingMapper;

	/**
     * 查询考勤，实名制日志信息
     * 
     * @param id 考勤，实名制日志ID
     * @return 考勤，实名制日志信息
     */
    @Override
	public HjLogging selectHjLoggingById(Integer id)
	{
	    return hjLoggingMapper.selectHjLoggingById(id);
	}

	public List<HjLogging> selectHjLoggingListNew(Map<String,Object> map){
    	return hjLoggingMapper.selectHjLoggingListNew(map);
	}
	/**
     * 查询考勤，实名制日志列表
     * 
     * @param hjLogging 考勤，实名制日志信息
     * @return 考勤，实名制日志集合
     */
	@Override
	public List<HjLogging> selectHjLoggingList(HjLogging hjLogging)
	{
	    return hjLoggingMapper.selectHjLoggingList(hjLogging);
	}
	
    /**
     * 新增考勤，实名制日志
     * 
     * @param hjLogging 考勤，实名制日志信息
     * @return 结果
     */
	@Override
	public int insertHjLogging(HjLogging hjLogging)
	{
	    return hjLoggingMapper.insertHjLogging(hjLogging);
	}
	
	/**
     * 修改考勤，实名制日志
     * 
     * @param hjLogging 考勤，实名制日志信息
     * @return 结果
     */
	@Override
	public int updateHjLogging(HjLogging hjLogging)
	{
	    return hjLoggingMapper.updateHjLogging(hjLogging);
	}

	/**
     * 删除考勤，实名制日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjLoggingByIds(String ids)
	{
		return hjLoggingMapper.deleteHjLoggingByIds(Convert.toStrArray(ids));
	}
	
}
