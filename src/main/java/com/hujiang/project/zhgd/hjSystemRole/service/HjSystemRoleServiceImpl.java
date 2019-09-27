package com.hujiang.project.zhgd.hjSystemRole.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.mapper.HjRolePrivilegesMapper;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.HjSystemPrivileges;
import com.hujiang.project.zhgd.hjSystemRole.domain.RoleParam;
import com.hujiang.project.zhgd.hjUserRole.mapper.HjUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjSystemRole.mapper.HjSystemRoleMapper;
import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.common.support.Convert;


/**
 * 系统-角色 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjSystemRoleServiceImpl implements IHjSystemRoleService 
{
	@Autowired
	private HjSystemRoleMapper hjSystemRoleMapper;

	@Autowired
	private HjRolePrivilegesMapper hjRolePrivilegesMapper; // 角色权限

	@Autowired
	private HjUserRoleMapper hjUserRoleMapper;// 用户角色

	/**
     * 查询系统-角色信息
     * 
     * @param id 系统-角色ID
     * @return 系统-角色信息
     */
    @Override
	public HjSystemRole selectHjSystemRoleById(Integer id)
	{
	    return hjSystemRoleMapper.selectHjSystemRoleById(id);
	}
	
	/**
     * 查询系统-角色列表
     * 
     * @param hjSystemRole 系统-角色信息
     * @return 系统-角色集合
     */
	@Override
	public List<HjSystemRole> selectHjSystemRoleList(HjSystemRole hjSystemRole)
	{
	    return hjSystemRoleMapper.selectHjSystemRoleList(hjSystemRole);
	}
	
    /**
     * 新增系统-角色
     * 
     * @param hjSystemRole 系统-角色信息
     * @return 结果
     */
	@Override
	public int insertHjSystemRole(HjSystemRole hjSystemRole)
	{
	    return hjSystemRoleMapper.insertHjSystemRole(hjSystemRole);
	}
	
	/**
     * 修改系统-角色
     * 
     * @param hjSystemRole 系统-角色信息
     * @return 结果
     */
	@Override
	public int updateHjSystemRole(HjSystemRole hjSystemRole)
	{
	    return hjSystemRoleMapper.updateHjSystemRole(hjSystemRole);
	}

	/**
     * 删除系统-角色对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSystemRoleByIds(String ids)
	{
		return hjSystemRoleMapper.deleteHjSystemRoleByIds(Convert.toStrArray(ids));
	}

	/**
	 * 创建角色
	 * @param hjSystemRole
	 * @return
	 */
	@Override
	public Map<String, Object> insertSystemRole(HjSystemRole hjSystemRole) {

		try {
			hjSystemRole.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			hjSystemRoleMapper.insertHjSystemRole(hjSystemRole);
			return AjaxResult.success("创建成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"创建失败！");
		}
	}

	/**
	 * 修改角色
	 * @param hjSystemRole
	 * @return
	 */
	@Override
	public Map<String, Object> updateSystemRole(HjSystemRole hjSystemRole) {
		try {
			hjSystemRole.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			hjSystemRoleMapper.updateHjSystemRole(hjSystemRole);
			return AjaxResult.success("修改成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"修改失败！");
		}
	}

	/**
	 * 删除角色
	 * @param ids 角色id(1,2,3)
	 * @return
	 */
	@Override
	public Map<String, Object> deleteSystemRole(String ids) {

		try {
			String [] id = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				hjSystemRoleMapper.deleteHjSystemRoleById(Integer.parseInt(id[i])); //删除角色表数据
				hjRolePrivilegesMapper.deleteHjRoleId(Integer.parseInt(id[i])); //删除角色权限表数据
				hjUserRoleMapper.deleteHjUserRoleId(Integer.parseInt(id[i]));//删除用户角色表数据
			}
			return AjaxResult.success("删除成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"删除失败！");
		}
	}

	/**
	 * 角色列表
	 * @param systemRoleParam
	 * @return
	 */
	@Override
	public Map<String, Object> selectSystemRole(RoleParam systemRoleParam) {

		Map<String, Object> map = new HashMap<>();

		try {
			// 总条数
			Integer countRole = hjSystemRoleMapper.selectSystemRoleCount(systemRoleParam);

			if(systemRoleParam.getPage()!=null && systemRoleParam.getPageSize()!=null){
				systemRoleParam.setOffset((systemRoleParam.getPage()-1)*systemRoleParam.getPageSize());
				systemRoleParam.setLimit(systemRoleParam.getPageSize());
			}

			List<RoleParam> systemRoleParamList = hjSystemRoleMapper.selectSystemRole(systemRoleParam);

			List<HjSystemRole> hjSystemRoleList = new ArrayList<>();
			if(systemRoleParamList.size() > 0){
				for (int i = 0; i < systemRoleParamList.size(); i++) {
					HjSystemRole hjSystemRole = new HjSystemRole();
					hjSystemRole.setId(systemRoleParamList.get(i).getId());
					hjSystemRole.setBelong(systemRoleParamList.get(i).getBelong());
					hjSystemRole.setCreateDate(systemRoleParamList.get(i).getCreateDate());
					hjSystemRole.setHierarchy(systemRoleParamList.get(i).getHierarchy());
					hjSystemRole.setRemark(systemRoleParamList.get(i).getRemark());
					hjSystemRole.setDataPermission(systemRoleParamList.get(i).getDataPermission());
					hjSystemRole.setRoleName(systemRoleParamList.get(i).getRoleName());
					hjSystemRoleList.add(hjSystemRole);
				}
			}
			map.put("total", countRole);
			map.put("rows", hjSystemRoleList);
			return AjaxResult.success(map);
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败！");
		}
	}

	/**
	 * 项目 或者 集团 公司 有那些角色
	 * @param hjSystemRole
	 * @return
	 */
	@Override
	public Map<String, Object> querySystemRole(HjSystemRole hjSystemRole) {

		try {
			List<HjSystemRole> hjSystemRoleList = hjSystemRoleMapper.selectHjSystemRoleList(hjSystemRole);
			return AjaxResult.success(hjSystemRoleList);
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败！");
		}
	}

	/**
	 * 修改前显示
	 * @param hjSystemRole
	 * @return
	 */
	@Override
	public Map<String, Object> selectSystemRoleId(HjSystemRole hjSystemRole) {

		try {
			Map<String, Object> map = new HashMap<>();
			HjSystemRole hjSystemRole1 = hjSystemRoleMapper.selectHjSystemRoleById(hjSystemRole.getId());
			HjRolePrivileges hjRolePrivileges = new HjRolePrivileges();
			hjRolePrivileges.setRoleId(hjSystemRole1.getId());
			List<HjRolePrivileges> list = hjRolePrivilegesMapper.selectHjRolePrivilegesList(hjRolePrivileges);

			if(list != null && list.size() > 0){
				StringBuffer strBuffer = new StringBuffer();
				for(int i=0;i<list.size();i++){
					strBuffer.append(list.get(i).getPrivilegesId()+",");

				}
				String privilegesId = strBuffer.substring(0, strBuffer.lastIndexOf(","));
				map.put("privilegesId",privilegesId);
			}
			map.put("id",hjSystemRole1.getId());
			map.put("roleName",hjSystemRole1.getRoleName());
			map.put("remark",hjSystemRole1.getRemark());
			map.put("hierarchy",hjSystemRole1.getHierarchy());
			map.put("belong",hjSystemRole1.getBelong());
			map.put("createDate",hjSystemRole1.getCreateDate());

			return AjaxResult.success(map);
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败！");
		}
	}


	/**
	 * 创建角色
	 * @param hjSystemRole
	 * @return
	 */
	@Override
	public Map<String, Object> addSystemRole(HjSystemRole hjSystemRole, String ids) {

		try {
			hjSystemRoleMapper.insertHjSystemRole(hjSystemRole);
			if(ids != null && !ids.equals("")){
				String [] id = ids.split(",");
				for (int i = 0; i < id.length; i++) {
					HjRolePrivileges hjRolePrivileges = new HjRolePrivileges();
					hjRolePrivileges.setRoleId(hjSystemRole.getId());
					hjRolePrivileges.setPrivilegesId(Integer.parseInt(id[i]));
					hjRolePrivilegesMapper.insertHjRolePrivileges(hjRolePrivileges);
				}
			}
			return AjaxResult.success("添加成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"添加失败！");
		}
	}

	/**
	 * 修改角色
	 * @param hjSystemRole
	 * @return
	 */
	@Override
	public Map<String, Object> updateRole(HjSystemRole hjSystemRole, String ids) {

		try {
			hjSystemRole.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			hjSystemRoleMapper.updateHjSystemRole(hjSystemRole);
			if(ids != null && !ids.equals("")){
				hjRolePrivilegesMapper.deleteHjRoleId(hjSystemRole.getId()); //删除角色权限表数据
				String [] id = ids.split(",");
				for (int i = 0; i < id.length; i++) {
					HjRolePrivileges hjRolePrivileges = new HjRolePrivileges();
					hjRolePrivileges.setRoleId(hjSystemRole.getId());
					hjRolePrivileges.setPrivilegesId(Integer.parseInt(id[i]));
					hjRolePrivilegesMapper.insertHjRolePrivileges(hjRolePrivileges);
				}
			}
			return AjaxResult.success("修改成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"修改失败！");
		}
	}
}
