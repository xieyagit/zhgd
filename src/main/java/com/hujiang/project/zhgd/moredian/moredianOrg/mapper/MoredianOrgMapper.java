package com.hujiang.project.zhgd.moredian.moredianOrg.mapper;

import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;

import java.util.List;

/**
 * 机构 数据层
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public interface MoredianOrgMapper 
{
	/**
     * 查询机构信息
     * 
     * @param id 机构ID
     * @return 机构信息
     */
	public MoredianOrg selectMoredianOrgById(Integer id);

	/**
	 * 根据群组id查询机构
	 * @param groupId
	 * @return
	 */
	public MoredianOrg selectMoredianOrgByGroupId(String groupId);

	/**
     * 查询机构列表
     * 
     * @param moredianOrg 机构信息
     * @return 机构集合
     */
	public List<MoredianOrg> selectMoredianOrgList(MoredianOrg moredianOrg);
	
	/**
     * 新增机构
     * 
     * @param moredianOrg 机构信息
     * @return 结果
     */
	public int insertMoredianOrg(MoredianOrg moredianOrg);
	
	/**
     * 修改机构
     * 
     * @param moredianOrg 机构信息
     * @return 结果
     */
	public int updateMoredianOrg(MoredianOrg moredianOrg);
	
	/**
     * 删除机构
     * 
     * @param id 机构ID
     * @return 结果
     */
	public int deleteMoredianOrgById(Integer id);
	
	/**
     * 批量删除机构
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianOrgByIds(String[] ids);
	
}