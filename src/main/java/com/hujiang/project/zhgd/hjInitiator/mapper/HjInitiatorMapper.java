package com.hujiang.project.zhgd.hjInitiator.mapper;

import com.hujiang.project.zhgd.hjInitiator.domain.HjInitiator;
import java.util.List;	

/**
 * 发起问题记录 数据层
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public interface HjInitiatorMapper 
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
     * 删除发起问题记录
     * 
     * @param id 发起问题记录ID
     * @return 结果
     */
	public int deleteHjInitiatorById(Integer id);
	
	/**
     * 批量删除发起问题记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjInitiatorByIds(String[] ids);
	
}