package com.hujiang.project.zhgd.moredian.moredianGroupDevice.mapper;

import com.hujiang.project.zhgd.moredian.moredianGroupDevice.domain.MoredianGroupDevice;

import java.util.List;

/**
 * 群组设备 数据层
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public interface MoredianGroupDeviceMapper 
{
	/**
     * 查询群组设备信息
     * 
     * @param id 群组设备ID
     * @return 群组设备信息
     */
	public MoredianGroupDevice selectMoredianGroupDeviceById(Integer id);
	
	/**
     * 查询群组设备列表
     * 
     * @param moredianGroupDevice 群组设备信息
     * @return 群组设备集合
     */
	public List<MoredianGroupDevice> selectMoredianGroupDeviceList(MoredianGroupDevice moredianGroupDevice);
	
	/**
     * 新增群组设备
     * 
     * @param moredianGroupDevice 群组设备信息
     * @return 结果
     */
	public int insertMoredianGroupDevice(MoredianGroupDevice moredianGroupDevice);
	
	/**
     * 修改群组设备
     * 
     * @param moredianGroupDevice 群组设备信息
     * @return 结果
     */
	public int updateMoredianGroupDevice(MoredianGroupDevice moredianGroupDevice);
	
	/**
     * 删除群组设备
     * 
     * @param id 群组设备ID
     * @return 结果
     */
	public int deleteMoredianGroupDeviceById(Integer id);
	
	/**
     * 批量删除群组设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianGroupDeviceByIds(String[] ids);
	
}