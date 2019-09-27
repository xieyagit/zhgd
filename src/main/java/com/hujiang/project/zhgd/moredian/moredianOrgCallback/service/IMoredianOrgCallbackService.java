package com.hujiang.project.zhgd.moredian.moredianOrgCallback.service;

import com.hujiang.project.zhgd.moredian.moredianOrgCallback.domain.MoredianOrgCallback;

import java.util.List;

/**
 * 回调 服务层
 * 
 * @author hujiang
 * @date 2019-05-13
 */
public interface IMoredianOrgCallbackService 
{
	/**
     * 查询回调信息
     * 
     * @param id 回调ID
     * @return 回调信息
     */
	public MoredianOrgCallback selectMoredianOrgCallbackById(Integer id);
	
	/**
     * 查询回调列表
     * 
     * @param moredianOrgCallback 回调信息
     * @return 回调集合
     */
	public List<MoredianOrgCallback> selectMoredianOrgCallbackList(MoredianOrgCallback moredianOrgCallback);
	
	/**
     * 新增回调
     * 
     * @param moredianOrgCallback 回调信息
     * @return 结果
     */
	public int insertMoredianOrgCallback(MoredianOrgCallback moredianOrgCallback);
	
	/**
     * 修改回调
     * 
     * @param moredianOrgCallback 回调信息
     * @return 结果
     */
	public int updateMoredianOrgCallback(MoredianOrgCallback moredianOrgCallback);
		
	/**
     * 删除回调信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoredianOrgCallbackByIds(String ids);
	
}
