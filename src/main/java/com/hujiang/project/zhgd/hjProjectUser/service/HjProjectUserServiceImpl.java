package com.hujiang.project.zhgd.hjProjectUser.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjProjectUser.mapper.HjProjectUserMapper;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.common.support.Convert;

/**
 * 项目用户 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjProjectUserServiceImpl implements IHjProjectUserService 
{
	@Autowired
	private HjProjectUserMapper hjProjectUserMapper;

	/**
     * 查询项目用户信息
     * 
     * @param id 项目用户ID
     * @return 项目用户信息
     */
    @Override
	public HjProjectUser selectHjProjectUserById(Integer id)
	{
	    return hjProjectUserMapper.selectHjProjectUserById(id);
	}
	
	/**
     * 查询项目用户列表
     * 
     * @param hjProjectUser 项目用户信息
     * @return 项目用户集合
     */
	@Override
	public List<HjProjectUser> selectHjProjectUserList(HjProjectUser hjProjectUser)
	{
	    return hjProjectUserMapper.selectHjProjectUserList(hjProjectUser);
	}
	
    /**
     * 新增项目用户
     * 
     * @param hjProjectUser 项目用户信息
     * @return 结果
     */
	@Override
	public int insertHjProjectUser(HjProjectUser hjProjectUser)
	{
	    return hjProjectUserMapper.insertHjProjectUser(hjProjectUser);
	}
	
	/**
     * 修改项目用户
     * 
     * @param hjProjectUser 项目用户信息
     * @return 结果
     */
	@Override
	public int updateHjProjectUser(HjProjectUser hjProjectUser)
	{
	    return hjProjectUserMapper.updateHjProjectUser(hjProjectUser);
	}

	/**
     * 删除项目用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjProjectUserByIds(String ids)
	{
		return hjProjectUserMapper.deleteHjProjectUserByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除项目信息
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public void deleteHjProjectUserIds(Integer id) {
		hjProjectUserMapper.deleteHjProjectUserIds(id);
	}
	
}
