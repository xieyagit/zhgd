package com.hujiang.project.zhgd.hjInitiator.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjInitiator.mapper.HjInitiatorMapper;
import com.hujiang.project.zhgd.hjInitiator.domain.HjInitiator;
import com.hujiang.project.zhgd.hjInitiator.service.IHjInitiatorService;
import com.hujiang.common.support.Convert;

/**
 * 发起问题记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Service
public class HjInitiatorServiceImpl implements IHjInitiatorService 
{
	@Autowired
	private HjInitiatorMapper hjInitiatorMapper;

	/**
     * 查询发起问题记录信息
     * 
     * @param id 发起问题记录ID
     * @return 发起问题记录信息
     */
    @Override
	public HjInitiator selectHjInitiatorById(Integer id)
	{
	    return hjInitiatorMapper.selectHjInitiatorById(id);
	}
	
	/**
     * 查询发起问题记录列表
     * 
     * @param hjInitiator 发起问题记录信息
     * @return 发起问题记录集合
     */
	@Override
	public List<HjInitiator> selectHjInitiatorList(HjInitiator hjInitiator)
	{
	    return hjInitiatorMapper.selectHjInitiatorList(hjInitiator);
	}
	
    /**
     * 新增发起问题记录
     * 
     * @param hjInitiator 发起问题记录信息
     * @return 结果
     */
	@Override
	public int insertHjInitiator(HjInitiator hjInitiator)
	{
	    return hjInitiatorMapper.insertHjInitiator(hjInitiator);
	}
	
	/**
     * 修改发起问题记录
     * 
     * @param hjInitiator 发起问题记录信息
     * @return 结果
     */
	@Override
	public int updateHjInitiator(HjInitiator hjInitiator)
	{
	    return hjInitiatorMapper.updateHjInitiator(hjInitiator);
	}

	/**
     * 删除发起问题记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjInitiatorByIds(String ids)
	{
		return hjInitiatorMapper.deleteHjInitiatorByIds(Convert.toStrArray(ids));
	}
	
}
