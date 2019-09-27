package com.hujiang.project.zhgd.hjSynchronizationInformation.mapper;

import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import java.util.List;	

/**
 * 项目两制同步 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjSynchronizationInformationMapper 
{
	/**
     * 查询项目两制同步信息
     * 
     * @param id 项目两制同步ID
     * @return 项目两制同步信息
     */
	public HjSynchronizationInformation selectHjSynchronizationInformationById(Integer id);
	
	/**
     * 查询项目两制同步列表
     * 
     * @param hjSynchronizationInformation 项目两制同步信息
     * @return 项目两制同步集合
     */
	public List<HjSynchronizationInformation> selectHjSynchronizationInformationList(HjSynchronizationInformation hjSynchronizationInformation);
	
	/**
     * 新增项目两制同步
     * 
     * @param hjSynchronizationInformation 项目两制同步信息
     * @return 结果
     */
	public int insertHjSynchronizationInformation(HjSynchronizationInformation hjSynchronizationInformation);
	
	/**
     * 修改项目两制同步
     * 
     * @param hjSynchronizationInformation 项目两制同步信息
     * @return 结果
     */
	public int updateHjSynchronizationInformation(HjSynchronizationInformation hjSynchronizationInformation);
	
	/**
     * 删除项目两制同步
     * 
     * @param id 项目两制同步ID
     * @return 结果
     */
	public int deleteHjSynchronizationInformationById(Integer id);
	
	/**
     * 批量删除项目两制同步
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSynchronizationInformationByIds(String[] ids);

	/***
	 * 获取项目同步信息
	 * @param hjSynchronizationInformation projectId & 平台名称 &
	 * @return
	 */
	public HjSynchronizationInformation getProjectSynchronizationInfoByPlatformname(HjSynchronizationInformation hjSynchronizationInformation);
}