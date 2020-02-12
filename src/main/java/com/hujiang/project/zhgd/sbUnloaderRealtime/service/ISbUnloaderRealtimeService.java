package com.hujiang.project.zhgd.sbUnloaderRealtime.service;

import com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain.SbUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderRealtime.domain.SbUnloaderRealtime;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.ExportUnloaderRealtime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 卸料实时数据 服务层
 *
 * @author hujiang
 * @date 2019-09-11
 */
public interface ISbUnloaderRealtimeService
{
	public List<ExportUnloaderRealtime> getSbUnloaderRealtimeListByIds(String[] ids,String startTime,String endTime,String deviceId);
	/**
	 * 卸料app界面数据
	 * @param sbUnloaderRealtime 卸料实时数据信息
	 * @return 卸料实时数据集合
	 */
	public SbUnloaderRealtime getSbUnloaderRealtimeList(SbUnloaderRealtime sbUnloaderRealtime);
	/**
	 * 卸料app历史记录
	 * @param sbUnloaderRealtime
	 * @return
	 */
	public List<SbUnloaderRealtime> getSbUnloaderHistory(SbUnloaderRealtime sbUnloaderRealtime);
	/**
	 * 查询卸料实时数据信息
	 *
	 * @param id 卸料实时数据ID
	 * @return 卸料实时数据信息
	 */
	public SbUnloaderRealtime selectSbUnloaderRealtimeById(Integer id);

	/**
	 * 查询卸料实时数据列表
	 *
	 * @param sbUnloaderRealtime 卸料实时数据信息
	 * @return 卸料实时数据集合
	 */
	public List<SbUnloaderRealtime> selectSbUnloaderRealtimeList(SbUnloaderRealtime sbUnloaderRealtime);

	/**
	 * 新增卸料实时数据
	 *
	 * @param sbUnloaderRealtime 卸料实时数据信息
	 * @return 结果
	 */
	public int insertSbUnloaderRealtime(SbUnloaderRealtime sbUnloaderRealtime);

	/**
	 * 修改卸料实时数据
	 *
	 * @param sbUnloaderRealtime 卸料实时数据信息
	 * @return 结果
	 */
	public int updateSbUnloaderRealtime(SbUnloaderRealtime sbUnloaderRealtime);

	/**
	 * 删除卸料实时数据信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteSbUnloaderRealtimeByIds(String ids);

}
