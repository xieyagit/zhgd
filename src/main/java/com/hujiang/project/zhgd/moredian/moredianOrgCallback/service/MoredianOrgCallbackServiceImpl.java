package com.hujiang.project.zhgd.moredian.moredianOrgCallback.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.moredian.moredianOrgCallback.domain.MoredianOrgCallback;
import com.hujiang.project.zhgd.moredian.moredianOrgCallback.mapper.MoredianOrgCallbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 回调 服务层实现
 * 
 * @author hujiang
 * @date 2019-05-13
 */
@Service
@Transactional
public class MoredianOrgCallbackServiceImpl implements IMoredianOrgCallbackService 
{
	@Autowired
	private MoredianOrgCallbackMapper moredianOrgCallbackMapper;

	/**
     * 查询回调信息
     * 
     * @param id 回调ID
     * @return 回调信息
     */
    @Override
	public MoredianOrgCallback selectMoredianOrgCallbackById(Integer id)
	{
	    return moredianOrgCallbackMapper.selectMoredianOrgCallbackById(id);
	}
	
	/**
     * 查询回调列表
     * 
     * @param moredianOrgCallback 回调信息
     * @return 回调集合
     */
	@Override
	public List<MoredianOrgCallback> selectMoredianOrgCallbackList(MoredianOrgCallback moredianOrgCallback)
	{
	    return moredianOrgCallbackMapper.selectMoredianOrgCallbackList(moredianOrgCallback);
	}
	
    /**
     * 新增回调
     * 
     * @param moredianOrgCallback 回调信息
     * @return 结果
     */
	@Override
	public int insertMoredianOrgCallback(MoredianOrgCallback moredianOrgCallback)
	{
	    return moredianOrgCallbackMapper.insertMoredianOrgCallback(moredianOrgCallback);
	}
	
	/**
     * 修改回调
     * 
     * @param moredianOrgCallback 回调信息
     * @return 结果
     */
	@Override
	public int updateMoredianOrgCallback(MoredianOrgCallback moredianOrgCallback)
	{
	    return moredianOrgCallbackMapper.updateMoredianOrgCallback(moredianOrgCallback);
	}

	/**
     * 删除回调对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoredianOrgCallbackByIds(String ids)
	{
		return moredianOrgCallbackMapper.deleteMoredianOrgCallbackByIds(Convert.toStrArray(ids));
	}
	
}
