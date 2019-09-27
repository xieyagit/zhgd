package com.hujiang.project.zhgd.hjStatistics.service;

import com.hujiang.project.zhgd.hjStatistics.domain.HjStatistics;
import java.util.List;
import java.util.Map;

/**
 * 每日考勤统计 服务层
 * 
 * @author hujiang
 * @date 2019-05-30
 */
public interface IHjStatisticsService 
{
	/**
     * 查询每日考勤统计信息
     * 
     * @param id 每日考勤统计ID
     * @return 每日考勤统计信息
     */
	public HjStatistics selectHjStatisticsById(Integer id);
	
	/**
     * 查询每日考勤统计列表
     * 
     * @param hjStatistics 每日考勤统计信息
     * @return 每日考勤统计集合
     */
	public List<HjStatistics> selectHjStatisticsList(HjStatistics hjStatistics);
	
	/**
     * 新增每日考勤统计
     * 
     * @param hjStatistics 每日考勤统计信息
     * @return 结果
     */
	public int insertHjStatistics(HjStatistics hjStatistics);
	
	/**
     * 修改每日考勤统计
     * 
     * @param hjStatistics 每日考勤统计信息
     * @return 结果
     */
	public int updateHjStatistics(HjStatistics hjStatistics);
		
	/**
     * 删除每日考勤统计信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjStatisticsByIds(String ids);

	/**
	 * 查询今日管理人员在场总人数
	 */
	public Integer selectOneOne(Map<String,String> map);
	/**
	 * 查询今日管理人员考勤人数
	 */
	public Integer selectOneTwo(Map<String,String> map);

	/**
	 * 查询今日劳务工人在场总人数
	 */
	public Integer selectTwoOne(Map<String,String> map);
	/**
	 * 查询今日劳务工人考勤人数
	 */
	public Integer selectTwoTwo(Map<String,String> map);
	/**
	 * 查询今日劳务工人总人数
	 */
	public Integer selectTwoThree(Map<String,String> map);
	/**
	 * 查询今日管理人员总人数
	 */
	public Integer selectOneThree(Map<String,String> map);
	/**
	 * 查询工人安全教育培训情况
	 */
	public Integer selectFour(Map<String,String> map);
	/**
	 * 查询统计的考勤
	 */
	public List<HjStatistics> selectHjStatisticsListTwo(Map<String,String> map);
}
