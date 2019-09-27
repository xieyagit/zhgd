package com.hujiang.project.zhgd.hjStatistics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjStatistics.mapper.HjStatisticsMapper;
import com.hujiang.project.zhgd.hjStatistics.domain.HjStatistics;
import com.hujiang.project.zhgd.hjStatistics.service.IHjStatisticsService;
import com.hujiang.common.support.Convert;

/**
 * 每日考勤统计 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-30
 */
@Service
@Transactional
public class HjStatisticsServiceImpl implements IHjStatisticsService 
{
	@Autowired
	private HjStatisticsMapper hjStatisticsMapper;

	/**
     * 查询每日考勤统计信息
     * 
     * @param id 每日考勤统计ID
     * @return 每日考勤统计信息
     */
    @Override
	public HjStatistics selectHjStatisticsById(Integer id)
	{
	    return hjStatisticsMapper.selectHjStatisticsById(id);
	}
	
	/**
     * 查询每日考勤统计列表
     * 
     * @param hjStatistics 每日考勤统计信息
     * @return 每日考勤统计集合
     */
	@Override
	public List<HjStatistics> selectHjStatisticsList(HjStatistics hjStatistics)
	{
	    return hjStatisticsMapper.selectHjStatisticsList(hjStatistics);
	}
	
    /**
     * 新增每日考勤统计
     * 
     * @param hjStatistics 每日考勤统计信息
     * @return 结果
     */
	@Override
	public int insertHjStatistics(HjStatistics hjStatistics)
	{
	    return hjStatisticsMapper.insertHjStatistics(hjStatistics);
	}
	
	/**
     * 修改每日考勤统计
     * 
     * @param hjStatistics 每日考勤统计信息
     * @return 结果
     */
	@Override
	public int updateHjStatistics(HjStatistics hjStatistics)
	{
	    return hjStatisticsMapper.updateHjStatistics(hjStatistics);
	}

	/**
     * 删除每日考勤统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjStatisticsByIds(String ids)
	{
		return hjStatisticsMapper.deleteHjStatisticsByIds(Convert.toStrArray(ids));
	}
	/**
	 * 查询今日管理人员在场总人数
	 */
	public Integer selectOneOne(Map<String,String> map){
		return hjStatisticsMapper.selectOneOne(map);
	}
	/**
	 * 查询今日管理人员考勤人数
	 */
	public Integer selectOneTwo(Map<String,String> map){
		return hjStatisticsMapper.selectOneTwo(map);
	}
	/**
	 * 查询今日劳务工人在场总人数
	 */
	public Integer selectTwoOne(Map<String,String> map){
		return hjStatisticsMapper.selectTwoOne(map);
	}
	/**
	 * 查询今日劳务工人考勤人数
	 */
	public Integer selectTwoTwo(Map<String,String> map){
		return hjStatisticsMapper.selectTwoTwo(map);
	}
	/**
	 * 查询今日劳务工人总人数
	 */
	public Integer selectTwoThree(Map<String,String> map){
		return hjStatisticsMapper.selectTwoThree(map);
	}
	/**
	 * 查询今日管理人员总人数
	 */
	public Integer selectOneThree(Map<String,String> map){
		return hjStatisticsMapper.selectOneThree(map);
	}
	/**
	 * 查询工人安全教育培训情况
	 */
	public Integer selectFour(Map<String,String> map){
		return hjStatisticsMapper.selectFour(map);
	}
	/**
	 * 查询统计的考勤
	 */
	public List<HjStatistics> selectHjStatisticsListTwo(Map<String,String> map){
		return hjStatisticsMapper.selectHjStatisticsListTwo(map);
	}
}
