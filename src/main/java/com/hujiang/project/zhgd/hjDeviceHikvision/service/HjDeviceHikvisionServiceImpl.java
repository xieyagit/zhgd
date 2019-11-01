package com.hujiang.project.zhgd.hjDeviceHikvision.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjDeviceHikvision.mapper.HjDeviceHikvisionMapper;
import com.hujiang.project.zhgd.hjDeviceHikvision.domain.HjDeviceHikvision;
import com.hujiang.project.zhgd.hjDeviceHikvision.service.IHjDeviceHikvisionService;
import com.hujiang.common.support.Convert;

/**
 * 海康萤石用户 服务层实现
 * 
 * @author hujiang
 * @date 2019-10-19
 */
@Service
public class HjDeviceHikvisionServiceImpl implements IHjDeviceHikvisionService 
{
	@Autowired
	private HjDeviceHikvisionMapper hjDeviceHikvisionMapper;

	/**
     * 查询海康萤石用户信息
     * 
     * @param id 海康萤石用户ID
     * @return 海康萤石用户信息
     */
    @Override
	public HjDeviceHikvision selectHjDeviceHikvisionById(Integer id)
	{
	    return hjDeviceHikvisionMapper.selectHjDeviceHikvisionById(id);
	}
	
	/**
     * 查询海康萤石用户列表
     * 
     * @param hjDeviceHikvision 海康萤石用户信息
     * @return 海康萤石用户集合
     */
	@Override
	public List<HjDeviceHikvision> selectHjDeviceHikvisionList(HjDeviceHikvision hjDeviceHikvision)
	{
	    return hjDeviceHikvisionMapper.selectHjDeviceHikvisionList(hjDeviceHikvision);
	}
	
    /**
     * 新增海康萤石用户
     * 
     * @param hjDeviceHikvision 海康萤石用户信息
     * @return 结果
     */
	@Override
	public int insertHjDeviceHikvision(HjDeviceHikvision hjDeviceHikvision)
	{
	    return hjDeviceHikvisionMapper.insertHjDeviceHikvision(hjDeviceHikvision);
	}
	
	/**
     * 修改海康萤石用户
     * 
     * @param hjDeviceHikvision 海康萤石用户信息
     * @return 结果
     */
	@Override
	public int updateHjDeviceHikvision(HjDeviceHikvision hjDeviceHikvision)
	{
	    return hjDeviceHikvisionMapper.updateHjDeviceHikvision(hjDeviceHikvision);
	}

	/**
     * 删除海康萤石用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjDeviceHikvisionByIds(String ids)
	{
		return hjDeviceHikvisionMapper.deleteHjDeviceHikvisionByIds(Convert.toStrArray(ids));
	}
	
}
