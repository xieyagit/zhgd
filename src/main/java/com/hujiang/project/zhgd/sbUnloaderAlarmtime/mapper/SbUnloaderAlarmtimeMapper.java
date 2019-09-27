package com.hujiang.project.zhgd.sbUnloaderAlarmtime.mapper;

import com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain.SbUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.ExportUnloaderAlarmtime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 卸料报警时刻数据 数据层
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public interface SbUnloaderAlarmtimeMapper 
{
	/** 卸料app报警界面数据*/
	public List<SbUnloaderAlarmtime> getSbUnloaderAlarmtimeList(SbUnloaderAlarmtime sbUnloaderAlarmtime);

	public List<ExportUnloaderAlarmtime> getSbUnloaderAlarmtimeListById(@Param("ids")String[] ids,
																		@Param("deviceId")String deviceId,
																		@Param("alarmType")Integer alarmType);
	public int count(@Param("projectId") Integer projectId,
					 @Param("hxzId") String hxzId,
					 @Param("alarmType") Integer alarmType,
					 @Param("startTime")String startTime);
	/**
     * 查询卸料报警时刻数据信息
     * 
     * @param id 卸料报警时刻数据ID
     * @return 卸料报警时刻数据信息
     */
	public SbUnloaderAlarmtime selectSbUnloaderAlarmtimeById(Integer id);
	
	/**
     * 查询卸料报警时刻数据列表
     * 
     * @param sbUnloaderAlarmtime 卸料报警时刻数据信息
     * @return 卸料报警时刻数据集合
     */
	public List<SbUnloaderAlarmtime> selectSbUnloaderAlarmtimeList(SbUnloaderAlarmtime sbUnloaderAlarmtime);
	
	/**
     * 新增卸料报警时刻数据
     * 
     * @param sbUnloaderAlarmtime 卸料报警时刻数据信息
     * @return 结果
     */
	public int insertSbUnloaderAlarmtime(SbUnloaderAlarmtime sbUnloaderAlarmtime);
	
	/**
     * 修改卸料报警时刻数据
     * 
     * @param sbUnloaderAlarmtime 卸料报警时刻数据信息
     * @return 结果
     */
	public int updateSbUnloaderAlarmtime(SbUnloaderAlarmtime sbUnloaderAlarmtime);
	
	/**
     * 删除卸料报警时刻数据
     * 
     * @param id 卸料报警时刻数据ID
     * @return 结果
     */
	public int deleteSbUnloaderAlarmtimeById(Integer id);
	
	/**
     * 批量删除卸料报警时刻数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbUnloaderAlarmtimeByIds(String[] ids);
	
}