package com.hujiang.project.zhgd.vehicleEquipment.service;


import com.hujiang.project.zhgd.vehicleEquipment.api.VehicleEquipmentApi;
import com.hujiang.project.zhgd.vehicleEquipment.domain.VehicleEquipment;

import java.util.List;

/**
 * 车牌设备 服务层
 * 
 * @author hujiang
 * @date 2019-05-07
 */
public interface IVehicleEquipmentService 
{
	/**
     * 查询车牌设备信息
     * 
     * @param id 车牌设备ID
     * @return 车牌设备信息
     */
	public VehicleEquipment selectVehicleEquipmentById(Integer id);
	
	/**
     * 查询车牌设备列表
     * 
     * @param vehicleEquipment 车牌设备信息
     * @return 车牌设备集合
     */
	public List<VehicleEquipment> selectVehicleEquipmentList(VehicleEquipment vehicleEquipment);
	
	/**
     * 新增车牌设备
     * 
     * @param vehicleEquipment 车牌设备信息
     * @return 结果
     */
	public int insertVehicleEquipment(VehicleEquipment vehicleEquipment);
	
	/**
     * 修改车牌设备
     * 
     * @param vehicleEquipment 车牌设备信息
     * @return 结果
     */
	public int updateVehicleEquipment(VehicleEquipment vehicleEquipment);
		
	/**
     * 删除车牌设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVehicleEquipmentByIds(String ids);

	/**
	 * 查询数据是否存在
	 * @param camera_mac
	 * @param deptID
	 * @return
	 */
    VehicleEquipment selectVehive(String camera_mac, Integer deptID);

	/**
	 * 查询工地剩余车位
	 *
     * @return*/
	public VehicleEquipment selectresidue(Integer deptID);
}
