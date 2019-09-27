package com.hujiang.project.zhgd.hjSystemRole.service;

import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.project.zhgd.hjSystemRole.domain.RoleParam;

import java.util.List;
import java.util.Map;

/**
 * 系统-角色 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjSystemRoleService 
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
     * 删除系统-角色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSystemRoleByIds(String ids);

	/**
	 * 创建角色
	 * @param hjSystemRole
	 * @return
	 */
	Map<String, Object> insertSystemRole(HjSystemRole hjSystemRole);

	/**
	 * 修改角色
	 * @param hjSystemRole
	 * @return
	 */
	Map<String, Object> updateSystemRole(HjSystemRole hjSystemRole);

	/**
	 * 删除角色
	 * @param ids 角色id(1,2,3)
	 * @return
	 */
	Map<String, Object> deleteSystemRole(String ids);

	/**
	 * 角色列表
	 * @param systemRoleParam
	 * @return
	 */
    Map<String, Object> selectSystemRole(RoleParam systemRoleParam);

	/**
	 * 项目 或者 集团 公司 有那些角色
	 * @param hjSystemRole
	 * @return
	 */
	Map<String, Object> querySystemRole(HjSystemRole hjSystemRole);

	/**
	 * 修改前显示
	 * @param hjSystemRole
	 * @return
	 */
	Map<String, Object> selectSystemRoleId(HjSystemRole hjSystemRole);

	/**
	 * 创建角色
	 * @param hjSystemRole
	 * @return
	 */
	Map<String, Object> addSystemRole(HjSystemRole hjSystemRole, String ids);

	/**
	 * 修改角色
	 * @param hjSystemRole
	 * @return
	 */
	Map<String, Object> updateRole(HjSystemRole hjSystemRole, String ids);

}
