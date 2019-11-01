package com.hujiang.project.zhgd.hjDeviceHikvision.service;

import com.hujiang.project.zhgd.hjDeviceHikvision.domain.HjDeviceHikvision;
import java.util.List;

/**
 * 海康萤石用户 服务层
 * 
 * @author hujiang
 * @date 2019-10-19
 */
public interface IHjDeviceHikvisionService 
{
	/**
     * 查询海康萤石用户信息
     * 
     * @param id 海康萤石用户ID
     * @return 海康萤石用户信息
     */
	public HjDeviceHikvision selectHjDeviceHikvisionById(Integer id);
	
	/**
     * 查询海康萤石用户列表
     * 
     * @param hjDeviceHikvision 海康萤石用户信息
     * @return 海康萤石用户集合
     */
	public List<HjDeviceHikvision> selectHjDeviceHikvisionList(HjDeviceHikvision hjDeviceHikvision);
	
	/**
     * 新增海康萤石用户
     * 
     * @param hjDeviceHikvision 海康萤石用户信息
     * @return 结果
     */
	public int insertHjDeviceHikvision(HjDeviceHikvision hjDeviceHikvision);
	
	/**
     * 修改海康萤石用户
     * 
     * @param hjDeviceHikvision 海康萤石用户信息
     * @return 结果
     */
	public int updateHjDeviceHikvision(HjDeviceHikvision hjDeviceHikvision);
		
	/**
     * 删除海康萤石用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjDeviceHikvisionByIds(String ids);
	
}
