package com.hujiang.project.zhgd.hjSystemUser.service;

import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjSystemUser.domain.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 系统用户 服务层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface IHjSystemUserService 
{
	public List<HjSystemUser> getUserList(Integer projectId);
	public List<HjSystemUser> getCraneUserList(Integer userId,String filed);
	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	public UpdateUser getUserById(Integer id);

	public HjSystemUser getByAlias(Integer userId);

	public int updateAlias(int userId,String alias);

	public HjSystemUser getByUser(String userName);

	/**
	 * 修改基本信息
	 * @param userName
	 * @param userPhone
	 * @return
	 */
	public int updateUserById(String userName, String userPhone,Integer id);
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
     * 删除系统用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteHjSystemUserByIds(String ids);

	/**
	 * 创建用户 查看账号是否存在
	 * @param user
	 * @return
	 */
	Map<String, Object> selectSystemUser(HjSystemUser user);

	/**
	 * 创建账号
	 * @param systemUserParam
	 * @return
	 */
	Map<String, Object> insertSystemUser(SystemUserParam systemUserParam);

	/**
	 * 修改账号
	 * @param hjSystemUser
	 * @return
	 */
	Map<String, Object> updateSystemUser(HjSystemUser hjSystemUser);

	/**
	 * 删除账号
	 * @param ids 角色id(1,2,3)
	 * @return
	 */
    Map<String, Object> deleteSystemUser(String ids);

	/**
	 * 列表账号
	 * @param userParam
	 * @return
	 */
	Map<String, Object> querySystemUser(UserParam userParam);


	/**
	 * 修改密码
	 * @param passwordParam 密码
	 * @return id 用户id
	 */
	Map<String, Object> updateUserPassword(PasswordParam passwordParam);

	/**
	 * 切换项目
	 * @param userId 人员id
	 * @return
	 */
    Map<String, Object> queryProject(Integer userId);


	/**
	 * 切换项目
	 * @param hjCompanyHierarchyList
	 * @return
	 */
	List<ProjectParam> selectProjectName(List <HjCompanyHierarchy> hjCompanyHierarchyList);

	/**
	 * 修改账号
	 * @param hjSystemUser
	 * @return
	 */
	Map<String, Object> updateUser(HjSystemUser hjSystemUser, String ids);

	/**
	 * 修改前显示
	 * @param hjSystemUser
	 * @return
	 */
	Map<String, Object> selectUserId(HjSystemUser hjSystemUser);
}
