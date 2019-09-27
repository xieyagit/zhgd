package com.hujiang.project.zhgd.hjMenu.service;

import com.hujiang.project.zhgd.hjMenu.domain.HjMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 两制服务菜单 服务层
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public interface IHjMenuService 
{
	/**
     * 查询两制服务菜单信息
     * 
     * @param id 两制服务菜单ID
     * @return 两制服务菜单信息
     */
	public HjMenu selectHjMenuById(Integer id);
	
	/**
     * 查询两制服务菜单列表
     * 
     * @param hjMenu 两制服务菜单信息
     * @return 两制服务菜单集合
     */
	public List<HjMenu> selectHjMenuList(HjMenu hjMenu);
	
	/**
     * 新增两制服务菜单
     * 
     * @param hjMenu 两制服务菜单信息
     * @return 结果
     */
	public int insertHjMenu(HjMenu hjMenu);
	
	/**
     * 修改两制服务菜单
     * 
     * @param hjMenu 两制服务菜单信息
     * @return 结果
     */
	public int updateHjMenu(HjMenu hjMenu);
		
	/**
     * 删除两制服务菜单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjMenuByIds(String ids);

	/**
	 * 查询菜单列表
	 * */
	public HjMenu seleall(HjMenu menu);
	public List<HjMenu> selealls(Integer typeId);
	/**
	 * 修改状态
	 * */
	public int update(Integer id);
	public int updates(Integer id);
}
