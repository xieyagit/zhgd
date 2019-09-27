package com.hujiang.project.zhgd.hjSystemUser.mapper;

import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.domain.UpdateUser;
import com.hujiang.project.zhgd.hjSystemUser.domain.UserParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 系统用户 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface HjSystemUserMapper 
{
	public List<HjSystemUser> getUserList(Integer projectId);
	public int updateAlias(@Param("userId")int userId,@Param("alias")String alias);
	public List<HjSystemUser> getCraneUserList(@Param("userId")Integer userId,
								   @Param("filed") String filed);

	/**
	 * 根据ID查询用户信息
	 * @param id ID
	 * @return
	 */
	public UpdateUser getUserById(@Param("id")Integer id);

	public HjSystemUser getByAlias(@Param("userId")Integer userId);

	public HjSystemUser getByUser(@Param("userName")String userName);

	/**
	 * 修改基本信息
	 * @param userName
	 * @param userPhone
	 * @return
	 */
	public int updateUserById(@Param("userName")String userName,
							  @Param("userPhone")String userPhone,
							  @Param("id")Integer id);


	/**
     * 查询系统用户信息
     * 
     * @param id 系统用户ID
     * @return 系统用户信息
     */
	public HjSystemUser selectHjSystemUserById(Integer id);
	
	/**
     * 查询系统用户列表
     * 
     * @param hjSystemUser 系统用户信息
     * @return 系统用户集合
     */
	public List<HjSystemUser> selectHjSystemUserList(HjSystemUser hjSystemUser);
	
	/**
     * 新增系统用户
     * 
     * @param hjSystemUser 系统用户信息
     * @return 结果
     */
	public int insertHjSystemUser(HjSystemUser hjSystemUser);
	
	/**
     * 修改系统用户
     * 
     * @param hjSystemUser 系统用户信息
     * @return 结果
     */
	public int updateHjSystemUser(HjSystemUser hjSystemUser);
	
	/**
     * 删除系统用户
     * 
     * @param id 系统用户ID
     * @return 结果
     */
	public int deleteHjSystemUserById(Integer id);
	
	/**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSystemUserByIds(String[] ids);


	/**
	 * 账号总条数
	 * @param userParam
	 * @return
	 */
	Integer queryCompanyUserCount(UserParam userParam);

	/**
	 * 列表账号
	 * @param userParam
	 * @return
	 */
	List<UserParam> queryCompanyUser(UserParam userParam);
}