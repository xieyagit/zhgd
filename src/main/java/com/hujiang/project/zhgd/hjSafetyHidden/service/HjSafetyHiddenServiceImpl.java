package com.hujiang.project.zhgd.hjSafetyHidden.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hujiang.project.zhgd.hjSafetyHidden.mapper.HjSafetyHiddenMapper;
import com.hujiang.project.zhgd.hjSafetyHidden.domain.HjSafetyHidden;
import com.hujiang.project.zhgd.hjSafetyHidden.service.IHjSafetyHiddenService;
import com.hujiang.common.support.Convert;

/**
 * 隐患类型 服务层实现
 * 
 * @author hujiang
 * @date 2019-07-10
 */
@Service
public class HjSafetyHiddenServiceImpl implements IHjSafetyHiddenService 
{
	@Autowired
	private HjSafetyHiddenMapper hjSafetyHiddenMapper;

	/**
     * 查询隐患类型信息
     * 
     * @param id 隐患类型ID
     * @return 隐患类型信息
     */
    @Override
	public HjSafetyHidden selectHjSafetyHiddenById(Integer id)
	{
	    return hjSafetyHiddenMapper.selectHjSafetyHiddenById(id);
	}
	
	/**
     * 查询隐患类型列表
     * 
     * @param hjSafetyHidden 隐患类型信息
     * @return 隐患类型集合
     */
	@Override
	public List<HjSafetyHidden> selectHjSafetyHiddenList(HjSafetyHidden hjSafetyHidden)
	{
	    return hjSafetyHiddenMapper.selectHjSafetyHiddenList(hjSafetyHidden);
	}
	
    /**
     * 新增隐患类型
     * 
     * @param hjSafetyHidden 隐患类型信息
     * @return 结果
     */
	@Override
	public int insertHjSafetyHidden(HjSafetyHidden hjSafetyHidden)
	{
	    return hjSafetyHiddenMapper.insertHjSafetyHidden(hjSafetyHidden);
	}
	
	/**
     * 修改隐患类型
     * 
     * @param hjSafetyHidden 隐患类型信息
     * @return 结果
     */
	@Override
	public int updateHjSafetyHidden(HjSafetyHidden hjSafetyHidden)
	{
	    return hjSafetyHiddenMapper.updateHjSafetyHidden(hjSafetyHidden);
	}

	/**
     * 删除隐患类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjSafetyHiddenByIds(String ids)
	{
		return hjSafetyHiddenMapper.deleteHjSafetyHiddenByIds(Convert.toStrArray(ids));
	}
	
}
