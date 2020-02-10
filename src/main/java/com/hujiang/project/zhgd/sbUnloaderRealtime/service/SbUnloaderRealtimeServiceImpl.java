package com.hujiang.project.zhgd.sbUnloaderRealtime.service;

import java.util.List;

import com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain.SbUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.ExportUnloaderRealtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.sbUnloaderRealtime.mapper.SbUnloaderRealtimeMapper;
import com.hujiang.project.zhgd.sbUnloaderRealtime.domain.SbUnloaderRealtime;
import com.hujiang.project.zhgd.sbUnloaderRealtime.service.ISbUnloaderRealtimeService;
import com.hujiang.common.support.Convert;

/**
 * 卸料实时数据 服务层实现
 *
 * @author hujiang
 * @date 2019-09-11
 */
@Service
public class SbUnloaderRealtimeServiceImpl implements ISbUnloaderRealtimeService
{
	@Autowired
	private SbUnloaderRealtimeMapper sbUnloaderRealtimeMapper;


	@Override
	public List<ExportUnloaderRealtime> getSbUnloaderRealtimeListByIds(String[] ids,String startTime,String endTime,String deviceId) {
		return sbUnloaderRealtimeMapper.getSbUnloaderRealtimeListByIds(ids,startTime,endTime,deviceId);
	}

	@Override
	public SbUnloaderRealtime getSbUnloaderRealtimeList(SbUnloaderRealtime sbUnloaderRealtime) {
		return sbUnloaderRealtimeMapper.getSbUnloaderRealtimeList(sbUnloaderRealtime);
	}

	@Override
	public List<SbUnloaderRealtime> getSbUnloaderHistory(SbUnloaderRealtime sbUnloaderRealtime) {
		return sbUnloaderRealtimeMapper.getSbUnloaderHistory(sbUnloaderRealtime);
	}

	/**
	 * 查询卸料实时数据信息
	 *
	 * @param id 卸料实时数据ID
	 * @return 卸料实时数据信息
	 */
	@Override
	public SbUnloaderRealtime selectSbUnloaderRealtimeById(Integer id)
	{
		return sbUnloaderRealtimeMapper.selectSbUnloaderRealtimeById(id);
	}

	/**
	 * 查询卸料实时数据列表
	 *
	 * @param sbUnloaderRealtime 卸料实时数据信息
	 * @return 卸料实时数据集合
	 */
	@Override
	public List<SbUnloaderRealtime> selectSbUnloaderRealtimeList(SbUnloaderRealtime sbUnloaderRealtime)
	{
		return sbUnloaderRealtimeMapper.selectSbUnloaderRealtimeList(sbUnloaderRealtime);
	}

	/**
	 * 新增卸料实时数据
	 *
	 * @param sbUnloaderRealtime 卸料实时数据信息
	 * @return 结果
	 */
	@Override
	public int insertSbUnloaderRealtime(SbUnloaderRealtime sbUnloaderRealtime)
	{
		return sbUnloaderRealtimeMapper.insertSbUnloaderRealtime(sbUnloaderRealtime);
	}

	/**
	 * 修改卸料实时数据
	 *
	 * @param sbUnloaderRealtime 卸料实时数据信息
	 * @return 结果
	 */
	@Override
	public int updateSbUnloaderRealtime(SbUnloaderRealtime sbUnloaderRealtime)
	{
		return sbUnloaderRealtimeMapper.updateSbUnloaderRealtime(sbUnloaderRealtime);
	}

	/**
	 * 删除卸料实时数据对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteSbUnloaderRealtimeByIds(String ids)
	{
		return sbUnloaderRealtimeMapper.deleteSbUnloaderRealtimeByIds(Convert.toStrArray(ids));
	}

}
