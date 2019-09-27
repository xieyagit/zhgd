package com.hujiang.project.zhgd.hjCompanyUser.mapper;

import com.hujiang.project.zhgd.hjCompanyUser.domain.HjCompanyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公司用户 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjCompanyUserMapper 
{
	/**
     * 查询公司用户信息
     * 
     * @param id 公司用户ID
     * @return 公司用户信息
     */
	public HjCompanyUser selectHjCompanyUserById(Integer id);
	
	/**
     * 查询公司用户列表
     * 
     * @param hjCompanyUser 公司用户信息
     * @return 公司用户集合
     */
	public List<HjCompanyUser> selectHjCompanyUserList(HjCompanyUser hjCompanyUser);
	
	/**
     * 新增公司用户
     * 
     * @param hjCompanyUser 公司用户信息
     * @return 结果
     */
	public int insertHjCompanyUser(HjCompanyUser hjCompanyUser);
	
	/**
     * 修改公司用户
     * 
     * @param hjCompanyUser 公司用户信息
     * @return 结果
     */
	public int updateHjCompanyUser(HjCompanyUser hjCompanyUser);
	
	/**
     * 删除公司用户
     * 
     * @param id 公司用户ID
     * @return 结果
     */
	public int deleteHjCompanyUserById(Integer id);
	
	/**
     * 批量删除公司用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjCompanyUserByIds(String[] ids);

	/**
	 * 删除公司用户信息
	 *
	 * @param parseInt 人员id
	 * @return 结果
	 */
	void deleteHjCompanyUserId(@Param(value = "parseInt") Integer parseInt);

}