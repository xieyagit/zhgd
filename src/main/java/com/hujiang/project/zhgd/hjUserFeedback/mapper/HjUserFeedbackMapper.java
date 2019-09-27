package com.hujiang.project.zhgd.hjUserFeedback.mapper;

import com.hujiang.project.zhgd.hjUserFeedback.domain.HjUserFeedback;
import java.util.List;	

/**
 * 用户反馈 数据层
 * 
 * @author hujiang
 * @date 2019-07-03
 */
public interface HjUserFeedbackMapper 
{
	/**
     * 查询用户反馈信息
     * 
     * @param id 用户反馈ID
     * @return 用户反馈信息
     */
	public HjUserFeedback selectHjUserFeedbackById(Integer id);
	
	/**
     * 查询用户反馈列表
     * 
     * @param hjUserFeedback 用户反馈信息
     * @return 用户反馈集合
     */
	public List<HjUserFeedback> selectHjUserFeedbackList(HjUserFeedback hjUserFeedback);
	
	/**
     * 新增用户反馈
     * 
     * @param hjUserFeedback 用户反馈信息
     * @return 结果
     */
	public int insertHjUserFeedback(HjUserFeedback hjUserFeedback);
	
	/**
     * 修改用户反馈
     * 
     * @param hjUserFeedback 用户反馈信息
     * @return 结果
     */
	public int updateHjUserFeedback(HjUserFeedback hjUserFeedback);
	
	/**
     * 删除用户反馈
     * 
     * @param id 用户反馈ID
     * @return 结果
     */
	public int deleteHjUserFeedbackById(Integer id);
	
	/**
     * 批量删除用户反馈
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjUserFeedbackByIds(String[] ids);
	
}