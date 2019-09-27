package com.hujiang.project.zhgd.hjUserRole.service;

import java.util.List;
import java.util.Map;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.project.zhgd.hjSystemRole.mapper.HjSystemRoleMapper;
import com.hujiang.project.zhgd.hjUserRole.domain.UserRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjUserRole.mapper.HjUserRoleMapper;
import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.common.support.Convert;
import sun.misc.Cache;

/**
 * 用户-角色 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjUserRoleServiceImpl implements IHjUserRoleService 
{
	@Autowired
	private HjUserRoleMapper hjUserRoleMapper;

	@Autowired
	private HjSystemRoleMapper hjSystemRoleMapper; // 角色

	@Override
	public List<HjUserRole> getHjUserRoleListByIds(Integer[] ids) {
		return hjUserRoleMapper.getHjUserRoleListByIds(ids);
	}

	/**
     * 查询用户-角色信息
     * 
     * @param id 用户-角色ID
     * @return 用户-角色信息
     */
    @Override
	public HjUserRole selectHjUserRoleById(Integer id)
	{
	    return hjUserRoleMapper.selectHjUserRoleById(id);
	}
	
	/**
     * 查询用户-角色列表
     * 
     * @param hjUserRole 用户-角色信息
     * @return 用户-角色集合
     */
	@Override
	public List<HjUserRole> selectHjUserRoleList(HjUserRole hjUserRole)
	{
	    return hjUserRoleMapper.selectHjUserRoleList(hjUserRole);
	}
	
    /**
     * 新增用户-角色
     * 
     * @param hjUserRole 用户-角色信息
     * @return 结果
     */
	@Override
	public int insertHjUserRole(HjUserRole hjUserRole)
	{
	    return hjUserRoleMapper.insertHjUserRole(hjUserRole);
	}
	
	/**
     * 修改用户-角色
     * 
     * @param hjUserRole 用户-角色信息
     * @return 结果
     */
	@Override
	public int updateHjUserRole(HjUserRole hjUserRole)
	{
	    return hjUserRoleMapper.updateHjUserRole(hjUserRole);
	}

	/**
     * 删除用户-角色对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjUserRoleByIds(String ids)
	{
		return hjUserRoleMapper.deleteHjUserRoleByIds(Convert.toStrArray(ids));
	}

	/**
	 * 用户添加角色
	 * @param userId 用户id
	 * @param ids 角色id 字符串
	 */
	@Override
	public Map<String, Object> insertUserRole(Integer userId, String ids) {
		try {
			hjUserRoleMapper.deleteHjUserId(userId); // 删除用户角色表数据
			String [] id = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				HjUserRole hjUserRole = new HjUserRole();
				hjUserRole.setRoleId(Integer.parseInt(id[i]));
				hjUserRole.setUserId(userId);
				hjUserRoleMapper.insertHjUserRole(hjUserRole);
			}
            return AjaxResult.success("添加成功！");
		} catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"添加失败！");
		}
	}

	/**
	 * 用户拥有角色
	 * @param userRoleParam
	 */
	@Override
	public Map<String, Object> queryUserRole(UserRoleParam userRoleParam){

		try {
			List<HjSystemRole> hjSystemRoleList = hjSystemRoleMapper.queryUserRole(userRoleParam);
			return AjaxResult.success(hjSystemRoleList);
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败！");
		}
	}




}
