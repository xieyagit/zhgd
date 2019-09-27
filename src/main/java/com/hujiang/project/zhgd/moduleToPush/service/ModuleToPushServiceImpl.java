package com.hujiang.project.zhgd.moduleToPush.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.moduleToPush.mapper.ModuleToPushMapper;
import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import com.hujiang.project.zhgd.moduleToPush.service.IModuleToPushService;
import com.hujiang.common.support.Convert;

/**
 * 用户推送开关 服务层实现
 * 
 * @author hujiang
 * @date 2019-09-05
 */
@Service
public class ModuleToPushServiceImpl implements IModuleToPushService 
{
	@Autowired
	private ModuleToPushMapper moduleToPushMapper;

	@Override
	public List<ModuleToPush> getModuleToPushList(ModuleToPush moduleToPush) {
		return moduleToPushMapper.getModuleToPushList(moduleToPush);
	}

	@Override
	public ModuleToPush selectModuleToPush(ModuleToPush moduleToPush) {
		return moduleToPushMapper.selectModuleToPush(moduleToPush);
	}

	/**
     * 查询用户推送开关信息
     * 
     * @param id 用户推送开关ID
     * @return 用户推送开关信息
     */
    @Override
	public ModuleToPush selectModuleToPushById(Integer id)
	{
	    return moduleToPushMapper.selectModuleToPushById(id);
	}
	
	/**
     * 查询用户推送开关列表
     * 
     * @param moduleToPush 用户推送开关信息
     * @return 用户推送开关集合
     */
	@Override
	public List<ModuleToPush> selectModuleToPushList(ModuleToPush moduleToPush)
	{
	    return moduleToPushMapper.selectModuleToPushList(moduleToPush);
	}
	
    /**
     * 新增用户推送开关
     * 
     * @param moduleToPush 用户推送开关信息
     * @return 结果
     */
	@Override
	public int insertModuleToPush(ModuleToPush moduleToPush)
	{
	    return moduleToPushMapper.insertModuleToPush(moduleToPush);
	}
	
	/**
     * 修改用户推送开关
     * 
     * @param moduleToPush 用户推送开关信息
     * @return 结果
     */
	@Override
	public int updateModuleToPush(ModuleToPush moduleToPush)
	{
	    return moduleToPushMapper.updateModuleToPush(moduleToPush);
	}

	/**
     * 删除用户推送开关对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteModuleToPushByIds(String ids)
	{
		return moduleToPushMapper.deleteModuleToPushByIds(Convert.toStrArray(ids));
	}
	
}
