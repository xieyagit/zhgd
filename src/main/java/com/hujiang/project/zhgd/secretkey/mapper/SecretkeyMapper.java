package com.hujiang.project.zhgd.secretkey.mapper;


import com.hujiang.project.zhgd.secretkey.domain.Secretkey;

import java.util.List;

/**
 * 智慧工地对接秘钥 数据层
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public interface SecretkeyMapper 
{
	/**
     * 查询智慧工地对接秘钥信息
     * 
     * @param id 智慧工地对接秘钥ID
     * @return 智慧工地对接秘钥信息
     */
	public Secretkey selectSecretkeyById(Long id);
	
	/**
     * 查询智慧工地对接秘钥列表
     * 
     * @param secretkey 智慧工地对接秘钥信息
     * @return 智慧工地对接秘钥集合
     */
	public List<Secretkey> selectSecretkeyList(Secretkey secretkey);
	
	/**
     * 新增智慧工地对接秘钥
     * 
     * @param secretkey 智慧工地对接秘钥信息
     * @return 结果
     */
	public int insertSecretkey(Secretkey secretkey);
	
	/**
     * 修改智慧工地对接秘钥
     * 
     * @param secretkey 智慧工地对接秘钥信息
     * @return 结果
     */
	public int updateSecretkey(Secretkey secretkey);
	
	/**
     * 删除智慧工地对接秘钥
     * 
     * @param id 智慧工地对接秘钥ID
     * @return 结果
     */
	public int deleteSecretkeyById(Long id);
	
	/**
     * 批量删除智慧工地对接秘钥
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSecretkeyByIds(String[] ids);
	
}