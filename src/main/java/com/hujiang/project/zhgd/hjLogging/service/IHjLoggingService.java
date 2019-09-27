package com.hujiang.project.zhgd.hjLogging.service;

import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;

import java.util.List;
import java.util.Map;

/**
 * 考勤，实名制日志 服务层
 * 
 * @author hujiang
 * @date 2019-06-14
 */
public interface IHjLoggingService 
{
	/**
     * 查询考勤，实名制日志信息
     * 
     * @param id 考勤，实名制日志ID
     * @return 考勤，实名制日志信息
     */
	public HjLogging selectHjLoggingById(Integer id);
	
	/**
     * 查询考勤，实名制日志列表
     * 
     * @param hjLogging 考勤，实名制日志信息
     * @return 考勤，实名制日志集合
     */
	public List<HjLogging> selectHjLoggingList(HjLogging hjLogging);
	public List<HjLogging> selectHjLoggingListNew(Map<String,Object> map);
	/**
     * 新增考勤，实名制日志
     * 
     * @param hjLogging 考勤，实名制日志信息
     * @return 结果
     */
	public int insertHjLogging(HjLogging hjLogging);
	
	/**
     * 修改考勤，实名制日志
     * 
     * @param hjLogging 考勤，实名制日志信息
     * @return 结果
     */
	public int updateHjLogging(HjLogging hjLogging);
		
	/**
     * 删除考勤，实名制日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjLoggingByIds(String ids);
	
}
