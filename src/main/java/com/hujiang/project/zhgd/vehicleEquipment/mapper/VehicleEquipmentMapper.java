package com.hujiang.project.zhgd.vehicleEquipment.mapper;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.vehicleEquipment.domain.VehicleEquipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车牌设备 数据层
 * 
 * @author hujiang
 * @date 2019-05-07
 */
public interface VehicleEquipmentMapper 
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
     * 删除车牌设备
     * 
     * @param id 车牌设备ID
     * @return 结果
     */
	public int deleteVehicleEquipmentById(Integer id);
	
	/**
     * 批量删除车牌设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVehicleEquipmentByIds(String[] ids);

	/**
	 * 查询数据是否存在
	 * @param camera_mac
	 * @param deptID
	 * @return
	 */
    VehicleEquipment selectVehive(@Param(value = "camera_mac") String camera_mac, @Param(value = "deptID") Integer deptID);

    /**
	 * 查询工地剩余车位
	 *
     * @return*/
    public VehicleEquipment selectresidue(Integer deptID);
}