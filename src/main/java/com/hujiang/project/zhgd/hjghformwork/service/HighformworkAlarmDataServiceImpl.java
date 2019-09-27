package com.hujiang.project.zhgd.hjghformwork.service;

import java.util.List;

import com.hujiang.project.zhgd.hjDeeppit.domain.StatisticsAlertor;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkAlarmData;
import com.hujiang.project.zhgd.hjghformwork.mapper.HighformworkAlarmDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.common.support.Convert;

/**
 * 高支模报警记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Service
public class HighformworkAlarmDataServiceImpl implements IHighformworkAlarmDataService 
{
	@Autowired
	private HighformworkAlarmDataMapper highformworkAlarmDataMapper;

	/**
     * 查询高支模报警记录信息
     * 
     * @param id 高支模报警记录ID
     * @return 高支模报警记录信息
     */
    @Override
	public HighformworkAlarmData selectHighformworkAlarmDataById(Integer id)
	{
	    return highformworkAlarmDataMapper.selectHighformworkAlarmDataById(id);
	}
	
	/**
     * 查询高支模报警记录列表
     * 
     * @param highformworkAlarmData 高支模报警记录信息
     * @return 高支模报警记录集合
     */
	@Override
	public List<HighformworkAlarmData> selectHighformworkAlarmDataList(HighformworkAlarmData highformworkAlarmData)
	{
	    return highformworkAlarmDataMapper.selectHighformworkAlarmDataList(highformworkAlarmData);
	}
	
    /**
     * 新增高支模报警记录
     * 
     * @param highformworkAlarmData 高支模报警记录信息
     * @return 结果
     */
	@Override
	public int insertHighformworkAlarmData(HighformworkAlarmData highformworkAlarmData)
	{
	    return highformworkAlarmDataMapper.insertHighformworkAlarmData(highformworkAlarmData);
	}
	
	/**
     * 修改高支模报警记录
     * 
     * @param highformworkAlarmData 高支模报警记录信息
     * @return 结果
     */
	@Override
	public int updateHighformworkAlarmData(HighformworkAlarmData highformworkAlarmData)
	{
	    return highformworkAlarmDataMapper.updateHighformworkAlarmData(highformworkAlarmData);
	}

	/**
     * 删除高支模报警记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHighformworkAlarmDataByIds(String ids)
	{
		return highformworkAlarmDataMapper.deleteHighformworkAlarmDataByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询报警等级统计
	 *
	 * @param structuresId 结构件id
	 * @return 结果
	 */
	@Override
	public List<StatisticsAlertor> statisticsAlertor(Integer structuresId){
		return highformworkAlarmDataMapper.statisticsAlertor(structuresId);
	}
	
}
