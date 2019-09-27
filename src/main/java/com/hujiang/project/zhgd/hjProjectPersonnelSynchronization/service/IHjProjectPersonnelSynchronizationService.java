package com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.service;

import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.domain.HjProjectPersonnelSynchronization;
import java.util.List;

/**
 * 项目人员同步 服务层
 * 
 * @author hujiang
 * @date 2019-05-16
 */
public interface IHjProjectPersonnelSynchronizationService 
{
	/**
     * 查询项目人员同步信息
     * 
     * @param id 项目人员同步ID
     * @return 项目人员同步信息
     */
	public HjProjectPersonnelSynchronization selectHjProjectPersonnelSynchronizationById(Integer id);
	
	/**
     * 查询项目人员同步列表
     * 
     * @param hjProjectPersonnelSynchronization 项目人员同步信息
     * @return 项目人员同步集合
     */
	public List<HjProjectPersonnelSynchronization> selectHjProjectPersonnelSynchronizationList(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization);
	
	/**
     * 新增项目人员同步
     * 
     * @param hjProjectPersonnelSynchronization 项目人员同步信息
     * @return 结果
     */
	public int insertHjProjectPersonnelSynchronization(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization);
	
	/**
     * 修改项目人员同步
     * 
     * @param hjProjectPersonnelSynchronization 项目人员同步信息
     * @return 结果
     */
	public int updateHjProjectPersonnelSynchronization(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization);
		
	/**
     * 删除项目人员同步信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjProjectPersonnelSynchronizationByIds(String ids);
	
}
