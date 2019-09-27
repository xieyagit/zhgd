package com.hujiang.project.zhgd.hjDeeppit.mapper;

import com.hujiang.project.zhgd.hjDeeppit.domain.DeeppitAlarmData;
import com.hujiang.project.zhgd.hjDeeppit.domain.StatisticsAlertor;

import java.util.List;

/**
 * 深基坑报警记录 数据层
 * 
 * @author hujiang
 * @date 2019-09-06
 */
public interface DeeppitAlarmDataMapper 
{
	/**
     * 查询深基坑报警记录信息
     * 
     * @param id 深基坑报警记录ID
     * @return 深基坑报警记录信息
     */
	public DeeppitAlarmData selectDeeppitAlarmDataById(String id);
	
	/**
     * 查询深基坑报警记录列表
     * 
     * @param deeppitAlarmData 深基坑报警记录信息
     * @return 深基坑报警记录集合
     */
	public List<DeeppitAlarmData> selectDeeppitAlarmDataList(DeeppitAlarmData deeppitAlarmData);
	
	/**
     * 新增深基坑报警记录
     * 
     * @param deeppitAlarmData 深基坑报警记录信息
     * @return 结果
     */
	public int insertDeeppitAlarmData(DeeppitAlarmData deeppitAlarmData);
	
	/**
     * 修改深基坑报警记录
     * 
     * @param deeppitAlarmData 深基坑报警记录信息
     * @return 结果
     */
	public int updateDeeppitAlarmData(DeeppitAlarmData deeppitAlarmData);
	
	/**
     * 删除深基坑报警记录
     * 
     * @param id 深基坑报警记录ID
     * @return 结果
     */
	public int deleteDeeppitAlarmDataById(String id);
	
	/**
     * 批量删除深基坑报警记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDeeppitAlarmDataByIds(String[] ids);

	/**
	 * 查询深基坑报警记录列表
	 *
	 * @param structuresId 结构件id
	 * @return 各级统计列表
	 */
	public List<StatisticsAlertor> statisticsAlertor(Integer structuresId);
	
}