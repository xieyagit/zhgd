package com.hujiang.project.zhgd.hjCompanyHierarchy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjCompanyHierarchy.mapper.HjCompanyHierarchyMapper;
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.common.support.Convert;

/**
 * 公司层级 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjCompanyHierarchyServiceImpl implements IHjCompanyHierarchyService 
{
	@Autowired
	private HjCompanyHierarchyMapper hjCompanyHierarchyMapper;

	/**
     * 查询公司层级信息
     * 
     * @param id 公司层级ID
     * @return 公司层级信息
     */
    @Override
	public HjCompanyHierarchy selectHjCompanyHierarchyById(Integer id)
	{
	    return hjCompanyHierarchyMapper.selectHjCompanyHierarchyById(id);
	}
	
	/**
     * 查询公司层级列表
     * 
     * @param hjCompanyHierarchy 公司层级信息
     * @return 公司层级集合
     */
	@Override
	public List<HjCompanyHierarchy> selectHjCompanyHierarchyList(HjCompanyHierarchy hjCompanyHierarchy)
	{
	    return hjCompanyHierarchyMapper.selectHjCompanyHierarchyList(hjCompanyHierarchy);
	}

    /**
     * 新增公司层级
     * 
     * @param hjCompanyHierarchy 公司层级信息
     * @return 结果
     */
	@Override
	public int insertHjCompanyHierarchy(HjCompanyHierarchy hjCompanyHierarchy)
	{
	    return hjCompanyHierarchyMapper.insertHjCompanyHierarchy(hjCompanyHierarchy);
	}
	
	/**
     * 修改公司层级
     * 
     * @param hjCompanyHierarchy 公司层级信息
     * @return 结果
     */
	@Override
	public int updateHjCompanyHierarchy(HjCompanyHierarchy hjCompanyHierarchy)
	{
	    return hjCompanyHierarchyMapper.updateHjCompanyHierarchy(hjCompanyHierarchy);
	}

	/**
     * 删除公司层级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjCompanyHierarchyByIds(String ids)
	{
		return hjCompanyHierarchyMapper.deleteHjCompanyHierarchyByIds(Convert.toStrArray(ids));
	}
	
}
