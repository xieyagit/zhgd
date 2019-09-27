package com.hujiang.project.zhgd.secretkey.service;

import com.hujiang.common.support.Convert;
import com.hujiang.project.zhgd.secretkey.domain.Secretkey;
import com.hujiang.project.zhgd.secretkey.mapper.SecretkeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 智慧工地对接秘钥 服务层实现
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Service
public class SecretkeyServiceImpl implements ISecretkeyService 
{
	@Autowired
	private SecretkeyMapper secretkeyMapper;

	/**
     * 查询智慧工地对接秘钥信息
     * 
     * @param id 智慧工地对接秘钥ID
     * @return 智慧工地对接秘钥信息
     */
    @Override
	public Secretkey selectSecretkeyById(Long id)
	{
	    return secretkeyMapper.selectSecretkeyById(id);
	}
	
	/**
     * 查询智慧工地对接秘钥列表
     * 
     * @param secretkey 智慧工地对接秘钥信息
     * @return 智慧工地对接秘钥集合
     */
	@Override
	public List<Secretkey> selectSecretkeyList(Secretkey secretkey)
	{
	    return secretkeyMapper.selectSecretkeyList(secretkey);
	}
	
    /**
     * 新增智慧工地对接秘钥
     * 
     * @param secretkey 智慧工地对接秘钥信息
     * @return 结果
     */
	@Override
	public int insertSecretkey(Secretkey secretkey)
	{
	    return secretkeyMapper.insertSecretkey(secretkey);
	}
	
	/**
     * 修改智慧工地对接秘钥
     * 
     * @param secretkey 智慧工地对接秘钥信息
     * @return 结果
     */
	@Override
	public int updateSecretkey(Secretkey secretkey)
	{
	    return secretkeyMapper.updateSecretkey(secretkey);
	}

	/**
     * 删除智慧工地对接秘钥对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSecretkeyByIds(String ids)
	{
		return secretkeyMapper.deleteSecretkeyByIds(Convert.toStrArray(ids));
	}
	
}
