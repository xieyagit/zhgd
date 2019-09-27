package com.hujiang.project.zhgd.hjSafetyProblemgrade.service;

import com.hujiang.project.zhgd.hjSafetyProblemgrade.domain.HjSafetyProblemgrade;
import java.util.List;

/**
 * 问题级别 服务层
 * 
 * @author hujiang
 * @date 2019-07-10
 */
public interface IHjSafetyProblemgradeService 
{
	/**
     * 查询问题级别信息
     * 
     * @param id 问题级别ID
     * @return 问题级别信息
     */
	public HjSafetyProblemgrade selectHjSafetyProblemgradeById(Integer id);
	
	/**
     * 查询问题级别列表
     * 
     * @param hjSafetyProblemgrade 问题级别信息
     * @return 问题级别集合
     */
	public List<HjSafetyProblemgrade> selectHjSafetyProblemgradeList(HjSafetyProblemgrade hjSafetyProblemgrade);
	
	/**
     * 新增问题级别
     * 
     * @param hjSafetyProblemgrade 问题级别信息
     * @return 结果
     */
	public int insertHjSafetyProblemgrade(HjSafetyProblemgrade hjSafetyProblemgrade);
	
	/**
     * 修改问题级别
     * 
     * @param hjSafetyProblemgrade 问题级别信息
     * @return 结果
     */
	public int updateHjSafetyProblemgrade(HjSafetyProblemgrade hjSafetyProblemgrade);
		
	/**
     * 删除问题级别信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSafetyProblemgradeByIds(String ids);
	
}
