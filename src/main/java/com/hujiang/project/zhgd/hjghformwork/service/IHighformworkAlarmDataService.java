package com.hujiang.project.zhgd.hjghformwork.service;


import com.hujiang.project.zhgd.hjDeeppit.domain.StatisticsAlertor;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkAlarmData;

import java.util.List;

/**
 * 高支模报警记录 服务层
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public interface IHighformworkAlarmDataService 
{
	/**
     * 查询高支模报警记录信息
     * 
     * @param id 高支模报警记录ID
     * @return 高支模报警记录信息
     */
	public HighformworkAlarmData selectHighformworkAlarmDataById(Integer id);
	
	/**
     * 查询高支模报警记录列表
     * 
     * @param highformworkAlarmData 高支模报警记录信息
     * @return 高支模报警记录集合
     */
	public List<HighformworkAlarmData> selectHighformworkAlarmDataList(HighformworkAlarmData highformworkAlarmData);
	
	/**
     * 新增高支模报警记录
     * 
     * @param highformworkAlarmData 高支模报警记录信息
     * @return 结果
     */
	public int insertHighformworkAlarmData(HighformworkAlarmData highformworkAlarmData);
	
	/**
     * 修改高支模报警记录
     * 
     * @param highformworkAlarmData 高支模报警记录信息
     * @return 结果
     */
	public int updateHighformworkAlarmData(HighformworkAlarmData highformworkAlarmData);
		
	/**
     * 删除高支模报警记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHighformworkAlarmDataByIds(String ids);


	/**
	 * 查询报警等级统计
	 *
	 * @param structuresId 结构件id
	 * @return 结果
	 */
	public List<StatisticsAlertor> statisticsAlertor(Integer structuresId);


	
}
