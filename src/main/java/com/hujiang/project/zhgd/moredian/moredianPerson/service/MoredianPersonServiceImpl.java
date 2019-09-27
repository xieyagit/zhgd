package com.hujiang.project.zhgd.moredian.moredianPerson.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianPerson.domain.MoredianPerson;
import com.hujiang.project.zhgd.moredian.moredianPerson.mapper.MoredianPersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 人员 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Service
@Transactional
public class MoredianPersonServiceImpl implements IMoredianPersonService 
{
	@Autowired
	private MoredianPersonMapper moredianPersonMapper;

	/**
     * 查询人员信息
     * 
     * @param id 人员ID
     * @return 人员信息
     */
    @Override
	public MoredianPerson selectMoredianPersonById(Integer id)
	{
	    return moredianPersonMapper.selectMoredianPersonById(id);
	}
	
	/**
     * 查询人员列表
     * 
     * @param moredianPerson 人员信息
     * @return 人员集合
     */
	@Override
	public List<MoredianPerson> selectMoredianPersonList(MoredianPerson moredianPerson)
	{
	    return moredianPersonMapper.selectMoredianPersonList(moredianPerson);
	}
	
    /**
     * 新增人员
     * 
     * @param moredianPerson 人员信息
     * @return 结果
     */
	@Override
	public int insertMoredianPerson(MoredianPerson moredianPerson)
	{
	    return moredianPersonMapper.insertMoredianPerson(moredianPerson);
	}
	
	/**
     * 修改人员
     * 
     * @param moredianPerson 人员信息
     * @return 结果
     */
	@Override
	public int updateMoredianPerson(MoredianPerson moredianPerson)
	{
	    return moredianPersonMapper.updateMoredianPerson(moredianPerson);
	}

	/**
     * 删除人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianPersonByIds(String ids)
	{
		return moredianPersonMapper.deleteMoredianPersonByIds(Convert.toStrArray(ids));
	}
	
}
