package com.hujiang.project.zhgd.shortCreedNumber.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.shortCreedNumber.mapper.ShortCreedNumberMapper;
import com.hujiang.project.zhgd.shortCreedNumber.domain.ShortCreedNumber;
import com.hujiang.project.zhgd.shortCreedNumber.service.IShortCreedNumberService;
import com.hujiang.common.support.Convert;

/**
 * 项目短信条数 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-01
 */
@Service
public class ShortCreedNumberServiceImpl implements IShortCreedNumberService
{
	@Autowired
	private ShortCreedNumberMapper shortCreedNumberMapper;

	/**
     * 查询项目短信条数信息
     * 
     * @param id 项目短信条数ID
     * @return 项目短信条数信息
     */
    @Override
	public ShortCreedNumber selectShortCreedNumberById(Integer id)
	{
	    return shortCreedNumberMapper.selectShortCreedNumberById(id);
	}
	
	/**
     * 查询项目短信条数列表
     * 
     * @param shortCreedNumber 项目短信条数信息
     * @return 项目短信条数集合
     */
	@Override
	public ShortCreedNumber selectShortCreedNumber(ShortCreedNumber shortCreedNumber)
	{
	    return shortCreedNumberMapper.selectShortCreedNumber(shortCreedNumber);
	}

	@Override
	public List<ShortCreedNumber> selectShortCreedNumberList(ShortCreedNumber shortCreedNumber) {
		return shortCreedNumberMapper.selectShortCreedNumberList(shortCreedNumber);
	}

	/**
     * 新增项目短信条数
     * 
     * @param shortCreedNumber 项目短信条数信息
     * @return 结果
     */
	@Override
	public int insertShortCreedNumber(ShortCreedNumber shortCreedNumber)
	{
	    return shortCreedNumberMapper.insertShortCreedNumber(shortCreedNumber);
	}
	
	/**
     * 修改项目短信条数
     * 
     * @param shortCreedNumber 项目短信条数信息
     * @return 结果
     */
	@Override
	public int updateShortCreedNumber(ShortCreedNumber shortCreedNumber)
	{
	    return shortCreedNumberMapper.updateShortCreedNumber(shortCreedNumber);
	}

	/**
     * 删除项目短信条数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteShortCreedNumberByIds(String ids)
	{
		return shortCreedNumberMapper.deleteShortCreedNumberByIds(Convert.toStrArray(ids));
	}
	
}
