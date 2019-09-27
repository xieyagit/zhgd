package com.hujiang.project.zhgd.hjDeeppit.service;

import java.util.List;

import com.hujiang.project.zhgd.hjDeeppit.domain.StatisticsAlertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.hjDeeppit.mapper.DeeppitAlarmDataMapper;
import com.hujiang.project.zhgd.hjDeeppit.domain.DeeppitAlarmData;
import com.hujiang.project.zhgd.hjDeeppit.service.IDeeppitAlarmDataService;
import com.hujiang.common.support.Convert;

/**
 * 深基坑报警记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-06
 */
@Service
public class DeeppitAlarmDataServiceImpl implements IDeeppitAlarmDataService 
{
	@Autowired
	private DeeppitAlarmDataMapper deeppitAlarmDataMapper;

	/**
     * 查询深基坑报警记录信息
     * 
     * @param id 深基坑报警记录ID
     * @return 深基坑报警记录信息
     */
    @Override
	public DeeppitAlarmData selectDeeppitAlarmDataById(String id)
	{
	    return deeppitAlarmDataMapper.selectDeeppitAlarmDataById(id);
	}
	
	/**
     * 查询深基坑报警记录列表
     * 
     * @param deeppitAlarmData 深基坑报警记录信息
     * @return 深基坑报警记录集合
     */
	@Override
	public List<DeeppitAlarmData> selectDeeppitAlarmDataList(DeeppitAlarmData deeppitAlarmData)
	{
	    return deeppitAlarmDataMapper.selectDeeppitAlarmDataList(deeppitAlarmData);
	}
	
    /**
     * 新增深基坑报警记录
     * 
     * @param deeppitAlarmData 深基坑报警记录信息
     * @return 结果
     */
	@Override
	public int insertDeeppitAlarmData(DeeppitAlarmData deeppitAlarmData)
	{
	    return deeppitAlarmDataMapper.insertDeeppitAlarmData(deeppitAlarmData);
	}
	
	/**
     * 修改深基坑报警记录
     * 
     * @param deeppitAlarmData 深基坑报警记录信息
     * @return 结果
     */
	@Override
	public int updateDeeppitAlarmData(DeeppitAlarmData deeppitAlarmData)
	{
	    return deeppitAlarmDataMapper.updateDeeppitAlarmData(deeppitAlarmData);
	}

	/**
     * 删除深基坑报警记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDeeppitAlarmDataByIds(String ids)
	{
		return deeppitAlarmDataMapper.deleteDeeppitAlarmDataByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询报警等级统计
	 *
	 * @param structuresId 结构件id
	 * @return 结果
	 */
	@Override
	public List<StatisticsAlertor> statisticsAlertor(Integer structuresId){
		return deeppitAlarmDataMapper.statisticsAlertor(structuresId);
	}
	
}
