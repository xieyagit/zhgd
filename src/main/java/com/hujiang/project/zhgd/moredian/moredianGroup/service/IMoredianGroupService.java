package com.hujiang.project.zhgd.moredian.moredianGroup.service;

import com.hujiang.project.zhgd.moredian.moredianGroup.domain.MoredianGroup;

import java.util.List;

/**
 * 项目群组 服务层
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public interface IMoredianGroupService 
{
	/**
     * 查询项目群组信息
     * 
     * @param id 项目群组ID
     * @return 项目群组信息
     */
	public MoredianGroup selectMoredianGroupById(Integer id);
	
	/**
     * 查询项目群组列表
     * 
     * @param moredianGroup 项目群组信息
     * @return 项目群组集合
     */
	public List<MoredianGroup> selectMoredianGroupList(MoredianGroup moredianGroup);
	
	/**
     * 新增项目群组
     * 
     * @param moredianGroup 项目群组信息
     * @return 结果
     */
	public int insertMoredianGroup(MoredianGroup moredianGroup);
	
	/**
     * 修改项目群组
     * 
     * @param moredianGroup 项目群组信息
     * @return 结果
     */
	public int updateMoredianGroup(MoredianGroup moredianGroup);
		
	/**
     * 删除项目群组信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianGroupByIds(String ids);
	
}
