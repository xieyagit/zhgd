package com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.domain.MoredianGroupDeviceConfiguration;
import com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.mapper.MoredianGroupDeviceConfigurationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 群组设备配置 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Service
@Transactional
public class MoredianGroupDeviceConfigurationServiceImpl implements IMoredianGroupDeviceConfigurationService 
{
	@Autowired
	private MoredianGroupDeviceConfigurationMapper moredianGroupDeviceConfigurationMapper;

	/**
     * 查询群组设备配置信息
     * 
     * @param id 群组设备配置ID
     * @return 群组设备配置信息
     */
    @Override
	public MoredianGroupDeviceConfiguration selectMoredianGroupDeviceConfigurationById(Integer id)
	{
	    return moredianGroupDeviceConfigurationMapper.selectMoredianGroupDeviceConfigurationById(id);
	}
	
	/**
     * 查询群组设备配置列表
     * 
     * @param moredianGroupDeviceConfiguration 群组设备配置信息
     * @return 群组设备配置集合
     */
	@Override
	public List<MoredianGroupDeviceConfiguration> selectMoredianGroupDeviceConfigurationList(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration)
	{
	    return moredianGroupDeviceConfigurationMapper.selectMoredianGroupDeviceConfigurationList(moredianGroupDeviceConfiguration);
	}
	
    /**
     * 新增群组设备配置
     * 
     * @param moredianGroupDeviceConfiguration 群组设备配置信息
     * @return 结果
     */
	@Override
	public int insertMoredianGroupDeviceConfiguration(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration)
	{
	    return moredianGroupDeviceConfigurationMapper.insertMoredianGroupDeviceConfiguration(moredianGroupDeviceConfiguration);
	}
	
	/**
     * 修改群组设备配置
     * 
     * @param moredianGroupDeviceConfiguration 群组设备配置信息
     * @return 结果
     */
	@Override
	public int updateMoredianGroupDeviceConfiguration(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration)
	{
	    return moredianGroupDeviceConfigurationMapper.updateMoredianGroupDeviceConfiguration(moredianGroupDeviceConfiguration);
	}

	/**
     * 删除群组设备配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianGroupDeviceConfigurationByIds(String ids)
	{
		return moredianGroupDeviceConfigurationMapper.deleteMoredianGroupDeviceConfigurationByIds(Convert.toStrArray(ids));
	}
	
}
