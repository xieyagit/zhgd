package com.hujiang.project.zhgd.hjSystemRole.mapper;

import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.project.zhgd.hjSystemRole.domain.RoleParam;
import com.hujiang.project.zhgd.hjUserRole.domain.UserRoleParam;

import java.util.List;

/**
 * 系统-角色 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjSystemRoleMapper 
{
	/**
     * 查询系统-角色信息
     * 
     * @param id 系统-角色ID
     * @return 系统-角色信息
     */
	public HjSystemRole selectHjSystemRoleById(Integer id);
	
	/**
     * 查询系统-角色列表
     * 
     * @param hjSystemRole 系统-角色信息
     * @return 系统-角色集合
     */
	public List<HjSystemRole> selectHjSystemRoleList(HjSystemRole hjSystemRole);
	
	/**
     * 新增系统-角色
     * 
     * @param hjSystemRole 系统-角色信息
     * @return 结果
     */
	public int insertHjSystemRole(HjSystemRole hjSystemRole);
	
	/**
     * 修改系统-角色
     * 
     * @param hjSystemRole 系统-角色信息
     * @return 结果
     */
	public int updateHjSystemRole(HjSystemRole hjSystemRole);
	
	/**
     * 删除系统-角色
     * 
     * @param id 系统-角色ID
     * @return 结果
     */
	public int deleteHjSystemRoleById(Integer id);
	
	/**
     * 批量删除系统-角色
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSystemRoleByIds(String[] ids);

	/**
	 * 角色总条数
	 *
	 * @param systemRoleParam
	 * @return 结果
	 */
    Integer selectSystemRoleCount(RoleParam systemRoleParam);

	/**
	 * 角色
	 *
	 * @param systemRoleParam
	 * @return 结果
	 */
	List<RoleParam> selectSystemRole(RoleParam systemRoleParam);

	/**
	 * 用户拥有角色
	 * @param userRoleParam
	 */
    List<HjSystemRole> queryUserRole(UserRoleParam userRoleParam);
}