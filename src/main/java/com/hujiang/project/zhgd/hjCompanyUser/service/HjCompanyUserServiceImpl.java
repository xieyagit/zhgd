package com.hujiang.project.zhgd.hjCompanyUser.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjCompanyUser.mapper.HjCompanyUserMapper;
import com.hujiang.project.zhgd.hjCompanyUser.domain.HjCompanyUser;
import com.hujiang.common.support.Convert;

/**
 * 公司用户 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjCompanyUserServiceImpl implements IHjCompanyUserService 
{
	@Autowired
	private HjCompanyUserMapper hjCompanyUserMapper;

	/**
     * 查询公司用户信息
     * 
     * @param id 公司用户ID
     * @return 公司用户信息
     */
    @Override
	public HjCompanyUser selectHjCompanyUserById(Integer id)
	{
	    return hjCompanyUserMapper.selectHjCompanyUserById(id);
	}
	
	/**
     * 查询公司用户列表
     * 
     * @param hjCompanyUser 公司用户信息
     * @return 公司用户集合
     */
	@Override
	public List<HjCompanyUser> selectHjCompanyUserList(HjCompanyUser hjCompanyUser)
	{
	    return hjCompanyUserMapper.selectHjCompanyUserList(hjCompanyUser);
	}
	
    /**
     * 新增公司用户
     * 
     * @param hjCompanyUser 公司用户信息
     * @return 结果
     */
	@Override
	public int insertHjCompanyUser(HjCompanyUser hjCompanyUser)
	{
	    return hjCompanyUserMapper.insertHjCompanyUser(hjCompanyUser);
	}
	
	/**
     * 修改公司用户
     * 
     * @param hjCompanyUser 公司用户信息
     * @return 结果
     */
	@Override
	public int updateHjCompanyUser(HjCompanyUser hjCompanyUser)
	{
	    return hjCompanyUserMapper.updateHjCompanyUser(hjCompanyUser);
	}

	/**
     * 删除公司用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjCompanyUserByIds(String ids)
	{
		return hjCompanyUserMapper.deleteHjCompanyUserByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除公司用户信息
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public void deleteHjCompanyUserId(Integer id) {
		hjCompanyUserMapper.deleteHjCompanyUserId(id);
	}

}
