package com.hujiang.project.zhgd.vehicleEquipment.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.vehicleEquipment.api.VehicleEquipmentApi;
import com.hujiang.project.zhgd.vehicleEquipment.domain.VehicleEquipment;
import com.hujiang.project.zhgd.vehicleEquipment.mapper.VehicleEquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车牌设备 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-07
 */
@Service
public class VehicleEquipmentServiceImpl implements IVehicleEquipmentService 
{
	@Autowired
	private VehicleEquipmentMapper vehicleEquipmentMapper;

	/**
     * 查询车牌设备信息
     * 
     * @param id 车牌设备ID
     * @return 车牌设备信息
     */
    @Override
	public VehicleEquipment selectVehicleEquipmentById(Integer id)
	{
	    return vehicleEquipmentMapper.selectVehicleEquipmentById(id);
	}
	
	/**
     * 查询车牌设备列表
     * 
     * @param vehicleEquipment 车牌设备信息
     * @return 车牌设备集合
     */
	@Override
	public List<VehicleEquipment> selectVehicleEquipmentList(VehicleEquipment vehicleEquipment)
	{
	    return vehicleEquipmentMapper.selectVehicleEquipmentList(vehicleEquipment);
	}
	
    /**
     * 新增车牌设备
     * 
     * @param vehicleEquipment 车牌设备信息
     * @return 结果
     */
	@Override
	public int insertVehicleEquipment(VehicleEquipment vehicleEquipment)
	{
	    return vehicleEquipmentMapper.insertVehicleEquipment(vehicleEquipment);
	}
	
	/**
     * 修改车牌设备
     * 
     * @param vehicleEquipment 车牌设备信息
     * @return 结果
     */
	@Override
	public int updateVehicleEquipment(VehicleEquipment vehicleEquipment)
	{
	    return vehicleEquipmentMapper.updateVehicleEquipment(vehicleEquipment);
	}

	/**
     * 删除车牌设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVehicleEquipmentByIds(String ids)
	{
		return vehicleEquipmentMapper.deleteVehicleEquipmentByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询数据是否存在
	 * @param camera_mac
	 * @param deptID
	 * @return
	 */
	@Override
	public VehicleEquipment selectVehive(String camera_mac, Integer deptID) {
		return vehicleEquipmentMapper.selectVehive(camera_mac,deptID);
	}

	/**
	 * 查询工地剩余车位
	 *
     * @return*/
	public VehicleEquipment selectresidue(Integer deptID){
		return  vehicleEquipmentMapper.selectresidue(deptID);
	}
}
