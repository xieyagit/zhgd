package com.hujiang.project.zhgd.hjRectify.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.hjRectify.mapper.HjRectifyMapper;
import com.hujiang.project.zhgd.hjRectify.domain.HjRectify;
import com.hujiang.project.zhgd.hjRectify.service.IHjRectifyService;
import com.hujiang.common.support.Convert;

/**
 * 整改记录 服务层实现
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Service
public class HjRectifyServiceImpl implements IHjRectifyService 
{
	@Autowired
	private HjRectifyMapper hjRectifyMapper;

	/**
     * 查询整改记录信息
     * 
     * @param id 整改记录ID
     * @return 整改记录信息
     */
    @Override
	public HjRectify selectHjRectifyById(Integer id)
	{
	    return hjRectifyMapper.selectHjRectifyById(id);
	}
	
	/**
     * 查询整改记录列表
     * 
     * @param hjRectify 整改记录信息
     * @return 整改记录集合
     */
	@Override
	public List<HjRectify> selectHjRectifyList(HjRectify hjRectify)
	{
	    return hjRectifyMapper.selectHjRectifyList(hjRectify);
	}
	
    /**
     * 新增整改记录
     * 
     * @param hjRectify 整改记录信息
     * @return 结果
     */
	@Override
	public int insertHjRectify(HjRectify hjRectify)
	{
	    return hjRectifyMapper.insertHjRectify(hjRectify);
	}
	
	/**
     * 修改整改记录
     * 
     * @param hjRectify 整改记录信息
     * @return 结果
     */
	@Override
	public int updateHjRectify(HjRectify hjRectify)
	{
	    return hjRectifyMapper.updateHjRectify(hjRectify);
	}

	/**
     * 删除整改记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteHjRectifyByIds(String ids)
	{
		return hjRectifyMapper.deleteHjRectifyByIds(Convert.toStrArray(ids));
	}
	
}
