package com.hujiang.project.zhgd.hjMenu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjMenu.mapper.HjMenuMapper;
import com.hujiang.project.zhgd.hjMenu.domain.HjMenu;
import com.hujiang.project.zhgd.hjMenu.service.IHjMenuService;
import com.hujiang.common.support.Convert;

/**
 * 两制服务菜单 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Service
public class HjMenuServiceImpl implements IHjMenuService 
{
	@Autowired
	private HjMenuMapper hjMenuMapper;

	/**
     * 查询两制服务菜单信息
     * 
     * @param id 两制服务菜单ID
     * @return 两制服务菜单信息
     */
    @Override
	public HjMenu selectHjMenuById(Integer id)
	{
	    return hjMenuMapper.selectHjMenuById(id);
	}
	
	/**
     * 查询两制服务菜单列表
     * 
     * @param hjMenu 两制服务菜单信息
     * @return 两制服务菜单集合
     */
	@Override
	public List<HjMenu> selectHjMenuList(HjMenu hjMenu)
	{
	    return hjMenuMapper.selectHjMenuList(hjMenu);
	}
	
    /**
     * 新增两制服务菜单
     * 
     * @param hjMenu 两制服务菜单信息
     * @return 结果
     */
	@Override
	public int insertHjMenu(HjMenu hjMenu)
	{
	    return hjMenuMapper.insertHjMenu(hjMenu);
	}
	
	/**
     * 修改两制服务菜单
     * 
     * @param hjMenu 两制服务菜单信息
     * @return 结果
     */
	@Override
	public int updateHjMenu(HjMenu hjMenu)
	{
	    return hjMenuMapper.updateHjMenu(hjMenu);
	}

	/**
     * 删除两制服务菜单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjMenuByIds(String ids)
	{
		return hjMenuMapper.deleteHjMenuByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询菜单列表
	 * */
	public HjMenu seleall(HjMenu menu){
		return hjMenuMapper.seleall(menu);
	}
	public List<HjMenu> selealls(Integer typeId){
		return  hjMenuMapper.selealls(typeId);
	}
	/**
	 * 修改状态
	 * */
	public int update(Integer id){
		return hjMenuMapper.update(id);
	}
	public int updates(Integer id){
		return hjMenuMapper.updates(id);
	}

}
