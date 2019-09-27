package com.hujiang.project.zhgd.vehicleImg.service;

import com.hujiang.project.zhgd.vehicleImg.domain.VehicleImg;

import java.util.List;

/**
 * 车牌照片 服务层
 * 
 * @author hujiang
 * @date 2019-05-13
 */
public interface IVehicleImgService 
{
	/**
     * 查询车牌照片信息
     * 
     * @param id 车牌照片ID
     * @return 车牌照片信息
     */
	public VehicleImg selectVehicleImgById(Integer id);
	
	/**
     * 查询车牌照片列表
     * 
     * @param vehicleImg 车牌照片信息
     * @return 车牌照片集合
     */
	public List<VehicleImg> selectVehicleImgList(VehicleImg vehicleImg);
	
	/**
     * 新增车牌照片
     * 
     * @param vehicleImg 车牌照片信息
     * @return 结果
     */
	public int insertVehicleImg(VehicleImg vehicleImg);
	
	/**
     * 修改车牌照片
     * 
     * @param vehicleImg 车牌照片信息
     * @return 结果
     */
	public int updateVehicleImg(VehicleImg vehicleImg);
		
	/**
     * 删除车牌照片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteVehicleImgByIds(String ids);

	/**
	 * 查询照片路径
	 * @param parkid
	 * @param order_id
	 * @return
	 */
    VehicleImg selectVehicleImgUrl(String parkid, String order_id,int picSource);
}
