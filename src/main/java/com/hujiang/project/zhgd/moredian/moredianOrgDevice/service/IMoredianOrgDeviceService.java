package com.hujiang.project.zhgd.moredian.moredianOrgDevice.service;

import com.hujiang.project.zhgd.moredian.moredianOrgDevice.domain.MoredianOrgDevice;

import java.util.List;

/**
 * 机构设备 服务层
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public interface IMoredianOrgDeviceService 
{
	/**
     * 查询机构设备信息
     * 
     * @param id 机构设备ID
     * @return 机构设备信息
     */
	public MoredianOrgDevice selectMoredianOrgDeviceById(Integer id);
	
	/**
     * 查询机构设备列表
     * 
     * @param moredianOrgDevice 机构设备信息
     * @return 机构设备集合
     */
	public List<MoredianOrgDevice> selectMoredianOrgDeviceList(MoredianOrgDevice moredianOrgDevice);
	
	/**
     * 新增机构设备
     * 
     * @param moredianOrgDevice 机构设备信息
     * @return 结果
     */
	public int insertMoredianOrgDevice(MoredianOrgDevice moredianOrgDevice);
	
	/**
     * 修改机构设备
     * 
     * @param moredianOrgDevice 机构设备信息
     * @return 结果
     */
	public int updateMoredianOrgDevice(MoredianOrgDevice moredianOrgDevice);
		
	/**
     * 删除机构设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianOrgDeviceByIds(String ids);
	
}
