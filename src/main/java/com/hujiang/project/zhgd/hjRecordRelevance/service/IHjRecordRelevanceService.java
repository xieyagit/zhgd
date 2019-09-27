package com.hujiang.project.zhgd.hjRecordRelevance.service;

import com.hujiang.project.zhgd.hjRecordRelevance.domain.HjRecordRelevance;
import java.util.List;

/**
 * 记录关联 服务层
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public interface IHjRecordRelevanceService 
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
     * 删除记录关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjRecordRelevanceByIds(String ids);
	
}
