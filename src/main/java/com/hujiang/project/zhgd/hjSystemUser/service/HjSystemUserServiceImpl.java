package com.hujiang.project.zhgd.hjSystemUser.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.mapper.HjCompanyHierarchyMapper;
import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import com.hujiang.project.zhgd.hjCompanyProject.mapper.HjCompanyProjectMapper;
import com.hujiang.project.zhgd.hjCompanyUser.domain.HjCompanyUser;
import com.hujiang.project.zhgd.hjCompanyUser.mapper.HjCompanyUserMapper;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.mapper.HjProjectMapper;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.mapper.HjProjectUserMapper;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.mapper.HjRolePrivilegesMapper;
import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.project.zhgd.hjSystemRole.mapper.HjSystemRoleMapper;
import com.hujiang.project.zhgd.hjSystemUser.domain.*;
import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.project.zhgd.hjUserRole.mapper.HjUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjSystemUser.mapper.HjSystemUserMapper;
import com.hujiang.common.support.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjSystemUserServiceImpl implements IHjSystemUserService 
{
	@Autowired
	private HjSystemUserMapper hjSystemUserMapper;

	@Autowired
	private HjSystemRoleMapper hjSystemRoleMapper;

	@Autowired
	private HjProjectUserMapper hjProjectUserMapper; // 项目人员

	@Autowired
	private HjCompanyUserMapper hjCompanyUserMapper; // 公司人员

	@Autowired
	private HjUserRoleMapper hjUserRoleMapper; // 用户角色

	@Autowired
	private HjCompanyHierarchyMapper hjCompanyHierarchyMapper; // 公司层级

	@Autowired
	private HjCompanyProjectMapper hjCompanyProjectMapper; // 公司项目

	@Autowired
	private HjProjectMapper hjProjectMapper; // 公司项目

	@Autowired
	private HjRolePrivilegesMapper hjRolePrivilegesMapper;

	@Override
	public List<HjSystemUser> getUserList(Integer projectId) {
		return hjSystemUserMapper.getUserList(projectId);
	}

	@Override
	public List<HjSystemUser> getCraneUserList(Integer userId, String filed) {
		return hjSystemUserMapper.getCraneUserList(userId,filed);
	}


	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	@Override
	public UpdateUser getUserById(Integer id) { return hjSystemUserMapper.getUserById(id);}

	@Override
	public HjSystemUser getByAlias(Integer userId) {
		return hjSystemUserMapper.getByAlias(userId);
	}

	@Override
	public int updateAlias(int userId, String alias) {
		return hjSystemUserMapper.updateAlias(userId, alias);
	}

	@Override
	public HjSystemUser getByUser(String userName) {
		return hjSystemUserMapper.getByUser(userName);
	}

	/**
	 * 修改用户基本信息
	 * @param userName
	 * @param userPhone
	 * @return
	 */
	@Override
	public int updateUserById(String userName,  String userPhone,Integer id) {
		return hjSystemUserMapper.updateUserById(userName, userPhone,id);
	}

	/**
     * 查询系统用户信息
     * 
     * @param id 系统用户ID
     * @return 系统用户信息
     */
    @Override
	public HjSystemUser selectHjSystemUserById(Integer id)
	{
	    return hjSystemUserMapper.selectHjSystemUserById(id);
	}
	
	/**
     * 查询系统用户列表
     * 
     * @param hjSystemUser 系统用户信息
     * @return 系统用户集合
     */
	@Override
	public List<HjSystemUser> selectHjSystemUserList(HjSystemUser hjSystemUser)
	{
	    return hjSystemUserMapper.selectHjSystemUserList(hjSystemUser);
	}
	
    /**
     * 新增系统用户
     * 
     * @param hjSystemUser 系统用户信息
     * @return 结果
     */
	@Override
	public int insertHjSystemUser(HjSystemUser hjSystemUser)
	{
	    return hjSystemUserMapper.insertHjSystemUser(hjSystemUser);
	}
	
	/**
     * 修改系统用户
     * 
     * @param hjSystemUser 系统用户信息
     * @return 结果
     */
	@Override
	public int updateHjSystemUser(HjSystemUser hjSystemUser)
	{
	    return hjSystemUserMapper.updateHjSystemUser(hjSystemUser);
	}

	/**
     * 删除系统用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSystemUserByIds(String ids)
	{
		return hjSystemUserMapper.deleteHjSystemUserByIds(Convert.toStrArray(ids));
	}

	/**
	 * 创建用户 查看账号是否存在
	 * @param user
	 * @return
	 */
	@Override
	public Map<String, Object> selectSystemUser(HjSystemUser user) {

		HjSystemUser hjSystemUser = new HjSystemUser();
		hjSystemUser.setUserAccount(user.getUserAccount());
		// 判断账户是否存在
		List<HjSystemUser> hjSystemUserList = hjSystemUserMapper.selectHjSystemUserList(hjSystemUser);
		if(hjSystemUserList.size() > 0){
			// 账户已存在
			return AjaxResult.error(-1,"账号已存在，请更改账号！");
		}
		return AjaxResult.success("成功！");
	}

	/**
	 * 创建账号
	 * @param systemUserParam
	 * @return
	 */
	@Override
	public Map<String, Object> insertSystemUser(SystemUserParam systemUserParam) {
		try {
			HjSystemUser user = this.insertUser(systemUserParam);
			hjSystemUserMapper.insertHjSystemUser(user);
			if(systemUserParam.getUserType() == 2){
				// 添加到项目人员
				HjProjectUser hjProjectUser = new HjProjectUser();
				hjProjectUser.setUserId(user.getId());
				hjProjectUser.setProjectId(systemUserParam.getProjectOrCompanyId());
				hjProjectUserMapper.insertHjProjectUser(hjProjectUser);

				if(systemUserParam.getPrivilegesId() != null && !systemUserParam.getPrivilegesId().equals("")){
					// 创建角色
					HjSystemRole hjSystemRole = new HjSystemRole();
					hjSystemRole.setRoleName("管理员");
					hjSystemRole.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					hjSystemRole.setHierarchy(2);
					hjSystemRoleMapper.insertHjSystemRole(hjSystemRole);
					String [] id = systemUserParam.getPrivilegesId().split(",");
					for (int i = 0; i < id.length; i++) {
						HjRolePrivileges hjRolePrivileges = new HjRolePrivileges();
						hjRolePrivileges.setRoleId(hjSystemRole.getId());
						hjRolePrivileges.setPrivilegesId(Integer.parseInt(id[i]));
						hjRolePrivilegesMapper.insertHjRolePrivileges(hjRolePrivileges);
					}
				}
			}else {
				// 添加到公司人员
				HjCompanyUser hjCompanyUser = new HjCompanyUser();
				hjCompanyUser.setUserId(user.getId());
				hjCompanyUser.setCompanyId(systemUserParam.getProjectOrCompanyId());
				hjCompanyUserMapper.insertHjCompanyUser(hjCompanyUser);
			}

			if(systemUserParam.getIds() != null && !systemUserParam.getIds().equals("")){
				String [] id = systemUserParam.getIds().split(",");
				for (int i = 0; i < id.length; i++) {
					HjUserRole hjUserRole = new HjUserRole();
					hjUserRole.setUserId(user.getId());
					hjUserRole.setRoleId(Integer.parseInt(id[i]));
					hjUserRoleMapper.insertHjUserRole(hjUserRole);
				}
			}
			return AjaxResult.success("创建成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"创建失败！");
		}

	}


	/**
	 * 修改账号
	 * @param hjSystemUser
	 * @return
	 */
	@Override
	public Map<String, Object> updateSystemUser(HjSystemUser hjSystemUser) {

		try {
			hjSystemUserMapper.updateHjSystemUser(hjSystemUser);
			return AjaxResult.success("修改成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"修改失败！");
		}
	}

	/**
	 * 删除账号
	 * @param ids 角色id(1,2,3)
	 * @return
	 */
	@Override
	public Map<String, Object> deleteSystemUser(String ids) {

		try {
			String [] id = ids.split(",");
			for (int i = 0; i < id.length; i++) {

				// 查询账号类型 集团公司 或者 项目
				HjSystemUser hjSystemUser = hjSystemUserMapper.selectHjSystemUserById(Integer.parseInt(id[i]));
				if(hjSystemUser.getUserType() == 2){
					hjProjectUserMapper.deleteHjProjectUserIds(Integer.parseInt(id[i]));//删除项目用户表数据
				}else {
					hjCompanyUserMapper.deleteHjCompanyUserId(Integer.parseInt(id[i]));//删除公司用户表数据
				}
				hjSystemUserMapper.deleteHjSystemUserById(Integer.parseInt(id[i]));// 删除账号
				hjUserRoleMapper.deleteHjUserId(Integer.parseInt(id[i])); // 删除用户角色表数据
			}
			return AjaxResult.success("删除成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"删除失败！");
		}
	}

	/**
	 * 列表账号
	 * @param userParam
	 * @return
	 */
	@Override
	public Map<String, Object> querySystemUser(UserParam userParam) {

		Map<String, Object> map = new HashMap<>();
       try {
			   if(userParam.getPage()!=null && userParam.getPageSize()!=null){
				   userParam.setOffset((userParam.getPage()-1)*userParam.getPageSize());
				   userParam.setLimit(userParam.getPageSize());
			   }
			   if(userParam.getUserType() == 2){
				   userParam.setCreator(userParam.getParameterId()+"B");
			   }else {
				   userParam.setCreator(userParam.getParameterId()+"A");
			   }
		   // 总条数
		   Integer userParamCount = hjSystemUserMapper.queryCompanyUserCount(userParam);
		   // 集团 公司 账号
		   List<UserParam> userParamList = hjSystemUserMapper.queryCompanyUser(userParam);
		   if(userParamList.size() > 0) {
			   List<HjSystemUser> hjSystemUserList = HjSystemUserServiceImpl.selectUser(userParamList);
			   map.put("total", userParamCount);
			   map.put("rows", hjSystemUserList);

		   }
		   return AjaxResult.success(map);
	   }catch (Exception e){
		   e.printStackTrace();
		   return AjaxResult.error(-1,"查询失败！");
	   }
	}


	/**
	 * 修改密码
	 * @param passwordParam
	 * @return
	 */
	@Override
	public Map<String, Object> updateUserPassword(PasswordParam passwordParam) {

		try {
			HjSystemUser hjSystemUser = new HjSystemUser();
			hjSystemUser.setId(passwordParam.getId());
			hjSystemUser.setUserPassword(passwordParam.getUserPassword());
			List<HjSystemUser> hjSystemUserList = hjSystemUserMapper.selectHjSystemUserList(hjSystemUser);
			if(hjSystemUserList.size() > 0){
				if(passwordParam.getNewPassword().equals(passwordParam.getConfirmPassword())){
					HjSystemUser user = new HjSystemUser();
					user.setId(passwordParam.getId());
					user.setUserPassword(passwordParam.getNewPassword());
					hjSystemUserMapper.updateHjSystemUser(user);
					return AjaxResult.success("修改成功！");
				}else {
					return AjaxResult.error(-1,"新密码两次输入不一致！");
				}
			}else {
				return AjaxResult.error(-1,"原密码输入错误！");
			}
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"修改失败！");
		}
	}

	/**
	 * 切换项目
	 * @param userId 人员id
	 * @return
	 */
	@Override
	public Map<String, Object> queryProject(Integer userId) {

		HjSystemUser hjSystemUser = hjSystemUserMapper.selectHjSystemUserById(userId);
		List<ProjectParam> paramList = new ArrayList<>();
		if(hjSystemUser==null){
			return AjaxResult.error(-1,"没有此人员账户");
		}
		if(hjSystemUser.getUserType() == 2){
			return AjaxResult.error(-1,"没有切换权限！");
		}else {
			// 找到公司
			HjCompanyUser user = new HjCompanyUser();
			user.setUserId(userId);
			List<HjCompanyUser> list = hjCompanyUserMapper.selectHjCompanyUserList(user);
			// 找到公司下是否有公司
//			HjCompanyHierarchy hjCompanyHierarchy = new HjCompanyHierarchy(); // 公司层级表
			if(list.size()==0){
				return AjaxResult.error(-1,"没有可以切换的项目！");
			}
			String companyId=list.get(0).getCompanyId().toString();
			paramList=	hjSystemUserMapper.getUserProjectList(companyId);
//			hjCompanyHierarchy.setCompanyId(list.get(0).getCompanyId());    // 公司id 查询公司层级表关系
//			List <HjCompanyHierarchy> hjCompanyHierarchyList =  hjCompanyHierarchyMapper.selectHjCompanyHierarchyList(hjCompanyHierarchy);
//			if(hjCompanyHierarchyList.size() > 0){
//				List<ProjectParam> projectParamList = this.selectProjectName(hjCompanyHierarchyList);
//
//				if(projectParamList.size() > 0){
//					for (int i = 0; i < projectParamList.size(); i++) {
//						HjProject project = new HjProject();
//						project.setId(projectParamList.get(i).getProjectId());
//						project.setShowState(0);
//						List<HjProject> hjProjectList = hjProjectMapper.selectHjProjectList(project);
//                        if(hjProjectList.size() > 0){
//							ProjectParam projectParam = new ProjectParam();
//							projectParam.setProjectId(hjProjectList.get(0).getId());
//							projectParam.setProjectName(hjProjectList.get(0).getProjectName());
//							paramList.add(projectParam);
//						}
//					}
//				}
//			}else {
//				return AjaxResult.error(-1,"没有可以切换的项目！");
//			}
		}
		return AjaxResult.success(paramList);
	}



	public List<ProjectParam> selectProjectName(List<HjCompanyHierarchy> hjCompanyHierarchyList) {
		List<ProjectParam> projectParamList = new ArrayList<>();
		if(hjCompanyHierarchyList.size() > 0){
			for (int i = 0; i < hjCompanyHierarchyList.size(); i++) {
				HjCompanyProject hjCompanyProject = new HjCompanyProject();  // 公司项目关系表
				hjCompanyProject.setCompanyId(hjCompanyHierarchyList.get(i).getCompanyId());
				// 根据公司id 查询项目
				List<HjCompanyProject> hjCompanyProjectList = hjCompanyProjectMapper.selectHjCompanyProjectList(hjCompanyProject);
				if (hjCompanyProjectList.size() > 0) {
					for (int j = 0; j < hjCompanyProjectList.size(); j++) { // 保存项目id
						ProjectParam projectParam = new ProjectParam();
						projectParam.setProjectId(hjCompanyProjectList.get(j).getProjectId());
						projectParamList.add(projectParam);
					}
				}
				HjCompanyHierarchy hjCompanyId = new HjCompanyHierarchy();
//				hjCompanyId.setParentId(hjCompanyHierarchyList.get(i).getId());
				List<HjCompanyHierarchy> hierarchyList = hjCompanyHierarchyMapper.selectHjCompanyHierarchyList(hjCompanyId);
				if (hierarchyList.size() > 0) {
					List<ProjectParam> projectParamList1 = this.selectProjectName(hierarchyList);
					for(ProjectParam p:projectParamList1){
						projectParamList.add(p);
					}
				}
			}
		}
		return projectParamList;
	}


	/**
	 * 修改账号
	 * @param hjSystemUser
	 * @return
	 */
	@Override
	public Map<String, Object> updateUser(HjSystemUser hjSystemUser, String ids) {

		try {
			hjSystemUser.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			hjSystemUserMapper.updateHjSystemUser(hjSystemUser);
			if(ids != null && !ids.equals("")){
				hjUserRoleMapper.deleteHjUserId(hjSystemUser.getId()); // 删除用户角色表数据
				String [] id = ids.split(",");
				for (int i = 0; i < id.length; i++) {
					HjUserRole hjUserRole = new HjUserRole();
					hjUserRole.setRoleId(Integer.parseInt(id[i]));
					hjUserRole.setUserId(hjSystemUser.getId());
					hjUserRoleMapper.insertHjUserRole(hjUserRole);
				}
			}
			return AjaxResult.success("修改成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"修改失败！");
		}
	}

	/**
	 * 修改前显示
	 * @param hjSystemUser
	 * @return
	 */
	@Override
	public Map<String, Object> selectUserId(HjSystemUser hjSystemUser) {
		try {
			Map<String, Object> map = new HashMap<>();
			HjSystemUser hjSystemUser1 = hjSystemUserMapper.selectHjSystemUserById(hjSystemUser.getId());

			HjUserRole hjUserRole = new HjUserRole();
			hjUserRole.setUserId(hjSystemUser1.getId());
			List<HjUserRole> list = hjUserRoleMapper.selectHjUserRoleList(hjUserRole);
			if(list != null && list.size() > 0){
				StringBuffer strBuffer = new StringBuffer();
				for(int i=0;i<list.size();i++){
					strBuffer.append(list.get(i).getRoleId()+",");
				}
				String roleId = strBuffer.substring(0, strBuffer.lastIndexOf(","));
				map.put("roleId",roleId);
			}
			map.put("id",hjSystemUser1.getId());
			map.put("userName",hjSystemUser1.getUserName());
			map.put("userPhone",hjSystemUser1.getUserPhone());
			map.put("userAccount",hjSystemUser1.getUserAccount());
			map.put("userPassword",hjSystemUser1.getUserPassword());
			map.put("userState",hjSystemUser1.getUserState());
			map.put("userType",hjSystemUser1.getUserType());
			map.put("entry",hjSystemUser1.getEntry());
			map.put("creator",hjSystemUser1.getCreator());
			map.put("createDate",hjSystemUser1.getCreateDate());

			return AjaxResult.success(map);
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败！");
		}
	}


	/**
	 * 创建账号
	 * @param systemUserParam
	 * @return
	 */
	public static HjSystemUser insertUser(SystemUserParam systemUserParam){
		HjSystemUser user = new HjSystemUser();
		user.setUserName(systemUserParam.getUserName()); //
		user.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if(systemUserParam.getUserType() == 2){
			user.setCreator(systemUserParam.getPublicId()+"B");
		}else {
			user.setCreator(systemUserParam.getPublicId()+"A");
		}
		user.setEntry(systemUserParam.getEntry());
		user.setUserPassword(systemUserParam.getUserPassword());
		user.setUserType(systemUserParam.getUserType());
		user.setUserState(systemUserParam.getUserState());
		user.setUserAccount(systemUserParam.getUserAccount());
		user.setUserPhone(systemUserParam.getUserPhone());
		user.setUserName(systemUserParam.getUserName());
		return user;
	}


	/**
	 * 查询账号
	 * @param
	 * @return
	 */
	public static List<HjSystemUser> selectUser(List<UserParam> userParamList){
		List<HjSystemUser> hjSystemUserList = new ArrayList<>();
			for (int i = 0; i < userParamList.size(); i++) {
				HjSystemUser hjSystemUser = new HjSystemUser();
				hjSystemUser.setId(userParamList.get(i).getId());
				hjSystemUser.setUserName(userParamList.get(i).getUserName());
				hjSystemUser.setUserPhone(userParamList.get(i).getUserPhone());
				hjSystemUser.setUserAccount(userParamList.get(i).getUserAccount());
				hjSystemUser.setUserState(userParamList.get(i).getUserState());
				hjSystemUser.setCreator(userParamList.get(i).getCreator());
				hjSystemUser.setCreateDate(userParamList.get(i).getCreateDate());
				hjSystemUser.setEntry(userParamList.get(i).getEntry());
				hjSystemUser.setUserType(userParamList.get(i).getUserType());
				hjSystemUserList.add(hjSystemUser);
		}
		return hjSystemUserList;
	}


}
