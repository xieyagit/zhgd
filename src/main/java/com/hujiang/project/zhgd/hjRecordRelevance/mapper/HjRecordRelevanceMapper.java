package com.hujiang.project.zhgd.hjRecordRelevance.mapper;

import com.hujiang.project.zhgd.hjRecordRelevance.domain.HjRecordRelevance;
import java.util.List;	

/**
 * 记录关联 数据层
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public interface HjRecordRelevanceMapper 
{
	/**
     * 查询记录关联信息
     * 
     * @param id 记录关联ID
     * @return 记录关联信息
     */
	public HjRecordRelevance selectHjRecordRelevanceById(Integer id);
	
	/**
     * 查询记录关联列表
     * 
     * @param hjRecordRelevance 记录关联信息
     * @return 记录关联集合
     */
	public List<HjRecordRelevance> selectHjRecordRelevanceList(HjRecordRelevance hjRecordRelevance);
	
	/**
     * 新增记录关联
     * 
     * @param hjRecordRelevance 记录关联信息
     * @return 结果
     */
	public int insertHjRecordRelevance(HjRecordRelevance hjRecordRelevance);
	
	/**
     * 修改记录关联
     * 
     * @param hjRecordRelevance 记录关联信息
     * @return 结果
     */
	public int updateHjRecordRelevance(HjRecordRelevance hjRecordRelevance);
	
	/**
     * 删除记录关联
     * 
     * @param id 记录关联ID
     * @return 结果
     */
	public int deleteHjRecordRelevanceById(Integer id);
	
	/**
     * 批量删除记录关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjRecordRelevanceByIds(String[] ids);
	
}