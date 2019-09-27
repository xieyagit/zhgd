package com.hujiang.project.zhgd.moredian.moredianGroup.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianGroup.domain.MoredianGroup;
import com.hujiang.project.zhgd.moredian.moredianGroup.mapper.MoredianGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目群组 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Service
@Transactional
public class MoredianGroupServiceImpl implements IMoredianGroupService 
{
	@Autowired
	private MoredianGroupMapper moredianGroupMapper;

	/**
     * 查询项目群组信息
     * 
     * @param id 项目群组ID
     * @return 项目群组信息
     */
    @Override
	public MoredianGroup selectMoredianGroupById(Integer id)
	{
	    return moredianGroupMapper.selectMoredianGroupById(id);
	}
	
	/**
     * 查询项目群组列表
     * 
     * @param moredianGroup 项目群组信息
     * @return 项目群组集合
     */
	@Override
	public List<MoredianGroup> selectMoredianGroupList(MoredianGroup moredianGroup)
	{
	    return moredianGroupMapper.selectMoredianGroupList(moredianGroup);
	}
	
    /**
     * 新增项目群组
     * 
     * @param moredianGroup 项目群组信息
     * @return 结果
     */
	@Override
	public int insertMoredianGroup(MoredianGroup moredianGroup)
	{
	    return moredianGroupMapper.insertMoredianGroup(moredianGroup);
	}
	
	/**
     * 修改项目群组
     * 
     * @param moredianGroup 项目群组信息
     * @return 结果
     */
	@Override
	public int updateMoredianGroup(MoredianGroup moredianGroup)
	{
	    return moredianGroupMapper.updateMoredianGroup(moredianGroup);
	}

	/**
     * 删除项目群组对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianGroupByIds(String ids)
	{
		return moredianGroupMapper.deleteMoredianGroupByIds(Convert.toStrArray(ids));
	}
	
}
