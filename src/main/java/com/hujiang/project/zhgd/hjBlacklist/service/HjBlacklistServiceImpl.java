package com.hujiang.project.zhgd.hjBlacklist.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjBlacklist.mapper.HjBlacklistMapper;
import com.hujiang.project.zhgd.hjBlacklist.domain.HjBlacklist;
import com.hujiang.project.zhgd.hjBlacklist.service.IHjBlacklistService;
import com.hujiang.common.support.Convert;

/**
 * 黑名单 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-25
 */
@Service
public class HjBlacklistServiceImpl implements IHjBlacklistService 
{
	@Autowired
	private HjBlacklistMapper hjBlacklistMapper;

	/**
     * 查询黑名单信息
     * 
     * @param id 黑名单ID
     * @return 黑名单信息
     */
    @Override
	public HjBlacklist selectHjBlacklistById(Integer id)
	{
	    return hjBlacklistMapper.selectHjBlacklistById(id);
	}
	
	/**
     * 查询黑名单列表
     * 
     * @param hjBlacklist 黑名单信息
     * @return 黑名单集合
     */
	@Override
	public List<HjBlacklist> selectHjBlacklistList(HjBlacklist hjBlacklist)
	{
	    return hjBlacklistMapper.selectHjBlacklistList(hjBlacklist);
	}
	
    /**
     * 新增黑名单
     * 
     * @param hjBlacklist 黑名单信息
     * @return 结果
     */
	@Override
	public int insertHjBlacklist(HjBlacklist hjBlacklist)
	{
	    return hjBlacklistMapper.insertHjBlacklist(hjBlacklist);
	}
	
	/**
     * 修改黑名单
     * 
     * @param hjBlacklist 黑名单信息
     * @return 结果
     */
	@Override
	public int updateHjBlacklist(HjBlacklist hjBlacklist)
	{
	    return hjBlacklistMapper.updateHjBlacklist(hjBlacklist);
	}

	/**
     * 删除黑名单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjBlacklistByIds(String ids)
	{
		return hjBlacklistMapper.deleteHjBlacklistByIds(Convert.toStrArray(ids));
	}
	
}
