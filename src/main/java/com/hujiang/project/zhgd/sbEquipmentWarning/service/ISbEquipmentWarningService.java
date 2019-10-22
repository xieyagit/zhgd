package com.hujiang.project.zhgd.sbEquipmentWarning.service;

import com.hujiang.project.zhgd.sbEquipmentWarning.domain.SbEquipmentWarning;
import java.util.List;

/**
 * 定位报警 服务层
 * 
 * @author hujiang
 * @date 2019-10-19
 */
public interface ISbEquipmentWarningService 
{
	public int warningCount(SbEquipmentWarning sbEquipmentWarning);
	public List<SbEquipmentWarning> getWarningList(SbEquipmentWarning sbEquipmentWarning);
	/**
     * 查询定位报警信息
     * 
     * @param id 定位报警ID
     * @return 定位报警信息
     */
	public SbEquipmentWarning selectSbEquipmentWarningById(Integer id);
	
	/**
     * 查询定位报警列表
     * 
     * @param sbEquipmentWarning 定位报警信息
     * @return 定位报警集合
     */
	public List<SbEquipmentWarning> selectSbEquipmentWarningList(SbEquipmentWarning sbEquipmentWarning);
	
	/**
     * 新增定位报警
     * 
     * @param sbEquipmentWarning 定位报警信息
     * @return 结果
     */
	public int insertSbEquipmentWarning(SbEquipmentWarning sbEquipmentWarning);
	
	/**
     * 修改定位报警
     * 
     * @param sbEquipmentWarning 定位报警信息
     * @return 结果
     */
	public int updateSbEquipmentWarning(SbEquipmentWarning sbEquipmentWarning);
		
	/**
     * 删除定位报警信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbEquipmentWarningByIds(String ids);
	
}
