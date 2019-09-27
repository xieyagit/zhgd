package com.hujiang.project.zhgd.hjRolePrivileges.mapper;

import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.PrivilegesRoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色-权限 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjRolePrivilegesMapper 
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
     * 删除角色-权限
     * 
     * @param id 角色-权限ID
     * @return 结果
     */
	public int deleteHjRolePrivilegesById(Integer id);
	
	/**
     * 批量删除角色-权限
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjRolePrivilegesByIds(String[] ids);

	/**
	 * 批量删除角色-权限
	 *
	 * @param parseInt 角色id
	 * @return 结果
	 */
    void deleteHjRoleId(@Param(value = "parseInt") Integer parseInt);

	/**
	 * 根据角色查询权限
	 * @param privilegesRoleParam
	 * @return
	 */
    List<PrivilegesRoleParam> selectRolePrivileges(PrivilegesRoleParam privilegesRoleParam);
}