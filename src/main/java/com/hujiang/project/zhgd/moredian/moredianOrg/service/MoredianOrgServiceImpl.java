package com.hujiang.project.zhgd.moredian.moredianOrg.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.mapper.MoredianOrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 机构 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Service
@Transactional
public class MoredianOrgServiceImpl implements IMoredianOrgService 
{
	@Autowired
	private MoredianOrgMapper moredianOrgMapper;

	/**
     * 查询机构信息
     * 
     * @param id 机构ID
     * @return 机构信息
     */
    @Override
	public MoredianOrg selectMoredianOrgById(Integer id)
	{
	    return moredianOrgMapper.selectMoredianOrgById(id);
	}
	/**
	 * 根据群组id查询机构
	 * @param groupId
	 * @return
	 */
	@Override
	public MoredianOrg selectMoredianOrgByGroupId(String groupId){
		return moredianOrgMapper.selectMoredianOrgByGroupId(groupId);
	}
	/**
     * 查询机构列表
     * 
     * @param moredianOrg 机构信息
     * @return 机构集合
     */
	@Override
	public List<MoredianOrg> selectMoredianOrgList(MoredianOrg moredianOrg)
	{
	    return moredianOrgMapper.selectMoredianOrgList(moredianOrg);
	}
	
    /**
     * 新增机构
     * 
     * @param moredianOrg 机构信息
     * @return 结果
     */
	@Override
	public int insertMoredianOrg(MoredianOrg moredianOrg)
	{
	    return moredianOrgMapper.insertMoredianOrg(moredianOrg);
	}
	
	/**
     * 修改机构
     * 
     * @param moredianOrg 机构信息
     * @return 结果
     */
	@Override
	public int updateMoredianOrg(MoredianOrg moredianOrg)
	{
	    return moredianOrgMapper.updateMoredianOrg(moredianOrg);
	}

	/**
     * 删除机构对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianOrgByIds(String ids)
	{
		return moredianOrgMapper.deleteMoredianOrgByIds(Convert.toStrArray(ids));
	}
	
}
