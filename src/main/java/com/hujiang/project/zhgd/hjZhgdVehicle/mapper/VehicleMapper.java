package com.hujiang.project.zhgd.hjZhgdVehicle.mapper;


import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Parkingspace;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Uploadcartype;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Vehicle;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车牌数据 数据层
 * 
 * @author hujiang
 * @date 2019-05-07
 */
public interface VehicleMapper 
{
	/**
     * 查询车牌数据信息
     * 
     * @param id 车牌数据ID
     * @return 车牌数据信息
     */
	public Vehicle selectVehicleById(Integer id);
	
	/**
     * 查询车牌数据列表
     * 
     * @param vehicle 车牌数据信息
     * @return 车牌数据集合
     */
	public List<Vehicle> selectVehicleList(Vehicle vehicle);
	public List<Vehicle> selectVehicleListTwo(Vehicle vehicle);

	/**
     * 新增车牌数据
     * 
     * @param vehicle 车牌数据信息
     * @return 结果
     */
	public int insertVehicle(Vehicle vehicle);
	
	/**
     * 修改车牌数据
     * 
     * @param vehicle 车牌数据信息
     * @return 结果
     */
	public int updateVehicle(Vehicle vehicle);
	
	/**
     * 删除车牌数据
     * 
     * @param id 车牌数据ID
     * @return 结果
     */
	public int deleteVehicleById(Integer id);
	
	/**
     * 批量删除车牌数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVehicleByIds(String[] ids);

	/**
	 * 上传车牌信息
	 * @param vehicle
	 */
    void updateVehicleIn(Vehicle vehicle);

    /**
	 * 获取全部进出车辆信息
	 * */
    public List<Vehicle> selectAll(Vehicle vehicle);

    /**
	 * 查询出在场车辆
	 * */
    public List<Vehicle> selectscene(Vehicle vehicle);
    public Vehicle selectscenes(Vehicle vehicle);

    /**
	 * 修改车位数
	 * */
    public int updatepkcount(Parkingspace parkingspace);

    /**
	 * 写入图片
	 * */
    public int update(Vehicle vehicle);

    /**
	 * 在场车辆总数
	 * */
    public Vehicle countcar(Vehicle vehicle);

    /** 修改车辆类型（大车 ， 小车）*/
    public int updatezhgd(Vehicle vehicle);

    public Vehicle todaycount(@Param("deptID") Integer deptID,
							  @Param("liftTime") String liftTime);

    public List<Vehicle> list(@Param("deptID") Integer deptID,
							  @Param("liftTime") String liftTime);

}