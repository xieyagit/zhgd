package com.hujiang.project.zhgd.moredian.moredianOrgDevice.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianOrgDevice.domain.MoredianOrgDevice;
import com.hujiang.project.zhgd.moredian.moredianOrgDevice.mapper.MoredianOrgDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 机构设备 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Service
@Transactional
public class MoredianOrgDeviceServiceImpl implements IMoredianOrgDeviceService 
{
	@Autowired
	private MoredianOrgDeviceMapper moredianOrgDeviceMapper;

	/**
     * 查询机构设备信息
     * 
     * @param id 机构设备ID
     * @return 机构设备信息
     */
    @Override
	public MoredianOrgDevice selectMoredianOrgDeviceById(Integer id)
	{
	    return moredianOrgDeviceMapper.selectMoredianOrgDeviceById(id);
	}
	
	/**
     * 查询机构设备列表
     * 
     * @param moredianOrgDevice 机构设备信息
     * @return 机构设备集合
     */
	@Override
	public List<MoredianOrgDevice> selectMoredianOrgDeviceList(MoredianOrgDevice moredianOrgDevice)
	{
	    return moredianOrgDeviceMapper.selectMoredianOrgDeviceList(moredianOrgDevice);
	}
	
    /**
     * 新增机构设备
     * 
     * @param moredianOrgDevice 机构设备信息
     * @return 结果
     */
	@Override
	public int insertMoredianOrgDevice(MoredianOrgDevice moredianOrgDevice)
	{
	    return moredianOrgDeviceMapper.insertMoredianOrgDevice(moredianOrgDevice);
	}
	
	/**
     * 修改机构设备
     * 
     * @param moredianOrgDevice 机构设备信息
     * @return 结果
     */
	@Override
	public int updateMoredianOrgDevice(MoredianOrgDevice moredianOrgDevice)
	{
	    return moredianOrgDeviceMapper.updateMoredianOrgDevice(moredianOrgDevice);
	}

	/**
     * 删除机构设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianOrgDeviceByIds(String ids)
	{
		return moredianOrgDeviceMapper.deleteMoredianOrgDeviceByIds(Convert.toStrArray(ids));
	}
	
}
