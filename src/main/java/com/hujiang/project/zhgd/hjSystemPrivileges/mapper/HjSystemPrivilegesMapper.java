package com.hujiang.project.zhgd.hjSystemPrivileges.mapper;

import com.hujiang.project.zhgd.hjSystemPrivileges.domain.HjSystemPrivileges;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.SystemRoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统-权限 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjSystemPrivilegesMapper 
{
	/**
	 * 根据用户id查询pc或app菜单权限
	 * @param uid
	 * @param appOrPc
	 * @return
	 */
	List<HjSystemPrivileges> selectHjSystemPrivilegesByUser(@Param("uid") Integer uid,@Param("appOrPc") Integer appOrPc,@Param("parentId")Integer parentId);
	/**
     * 查询系统-权限信息
     * 
     * @param id 系统-权限ID
     * @return 系统-权限信息
     */
	public HjSystemPrivileges selectHjSystemPrivilegesById(Integer id);
	
	/**
     * 查询系统-权限列表
     * 
     * @param hjSystemPrivileges 系统-权限信息
     * @return 系统-权限集合
     */
	public List<HjSystemPrivileges> selectHjSystemPrivilegesList(HjSystemPrivileges hjSystemPrivileges);
	
	/**
     * 新增系统-权限
     * 
     * @param hjSystemPrivileges 系统-权限信息
     * @return 结果
     */
	public int insertHjSystemPrivileges(HjSystemPrivileges hjSystemPrivileges);
	
	/**
     * 修改系统-权限
     * 
     * @param hjSystemPrivileges 系统-权限信息
     * @return 结果
     */
	public int updateHjSystemPrivileges(HjSystemPrivileges hjSystemPrivileges);
	
	/**
     * 删除系统-权限
     * 
     * @param id 系统-权限ID
     * @return 结果
     */
	public int deleteHjSystemPrivilegesById(Integer id);
	
	/**
     * 批量删除系统-权限
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSystemPrivilegesByIds(String[] ids);

	/**
	 * 查询权限列表
	 * @param systemRoleParam
	 * @return
	 */
	List<SystemRoleParam> selectSystemPrivileges(SystemRoleParam systemRoleParam);
	/**
	 * 查询权限列表
	 * @param systemRoleParam
	 * @return
	 */
	List<SystemRoleParam> getPrivilegesOne(SystemRoleParam systemRoleParam);
	List<SystemRoleParam> getPrivilegesTwo(SystemRoleParam systemRoleParam);
}