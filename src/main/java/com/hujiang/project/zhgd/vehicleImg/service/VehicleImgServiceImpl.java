package com.hujiang.project.zhgd.vehicleImg.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.vehicleImg.domain.VehicleImg;
import com.hujiang.project.zhgd.vehicleImg.mapper.VehicleImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车牌照片 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-13
 */
@Service
public class VehicleImgServiceImpl implements IVehicleImgService 
{
	@Autowired
	private VehicleImgMapper vehicleImgMapper;

	/**
     * 查询车牌照片信息
     * 
     * @param id 车牌照片ID
     * @return 车牌照片信息
     */
    @Override
	public VehicleImg selectVehicleImgById(Integer id)
	{
	    return vehicleImgMapper.selectVehicleImgById(id);
	}
	
	/**
     * 查询车牌照片列表
     * 
     * @param vehicleImg 车牌照片信息
     * @return 车牌照片集合
     */
	@Override
	public List<VehicleImg> selectVehicleImgList(VehicleImg vehicleImg)
	{
	    return vehicleImgMapper.selectVehicleImgList(vehicleImg);
	}
	
    /**
     * 新增车牌照片
     * 
     * @param vehicleImg 车牌照片信息
     * @return 结果
     */
	@Override
	public int insertVehicleImg(VehicleImg vehicleImg)
	{
	    return vehicleImgMapper.insertVehicleImg(vehicleImg);
	}
	
	/**
     * 修改车牌照片
     * 
     * @param vehicleImg 车牌照片信息
     * @return 结果
     */
	@Override
	public int updateVehicleImg(VehicleImg vehicleImg)
	{
	    return vehicleImgMapper.updateVehicleImg(vehicleImg);
	}

	/**
     * 删除车牌照片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVehicleImgByIds(String ids)
	{
		return vehicleImgMapper.deleteVehicleImgByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询照片路径
	 * @param parkid
	 * @param order_id
	 * @return
	 */
	@Override
	public VehicleImg selectVehicleImgUrl(String parkid, String order_id,int picSource) {
		return vehicleImgMapper.selectVehicleImgUrl(parkid,order_id,picSource);
	}

}
