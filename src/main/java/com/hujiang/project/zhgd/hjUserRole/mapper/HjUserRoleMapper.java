package com.hujiang.project.zhgd.hjUserRole.mapper;

import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户-角色 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjUserRoleMapper 
{
	public List<HjUserRole> getHjUserRoleListByIds(@Param("ids")Integer ids[]);
	/**
     * 查询用户-角色信息
     * 
     * @param id 用户-角色ID
     * @return 用户-角色信息
     */
	public HjUserRole selectHjUserRoleById(Integer id);
	
	/**
     * 查询用户-角色列表
     * 
     * @param hjUserRole 用户-角色信息
     * @return 用户-角色集合
     */
	public List<HjUserRole> selectHjUserRoleList(HjUserRole hjUserRole);
	
	/**
     * 新增用户-角色
     * 
     * @param hjUserRole 用户-角色信息
     * @return 结果
     */
	public int insertHjUserRole(HjUserRole hjUserRole);
	
	/**
     * 修改用户-角色
     * 
     * @param hjUserRole 用户-角色信息
     * @return 结果
     */
	public int updateHjUserRole(HjUserRole hjUserRole);
	
	/**
     * 删除用户-角色
     * 
     * @param id 用户-角色ID
     * @return 结果
     */
	public int deleteHjUserRoleById(Integer id);
	
	/**
     * 批量删除用户-角色
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjUserRoleByIds(String[] ids);

	/**
	 * 删除角色对应数据
	 *
	 * @param parseInt
	 * @return 结果
	 */
    void deleteHjUserRoleId(@Param(value = "parseInt") Integer parseInt);

	/**
	 * 删除用户对应数据
	 *
	 * @param parseInt
	 * @return 结果
	 */
	void deleteHjUserId(@Param(value = "parseInt") Integer parseInt);
}