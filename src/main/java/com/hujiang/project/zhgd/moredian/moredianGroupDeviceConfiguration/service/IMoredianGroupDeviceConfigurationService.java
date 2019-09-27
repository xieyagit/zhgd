package com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.service;

import com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.domain.MoredianGroupDeviceConfiguration;

import java.util.List;

/**
 * 群组设备配置 服务层
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public interface IMoredianGroupDeviceConfigurationService 
{
	/**
     * 查询群组设备配置信息
     * 
     * @param id 群组设备配置ID
     * @return 群组设备配置信息
     */
	public MoredianGroupDeviceConfiguration selectMoredianGroupDeviceConfigurationById(Integer id);
	
	/**
     * 查询群组设备配置列表
     * 
     * @param moredianGroupDeviceConfiguration 群组设备配置信息
     * @return 群组设备配置集合
     */
	public List<MoredianGroupDeviceConfiguration> selectMoredianGroupDeviceConfigurationList(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration);
	
	/**
     * 新增群组设备配置
     * 
     * @param moredianGroupDeviceConfiguration 群组设备配置信息
     * @return 结果
     */
	public int insertMoredianGroupDeviceConfiguration(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration);
	
	/**
     * 修改群组设备配置
     * 
     * @param moredianGroupDeviceConfiguration 群组设备配置信息
     * @return 结果
     */
	public int updateMoredianGroupDeviceConfiguration(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration);
		
	/**
     * 删除群组设备配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianGroupDeviceConfigurationByIds(String ids);
	
}
