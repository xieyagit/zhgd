package com.hujiang.project.zhgd.moduleToPush.service;

import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import java.util.List;

/**
 * 用户推送开关 服务层
 * 
 * @author hujiang
 * @date 2019-09-05
 */
public interface IModuleToPushService 
{
	/**
	 * 查询用户推送开关列表
	 *
	 * @param moduleToPush 用户推送开关信息
	 * @return 用户推送开关集合
	 */
	public List<ModuleToPush> getModuleToPushList(ModuleToPush moduleToPush);
	/**
	 * 查询用户推送开关
	 *
	 * @param moduleToPush 用户推送开关信息
	 * @return 用户推送开关集合
	 */
	public ModuleToPush selectModuleToPush(ModuleToPush moduleToPush);
	/**
     * 查询用户推送开关信息
     * 
     * @param id 用户推送开关ID
     * @return 用户推送开关信息
     */
	public ModuleToPush selectModuleToPushById(Integer id);
	
	/**
     * 查询用户推送开关列表
     * 
     * @param moduleToPush 用户推送开关信息
     * @return 用户推送开关集合
     */
	public List<ModuleToPush> selectModuleToPushList(ModuleToPush moduleToPush);
	
	/**
     * 新增用户推送开关
     * 
     * @param moduleToPush 用户推送开关信息
     * @return 结果
     */
	public int insertModuleToPush(ModuleToPush moduleToPush);
	
	/**
     * 修改用户推送开关
     * 
     * @param moduleToPush 用户推送开关信息
     * @return 结果
     */
	public int updateModuleToPush(ModuleToPush moduleToPush);
		
	/**
     * 删除用户推送开关信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteModuleToPushByIds(String ids);
	
}
