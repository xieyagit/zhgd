package com.hujiang.project.zhgd.hjInitiator.service;

import com.hujiang.project.zhgd.hjInitiator.domain.HjInitiator;
import java.util.List;

/**
 * 发起问题记录 服务层
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public interface IHjInitiatorService 
{
	/**
     * 查询发起问题记录信息
     * 
     * @param id 发起问题记录ID
     * @return 发起问题记录信息
     */
	public HjInitiator selectHjInitiatorById(Integer id);
	
	/**
     * 查询发起问题记录列表
     * 
     * @param hjInitiator 发起问题记录信息
     * @return 发起问题记录集合
     */
	public List<HjInitiator> selectHjInitiatorList(HjInitiator hjInitiator);
	
	/**
     * 新增发起问题记录
     * 
     * @param hjInitiator 发起问题记录信息
     * @return 结果
     */
	public int insertHjInitiator(HjInitiator hjInitiator);
	
	/**
     * 修改发起问题记录
     * 
     * @param hjInitiator 发起问题记录信息
     * @return 结果
     */
	public int updateHjInitiator(HjInitiator hjInitiator);
		
	/**
     * 删除发起问题记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjInitiatorByIds(String ids);
	
}
