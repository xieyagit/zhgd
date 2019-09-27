package com.hujiang.project.zhgd.hjUserFeedback.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.hjUserFeedback.mapper.HjUserFeedbackMapper;
import com.hujiang.project.zhgd.hjUserFeedback.domain.HjUserFeedback;
import com.hujiang.common.support.Convert;

/**
 * 用户反馈 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-03
 */
@Service
public class HjUserFeedbackServiceImpl implements IHjUserFeedbackService 
{
	@Autowired
	private HjUserFeedbackMapper hjUserFeedbackMapper;

	/**
     * 查询用户反馈信息
     * 
     * @param id 用户反馈ID
     * @return 用户反馈信息
     */
    @Override
	public HjUserFeedback selectHjUserFeedbackById(Integer id)
	{
	    return hjUserFeedbackMapper.selectHjUserFeedbackById(id);
	}
	
	/**
     * 查询用户反馈列表
     * 
     * @param hjUserFeedback 用户反馈信息
     * @return 用户反馈集合
     */
	@Override
	public List<HjUserFeedback> selectHjUserFeedbackList(HjUserFeedback hjUserFeedback)
	{
	    return hjUserFeedbackMapper.selectHjUserFeedbackList(hjUserFeedback);
	}
	
    /**
     * 新增用户反馈
     * 
     * @param hjUserFeedback 用户反馈信息
     * @return 结果
     */
	@Override
	public int insertHjUserFeedback(HjUserFeedback hjUserFeedback)
	{
	    return hjUserFeedbackMapper.insertHjUserFeedback(hjUserFeedback);
	}
	
	/**
     * 修改用户反馈
     * 
     * @param hjUserFeedback 用户反馈信息
     * @return 结果
     */
	@Override
	public int updateHjUserFeedback(HjUserFeedback hjUserFeedback)
	{
	    return hjUserFeedbackMapper.updateHjUserFeedback(hjUserFeedback);
	}

	/**
     * 删除用户反馈对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjUserFeedbackByIds(String ids)
	{
		return hjUserFeedbackMapper.deleteHjUserFeedbackByIds(Convert.toStrArray(ids));
	}
	
}
