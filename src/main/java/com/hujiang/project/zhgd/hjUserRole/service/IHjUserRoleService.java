package com.hujiang.project.zhgd.hjUserRole.service;

import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.project.zhgd.hjUserRole.domain.UserRoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户-角色 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjUserRoleService 
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
     * 删除用户-角色信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjUserRoleByIds(String ids);

	/**
	 * 用户添加角色
	 * @param userId 用户id
	 * @param ids 角色id 字符串
	 */
    Map<String, Object> insertUserRole(Integer userId, String ids);

	/**
	 * 用户拥有角色
	 * @param userRoleParam
	 */
	Map<String, Object> queryUserRole(UserRoleParam userRoleParam);
}
