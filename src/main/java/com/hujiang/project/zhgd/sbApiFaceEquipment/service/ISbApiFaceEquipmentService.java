package com.hujiang.project.zhgd.sbApiFaceEquipment.service;

import com.hujiang.project.zhgd.sbApiFaceEquipment.domain.SbApiFaceEquipment;
import java.util.List;

/**
 * 考勤人脸 服务层
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public interface ISbApiFaceEquipmentService 
{
	/**
     * 查询考勤人脸信息
     * 
     * @param id 考勤人脸ID
     * @return 考勤人脸信息
     */
	public SbApiFaceEquipment selectSbApiFaceEquipmentById(Integer id);
	
	/**
     * 查询考勤人脸列表
     * 
     * @param sbApiFaceEquipment 考勤人脸信息
     * @return 考勤人脸集合
     */
	public List<SbApiFaceEquipment> selectSbApiFaceEquipmentList(SbApiFaceEquipment sbApiFaceEquipment);
	
	/**
     * 新增考勤人脸
     * 
     * @param sbApiFaceEquipment 考勤人脸信息
     * @return 结果
     */
	public int insertSbApiFaceEquipment(SbApiFaceEquipment sbApiFaceEquipment);
	
	/**
     * 修改考勤人脸
     * 
     * @param sbApiFaceEquipment 考勤人脸信息
     * @return 结果
     */
	public int updateSbApiFaceEquipment(SbApiFaceEquipment sbApiFaceEquipment);
		
	/**
     * 删除考勤人脸信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbApiFaceEquipmentByIds(String ids);
	
}
