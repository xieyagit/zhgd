package com.hujiang.project.zhgd.hjConstructionUser.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.hjConstructionUser.mapper.HjConstructionUserMapper;
import com.hujiang.project.zhgd.hjConstructionUser.domain.HjConstructionUser;
import com.hujiang.project.zhgd.hjConstructionUser.service.IHjConstructionUserService;
import com.hujiang.common.support.Convert;

/**
 * 分包单位与用户关系 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-10
 */
@Service
public class HjConstructionUserServiceImpl implements IHjConstructionUserService 
{
	@Autowired
	private HjConstructionUserMapper hjConstructionUserMapper;

	/**
     * 查询分包单位与用户关系信息
     * 
     * @param id 分包单位与用户关系ID
     * @return 分包单位与用户关系信息
     */
    @Override
	public HjConstructionUser selectHjConstructionUserById(Integer id)
	{
	    return hjConstructionUserMapper.selectHjConstructionUserById(id);
	}
	
	/**
     * 查询分包单位与用户关系列表
     * 
     * @param hjConstructionUser 分包单位与用户关系信息
     * @return 分包单位与用户关系集合
     */
	@Override
	public List<HjConstructionUser> selectHjConstructionUserList(HjConstructionUser hjConstructionUser)
	{
	    return hjConstructionUserMapper.selectHjConstructionUserList(hjConstructionUser);
	}
	
    /**
     * 新增分包单位与用户关系
     * 
     * @param hjConstructionUser 分包单位与用户关系信息
     * @return 结果
     */
	@Override
	public int insertHjConstructionUser(HjConstructionUser hjConstructionUser)
	{
	    return hjConstructionUserMapper.insertHjConstructionUser(hjConstructionUser);
	}
	
	/**
     * 修改分包单位与用户关系
     * 
     * @param hjConstructionUser 分包单位与用户关系信息
     * @return 结果
     */
	@Override
	public int updateHjConstructionUser(HjConstructionUser hjConstructionUser)
	{
	    return hjConstructionUserMapper.updateHjConstructionUser(hjConstructionUser);
	}

	/**
     * 删除分包单位与用户关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjConstructionUserByIds(String ids)
	{
		return hjConstructionUserMapper.deleteHjConstructionUserByIds(Convert.toStrArray(ids));
	}
	
}
