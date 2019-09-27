package com.hujiang.project.zhgd.hjSystemPrivileges.service;

import com.alibaba.fastjson.JSONArray;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.HjSystemPrivileges;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.SystemRoleParam;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

/**
 * 系统-权限 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjSystemPrivilegesService 
{
	/**
	 * 根据用户id查询pc或app菜单权限
	 * @param uid
	 * @param appOrPc
	 * @return
	 */
	JSONArray selectHjSystemPrivilegesByUser(Integer uid, Integer appOrPc,Integer parentId);

	List<SystemRoleParam> getPrivilegesOne(SystemRoleParam systemRoleParam);
	List<SystemRoleParam> getPrivilegesTwo(SystemRoleParam systemRoleParam);
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
     * 删除系统-权限信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSystemPrivilegesByIds(String ids);

	/**
	 * 查询权限列表
	 * @param systemRoleParam
	 * @return
	 */
	Map<String, Object> selectSystemPrivileges(SystemRoleParam systemRoleParam);

	/**
	 * 添加权限菜单
	 * @param hjSystemPrivileges
	 * @return
	 */
	Map<String, Object> insertSystemPrivileges(HjSystemPrivileges hjSystemPrivileges);

	/**
	 * 查询权限列表
	 * @param hjSystemPrivileges
	 * @return
	 */
	Map<String, Object> querySystemPrivileges(HjSystemPrivileges hjSystemPrivileges);
}
