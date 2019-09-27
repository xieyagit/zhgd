package com.hujiang.project.zhgd.hjRolePrivileges.service;

import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.PrivilegesRoleParam;

import java.util.List;
import java.util.Map;

/**
 * 角色-权限 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjRolePrivilegesService 
{
	/**
     * 查询角色-权限信息
     * 
     * @param id 角色-权限ID
     * @return 角色-权限信息
     */
	public HjRolePrivileges selectHjRolePrivilegesById(Integer id);
	
	/**
     * 查询角色-权限列表
     * 
     * @param hjRolePrivileges 角色-权限信息
     * @return 角色-权限集合
     */
	public List<HjRolePrivileges> selectHjRolePrivilegesList(HjRolePrivileges hjRolePrivileges);
	
	/**
     * 新增角色-权限
     * 
     * @param hjRolePrivileges 角色-权限信息
     * @return 结果
     */
	public int insertHjRolePrivileges(HjRolePrivileges hjRolePrivileges);
	
	/**
     * 修改角色-权限
     * 
     * @param hjRolePrivileges 角色-权限信息
     * @return 结果
     */
	public int updateHjRolePrivileges(HjRolePrivileges hjRolePrivileges);
		
	/**
     * 删除角色-权限信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjRolePrivilegesByIds(String ids);

	/**
	 * 角色添加权限
	 * @param roleId 角色id
	 * @param privilegesId 权限id
	 * @return
	 */
    Map<String, Object> insertRolePrivileges(Integer roleId, String privilegesId);

	/**
	 * 根据角色查询权限
	 * @param privilegesRoleParam
	 * @return
	 */
	Map<String, Object> selectRolePrivileges(PrivilegesRoleParam privilegesRoleParam);
}
