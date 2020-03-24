package com.hujiang.project.zhgd.lyStatistics.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.lyStatistics.mapper.LyStatisticsMapper;
import com.hujiang.project.zhgd.lyStatistics.domain.LyStatistics;
import com.hujiang.project.zhgd.lyStatistics.service.ILyStatisticsService;
import com.hujiang.common.support.Convert;

/**
 * 楼宇人员进出每日统计 服务层实现
 * 
 * @author hujiang
 * @date 2020-03-13
 */
@Service
public class LyStatisticsServiceImpl implements ILyStatisticsService 
{
	@Autowired
	private LyStatisticsMapper lyStatisticsMapper;

	/**
     * 查询楼宇人员进出每日统计信息
     * 
     * @param id 楼宇人员进出每日统计ID
     * @return 楼宇人员进出每日统计信息
     */
    @Override
	public LyStatistics selectLyStatisticsById(Integer id)
	{
	    return lyStatisticsMapper.selectLyStatisticsById(id);
	}
	
	/**
     * 查询楼宇人员进出每日统计列表
     * 
     * @param lyStatistics 楼宇人员进出每日统计信息
     * @return 楼宇人员进出每日统计集合
     */
	@Override
	public List<LyStatistics> selectLyStatisticsList(LyStatistics lyStatistics)
	{
	    return lyStatisticsMapper.selectLyStatisticsList(lyStatistics);
	}
	@Override
	public List<LyStatistics> selectLyTimeCount(LyStatistics lyStatistics)
	{
	    return lyStatisticsMapper.selectLyTimeCount(lyStatistics);
	}
	
    /**
     * 新增楼宇人员进出每日统计
     * 
     * @param lyStatistics 楼宇人员进出每日统计信息
     * @return 结果
     */
	@Override
	public int insertLyStatistics(LyStatistics lyStatistics)
	{
	    return lyStatisticsMapper.insertLyStatistics(lyStatistics);
	}
	
	/**
     * 修改楼宇人员进出每日统计
     * 
     * @param lyStatistics 楼宇人员进出每日统计信息
     * @return 结果
     */
	@Override
	public int updateLyStatistics(LyStatistics lyStatistics)
	{
	    return lyStatisticsMapper.updateLyStatistics(lyStatistics);
	}

	/**
     * 删除楼宇人员进出每日统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLyStatisticsByIds(String ids)
	{
		return lyStatisticsMapper.deleteLyStatisticsByIds(Convert.toStrArray(ids));
	}
	
}
