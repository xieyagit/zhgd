package com.hujiang.project.zhgd.lyStatistics.mapper;

import com.hujiang.project.zhgd.lyStatistics.domain.LyStatistics;
import java.util.List;	

/**
 * 楼宇人员进出每日统计 数据层
 * 
 * @author hujiang
 * @date 2020-03-13
 */
public interface LyStatisticsMapper 
{
	/**
     * 查询楼宇人员进出每日统计信息
     * 
     * @param id 楼宇人员进出每日统计ID
     * @return 楼宇人员进出每日统计信息
     */
	public LyStatistics selectLyStatisticsById(Integer id);
	
	/**
     * 查询楼宇人员进出每日统计列表
     * 
     * @param lyStatistics 楼宇人员进出每日统计信息
     * @return 楼宇人员进出每日统计集合
     */
	public List<LyStatistics> selectLyStatisticsList(LyStatistics lyStatistics);
	public List<LyStatistics> selectLyTimeCount(LyStatistics lyStatistics);

	/**
     * 新增楼宇人员进出每日统计
     * 
     * @param lyStatistics 楼宇人员进出每日统计信息
     * @return 结果
     */
	public int insertLyStatistics(LyStatistics lyStatistics);
	
	/**
     * 修改楼宇人员进出每日统计
     * 
     * @param lyStatistics 楼宇人员进出每日统计信息
     * @return 结果
     */
	public int updateLyStatistics(LyStatistics lyStatistics);
	
	/**
     * 删除楼宇人员进出每日统计
     * 
     * @param id 楼宇人员进出每日统计ID
     * @return 结果
     */
	public int deleteLyStatisticsById(Integer id);
	
	/**
     * 批量删除楼宇人员进出每日统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteLyStatisticsByIds(String[] ids);
	
}