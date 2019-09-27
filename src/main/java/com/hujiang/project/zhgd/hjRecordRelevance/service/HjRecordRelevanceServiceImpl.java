package com.hujiang.project.zhgd.hjRecordRelevance.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjRecordRelevance.mapper.HjRecordRelevanceMapper;
import com.hujiang.project.zhgd.hjRecordRelevance.domain.HjRecordRelevance;
import com.hujiang.project.zhgd.hjRecordRelevance.service.IHjRecordRelevanceService;
import com.hujiang.common.support.Convert;

/**
 * 记录关联 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Service
public class HjRecordRelevanceServiceImpl implements IHjRecordRelevanceService 
{
	@Autowired
	private HjRecordRelevanceMapper hjRecordRelevanceMapper;

	/**
     * 查询记录关联信息
     * 
     * @param id 记录关联ID
     * @return 记录关联信息
     */
    @Override
	public HjRecordRelevance selectHjRecordRelevanceById(Integer id)
	{
	    return hjRecordRelevanceMapper.selectHjRecordRelevanceById(id);
	}
	
	/**
     * 查询记录关联列表
     * 
     * @param hjRecordRelevance 记录关联信息
     * @return 记录关联集合
     */
	@Override
	public List<HjRecordRelevance> selectHjRecordRelevanceList(HjRecordRelevance hjRecordRelevance)
	{
	    return hjRecordRelevanceMapper.selectHjRecordRelevanceList(hjRecordRelevance);
	}
	
    /**
     * 新增记录关联
     * 
     * @param hjRecordRelevance 记录关联信息
     * @return 结果
     */
	@Override
	public int insertHjRecordRelevance(HjRecordRelevance hjRecordRelevance)
	{
	    return hjRecordRelevanceMapper.insertHjRecordRelevance(hjRecordRelevance);
	}
	
	/**
     * 修改记录关联
     * 
     * @param hjRecordRelevance 记录关联信息
     * @return 结果
     */
	@Override
	public int updateHjRecordRelevance(HjRecordRelevance hjRecordRelevance)
	{
	    return hjRecordRelevanceMapper.updateHjRecordRelevance(hjRecordRelevance);
	}

	/**
     * 删除记录关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjRecordRelevanceByIds(String ids)
	{
		return hjRecordRelevanceMapper.deleteHjRecordRelevanceByIds(Convert.toStrArray(ids));
	}
	
}
