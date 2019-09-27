package com.hujiang.project.zhgd.moredian.moredianGroupPerson.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianGroupPerson.domain.MoredianGroupPerson;
import com.hujiang.project.zhgd.moredian.moredianGroupPerson.mapper.MoredianGroupPersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 群组人员 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Service
@Transactional
public class MoredianGroupPersonServiceImpl implements IMoredianGroupPersonService 
{
	@Autowired
	private MoredianGroupPersonMapper moredianGroupPersonMapper;

	/**
     * 查询群组人员信息
     * 
     * @param id 群组人员ID
     * @return 群组人员信息
     */
    @Override
	public MoredianGroupPerson selectMoredianGroupPersonById(Integer id)
	{
	    return moredianGroupPersonMapper.selectMoredianGroupPersonById(id);
	}
	
	/**
     * 查询群组人员列表
     * 
     * @param moredianGroupPerson 群组人员信息
     * @return 群组人员集合
     */
	@Override
	public List<MoredianGroupPerson> selectMoredianGroupPersonList(MoredianGroupPerson moredianGroupPerson)
	{
	    return moredianGroupPersonMapper.selectMoredianGroupPersonList(moredianGroupPerson);
	}
	
    /**
     * 新增群组人员
     * 
     * @param moredianGroupPerson 群组人员信息
     * @return 结果
     */
	@Override
	public int insertMoredianGroupPerson(MoredianGroupPerson moredianGroupPerson)
	{
	    return moredianGroupPersonMapper.insertMoredianGroupPerson(moredianGroupPerson);
	}
	
	/**
     * 修改群组人员
     * 
     * @param moredianGroupPerson 群组人员信息
     * @return 结果
     */
	@Override
	public int updateMoredianGroupPerson(MoredianGroupPerson moredianGroupPerson)
	{
	    return moredianGroupPersonMapper.updateMoredianGroupPerson(moredianGroupPerson);
	}

	/**
     * 删除群组人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianGroupPersonByIds(String ids)
	{
		return moredianGroupPersonMapper.deleteMoredianGroupPersonByIds(Convert.toStrArray(ids));
	}

	@Override
	public int deleteMoredianGroupPersonBymemberId(String ids)
	{
		return moredianGroupPersonMapper.deleteMoredianGroupPersonBymemberId(Convert.toStrArray(ids));
	}

	
}
