package com.hujiang.project.zhgd.hjZhgdVehicle.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Parkingspace;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Vehicle;
import com.hujiang.project.zhgd.hjZhgdVehicle.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车牌数据 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-07
 */
@Service
public class  VehicleServiceImpl implements IVehicleService
{
	@Autowired
	private VehicleMapper vehicleMapper;

	/**
     * 查询车牌数据信息
     * 
     * @param id 车牌数据ID
     * @return 车牌数据信息
     */
    @Override
	public Vehicle selectVehicleById(Integer id)
	{
	    return vehicleMapper.selectVehicleById(id);
	}
	
	/**
     * 查询车牌数据列表
     * 
     * @param vehicle 车牌数据信息
     * @return 车牌数据集合
     */
	@Override
	public List<Vehicle> selectVehicleList(Vehicle vehicle)
	{
	    return vehicleMapper.selectVehicleList(vehicle);
	}
	
    /**
     * 新增车牌数据
     * 
     * @param vehicle 车牌数据信息
     * @return 结果
     */
	@Override
	public int insertVehicle(Vehicle vehicle)
	{
	    return vehicleMapper.insertVehicle(vehicle);
	}
	
	/**
     * 修改车牌数据
     * 
     * @param vehicle 车牌数据信息
     * @return 结果
     */
	@Override
	public int updateVehicle(Vehicle vehicle)
	{
	    return vehicleMapper.updateVehicle(vehicle);
	}

	/**
     * 删除车牌数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVehicleByIds(String ids)
	{
		return vehicleMapper.deleteVehicleByIds(Convert.toStrArray(ids));
	}

	/**
	 * 上传车牌信息
	 * @param vehicle
	 */
	@Override
	public void updateVehicleIn(Vehicle vehicle) {
		vehicleMapper.updateVehicleIn(vehicle);
	}

	/**
	 * 获取全部进出车辆信息
	 * */
	public List<Vehicle> selectAll(Vehicle vehicle){
		return vehicleMapper.selectAll(vehicle);
	}

	/**
	 * 查询出在场车辆
	 * */
	public List<Vehicle> selectscene(Vehicle vehicle){
		return vehicleMapper.selectscene(vehicle);
	}
	public Vehicle selectscenes(Vehicle vehicle){
		return vehicleMapper.selectscenes(vehicle);
	}

	/**
	 * 修改车位数
	 * */
	public int updatepkcount(Parkingspace parkingspace){
		return vehicleMapper.updatepkcount(parkingspace);
	}

	/**
	 * 写入图片
	 * */
	public int update(Vehicle vehicle){
		return vehicleMapper.update(vehicle);
	}

	/**
	 * 在场车辆总数
	 * */
	public Vehicle countcar(Vehicle vehicle){
		return vehicleMapper.countcar(vehicle);
	}
	/** 修改车辆类型（大车 ， 小车）*/
	public int updatezhgd(Vehicle vehicle){
		return vehicleMapper.updatezhgd(vehicle);
	}

	public Vehicle todaycount(Integer deptID,String liftTime){
		return vehicleMapper.todaycount(deptID,liftTime);
	}

	public List<Vehicle> list(Integer deptID,String liftTime){
		return vehicleMapper.list(deptID,liftTime);
	}
}

