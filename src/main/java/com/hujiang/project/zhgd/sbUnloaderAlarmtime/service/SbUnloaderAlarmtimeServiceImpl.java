package com.hujiang.project.zhgd.sbUnloaderAlarmtime.service;

import java.util.List;

import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.ExportUnloaderAlarmtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.mapper.SbUnloaderAlarmtimeMapper;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain.SbUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.service.ISbUnloaderAlarmtimeService;
import com.hujiang.common.support.Convert;

/**
 * 卸料报警时刻数据 服务层实现
 *
 * @author hujiang
 * @date 2019-09-11
 */
@Service
public class SbUnloaderAlarmtimeServiceImpl implements ISbUnloaderAlarmtimeService
{
	@Autowired
	private SbUnloaderAlarmtimeMapper sbUnloaderAlarmtimeMapper;

	@Override
	public List<SbUnloaderAlarmtime> getSbUnloaderAlarmtimeList(SbUnloaderAlarmtime sbUnloaderAlarmtime) {
		return sbUnloaderAlarmtimeMapper.getSbUnloaderAlarmtimeList(sbUnloaderAlarmtime);
	}

	@Override
	public int count(Integer projectId,String hxzId,Integer alarmType,String startTime) {
		return sbUnloaderAlarmtimeMapper.count(projectId,hxzId,alarmType,startTime);
	}

	@Override
	public List<ExportUnloaderAlarmtime> getSbUnloaderAlarmtimeListById(String[] ids,String startTime,String endTime,String deviceId,Integer alarmType) {
		return sbUnloaderAlarmtimeMapper.getSbUnloaderAlarmtimeListById(ids,startTime,endTime,deviceId,alarmType);
	}

	/**
	 * 查询卸料报警时刻数据信息
	 *
	 * @param id 卸料报警时刻数据ID
	 * @return 卸料报警时刻数据信息
	 */
	@Override
	public SbUnloaderAlarmtime selectSbUnloaderAlarmtimeById(Integer id)
	{
		return sbUnloaderAlarmtimeMapper.selectSbUnloaderAlarmtimeById(id);
	}

	/**
	 * 查询卸料报警时刻数据列表
	 *
	 * @param sbUnloaderAlarmtime 卸料报警时刻数据信息
	 * @return 卸料报警时刻数据集合
	 */
	@Override
	public List<SbUnloaderAlarmtime> selectSbUnloaderAlarmtimeList(SbUnloaderAlarmtime sbUnloaderAlarmtime)
	{
		return sbUnloaderAlarmtimeMapper.selectSbUnloaderAlarmtimeList(sbUnloaderAlarmtime);
	}

	/**
	 * 新增卸料报警时刻数据
	 *
	 * @param sbUnloaderAlarmtime 卸料报警时刻数据信息
	 * @return 结果
	 */
	@Override
	public int insertSbUnloaderAlarmtime(SbUnloaderAlarmtime sbUnloaderAlarmtime)
	{
		return sbUnloaderAlarmtimeMapper.insertSbUnloaderAlarmtime(sbUnloaderAlarmtime);
	}

	/**
	 * 修改卸料报警时刻数据
	 *
	 * @param sbUnloaderAlarmtime 卸料报警时刻数据信息
	 * @return 结果
	 */
	@Override
	public int updateSbUnloaderAlarmtime(SbUnloaderAlarmtime sbUnloaderAlarmtime)
	{
		return sbUnloaderAlarmtimeMapper.updateSbUnloaderAlarmtime(sbUnloaderAlarmtime);
	}

	/**
	 * 删除卸料报警时刻数据对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteSbUnloaderAlarmtimeByIds(String ids)
	{
		return sbUnloaderAlarmtimeMapper.deleteSbUnloaderAlarmtimeByIds(Convert.toStrArray(ids));
	}

}
