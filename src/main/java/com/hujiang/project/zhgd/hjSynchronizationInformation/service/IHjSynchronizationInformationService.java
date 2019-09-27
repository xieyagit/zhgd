package com.hujiang.project.zhgd.hjSynchronizationInformation.service;

import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import java.util.List;

/**
 * 项目同步 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjSynchronizationInformationService 
{
	/**
     * 查询项目同步信息
     * 
     * @param id 项目同步ID
     * @return 项目同步信息
     */
	public HjSynchronizationInformation selectHjSynchronizationInformationById(Integer id);
	
	/**
     * 查询项目同步列表
     * 
     * @param hjSynchronizationInformation 项目同步信息
     * @return 项目同步集合
     */
	public List<HjSynchronizationInformation> selectHjSynchronizationInformationList(HjSynchronizationInformation hjSynchronizationInformation);
	
	/**
     * 新增项目同步
     * 
     * @param hjSynchronizationInformation 项目同步信息
     * @return 结果
     */
	public int insertHjSynchronizationInformation(HjSynchronizationInformation hjSynchronizationInformation);
	
	/**
     * 修改项目同步
     * 
     * @param hjSynchronizationInformation 项目同步信息
     * @return 结果
     */
	public int updateHjSynchronizationInformation(HjSynchronizationInformation hjSynchronizationInformation);
		
	/**
     * 删除项目同步信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSynchronizationInformationByIds(String ids);

	/***
	 * 获取项目同步信息
	 * @param projectId & 平台名称
	 * @return HjSynchronizationInformation
	 */
	public HjSynchronizationInformation getProjectSynchronizationInfoByPlatformname(Integer projectId, String platformName, String apiType);
}
