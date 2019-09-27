package com.hujiang.project.zhgd.hjSafetyNopass.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.hjSafetyNopass.mapper.HjSafetyNopassMapper;
import com.hujiang.project.zhgd.hjSafetyNopass.domain.SafetyNopass;
import com.hujiang.common.support.Convert;

/**
 * 整改未通过 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-18
 */
@Service
public class HjSafetyNopassServiceImpl implements IHjSafetyNopassService 
{
	@Autowired
	private HjSafetyNopassMapper hjSafetyNopassMapper;


	/**
     * 查询整改未通过信息
     * 
     * @param id 整改未通过ID
     * @return 整改未通过信息
     */
    @Override
	public SafetyNopass selectHjSafetyNopassById(Integer id)
	{
	    return hjSafetyNopassMapper.selectHjSafetyNopassById(id);
	}
	
	/**
     * 查询整改未通过列表
     * 
     * @param hjSafetyNopass 整改未通过信息
     * @return 整改未通过集合
     */
	@Override
	public List<SafetyNopass> selectHjSafetyNopassList(SafetyNopass hjSafetyNopass)
	{
	    return hjSafetyNopassMapper.selectHjSafetyNopassList(hjSafetyNopass);
	}
	
    /**
     * 新增整改未通过
     * 
     * @param hjSafetyNopass 整改未通过信息
     * @return 结果
     */
	@Override
	public int insertHjSafetyNopass(SafetyNopass hjSafetyNopass)
	{
	    return hjSafetyNopassMapper.insertHjSafetyNopass(hjSafetyNopass);
	}
	
	/**
     * 修改整改未通过
     * 
     * @param hjSafetyNopass 整改未通过信息
     * @return 结果
     */
	@Override
	public int updateHjSafetyNopass(SafetyNopass hjSafetyNopass)
	{
	    return hjSafetyNopassMapper.updateHjSafetyNopass(hjSafetyNopass);
	}

	/**
     * 删除整改未通过对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSafetyNopassByIds(String ids)
	{
		return hjSafetyNopassMapper.deleteHjSafetyNopassByIds(Convert.toStrArray(ids));
	}
	
}
