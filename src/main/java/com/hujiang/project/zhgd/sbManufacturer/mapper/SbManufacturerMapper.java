package com.hujiang.project.zhgd.sbManufacturer.mapper;

import com.hujiang.project.zhgd.sbManufacturer.domain.SbManufacturer;
import java.util.List;	

/**
 * 设备厂商名称 数据层
 * 
 * @author hujiang
 * @date 2019-09-24
 */
public interface SbManufacturerMapper 
{
	/**
     * 查询设备厂商名称信息
     * 
     * @param id 设备厂商名称ID
     * @return 设备厂商名称信息
     */
	public SbManufacturer selectSbManufacturerById(Integer id);
	
	/**
     * 查询设备厂商名称列表
     * 
     * @param sbManufacturer 设备厂商名称信息
     * @return 设备厂商名称集合
     */
	public List<SbManufacturer> selectSbManufacturerList(SbManufacturer sbManufacturer);
	
	/**
     * 新增设备厂商名称
     * 
     * @param sbManufacturer 设备厂商名称信息
     * @return 结果
     */
	public int insertSbManufacturer(SbManufacturer sbManufacturer);
	
	/**
     * 修改设备厂商名称
     * 
     * @param sbManufacturer 设备厂商名称信息
     * @return 结果
     */
	public int updateSbManufacturer(SbManufacturer sbManufacturer);
	
	/**
     * 删除设备厂商名称
     * 
     * @param id 设备厂商名称ID
     * @return 结果
     */
	public int deleteSbManufacturerById(Integer id);
	
	/**
     * 批量删除设备厂商名称
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSbManufacturerByIds(String[] ids);
	
}