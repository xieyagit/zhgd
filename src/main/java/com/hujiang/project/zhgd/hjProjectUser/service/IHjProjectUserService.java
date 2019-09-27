package com.hujiang.project.zhgd.hjProjectUser.service;

import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import java.util.List;

/**
 * 项目用户 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjProjectUserService 
{
	/**
     * 查询项目用户信息
     * 
     * @param id 项目用户ID
     * @return 项目用户信息
     */
	public HjProjectUser selectHjProjectUserById(Integer id);


	
	/**
     * 查询项目用户列表
     * 
     * @param hjProjectUser 项目用户信息
     * @return 项目用户集合
     */
	public List<HjProjectUser> selectHjProjectUserList(HjProjectUser hjProjectUser);
	
	/**
     * 新增项目用户
     * 
     * @param hjProjectUser 项目用户信息
     * @return 结果
     */
	public int insertHjProjectUser(HjProjectUser hjProjectUser);
	
	/**
     * 修改项目用户
     * 
     * @param hjProjectUser 项目用户信息
     * @return 结果
     */
	public int updateHjProjectUser(HjProjectUser hjProjectUser);
		
	/**
     * 删除项目用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjProjectUserByIds(String ids);

	/**
	 * 删除项目信息
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	void deleteHjProjectUserIds(Integer id);
	
}
