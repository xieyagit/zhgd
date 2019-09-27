package com.hujiang.project.zhgd.hjSystemPrivileges.service;

import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.SystemRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjSystemPrivileges.mapper.HjSystemPrivilegesMapper;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.HjSystemPrivileges;
import com.hujiang.common.support.Convert;

/**
 * 系统-权限 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
@Transactional
public class HjSystemPrivilegesServiceImpl implements IHjSystemPrivilegesService 
{
	@Autowired
	private HjSystemPrivilegesMapper hjSystemPrivilegesMapper;

	/**
	 * 根据用户id查询pc或app菜单权限
	 * @param uid
	 * @param appOrPc
	 * @param parentId
	 * @return
	 */
	@Override
	public JSONArray selectHjSystemPrivilegesByUser(Integer uid, Integer appOrPc,Integer parentId){
		List<HjSystemPrivileges> hjSystemPrivileges = hjSystemPrivilegesMapper.selectHjSystemPrivilegesByUser(uid, appOrPc,parentId);
		JSONArray jsonArray = new JSONArray();
		for(HjSystemPrivileges h:hjSystemPrivileges){
			JSONObject object = new JSONObject();
			object.put("id",h.getId());
			object.put("privilegesName",h.getPrivilegesName());
			object.put("url",h.getUrl());
			jsonArray.add(object);
		}
		return jsonArray;
	}

	@Override
	public List<SystemRoleParam> getPrivilegesOne(SystemRoleParam systemRoleParam) {
		return hjSystemPrivilegesMapper.getPrivilegesOne(systemRoleParam);
	}

	@Override
	public List<SystemRoleParam> getPrivilegesTwo(SystemRoleParam systemRoleParam) {
		return hjSystemPrivilegesMapper.getPrivilegesTwo(systemRoleParam);
	}

	/**
     * 查询系统-权限信息
     * 
     * @param id 系统-权限ID
     * @return 系统-权限信息
     */
    @Override
	public HjSystemPrivileges selectHjSystemPrivilegesById(Integer id)
	{
	    return hjSystemPrivilegesMapper.selectHjSystemPrivilegesById(id);
	}
	
	/**
     * 查询系统-权限列表
     * 
     * @param hjSystemPrivileges 系统-权限信息
     * @return 系统-权限集合
     */
	@Override
	public List<HjSystemPrivileges> selectHjSystemPrivilegesList(HjSystemPrivileges hjSystemPrivileges)
	{
	    return hjSystemPrivilegesMapper.selectHjSystemPrivilegesList(hjSystemPrivileges);
	}
	
    /**
     * 新增系统-权限
     * 
     * @param hjSystemPrivileges 系统-权限信息
     * @return 结果
     */
	@Override
	public int insertHjSystemPrivileges(HjSystemPrivileges hjSystemPrivileges)
	{
	    return hjSystemPrivilegesMapper.insertHjSystemPrivileges(hjSystemPrivileges);
	}
	
	/**
     * 修改系统-权限
     * 
     * @param hjSystemPrivileges 系统-权限信息
     * @return 结果
     */
	@Override
	public int updateHjSystemPrivileges(HjSystemPrivileges hjSystemPrivileges)
	{
	    return hjSystemPrivilegesMapper.updateHjSystemPrivileges(hjSystemPrivileges);
	}

	/**
     * 删除系统-权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSystemPrivilegesByIds(String ids)
	{
		return hjSystemPrivilegesMapper.deleteHjSystemPrivilegesByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询权限列表
	 * @param systemRoleParam
	 * @return
	 */
	@Override
	public Map<String, Object> selectSystemPrivileges(SystemRoleParam systemRoleParam) {
		try {
			// 根据登录人查看对应的权限菜单
			List<SystemRoleParam> systemRoleParamList = hjSystemPrivilegesMapper.selectSystemPrivileges(systemRoleParam);
			return AjaxResult.success(systemRoleParamList);
		}catch (Exception e){
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败！");
		}
	}

	/**
	 * 添加权限菜单
	 * @param hjSystemPrivileges
	 * @return
	 */
	@Override
	public Map<String, Object> insertSystemPrivileges(HjSystemPrivileges hjSystemPrivileges) {

		try {
			hjSystemPrivilegesMapper.insertHjSystemPrivileges(hjSystemPrivileges);
			return AjaxResult.success("添加成功！");
		}catch (Exception e) {
		  e.printStackTrace();
			return AjaxResult.error(-1,"添加失败！");
		}
	}

	@Override
	public Map<String, Object> querySystemPrivileges(HjSystemPrivileges hjSystemPrivileges) {
		try {
			List<HjSystemPrivileges> list = hjSystemPrivilegesMapper.selectHjSystemPrivilegesList(hjSystemPrivileges);
			return AjaxResult.success(list);
		}catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error(-1,"查询失败！");
		}
	}


}
