package com.hujiang.project.zhgd.hjDeviceTemperature.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeviceTemperature.mapper.HjDeviceTemperatureMapper;
import com.hujiang.project.zhgd.hjDeviceTemperature.domain.HjDeviceTemperature;
import com.hujiang.project.zhgd.hjDeviceTemperature.service.IHjDeviceTemperatureService;
import com.hujiang.common.support.Convert;

/**
 * 人脸设备和测温设备绑定 服务层实现
 * 
 * @author hujiang
 * @date 2020-03-05
 */
@Service
public class HjDeviceTemperatureServiceImpl implements IHjDeviceTemperatureService 
{
	@Autowired
	private HjDeviceTemperatureMapper hjDeviceTemperatureMapper;

	/**
     * 查询人脸设备和测温设备绑定信息
     * 
     * @param id 人脸设备和测温设备绑定ID
     * @return 人脸设备和测温设备绑定信息
     */
    @Override
	public HjDeviceTemperature selectHjDeviceTemperatureById(Integer id)
	{
	    return hjDeviceTemperatureMapper.selectHjDeviceTemperatureById(id);
	}
	
	/**
     * 查询人脸设备和测温设备绑定列表
     * 
     * @param hjDeviceTemperature 人脸设备和测温设备绑定信息
     * @return 人脸设备和测温设备绑定集合
     */
	@Override
	public List<HjDeviceTemperature> selectHjDeviceTemperatureList(HjDeviceTemperature hjDeviceTemperature)
	{
	    return hjDeviceTemperatureMapper.selectHjDeviceTemperatureList(hjDeviceTemperature);
	}
	
    /**
     * 新增人脸设备和测温设备绑定
     * 
     * @param hjDeviceTemperature 人脸设备和测温设备绑定信息
     * @return 结果
     */
	@Override
	public int insertHjDeviceTemperature(HjDeviceTemperature hjDeviceTemperature)
	{
	    return hjDeviceTemperatureMapper.insertHjDeviceTemperature(hjDeviceTemperature);
	}
	
	/**
     * 修改人脸设备和测温设备绑定
     * 
     * @param hjDeviceTemperature 人脸设备和测温设备绑定信息
     * @return 结果
     */
	@Override
	public int updateHjDeviceTemperature(HjDeviceTemperature hjDeviceTemperature)
	{
	    return hjDeviceTemperatureMapper.updateHjDeviceTemperature(hjDeviceTemperature);
	}

	/**
     * 删除人脸设备和测温设备绑定对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjDeviceTemperatureByIds(String ids)
	{
		return hjDeviceTemperatureMapper.deleteHjDeviceTemperatureByIds(Convert.toStrArray(ids));
	}
	
}
