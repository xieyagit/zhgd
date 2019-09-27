package com.hujiang.project.zhgd.hjSafetyProblemgrade.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.hjSafetyProblemgrade.mapper.HjSafetyProblemgradeMapper;
import com.hujiang.project.zhgd.hjSafetyProblemgrade.domain.HjSafetyProblemgrade;
import com.hujiang.project.zhgd.hjSafetyProblemgrade.service.IHjSafetyProblemgradeService;
import com.hujiang.common.support.Convert;

/**
 * 问题级别 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-10
 */
@Service
public class HjSafetyProblemgradeServiceImpl implements IHjSafetyProblemgradeService 
{
	@Autowired
	private HjSafetyProblemgradeMapper hjSafetyProblemgradeMapper;

	/**
     * 查询问题级别信息
     * 
     * @param id 问题级别ID
     * @return 问题级别信息
     */
    @Override
	public HjSafetyProblemgrade selectHjSafetyProblemgradeById(Integer id)
	{
	    return hjSafetyProblemgradeMapper.selectHjSafetyProblemgradeById(id);
	}
	
	/**
     * 查询问题级别列表
     * 
     * @param hjSafetyProblemgrade 问题级别信息
     * @return 问题级别集合
     */
	@Override
	public List<HjSafetyProblemgrade> selectHjSafetyProblemgradeList(HjSafetyProblemgrade hjSafetyProblemgrade)
	{
	    return hjSafetyProblemgradeMapper.selectHjSafetyProblemgradeList(hjSafetyProblemgrade);
	}
	
    /**
     * 新增问题级别
     * 
     * @param hjSafetyProblemgrade 问题级别信息
     * @return 结果
     */
	@Override
	public int insertHjSafetyProblemgrade(HjSafetyProblemgrade hjSafetyProblemgrade)
	{
	    return hjSafetyProblemgradeMapper.insertHjSafetyProblemgrade(hjSafetyProblemgrade);
	}
	
	/**
     * 修改问题级别
     * 
     * @param hjSafetyProblemgrade 问题级别信息
     * @return 结果
     */
	@Override
	public int updateHjSafetyProblemgrade(HjSafetyProblemgrade hjSafetyProblemgrade)
	{
	    return hjSafetyProblemgradeMapper.updateHjSafetyProblemgrade(hjSafetyProblemgrade);
	}

	/**
     * 删除问题级别对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSafetyProblemgradeByIds(String ids)
	{
		return hjSafetyProblemgradeMapper.deleteHjSafetyProblemgradeByIds(Convert.toStrArray(ids));
	}
	
}
