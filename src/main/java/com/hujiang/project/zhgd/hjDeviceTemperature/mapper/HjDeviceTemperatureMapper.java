package com.hujiang.project.zhgd.hjDeviceTemperature.mapper;

import com.hujiang.project.zhgd.hjDeviceTemperature.domain.HjDeviceTemperature;
import java.util.List;	

/**
 * 人脸设备和测温设备绑定 数据层
 * 
 * @author hujiang
 * @date 2020-03-05
 */
public interface HjDeviceTemperatureMapper 
{
	/**
     * 查询人脸设备和测温设备绑定信息
     * 
     * @param id 人脸设备和测温设备绑定ID
     * @return 人脸设备和测温设备绑定信息
     */
	public HjDeviceTemperature selectHjDeviceTemperatureById(Integer id);
	
	/**
     * 查询人脸设备和测温设备绑定列表
     * 
     * @param hjDeviceTemperature 人脸设备和测温设备绑定信息
     * @return 人脸设备和测温设备绑定集合
     */
	public List<HjDeviceTemperature> selectHjDeviceTemperatureList(HjDeviceTemperature hjDeviceTemperature);
	
	/**
     * 新增人脸设备和测温设备绑定
     * 
     * @param hjDeviceTemperature 人脸设备和测温设备绑定信息
     * @return 结果
     */
	public int insertHjDeviceTemperature(HjDeviceTemperature hjDeviceTemperature);
	
	/**
     * 修改人脸设备和测温设备绑定
     * 
     * @param hjDeviceTemperature 人脸设备和测温设备绑定信息
     * @return 结果
     */
	public int updateHjDeviceTemperature(HjDeviceTemperature hjDeviceTemperature);
	
	/**
     * 删除人脸设备和测温设备绑定
     * 
     * @param id 人脸设备和测温设备绑定ID
     * @return 结果
     */
	public int deleteHjDeviceTemperatureById(Integer id);
	
	/**
     * 批量删除人脸设备和测温设备绑定
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjDeviceTemperatureByIds(String[] ids);
	
}