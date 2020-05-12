package com.hujiang.project.zhgd.hjNanhuId.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjNanhuId.mapper.HjNanhuIdMapper;
import com.hujiang.project.zhgd.hjNanhuId.domain.HjNanhuId;
import com.hujiang.project.zhgd.hjNanhuId.service.IHjNanhuIdService;
import com.hujiang.common.support.Convert;

/**
 * 南湖项目id-我系统项目id 服务层实现
 * 
 * @author hujiang
 * @date 2020-04-27
 */
@Service
public class HjNanhuIdServiceImpl implements IHjNanhuIdService 
{
	@Autowired
	private HjNanhuIdMapper hjNanhuIdMapper;

	/**
     * 查询南湖项目id-我系统项目id信息
     * 
     * @param id 南湖项目id-我系统项目idID
     * @return 南湖项目id-我系统项目id信息
     */
    @Override
	public HjNanhuId selectHjNanhuIdById(Integer id)
	{
	    return hjNanhuIdMapper.selectHjNanhuIdById(id);
	}
	
	/**
     * 查询南湖项目id-我系统项目id列表
     * 
     * @param hjNanhuId 南湖项目id-我系统项目id信息
     * @return 南湖项目id-我系统项目id集合
     */
	@Override
	public List<HjNanhuId> selectHjNanhuIdList(HjNanhuId hjNanhuId)
	{
	    return hjNanhuIdMapper.selectHjNanhuIdList(hjNanhuId);
	}
	
    /**
     * 新增南湖项目id-我系统项目id
     * 
     * @param hjNanhuId 南湖项目id-我系统项目id信息
     * @return 结果
     */
	@Override
	public int insertHjNanhuId(HjNanhuId hjNanhuId)
	{
	    return hjNanhuIdMapper.insertHjNanhuId(hjNanhuId);
	}
	
	/**
     * 修改南湖项目id-我系统项目id
     * 
     * @param hjNanhuId 南湖项目id-我系统项目id信息
     * @return 结果
     */
	@Override
	public int updateHjNanhuId(HjNanhuId hjNanhuId)
	{
	    return hjNanhuIdMapper.updateHjNanhuId(hjNanhuId);
	}

	/**
     * 删除南湖项目id-我系统项目id对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjNanhuIdByIds(String ids)
	{
		return hjNanhuIdMapper.deleteHjNanhuIdByIds(Convert.toStrArray(ids));
	}
	
}
