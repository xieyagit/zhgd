package com.hujiang.project.zhgd.hjZhgdDriver.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjZhgdDriver.mapper.HjZhgdDriverMapper;
import com.hujiang.project.zhgd.hjZhgdDriver.domain.HjZhgdDriver;
import com.hujiang.project.zhgd.hjZhgdDriver.service.IHjZhgdDriverService;
import com.hujiang.common.support.Convert;

/**
 * 车牌绑定司机 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-30
 */
@Service
public class HjZhgdDriverServiceImpl implements IHjZhgdDriverService 
{
	@Autowired
	private HjZhgdDriverMapper hjZhgdDriverMapper;

	/**
     * 查询车牌绑定司机信息
     * 
     * @param id 车牌绑定司机ID
     * @return 车牌绑定司机信息
     */
    @Override
	public HjZhgdDriver selectHjZhgdDriverById(Integer id)
	{
	    return hjZhgdDriverMapper.selectHjZhgdDriverById(id);
	}
	
	/**
     * 查询车牌绑定司机列表
     * 
     * @param hjZhgdDriver 车牌绑定司机信息
     * @return 车牌绑定司机集合
     */
	@Override
	public List<HjZhgdDriver> selectHjZhgdDriverList(HjZhgdDriver hjZhgdDriver)
	{
	    return hjZhgdDriverMapper.selectHjZhgdDriverList(hjZhgdDriver);
	}
	
    /**
     * 新增车牌绑定司机
     * @return 结果
     */
	@Override
	public int insertHjZhgdDriver(String driver, String vehicle,String projectId)
	{
	    return hjZhgdDriverMapper.insertHjZhgdDriver(driver, vehicle,projectId);
	}
	
	/**
     * 修改车牌绑定司机
     * 
     * @param hjZhgdDriver 车牌绑定司机信息
     * @return 结果
     */
	@Override
	public int updateHjZhgdDriver(HjZhgdDriver hjZhgdDriver)
	{
	    return hjZhgdDriverMapper.updateHjZhgdDriver(hjZhgdDriver);
	}

	/**
     * 删除车牌绑定司机对象
     * @return 结果
     */
	@Override
	public int deleteHjZhgdDriverById(Integer id)
	{
		return hjZhgdDriverMapper.deleteHjZhgdDriverById(id);
	}
	
}
