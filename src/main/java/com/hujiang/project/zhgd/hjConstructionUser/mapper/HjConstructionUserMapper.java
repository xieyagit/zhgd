package com.hujiang.project.zhgd.hjConstructionUser.mapper;

import com.hujiang.project.zhgd.hjConstructionUser.domain.HjConstructionUser;
import java.util.List;	

/**
 * 分包单位与用户关系 数据层
 * 
 * @author hujiang
 * @date 2019-07-10
 */
public interface HjConstructionUserMapper 
{
	/**
     * 查询分包单位与用户关系信息
     * 
     * @param id 分包单位与用户关系ID
     * @return 分包单位与用户关系信息
     */
	public HjConstructionUser selectHjConstructionUserById(Integer id);
	
	/**
     * 查询分包单位与用户关系列表
     * 
     * @param hjConstructionUser 分包单位与用户关系信息
     * @return 分包单位与用户关系集合
     */
	public List<HjConstructionUser> selectHjConstructionUserList(HjConstructionUser hjConstructionUser);
	
	/**
     * 新增分包单位与用户关系
     * 
     * @param hjConstructionUser 分包单位与用户关系信息
     * @return 结果
     */
	public int insertHjConstructionUser(HjConstructionUser hjConstructionUser);
	
	/**
     * 修改分包单位与用户关系
     * 
     * @param hjConstructionUser 分包单位与用户关系信息
     * @return 结果
     */
	public int updateHjConstructionUser(HjConstructionUser hjConstructionUser);
	
	/**
     * 删除分包单位与用户关系
     * 
     * @param id 分包单位与用户关系ID
     * @return 结果
     */
	public int deleteHjConstructionUserById(Integer id);
	
	/**
     * 批量删除分包单位与用户关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjConstructionUserByIds(String[] ids);
	
}