package com.hujiang.project.zhgd.hjRolePrivileges.service;

import java.util.List;
import java.util.Map;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.PrivilegesRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjRolePrivileges.mapper.HjRolePrivilegesMapper;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.common.support.Convert;

/**
 * 角色-权限 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjRolePrivilegesServiceImpl implements IHjRolePrivilegesService 
{
	@Autowired
	private HjRolePrivilegesMapper hjRolePrivilegesMapper;

	/**
     * 查询角色-权限信息
     * 
     * @param id 角色-权限ID
     * @return 角色-权限信息
     */
    @Override
	public HjRolePrivileges selectHjRolePrivilegesById(Integer id)
	{
	    return hjRolePrivilegesMapper.selectHjRolePrivilegesById(id);
	}
	
	/**
     * 查询角色-权限列表
     * 
     * @param hjRolePrivileges 角色-权限信息
     * @return 角色-权限集合
     */
	@Override
	public List<HjRolePrivileges> selectHjRolePrivilegesList(HjRolePrivileges hjRolePrivileges)
	{
	    return hjRolePrivilegesMapper.selectHjRolePrivilegesList(hjRolePrivileges);
	}
	
    /**
     * 新增角色-权限
     * 
     * @param hjRolePrivileges 角色-权限信息
     * @return 结果
     */
	@Override
	public int insertHjRolePrivileges(HjRolePrivileges hjRolePrivileges)
	{
	    return hjRolePrivilegesMapper.insertHjRolePrivileges(hjRolePrivileges);
	}
	
	/**
     * 修改角色-权限
     * 
     * @param hjRolePrivileges 角色-权限信息
     * @return 结果
     */
	@Override
	public int updateHjRolePrivileges(HjRolePrivileges hjRolePrivileges)
	{
	    return hjRolePrivilegesMapper.updateHjRolePrivileges(hjRolePrivileges);
	}

	/**
     * 删除角色-权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjRolePrivilegesByIds(String ids)
	{
		return hjRolePrivilegesMapper.deleteHjRolePrivilegesByIds(Convert.toStrArray(ids));
	}

	/**
	 * 角色添加权限
	 * @param roleId 角色id
	 * @param privilegesId 权限id
	 * @return
	 */
	@Override
	public Map<String, Object> insertRolePrivileges(Integer roleId, String privilegesId) {

		try {
			hjRolePrivilegesMapper.deleteHjRoleId(roleId); //删除角色权限表数据
			String [] id = privilegesId.split(",");
			for (int i = 0; i < id.length; i++) {
				HjRolePrivileges hjRolePrivileges = new HjRolePrivileges();
				hjRolePrivileges.setRoleId(roleId);
				hjRolePrivileges.setPrivilegesId(Integer.parseInt(id[i]));
				hjRolePrivilegesMapper.insertHjRolePrivileges(hjRolePrivileges);
			}
			return AjaxResult.success("添加成功！");
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"添加失败！");
		}
	}

	/**
	 * 根据角色查询权限
	 * @param privilegesRoleParam
	 * @return
	 */
	@Override
	public Map<String, Object> selectRolePrivileges(PrivilegesRoleParam privilegesRoleParam) {

		try {
			List<PrivilegesRoleParam> privilegesRoleParams = hjRolePrivilegesMapper.selectRolePrivileges(privilegesRoleParam);
			return AjaxResult.success(privilegesRoleParams);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error(-1, "查询失败！");
		}
	}

}
