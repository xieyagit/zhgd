package com.hujiang.project.zhgd.sbUnloaderAlarminformation.mapper;

import com.hujiang.project.zhgd.sbUnloaderAlarminformation.domain.SbUnloaderAlarminformation;
import java.util.List;	

/**
 * 卸料报警时段数据 数据层
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public interface SbUnloaderAlarminformationMapper 
{
	/**
     * 查询卸料报警时段数据信息
     * 
     * @param id 卸料报警时段数据ID
     * @return 卸料报警时段数据信息
     */
	public SbUnloaderAlarminformation selectSbUnloaderAlarminformationById(Integer id);
	
	/**
     * 查询卸料报警时段数据列表
     * 
     * @param sbUnloaderAlarminformation 卸料报警时段数据信息
     * @return 卸料报警时段数据集合
     */
	public List<SbUnloaderAlarminformation> selectSbUnloaderAlarminformationList(SbUnloaderAlarminformation sbUnloaderAlarminformation);
	
	/**
     * 新增卸料报警时段数据
     * 
     * @param sbUnloaderAlarminformation 卸料报警时段数据信息
     * @return 结果
     */
	public int insertSbUnloaderAlarminformation(SbUnloaderAlarminformation sbUnloaderAlarminformation);
	
	/**
     * 修改卸料报警时段数据
     * 
     * @param sbUnloaderAlarminformation 卸料报警时段数据信息
     * @return 结果
     */
	public int updateSbUnloaderAlarminformation(SbUnloaderAlarminformation sbUnloaderAlarminformation);
	
	/**
     * 删除卸料报警时段数据
     * 
     * @param id 卸料报警时段数据ID
     * @return 结果
     */
	public int deleteSbUnloaderAlarminformationById(Integer id);
	
	/**
     * 批量删除卸料报警时段数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbUnloaderAlarminformationByIds(String[] ids);
	
}