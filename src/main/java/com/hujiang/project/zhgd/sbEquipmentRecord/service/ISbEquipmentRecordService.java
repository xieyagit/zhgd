package com.hujiang.project.zhgd.sbEquipmentRecord.service;

import com.hujiang.project.zhgd.sbEquipmentRecord.domain.SbEquipmentRecord;

import java.util.List;

/**
 * 定位记录 服务层
 * 
 * @author hujiang
 * @date 2019-06-29
 */
public interface ISbEquipmentRecordService 
{
	/**
     * 查询定位记录信息
     * 
     * @param id 定位记录ID
     * @return 定位记录信息
     */
	public SbEquipmentRecord selectSbEquipmentRecordById(Integer id);
	
	/**
     * 查询定位记录列表
     * 
     * @param sbEquipmentRecord 定位记录信息
     * @return 定位记录集合
     */
	public List<SbEquipmentRecord> selectSbEquipmentRecordList(SbEquipmentRecord sbEquipmentRecord);
	
	/**
     * 新增定位记录
     * 
     * @param sbEquipmentRecord 定位记录信息
     * @return 结果
     */
	public int insertSbEquipmentRecord(SbEquipmentRecord sbEquipmentRecord);
	
	/**
     * 修改定位记录
     * 
     * @param sbEquipmentRecord 定位记录信息
     * @return 结果
     */
	public int updateSbEquipmentRecord(SbEquipmentRecord sbEquipmentRecord);
		
	/**
     * 删除定位记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbEquipmentRecordByIds(String ids);
	
}
