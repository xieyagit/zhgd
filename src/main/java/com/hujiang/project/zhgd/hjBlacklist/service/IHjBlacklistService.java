package com.hujiang.project.zhgd.hjBlacklist.service;

import com.hujiang.project.zhgd.hjBlacklist.domain.HjBlacklist;
import java.util.List;

/**
 * 黑名单 服务层
 * 
 * @author hujiang
 * @date 2019-06-25
 */
public interface IHjBlacklistService 
{
	/**
     * 查询黑名单信息
     * 
     * @param id 黑名单ID
     * @return 黑名单信息
     */
	public HjBlacklist selectHjBlacklistById(Integer id);
	
	/**
     * 查询黑名单列表
     * 
     * @param hjBlacklist 黑名单信息
     * @return 黑名单集合
     */
	public List<HjBlacklist> selectHjBlacklistList(HjBlacklist hjBlacklist);
	
	/**
     * 新增黑名单
     * 
     * @param hjBlacklist 黑名单信息
     * @return 结果
     */
	public int insertHjBlacklist(HjBlacklist hjBlacklist);
	
	/**
     * 修改黑名单
     * 
     * @param hjBlacklist 黑名单信息
     * @return 结果
     */
	public int updateHjBlacklist(HjBlacklist hjBlacklist);
		
	/**
     * 删除黑名单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjBlacklistByIds(String ids);
	
}
