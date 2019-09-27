package com.hujiang.project.zhgd.sbUnloaderEquipment.mapper;

import com.hujiang.project.zhgd.sbUnloaderEquipment.domain.SbUnloaderEquipment;
import java.util.List;	

/**
 * 卸料设备运行时长 数据层
 * 
 * @author hujiang
 * @date 2019-09-12
 */
public interface SbUnloaderEquipmentMapper 
{
	public SbUnloaderEquipment getSbUnloaderEquipment(SbUnloaderEquipment sbUnloaderEquipment);
	int count(SbUnloaderEquipment sbUnloaderEquipment);
	/**
     * 查询卸料设备运行时长信息
     * 
     * @param id 卸料设备运行时长ID
     * @return 卸料设备运行时长信息
     */
	public SbUnloaderEquipment selectSbUnloaderEquipmentById(Integer id);
	
	/**
     * 查询卸料设备运行时长列表
     * 
     * @param sbUnloaderEquipment 卸料设备运行时长信息
     * @return 卸料设备运行时长集合
     */
	public List<SbUnloaderEquipment> selectSbUnloaderEquipmentList(SbUnloaderEquipment sbUnloaderEquipment);
	
	/**
     * 新增卸料设备运行时长
     * 
     * @param sbUnloaderEquipment 卸料设备运行时长信息
     * @return 结果
     */
	public int insertSbUnloaderEquipment(SbUnloaderEquipment sbUnloaderEquipment);
	
	/**
     * 修改卸料设备运行时长
     * 
     * @param sbUnloaderEquipment 卸料设备运行时长信息
     * @return 结果
     */
	public int updateSbUnloaderEquipment(SbUnloaderEquipment sbUnloaderEquipment);
	
	/**
     * 删除卸料设备运行时长
     * 
     * @param id 卸料设备运行时长ID
     * @return 结果
     */
	public int deleteSbUnloaderEquipmentById(Integer id);
	
	/**
     * 批量删除卸料设备运行时长
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbUnloaderEquipmentByIds(String[] ids);
	
}