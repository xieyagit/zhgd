package com.hujiang.project.zhgd.moredian.moredianGroupDevice.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianGroupDevice.domain.MoredianGroupDevice;
import com.hujiang.project.zhgd.moredian.moredianGroupDevice.mapper.MoredianGroupDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 群组设备 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Service
@Transactional
public class MoredianGroupDeviceServiceImpl implements IMoredianGroupDeviceService 
{
	@Autowired
	private MoredianGroupDeviceMapper moredianGroupDeviceMapper;

	/**
     * 查询群组设备信息
     * 
     * @param id 群组设备ID
     * @return 群组设备信息
     */
    @Override
	public MoredianGroupDevice selectMoredianGroupDeviceById(Integer id)
	{
	    return moredianGroupDeviceMapper.selectMoredianGroupDeviceById(id);
	}
	
	/**
     * 查询群组设备列表
     * 
     * @param moredianGroupDevice 群组设备信息
     * @return 群组设备集合
     */
	@Override
	public List<MoredianGroupDevice> selectMoredianGroupDeviceList(MoredianGroupDevice moredianGroupDevice)
	{
	    return moredianGroupDeviceMapper.selectMoredianGroupDeviceList(moredianGroupDevice);
	}
	
    /**
     * 新增群组设备
     * 
     * @param moredianGroupDevice 群组设备信息
     * @return 结果
     */
	@Override
	public int insertMoredianGroupDevice(MoredianGroupDevice moredianGroupDevice)
	{
	    return moredianGroupDeviceMapper.insertMoredianGroupDevice(moredianGroupDevice);
	}
	
	/**
     * 修改群组设备
     * 
     * @param moredianGroupDevice 群组设备信息
     * @return 结果
     */
	@Override
	public int updateMoredianGroupDevice(MoredianGroupDevice moredianGroupDevice)
	{
	    return moredianGroupDeviceMapper.updateMoredianGroupDevice(moredianGroupDevice);
	}

	/**
     * 删除群组设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianGroupDeviceByIds(String ids)
	{
		return moredianGroupDeviceMapper.deleteMoredianGroupDeviceByIds(Convert.toStrArray(ids));
	}
	
}
